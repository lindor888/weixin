package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.Account;
import com.ctvit.dao.QueryDataBean;

public interface AccountMapper {
    int deleteByPrimaryKey(String accountId) throws Exception;

    int insert (Account record) throws Exception;

    int insertSelective(Account record) throws Exception;

    Account selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(Account record) throws Exception;

    int updateByPrimaryKey(Account record) throws Exception;
    
    List<Account> selectByExample(QueryDataBean queryData);
    
    Account selectCheckUserNameByPrimaryKey(Account record);

	Account checkdepartment(String accountId);

	int countByExample(QueryDataBean query);
    
}