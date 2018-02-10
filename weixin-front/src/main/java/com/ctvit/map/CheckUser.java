package com.ctvit.map;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctvit.dao.LocationDao;

public class CheckUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6001615437322939069L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @return
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		String openid = request.getParameter("openId");

		LocationDao dao = new LocationDao();
		int num = dao.checkuser(openid);
		if (num == 0) {
			jsonObject.put("result", "success");
		} else {
			jsonObject.put("result", "error");
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonObject);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//String xml = "<?xml version='1.0' encoding='UTF-8' ?><book><name>Xml应用测试</name><auhtor>中视广信</auhtor><date>2015-10-12</date></book>";
		 out.write(jsonObject.toJSONString());
		//out.write(xml);
		out.flush();
		out.close();
	}
}
