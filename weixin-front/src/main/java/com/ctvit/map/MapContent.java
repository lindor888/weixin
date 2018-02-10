package com.ctvit.map;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ctvit.bean.GraphicBean;
import com.ctvit.bean.InteractLocation;
import com.ctvit.bean.JsonBean;
import com.ctvit.bean.LocationMessage;
import com.ctvit.bean.Mapkeyword;
import com.ctvit.bean.Menus;
import com.ctvit.bean.News;
import com.ctvit.dao.LocationDao;
import com.ctvit.dao.MenusDao;
import com.ctvit.dao.ReservationDao;
import com.ctvit.framework.util.HttpUtils;
import com.ctvit.framework.util.Md5Encrypt;
import com.ctvit.util.ConfKit;
import com.ctvit.util.HttpKit;
import com.ctvit.util.InterfaceManagementClient;
import com.ctvit.util.LoadedText;
import com.ctvit.util.MessageUtil;
import com.ctvit.util.SendSeqUtils;

/**
 * 附件地理位置推送和签到
 * 
 * @author Admini
 * 
 */
public class MapContent extends Thread {
	private static final Logger LOGGER = Logger.getLogger(MapContent.class);
	private static final String KEY = "4NIBZ-7M4C3-NBX3V-3LWRZ-Z4F36-7XB7Q";
	private static final String REQ_URL = "http://apis.map.qq.com/ws/place/v1/search?boundary=nearby(%s,%s,1000)&keyword=%s&page_size=10&page_index=1&orderby=_distance&key=%s";
	private static final String MAP_TOOL_URL = "http://apis.map.qq.com/tools/routeplan/eword=%s&epointx=%s&epointy=%s?referer=myapp&key=%s";
	private static final String TITLE_FORMAT = "%s 电话：%s 距离：%s米  地址：%s";
	private static final String PIC_URL = "http://apis.map.qq.com/ws/streetview/v1/image?size=600x480&pano=%s&pitch=0&heading=0&key=%s";
	private static final String MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
	private String paramJson;
	private String msg;
	private String content;
	private String fromUser;
	private String toUser;
	private String id;
	private ReservationDao reservationDao = new ReservationDao();

	public MapContent(String content, String fromUser, String toUser, String id) {
		this.content = content;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.id = id;
	}

	public MapContent() {

	}

	// public void checkContent(String content,String fromUser,String toUser,String id) {
	@Override
	public void run() {
		System.out.println("进来了" + content + fromUser);
		if (content.contains("签到")) {
			this.addIntegral(fromUser);
		}
		if (content.contains("线索")) {
			this.newsContent(fromUser, content);
		}
		LoadedText loadText = new LoadedText();
		String appid = loadText.getAppId(id);
		String secret = loadText.getSecret(id);
		String accessToken = this.getAccessToken(appid, secret);
		News news = new News();
		MessageUtil messageUtil = new MessageUtil();
		GraphicBean graphicBean = new GraphicBean();
		List<JsonBean> listjson = new ArrayList<JsonBean>();
		String fileWaibuUrl = ConfKit.get("fileWaibuWebUrl");
		LocationMessage locationMessage = reservationDao.selectLocation(fromUser, toUser);
		List<Mapkeyword> mapkeyword = reservationDao.getMapKey();
		for (int i = 0; i < mapkeyword.size(); i++) {
			if (content.equals(mapkeyword.get(i).getMapKeywordName())) {
				String Latitude = locationMessage.getLatitude();
				String Longitude = locationMessage.getLongitude();
				try {
					String url = String.format(REQ_URL, Latitude, Longitude, URLEncoder.encode(content, "UTF-8"), KEY);
					String result = HttpKit.get(url);
					// System.out.println(result);
					JSONObject localJObj = JSONObject.fromObject(result);
					if (localJObj.getInt("count") > 0) {
						JSONArray pointJArray = localJObj.getJSONArray("data");
						for (int j = 0; j < pointJArray.size(); j++) {
							JsonBean jsonBean = new JsonBean();
							JSONObject point = pointJArray.getJSONObject(j);
							String title = point.getString("title");
							String category = point.getString("category");
							JSONObject location = point.getJSONObject("location");
							String lat = location.getString("lat");
							String lng = location.getString("lng");
							JSONObject pano = point.getJSONObject("pano");
							String panoID = pano.getString("id");
							String mapurl = String.format(MAP_TOOL_URL, URLEncoder.encode(title, "UTF-8"), lng, lat, KEY);
							String titleFormat = String.format(TITLE_FORMAT, title, point.getString("tel"), point.getString("_distance"), point.getString("address"));
							String picurl = String.format(PIC_URL, panoID, KEY);
							jsonBean.setTitle(titleFormat);
							jsonBean.setPicurl(picurl);
							jsonBean.setDescription(category);
							jsonBean.setUrl(mapurl);
							listjson.add(jsonBean);

						}

					} else {
						JsonBean jsonBean = new JsonBean();
						jsonBean.setTitle("该区域内没有相关场所");
						jsonBean.setPicurl(fileWaibuUrl + "/toupiao2/images/point.jpg");
						jsonBean.setDescription("该区域内没有相关场所");
						jsonBean.setUrl("");
						listjson.add(jsonBean);

					}
					news.setArticles(listjson);
					graphicBean.setMsgtype("news");
					graphicBean.setNews(news);
					graphicBean.setTouser(fromUser);
					paramJson = com.alibaba.fastjson.JSONObject.toJSONString(graphicBean);
					msg = messageUtil.sendTwenAll(appid, secret, paramJson);
					// msg = HttpUtils.doHttpPostJsonRequest(MESSAGE_URL.concat(accessToken), paramJson, "utf-8");
					LOGGER.info("返回结果：" + msg);
				} catch (Exception e) {
					JsonBean jsonBean = new JsonBean();
					jsonBean.setTitle("返回结果错误");
					jsonBean.setPicurl(fileWaibuUrl + "/toupiao2/images/point.jpg");
					jsonBean.setDescription("该区域内没有相关场所");
					jsonBean.setUrl("");
					listjson.add(jsonBean);
					try {
						news.setArticles(listjson);
						graphicBean.setMsgtype("news");
						graphicBean.setNews(news);
						graphicBean.setTouser(fromUser);
						paramJson = com.alibaba.fastjson.JSONObject.toJSONString(graphicBean);
						System.out.println("paramJson异常" + paramJson);
						msg = messageUtil.sendTwenAll(appid, secret, paramJson);
						// msg = HttpUtils.doHttpPostJsonRequest(MESSAGE_URL.concat(accessToken), paramJson, "utf-8");
					} catch (Exception e1) {

						e1.printStackTrace();
					}
					e.printStackTrace();
				}

			}

		}

	}

