package com.ctvit.map;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.ctvit.dao.LocationDao;
import com.ctvit.weixin.ScopeUtils;

public class Activity extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4762870694778235715L;

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
			LocationDao dao = new LocationDao();
			int num = dao.selectReports(openid);
			if (num == 0) {
				response.sendRedirect("epg/activity_error.html");
			} else {
				response.sendRedirect("epg/activity/index.html?openId=" + openid);
			}
		} else {
			response.sendRedirect("epg/result_error.html");
		}

		// response.sendRedirect("epg/epg.html?openid=" + openid);
		// response.sendRedirect("epg/epg.html?openid=o8VcbwC0ysO2uduJyo6yK489c9i0");
	}
}
