package com.ctvit.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountRelation;
import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Logger;
import com.ctvit.dao.QueryDataBean;
import com.ctvit.service.AccountRelationServiceImpl;
import com.ctvit.service.LoggerServiceImpl;
import com.ctvit.service.WaccountServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class AccountRelationAction extends ActionSupport {

	private String message;
	private String accountId;
	private String selectedIds;
	private QueryDataBean queryData;
	private AccountRelation record;
	private LoggerServiceImpl loggerService;
	private Logger log = new Logger();
	private WaccountServiceImpl waccountService;
	private AccountRelationServiceImpl accountRelationService;
	private Map<String, Object> mapJson = new Hashtable<String, Object>();
	private HttpSession session = ServletActionContext.getRequest().getSession();
	
	
	public String addrelation() {
		
		String [] selectid=null;
		try{
			
	
			Account account=(Account)session.getAttribute("user");
			 deleterelation();
			if(!selectedIds.equals("")){
				selectedIds=selectedIds.replace("[", "").replace("]", "");
				selectid=selectedIds.split(",");
			   
				
			
			 if(null!=selectid&&selectid.length>0){
				for (int i=0;i<selectid.length;i++){
					AccountRelation  relation=new AccountRelation();
					relation.setAccountId(accountId.trim());
					relation.setWaccountId(selectid[i].toString().trim());
					relation.setOperateAccount(account.getAccountId());//修改人
					accountRelationService.insert(relation);
			
					}
		
	
			}
			 
			}
		log.setType("分配公共账号");
		log.setAct("insert");
		loggerService.saveLog(log);
		message="OK";
		
		
		}catch(Exception e){
			message="ERROR";
			e.printStackTrace();
		}
		
		mapJson.put("message", message);
		return "add";
	}
	
	public String findByaccount(){
		List<AccountRelation> list=accountRelationService.selectByExample(record);
		message="OK";
		mapJson.put("message", message);
		mapJson.put("list", list);
		return "findByaccount";
	}
	
	
	
	public void deleterelation(){
		
		try{
			AccountRelation accountrelation=new AccountRelation();
			accountrelation.setAccountId(accountId.trim());
			accountRelationService.deleteByaccount(accountrelation);
		
	}catch(Exception e){
		e.printStackTrace();
	     }
		
	}
	
	
	
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public Map<String, Object> getMapJson() {
		return mapJson;
	}
	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}



	public QueryDataBean getQueryData() {
		return queryData;
	}



	public void setQueryData(QueryDataBean queryData) {
		this.queryData = queryData;
	}



	public AccountRelationServiceImpl getAccountRelationService() {
		return accountRelationService;
	}



	public void setAccountRelationService(
			AccountRelationServiceImpl accountRelationService) {
		this.accountRelationService = accountRelationService;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	public AccountRelation getRecord() {
		return record;
	}

	public void setRecord(AccountRelation record) {
		this.record = record;
	}

	public LoggerServiceImpl getLoggerService() {
		return loggerService;
	}

	public void setLoggerService(LoggerServiceImpl loggerService) {
		this.loggerService = loggerService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public WaccountServiceImpl getWaccountService() {
		return waccountService;
	}

	public void setWaccountService(WaccountServiceImpl waccountService) {
		this.waccountService = waccountService;
	}
	
	
	
	
}
