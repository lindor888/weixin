package com.ctvit.writewords.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.AccountSessionBean;
import com.ctvit.writewords.entity.WriteWordsList;
import com.ctvit.writewords.entity.WriteWordsListExample;
import com.ctvit.writewords.service.WriteWordsListService;
import com.opensymphony.xwork2.ActionSupport;

public class WriteWordsListAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5464695845028160725L;
	
	private Logger log = Logger.getLogger(WriteWordsListAction.class);
	
	private WriteWordsList bean;
	
	private WriteWordsListExample example;
	
	private Map<String, Object> queryJson;
	
	private WriteWordsListService writeWordsListService ;
	
	private String data;
	
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
		try{
			int total = writeWordsListService.findCount(example, this.findWaccountId());
			List<WriteWordsList> list = writeWordsListService.findListByPaging(example,this.findWaccountId());
			queryJson.put("total", total);
			queryJson.put("rows", list);
		}catch(Exception e){
			log.error("", e);
		}
		return "jsonResult";
	}
	
	/**
	 * 接收提交的汉字（对外接口）
	 * @return
	 * @throws IOException
	 */
	public String receive() throws IOException{
		queryJson = new HashMap<String, Object>();
		try{
			writeWordsListService.receive(bean,data);
			queryJson.put("msg", "success");
		}catch(Exception e){
			log.error("", e);
			queryJson.put("msg", "fail");
		}
		
		return "jsonResult";
	}
	
	/**
	 * 对外接口，展示最近活动的数据
	 * @return
	 */
	public String show() {
		queryJson = new HashMap<String, Object>();
		try{
			List<WriteWordsList> list = writeWordsListService.show();
			queryJson.put("rows", list);
		}catch(Exception e){
			log.error("", e);
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

	public WriteWordsList getBean() {
		return bean;
	}

	public void setBean(WriteWordsList bean) {
		this.bean = bean;
	}

	public WriteWordsListExample getExample() {
		return example;
	}

	public void setExample(WriteWordsListExample example) {
		this.example = example;
	}

	public Map<String, Object> getQueryJson() {
		return queryJson;
	}

	public void setQueryJson(Map<String, Object> queryJson) {
		this.queryJson = queryJson;
	}

	public WriteWordsListService getWriteWordsListService() {
		return writeWordsListService;
	}

	public void setWriteWordsListService(WriteWordsListService writeWordsListService) {
		this.writeWordsListService = writeWordsListService;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
