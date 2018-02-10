package com.ctvit.action;

import com.ctvit.bean.Logger;
import com.ctvit.service.LoggerServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class LoggerAction extends ActionSupport{
	
	private LoggerServiceImpl loggerService;
	private LoginAction loginAction;
	private Logger log=new Logger();
	
	public LoginAction getLoginAction() {
		return loginAction;
	}

	public void setLoginAction(LoginAction loginAction) {
		this.loginAction = loginAction;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}


	public LoggerServiceImpl getLoggerService() {
		return loggerService;
	}

	public void setLoggerService(LoggerServiceImpl loggerService) {
		this.loggerService = loggerService;
	}
	
	public void saveLog(){
		System.out.println("1");
		loggerService.saveLog(log);
	}

}
