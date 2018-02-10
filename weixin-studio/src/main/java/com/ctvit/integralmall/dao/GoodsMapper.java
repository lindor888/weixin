package com.ctvit.integralmall.dao;

import java.util.List;
import java.util.Map;

import com.ctvit.integralmall.entity.Goods;
import com.ctvit.integralmall.entity.GoodsType;

public interface GoodsMapper {
	/**
	 * 积分商城所有商品
	 * 
	 * @author yaonana
	 * @date 2015年10月9日 上午9:46:34
	 * @param fenyeBean
	 * @return
	 * @return List<Goods>
	 */
	List<Goods> getAllList(Map map);

	Goods selectOne(String goodsID);

	int insertSelective(Goods record);

	int updateByPrimaryKeySelective(Goods record);

	int getCount(String appid);

	List<GoodsType> getGoodsTypeList();
}
