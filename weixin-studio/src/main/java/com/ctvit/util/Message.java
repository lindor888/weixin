package com.ctvit.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 客服消息接口
 * 
 * @author L.cm
 * @date 2013-11-5 下午3:32:30
 * @description 当用户主动发消息给公众号的时候（包括发送信息、点击自定义菜单、订阅事件、扫描二维码事件、支付成功事件、用户维权），微信将会把消息数据推送给开发者，开发者在一段时间内（目前修改为48小时）可以调用客服消息接口，通过POST一个JSON数据包来发送消息给普通用户，在48小时内不限制发送次数。此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加优质的服务。
 */
public class Message {

	private static final String MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
	private static final String MESSAGE_ALL_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";

	/**
	 * 发送客服消息
	 * 
	 * @param accessToken
	 * @param message
	 * @return
	 * @throws Exception
	 */
	private String sendMsg(String accessToken, Map<String, Object> message) throws Exception {
		String reslut = HttpKit.post(MESSAGE_URL.concat(accessToken), JSONObject.toJSONString(message));
		return reslut;
	}

	/**
	 * 发送文本客服消息
	 * 
	 * @param openId
	 * @param text
	 * @throws Exception
	 */
	public String sendText(String appid, String secret, String openId, String text) throws Exception {
		String accessToken = this.getAccessToken(appid, secret);
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> textObj = new HashMap<String, Object>();
		textObj.put("content", text);
		json.put("touser", openId);
		json.put("msgtype", "text");
		json.put("text", textObj);
		String reslut = sendMsg(accessToken, json);
		return reslut;
	}

	/**
	 * 发送图片消息
	 * 
	 * @param accessToken
	 * @param openId
	 * @param media_id
	 * @return
	 * @throws Exception
	 */
	public String SendImage(String accessToken, String openId, String media_id) throws Exception {
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> textObj = new HashMap<String, Object>();
		textObj.put("media_id", media_id);
		json.put("touser", openId);
		json.put("msgtype", "image");
		json.put("image", textObj);
		String reslut = sendMsg(accessToken, json);
		return reslut;
	}

	/**
	 * 发送语言回复
	 * 
	 * @param accessToken
	 * @param openId
	 * @param media_id
	 * @return
	 * @throws Exception
	 */
	public String SendVoice(String accessToken, String openId, String media_id) throws Exception {
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> textObj = new HashMap<String, Object>();
		textObj.put("media_id", media_id);
		json.put("touser", openId);
		json.put("msgtype", "voice");
		json.put("voice", textObj);
		String reslut = sendMsg(accessToken, json);
		return reslut;
	}

	/**
	 * 发送视频回复
	 * 
	 * @param accessToken
	 * @param openId
	 * @param media_id
	 * @param title
	 * @param description
	 * @return
	 * @throws Exception
	 */
	public String SendVideo(String accessToken, String openId, String media_id, String title, String description) throws Exception {
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> textObj = new HashMap<String, Object>();
		textObj.put("media_id", media_id);
		textObj.put("title", title);
		textObj.put("description", description);

		json.put("touser", openId);
		json.put("msgtype", "video");
		json.put("video", textObj);
		String reslut = sendMsg(accessToken, json);
		return reslut;
	}

	/**
	 * 发送音乐回复
	 * 
	 * @param accessToken
	 * @param openId
	 * @param musicurl
	 * @param hqmusicurl
	 * @param thumb_media_id
	 * @param title
	 * @param description
	 * @return
	 * @throws Exception
	 */
	public String SendMusic(String accessToken, String openId, String musicurl, String hqmusicurl, String thumb_media_id, String title, String description) throws Exception {
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> textObj = new HashMap<String, Object>();
		textObj.put("musicurl", musicurl);
		textObj.put("hqmusicurl", hqmusicurl);
		textObj.put("thumb_media_id", thumb_media_id);
		textObj.put("title", title);
		textObj.put("description", description);

		json.put("touser", openId);
		json.put("msgtype", "music");
		json.put("music", textObj);
		String reslut = sendMsg(accessToken, json);
		return reslut;
	}

	/**
	 * 获取token
	 * 
	 * @return
	 */
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

