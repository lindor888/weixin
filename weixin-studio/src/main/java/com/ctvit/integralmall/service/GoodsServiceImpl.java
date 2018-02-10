package com.ctvit.integralmall.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ctvit.integralmall.dao.GoodsMapper;
import com.ctvit.integralmall.entity.Goods;
import com.ctvit.integralmall.entity.GoodsType;

public class GoodsServiceImpl {

	private GoodsMapper goodsMapper;

	public GoodsMapper getGoodsMapper() {
		return goodsMapper;
	}

	public void setGoodsMapper(GoodsMapper goodsMapper) {
		this.goodsMapper = goodsMapper;
	}

	public List<Goods> getList(Map map) {
		return goodsMapper.getAllList(map);
	}

	public Goods getGoodsInfo(String goodsID) {
		return goodsMapper.selectOne(goodsID);
	}

	public int insertGoods(Goods record) {
		record.setGoodsID("goods" + System.currentTimeMillis());
		record.setCreateTime(new Date());
		// record.setGoodsImageName("resources/mall/ABUIABACGAAgnvLsmgUotMDwrgIwgBA4gA");
		// record.setWaccountID("wxfab418ef0ef51787");
		return goodsMapper.insertSelective(record);
	}

	public int updateGoods(Goods record) {
		return goodsMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteGoods(String goodsID) {
		Goods record = new Goods();
		record.setGoodsID(goodsID);
		record.setStatus(1);
		return goodsMapper.updateByPrimaryKeySelective(record);
	}

	public int getCount(String appid) {
		return goodsMapper.getCount(appid);
	}

	public List<GoodsType> getGoodsTypes() {
		return goodsMapper.getGoodsTypeList();
	}
}
