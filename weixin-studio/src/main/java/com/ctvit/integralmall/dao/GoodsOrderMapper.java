package com.ctvit.integralmall.dao;

import java.util.List;
import java.util.Map;

import com.ctvit.integralmall.entity.GoodsOrder;

public interface GoodsOrderMapper {
	List<GoodsOrder> getOrderList(Map map);

	int getCount(String appid);

	List<GoodsOrder> selectone(Map<String, Object> paraMap);
}
