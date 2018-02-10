package com.ctvit.bean;

public class Followers {
	private String openid;

	private String waccountId;

	private String subscribe;

	private String nickname;

	private String sex;

	private String city;

	private String country;

	private String province;

	private String headimgurl;

	private String subscribeTime;

	private int groupsId;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public String getWaccountId() {
		return waccountId;
	}

	public void setWaccountId(String waccountId) {
		// this.waccountId = waccountId;
		this.waccountId = waccountId == null ? null : waccountId.trim();
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe == null ? null : subscribe.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country == null ? null : country.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl == null ? null : headimgurl.trim();
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime == null ? null : subscribeTime.trim();
	}

	public int getGroupsId() {
		return groupsId;
	}

	public void setGroupsId(int groupsId) {
		this.groupsId = groupsId;
	}

}