package com.ctvit.bean;

import java.io.Serializable;

public class AccountSessionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Account account;
	private String waccountId;
	
	public String getWaccountId() {
		return waccountId;
	}
	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String ip;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	

}
