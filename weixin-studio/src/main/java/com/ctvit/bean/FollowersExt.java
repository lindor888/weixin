package com.ctvit.bean;

import java.io.Serializable;
import java.util.List;

public class FollowersExt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3830210688189356541L;

	private boolean pagination;

	// private String startDate;

	// private String endDate;

	private int page; // 当前第几页

	private int rows; // 每页显示的记录数

	private int rowStart;// 从第几条开始

	private int pageSize;// 每页显示条数

	private String openId;

	private String subscribe;

	private String waccountId;

	private String nickname;

	private String sex;

	private String city;

	private String country;

	private String province;

	private String headimgurl;

	private String subscribe_time;

	private int groupsId;

	private List<ReplayBean> replayBean;

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe == null ? null : subscribe.trim();
	}

	public String getWaccountId() {
		return waccountId;
	}

	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId;
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public int getGroupsId() {
		return groupsId;
	}

	public void setGroupsId(int groupsId) {
		this.groupsId = groupsId;
	}

	public List<ReplayBean> getReplayBean() {
		return replayBean;
	}

	public void setReplayBean(List<ReplayBean> replayBean) {
		this.replayBean = replayBean;
	}

	public int getRowStart() {
		return rowStart;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}