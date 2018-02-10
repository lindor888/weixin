package com.ctvit.weixin;

import net.sf.json.JSONObject;

import com.ctvit.dao.WaccountDao;
import com.ctvit.util.HttpKit;

public class ScopeUtils {
	// private String scopebaseURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxfab418ef0ef51787&secret=908440aaa973549c1989eda6d172e964";
	private String scope_baseURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s";
	private WaccountDao waccountDao = new WaccountDao();

	public String getOpenid(String code, String appid) {
		// https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
		String appSecret = waccountDao.getAppSecret(appid);
		String scopebaseURL = String.format(scope_baseURL, appid, appSecret);
		StringBuffer url = new StringBuffer(scopebaseURL);
		url.append("&code=" + code);
		url.append("&grant_type=authorization_code");
		HttpKit httpKit = new HttpKit();
		try {
			String responseData = HttpKit.post(url.toString(), "");
			// System.out.println("weixin ---responseDate:" + responseData);
			JSONObject jObject = JSONObject.fromObject(responseData);
			if (jObject.containsKey("openid")) {
				return jObject.getString("openid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "error";
	}
}
