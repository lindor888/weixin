package com.ctvit.weixin;

/**
 * 处理微信服务器发起请求
 *
 */

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ctvit.bean.Articles;
import com.ctvit.bean.Followers;
import com.ctvit.bean.InMessage;
import com.ctvit.bean.Interact;
import com.ctvit.bean.LocationMessage;
import com.ctvit.bean.OutMessage;
import com.ctvit.bean.TextOutMessage;
import com.ctvit.dao.FollowerDao;
import com.ctvit.dao.InteractDao;
import com.ctvit.dao.LocationDao;
import com.ctvit.location.service.LocationService;
import com.ctvit.location.service.impl.LocationServiceImpl;
import com.ctvit.map.MapContent;
import com.ctvit.thread.CatchAudioThread;
import com.ctvit.thread.CatchImageThread;
import com.ctvit.thread.CatchVideoThread;
import com.ctvit.util.ConfKit;
import com.ctvit.util.EmojiFilter;
import com.ctvit.util.HttpKit;
import com.ctvit.util.LoadedText;
import com.ctvit.util.WeixinUtil;
import com.ctvit.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;

public class ReceiveMessage {

	private static final Logger logger = Logger.getLogger(ReceiveMessage.class);

	// 用户基本信息
	private static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";

	private FollowerDao followersDao = new FollowerDao();

	private InteractDao interactDao = new InteractDao();

	private LocationDao locationDao = new LocationDao();

	private LocationMessage locationMessage = new LocationMessage();

