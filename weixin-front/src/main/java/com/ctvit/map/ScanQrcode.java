package com.ctvit.map;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.ctvit.dao.LocationDao;
import com.ctvit.framework.util.HttpUtils;
import com.ctvit.framework.util.Md5Encrypt;
import com.ctvit.util.ConfKit;
import com.ctvit.util.MessageUtil;
import com.ctvit.util.SendSeqUtils;
import com.ctvit.weixin.ScopeUtils;

/**
 * 扫二维码签到
 * 
 * @author Admini
 * 
 */
public class ScanQrcode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4171776828191724902L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String param = request.getParameter("state");
		String[] str = param.split("-");
		String openid = "";
		if (StringUtils.isNotEmpty(code)) {
			openid = new ScopeUtils().getOpenid(code, str[1]);

			String activeId = ConfKit.get("activeId");
			MessageUtil messageUtil = new MessageUtil();
			// LoadedText loadText = new LoadedText();
			String appid = ConfKit.get("appid");
			String secret = ConfKit.get("secret");
			LocationDao dao = new LocationDao();
			int num = dao.selectintegralAddtime(activeId, openid);
			if (num == 0) {
				try {
					JSONObject object = new JSONObject();
					String prikey = ConfKit.get("prikey");
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
							response.sendRedirect("epg/result_success.html?score=" + score);
						}
					} else {
						String result = messageUtil.sendText(appid, secret, openid, "签到失败");
						response.sendRedirect("epg/result_error.html");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				String result;
				try {
					result = messageUtil.sendText(appid, secret, openid, "今日已签到!");
					System.out.println(result);
					response.sendRedirect("epg/result_ready.html");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// response.sendRedirect("epg/epg.html?openid=" + openid);
	// response.sendRedirect("epg/epg.html?openid=o8VcbwC0ysO2uduJyo6yK489c9i0");
}
