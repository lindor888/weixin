package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.AccountRelation;

public interface AccountRelationMapper {
    int deleteByPrimaryKey(String relationId) throws Exception;

    int insert(AccountRelation record) throws Exception;

    int insertSelective(AccountRelation record) throws Exception;

    AccountRelation selectByPrimaryKey(String relationId);

    int updateByPrimaryKeySelective(AccountRelation record) throws Exception;

    int updateByPrimaryKey(AccountRelation record) throws Exception;
    
      List<AccountRelation>  selectByExample(AccountRelation record);
      
     int  deleteByaccount(AccountRelation record) throws Exception;
     
     List<AccountRelation>  selectBywaccount(String  id);
     
     
}