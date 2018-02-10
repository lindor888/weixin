package com.ctvit.epg;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ctvit.bean.Reservation;
import com.ctvit.dao.ReservationDao;
import com.ctvit.dao.ReservationSubDao;

/**
 * 预约成功的展示信息获取
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年9月2日 上午10:20:05
 */
public class ShowSubscribeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowSubscribeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat sdf2 = new SimpleDateFormat("HH:mm");

		String epgid = request.getParameter("epgid");
		ReservationDao resDao = new ReservationDao();
		Reservation reservation = resDao.getOne(epgid);
		JSONObject jObject = new JSONObject();
		String epgday = sdf.format(reservation.getStart_time());
		jObject.put("push_time", reservation.getPush_time().getTime() / 1000);
		jObject.put("epgName", reservation.getProgram_name());
		jObject.put("livetime", epgday + " " + sdf2.format(reservation.getStart_time()) + "-" + sdf2.format(reservation.getEnd_time()));
		jObject.put("epgday", epgday);

		ReservationSubDao subDao = new ReservationSubDao();
		int count = subDao.getCountByEpgid(epgid);
		jObject.put("count", count - 1);

		PrintWriter pw = response.getWriter();
		pw.write(jObject.toString());

	}
}
