package com.ctvit.dao;

import com.ctvit.bean.Account;

public interface LoginMapper {
	Account login(Account account)throws Exception;
	Account selectUserbyName(Account account)throws Exception;
	Account ssoLogin(String accountId);
	
}
