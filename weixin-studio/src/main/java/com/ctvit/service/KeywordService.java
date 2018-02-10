package com.ctvit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Keyword;
import com.ctvit.dao.KeywordMapper;
import com.ctvit.util.Const;
import com.ctvit.util.EncryptUtils;
import com.ctvit.util.KeyGenerator;
import com.ctvit.util.MemcacheUtils;
import com.danga.MemCached.MemCachedClient;

public class KeywordService  implements KeywordMapper{
	private KeywordMapper keywordMapper;
	private SqlSessionFactory sqlSessionFactory;
	private KeyGenerator keyGenerator;
	
	public int selectKeyWordCount(Keyword keyword)throws Exception{
		if("".equals(keyword.getKeywordName())){
			keyword.setKeywordName(null);
		}
		if("".equals(keyword.getStatus())){
			keyword.setStatus(null);
		}
		if("".equals(keyword.getKeywordRule())){
			keyword.setKeywordRule(null);
		}
		if("".equals(keyword.getWaccount_id())){
			keyword.setWaccount_id(null);
		}
		if("".equals(keyword.getType())){
			keyword.setType(null);
		}
		int count=keywordMapper.selectKeyWordCount(keyword);
		return count;
	}
	
	public List<Keyword> selectKeyWordList(Keyword keyword) throws Exception {
		if("".equals(keyword.getKeywordName())){
			keyword.setKeywordName(null);
		}
		if("".equals(keyword.getStatus())){
			keyword.setStatus(null);
		}
		if("".equals(keyword.getKeywordRule())){
			keyword.setKeywordRule(null);
		}
		if("".equals(keyword.getWaccount_id())){
			keyword.setWaccount_id(null);
		}
		if("".equals(keyword.getType())){
			keyword.setType(null);
		}
		List<Keyword> list=keywordMapper.selectKeyWordList(keyword);
		return list;
	}
	public Keyword selectKeyWordById(Keyword keyword) throws Exception {
		Keyword word=keywordMapper.selectKeyWordById(keyword);
		return word;
	}
	public Keyword selectKeyWordText(Keyword keyword)throws Exception{
		Keyword word=keywordMapper.selectKeyWordText(keyword);
		return word;
	}
	public Keyword selectKeyWordTuWen(Keyword keyword)throws Exception{
		Keyword word=keywordMapper.selectKeyWordTuWen(keyword);
		return word;
	}
	public Keyword selectkwdByTitle(Keyword keyword)throws Exception{
		Keyword word=keywordMapper.selectkwdByTitle(keyword);
		return word;
	}
	public Keyword selectkwdTextByTitle(Keyword keyword)throws Exception{
		Keyword word=keywordMapper.selectkwdTextByTitle(keyword);
		return word;
	}
	public Keyword selectkwdTuWenByTitle(Keyword keyword)throws Exception{
		Keyword word=keywordMapper.selectkwdTuWenByTitle(keyword);
		return word;
	}
	public void insert(Keyword keyword)throws Exception{
		keyword.setId(keyGenerator.getKey(new Keyword()));
		keyword.setWaccount_id(keyword.getWaccount_id());
		keyword.setCreateuser(keyword.getUser().getUsername());
		keyword.setCreateuserid(keyword.getUser().getAccountId());
		keyword.setCreatedate(new Timestamp(new Date().getTime()));
		keyword.setModifyuser(keyword.getUser().getUsername());
		keyword.setModifyuserid(keyword.getUser().getAccountId());
		keyword.setModifydate(new Timestamp(new Date().getTime()));
		keywordMapper.insert(keyword);
	}
	public void delete(Keyword keyword)throws Exception{
		keywordMapper.delete(keyword);
	}
	public void update(Keyword keyword)throws Exception{
		keyword.setWaccount_id(keyword.getWaccount_id());
		keyword.setModifyuser(keyword.getUser().getUsername());
		keyword.setModifyuserid(keyword.getUser().getAccountId());
		keyword.setModifydate(new Timestamp(new Date().getTime()));
		keywordMapper.update(keyword);
	}
	public List<Keyword> selectKeyWordListByMaterial(Keyword keyword)throws Exception{
		List<Keyword> list=keywordMapper.selectKeyWordListByMaterial(keyword);
		return list;
	}
	