	@SuppressWarnings("static-access")
	public String sendMessage(String content, String id) {
		System.out.println(content);
		XStream xs = XStreamFactory.init(false);
		LoadedText loadText = new LoadedText();
		String omg = "";
		// 图片内网地址
		String fileWebUrl = ConfKit.get("fileWebUrl");
		// 图片公网地址
		String fileWaibuUrl = ConfKit.get("fileWaibuWebUrl");
		try {
			xs.alias("xml", InMessage.class);
			InMessage msg = (InMessage) xs.fromXML(content);

			// 用户信息处理，用户不存在则入库
			if (followersDao.selectCount(msg.getFromUserName()) == 0) {
				Followers value = this.getUserInfo(msg.getFromUserName(), id);
				value.setGroupsId(1);
				value.setWaccountId(id);
				followersDao.insert(value);
			}

			String type = msg.getMsgType();

			if ("text".equals(type)) {
				Followers follower = this.getUserInfo(msg.getFromUserName(), id);
				followersDao.updateUser(follower);
				System.out.println("msg.getContent()" + msg.getContent());
				omg = loadText.getOutMessage(msg.getContent(), id);
				// 这里需要入库，用户id、内容
				Interact interact = new Interact();
				interact.setWaccountId(id);
				interact.setOpenId(msg.getFromUserName());
				interact.setContent(msg.getContent());
				interact.setType(Interact.TYPE_TEXT);
				interactDao.insert(interact);
				logger.info("[text:]ToUserName:" + msg.getFromUserName() + "FromUserName:" + msg.getToUserName() + "content:" + msg.getContent());
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
				// 此功能为推送地理位置附近信息和输入签到
				// MapContent mt = new MapContent(msg.getContent(), msg.getFromUserName(), msg.getToUserName(), id);
				// mt.start();

				// 将图片内网地址改为外网地址
				omg = omg.replaceAll(fileWebUrl, fileWaibuUrl);
				omg = setMsgInfoNew(omg, msg);

			} else if ("image".equals(type)) {
				Followers follower = this.getUserInfo(msg.getFromUserName(), id);
				followersDao.updateUser(follower);
				// 这里需要抓取图片并入库
				CatchImageThread thread = new CatchImageThread(msg.getPicUrl(), id, msg.getFromUserName());
				thread.start();
				// 抓取视频
			} else if ("video".equals(type)) {
				Followers follower = this.getUserInfo(msg.getFromUserName(), id);
				followersDao.updateUser(follower);
				CatchVideoThread thread = new CatchVideoThread(msg.getMediaId(), id, msg.getFromUserName());
				thread.start();
				// 抓取音频
			} else if ("voice".equals(type)) {
				Followers follower = this.getUserInfo(msg.getFromUserName(), id);
				followersDao.updateUser(follower);
				CatchAudioThread thread = new CatchAudioThread(msg.getMediaId(), id, msg.getFromUserName());
				thread.start();
			} else if ("location".equals(type)) {
				// 发送地理位置
				// Followers follower = this.getUserInfo(msg.getFromUserName(), id);
				// followersDao.updateUser(follower);
				// omg = locationOperation(id, msg);
			} else if ("link".equals(type)) {

			} else if ("event".equals(type)) {
				OutMessage oms = new TextOutMessage();
				System.out.println("msg.getEvent()=" + msg.getEvent());
				// 关注入库
				if ("subscribe".equals(msg.getEvent())) {
					// 如果有Ticket则，返回ticket绑定的素材
					String ticket = msg.getTicket();
					if (StringUtils.isNotBlank(ticket)) {
						String eventKey = msg.getEventKey();
						if ("qrscene_1".equals(eventKey)) {
							omg = loadText.getClickOutMessage("Grap1432795981147101", id);
						} else if ("qrscene_2".equals(eventKey)) {
							omg = loadText.getClickOutMessage("Grap1432796091377102", id);
						}
						omg = omg.replaceAll(fileWebUrl, fileWaibuUrl);
						omg = setMsgInfoNew(omg, msg);
					} else {
						omg = loadText.showwelcome(id);
						// 将图片内网地址改为外网地址
						// omg = omg.replaceAll(fileWebUrl, fileWaibuUrl);
						omg = setMsgInfoNew(omg, msg);
					}
					// 取消关注
				} else if ("unsubscribe".equals(msg.getEvent())) {
					oms = new TextOutMessage();
					((TextOutMessage) oms).setContent("取消订阅成功");
					logger.info("[unsubscribe:]FromUserName:" + msg.getFromUserName() + " ToUserName:" + msg.getToUserName() + "content:" + ((TextOutMessage) oms).getContent());
					// 删除数据库数据
					followersDao.delete(msg.getFromUserName());
					setMsgInfo(oms, msg);
					xs = XStreamFactory.init(true);
					xs.alias("xml", oms.getClass());
					xs.alias("item", Articles.class);
					omg = xs.toXML(oms);
				} else if ("CLICK".equals(msg.getEvent())) {
					String EventKey = msg.getEventKey();
					omg = loadText.getClickOutMessage(EventKey, id);
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
						/*
						 * if (omg.indexOf("签到") != -1) { MapContent mapContent = new MapContent("", msg.getFromUserName(), msg.getToUserName(), id); mapContent.addIntegral(msg.getFromUserName()); return ""; }
						 */
					}
					// 签到的特殊处理，通过素材id和公众号id查询菜单名称是否为签到，如果为签到则调用积分的接口
					logger.info("begin sign");
					// SignInStrategy signInStrategy = new SignInStrategy(id, EventKey, msg.getFromUserName());
					// String returnStr = signInStrategy.run();
					// if (!"".equals(returnStr)) {
					// omg = returnStr;
					// }
					MapContent mt = new MapContent(msg.getContent(), msg.getFromUserName(), msg.getToUserName(), id);
					String returnmsg = mt.qiandao(id, EventKey, msg.getFromUserName());
					if (!"".equals(returnmsg) && returnmsg != null) {
						return "";
					}
					// 将图片内网地址改为外网地址
					omg = omg.replaceAll(fileWebUrl, fileWaibuUrl);
					omg = setMsgInfoNew(omg, msg);
				} else if ("SCAN".equals(msg.getEvent())) {
					String eventKey = msg.getEventKey();
					if ("1".equals(eventKey)) {
						omg = loadText.getClickOutMessage("Grap1432795981147101", id);
					} else if ("2".equals(eventKey)) {
						omg = loadText.getClickOutMessage("Grap1432796091377102", id);
					}
					omg = omg.replaceAll(fileWebUrl, fileWaibuUrl);
					omg = setMsgInfoNew(omg, msg);

				} else if ("LOCATION".equals(msg.getEvent())) {
					// 获取地图信息
					// omg = locationOperation(id, msg);
					this.locationInsert(id, msg);
				} else if ("VIEW".equals(msg.getEvent())) {
					String EventKey = msg.getEventKey();
					omg = loadText.getClickOutMessage(EventKey, id);
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
								System.out.println("omg= " + omg);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("[sendMessageError]" + e.getLocalizedMessage());
			e.printStackTrace();
			return loadText.showErro();
		}

		return omg;
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

	private void setMsgInfo(OutMessage oms, InMessage msg) throws Exception {
		// 设置发送信息
		Class<?> outMsg = oms.getClass().getSuperclass();
		Field CreateTime = outMsg.getDeclaredField("CreateTime");
		Field ToUserName = outMsg.getDeclaredField("ToUserName");
		Field FromUserName = outMsg.getDeclaredField("FromUserName");

		ToUserName.setAccessible(true);
		CreateTime.setAccessible(true);
		FromUserName.setAccessible(true);

		CreateTime.set(oms, new Date().getTime());
		ToUserName.set(oms, msg.getFromUserName());
		FromUserName.set(oms, msg.getToUserName());
	}

	/**
	 * 获取用户信息
	 * 
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public static Followers getUserInfo(String openid, String id) throws Exception {
		String sex = "";

		LoadedText loadText = new LoadedText();
		String appid = loadText.getAppId(id);
		String secret = loadText.getSecret(id);
		String accessToken = WeixinUtil.getAccessToken(appid, secret);
		System.out.println("accessToken++++++++++" + accessToken);
		String lang = "zh_CN";
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("openid", openid);
		params.put("lang", lang);
		String jsonStr = HttpKit.get(USER_INFO, params);
		Followers followers = new Followers();
		if (StringUtils.isNotEmpty(jsonStr)) {
			JSONObject obj = JSONObject.fromObject(jsonStr);
			followers.setOpenid(openid.toString());
			followers.setSubscribe(tranJson(obj, "subscribe"));
			followers.setNickname(EmojiFilter.filterEmoji(" " + tranJson(obj, "nickname")));
			sex = tranJson(obj, "sex").equals("1") ? "男" : "女";
			followers.setSex(sex);
			followers.setCity(tranJson(obj, "city"));
			followers.setCountry(tranJson(obj, "province"));
			followers.setProvince(tranJson(obj, "country"));
			// 这里需要把头像抓下来
			String headimgurl = tranJson(obj, "headimgurl");
			if (!"".equals(headimgurl)) {
				headimgurl = WeixinUtil.downloadImageUrl(headimgurl);
			}

			followers.setHeadimgurl(headimgurl);
			followers.setSubscribeTime(tranJson(obj, "subscribe_time"));
			if (obj.get("errcode") != null) {
				System.out.println(obj.getString("errmsg"));
				throw new Exception(obj.getString("errmsg"));

			}
			return followers;
		}
		return followers;
	}

	/**
	 * 解析数据
	 * 
	 * @param object
	 * @param field
	 * @return
	 */
	private static String tranJson(JSONObject object, String field) {
		try {
			String content = object.getString(field);
			return content;
		} catch (Exception e) {
			return "";
		}
	}

	private LocationService locationService = new LocationServiceImpl();

	private String locationOperation(String id, InMessage msg) {
		String omg;
		omg = locationService.locationNews(msg, id);
		if (omg == null || "".equals(omg)) {
			return "";
		}
		omg = setMsgInfoNew(omg, msg);
		return omg;
	}

	/**
	 * 更新地理位置
	 * 
	 * @param id
	 * @param msg
	 */
	private void locationInsert(String id, InMessage msg) {
		int num;
		try {
			num = locationDao.select(msg.getFromUserName());
			locationMessage.setToUserName(msg.getToUserName());
			locationMessage.setFromUserName(msg.getFromUserName());
			locationMessage.setMsgType(msg.getMsgType());
			locationMessage.setEvent(msg.getEvent());
			locationMessage.setCreateTime(msg.getCreateTime() + "");
			locationMessage.setLatitude(msg.getLatitude());
			locationMessage.setLongitude(msg.getLongitude());
			locationMessage.setPrecision(msg.getPrecision());
			locationMessage.setOriginX(msg.getLatitude());
			locationMessage.setOriginY(msg.getLongitude());
			if (num < 1) {
				System.out.println("插入操作");
				logger.info("成功");
				locationDao.insert(locationMessage);
			} else {
				System.out.println("更新操作");
				locationDao.update(locationMessage);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}