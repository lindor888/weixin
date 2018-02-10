package com.ctvit.integralmall.service;

import java.util.List;
import java.util.Map;

import com.ctvit.bean.Followers;
import com.ctvit.dao.FollowersMapper;
import com.ctvit.integralmall.dao.GoodsOrderMapper;
import com.ctvit.integralmall.entity.GoodsOrder;

public class GoodsOrderServiceImpl {
	private GoodsOrderMapper goodsOrderMapper;
	private FollowersMapper followersMapper;

	public GoodsOrderMapper getGoodsOrderMapper() {
		return goodsOrderMapper;
	}

	public void setGoodsOrderMapper(GoodsOrderMapper goodsOrderMapper) {
		this.goodsOrderMapper = goodsOrderMapper;
	}

	public FollowersMapper getFollowersMapper() {
		return followersMapper;
	}

	public void setFollowersMapper(FollowersMapper followersMapper) {
		this.followersMapper = followersMapper;
	}

	public List<GoodsOrder> getList(Map map) {
		List<GoodsOrder> orders = goodsOrderMapper.getOrderList(map);
		for (int i = 0; i < orders.size(); i++) {
			GoodsOrder order = orders.get(i);
			Followers followers = followersMapper.selectByPrimaryKey(order.getUserName());
			orders.get(i).setHeadUrl(followers.getHeadimgurl());
			orders.get(i).setNickName(followers.getNickname());
			String reciveInfo = order.getReciveAddress() + "_" + order.getMobilePhone() + ";" + order.getTelephone();
			orders.get(i).setReciveInfo(reciveInfo);
		}
		return orders;
	}

	public int getCount(String appid) {
		return goodsOrderMapper.getCount(appid);
	}

	public List<GoodsOrder> selectone(Map<String, Object> paraMap) {
		List<GoodsOrder> orders = goodsOrderMapper.selectone(paraMap);
		for (int i = 0; i < orders.size(); i++) {
			GoodsOrder order = orders.get(i);
			Followers followers = followersMapper.selectByPrimaryKey(order.getUserName());
			orders.get(i).setHeadUrl(followers.getHeadimgurl());
			orders.get(i).setNickName(followers.getNickname());
			String reciveInfo = order.getReciveAddress() + "_" + order.getMobilePhone() + ";" + order.getTelephone();
			orders.get(i).setReciveInfo(reciveInfo);
		}
		return orders;
	}
}
