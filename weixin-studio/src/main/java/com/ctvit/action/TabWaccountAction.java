package com.ctvit.action;
 
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountRelation;
import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Logger;
import com.ctvit.bean.Waccount;
import com.ctvit.dao.QueryDataBean;
import com.ctvit.service.AccountRelationServiceImpl;
import com.ctvit.service.LoggerServiceImpl;
import com.ctvit.service.WaccountServiceImpl;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.util.encrypttools.SysConfig;
import com.danga.MemCached.MemCachedClient;
import com.opensymphony.xwork2.ActionSupport;

public class TabWaccountAction extends ActionSupport {
	
	
	private boolean  message;
	//private String  newPassword;
    private	QueryDataBean  queryData ;
    private   WaccountServiceImpl	waccountService;
    private Map<String, Object> mapJson = new Hashtable<String, Object>();
    private int page;
    private int rows;
   
    private Byte urgentstate;
    private Waccount waccount;
   
    private String wid;
    private String errorMes;
    private LoggerServiceImpl loggerService;
    private Logger log = new Logger();
    private HttpSession session=ServletActionContext.getRequest().getSession();
    private AccountRelationServiceImpl accountRelationService;
    MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
    
    
    
    public String listfindurl(){
    
    	return "listfindurl";
    }
    
    public String insertfindurl(){
    	
    	
    	return "insertfindurl";
    }
    public String selectAll(){
    	
    	queryData=new QueryDataBean();
    	
        queryData.setPagination(false);
    	queryData.setGroupName( ((Account)session.getAttribute("user")).getAccountId());
    	List<Waccount> l1=waccountService.selectByaccount(queryData);
    	queryData.setPagination(true);
    	queryData.setPage(page);
		queryData.setRows(rows);
    	List<Waccount> l2=waccountService.selectByaccount(queryData);
    	
    	mapJson.put("total", l1.size());
    	mapJson.put("rows", l2);
    	return "selectAll";
    }
    
    
    public String selectAllfebpei(){
    	
    	queryData=new QueryDataBean();
    	
        queryData.setPagination(false);
    	//queryData.setGroupName( ((Account)session.getAttribute("user")).getAccountId());
    	List<Waccount> l1=waccountService.selectByExample(queryData);
    	List<AccountRelation> list=accountRelationService.selectBywaccount(wid);
    	//queryData.setPagination(true);
    	//queryData.setPage(page);
		//queryData.setRows(rows);
    	//iList<Waccount> l2=waccountService.selectByExample(queryData);
    	
    	//mapJson.put("total", l1.size());
    	
    	mapJson.put("rows", l1);
    	mapJson.put("reallist", list);
    	return "selectAll";
    }
    
    public String selectAllBysearch(){
    	
    	
    	queryData.setPagination(false);
    	queryData.setGroupName( ((Account)session.getAttribute("user")).getAccountId());
    	List<Waccount> l1=waccountService.selectByaccount(queryData);
    	queryData.setPagination(true);
    	queryData.setPage(page);
		queryData.setRows(rows);
    	List<Waccount> l2=waccountService.selectByaccount(queryData);
    	
    	mapJson.put("total", l1.size());
    	mapJson.put("rows", l2);
    	return "selectAll";
    }
    
   public String findurl(){
		   waccount =waccountService.selectByPrimaryKey(wid);
		   Waccount   noreply= waccountService.selectBynoReplyContent(wid);//无返回结果提示内容
		   Waccount   comreply= waccountService.selectBywelcomcontent(wid); //关注欢迎语内容
		   if(null!=noreply&&null!=noreply.getNocatalogTitle()&&!"".equals(noreply.getNocatalogTitle())){
		   waccount.setNocatalogTitle(noreply.getNocatalogTitle());
		 
		   }
		   if(null!=comreply&&null!=comreply.getComcatalogTitle()&&!"".equals(comreply.getComcatalogTitle()))
		   waccount.setComcatalogTitle(comreply.getComcatalogTitle());
		     return "updatewaccount";
	  
   }
   
