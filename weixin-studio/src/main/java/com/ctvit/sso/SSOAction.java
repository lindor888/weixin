package com.ctvit.sso;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Account;
import com.ctvit.util.Const;
import com.ctvit.util.MemcacheUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 微信系统做为用户中心提供单点登陆所需要的用户信息
 * @author tqc
 *
 */
public class SSOAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3981859352415165785L;
	
	private static final Logger log = Logger.getLogger(SSOAction.class);
	
	private Map<String, Object> queryJson;
	
	private String from;
	
	private String redirectURL;
	
	private String ticket;
	
	/**
	 * 获取票证
	 * @return
	 * @throws Exception 
	 */
	public String getTicket() throws Exception{
		String ticket = String.valueOf(System.currentTimeMillis());
		Account account = (Account)ServletActionContext.getRequest().getSession().getAttribute(Const.SESSION_USER);
		MemcacheUtils.getMemCachedClientInstance().set("SSO_TICKET"+ ticket, account , 1000 * 15);
		from =URLDecoder.decode(from, "UTF-8");
		log.debug("SSO登陆获取TICKET接口：ticket="+ticket+",from="+from);
		if(from.contains("?")){
			redirectURL=from+"&ticket="+ticket;
		}else{
			redirectURL=from+"?ticket="+ticket;
		}
		log.debug("SSO登陆获取TICKET接口，重定向到业务系统："+redirectURL);
		return SUCCESS;
	}
	
	/**
	 * 校验
	 * @return
	 */
	public String verify(){
		Account account = (Account)MemcacheUtils.getMemCachedClientInstance().get("SSO_TICKET"+ ticket);
		queryJson = new HashMap<String, Object>();
		if(account!=null){
			log.debug("SSO登陆验证TICKET接口,验证ticket:"+ticket+account.getAccountId()==null?"验证成功":"验证成功"+",用户ID："+account.getAccountId());
			queryJson.put("userid", account.getAccountId());
			queryJson.put("username", account.getLogin());
		}
		return "toJson";
	}
	

	public Map<String, Object> getQueryJson() {
		return queryJson;
	}

	public void setQueryJson(Map<String, Object> queryJson) {
		this.queryJson = queryJson;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}


	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	
}
