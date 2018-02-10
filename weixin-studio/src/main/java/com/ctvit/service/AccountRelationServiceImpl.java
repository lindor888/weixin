package com.ctvit.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountRelation;
import com.ctvit.dao.AccountMapper;
import com.ctvit.dao.AccountRelationMapper;
import com.ctvit.dao.QueryDataBean;
import com.ctvit.util.KeyGenerator;

public class AccountRelationServiceImpl implements AccountRelationMapper {
	
	private AccountRelationMapper accountRelationmapper;
	private SqlSessionFactory sqlSessionFactory;
	private KeyGenerator keyGenerator;
	
	

	

	public List<AccountRelation> selectBywaccount(String id) {
		// TODO Auto-generated method stub
		return accountRelationmapper.selectBywaccount(id);
	}

	public int deleteByaccount(AccountRelation record) throws Exception {
		// TODO Auto-generated method stub
		return accountRelationmapper.deleteByaccount(record);
	}

	public List<AccountRelation> selectByExample(AccountRelation record) {
		// TODO Auto-generated method stub
		return accountRelationmapper.selectByExample(record);
	}

	public int deleteByPrimaryKey(String relationId) throws Exception{
		// TODO Auto-generated method stub
		return accountRelationmapper.deleteByPrimaryKey(relationId);
	}

	public int insert(AccountRelation record) throws Exception {
		// TODO Auto-generated method stub
	
		String id=keyGenerator.getKey(record);
		record.setRelationId(id);
		record.setOperateTime(new Timestamp((new Date()).getTime()));
		
		return accountRelationmapper.insert(record);
	}

	public int insertSelective(AccountRelation record) throws Exception {
		// TODO Auto-generated method stub
		return accountRelationmapper.insertSelective(record);
	}

	public AccountRelation selectByPrimaryKey(String relationId)  {
		// TODO Auto-generated method stub
		return accountRelationmapper.selectByPrimaryKey(relationId);
	}

	public int updateByPrimaryKeySelective(AccountRelation record) throws Exception {
		// TODO Auto-generated method stub
		return accountRelationmapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(AccountRelation record) throws Exception {
		// TODO Auto-generated method stub
		return accountRelationmapper.updateByPrimaryKey(record);
	}

	

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	public AccountRelationMapper getAccountRelationmapper() {
		return accountRelationmapper;
	}

	public void setAccountRelationmapper(AccountRelationMapper accountRelationmapper) {
		this.accountRelationmapper = accountRelationmapper;
	}

	
	
}
