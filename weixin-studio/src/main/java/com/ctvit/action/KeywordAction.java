package com.ctvit.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Keyword;
import com.ctvit.bean.Logger;
import com.ctvit.bean.Waccount;
import com.ctvit.service.KeywordService;
import com.ctvit.service.LoggerServiceImpl;
import com.danga.MemCached.MemCachedClient;
import com.ctvit.service.WaccountServiceImpl;
import com.ctvit.util.Const;
import com.ctvit.util.EncryptUtils;
import com.ctvit.util.MemcacheUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class KeywordAction extends ActionSupport implements Preparable,SessionAware{
	private Map<String,Object>mapJson=new HashMap<String,Object>();
	private List<Waccount>waccountList=new ArrayList<Waccount>();
	private WaccountServiceImpl	waccountService;
	private KeywordService keyWordService;
	private Waccount waccount;
	private Keyword keyword;
	private Keyword eidtkeyword;
	private String page;
	private String rows;
	private String material;
	private Map<String,Object> keyWords =new HashMap<String,Object>();
	private HttpSession session = null;
	private Account user;
	private String waccountId=null;
	private LoggerServiceImpl loggerService;
	private Logger log = new Logger();
	/**
	 * 匹配词查询
	 * 
	 * */
	public String query(){
		String modify="";
		String delete="";
		String operate="";
		if(page==null||rows==null){
			page="1";
			rows="10";
		}
		if(keyword==null){
			keyword=new Keyword();
		}
		keyword.setStart((Integer.parseInt(page)-1)*(Integer.parseInt(rows))<0?0:(Integer.parseInt(page)-1)*(Integer.parseInt(rows)));
		keyword.setEnd(Integer.parseInt(rows));
		keyword.setWaccount_id(waccountId);
		keyword.setUserId(user.getAccountId());
			try {
				/*if(keyword.getWaccount_id()==null||keyword.getWaccount_id().length()==0){
					if(user==null){
						return "dft";
					}
					List<Waccount> lt=waccountService.selectWaccountListbyAccountId(user.getAccountId());
					String[] ids=new String[lt.size()];
					for(int i=0 ;i<lt.size();i++){
						ids[i]=((Waccount)lt.get(i)).getWaccountId();
					}
					keyword.setWaccountIds(ids);
				}*/
				int count=keyWordService.selectKeyWordCount(keyword);
				List<Keyword> list=keyWordService.selectKeyWordList(keyword);
				for(Keyword keyword:list){
					if(keyword!=null){
						modify="<a class='easyui-linkbutton l-btn l-btn-plain' onclick=\"modify('"+keyword.getId()+"')\"><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>修改</span></span></a>";
						delete=	"<a class='easyui-linkbutton l-btn l-btn-plain' onclick=\"deleteKeyWord('"+keyword.getId()+"')\"><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
					}
					operate=modify+"&nbsp;&nbsp;&nbsp;"+delete;
					keyword.setOperate(operate);
				}
				mapJson.put("total", count);
				mapJson.put("rows", list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}finally{
				keyword=null;
			}
			
 		return "dft";
	}
	/**
	 * 跳转
	 * */
	public String tiaozhuan(){
			if(keyword!=null){
				try {
					Keyword word=keyWordService.selectKeyWordById(keyword);
						if(word!=null){
							if(word.getType().equals(0)){//走文本
								eidtkeyword=keyWordService.selectKeyWordText(keyword);
							}else{//走图文
								eidtkeyword=keyWordService.selectKeyWordTuWen(keyword);
							}
						}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return "tiaozhuan";
	}
	public String tiaoxuan(){
		return "tiaoxuan";
	}
	/**
	 * 添加匹配词
	 * */
	public String add(){
		Keyword keywords=null;
		if(eidtkeyword!=null){
			try {
				/*if(eidtkeyword.getType().equals(0)){
					keywords=keyWordService.selectkwdTextByTitle(eidtkeyword);//文本
				}else{
					keywords=keyWordService.selectkwdTuWenByTitle(eidtkeyword);//图文
				}*/
				if(user==null){
					mapJson.put("mg", "fail");
					return "dft";
				}
				if(waccountId ==null){
					mapJson.put("mg", "fail");
					return "dft";
				}
				eidtkeyword.setUser(user);
				eidtkeyword.setWaccount_id(waccountId);
				keywords=keyWordService.selectwordByName(eidtkeyword);
				if(keywords==null){
					keyWordService.insert(eidtkeyword);
					if(eidtkeyword.getStatus()==1){
						MemcacheUtils.getMemCachedClientInstance().delete(EncryptUtils.toMessageDigest(waccountId+"_"+eidtkeyword.getKeywordName()));
						if(eidtkeyword.getKeywordRule()==0){
							addMapMemcache();
						}else{
							MemcacheUtils.getMemCachedClientInstance().set(EncryptUtils.toMessageDigest(waccountId+"_"+eidtkeyword.getKeywordName()), eidtkeyword.getMaterialId());
						}
					}
					
					mapJson.put("mg", "success");
				}else{
					mapJson.put("mg", "exist");
				}
				log.setAct("添加");
				log.setType("匹配词");
				log.setValue(eidtkeyword.getId());
				loggerService.saveLog(log);
				eidtkeyword=new Keyword();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mapJson.put("mg", "fail");
				return "dft";
			}
		}else{
			mapJson.put("mg", "fail");
			return "dft";
		}
		return "dft";
	}
	/**
	 * 更新匹配词
	 * */
	public String update(){
		Keyword keywords=null;
		if(eidtkeyword!=null){
			try {
				/*if(eidtkeyword.getType().equals(0)){
					keywords=keyWordService.selectkwdTextByTitle(eidtkeyword);//文本
				}else{
					keywords=keyWordService.selectkwdTuWenByTitle(eidtkeyword);//图文
				}*/
				if(user==null){
					mapJson.put("mg", "fail");
					return "dft";
				}
				if(waccountId==null){
					mapJson.put("mg", "fail");
					return "dft";
				}
				eidtkeyword.setWaccount_id(waccountId);
				keywords=keyWordService.selectwordByName(eidtkeyword);
				eidtkeyword.setWaccount_id(null);
				eidtkeyword.setUser(user);
				if(keywords==null){
					keyWordService.update(eidtkeyword);
					if(eidtkeyword.getStatus()==1){
						Keyword kwd=new Keyword();
						kwd.setKeywordName(eidtkeyword.getCacheTitle());
						if(eidtkeyword.getKeywordRule()==0){
							MemcacheUtils.getMemCachedClientInstance().delete(EncryptUtils.toMessageDigest(waccountId+"_"+kwd.getKeywordName()));
							addMapMemcache();
						}else{
							deleteMapMemcache(kwd);
							MemcacheUtils.getMemCachedClientInstance().set(EncryptUtils.toMessageDigest(waccountId+"_"+eidtkeyword.getKeywordName()), eidtkeyword.getMaterialId());
						}
					}else{
						Keyword kwd=new Keyword();
						kwd.setKeywordName(eidtkeyword.getCacheTitle());
						if(eidtkeyword.getKeywordRule()==0){
							deleteMapMemcache(kwd);
						}else{
							MemcacheUtils.getMemCachedClientInstance().delete(EncryptUtils.toMessageDigest(waccountId+"_"+kwd.getKeywordName()));
						}
						
					}
					
					mapJson.put("mg", "success");
					log.setAct("更新");
					log.setType("匹配词");
					log.setValue(eidtkeyword.getId());
					loggerService.saveLog(log);
					eidtkeyword=new Keyword();
				}else{
					mapJson.put("mg", "exist");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mapJson.put("mg", "fail");
				return "dft";
			}
		}else{
			mapJson.put("mg", "fail");
			return "dft";
		}
		return "dft";
	}
	/**
	 * 删除匹配词
	 * */
	public String delete(){
		try {
			if(eidtkeyword!=null){
				Keyword keywords=keyWordService.selectKeyWordById(eidtkeyword);
				keyWordService.delete(eidtkeyword);
					if(keywords!=null){
						if(keywords.getKeywordRule()==0){
							deleteMapMemcache(keywords);
						}else{
							System.out.println(MemcacheUtils.getMemCachedClientInstance().get(EncryptUtils.toMessageDigest(waccountId+"_"+keywords.getKeywordName())));
							MemcacheUtils.getMemCachedClientInstance().delete(EncryptUtils.toMessageDigest(waccountId+"_"+keywords.getKeywordName()));
						}
				}
				
				log.setAct("删除");//添加日志操作
				log.setType("匹配词");
				log.setValue(eidtkeyword.getId());
				loggerService.saveLog(log);
				//List<Keyword> matchList = keyWordService.selectMatchingBySearchs(waccountId);
				
				eidtkeyword=new Keyword();
			}
			mapJson.put("mg", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mapJson.put("mg", "fail");
			return "dft";
		}
		return "dft";
	}
	/**
	 * 初始化匹配词管理界面查找公众帐号
	 * 
	 * */
	public String init(){
		/*try {
			waccountList=waccountService.selectWaccountListbyAccountId(user.getAccountId());
			 int index=0;
			 for(int i=0;i<waccountList.size();i++){
				 index=i;
			 }
			 Object temp = waccountList.get(index);
			 waccountList.set(index, waccountList.get(0));
			 waccountList.set(0, (Waccount) temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return "init";
	}
	
	/**
	 * 根据素材id查询关键字集合
	 * 
	 * */
	
	public String byMaterial(){
		try {
			List<Keyword> list=keyWordService.selectKeyWordListByMaterial(keyword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 根据素材id批量删除关键字
	 * 
	 * */
	public String deleteByMaterial(){
			try {
				keyWordService.deleteKeyWordByMaterial(keyword);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "";
	}
	/**
	 * 添加或修改匹配词缓存
	 * @return
	 */
	public boolean addMapMemcache() {
		try {
			Map<String, String> map = new LinkedHashMap<String,String>();
			List<Keyword> matchList = keyWordService.selectMatchingBySearchs(waccountId);
			MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
			if (null != matchList) {
					for (Keyword matching : matchList) {
						String memeKey = matching.getKeywordName();
						map.put(memeKey, matching.getMaterialId() + "");
					
				}
					memCachedClientInstance.set(waccountId+"_mohu", map);
					 MemcacheUtils
						.getMemCachedClientInstance();
				map = (LinkedHashMap<String, String>) memCachedClientInstance.get(waccountId+"_mohu");
					return true;
					
			}else{
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * 删除匹配词缓存
	 */
	public void deleteListForMem(List<Keyword> list){
		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		Map<String, String> map = new LinkedHashMap<String,String>();
		for(Keyword matching:list){
			map = (LinkedHashMap<String, String>) memCachedClientInstance.get(waccountId+"_mohu");
			map.remove(matching.getKeywordName());
			memCachedClientInstance.set(waccountId+"_mohu", map);
		}
	}
	/**
	 * 删除匹配词缓存
	 */
	public boolean deleteMapMemcache(Keyword matching) {
		boolean f = true;
		Map<String, String> map = new LinkedHashMap<String, String>();
		MemCachedClient memCachedClientInstance = MemcacheUtils
				.getMemCachedClientInstance();
		map = (LinkedHashMap<String, String>) memCachedClientInstance.get(waccountId+"_mohu");
		map.remove(matching.getKeywordName());
		memCachedClientInstance.set(waccountId+"_mohu", map);
		return f;
	}

	/**
	 * 批量添加匹配词
	 * 
	 * */
	public String batchAddKeyWord(){
		try {
			keyWordService.batchAddKeyWord(keyWords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public void prepare() throws Exception {
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		AccountSessionBean accountSessionBean =  (AccountSessionBean)applicationContext.getBean("accountSessionBean");
		waccountId=accountSessionBean.getWaccountId();
	}
	
	
	public KeywordService getKeyWordService() {
		return keyWordService;
	}

	public void setKeyWordService(KeywordService keyWordService) {
		this.keyWordService = keyWordService;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}


	public Map<String, Object> getMapJson() {
		return mapJson;
	}


	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}
	public Keyword getEidtkeyword() {
		return eidtkeyword;
	}
	public void setEidtkeyword(Keyword eidtkeyword) {
		this.eidtkeyword = eidtkeyword;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public List<Waccount> getWaccountList() {
		return waccountList;
	}
	public void setWaccountList(List<Waccount> waccountList) {
		this.waccountList = waccountList;
	}
	public WaccountServiceImpl getWaccountService() {
		return waccountService;
	}
	public void setWaccountService(WaccountServiceImpl waccountService) {
		this.waccountService = waccountService;
	}
	public Waccount getWaccount() {
		return waccount;
	}
	public void setWaccount(Waccount waccount) {
		this.waccount = waccount;
	}
	public Map<String, Object> getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(Map<String, Object> keyWords) {
		this.keyWords = keyWords;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public Account getUser() {
		return user;
	}
	public void setUser(Account user) {
		this.user = user;
	}
	public String getWaccountId() {
		return waccountId;
	}
	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId;
	}
	
	public LoggerServiceImpl getLoggerService() {
		return loggerService;
	}
	public void setLoggerService(LoggerServiceImpl loggerService) {
		this.loggerService = loggerService;
	}
	
	public Logger getLog() {
		return log;
	}
	public void setLog(Logger log) {
		this.log = log;
	}
	public void setSession(Map<String, Object> arg0) {
		SessionMap session =  (SessionMap) arg0;		
		this.user = (Account) session.get(Const.SESSION_USER);
	}
	
}
