package com.ctvit.epg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ctvit.bean.ReservationSub;
import com.ctvit.bean.VirtualseatChose;
import com.ctvit.bean.VirtualseatInfo;
import com.ctvit.dao.ReservationSubDao;
import com.ctvit.dao.VirtualseatChoseDao;
import com.ctvit.dao.VirtualseatInfoDao;
import com.ctvit.util.CreateTableid;

/**
 * 预约和取消预约的 servlet
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年9月2日 上午10:22:02
 */

public class SubscribeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubscribeServlet() {
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
		JSONObject jObject = new JSONObject();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String openid = request.getParameter("openid");
		String epgid = request.getParameter("epgid");
		String subid = request.getParameter("subid");
		String waccountid = request.getParameter("waccountid");
		String subtype = request.getParameter("subtype");
		String nickname = request.getParameter("nickname");
		String headimgurl = request.getParameter("headimgurl");

		// 分配座位
		VirtualseatChose chose = new VirtualseatChose();
		chose.setChoseID(CreateTableid.createTableID(VirtualseatChose.class));
		chose.setHeadimgurl(headimgurl);
		chose.setNickname(nickname);
		chose.setOpenID(openid);
		chose.setVirtualSeatID(waccountid + epgid);

		ReservationSubDao subDao = new ReservationSubDao();
		JSONObject seat = new JSONObject();
		if (StringUtils.isEmpty(subid)) {
			// 首次预约
			subid = "subs" + new Date().getTime();
			ReservationSub sub = new ReservationSub();
			sub.setOpen_id(openid);
			sub.setReservation_id(epgid);
			sub.setSubscription_id(subid);
			sub.setFlag((short) 1);
			subDao.addSub(sub);
			seat = distribueVirtualseat(chose);

		} else if (subtype.equals("0")) {
			// 取消预约
			subDao.updataSub((short) 0, subid);

		} else {
			// 取消了预约，再次预约
			subDao.updataSub((short) 1, subid);
			seat = getVirtualseat(chose);
		}

		jObject.put("result", true);
		jObject.put("subid", subid);
		jObject.put("seat", seat);
		PrintWriter pw = response.getWriter();
		pw.write(jObject.toString());
	}

	private JSONObject distribueVirtualseat(VirtualseatChose chose) {
		JSONObject seat = new JSONObject();
		VirtualseatInfoDao infoDao = new VirtualseatInfoDao();
		VirtualseatInfo info = infoDao.findOne(chose.getVirtualSeatID());
		seat.put("resultSeat", info.getResultSeats() - 1);
		if (info.getResultSeats() >= 0) {
			int nowNum = info.getLineCount() * info.getColumnCount() - info.getResultSeats() + 1;
			int columnNum = nowNum % info.getLineCount() == 0 ? info.getColumnCount() : nowNum % info.getLineCount();
			int lineNum = nowNum / info.getLineCount() == 0 ? 1 : nowNum / info.getLineCount();
			chose.setLineNum(lineNum);
			chose.setColumnNum(columnNum);
			VirtualseatChoseDao choseDao = new VirtualseatChoseDao();
			choseDao.addChoseSeat(chose);
			seat.put("lineNum", lineNum);
			seat.put("columnNum", columnNum);
			// 减少座位
			infoDao.updateResultSeats(chose.getVirtualSeatID());
		}
		return seat;
	}

	private JSONObject getVirtualseat(VirtualseatChose chose) {
		JSONObject seat = new JSONObject();
		VirtualseatChoseDao choseDao = new VirtualseatChoseDao();
		VirtualseatChose record = choseDao.selectOne(chose.getOpenID(), chose.getVirtualSeatID());
		seat.put("lineNum", record.getLineNum());
		seat.put("columnNum", record.getColumnNum());
		return seat;
	}
}
