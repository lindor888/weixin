package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.JsonBean;

public interface GraphicPushMapper {
	
	List<String> selectOpenid();
    
	void insertJson(List<JsonBean> listjson);
}
