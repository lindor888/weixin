package com.ctvit.location.service.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ctvit.bean.Articles;
import com.ctvit.bean.InMessage;
import com.ctvit.bean.LocationMessage;
import com.ctvit.bean.NewsOutMessage;
import com.ctvit.bean.OutMessage;
import com.ctvit.bean.TextOutMessage;
import com.ctvit.dao.LocationDao;
import com.ctvit.location.service.LocationService;
import com.ctvit.location.util.BaiduMapUtils;
import com.ctvit.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;

public class LocationServiceImpl implements LocationService {

	private static final Logger LOGGER = Logger.getLogger(BaiduMapUtils.class);
	
	public static final String AK = "lX2hUCMPoKTHLrQP3gzBg4Z9";
	public static final String TITLE_FORMAT = "%s 电话：%s 距离：%s米";
	public static final String PIC_URL_FORMAT = "http://api.map.baidu.com/staticimage?center=%s&width=%s&height=%s&zoom=%s&markers=%s&markerStyles=l,A";
	public static final String URL_MARKER_FORMAT = "http://api.map.baidu.com/marker?location=%s&title=%s&content=%s&output=html";
	public static final String URL_DIRECTION_FORMAT = "http://api.map.baidu.com/direction?origin=latlng:%s|name:%s&destination=latlng:%s|name:%s&mode=driving&region=%s&output=html&src=CTVIT";
	
	public String locationNews(InMessage msg, String id) {
		try {
			String xy = "";
			if ("location".equals(msg.getMsgType())) {
				//用户主动发送地理位置
				xy = BaiduMapUtils.geoconv(msg.getLocationY() + "," + msg.getLocationX(), AK);
			} else {
				//xy = BaiduMapUtils.map_tx2bd(Integer.parseInt(msg.getLatitude()),Integer.parseInt( msg.getLongitude()));
				xy = BaiduMapUtils.geoconv(msg.getLongitude() + "," + msg.getLatitude(), AK);
			}
			LOGGER.info("locationNews.xy=" + xy);
			JSONObject xyJObj = JSONObject.fromObject(xy);
			JSONArray result = xyJObj.getJSONArray("result");
			JSONObject xyJR = result.getJSONObject(0);
			String originXY = getLatlng(xyJR.getString("x"), xyJR.getString("y"));
			String originYX = getLatlng(xyJR.getString("y"), xyJR.getString("x"));
			String originX = originXY.split(",")[0];
			String originY = originXY.split(",")[1];
			String local = BaiduMapUtils.local(originXY, "酒店", AK);
			LocationDao locationDao = new LocationDao();
			LocationMessage locationMessage = new LocationMessage();
			int num = locationDao.select(msg.getFromUserName());
			locationMessage.setToUserName(msg.getToUserName());
			locationMessage.setFromUserName(msg.getFromUserName());
			locationMessage.setMsgType(msg.getMsgType());
			locationMessage.setEvent(msg.getEvent());
			locationMessage.setCreateTime(msg.getCreateTime() + "");
			locationMessage.setLatitude(msg.getLatitude());
			locationMessage.setLongitude(msg.getLongitude());
			locationMessage.setPrecision(msg.getPrecision());
			locationMessage.setOriginX(originX);
			locationMessage.setOriginY(originY);
			if(num < 1){
			
			locationDao.insert(locationMessage);
			}else{
				
				locationDao.update(locationMessage);
			}
			
			LOGGER.info("locationNews.local=" + local);
			JSONObject localJObj = JSONObject.fromObject(local);
			if (localJObj.getInt("count") == 0) {
				if ("location".equals(msg.getMsgType())) {
					return defaultOutMessagae(msg);
				} else {
					return null;
				}
			}
			List<Articles> list = new ArrayList<Articles>();
			OutMessage oms = new NewsOutMessage();
			oms.setToUserName(msg.getFromUserName());
			oms.setFromUserName(msg.getToUserName());
			oms.setCreateTime(new Date().getTime());
			JSONArray pointJArray = localJObj.getJSONArray("pointList");
			for (int i=0; i<10; i++) {
				JSONObject point = pointJArray.getJSONObject(i);
				JSONObject location = point.getJSONObject("location");
				String destinationLngLat = getLatlng(location.getString("lng"), location.getString("lat"));
				String destinationLatLng = getLatlng(location.getString("lat"), location.getString("lng"));
				String telephone = "";
				JSONObject additionalInformation = point.getJSONObject("additionalInformation");
				if (additionalInformation != null) {
					telephone = additionalInformation.getString("telephone");
				}
				String title = String.format(TITLE_FORMAT, point.getString("name"), telephone, point.getString("distance"));
				String picUrl = "#";
				if (i == 0) {
					picUrl = String.format(PIC_URL_FORMAT, destinationLngLat, 320, 160, 18, destinationLngLat);
				} else {
					picUrl = String.format(PIC_URL_FORMAT, destinationLngLat, 80, 80, 15, destinationLngLat);
				}
//				String url = String.format(URL_MARKER_FORMAT, destinationLatLng, URLEncoder.encode(point.getString("name"), "UTF-8"), URLEncoder.encode(title, "UTF-8"));
				String url = String.format(URL_DIRECTION_FORMAT, originYX, "%E6%88%91%E7%9A%84%E4%BD%8D%E7%BD%AE", destinationLatLng, URLEncoder.encode(point.getString("name"), "UTF-8"), "%E5%8C%97%E4%BA%AC");
				Articles articles = new Articles();
				articles.setTitle(title);
				articles.setPicUrl(picUrl);
				articles.setUrl(url);
				list.add(articles);
			}
			((NewsOutMessage) oms).setArticles(list);
			XStream xs = XStreamFactory.init(true);
			xs.alias("xml", oms.getClass());
			xs.alias("item", Articles.class);
			return xs.toXML(oms);
		} catch (Exception e) {
			LOGGER.error("LocationServiceImpl.locationNews.Exception", e);
		}
		
		if ("location".equals(msg.getMsgType())) {
			return defaultOutMessagae(msg);
		} else {
			return null;
		}
	}

	private String getLatlng(String x, String y) {
		return x + "," + y;
	}
	
	private String defaultOutMessagae(InMessage msg) {
		TextOutMessage oms = new TextOutMessage();
		oms.setToUserName(msg.getFromUserName());
		oms.setFromUserName(msg.getToUserName());
		oms.setCreateTime(new Date().getTime());
		oms.setContent("您好，您的周边无可推荐信息！");
		XStream xs = XStreamFactory.init(true);
		xs.alias("xml", oms.getClass());
		return xs.toXML(oms);
	}

}
