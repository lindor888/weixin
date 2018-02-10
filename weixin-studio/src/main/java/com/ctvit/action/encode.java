package com.ctvit.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.ctvit.util.HttpKit;

public class encode {

	/**
	 * @param args
	 *            oX7GJjhqwwKpukqoyYB8uiDHFpmU
	 */
	public static void main(String[] args) {
		String urlencode = "\"articles\":[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"http://www.baidu.com\",\"picurl\":\"http://www.baidu.com/img/bdlogo.gif\"},{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"http://www.baidu.com\",\"picurl\":\"http://www.baidu.com/img/bdlogo.gif\"}]";
		Object text = com.alibaba.fastjson.JSONObject.toJSON(urlencode);
		System.out.println(text);
		try {
			String url = "http://mb.mtq.tvm.cn/rest/wxcron?token=636e6742d6&weixin_token=tvmcj";
			String mytext = java.net.URLEncoder.encode(text + "", "utf-8");
			System.out.println(mytext);
			try {
				HttpKit.post(url, "content" + ":" + mytext);
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
