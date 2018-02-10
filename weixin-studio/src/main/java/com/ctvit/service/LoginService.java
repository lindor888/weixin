package com.ctvit.service;

import org.apache.ibatis.session.SqlSessionFactory;

import com.ctvit.bean.Account;
import com.ctvit.dao.LoginMapper;
import com.ctvit.util.KeyGenerator;

public class LoginService implements LoginMapper{
	private LoginMapper loginMapper;
	private SqlSessionFactory sqlSessionFactory;
	private KeyGenerator keyGenerator;
	
	public Account login(Account account)throws Exception{
		return loginMapper.login(account);
	}

	public Account selectUserbyName(Account account)throws Exception{
		return loginMapper.selectUserbyName(account);
	}
	
	public Account ssoLogin(String accountId) {
		return loginMapper.ssoLogin(accountId);
	}
	
	public LoginMapper getLoginMapper() {
		return loginMapper;
	}

	public void setLoginMapper(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
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
	
}