	/**
	 * 将回复信息post给天脉
	 * 
	 * @param omg
	 * @param bridgeToken
	 * @return
	 */
	public String publishToBridge(String content, String bridgeToken, String openId) throws Exception {
		String result = "";
		String apiToken = ResourceLoader.getInstance().getConfig().getProperty("apiToken");
		String url = "http://mb.mtq.tvm.cn/rest/wxpush?token=" + apiToken;
		String params = "weixin_token=" + bridgeToken;
		params += "&openids=" + openId;
		params += "&weixin_type=text";
		params += "&content=" + URLEncoder.encode(content, "utf-8");
		System.out.println("参数==" + params);
		System.out.println("url ===" + url);
		result = HttpKit.post(url, params);
		return result;
	}

	public String publishToBridgeAll(String content, String bridgeToken, List<String> openid) {
		String result = "";
		try {
			String apiToken = ResourceLoader.getInstance().getConfig().getProperty("apiToken");
			String url = "http://mb.mtq.tvm.cn/rest/wxpush?token=" + apiToken;
			String params = "weixin_token=" + bridgeToken;
			params += "&openids=" + openid;
			params += "&weixin_type=text";
			params += "&content=" + URLEncoder.encode(content, "utf-8");
			result = HttpKit.post(url, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 文本推送
	 * 
	 * @param appid
	 * @param secret
	 * @param openid
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String sendTextAll(String appid, String secret, List<String> openid, String content) throws Exception {
		String accessToken = this.getAccessToken(appid, secret);
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> textObj = new HashMap<String, Object>();
		textObj.put("content", content);
		json.put("touser", openid);
		json.put("msgtype", "text");
		json.put("text", textObj);
		String reslut = sendMsgAll(accessToken, json);
		System.out.println("准备发送");
		return reslut;
	}

	/**
	 * 图文推送
	 * 
	 * @param appid
	 * @param secret
	 * @param openid
	 * @param paramJson
	 * @return
	 * @throws Exception
	 */
	public String sendTwenAll(String appid, String secret, String paramJson) throws Exception {
		String accessToken = this.getAccessToken(appid, secret);
		String reslut = sendtuwenMsgAll(accessToken, paramJson);
		System.out.println("准备发送");
		return reslut;
	}

	private String sendtuwenMsgAll(String accessToken, String paramJson) throws Exception {
		String reslut = HttpKit.post(MESSAGE_ALL_URL.concat(accessToken), paramJson);
		return reslut;
	}

	public String sendTwen(String appid, String secret, String paramJson, List<String> openid) throws Exception {
		String accessToken = this.getAccessToken(appid, secret);
		String reslut = sendtuwen(accessToken, paramJson, openid);
		System.out.println("准备发送");
		return reslut;
	}

	private String sendtuwen(String accessToken, String paramJson, List<String> openid) throws Exception {
		String reslut = null;
		if (openid.size() == 1) {
			reslut = HttpKit.post(MESSAGE_URL.concat(accessToken), paramJson);
		} else {
			reslut = HttpKit.post(MESSAGE_ALL_URL.concat(accessToken), paramJson);
		}
		return reslut;
	}

	private String sendMsgAll(String accessToken, Map<String, Object> message) throws Exception {
		System.out.println("发送。。。。");
		String reslut = HttpKit.post(MESSAGE_ALL_URL.concat(accessToken), JSONObject.toJSONString(message));
		System.out.println("发送完成。。。。");
		return reslut;
	}

	public String publishToBridgeTextAll(String content, String bridgeToken, List<String> openid) {
		String result = "";
		String openids = "";
		try {
			String API_TOKEN = ConfKit.get("apiToken");
			String url = "http://mb.mtq.tvm.cn/rest/wxpush?token=" + API_TOKEN;
			String params = "weixin_token=" + bridgeToken;
			for (int i = 0; i < openid.size(); i++) {
				openids += openid.get(i) + ",";
			}
			openids = openids.substring(0, openids.length() - 1);
			params += "&openids=" + openids;
			params += "&weixin_type=text";
			params += "&content=" + content;
			result = HttpKit.post(url, params);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public String publishToBridgetuwenAll(String paramJson, String bridgeToken, List<String> openidList) {
		String result = "";
		String openids = "";
		try {
			String API_TOKEN = ConfKit.get("apiToken");
			String url = "http://mb.mtq.tvm.cn/rest/wxpush?token=" + API_TOKEN;
			String params = "weixin_token=" + bridgeToken;
			for (int i = 0; i < openidList.size(); i++) {
				openids += openidList.get(i) + ",";
			}
			openids = openids.substring(0, openids.length() - 1);
			params += "&openids=" + openids;
			params += "&weixin_type=text";
			params += "&content=" + paramJson;
			result = HttpKit.post(url, params);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;

	}

}
