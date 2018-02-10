package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.Waccount;
import com.ctvit.dao.QueryDataBean;

public interface WaccountMapper {
    int deleteByPrimaryKey(String waccountId);

    int insert (Waccount record) throws Exception;

    int insertSelective(Waccount record) throws Exception;

    Waccount selectByPrimaryKey(String waccountId);

    int updateByPrimaryKeySelective(Waccount record) throws Exception;

    int updateByPrimaryKey(Waccount record) throws Exception;
    
    List<Waccount> selectByExample(QueryDataBean query);
    
    List<Waccount> selectByaccount(QueryDataBean query);

    List<Waccount> selectWaccountList()throws Exception;
    
    List<Waccount> selectWaccountListbyAccountId(String accountId)throws Exception;
   
    Waccount   checknameBywaccount(Waccount record);
    
    Waccount selectBywelcomcontent(String waccountId);
    
    Waccount selectBynoReplyContent(String waccountId);
}