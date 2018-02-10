package com.ctvit.integralmall.entity;

import java.util.Date;

/**
 * 商品订单信息
 * 
 * @author yaonana
 * @email yaonana@ctvit.com.cn
 * @date 2015年10月30日 上午10:56:31
 */
public class GoodsOrder {
	private String exchangeRecordID;
	private String userName;
	private String headUrl;
	private String nickName;
	private String waccount;
	private String sex;
	private String birthday;
	private String telephone;
	private String mobilePhone;
	private String reciveAddress;
	private String address;
	private String reciveInfo;
	private String goodsID;
	private String goodsName;
	private Integer count;
	private Integer point;
	private Date createTime;

	public String getExchangeRecordID() {
		return exchangeRecordID;
	}

	public void setExchangeRecordID(String exchangeRecordID) {
		this.exchangeRecordID = exchangeRecordID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWaccount() {
		return waccount;
	}

	public void setWaccount(String waccount) {
		this.waccount = waccount;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getReciveAddress() {
		return reciveAddress;
	}

	public void setReciveAddress(String reciveAddress) {
		this.reciveAddress = reciveAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getReciveInfo() {
		return reciveInfo;
	}

	public void setReciveInfo(String reciveInfo) {
		this.reciveInfo = reciveInfo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
