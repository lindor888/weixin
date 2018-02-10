package com.ctvit.service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.GraphicMaterial;
import com.ctvit.bean.Imagexml;
import com.ctvit.bean.Keyword;
import com.ctvit.dao.GraphicMaterialMapper;
import com.ctvit.util.EncryptUtils;
import com.ctvit.util.KeyGenerator;
import com.ctvit.util.MemcacheUtils;
import com.danga.MemCached.MemCachedClient;

public class GraphicMaterialServiceImp {
	private GraphicMaterialMapper graphicMaterialMapper;
	private SqlSessionFactory sqlSessionFactory;
	private KeyGenerator keyGenerator;

	public GraphicMaterialMapper getGraphicMaterialMapper() {
		return graphicMaterialMapper;
	}

	public void setGraphicMaterialMapper(
			GraphicMaterialMapper graphicMaterialMapper) {
		this.graphicMaterialMapper = graphicMaterialMapper;
	}

	/**
	 * @return the sqlSessionFactory
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	/**
	 * @param sqlSessionFactory
	 *            the sqlSessionFactory to set
	 */
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * @return the keyGenerator
	 */
	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	/**
	 * @param keyGenerator
	 *            the keyGenerator to set
	 */
	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	/**
	 * 图文挑选素材
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public List<GraphicMaterial> selectGraphicList(
			GraphicMaterial graphicMaterial) throws Exception {
		List<GraphicMaterial> result = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			result = session.selectList(
					"com.ctvit.dao.GraphicMaterialMapper.selectGraphicList",
					graphicMaterial);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * 根据条件查询数据
	 * 
	 * @param queryBean
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<GraphicMaterial> selectgraphicMaterial(
			GraphicMaterial graphicMaterial) throws Exception {
		int page = graphicMaterial.getPage();
		int rows = graphicMaterial.getRows();
		int start = (page - 1) * rows;
		graphicMaterial.setPage(start);

		List<GraphicMaterial> result = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			result = session.selectList(
							"com.ctvit.dao.GraphicMaterialMapper.selectgraphicMaterial",
							graphicMaterial);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * 根据条件统计个数
	 * 
	 * @param queryBean
	 * @return
	 */
	public String countgraphicMaterial(GraphicMaterial graphicMaterial)
			throws Exception {
		
		if("".equals(graphicMaterial.getCatalogTitle())){
			graphicMaterial.setCatalogTitle(null);
		}
		if("".equals(graphicMaterial.getStatus())){
			graphicMaterial.setStatus(null);
		}
		
		if("".equals(graphicMaterial.getWaccount_id())){
			graphicMaterial.setWaccount_id(null);
		}
		if("".equals(graphicMaterial.getKeyword0())){
			graphicMaterial.setKeyword0(null);
		}
		
		String count = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			count = (String) session.selectOne(
					"com.ctvit.dao.GraphicMaterialMapper.countgraphicMaterial",
					graphicMaterial);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return count;
	}

	/**
	 * 根据标题查找图文素材
	 * @param graphicMaterial (必须包含catalogTitle和waccount_id)
	 * @return GraphicMaterial
	 * @throws Exception
	 */
	public GraphicMaterial selectGraphicByTitle(GraphicMaterial graphicMaterial)
			throws Exception {
		return graphicMaterialMapper.selectOneGraphicByTitle(graphicMaterial);
	}

	/**
	 * 根据图文素材主键id查询图文素材
	 * 
	 * @param queryBean
	 * @return
	 * @throws Exception
	 */
	public GraphicMaterial selectgraphicById(GraphicMaterial graphicMaterial)
			throws Exception {
		return graphicMaterialMapper.selectgraphicById(graphicMaterial);
	}
	
	/**
	 * 根据id查询素材，包含关键词
	 * @param id
	 * @return
	 */
	public GraphicMaterial selectgraphicWithKeywordsById(String id) {
		GraphicMaterial graphic = graphicMaterialMapper.selectgraphicWithKeywordsById(id);
		return graphic;
	}

	/**
	 * 添加图文素材
	 * @param graphicMaterial
	 * @throws Exception
	 */
	public void insertGraphic(GraphicMaterial graphicMaterial) throws Exception {
		graphicMaterial.setCreateuser(param().getAccount().getUsername());
		graphicMaterial.setCreateuserid(param().getAccount().getAccountId());
		graphicMaterial.setCreatedate(new java.sql.Timestamp(new Date()
				.getTime()));
		graphicMaterial.setModifydate(new java.sql.Timestamp(new Date()
				.getTime()));
		graphicMaterial.setModifyuser(param().getAccount().getUsername());
		graphicMaterial.setModifyuserid(param().getAccount().getAccountId());
		graphicMaterialMapper.insert(graphicMaterial);
	}