	/**
	 * 征集新闻线索推送
	 * 
	 * @param fromUser2
	 */
	private void newsContent(String openid, String content) {
		String text = "";
		String zhutiid = "";
		try {
			MessageUtil messageUtil = new MessageUtil();
			LocationDao dao = new LocationDao();
			// TODO id需要查询出来
			if (content.contains("征集")) {
				zhutiid = "15";
			}
			if (content.contains("新闻")) {
				zhutiid = "9";
			}
			List<InteractLocation> interactLocation = dao.contentlList(zhutiid);
			for (int i = 0; i < interactLocation.size(); i++) {

				text += "名称:" + interactLocation.get(i).getNickname() + ";主题:" + interactLocation.get(i).getTitle() + ";内容:" + interactLocation.get(i).getContent() + ";经度" + interactLocation.get(i).getLongitude() + ";纬度" + interactLocation.get(i).getLatitude() + "  ";

			}
			LoadedText loadText = new LoadedText();
			String appid = loadText.getAppId(id);
			String secret = loadText.getSecret(id);
			String result = messageUtil.sendText(appid, secret, openid, text);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 签到and增加积分
	 * 
	 * @param openid
	 */
	public String addIntegral(String openid) {
		String activeId = ConfKit.get("activeId");
		MessageUtil messageUtil = new MessageUtil();
		LoadedText loadText = new LoadedText();
		String appid = loadText.getAppId(id);
		String secret = loadText.getSecret(id);
		LocationDao dao = new LocationDao();
		int num = dao.selectintegralAddtime(activeId, openid);
		if (num == 0) {
			try {
				JSONObject object = new JSONObject();
				String prikey = "85522ff78c5ab6c8529320084b990727";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String sendTime = sdf.format(new Date());
				String sendSeq = SendSeqUtils.createSendSeq();
				int pointAdd = 10;
				String optTime = sdf.format(new Date());
				String appId = ConfKit.get("appID");
				object.put("sendTime", sendTime);
				object.put("sendSeq", sendSeq);
				object.put("userName", openid);
				object.put("appId", appId);
				object.put("activeId", activeId);
				object.put("point", pointAdd);
				object.put("optTime", optTime);
				String sign = Md5Encrypt.encryptUTF8(prikey + sendTime + sendSeq + openid + appId + activeId + pointAdd + optTime);
				object.put("sign", sign);
				// System.out.println("参数对象：" + object);
				String add_url = ConfKit.get("add_url");
				String jifen_add = HttpUtils.doHttpPostJsonRequest(add_url, com.alibaba.fastjson.JSONObject.toJSONString(object), "utf-8");
				if (StringUtils.isNotEmpty(jifen_add)) {
					// 将返回的数据格式化解析
					JSONObject resJson = JSONObject.fromObject(jifen_add);
					// resJson.get(key)
					String record = resJson.getString("retcode");
					if (record.equals("0")) {
						String score = dao.selectScore(appId, openid);
						String result = messageUtil.sendText(appid, secret, openid, "签到成功!当前积分为" + score);
						System.out.println(result);
						return "签到成功";
					}
				} else {
					String result = messageUtil.sendText(appid, secret, openid, "签到失败");
					return "签到失败";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String result;
			try {
				result = messageUtil.sendText(appid, secret, openid, "今日已签到!");
				System.out.println(result);
				return "今日已签到";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public String qiandao(String account, String materialId, String openId) {
		String str = null;
		MenusDao menusDao = new MenusDao();
		Menus menus = menusDao.select(account, materialId);
		System.out.println("men + = " + menus.getName());
		if (menus != null) {
			String name = menus.getName();
			// log.info(name);
			if (name.indexOf("签到") != -1 || name.indexOf("我来了") != -1) {
				str = this.addIntegral(openId);
			}
			// if (name.indexOf("我要答") != -1) {
			// str = this.responsemessage(openId);
			// }

		}
		return str;
	}

	private String responsemessage(String openId) {
		MessageUtil messageUtil = new MessageUtil();
		LoadedText loadText = new LoadedText();
		String appid = loadText.getAppId(id);
		String secret = loadText.getSecret(id);
		String result = null;
		try {
			result = messageUtil.sendText(appid, secret, openId, "您对现在的交通情况如何看待！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getAccessToken(String appid, String secret) {
		String access_token = null;
		String ccess_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&&appid=" + appid + "&&secret=" + secret;
		try {
			String tokenJson = InterfaceManagementClient.httpClientGetStream("", ccess_token_url);
			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(tokenJson);
			access_token = json.getString("access_token");

			if (access_token == null) {
				System.out.println("验证失败！");
			} else {
				System.out.println("验证成功！access_token" + access_token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}
}
