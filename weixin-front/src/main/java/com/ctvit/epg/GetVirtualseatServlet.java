package com.ctvit.epg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctvit.bean.VirtualseatInfo;
import com.ctvit.dao.VirtualseatInfoDao;

/**
 * Servlet implementation class GetVirtualseatServlet
 */
public class GetVirtualseatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetVirtualseatServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String virtualseatID = request.getParameter("virtualseatId");
		VirtualseatInfoDao infoDao = new VirtualseatInfoDao();
		VirtualseatInfo info = infoDao.findOne(virtualseatID);
		JSONArray choseSeat = new JSONArray();
		JSONArray map = new JSONArray();
		for (int j = 0; j < info.getLineCount(); j++) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < info.getColumnCount(); i++) {
				sb.append("a");
			}
			map.add(sb);
		}
		int num = 0;
		int choseNum = info.getColumnCount() * info.getLineCount() - info.getResultSeats();
		for (int j = 0; j < info.getLineCount() && num < choseNum; j++) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < info.getColumnCount() && num < choseNum; i++) {
				sb.append("a");
				choseSeat.add((j + 1) + "_" + (i + 1));
				num++;
			}
			map.add(sb);
		}

		JSONObject result = new JSONObject();
		result.put("param", map);
		result.put("choseSeat", choseSeat);
		PrintWriter pw = response.getWriter();
		pw.write(result.toString());
	}
}
