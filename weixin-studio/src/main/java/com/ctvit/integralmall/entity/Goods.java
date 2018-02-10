package com.ctvit.integralmall.entity;

import java.util.Date;

/**
 * 积分商城商品信息
 * 
 * @author yaonana
 * @email yaonana@ctvit.com.cn
 * @date 2015年10月8日 下午6:43:07
 */
public class Goods {
	private String goodsID;
	private String goodsName;
	private String goodsTypeID;
	private String goodsTypeName;
	private String goodsDescript;
	private Integer goodsUnitPoint;
	private String goodsImageName;
	private Integer inventory;
	private Date createTime;
	private String waccountID;
	private Integer status;

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

	public String getGoodsTypeID() {
		return goodsTypeID;
	}

	public void setGoodsTypeID(String goodsTypeID) {
		this.goodsTypeID = goodsTypeID;
	}

	public String getGoodsDescript() {
		return goodsDescript;
	}

	public void setGoodsDescript(String goodsDescript) {
		this.goodsDescript = goodsDescript;
	}

	public Integer getGoodsUnitPoint() {
		return goodsUnitPoint;
	}

	public void setGoodsUnitPoint(Integer goodsUnitPoint) {
		this.goodsUnitPoint = goodsUnitPoint;
	}

	public String getGoodsImageName() {
		return goodsImageName;
	}

	public void setGoodsImageName(String goodsImageName) {
		this.goodsImageName = goodsImageName;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getWaccountID() {
		return waccountID;
	}

	public void setWaccountID(String waccountID) {
		this.waccountID = waccountID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

}
