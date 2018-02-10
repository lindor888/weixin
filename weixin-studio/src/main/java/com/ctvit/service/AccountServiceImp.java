package com.ctvit.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ctvit.bean.Account;
import com.ctvit.dao.AccountMapper;
import com.ctvit.dao.QueryDataBean;
import com.ctvit.util.KeyGenerator;

public class AccountServiceImp implements AccountMapper {
	
	private AccountMapper accountMapper;
	private SqlSessionFactory sqlSessionFactory;
	//private SqlSessionFactory sqlSessionFactory;
    //private CacheManager cacheManager;
	private KeyGenerator keyGenerator;
	//public UserServiceImp(){}

	public int deleteByPrimaryKey(String accountId) throws Exception {
		// TODO Auto-generated method stub
		return accountMapper.deleteByPrimaryKey(accountId);
	}

	public int insert(Account record) throws Exception{
		// TODO Auto-generated method stub
		if(record.getAccountId()==null||"".equals(record.getAccountId())){
			String	accountId = keyGenerator.getKey(record);
			record.setAccountId(accountId);
		}
		Byte i=0;
		 
		 record.setState(i);
		 record.setCreateTime(new Timestamp((new Date()).getTime()));
			
		return accountMapper.insert(record);
	}

	public int insertSelective(Account record) throws Exception {
		// TODO Auto-generated method stub
		return accountMapper.insertSelective(record);
	}

	public Account selectByPrimaryKey(String accountId) {
		// TODO Auto-generated method stub
		return accountMapper.selectByPrimaryKey(accountId);
	}

	public int updateByPrimaryKeySelective(Account record) throws Exception {
		// TODO Auto-generated method stub
		return accountMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Account record) throws Exception {
		// TODO Auto-generated method stub
		return accountMapper.updateByPrimaryKey(record);
	}
	
	public int countByExample(QueryDataBean query) {
		return accountMapper.countByExample(query);
	}
	
	public List<Account> selectByExample(QueryDataBean query) {
		
		int page = query.getPage();
		int rows = query.getRows();
		int start = (page-1)*rows;
		query.setPage(start);
		List result = null;
		SqlSession session = sqlSessionFactory.openSession();
		query = setQueryConditions(query);
		try {
			if(query.isPagination()){
				result = session.selectList("com.ctvit.dao.AccountMapper.selectByExamplepage",query);
			}else{
				result = session.selectList("com.ctvit.dao.AccountMapper.selectByExample",query);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}
	

	public QueryDataBean setQueryConditions(QueryDataBean query){
		if(query.getLogin()!=null && query.getLogin().equals(""))query.setLogin(null);
	
		if(query.getUsername()!=null && query.getUsername().equals(""))query.setUsername(null);
		if(query.getDepartment()!=null && query.getDepartment().equals(""))query.setDepartment(null);
		if(query.getGroupId()!=null && query.getGroupId().equals(""))query.setGroupId(null);
		return query;
	}

	public Account checkdepartment(String accountId) {
		// TODO Auto-generated method stub
		return accountMapper.checkdepartment(accountId);
	}


	public Account selectCheckUserNameByPrimaryKey(Account record) {
		// TODO Auto-generated method stub
		return accountMapper.selectCheckUserNameByPrimaryKey(record);
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}


	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public AccountMapper getAccountMapper() {
		return accountMapper;
	}

	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}
	
}
