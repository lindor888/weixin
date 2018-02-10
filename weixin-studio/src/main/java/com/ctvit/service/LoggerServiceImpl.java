package com.ctvit.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Logger;
import com.ctvit.dao.LoggerMapper;

public class LoggerServiceImpl {

	private LoggerMapper loggerMapper; 

	public LoggerMapper getLoggerMapper() {
		return loggerMapper;
	}

	public void setLoggerMapper(LoggerMapper loggerMapper) {
		this.loggerMapper = loggerMapper;
	}

	public void saveLog(Logger log) {
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		AccountSessionBean accountSessionBean =  (AccountSessionBean)applicationContext.getBean("accountSessionBean");
		Account account = accountSessionBean.getAccount();
//		System.out.println(account);
//		System.out.println("1");
		log.setUsername(account.getUsername());
		log.setIp(accountSessionBean.getIp());
		log.setTime(new Date());
		loggerMapper.saveLog(log);
	}

	public List<LoggerMapper> selectLog(Logger log) {
		// TODO Auto-generated method stub
		return loggerMapper.selectLog(log);
	}

	public LoggerMapper selectLogById(int logId) {
		// TODO Auto-generated method stub
		return loggerMapper.selectLogById(logId);
	}

	public void deleteById(int logId) {
		// TODO Auto-generated method stub
		loggerMapper.deleteById(logId);
	}

}
