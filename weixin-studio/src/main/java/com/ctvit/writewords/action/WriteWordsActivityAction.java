package com.ctvit.writewords.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.AccountSessionBean;
import com.ctvit.writewords.entity.WriteWordsActivity;
import com.ctvit.writewords.entity.WriteWordsActivityExample;
import com.ctvit.writewords.service.WriteWordsActivityService;
import com.opensymphony.xwork2.ActionSupport;

public class WriteWordsActivityAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8097976145542382963L;
	
	private Logger log = Logger.getLogger(WriteWordsActivityAction.class);
	
	private WriteWordsActivity bean;
	
	private WriteWordsActivityExample example;
	
	private Map<String, Object> queryJson;
	
	private WriteWordsActivityService writeWordsActivityService;
	
	private int page;
	
	private int rows;
	
	/**
	 * 初始化页面
	 * @return
	 */
	public String init(){
		return "init";
	}
	
	/**
	 * 查询
	 * @return
	 */
	public String select(){
		queryJson = new HashMap<String, Object>();
		example.setPage(page);
		example.setRows(rows);
		example.setWaccountId(this.findWaccountId());
		try{
			int total = writeWordsActivityService.findCount(example,this.findWaccountId());
			List<WriteWordsActivity> list = writeWordsActivityService.findListByPaging(example,this.findWaccountId());
			queryJson.put("total", total);
			queryJson.put("rows", list);
		}catch(Exception e){
			log.error("", e);
		}
		return "jsonResult";
	}
	
	/**
	 * 添加或者更新
	 * @return
	 */
	public String addOrUpdate(){
		queryJson = new HashMap<String, Object>();
		try{
			writeWordsActivityService.addOrUpdate(bean, this.findWaccountId());
			queryJson.put("msg", "success");
		}catch(Exception e){
			log.error("", e);
			queryJson.put("msg", "fail");
		}
		return "jsonResult";
	}
	
	/**
	 * 按主键查询
	 * @return
	 */
	public String selectByKey(){
		queryJson = new HashMap<String, Object>();
		try{
			bean = writeWordsActivityService.selectByKey(bean);
			queryJson.put("rows", bean);
		}catch(Exception e){
			log.error("", e);
		}
		return "jsonResult";
	}
	
	/**
	 * 发布
	 * @return
	 */
	public String fabu(){
		queryJson = new HashMap<String, Object>();
		try{
			writeWordsActivityService.fabu(bean);
			queryJson.put("msg", "success");
		}catch(Exception e){
			log.error("", e);
			queryJson.put("msg", "fail");
		}
		return "jsonResult";
	}
	
	/**
	 * 回收
	 * @return
	 */
	public String huishou(){
		queryJson = new HashMap<String, Object>();
		try{
			writeWordsActivityService.huishou(bean);
			queryJson.put("msg", "success");
		}catch(Exception e){
			log.error("", e);
			queryJson.put("msg", "fail");
		}
		return "jsonResult";
	}
	
	/**
	 * 获取session中公众号id
	 * @return
	 */
	private String findWaccountId(){
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		AccountSessionBean accountSessionBean =  (AccountSessionBean)applicationContext.getBean("accountSessionBean");
		return accountSessionBean.getWaccountId();
	}

	public WriteWordsActivity getBean() {
		return bean;
	}

	public void setBean(WriteWordsActivity bean) {
		this.bean = bean;
	}

	public WriteWordsActivityExample getExample() {
		return example;
	}

	public void setExample(WriteWordsActivityExample example) {
		this.example = example;
	}

	public Map<String, Object> getQueryJson() {
		return queryJson;
	}

	public void setQueryJson(Map<String, Object> queryJson) {
		this.queryJson = queryJson;
	}

	public WriteWordsActivityService getWriteWordsActivityService() {
		return writeWordsActivityService;
	}

	public void setWriteWordsActivityService(WriteWordsActivityService writeWordsActivityService) {
		this.writeWordsActivityService = writeWordsActivityService;
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

}
