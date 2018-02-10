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

import com.ctvit.bean.PrizeBean;
import com.ctvit.dao.LocationDao;
import com.ctvit.util.HttpKit;
import com.ctvit.util.KeyGenerator;

public class PrizeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3413205617538615582L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String openID = request.getParameter("openId");
			String prizeName = request.getParameter("prizeName");
			// /String waccount = request.getParameter("waccount");
			String waccount = HttpKit.get("http://localhost:8080/weixin-studio/interact/showUInteract?ids=" + openID);
			JSONObject json = JSONObject.fromObject(waccount);
			JSONArray jsonArray = json.getJSONArray("rows");
			JSONObject point = jsonArray.getJSONObject(0);
			String waccountID = point.getString("waccountId");
			LocationDao dao = new LocationDao();
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(date);
			PrizeBean prizeBean = new PrizeBean();
			KeyGenerator keyGenerator = new KeyGenerator();
			prizeBean.setPrizeID(keyGenerator.getKey(prizeBean));
			prizeBean.setPrizeTime(time);
			prizeBean.setOpenID(openID);
			prizeBean.setPrizeName(prizeName);
			prizeBean.setWaccountID(waccountID);
			dao.savePrize(prizeBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
