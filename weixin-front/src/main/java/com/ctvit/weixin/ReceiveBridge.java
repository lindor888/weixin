package com.ctvit.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ctvit.bean.Followers;
import com.ctvit.bean.InMessage;
import com.ctvit.bean.Interact;
import com.ctvit.dao.FollowerDao;
import com.ctvit.dao.InteractDao;
import com.ctvit.dao.MenusDao;
import com.ctvit.dao.WaccountDao;
import com.ctvit.thread.CatchBridgeAudioThread;
import com.ctvit.thread.CatchBridgeVideoThread;
import com.ctvit.thread.CatchImageThread;
import com.ctvit.util.ConfKit;
import com.ctvit.util.HttpKit;
import com.ctvit.util.LoadedText;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.util.Native2AsciiUtils;
import com.ctvit.util.WeixinUtil;
import com.ctvit.util.XStreamFactory;
import com.danga.MemCached.MemCachedClient;
import com.thoughtworks.xstream.XStream;

/**
 * 注册到天脉的接口，获取天脉推送的信息
 * 
 * @author tqc
 * 
 */
public class ReceiveBridge extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5184955642903005476L;

	private static final Logger log = Logger.getLogger(ReceiveBridge.class);

	private static final String API_TOKEN = ConfKit.get("apiToken");

	private FollowerDao followersDao = new FollowerDao();

	private InteractDao interactDao = new InteractDao();

	private MenusDao menusDao = new MenusDao();

	private WaccountDao waccountDao = new WaccountDao();

	MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();

	@SuppressWarnings("static-access")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bridgeToken = req.getParameter("wx_token");
		log.info("bridge token =" + bridgeToken);

		if (StringUtils.isBlank(bridgeToken)) {
			resp.getWriter().print("{\"message\":\"no wx_token\"}");
			return;
		}
		// 通过bridgeToken查询出公众号id
		String waccountId = waccountDao.selectWaccountIdByBridgeToken(bridgeToken);
		log.info("waccountId = " + waccountId);
		// waccountId + "_bridge"
		memCachedClientInstance.set(waccountId + "_bridge", bridgeToken);
		WeixinHandler weixinHandler = new WeixinHandler(req, resp);
		// 天脉推过来的数据
		String message = weixinHandler.readStreamParameter(req.getInputStream());
		log.info("receive message :" + message);

		XStream xs = XStreamFactory.init(false);
		try {
			xs.alias("xml", InMessage.class);
			InMessage msg = (InMessage) xs.fromXML(message);
			LoadedText loadText = new LoadedText();
			String omg = "";
			// 图片内网地址
			String fileWebUrl = ConfKit.get("fileWebUrl");
			// 图片公网地址
			String fileWaibuUrl = ConfKit.get("fileWaibuWebUrl");

			// 用户信息处理，用户不存在则需要调用天脉的接口获取用户信息
			if (followersDao.selectCount(msg.getFromUserName()) == 0) {
				Followers value = this.getUserInfo(msg.getFromUserName(), bridgeToken);
				value.setGroupsId(1);
				value.setWaccountId(waccountId);
				followersDao.insert(value);
			}

			String type = msg.getMsgType();

			// 输入的内容
			if ("text".equals(type)) {
				omg = loadText.getOutMessage(msg.getContent(), waccountId);
				// 这里需要入库，用户id、内容
				Interact interact = new Interact();
				interact.setWaccountId(waccountId);
				interact.setOpenId(msg.getFromUserName());
				interact.setContent(msg.getContent());
				interact.setType(Interact.TYPE_TEXT);
				interactDao.insert(interact);

				if (omg != null && !"".equals(omg)) {
					if (omg.indexOf("<Url>") != -1) {
						String url = omg.substring(omg.indexOf("<Url>") + 5, omg.indexOf("</Url>"));
						if (!"".equals(url)) {
							String nowUrl = "";
							if (url.indexOf("?") != -1) {
								nowUrl = url + "&amp;r=" + System.currentTimeMillis() + "&amp;openId=" + msg.getFromUserName();

							} else {
								nowUrl = url + "?r=" + System.currentTimeMillis() + "&amp;openId=" + msg.getFromUserName();
							}
							omg = omg.replace("<Url>" + url + "</Url>", "<Url>" + nowUrl + "</Url>");
						}
					}
				}
				// 将图片内网地址改为外网地址
				omg = omg.replaceAll(fileWebUrl, fileWaibuUrl);
				omg = setMsgInfoNew(omg, msg);
				// 抓取图片
			} else if ("image".equals(type)) {
				// 这里需要抓取图片并入库
				CatchImageThread thread = new CatchImageThread(msg.getPicUrl(), waccountId, msg.getFromUserName());
				thread.start();
				// 抓取视频
			} else if ("video".equals(type)) {
				CatchBridgeVideoThread thread = new CatchBridgeVideoThread(msg.getLocalUrl(), waccountId, msg.getFromUserName());
				thread.start();
				// 抓取音频
			} else if ("voice".equals(type)) {
				log.info("localUrl : " + msg.getLocalUrl());
				CatchBridgeAudioThread thread = new CatchBridgeAudioThread(msg.getLocalUrl(), waccountId, msg.getFromUserName());
				thread.start();
			} else if ("event".equals(type)) {
				// 关注入库
				if ("subscribe".equals(msg.getEvent())) {
					omg = loadText.showwelcome(waccountId);
					// 将图片内网地址改为外网地址
					omg = omg.replaceAll(fileWebUrl, fileWaibuUrl);
					omg = setMsgInfoNew(omg, msg);
					// 取消关注
				} else if ("unsubscribe".equals(msg.getEvent())) {
					// 删除数据库数据
					followersDao.delete(msg.getFromUserName());
				} else if ("CLICK".equals(msg.getEvent())) {
					// 这里需要根据菜单名称进行匹配
					log.info("menue name : " + msg.getEventKey());
					String EventKey = URLDecoder.decode(msg.getEventKey(), "utf-8");// 菜单名称
					EventKey = menusDao.selectByName(waccountId, EventKey).getMaterialId();

					omg = loadText.getClickOutMessage(EventKey, waccountId);
					if (omg != null && !"".equals(omg)) {
						if (omg.indexOf("<Url>") != -1) {
							String url = omg.substring(omg.indexOf("<Url>") + 5, omg.indexOf("</Url>"));
							System.out.println("url=" + url);
							if (!"".equals(url)) {
								String nowUrl = "";
								if (url.indexOf("?") != -1) {
									nowUrl = url + "&amp;r=" + System.currentTimeMillis() + "&amp;openId=" + msg.getFromUserName();
								} else {
									nowUrl = url + "?r=" + System.currentTimeMillis() + "&amp;openId=" + msg.getFromUserName();
								}
								omg = omg.replace("<Url>" + url + "</Url>", "<Url>" + nowUrl + "</Url>");
							}
						}
					}
					// 签到的特殊处理，通过素材id和公众号id查询菜单名称是否为签到，如果为签到则调用积分的接口
					/*
					 * log.info("begin sign"); SignInStrategy signInStrategy = new SignInStrategy(waccountId, EventKey, msg.getFromUserName()); String returnStr = signInStrategy.run(); if(!"".equals(returnStr)){ omg = returnStr; }
					 */
					// 将图片内网地址改为外网地址
					omg = omg.replaceAll(fileWebUrl, fileWaibuUrl);
					omg = setMsgInfoNew(omg, msg);
				}
			}
			// 将回复信息推送给天脉
			if (!"".equals(omg)) {
				try {
					String publishResult = publishToBridge(omg, bridgeToken);
					log.info(publishResult);
				} catch (Exception e) {
					log.error("", e);
					resp.getWriter().print("{\"message\":\"push error\"}");
					return;
				}
			}
			resp.getWriter().print("{\"message\":\"success\"}");

		} catch (Exception e) {
			log.error("", e);
			resp.getWriter().print("{\"message\":\"receive error\"}");
		}

	}

	/**
	 * 从天脉接口获取用户信息
	 * 
	 * @param openId
	 * @param bridgeToken
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws DocumentException
	 */
	private Followers getUserInfo(String openId, String bridgeToken) {
		Followers followers = new Followers();
		String url = "http://mb.mtq.tvm.cn/rest/PlatformUserinfo?token=" + API_TOKEN;
		url = url + "&openid=" + openId + "&wx_token=" + bridgeToken;
		try {
			String result = HttpKit.get(url);
			log.info("user message : " + result);
			Document doc = DocumentHelper.parseText(result);
			Element data = doc.getRootElement().element("data").element("data");
			String nickname = data.elementText("nickname");
			String city = data.elementText("city");
			String province = data.elementText("province");
			String country = data.elementText("country");
			String headimgurl = data.elementText("weixin_avatar_url");
			if (!"".equals(headimgurl)) {
				headimgurl = WeixinUtil.downloadImageUrl(headimgurl);
			}
			String subscribeTime = data.elementText("add_time");

			followers.setOpenid(openId);
			followers.setNickname(nickname);
			followers.setCity(city);
			followers.setProvince(province);
			followers.setCountry(country);
			followers.setHeadimgurl(headimgurl);
			followers.setSubscribeTime(subscribeTime);

		} catch (Exception e) {
			log.error("", e);
		}
		return followers;
	}

	/**
	 * 将回复信息post给天脉
	 * 
	 * @param omg
	 * @param bridgeToken
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String publishToBridge(String omg, String bridgeToken) throws Exception {
		String result = "";
		String url = "http://mb.mtq.tvm.cn/rest/wxpush?token=" + API_TOKEN;

		Document doc = DocumentHelper.parseText(omg);
		Element root = doc.getRootElement();
		String msgType = root.elementText("MsgType");
		String params = "weixin_token=" + bridgeToken;
		String openId = root.elementText("ToUserName");
		params += "&openids=" + openId;
		// 文本
		if ("text".equals(msgType)) {
			params += "&weixin_type=text";
			String content = root.elementText("Content") == null ? "" : URLEncoder.encode(root.elementText("Content").toString(), "utf-8");
			params += "&content=" + content;
			log.info(params);

			// 图文
		} else if ("news".equals(msgType)) {
			params += "&weixin_type=news";

			String contentStr = "{\"articles\":[";

			List<Element> elementList = root.element("Articles").elements("item");
			for (Element e : elementList) {
				contentStr += "{";
				contentStr += "\"title\":\"";
				contentStr += e.elementText("Title") == null ? "" : Native2AsciiUtils.encode(e.elementText("Title").toString());
				contentStr += "\",";
				contentStr += "\"description\":\"";
				contentStr += e.elementText("Description") == null ? "" : Native2AsciiUtils.encode(e.elementText("Description").toString());
				contentStr += "\",";
				contentStr += "\"url\":\"";
				contentStr += e.elementText("Url") == null ? "" : Native2AsciiUtils.encode(e.elementText("Url").toString());
				contentStr += "\",";
				contentStr += "\"picurl\":\"";
				contentStr += e.elementText("PicUrl") == null ? "" : Native2AsciiUtils.encode(e.elementText("PicUrl").toString());
				contentStr += "\"";
				contentStr += "},";
			}

			if (elementList.size() > 0) {
				contentStr = contentStr.substring(0, contentStr.length() - 1);
			}
			contentStr += "]}";
			log.info("contentStr ： " + contentStr);
			contentStr = URLEncoder.encode(URLEncoder.encode(contentStr, "utf-8"), "utf-8");
			params += "&content=" + contentStr;
			log.info(params);
		}

		result = HttpKit.post(url, params);
		return result;
	}

	private String setMsgInfoNew(String oms, InMessage msg) {
		// 设置发送信息
		String ToUserName = msg.getFromUserName();
		String FromUserName = msg.getToUserName();
		String CreateTime = new Date().getTime() + "";
		oms = oms.replace("%1$s", ToUserName);
		oms = oms.replace("%2$s", FromUserName);
		oms = oms.replace("%3$s", CreateTime);
		return oms;
	}

}
