package com.ctvit.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.ctvit.bean.GraphicMaterial;
import com.ctvit.bean.Imagexml;
import com.ctvit.bean.Keyword;
import com.ctvit.bean.Logger;
import com.ctvit.bean.Waccount;
import com.ctvit.service.GraphicMaterialServiceImp;
import com.ctvit.service.KeywordService;
import com.ctvit.service.LoggerServiceImpl;
import com.ctvit.service.WaccountServiceImpl;
import com.ctvit.util.EncryptUtils;
import com.ctvit.util.KeyGenerator;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.util.ResourceLoader;
import com.danga.MemCached.MemCachedClient;

/**
 * 素材——图文管理类
 * 
 * @author haotp
 * 
 */
public class GraphicMaterialAction {

	
	private GraphicMaterialServiceImp graphicMaterialService;
	private KeywordService keyWordService;
	private Map<String, Object> mapJson = new Hashtable<String, Object>();
	private GraphicMaterial graphicMaterial;
	private GraphicMaterial gm;
	private GraphicMaterial g;
	private KeyGenerator keyGenerator;
	private String page;
	private String rows;
	private String order = "";
	private String sort;
	private int status;
	private String catalogTitle;
	private Keyword keyword;
	private String userInput;
	private JSON retMap;
	private String type;
	private String channelId;
	private String interfaceUrl;
	private String param;
	private Imagexml image;
	private LoggerServiceImpl loggerService;
	private Logger log = new Logger();
	private List<Waccount> waccountList = new ArrayList<Waccount>();
	private WaccountServiceImpl waccountService;

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

	public GraphicMaterial getG() {
		return g;
	}

	public void setG(GraphicMaterial g) {
		this.g = g;
	}

	public GraphicMaterial getGm() {
		return gm;
	}

	public void setGm(GraphicMaterial gm) {
		this.gm = gm;
	}

	public Imagexml getImage() {
		return image;
	}

	public void setImage(Imagexml image) {
		this.image = image;
	}

	public KeywordService getKeyWordService() {
		return keyWordService;
	}

	public void setKeyWordService(KeywordService keyWordService) {
		this.keyWordService = keyWordService;
	}

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	public JSON getRetMap() {
		return retMap;
	}

