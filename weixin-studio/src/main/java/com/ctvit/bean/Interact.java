package com.ctvit.bean;

import java.util.Date;

public class Interact {
	// private String programme_account_name;

	private Integer id;

	private String orderId;

	private String programmeId;

	private String waccountId;

	private String openId;

	private String image;

	private Date insertTime;

	private Date updateTime;

	private String updateTimeStr;

	private Integer flag;

	private Integer flagx;

	private String content;

	private String huifu;

	private String video;

	private Integer xuanzhuan;

	private String audio;

	private Integer type;

	// 关联用户信息
	private String nickname;

	private String sex;

	private String city;

	private String country;

	private String province;

	private String headimgurl;

	// 分页使用
	private int page;

	private int rows;

	private int beginIndex;

	/*
	 * public String getProgramme_account_name() {
	 * 
	 * Account account = new Account(); programme_account_name = account.getUsername(); return programme_account_name; }
	 * 
	 * public void setProgramme_account_name(String programme_account_name) {
	 * 
	 * this.programme_account_name = programme_account_name; }
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getWaccountId() {
		return waccountId;
	}

	public String getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}

	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId == null ? null : waccountId.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getFlagx() {
		return flagx;
	}

	public void setFlagx(Integer flagx) {
		this.flagx = flagx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
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

	public int getBeginIndex() {
		if (page > 0 && rows > 0) {
			beginIndex = (page - 1) * rows;
		}
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getHuifu() {
		return huifu;
	}

	public void setHuifu(String huifu) {
		this.huifu = huifu;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public Integer getXuanzhuan() {
		return xuanzhuan;
	}

	public void setXuanzhuan(Integer xuanzhuan) {
		this.xuanzhuan = xuanzhuan;
	}
}