	/**
	 * 更新图文素材
	 * 
	 * @param queryBean
	 * @return
	 * @throws Exception
	 */

	public void updategraphic(GraphicMaterial graphicMaterial) throws Exception {
		graphicMaterialMapper.updategraphic(graphicMaterial);
	}

	public String spellXML(GraphicMaterial graphic) {
		int count = 0;

		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");// 创建根节点
		root.addElement("ToUserName").setText("%1$s");
		root.addElement("FromUserName").setText("%2$s");
		root.addElement("CreateTime").setText("%3$s");
		root.addElement("MsgType").addCDATA("news");
		root.addElement("ArticleCount").setText(count + "");
		@SuppressWarnings("unused")
		Element articlesElement = root.addElement("Articles");
		// 加一个图文xml
		return root.asXML();
	}

	public String spellXMLfront(Imagexml image) {

		int count = 0;
		if (!"".equals(image.getTitle1())) {
			count++;
		}
		if (!"".equals(image.getTitle2())) {
			count++;
		}
		if (!"".equals(image.getTitle3())) {
			count++;
		}
		if (!"".equals(image.getTitle4())) {
			count++;
		}
		if (!"".equals(image.getTitle5())) {
			count++;
		}
		if (!"".equals(image.getTitle6())) {
			count++;
		}
		if (!"".equals(image.getTitle7())) {
			count++;
		}
		if (!"".equals(image.getTitle8())) {
			count++;
		}
		if (!"".equals(image.getTitle9())) {
			count++;
		}
		if (!"".equals(image.getTitle10())) {
			count++;
		}
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");// 创建根节点
		root.addElement("ToUserName").setText("%1$s");
		root.addElement("FromUserName").setText("%2$s");
		root.addElement("CreateTime").setText("%3$s");
		root.addElement("MsgType").addCDATA("news");
		root.addElement("ArticleCount").setText(count + "");
		Element articlesElement = root.addElement("Articles");
		if (!"".equals(image.getTitle1())) {
			Element itemElement = articlesElement.addElement("item");
			itemElement.addElement("Title").setText(image.getTitle1());
			itemElement.addElement("Description").setText(image.getDesc1());
			itemElement.addElement("PicUrl").setText(image.getImageUrl1());
			itemElement.addElement("Url").setText(image.getUrl1());
		}
		if (!"".equals(image.getTitle2())) {
			Element itemElement = articlesElement.addElement("item");
			itemElement.addElement("Title").setText(image.getTitle2());
			itemElement.addElement("Description").setText("");
			itemElement.addElement("PicUrl").setText(image.getImageUrl2());
			itemElement.addElement("Url").setText(image.getUrl2());
		}
		if (!"".equals(image.getTitle3())) {
			Element itemElement = articlesElement.addElement("item");
			itemElement.addElement("Title").setText(image.getTitle3());
			itemElement.addElement("Description").setText("");
			itemElement.addElement("PicUrl").setText(image.getImageUrl3());
			itemElement.addElement("Url").setText(image.getUrl3());
		}
		if (!"".equals(image.getTitle4())) {
			Element itemElement = articlesElement.addElement("item");
			itemElement.addElement("Title").setText(image.getTitle4());
			itemElement.addElement("Description").setText("");
			itemElement.addElement("PicUrl").setText(image.getImageUrl4());
			itemElement.addElement("Url").setText(image.getUrl4());
		}
		if (!"".equals(image.getTitle5())) {
			Element itemElement = articlesElement.addElement("item");
			itemElement.addElement("Title").setText(image.getTitle5());
			itemElement.addElement("Description").setText("");
			itemElement.addElement("PicUrl").setText(image.getImageUrl5());
			itemElement.addElement("Url").setText(image.getUrl5());
		}
		if (!"".equals(image.getTitle6())) {
			Element itemElement = articlesElement.addElement("item");
			itemElement.addElement("Title").setText(image.getTitle6());
			itemElement.addElement("Description").setText("");
			itemElement.addElement("PicUrl").setText(image.getImageUrl6());
			itemElement.addElement("Url").setText(image.getUrl6());
		}
		if (!"".equals(image.getTitle7())) {
			Element itemElement = articlesElement.addElement("item");
			itemElement.addElement("Title").setText(image.getTitle7());
			itemElement.addElement("Description").setText("");
			itemElement.addElement("PicUrl").setText(image.getImageUrl7());
			itemElement.addElement("Url").setText(image.getUrl7());
		}
		if (!"".equals(image.getTitle8())) {
			Element itemElement = articlesElement.addElement("item");
			itemElement.addElement("Title").setText(image.getTitle8());
			itemElement.addElement("Description").setText("");
			itemElement.addElement("PicUrl").setText(image.getImageUrl8());
			itemElement.addElement("Url").setText(image.getUrl8());
		}
		if (!"".equals(image.getTitle9())) {
			Element itemElement = articlesElement.addElement("item");
			itemElement.addElement("Title").setText(image.getTitle9());
			itemElement.addElement("Description").setText("");
			itemElement.addElement("PicUrl").setText(image.getImageUrl9());
			itemElement.addElement("Url").setText(image.getUrl9());
		}
		if (!"".equals(image.getTitle10())) {
			Element itemElement = articlesElement.addElement("item");
			itemElement.addElement("Title").setText(image.getTitle10());
			itemElement.addElement("Description").setText("");
			itemElement.addElement("PicUrl").setText(image.getImageUrl10());
			itemElement.addElement("Url").setText(image.getUrl10());
		}
		
		return root.asXML();
	}

