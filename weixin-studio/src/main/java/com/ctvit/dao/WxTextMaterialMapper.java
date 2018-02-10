package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.WxTextMaterial;
import com.ctvit.dao.QueryDataBean;

public interface WxTextMaterialMapper {
    int deleteByPrimaryKey(String id);

    int insert(WxTextMaterial record);

    int insertSelective(WxTextMaterial record);

    WxTextMaterial selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WxTextMaterial record);

    List<WxTextMaterial> selectByExample(QueryDataBean queryData);
    
    int updateByPrimaryKey(WxTextMaterial record);
}