   public String deletewaccount(){
	 
	   String resmag="";
	 //   Byte state=1;
	  // waccount.setState(state);
	   try{
		   
	   waccountService.updateByPrimaryKeySelective(waccount);
	   if(waccount.getState()==1){
	   AccountRelation accountrelation=new AccountRelation();
	   accountrelation.setWaccountId(waccount.getWaccountId());
	   accountRelationService.deleteByaccount(accountrelation);
	   
	   memCachedClientInstance.delete(waccount.getWaccountId()+"_huanyingyu"); 
	   memCachedClientInstance.delete(waccount.getWaccountId()+"_wufanhuijieguotishiyu"); 
	   memCachedClientInstance.delete(waccount.getWaccountId()+"_token"); 
	   memCachedClientInstance.delete(waccount.getWaccountId()+"_qiangzhifanhui");
	   memCachedClientInstance.delete(waccount.getWaccountId()+"_appid");
	   memCachedClientInstance.delete(waccount.getWaccountId()+"_secret");
	   memCachedClientInstance.delete(waccount.getWaccountId()+"_bridge");
	   }
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   
		    log.setType("微信公众账号");
			log.setAct("delete");
			log.setValue(waccount.getWaccountId());
			loggerService.saveLog(log);
	   resmag="true";
	   mapJson.put("message", resmag);
	   }
	   return "deletewaccount" ;
   }
    
   
   public String UrgentStatewaccount(){
	  
	   String resmag="";
	   waccount.setUrgentState(urgentstate);
	   try{
		   //缓存？
		   if(urgentstate==1)
		   memCachedClientInstance.set(waccount.getWaccountId()+"_qiangzhifanhui","true");
		   else if(urgentstate==0)
		   memCachedClientInstance.delete(waccount.getWaccountId()+"_qiangzhifanhui");
		   
	   waccountService.updateByPrimaryKeySelective(waccount);
	   resmag="true";
	   }catch(Exception e){
		   e.printStackTrace();
		   resmag="ERROR";
	   }finally{
		   
		    log.setType("微信公众账号--应急方案");
			log.setAct("delete");
			log.setValue(waccount.getWaccountId());
			loggerService.saveLog(log);
			
			mapJson.put("message", resmag);
			return "UrgentStatewaccount" ;
	   }
	 
   }
    
   
   public String insertwaccount(){
	 
	  try{
		 
		  Account account=(Account)session.getAttribute("user");
		  waccount.setCreateAccount(account.getAccountId());
		  waccount.setOperateAccount(account.getAccountId());
		  waccount.setCreateTime( new Timestamp((new Date()).getTime()));
		  waccount.setOperateTime( new Timestamp((new Date()).getTime()));
		  waccountService.insert(waccount);
		  
		  
		  if(null!=waccount.getToken()&&!"".equals(waccount.getToken())){
			  String key=waccount.getWaccountId()+"_token";
		  memCachedClientInstance.set(key, waccount.getToken());
		  
		  }
		  if(null!=waccount.getComcatalogTitle()&&!"".equals(waccount.getComcatalogTitle())){
			  String key=waccount.getWaccountId()+"_huanyingyu";
		  memCachedClientInstance.set(key, waccount.getWelcomeContent());
		  
		  }
		  if(null!=waccount.getNocatalogTitle()&&!"".equals(waccount.getNocatalogTitle()));{
			  String key=waccount.getWaccountId()+"_wufanhuijieguotishiyu";
			  memCachedClientInstance.set(key, waccount.getNoReplyContent());
		  }
		  //将appId和secret加入缓存
		  if(null!=waccount.getAppId() && !"".equals(waccount.getAppId())){
			  String key = waccount.getWaccountId()+"_appid";
			  memCachedClientInstance.set(key, waccount.getAppId());
		  }
		  if(null!=waccount.getAppSecret() && !"".equals(waccount.getAppSecret())){
			  String key = waccount.getWaccountId()+"_secret";
			  memCachedClientInstance.set(key, waccount.getAppSecret()); 
		  }
		  //将天脉的token加入缓存
		  if(null!=waccount.getBridgeToken() && !"".equals(waccount.getBridgeToken())){
			  String key = waccount.getWaccountId()+"_bridge";
			  memCachedClientInstance.set(key, waccount.getBridgeToken()); 
		  }
	   
	  }catch(Exception e){
		 
		  if(e.getMessage().indexOf("waccount_name_unique")>-1){
			  errorMes="该账号已存在！";
		  }else{
			  errorMes="操作失败！"; 
		  }
		  e.printStackTrace();
		 
		  return "filerror";
	  } finally{
		  
		    log.setType("微信公众账号");
			log.setAct("insert");
			log.setValue(waccount.getWaccountId());
			loggerService.saveLog(log);
	  }
	   
	   return "insertwaccount";
   }
   
   

