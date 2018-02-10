package com.ctvit.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ctvit.dao.ReservationDao;
import com.ctvit.util.GetRequestMessage;
import com.ctvit.util.LoadedText;
import com.ctvit.util.MemcacheUtils;
import com.danga.MemCached.MemCachedClient;

public class WeixinHandler {
	private static final Logger logger = Logger.getLogger(WeixinHandler.class);
	private HttpServletRequest request;
	private HttpServletResponse response;

	// private final String TOKEN = "OMG";

	public WeixinHandler(final HttpServletRequest request, final HttpServletResponse response) {
		this.request = request;
		this.response = response;

	}

	/**
	 * 微信入口验证
	 */
	public void valid() {
		String echostr = request.getParameter("echostr");
		String accessID = request.getParameter("id");
		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		memCachedClientInstance.set("initialize_id", accessID);
		logger.info("id放入缓存中+++initialize_id");
		if (StringUtils.isNotEmpty(accessID)) {
			LoadedText loadText = new LoadedText();
			String appid = loadText.getAppId(accessID);
			String secret = loadText.getSecret(accessID);
			ReservationDao reservationDao = new ReservationDao();
			// int count = reservationDao.selectAppid(accessID);
			// if(count == 0){
			reservationDao.insertAppid(accessID, appid, secret);
			// }
		}
		try {

			String ip = GetRequestMessage.getIpAddr(request);
			logger.info("[getRequestIp:]" + ip);
		} catch (Exception e) {
			logger.error("[getRequestIpError:]" + e.getLocalizedMessage());
		}
		if (null == echostr || echostr.isEmpty()) {
			if (this.checkSignature((String) request.getParameter("id"))) {
				responseMsg();
			}
		} else {
			if (this.checkSignature((String) request.getParameter("id"))) {
				this.print(echostr);
			} else {
				this.print("error");
			}
		}
	}

	// 自动回复内容
	public void responseMsg() {
		String postStr = null;
		try {
			postStr = this.readStreamParameter(request.getInputStream());
		} catch (Exception e) {
			logger.error("[responseMsgError:]" + e.getLocalizedMessage());
		}
		if (null != postStr && !postStr.isEmpty()) {
			ReceiveMessage receiveMessage = new ReceiveMessage();

			String outMessage = receiveMessage.sendMessage(postStr, (String) request.getParameter("id"));
			this.print(outMessage);

		} else {
			this.print("");
		}
	}

	// 微信接口验证
	public boolean checkSignature(String id) {
		try {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
			String token = (String) memCachedClientInstance.get(id + "_token");
			System.out.println("token++" + token);
			String[] tmpArr = { token, timestamp, nonce };
			Arrays.sort(tmpArr);
			String tmpStr = this.ArrayToString(tmpArr);
			tmpStr = this.SHA1Encode(tmpStr);
			System.out.println("tmpStr++" + tmpStr);
			if (tmpStr.equalsIgnoreCase(signature)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error("[checkSignatureError:]" + e.getLocalizedMessage());
			return false;
		}
	}

	// 向请求端发送返回数据
	public void print(String content) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(content);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			logger.error("[printError:]" + e.getLocalizedMessage());
		}
	}

	// 数组转字符串
	public String ArrayToString(String[] arr) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			bf.append(arr[i]);
		}
		return bf.toString();
	}

	// sha1加密
	public String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			logger.error("[SHA1EncodeError:]" + ex.getLocalizedMessage());
		}
		return resultString;
	}

	public final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}

	// 从输入流读取post参数
	public String readStreamParameter(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			logger.error("[readStreamParameterError:]" + e.getLocalizedMessage());
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("[readStreamParameterFinallyError:]" + e.getLocalizedMessage());
				}
			}
		}
		return buffer.toString();
	}

	public String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}
}
