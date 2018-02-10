package com.ctvit.dao;

import java.util.List;


import com.ctvit.bean.WxCustomizeMenus;
import com.ctvit.dao.QueryDataBean;

public interface WxCustomizeMenusMapper {
    int deleteByPrimaryKey(String id);

    int insert(WxCustomizeMenus record);

    int insertSelective(WxCustomizeMenus record);

    WxCustomizeMenus selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WxCustomizeMenus record);

    int updateByPrimaryKey(WxCustomizeMenus record);
    
    List<WxCustomizeMenus> selectByExample(QueryDataBean queryData);
    
    WxCustomizeMenus selectById(String id);

    int menuCount(String id);
    
    int menuparentCount(String id);
    
    int menuparentCountByName(WxCustomizeMenus record);
    
}