	public void setRetMap(JSON retMap) {
		this.retMap = retMap;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getInterfaceUrl() {
		return interfaceUrl;
	}

	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCatalogTitle() {
		return catalogTitle;
	}

	public void setCatalogTitle(String catalogTitle) {
		this.catalogTitle = catalogTitle;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public GraphicMaterial getGraphicMaterial() {
		return graphicMaterial;
	}

	public void setGraphicMaterial(GraphicMaterial graphicMaterial) {
		this.graphicMaterial = graphicMaterial;
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

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	public GraphicMaterialServiceImp getGraphicMaterialService() {
		return graphicMaterialService;
	}

	public void setGraphicMaterialService(GraphicMaterialServiceImp graphicMaterialService) {
		this.graphicMaterialService = graphicMaterialService;
	}

	public Map<String, Object> getMapJson() {
		return mapJson;
	}

	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}

	public String init() {
		graphicMaterial = new GraphicMaterial();
		return "init";
	}

	/**
	 * 图文素材列表
	 * @return
	 */
	public String querylist() {
		if (page == null || rows == null) {
			page = "1";
			rows = "10";
		}
		if (graphicMaterial == null) {
			graphicMaterial = new GraphicMaterial();
		}
		graphicMaterial.setStart((Integer.parseInt(page) - 1) * (Integer.parseInt(rows)));
		graphicMaterial.setEnd(Integer.parseInt(rows));
		try {
			graphicMaterial.setWaccount_id(GraphicMaterialServiceImp.param().getWaccountId());
			String count = graphicMaterialService.countgraphicMaterial(graphicMaterial);
			List<GraphicMaterial> list = graphicMaterialService.selectgraphicMaterial(graphicMaterial);
			for (GraphicMaterial graphicMaterial : list) {
				if (graphicMaterial != null) {
					String s0 = keyWordService.selectBothTypeKeyWordNameByMaterial(graphicMaterial.getId(),
							Keyword.RULE_MOHU);
					String s1 = keyWordService.selectBothTypeKeyWordNameByMaterial(graphicMaterial.getId(),
							Keyword.RULE_JINGQUE);
					graphicMaterial.setKeyword0(s0);
					graphicMaterial.setKeyword1(s1);
				}
			}
			mapJson.put("total", count);
			mapJson.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//清理现场
		page = null;
		rows = null;
		graphicMaterial = null;

		return "dft";
	}
	
	
	
	public String querylisted() {
		if (page == null || rows == null) {
			page = "1";
			rows = "10";
		}
		if (graphicMaterial == null) {
			graphicMaterial = new GraphicMaterial();
		}
		graphicMaterial.setStart((Integer.parseInt(page) - 1) * (Integer.parseInt(rows)));
		graphicMaterial.setEnd(Integer.parseInt(rows));
		graphicMaterial.setStatus(1);
		try {
			List<GraphicMaterial> list = graphicMaterialService.selectGraphicList(graphicMaterial);
			String count = graphicMaterialService.countgraphicMaterial(graphicMaterial);
			for (GraphicMaterial graphicMaterial : list) {
				if (graphicMaterial != null) {
					String s0 = keyWordService.selectBothTypeKeyWordNameByMaterial(graphicMaterial.getId(),
							Keyword.RULE_MOHU);
					String s1 = keyWordService.selectBothTypeKeyWordNameByMaterial(graphicMaterial.getId(),
							Keyword.RULE_JINGQUE);
					graphicMaterial.setKeyword0(s0);
					graphicMaterial.setKeyword1(s1);
				}
			}
			mapJson.put("total", count);
			mapJson.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//清理现场
		page = null;
		rows = null;
		graphicMaterial = null;

		return "dft";
	}
	
	

	/*
	 * 新增和修改跳转页
	 */
	public String modify() {
		if (g != null) { // 标题，内容xml，状态
			try {
				
				gm = graphicMaterialService.selectgraphicWithKeywordsById(g.getId());
				image = graphicMaterialService.parseXml(gm.getTuwenxml());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "modify";
	}

	/*
	 * 新增图文素材
	 */
	public String add() {
		if (gm != null) {
			try {
				String waccountId = GraphicMaterialServiceImp.param().getWaccountId();
				gm.setWaccount_id(waccountId);
				GraphicMaterial g = graphicMaterialService.selectGraphicByTitle(gm);// 判断库里边是否有此标题(根据标题和当前公众号)
				if (g == null) {

					String[] word0 = graphicMaterialService.StrToArray(gm.getKeyword0());
					if (word0.length > 0) {
						if (keyWordService.isExsitInWaccount(word0, waccountId)) {
							mapJson.put("mg", "mhexist");
							
							//清理现场
							gm = null;
							
							return "dft";
						}
					}

					String[] word1 = graphicMaterialService.StrToArray(gm.getKeyword1());
					if (word1.length > 0) {
						if (keyWordService.isExsitInWaccount(word1, waccountId)) {
							mapJson.put("mg", "jqexist");
							
							//清理现场
							gm = null;
							
							return "dft";
						}
					}

					KeyGenerator keyGenerator = new KeyGenerator();
					String id = keyGenerator.getKey(gm);
					gm.setId(id);// 主键id

					String weixinxml = graphicMaterialService.spellXMLfront(image);
					gm.setTuwenxml(weixinxml);// 插入前转为微信xml格式

					graphicMaterialService.insertGraphic(gm);// 为空执行插入操作
					
					if(gm.getStatus() == 1) {

						if (word0.length > 0) {
							HashMap<String, Object> word0map = new HashMap<String, Object>();
							word0map.put(id, word0);
							keyWordService.batchAddKeyWord(word0map, Keyword.RULE_MOHU, Keyword.TYPE_TUWEN);// 批量插入绑定的关键字
						}
						if (word1.length > 0) {
							HashMap<String, Object> word1map = new HashMap<String, Object>();
							word1map.put(id, word1);
							keyWordService.batchAddKeyWord(word1map, Keyword.RULE_JINGQUE, Keyword.TYPE_TUWEN);// 批量插入绑定的关键字
						}
						
						GraphicMaterialServiceImp.setInfoForMem(gm.getId() + "", gm.getTuwenxml());
						
						log.setAct("添加");
						log.setType("图文素材");
						log.setValue(gm.getId());
						loggerService.saveLog(log);
					}
					
					mapJson.clear();
					mapJson.put("mg", "success");
					
				} else {
					mapJson.clear();
					mapJson.put("mg", "exist");
				}

			} catch (Exception e) {
				e.printStackTrace();
				mapJson.clear();
				mapJson.put("mg", "fail");
			}

		} else {
			mapJson.clear();
			mapJson.put("mg", "fail");
		}

		//清理现场
		gm = null;
		
		return "dft";
	}

	/**
	 * 挑选图文素材
	 * 
	 * */
	public String query() {
		try {
			if (graphicMaterial == null) {
				graphicMaterial = new GraphicMaterial();
			}

			if (page == null || rows == null) {
				page = "1";
				rows = "10";
			}

			
			graphicMaterial.setStart((Integer.parseInt(page) - 1) * (Integer.parseInt(rows))>0?(Integer.parseInt(page) - 1) * (Integer.parseInt(rows)):0);
			graphicMaterial.setEnd(Integer.parseInt(rows));
			graphicMaterial.setStatus(1);
			if(null==graphicMaterial.getWaccount_id()||"".equals(graphicMaterial.getWaccount_id()))
			graphicMaterial.setWaccount_id(GraphicMaterialServiceImp.param().getWaccountId());
			String count = graphicMaterialService.countgraphicMaterial(graphicMaterial);
			
			List<GraphicMaterial> list = graphicMaterialService.selectGraphicList(graphicMaterial);
			
			mapJson.put("total", count);
			mapJson.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			graphicMaterial=null;
		}

		//清理现场
		graphicMaterial = null;
		
		return "dft";
	}

	/*
	 * 修改图文素材
	 */
	public String update() {

		if (gm != null) {

			try {

				GraphicMaterial grap = graphicMaterialService.selectGraphicByTitle(gm);// 根据前台的标题和公众号id查询素材
				
				if (grap == null || grap.getId().equals(gm.getId())) { // 如果grap为空，那么标题已经修改了，如果grap不为空同时id相同

					gm.setModifydate(new java.sql.Timestamp(new Date().getTime()));
					gm.setModifyuser(GraphicMaterialServiceImp.param().getAccount().getUsername());
					gm.setModifyuserid(GraphicMaterialServiceImp.param().getAccount().getAccountId());
					String weixinxml = graphicMaterialService.spellXMLfront(image);
					gm.setTuwenxml(weixinxml);// 更新前转为微信xml格式

					graphicMaterialService.update(gm);

					// 删除缓存
					keyword = new Keyword();
					keyword.setMaterialId(gm.getId());
					List<Keyword> keywordListbytuwen = keyWordService.selectKwdListbyGraphic(keyword);
					for (Keyword kwds : keywordListbytuwen) {
						if (kwds.getKeywordRule() == 0) {// mohu
							keyWordService.deleteMapMemcache(kwds, GraphicMaterialServiceImp.param().getWaccountId());
						} else {// jingque
							System.out.println("精确公众号id："+GraphicMaterialServiceImp.param().getWaccountId());
							
							MemcacheUtils.getMemCachedClientInstance().delete(
									EncryptUtils.toMessageDigest(GraphicMaterialServiceImp.param().getWaccountId()
											+ "_" + kwds.getKeywordName()));

							System.out.println(MemcacheUtils.getMemCachedClientInstance().get(EncryptUtils.toMessageDigest(GraphicMaterialServiceImp.param().getWaccountId()+"_"+kwds.getKeywordName())));
						}
					}
					// 删除库中的关键词
					keyWordService.deleteKeyWordByMaterial(keyword);// 批量删除相关的匹配词

					String waccountId = gm.getWaccount_id();
					//检查模糊关键词是否存在
					String[] word0 = graphicMaterialService.StrToArray(gm.getKeyword0());
					if (word0.length > 0) {
						if (keyWordService.isExsitInWaccount(word0, waccountId)) {
							mapJson.put("mg", "mhexist");
							
							gm = null;
							return "dft";
						}
					}
					//检查精确关键词是否存在
					String[] word1 = graphicMaterialService.StrToArray(gm.getKeyword1());
					if (word1.length > 0) {
						if (keyWordService.isExsitInWaccount(word1, waccountId)) {
							mapJson.put("mg", "jqexist");

							gm = null;
							return "dft";
						}
					}
					
					if (gm.getStatus() == 1) { // 新状态为开启
						GraphicMaterialServiceImp.setInfoForMem(gm.getId(), weixinxml);// 覆盖原来的xml
						// 再添加新的关键词
						HashMap<String, Object> map = new HashMap<String, Object>();
						if (word0.length > 0) {
							map.put(gm.getId(), word0);
							keyWordService.batchAddKeyWord(map, Keyword.RULE_MOHU, Keyword.TYPE_TUWEN);// 批量插入绑定的关键字
						}
						if (word1.length > 0) {
							map.put(gm.getId(), word1);
							keyWordService.batchAddKeyWord(map, Keyword.RULE_JINGQUE, Keyword.TYPE_TUWEN);// 批量插入绑定的关键字
						}
					} else if (gm.getStatus() == 0) {
						graphicMaterialService.deleteInfoForMem(gm.getId());
					}

					log.setAct("更新");
					log.setType("图文素材");
					log.setValue(gm.getId());
					loggerService.saveLog(log);
					
					mapJson.clear();
					mapJson.put("mg", "success");
					
				} else {

					mapJson.clear();
					mapJson.put("mg", "exist");
				}
			} catch (Exception e) {
				e.printStackTrace();
				mapJson.clear();
				mapJson.put("mg", "fail");
			}
		} else {
			mapJson.clear();
			mapJson.put("mg", "fail");
		}
		gm = null;
		return "dft";
	}

	/*
	 * 删除图文素材
	 */

	public String delete() {

		try {

			if (gm != null) {

				graphicMaterialService.deleteInfoForMem(gm.getId() + ""); // 根据id删除相关xml（id,xml）
				Keyword keyword = new Keyword();
				keyword.setMaterialId(gm.getId() + "");
				List<Keyword> keywordListbytuwen = keyWordService.selectKwdListbyGraphic(keyword);
				for (Keyword kwds : keywordListbytuwen) {
					if (kwds.getKeywordRule() == 0) {// mohu
						keyWordService.deleteMapMemcache(kwds, GraphicMaterialServiceImp.param().getWaccountId());
					} else {// jingque
						MemcacheUtils.getMemCachedClientInstance().delete(
								EncryptUtils.toMessageDigest(GraphicMaterialServiceImp.param().getWaccountId()
										+ "_" + kwds.getKeywordName()));
					}
				}
				keyWordService.deleteKeyWordByMaterial(keyword);// 批量删除相关的匹配词

				gm.setStatus(-1);
				graphicMaterialService.updatedeault(gm);// 根据id和状态值做更新操作

				gm = new GraphicMaterial();
			}
			mapJson.put("mg", "success");
			log.setAct("删除");// 添加日志操作
			log.setType("匹配词");
			log.setValue(gm.getId());
			loggerService.saveLog(log);
		} catch (Exception e) {
			e.printStackTrace();
			mapJson.put("mg", "fail");
		}
		
		gm = null;
		return "dft";
	}
	
	/*
	 * 图文素材状态切换
	 */
	
	public String switching() {
		if (gm != null) {
			try {
					gm.setModifydate(new java.sql.Timestamp(new Date().getTime()));
					gm.setModifyuser(GraphicMaterialServiceImp.param().getAccount().getUsername());
					gm.setModifyuserid(GraphicMaterialServiceImp.param().getAccount().getAccountId());

					graphicMaterialService.update(gm);
					if (gm.getStatus() == 1) { // 新状态为开启
						GraphicMaterial gp= graphicMaterialService.selectgraphicById(gm);//查询图文xml
						GraphicMaterialServiceImp.setInfoForMem(gm.getId(), gp.getTuwenxml());//添加缓存
					} else if (gm.getStatus() == 0) {
						graphicMaterialService.deleteInfoForMem(gm.getId());
						keyword = new Keyword();
						keyword.setMaterialId(gm.getId());
						List<Keyword> keywordListbytuwen = keyWordService.selectKwdListbyGraphic(keyword);
						for (Keyword kwds : keywordListbytuwen) {
							if (kwds.getKeywordRule() == 0) {// mohu
								keyWordService.deleteMapMemcache(kwds, GraphicMaterialServiceImp.param().getWaccountId());
							} else {// jingque
								MemcacheUtils.getMemCachedClientInstance().delete(
										EncryptUtils.toMessageDigest(GraphicMaterialServiceImp.param().getWaccountId()
												+ "_" + kwds.getKeywordName()));
							}
						}
						// 删除库中的关键词
						keyWordService.deleteKeyWordByMaterial(keyword);// 批量删除相关的匹配词
					}

					log.setAct("修改状态");
					log.setType("图文素材");
					log.setValue(gm.getId());
					loggerService.saveLog(log);
					
					mapJson.clear();
					mapJson.put("mg", "success");
					
				
			} catch (Exception e) {
				e.printStackTrace();
				mapJson.clear();
				mapJson.put("mg", "fail");
			}
		} else {
			mapJson.clear();
			mapJson.put("mg", "fail");
		}
		gm = null;
		return "dft";
	}

	/**
	 * 获取新CMS接口数据实现图文挑选数据
	 * 
	 * */
	public String search() {
		String par = "&version=2.2&indent=on&wt=json&sort=publishdate+desc,score+desc";
		String url = ResourceLoader.getInstance().getConfig().getProperty("searchAddress");

		String shards = "";
		String shardsUrl = "";

		if (param != null) {
			par = param;
		}

		shardsUrl = url.replace("http://", "");

		String action = "article/select/";

		int start = 0;
		start = (Integer.parseInt(page) - 1) * Integer.parseInt(rows);

		if (type.indexOf("ARTI") >= 0) {
			action = "article/select/";
			shards = shards + shardsUrl + "article,";
		}

		if (type.indexOf("PHOA") >= 0) {
			action = "photo/select/";
			shards = shards + shardsUrl + "photo,";
		}

		if (type.indexOf("VIDE") >= 0) {
			action = "video/select/";
			shards = shards + shardsUrl + "video,";
		}

		if (type.indexOf("AUDI") >= 0) {
			action = "audio/select/";
			shards = shards + shardsUrl + "audio,";
		}

		if (type.indexOf("VIDA") >= 0) {
			action = "videoalbum/select/";
			shards = shards + shardsUrl + "videoalbum,";
		}

		try {
			userInput = java.net.URLEncoder.encode(userInput, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (shards.length() > 0) {
			shards = "&shards=" + shards;
		}

		url = url + action + "?q=" + userInput + "&start=" + start + "&rows=" + rows + par + shards;
		System.out.println(url);
		retMap = DownLoadPages(url);
		return "search";
	}

	public static JSON DownLoadPages(String urlStr) {
		URL url = null;
		HttpURLConnection httpConn = null;
		InputStream in = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			System.out.println(urlStr);
			url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("POST");

			in = httpConn.getInputStream();
			System.out.println(httpConn.getHeaderField("response"));
			br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			for (String str = br.readLine(); str != null; str = br.readLine()) {
				sb.append(str);
			}
			String sb1 =sb.toString().replaceFirst("numFound", "total");
			sb1 =sb1.replaceAll("docs", "rows");
			JSONObject json = JSONObject.fromObject(sb1);
			json.remove("responseHeader");
			json = json.getJSONObject("response");
			// System.out.println("=========================");
			// System.out.println("========================="+json);
			// System.out.println("=========================");
			return (JSON) json;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				httpConn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 删除匹配词缓存
	 */
	public void deleteListForMem(List<Keyword> list) {
		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Keyword matching : list) {
			map = (LinkedHashMap<String, String>) memCachedClientInstance.get(GraphicMaterialServiceImp.param()
					.getWaccountId() + "_mohu");
			map.remove(matching.getKeywordName());
			memCachedClientInstance.set(GraphicMaterialServiceImp.param().getWaccountId() + "_mohu", map);
		}
	}

	/**
	 * 删除匹配词缓存
	 */
	public boolean deleteMapMemcache(Keyword matching) {
		boolean f = true;
		Map<String, String> map = new LinkedHashMap<String, String>();
		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		map = (LinkedHashMap<String, String>) memCachedClientInstance.get(GraphicMaterialServiceImp.param()
				.getWaccountId() + "_mohu");
		map.remove(GraphicMaterialServiceImp.param().getWaccountId() + "_" + matching.getKeywordName());
		memCachedClientInstance.set(GraphicMaterialServiceImp.param().getWaccountId() + "_mohu", map);
		return f;
	}
}
