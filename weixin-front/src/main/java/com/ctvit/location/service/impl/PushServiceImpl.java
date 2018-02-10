package com.ctvit.location.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.ctvit.bean.GraphicBean;
import com.ctvit.bean.JsonBean;
import com.ctvit.bean.News;
import com.ctvit.bean.Reservation;
import com.ctvit.bean.ReservationSub;
import com.ctvit.dao.ReservationDao;
import com.ctvit.location.service.PushService;
import com.ctvit.util.LoadedText;
import com.ctvit.util.MessageUtil;

public class PushServiceImpl implements PushService {
	private HttpServletRequest request;
	HttpSession session;
	private Reservation reservation;
	private String msg;
	String paramJson;
	String time;
	ReservationDao reservationDao;

	@Override
	public String pushService() {
		reservationDao = new ReservationDao();
		String id = reservationDao.selectAppid();
		LoadedText loadText = new LoadedText();
		String appid = loadText.getAppId(id);// "wxfab418ef0ef51787";//loadText.getAppId(id);
		String secret = loadText.getSecret(id);// "908440aaa973549c1989eda6d172e964";//loadText.getSecret(id);
		// System.out.println("appid+++++" + appid);
		JSONObject json = new JSONObject();
		JsonBean jsonBean = new JsonBean();
		News news = new News();
		MessageUtil messageUtil = new MessageUtil();
		GraphicBean graphicBean = new GraphicBean();
		List<JsonBean> listjson = new ArrayList<JsonBean>();
		List<ReservationSub> reservationSub = reservationDao.getReservationSubList();
		for (int i = 0; i < reservationSub.size(); i++) {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			time = format.format(date);
			reservation = reservationDao.getreservation(reservationSub.get(i));
			// System.out.println("time" + time);
			// System.out.println(reservation.getReservation_id());
			// System.out.println(format.format(reservation.getPush_time()));
			// System.out.println("kk+" + reservation.getPush_time().equals(time));
			String reservationTime = format.format(reservation.getPush_time());
			if (reservationTime.equals(time)) {
				String videoUrl = "http://www.4uplus.com/weixinopen/live/btv/index.html?openId=" + reservationSub.get(i).getOpen_id() + "&videoId=" + reservation.getReservation_id() + "&liveUrl=" + reservation.getLive_url();
				jsonBean.setDescription(reservation.getProgram_name());
				jsonBean.setTitle(reservation.getProgram_name());
				jsonBean.setUrl(videoUrl);
				jsonBean.setPicurl("http://pic40.nipic.com/20140412/18031351_135710031392_2.jpg");
				listjson.add(jsonBean);
				news.setArticles(listjson);
				graphicBean.setMsgtype("news");
				graphicBean.setNews(news);
				graphicBean.setTouser(reservationSub.get(i).getOpen_id());
				paramJson = json.toJSONString(graphicBean);
				reservationDao.setFlag(reservationSub.get(i).getSubscription_id());
				try {
					msg = messageUtil.sendTwenAll(appid, secret, paramJson);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		loadText = null;
		jsonBean = null;
		listjson = null;
		json = null;
		news = null;
		messageUtil = null;
		graphicBean = null;
		reservationDao = null;
		reservationSub = null;
		reservation = null;
		paramJson = null;
		time = null;
		reservationDao = null;
		return msg;
	}

}