	/**
	 * 删除缓存xml
	 * 
	 * @param queryBean
	 * @return
	 * @throws Exception
	 */

	public void deleteInfoForMem(String str) {
		MemCachedClient memCachedClientInstance = MemcacheUtils
				.getMemCachedClientInstance();
		@SuppressWarnings("unused")
		Boolean deleFlag = memCachedClientInstance.delete(str);
	}

	/**
	 * 批量删除缓存
	 * 
	 * @param list
	 */
	@SuppressWarnings("unused")
	public static void deleteListForMemKeyword(List<Keyword> list) {
		MemCachedClient memCachedClientInstance = MemcacheUtils
				.getMemCachedClientInstance();

		for (Keyword Word : list) {
			Boolean deleFlag = memCachedClientInstance.delete(EncryptUtils
					.toMessageDigest(Word.getKeywordName()));
		}
	}

	@SuppressWarnings("unused")
	public static void setInfoForMem(String str, String info) {
		MemCachedClient memCachedClientInstance = MemcacheUtils
				.getMemCachedClientInstance();
		Boolean setFlag = memCachedClientInstance.set(str, info);

	}

	public Imagexml parseXml(String protocolXML) throws DocumentException {
		Imagexml image = new Imagexml();
		Map<String, String> map = new LinkedHashMap<String, String>();

		Document doc = (Document) DocumentHelper.parseText(protocolXML);
		int i = 0;
		List<Element> itemList = doc.getRootElement().element("Articles")
				.elements("item");
		for (Element e : itemList) {
			i++;
			Iterator Elements = e.elementIterator();
			while (Elements.hasNext()) {
				Element user = (Element) Elements.next();
				String key = user.getName() + i + "";
				String value = user.getText() + "";
				map.put(key, value);
			}

		}

		image.setI(i);
		
		switch (i) {
		case 0: return null;
		case 1:
			image.setTitle1(map.get("Title1"));
			image.setDesc1(map.get("Description1"));
			image.setImageUrl1(map.get("PicUrl1"));
			image.setUrl1(map.get("Url1"));
			break;
		case 2:
			image.setTitle1(map.get("Title1"));
			image.setDesc1(map.get("Description1"));
			image.setImageUrl1(map.get("PicUrl1"));
			image.setUrl1(map.get("Url1"));

			image.setTitle2(map.get("Title2"));
			image.setImageUrl2(map.get("PicUrl2"));
			image.setUrl2(map.get("Url2"));
			break;
		case 3:
			image.setTitle1(map.get("Title1"));
			image.setDesc1(map.get("Description1"));
			image.setImageUrl1(map.get("PicUrl1"));
			image.setUrl1(map.get("Url1"));

			image.setTitle2(map.get("Title2"));
			image.setImageUrl2(map.get("PicUrl2"));
			image.setUrl2(map.get("Url2"));
			image.setTitle3(map.get("Title3"));
			image.setImageUrl3(map.get("PicUrl3"));
			image.setUrl3(map.get("Url3"));
			break;
		case 4:
			image.setTitle1(map.get("Title1"));
			image.setDesc1(map.get("Description1"));
			image.setImageUrl1(map.get("PicUrl1"));
			image.setUrl1(map.get("Url1"));

			image.setTitle2(map.get("Title2"));
			image.setImageUrl2(map.get("PicUrl2"));
			image.setUrl2(map.get("Url2"));
			image.setTitle3(map.get("Title3"));
			image.setImageUrl3(map.get("PicUrl3"));
			image.setUrl3(map.get("Url3"));
			image.setTitle4(map.get("Title4"));
			image.setImageUrl4(map.get("PicUrl4"));
			image.setUrl4(map.get("Url4"));
			break;
		case 5:
			image.setTitle1(map.get("Title1"));
			image.setDesc1(map.get("Description1"));
			image.setImageUrl1(map.get("PicUrl1"));
			image.setUrl1(map.get("Url1"));

			image.setTitle2(map.get("Title2"));
			image.setImageUrl2(map.get("PicUrl2"));
			image.setUrl2(map.get("Url2"));
			image.setTitle3(map.get("Title3"));
			image.setImageUrl3(map.get("PicUrl3"));
			image.setUrl3(map.get("Url3"));
			image.setTitle4(map.get("Title4"));
			image.setImageUrl4(map.get("PicUrl4"));
			image.setUrl4(map.get("Url4"));
			image.setTitle5(map.get("Title5"));
			image.setImageUrl5(map.get("PicUrl5"));
			image.setUrl5(map.get("Url5"));
			break;
		case 6:
			image.setTitle1(map.get("Title1"));
			image.setDesc1(map.get("Description1"));
			image.setImageUrl1(map.get("PicUrl1"));
			image.setUrl1(map.get("Url1"));

			image.setTitle2(map.get("Title2"));
			image.setImageUrl2(map.get("PicUrl2"));
			image.setUrl2(map.get("Url2"));
			image.setTitle3(map.get("Title3"));
			image.setImageUrl3(map.get("PicUrl3"));
			image.setUrl3(map.get("Url3"));
			image.setTitle4(map.get("Title4"));
			image.setImageUrl4(map.get("PicUrl4"));
			image.setUrl4(map.get("Url4"));
			image.setTitle5(map.get("Title5"));
			image.setImageUrl5(map.get("PicUrl5"));
			image.setUrl5(map.get("Url5"));
			image.setTitle6(map.get("Title6"));
			image.setImageUrl6(map.get("PicUrl6"));
			image.setUrl6(map.get("Url6"));
			break;
		case 7:
			image.setTitle1(map.get("Title1"));
			image.setDesc1(map.get("Description1"));
			image.setImageUrl1(map.get("PicUrl1"));
			image.setUrl1(map.get("Url1"));

			image.setTitle2(map.get("Title2"));
			image.setImageUrl2(map.get("PicUrl2"));
			image.setUrl2(map.get("Url2"));
			image.setTitle3(map.get("Title3"));
			image.setImageUrl3(map.get("PicUrl3"));
			image.setUrl3(map.get("Url3"));
			image.setTitle4(map.get("Title4"));
			image.setImageUrl4(map.get("PicUrl4"));
			image.setUrl4(map.get("Url4"));
			image.setTitle5(map.get("Title5"));
			image.setImageUrl5(map.get("PicUrl5"));
			image.setUrl5(map.get("Url5"));
			image.setTitle6(map.get("Title6"));
			image.setImageUrl6(map.get("PicUrl6"));
			image.setUrl6(map.get("Url6"));
			image.setTitle7(map.get("Title7"));
			image.setImageUrl7(map.get("PicUrl7"));
			image.setUrl7(map.get("Url7"));
			break;
		case 8:
			image.setTitle1(map.get("Title1"));
			image.setDesc1(map.get("Description1"));
			image.setImageUrl1(map.get("PicUrl1"));
			image.setUrl1(map.get("Url1"));

			image.setTitle2(map.get("Title2"));
			image.setImageUrl2(map.get("PicUrl2"));
			image.setUrl2(map.get("Url2"));
			image.setTitle3(map.get("Title3"));
			image.setImageUrl3(map.get("PicUrl3"));
			image.setUrl3(map.get("Url3"));
			image.setTitle4(map.get("Title4"));
			image.setImageUrl4(map.get("PicUrl4"));
			image.setUrl4(map.get("Url4"));
			image.setTitle5(map.get("Title5"));
			image.setImageUrl5(map.get("PicUrl5"));
			image.setUrl5(map.get("Url5"));
			image.setTitle6(map.get("Title6"));
			image.setImageUrl6(map.get("PicUrl6"));
			image.setUrl6(map.get("Url6"));
			image.setTitle7(map.get("Title7"));
			image.setImageUrl7(map.get("PicUrl7"));
			image.setUrl7(map.get("Url7"));
			image.setTitle8(map.get("Title8"));
			image.setImageUrl8(map.get("PicUrl8"));
			image.setUrl8(map.get("Url8"));
			break;
		case 9:
			image.setTitle1(map.get("Title1"));
			image.setDesc1(map.get("Description1"));
			image.setImageUrl1(map.get("PicUrl1"));
			image.setUrl1(map.get("Url1"));
			image.setTitle2(map.get("Title2"));
			image.setImageUrl2(map.get("PicUrl2"));
			image.setUrl2(map.get("Url2"));
			image.setTitle3(map.get("Title3"));
			image.setImageUrl3(map.get("PicUrl3"));
			image.setUrl3(map.get("Url3"));
			image.setTitle4(map.get("Title4"));
			image.setImageUrl4(map.get("PicUrl4"));
			image.setUrl4(map.get("Url4"));
			image.setTitle5(map.get("Title5"));
			image.setImageUrl5(map.get("PicUrl5"));
			image.setUrl5(map.get("Url5"));
			image.setTitle6(map.get("Title6"));
			image.setImageUrl6(map.get("PicUrl6"));
			image.setUrl6(map.get("Url6"));
			image.setTitle7(map.get("Title7"));
			image.setImageUrl7(map.get("PicUrl7"));
			image.setUrl7(map.get("Url7"));
			image.setTitle8(map.get("Title8"));
			image.setImageUrl8(map.get("PicUrl8"));
			image.setUrl8(map.get("Url8"));
			image.setTitle9(map.get("Title9"));
			image.setImageUrl9(map.get("PicUrl9"));
			image.setUrl9(map.get("Url9"));
			break;
		case 10:
			image.setTitle1(map.get("Title1"));
			image.setDesc1(map.get("Description1"));
			image.setImageUrl1(map.get("PicUrl1"));
			image.setUrl1(map.get("Url1"));
			image.setTitle2(map.get("Title2"));
			image.setImageUrl2(map.get("PicUrl2"));
			image.setUrl2(map.get("Url2"));
			image.setTitle3(map.get("Title3"));
			image.setImageUrl3(map.get("PicUrl3"));
			image.setUrl3(map.get("Url3"));
			image.setTitle4(map.get("Title4"));
			image.setImageUrl4(map.get("PicUrl4"));
			image.setUrl4(map.get("Url4"));
			image.setTitle5(map.get("Title5"));
			image.setImageUrl5(map.get("PicUrl5"));
			image.setUrl5(map.get("Url5"));
			image.setTitle6(map.get("Title6"));
			image.setImageUrl6(map.get("PicUrl6"));
			image.setUrl6(map.get("Url6"));
			image.setTitle7(map.get("Title7"));
			image.setImageUrl7(map.get("PicUrl7"));
			image.setUrl7(map.get("Url7"));
			image.setTitle8(map.get("Title8"));
			image.setImageUrl8(map.get("PicUrl8"));
			image.setUrl8(map.get("Url8"));
			image.setTitle9(map.get("Title9"));
			image.setImageUrl9(map.get("PicUrl9"));
			image.setUrl9(map.get("Url9"));
			image.setTitle10(map.get("Title10"));
			image.setImageUrl10(map.get("PicUrl10"));
			image.setUrl10(map.get("Url10"));
			break;

		}

		/*
		 * for (String key : map.keySet()) { System.out.println("key= "+ key +
		 * " and value= " + map.get(key)); }
		 */
		return image;

	}

	public String[] StrToArray(String keyword) {
		if(keyword == null || keyword.trim().equals("")) {
			return new String[0];
		}
		String[] strArray = keyword.split(",");

		return strArray;
	}

	public int update(GraphicMaterial gm) throws Exception {
		return graphicMaterialMapper.update(gm);
		
	}

		public int updatedeault(GraphicMaterial gm) throws Exception {
			return graphicMaterialMapper.updatedeault(gm);
		}
		
		public	static  AccountSessionBean  param() {
			WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
			AccountSessionBean accountSessionBean =  (AccountSessionBean)applicationContext.getBean("accountSessionBean");
			String username= accountSessionBean.getAccount().getUsername();
			String accountid=accountSessionBean.getWaccountId();
			System.out.println(username+accountid+"----------------------------------@@@@");
			return accountSessionBean;
			
		}
	
}