   public String updatewaccount(){
	  
	  try{	 
		
		  Account account=(Account)session.getAttribute("user");
		  waccount.setOperateAccount(account.getAccountId());
		  
		  waccount.setOperateTime( new Timestamp((new Date()).getTime()));
		  /*if(waccount.getType()==1){
	    	  waccount.setAppId("");
	    	  waccount.setAppSecret("");
	      }*/
	      waccountService.updateByPrimaryKeySelective(waccount);
	    
	      
	      if(null!=waccount.getToken()&&!"".equals(waccount.getToken())){
			  String key=waccount.getWaccountId()+"_token";
		  memCachedClientInstance.set(key, waccount.getToken());
		  
		  }
	      if(null!=waccount.getComcatalogTitle()&&!"".equals(waccount.getComcatalogTitle())){
			  String key=waccount.getWaccountId()+"_huanyingyu";
		  memCachedClientInstance.set(key, waccount.getWelcomeContent());
		  
		  }
		  if(null!=waccount.getNocatalogTitle()&&!"".equals(waccount.getNocatalogTitle()));{
			  String key=waccount.getWaccountId()+"_wufanhuijieguotishiyu";
			  memCachedClientInstance.set(key, waccount.getNoReplyContent());
		  }
	   
		  //将appId和secret加入缓存
		  if(null!=waccount.getAppId() && !"".equals(waccount.getAppId())){
			  String key = waccount.getWaccountId()+"_appid";
			  memCachedClientInstance.set(key, waccount.getAppId());
		  }
		  if(null!=waccount.getAppSecret() && !"".equals(waccount.getAppSecret())){
			  String key = waccount.getWaccountId()+"_secret";
			  memCachedClientInstance.set(key, waccount.getAppSecret()); 
		  }
		  //将天脉的token加入缓存
		  if(null!=waccount.getBridgeToken() && !"".equals(waccount.getBridgeToken())){
			  String key = waccount.getWaccountId()+"_bridge";
			  memCachedClientInstance.set(key, waccount.getBridgeToken()); 
		  }
	  }catch(Exception e){
		  if(e.getMessage().indexOf("waccount_name_unique")>-1){
			  errorMes="该账号已存在！";
		  }else{
			  errorMes="操作失败！"; 
		  }
		  e.printStackTrace();
		 
		  return "filerror";
	  } finally{
		    log.setType("微信公众账号");
			log.setAct("update");
			log.setValue(waccount.getWaccountId());
			loggerService.saveLog(log);
	  }
	   
	   return "success";
   }


    
   public String checkwaccount(){
	  
	  try{
	  Waccount old= waccountService.checknameBywaccount(waccount);
	  if(null==old){
		  
		message=true;
	  }else{
		  message=false;  
		  
	  }
	  }catch(Exception e){
		  e.printStackTrace();
	  } 
	   
	   return "checkwaccount";
   }
   
   
   public static void main (String[] args){
	
	   MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
	   System.out.println("===============================================");
	   memCachedClientInstance.get("Wacc1400833336394102_huanyingyu");
   }
   
   public String createurl(){
	   String url="";
	    Map<String,String> params = new HashMap<String, String>();
	   String CONFIG_FILE="";
	   Properties pros = new Properties();
	   try{
	   pros.load(SysConfig.class.getResourceAsStream("/conf.properties"));
	   Enumeration  e = pros.propertyNames();
		while(e.hasMoreElements()) {
			String key = (String)e.nextElement();
			String value = pros.getProperty(key);
			params.put(key, value);
		}
		 url=params.get("waccount_url");
		 url+=wid;
	
		
	   }catch(Exception e){
		   e.printStackTrace();
		   mapJson.put("message", "ERROR");
	   }
	   
	   mapJson.put("message", "OK");
	   mapJson.put("url", url);
		   return "createurl";
   }

	public HttpSession getSession() {
	return session;
}

public void setSession(HttpSession session) {
	this.session = session;
}

	public Waccount getWaccount() {
	return waccount;
}

public void setWaccount(Waccount waccount) {
	this.waccount = waccount;
}

	public boolean isMessage() {
		return message;
	}


	public void setMessage(boolean message) {
		this.message = message;
	}


	public QueryDataBean getQueryData() {
		return queryData;
	}


	public void setQueryData(QueryDataBean queryData) {
		this.queryData = queryData;
	}


	public WaccountServiceImpl getWaccountService() {
		return waccountService;
	}


	public void setWaccountService(WaccountServiceImpl waccountService) {
		this.waccountService = waccountService;
	}


	public Map<String, Object> getMapJson() {
		return mapJson;
	}


	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}




	public Byte getUrgentstate() {
		return urgentstate;
	}

	public void setUrgentstate(Byte urgentstate) {
		this.urgentstate = urgentstate;
	}



	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getErrorMes() {
		return errorMes;
	}

	public void setErrorMes(String errorMes) {
		this.errorMes = errorMes;
	}

	public AccountRelationServiceImpl getAccountRelationService() {
		return accountRelationService;
	}

	public void setAccountRelationService(
			AccountRelationServiceImpl accountRelationService) {
		this.accountRelationService = accountRelationService;
	}

	public LoggerServiceImpl getLoggerService() {
		return loggerService;
	}

	public void setLoggerService(LoggerServiceImpl loggerService) {
		this.loggerService = loggerService;
	}


    
}