	/**
	 * 根据素材id和关键词类型,查询关键字name的集合字符串(以逗号连接)
	 * @param id
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String selectBothRuleByMaterial(Map<String, Object> params) throws Exception {
		return keywordMapper.selectBothRuleByMaterial(params);
	}
	
	/**
	 * 根据素材id和关键词类型,查询关键字name的集合字符串(以逗号连接)
	 * @param id
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String  selectBothTypeKeyWordNameByMaterial (String id, int keywordRule)throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("keywordRule", keywordRule);
		return selectBothRuleByMaterial(params);
	}

	public List<Keyword> selectMatchingBySearchs(String waccount)throws Exception{
		List<Keyword> keyWordList=selectKeyWordRule(waccount);
		return keyWordList;
	}
	/*public LinkedHashMap getMemCachedMap(Keyword keyword,String waccountId)throws Exception{
		LinkedHashMap ruleKeywordMap=new LinkedHashMap();
		List<Keyword> keyWordList=selectKeyWordRule(keyword);
		for(Keyword kd:keyWordList){
			ruleKeywordMap.put(waccountId+"_"+kd.getKeywordName(),keyword.getMaterialId());
		}
		return ruleKeywordMap;
	}
	public LinkedHashMap getMemCachedMapbyKeywordName(Keyword keyword,String waccountId)throws Exception{
		LinkedHashMap ruleKeywordMap=new LinkedHashMap();
		ruleKeywordMap =getMemCachedMap(keyword,waccountId);
		return ruleKeywordMap;
	}*/
	public List<Keyword> selectKeyWordRule(String waccount)throws Exception{
		List<Keyword> keyWordList=keywordMapper.selectKeyWordRule(waccount);
		return keyWordList;
	}
	
	
	public void deleteKeyWordByMaterial(Keyword keyword)throws Exception{
		keywordMapper.deleteKeyWordByMaterial(keyword);
	}
	public Keyword selectwordByName(Keyword keyword)throws Exception{
		Keyword word=keywordMapper.selectwordByName(keyword);
		return word;
	}
	public List<Keyword> selectKwdListbyGraphic(Keyword keyword)throws Exception{
		List<Keyword> keyWordList=keywordMapper.selectKwdListbyGraphic(keyword);
		return keyWordList;
	}
	/**
	 * 添加关键词
	 * @param keywords
	 * @param keywordRule
	 * @param type
	 * @throws Exception
	 */
	public void batchAddKeyWord(Map<String,Object> keywords, int keywordRule, int type)throws Exception{
		List<Keyword> list = new ArrayList<Keyword>();
		Account user = getAccount();
		Iterator<Entry<String, Object>> it = keywords.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String[] kwds = (String[]) entry.getValue();
			for(int i = 0; i < kwds.length; i++) {
				Keyword kwd = new Keyword();
				kwd.setId(keyGenerator.getKey(kwd));
				kwd.setKeywordName(kwds[i]);
				kwd.setKeywordRule(keywordRule);
				kwd.setKeywordWeight(1);
				kwd.setMaterialId(entry.getKey());
				kwd.setWaccount_id(getWaccountId());
				kwd.setCreateuser(user.getUsername());
				kwd.setCreateuserid(user.getAccountId());
				kwd.setCreatedate(new Timestamp(System.currentTimeMillis()));
				kwd.setModifyuser(user.getUsername());
				kwd.setModifyuserid(user.getAccountId());
				kwd.setModifydate(new Timestamp(System.currentTimeMillis()));
				kwd.setStatus(1);
				kwd.setType(type);
				list.add(kwd);
			}
		}
		batchAddKeyWord(list);
		if(keywordRule == 0){
			for(Keyword keyword:list){
				MemcacheUtils.getMemCachedClientInstance().delete(EncryptUtils.toMessageDigest(getWaccountId()+"_"+keyword.getKeywordName()));
				addMapMemcache(getWaccountId());
			}
		}else{
			for(Keyword keyword:list){
				System.out.println("批量添加精确公众号Id:"+getWaccountId());
				MemcacheUtils.getMemCachedClientInstance().set(EncryptUtils.toMessageDigest(getWaccountId()+"_"+keyword.getKeywordName()), keyword.getMaterialId());
				System.out.println(MemcacheUtils.getMemCachedClientInstance().get(EncryptUtils.toMessageDigest(getWaccountId()+"_"+keyword.getKeywordName())));
			}
		}
		
	}
	/**
	 * 添加关键词
	 * @param keywords是一个map，它的key为素材id，value为关联的关键词的数组
	 * @throws Exception
	 */
	public void batchAddKeyWord(Map<String,Object> keywords)throws Exception{
		List<Keyword> list=new ArrayList<Keyword>();
		Account user=getAccount();
		Iterator<Entry<String, Object>> it = keywords.entrySet().iterator();
		while(it.hasNext()){   
			Entry<String, Object> entry = it.next();
			String[] kwds = (String[]) entry.getValue();
			for(int i = 0; i < kwds.length; i++) {
				Keyword kwd=new Keyword();
				kwd.setId(keyGenerator.getKey(new Keyword()));
				kwd.setKeywordName(kwds[i]);
				kwd.setKeywordRule(1);
				kwd.setKeywordWeight(1);
				kwd.setWaccount_id(getWaccountId());
				kwd.setCreateuser(user.getUsername());
				kwd.setCreateuserid(user.getAccountId());
				kwd.setCreatedate(new Timestamp(new Date().getTime()));
				kwd.setModifyuser(user.getUsername());
				kwd.setModifyuserid(user.getAccountId());
				kwd.setModifydate(new Timestamp(new Date().getTime()));
				kwd.setStatus(1);
				String key = entry.getKey();
				kwd.setMaterialId(key);
				if(key.indexOf("Text") != -1){
					kwd.setType(0);
				}else{
					kwd.setType(1);
				}
				list.add(kwd);
			}
		}
		batchAddKeyWord(list);
	}
	
	/**
	 * 添加或修改匹配词缓存
	 * @return
	 */
	public boolean addMapMemcache(String waccountId) {
		try {
			Map<String, String> map = new LinkedHashMap<String, String>();
			List<Keyword> matchList = selectMatchingBySearchs(waccountId);
			MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
			if (null != matchList) {
				for (Keyword matching : matchList) {
					String memeKey = matching.getKeywordName();
					map.put(memeKey, matching.getMaterialId() + "");

				}
				memCachedClientInstance.set(waccountId + "_mohu", map);
				MemcacheUtils.getMemCachedClientInstance();
				map = (LinkedHashMap<String, String>) memCachedClientInstance.get(waccountId+"_mohu");
				return true;

			} else {
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
	public void deleteListForMem(List<Keyword> list,String waccountId){
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
	public boolean deleteMapMemcache(Keyword matching,String waccountId) {
		boolean f = true;
		Map<String, String> map = new LinkedHashMap<String, String>();
		MemCachedClient memCachedClientInstance = MemcacheUtils
				.getMemCachedClientInstance();
		map = (LinkedHashMap<String, String>) memCachedClientInstance.get(waccountId+"_mohu");
		if(map != null) {
			map.remove(matching.getKeywordName());
			memCachedClientInstance.set(waccountId+"_mohu", map);
		}
		return f;
	}
	
	public void batchAddKeyWord(List<Keyword> list)throws Exception{
		keywordMapper.batchAddKeyWord(list);
	}
	public String getWaccountId(){
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		AccountSessionBean accountSessionBean =  (AccountSessionBean)applicationContext.getBean("accountSessionBean");
		return accountSessionBean.getWaccountId();
	}
	private Account getAccount(){
		Account user=null;
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session!=null){
			user = (Account)session.getAttribute(Const.SESSION_USER);
		}
		return user;
	}
	public KeywordMapper getKeywordMapper() {
		return keywordMapper;
	}

	public void setKeywordMapper(KeywordMapper keywordMapper) {
		this.keywordMapper = keywordMapper;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	/**
	 * @param word0
	 * @return
	 */
	public boolean isExsitInWaccount(String[] word0, String waccountId) throws Exception {
		if(word0.length > 0) {
			List<String> kwds = Arrays.asList(word0);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("kwds", kwds);
			param.put("waccountId", waccountId);
			int count = countByNameAndWaccount(param);
			return count > 0;
		}
		return false;
	}

	public Integer countByNameAndWaccount(Map<String, Object> param) throws Exception {
		return keywordMapper.countByNameAndWaccount(param);
	}

	/**
	 * 查看素材的关键词是否和其他素材的关键词重复
	 * @param word0
	 * @param id
	 * @return
	 */
	public boolean isExsitNotSelfInWaccount(String[] word0, String materialId, String waccountId) {
		if(word0.length > 0) {
			List<String> kwds = Arrays.asList(word0);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("kwds", kwds);
			param.put("waccountId", waccountId);
			param.put("materialId", materialId);
			int count = countByNameNotMaterial(param);
			return count > 0;
		}
		return false;
	}

	public int countByNameNotMaterial(Map<String, Object> param) {
		return keywordMapper.countByNameNotMaterial(param);
	}

	public static void main(String[] args) {
		MemCachedClient memCachedClientInstance = MemcacheUtils
				.getMemCachedClientInstance();
		System.out.println(memCachedClientInstance.get("Wacc1400656785427101_mohu"));
		System.out.println(MemcacheUtils.getMemCachedClientInstance().get(EncryptUtils.toMessageDigest("Wacc1400656785427101"+"_lzdtest002")));
	}

}
