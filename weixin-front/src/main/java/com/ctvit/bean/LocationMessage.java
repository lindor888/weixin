package com.ctvit.bean;

public class LocationMessage {
	
	private int Id;
	private String ToUserName;
	private String FromUserName; //发送方帐号（一个OpenID）
	private String CreateTime;
	private String MsgType;
	private String Event;
	private String Precision;
	private String Latitude;
	private String Longitude;
	private String OriginX;
	private String OriginY;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getOriginX() {
		return OriginX;
	}
	public void setOriginX(String originX) {
		OriginX = originX;
	}
	public String getOriginY() {
		return OriginY;
	}
	public void setOriginY(String originY) {
		OriginY = originY;
	}
	
	


}
