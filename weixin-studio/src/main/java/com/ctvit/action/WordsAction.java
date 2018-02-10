package com.ctvit.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Words;
import com.ctvit.bean.WordsExample;
import com.ctvit.service.WordsService;
import com.ctvit.util.ActionUtil;
import com.opensymphony.xwork2.ActionSupport;

public class WordsAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8002622301202663113L;
	
	private Logger log = Logger.getLogger(WordsAction.class);
	
	private Words bean;
	
	private WordsExample example;
	
	private Map<String, Object> queryJson;
	
	private WordsService wordsService;
	
	private int page;
	
	private int rows;
	
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
		example.setWaccountId(ActionUtil.findWaccountId());
		try{
			int total = wordsService.findCount(example);
			List<Words> list = wordsService.findListByPaging(example);
			queryJson.put("total", total);
			queryJson.put("rows", list);
		}catch(Exception e){
			log.error("", e);
		}
		
		return "select";
	}
	
	public String toAdd(){
		return "toAdd";
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String add(){
		queryJson = new HashMap<String, Object>();
		try{
			bean.setWaccountId(ActionUtil.findWaccountId());
			//查询是否已经添加
			example = new WordsExample();
			example.setContent(bean.getContent());
			example.setWaccountId(bean.getWaccountId());
			List<Words> list = wordsService.findListByPaging(example);
			if(list!=null&&list.size()>0){
				queryJson.put("msg", "limit");
				return "add";
			}
			wordsService.add(bean);
			queryJson.put("msg", "success");
		}catch(Exception e){
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "add";
	}
	
	public String toUpdate(){
		bean = wordsService.findByKey(bean);
		return "toUpdate";
	}
	
	/**
	 * 更新
	 * @return
	 */
	public String update(){
		queryJson = new HashMap<String, Object>();
		try{
			wordsService.update(bean);
			queryJson.put("msg", "success");
		}catch(Exception e){
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "update";
	}
	
	public String del(){
		queryJson = new HashMap<String, Object>();
		try{
			wordsService.del(bean);
			queryJson.put("msg", "success");
		}catch(Exception e){
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "del";
	}
	

	public Words getBean() {
		return bean;
	}

	public void setBean(Words bean) {
		this.bean = bean;
	}

	public WordsExample getExample() {
		return example;
	}

	public void setExample(WordsExample example) {
		this.example = example;
	}

	public Map<String, Object> getQueryJson() {
		return queryJson;
	}

	public void setQueryJson(Map<String, Object> queryJson) {
		this.queryJson = queryJson;
	}

	public WordsService getWordsService() {
		return wordsService;
	}

	public void setWordsService(WordsService wordsService) {
		this.wordsService = wordsService;
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
