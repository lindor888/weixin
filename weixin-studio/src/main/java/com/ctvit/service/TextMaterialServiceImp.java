package com.ctvit.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import java.util.Iterator;

import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Keyword;
import com.ctvit.bean.TextMaterial;
import com.ctvit.dao.TextMaterialMapper;
import com.ctvit.util.EncryptUtils;
import com.ctvit.util.KeyGenerator;
import com.ctvit.util.MemcacheUtils;
import com.danga.MemCached.MemCachedClient;

public class TextMaterialServiceImp implements TextMaterialMapper {
	private TextMaterialMapper textMaterialMapper;
	private SqlSessionFactory sqlSessionFactory;
	private KeyGenerator keyGenerator;

	public List<TextMaterial> selectTextMaterialList(TextMaterial textMaterial)
			throws Exception {
		List<TextMaterial> list = textMaterialMapper
				.selectTextMaterialList(textMaterial);
		// TODO Auto-generated method stub
		return list;
	}
	public int selectTextCount(TextMaterial textMaterial)throws Exception{
		int count =textMaterialMapper.selectTextCount(textMaterial);
		return count;
	}

	public List<TextMaterial> selectBytitle(TextMaterial textMaterial)
			throws Exception {
		// TODO Auto-generated method stub
		return textMaterialMapper.selectBytitle(textMaterial);
	}

	public TextMaterialMapper getTextMaterialMapper() {
		return textMaterialMapper;
	}

	public void setTextMaterialMapper(TextMaterialMapper textMaterialMapper) {
		this.textMaterialMapper = textMaterialMapper;
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

	public List<TextMaterial> selecttextMaterial(TextMaterial textMaterial)
			throws Exception {

		if("".equals(textMaterial.getCatalogTitle())){
			textMaterial.setCatalogTitle(null);
		}
		if("".equals(textMaterial.getStatus())){
			textMaterial.setStatus(null);
		}
		
		if("".equals(textMaterial.getWaccount_id())){
			textMaterial.setWaccount_id(null);
		}
		if("".equals(textMaterial.getKeyword0())){
			textMaterial.setKeyword0(null);
		}
		
		
		List<TextMaterial> list = textMaterialMapper
				.selecttextMaterial(textMaterial);
		return list;
	}

	public TextMaterial selecttextMaterialById(TextMaterial textMaterial)
			throws Exception {
		TextMaterial text = textMaterialMapper
				.selecttextMaterialById(textMaterial);
		return text;
	}
	
	public TextMaterial selecttextMaterialWithKeywordsById(TextMaterial textMaterial)
			throws Exception {
		TextMaterial text = textMaterialMapper
				.selecttextMaterialWithKeywordsById(textMaterial);
		return text;
	}

	public int counttextMaterial(TextMaterial textMaterial) throws Exception {
		if("".equals(textMaterial.getCatalogTitle())){
			textMaterial.setCatalogTitle(null);
		}
		if("".equals(textMaterial.getStatus())){
			textMaterial.setStatus(null);
		}
		
		if("".equals(textMaterial.getWaccount_id())){
			textMaterial.setWaccount_id(null);
		}
		if("".equals(textMaterial.getKeyword0())){
			textMaterial.setKeyword0(null);
		}
		int count = textMaterialMapper.counttextMaterial(textMaterial);
		return count;
	}

	public int countquerytextMaterial(TextMaterial textMaterial)
			throws Exception {
		int count = textMaterialMapper.countquerytextMaterial(textMaterial);
		return count;
	}

	public TextMaterial selecttextByTitle(TextMaterial text) throws Exception {

		TextMaterial t = textMaterialMapper.selecttextByTitle(text);
		return t;
	}

	/*
	 * 解析xml数据
	 */

	public  String parseXml(String protocolXML) throws DocumentException {
		if (protocolXML.contains("<xml><ToUserName>%1$s</ToUserName>")) {
			Document doc = (Document) DocumentHelper.parseText(protocolXML);
			Element books = doc.getRootElement();
			Iterator Elements = books.elementIterator("Content");
			Element user = (Element) Elements.next();
			String value = user.getText();
			return value;
		}else{
			return protocolXML;}
		
		
	}

	public String spellXMLfront(TextMaterial text) {
		if (text.getWenbenxml().contains("<xml><ToUserName>%1$s</ToUserName>")) {
			return text.getWenbenxml();
		}

		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");// 创建根节点
		root.addElement("ToUserName").setText("%1$s");
		root.addElement("FromUserName").setText("%2$s");
		root.addElement("CreateTime").setText("%3$s");
		root.addElement("MsgType").addCDATA("text");
		if (text != null && !"".equals(text.getWenbenxml())) {
			root.addElement("Content").setText(text.getWenbenxml());
		}
		return root.asXML();

	}

	public void setInfoForMem(String str, String info) {
		MemCachedClient memCachedClientInstance = MemcacheUtils
				.getMemCachedClientInstance();
		memCachedClientInstance.set(str, info);

	}

	public void deleteInfoForMem(String str) {

		MemCachedClient memCachedClientInstance = MemcacheUtils
				.getMemCachedClientInstance();
		memCachedClientInstance.delete(str);
	}

	public void deleteListForMemKeyword(List<Keyword> list) {

		MemCachedClient memCachedClientInstance = MemcacheUtils
				.getMemCachedClientInstance();

		for (Keyword Word : list) {
			memCachedClientInstance.delete(EncryptUtils.toMessageDigest(Word
					.getKeywordName()));
		}
	}

	public int update(TextMaterial text) throws Exception {
		return textMaterialMapper.update(text);
	}

	public void insertText(TextMaterial text) throws Exception {
		try {
			
			text.setCreateuser(param().getAccount().getUsername());
			text.setCreateuserid(param().getAccount().getAccountId());
			text.setCreatedate(new java.sql.Timestamp(new Date().getTime()));
			text.setModifydate(new java.sql.Timestamp(new Date().getTime()));
			text.setModifyuser(param().getAccount().getUsername());
			text.setModifyuserid(param().getAccount().getAccountId());
			textMaterialMapper.insertText(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int updatedeault(TextMaterial text) throws Exception {
		return textMaterialMapper.updatedeault(text);
	}

	public List<TextMaterial> querytextMaterial(TextMaterial textMaterial)
			throws Exception {
		List<TextMaterial> list = textMaterialMapper
				.querytextMaterial(textMaterial);
		// TODO Auto-generated method stub
		return list;
	}

	public String[] StrToArray(String keyword) {
		if(keyword == null || keyword.trim().equals("")) {
			return new String[0];
		}
		String[] strArray = keyword.split(",");  
		return strArray;
	}
	public	static  AccountSessionBean  param() {
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		AccountSessionBean accountSessionBean =  (AccountSessionBean)applicationContext.getBean("accountSessionBean");
		return accountSessionBean;
	}
	/**
	 * 标题相同的文本素材是否存在
	 * @param text
	 * @return
	 */
	public boolean exists(TextMaterial text) throws Exception {
		TextMaterial tmp = selecttextByTitleNotSelf(text);
		return tmp != null;
	}
	/**
	 * 查询在同一公共账号下是否存在标题相同的素材
	 */
	public int existInWaccount(TextMaterial text) throws Exception {
		return textMaterialMapper.existInWaccount(text);
	}
	/**
	 * 查询标题相同id不同的素材
	 */
	public TextMaterial selecttextByTitleNotSelf(TextMaterial text) throws Exception {
		return textMaterialMapper.selecttextByTitleNotSelf(text);
	}

}
