package com.ctvit.bean;

/**
 * 互动应用用户地理位置bean
 * 
 * @author Admini
 * 
 */
public class InteractLocation {

	private String interactid;
	private String waccount_id;
	private String openid;
	private String headurl;
	private String zhutiid;
	private String Longitude;
	private String Latitude;
	private String send_time;
	private String title;
	private String content;
	private String nickname;
	private int type;
	private int flag;

	public String getInteractid() {
		return interactid;
	}

	public void setInteractid(String interactid) {
		this.interactid = interactid;
	}

	public String getWaccount_id() {
		return waccount_id;
	}

	public void setWaccount_id(String waccount_id) {
		this.waccount_id = waccount_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getZhutiid() {
		return zhutiid;
	}

	public void setZhutiid(String zhutiid) {
		this.zhutiid = zhutiid;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

}
