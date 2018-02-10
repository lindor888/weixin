package com.ctvit.map;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.ctvit.bean.ReportsBean;
import com.ctvit.dao.LocationDao;
import com.ctvit.util.HttpKit;
import com.ctvit.util.KeyGenerator;

public class Reports extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6686011762442481657L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = "123";// request.getParameter("code");
		String openid = "o8VcbwPAC9a24fGErCBCsm4UNd7I";
		if (StringUtils.isNotEmpty(code)) {
			try {
				// openid = new ScopeUtils().getOpenid(code);
				LocationDao dao = new LocationDao();
				int num = dao.selectReports(openid);
				if (num == 0) {
					String waccount = HttpKit.get("http://localhost:8080/weixin-studio/interact/showUInteract?ids=" + openid);
					JSONObject json = JSONObject.fromObject(waccount);
					JSONArray jsonArray = json.getJSONArray("rows");
					JSONObject point = jsonArray.getJSONObject(0);
					String waccountID = point.getString("waccountId");
					String city = point.getString("city");
					String headimgurl = point.getString("headimgurl");
					String nickname = point.getString("nickname");
					ReportsBean reportsBean = new ReportsBean();
					KeyGenerator keyGenerator = new KeyGenerator();
					String reportsID = keyGenerator.getKey(reportsBean);
					String qrcodeID = request.getParameter("state");
					Date date = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = dateFormat.format(date);
					reportsBean.setReportsID(reportsID);
					reportsBean.setOpenID(openid);
					reportsBean.setQrcodeID(qrcodeID);
					reportsBean.setReportsTime(time);
					reportsBean.setCity(city);
					reportsBean.setHeadImg(headimgurl);
					reportsBean.setNickName(nickname);
					reportsBean.setWaccount(waccountID);
					dao.saveReports(reportsBean);
					response.sendRedirect("epg/result_success.html");
				} else {
					response.sendRedirect("epg/result_ready.html");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("epg/result_error.html");
		}

	}

	// response.sendRedirect("epg/epg.html?openid=" + openid);
	// response.sendRedirect("epg/epg.html?openid=o8VcbwC0ysO2uduJyo6yK489c9i0");
}