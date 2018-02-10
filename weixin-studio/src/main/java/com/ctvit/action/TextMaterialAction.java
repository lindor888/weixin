package com.ctvit.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.ctvit.bean.Keyword;
import com.ctvit.bean.Logger;
import com.ctvit.bean.TextMaterial;
import com.ctvit.bean.Waccount;
import com.ctvit.service.GraphicMaterialServiceImp;
import com.ctvit.service.KeywordService;
import com.ctvit.service.LoggerServiceImpl;
import com.ctvit.service.TextMaterialServiceImp;
import com.ctvit.service.WaccountServiceImpl;
import com.ctvit.util.EncryptUtils;
import com.ctvit.util.KeyGenerator;
import com.ctvit.util.MemcacheUtils;

public class TextMaterialAction {
	private TextMaterialServiceImp textMaterialServiceImp;
	private KeywordService keyWordService;
	private Map<String, Object> mapJson = new Hashtable<String, Object>();
	private TextMaterial textMaterial;
	private TextMaterial text;
	private String page;
	private String rows;
	private LoggerServiceImpl loggerService;
	private Logger log = new Logger();
	private List<Waccount> waccountList = new ArrayList<Waccount>();
	private WaccountServiceImpl waccountService;
	private Keyword keyword;
	

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
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

	public KeywordService getKeyWordService() {
		return keyWordService;
	}

	public void setKeyWordService(KeywordService keyWordService) {
		this.keyWordService = keyWordService;
	}

	public TextMaterial getText() {
		return text;
	}

	public void setText(TextMaterial text) {
		this.text = text;
	}

	public TextMaterialServiceImp getTextMaterialServiceImp() {
		return textMaterialServiceImp;
	}

	public void setTextMaterialServiceImp(TextMaterialServiceImp textMaterialServiceImp) {
		this.textMaterialServiceImp = textMaterialServiceImp;
	}

	public TextMaterial getTextMaterial() {
		return textMaterial;
	}

	public void setTextMaterial(TextMaterial textMaterial) {
		this.textMaterial = textMaterial;
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

	public Map<String, Object> getMapJson() {
		return mapJson;
	}

	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}

	/**
	 * 初始化匹配词管理界面查找公众帐号
	 * 
	 * */
	public String init() {
		return "init";
	}

	public String query() {

		try {
			if (page == null || rows == null) {
				page = "1";
				rows = "10";
			}
			if (textMaterial == null) {
				textMaterial = new TextMaterial();
				
				
			}
			textMaterial.setPage((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
			textMaterial.setRows(Integer.parseInt(rows));
			// textMaterial.setCatalogTitle("马航33");//测试用
			if(null==textMaterial.getWaccount_id()||"".equals(textMaterial.getWaccount_id()))
			textMaterial.setWaccount_id(TextMaterialServiceImp.param().getWaccountId());
			int total = textMaterialServiceImp.selectTextCount(textMaterial);
			List<TextMaterial> list = textMaterialServiceImp.selectTextMaterialList(textMaterial);
			
			mapJson.put("total", total);
			mapJson.put("rows", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			textMaterial=null;
		}
		
		//清理现场
		page = null;
		rows = null;
		textMaterial = null;
		return "dft";
	}
	
	
	

	public String queryBytitle() {

		try {
			if (page == null || rows == null) {
				page = "1";
				rows = "10";
			}
			if (textMaterial == null) {
				textMaterial = new TextMaterial();
			}
			textMaterial.setPage((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
			textMaterial.setRows(Integer.parseInt(rows));
			// textMaterial.setCatalogTitle("马航33");//测试用
			textMaterial.setWaccount_id(TextMaterialServiceImp.param().getWaccountId());
			int total = textMaterialServiceImp.selectTextCount(textMaterial);
			List<TextMaterial> list = textMaterialServiceImp.selectTextMaterialList(textMaterial);
			mapJson.put("total", total);
			mapJson.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//清理现场
		page = null;
		rows = null;
		textMaterial = null;
		return "queryBytitle";
	}

	
	public String queryBytitleed() {

		try {
			if (page == null || rows == null) {
				page = "1";
				rows = "10";
			}
			if (textMaterial == null) {
				textMaterial = new TextMaterial();
			}
			textMaterial.setPage((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
			textMaterial.setRows(Integer.parseInt(rows));
			// textMaterial.setCatalogTitle("马航33");//测试用
			//textMaterial.setWaccount_id(TextMaterialServiceImp.param().getWaccountId());
			int total = textMaterialServiceImp.selectTextCount(textMaterial);
			List<TextMaterial> list = textMaterialServiceImp.selectTextMaterialList(textMaterial);
			mapJson.put("total", total);
			mapJson.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//清理现场
		page = null;
		rows = null;
		textMaterial = null;
		return "queryBytitle";
	}
	public String querylist() {
		if (page == null || rows == null) {
			page = "1";
			rows = "10";
		}
		if (textMaterial == null) {
			textMaterial = new TextMaterial();
		}
		textMaterial.setStart((Integer.parseInt(page) - 1) * (Integer.parseInt(rows)));
		textMaterial.setEnd(Integer.parseInt(rows));
		try {
			textMaterial.setWaccount_id(GraphicMaterialServiceImp.param().getWaccountId());
			int count = textMaterialServiceImp.counttextMaterial(textMaterial);
			List<TextMaterial> list = textMaterialServiceImp.selecttextMaterial(textMaterial);
			for (TextMaterial textMaterial : list) {
				if (textMaterial != null) {
					String word0 = keyWordService.selectBothTypeKeyWordNameByMaterial(textMaterial.getId(), 0);
					String word1 = keyWordService.selectBothTypeKeyWordNameByMaterial(textMaterial.getId(), 1);
					textMaterial.setKeyword0(word0);
					textMaterial.setKeyword1(word1);
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
		textMaterial = null;
		return "dft";
	}

	/*
	 * 新增文本素材
	 */

	public String add() {
		if (text != null) {
			try {
				String waccountId = TextMaterialServiceImp.param().getWaccountId();
				text.setWaccount_id(waccountId);
				TextMaterial g = textMaterialServiceImp.selecttextByTitle(text);// 判断库里边是否有此标题(根据标题和当前公众号)
				if (g == null) {

					String[] word0 = textMaterialServiceImp.StrToArray(text.getKeyword0());
					if (word0.length > 0) {
						if (keyWordService.isExsitInWaccount(word0, waccountId)) {
							mapJson.put("mg", "mhexist");
							//清理现场
							text = null;
							return "dft";
						}
					}

					String[] word1 = textMaterialServiceImp.StrToArray(text.getKeyword1());
					if (word1.length > 0) {
						if (keyWordService.isExsitInWaccount(word1, waccountId)) {
							mapJson.put("mg", "jqexist");
							//清理现场
							text = null;
							return "dft";
						}
					}

					KeyGenerator keyGenerator = new KeyGenerator();
					String id = keyGenerator.getKey(text);
					text.setId(id);// 主键id
					String weixinxml = textMaterialServiceImp.spellXMLfront(text);
					text.setWenbenxml(weixinxml);// 插入前转为微信xml格式

					textMaterialServiceImp.insertText(text);// 为空执行插入操作

					if(text.getStatus() == 1) {
						
						HashMap<String, Object> map = new HashMap<String, Object>();
						if (word0.length > 0) {
							map.put(id, word0);
							keyWordService.batchAddKeyWord(map, Keyword.RULE_MOHU, Keyword.TYPE_WENBEN);// 批量插入绑定的关键字
						}
						if (word1.length > 0) {
							map.put(id, word1);
							keyWordService.batchAddKeyWord(map, Keyword.RULE_JINGQUE, Keyword.TYPE_WENBEN);// 批量插入绑定的关键字
						}
	
						textMaterialServiceImp.setInfoForMem(text.getId() + "", text.getWenbenxml());
						

					}
					
					log.setAct("添加");
					log.setType("文本素材");
					log.setValue(text.getId());
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
		//清理现场
		text = null;
		return "dft";
	}

	/*
	 * 修改文本素材跳转方法
	 */

	public String modify() {
		if (textMaterial != null) { // 标题，内容xml，状态
			try {

				text = textMaterialServiceImp.selecttextMaterialWithKeywordsById(textMaterial);

				String value = textMaterialServiceImp.parseXml(text.getWenbenxml());

				text.setWenbenxml(value);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "modify";
	}

	/*
	 * 修改文本素材
	 */
	public String update() {

		if (text != null) {

			try {
				TextMaterial grap = textMaterialServiceImp.selecttextMaterialById(text);// 根据前台的id查询素材
				// 原来def
				if (grap != null) { // 如果grap为空，那么标题已经修改了，如果grap不为空同时id相同
					
					if(textMaterialServiceImp.existInWaccount(text) > 0){
						mapJson.clear();
						mapJson.put("mg", "exist");
						//清理现场
						text = null;
						return "dft";
					}
					

					String[] word0 = textMaterialServiceImp.StrToArray(text.getKeyword0());
					if (word0.length > 0) {
						if (keyWordService.isExsitNotSelfInWaccount(word0, grap.getId(), grap.getWaccount_id())) {
							mapJson.put("mg", "mhexist");
							//清理现场
							text = null;
							return "dft";
						}
					}

					String[] word1 = textMaterialServiceImp.StrToArray(text.getKeyword1());
					if (word1.length > 0) {
						if (keyWordService.isExsitNotSelfInWaccount(word1, grap.getId(), grap.getWaccount_id())) {
							mapJson.put("mg", "jqexist");
							//清理现场
							text = null;
							return "dft";
						}
					}
					
					text.setModifydate(new java.sql.Timestamp(new Date().getTime()));
					text.setModifyuser(TextMaterialServiceImp.param().getAccount().getUsername());
					text.setModifyuserid(TextMaterialServiceImp.param().getAccount().getAccountId());

					String xml = textMaterialServiceImp.spellXMLfront(text);
					text.setWenbenxml(xml);

					textMaterialServiceImp.update(text);

					keyword = new Keyword();
					keyword.setMaterialId(text.getId());
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

					if (text.getStatus() == 1) { // 新状态为开启
						textMaterialServiceImp.setInfoForMem(text.getId(), xml);// 覆盖原来的xml

						// 再添加新的关键词
						HashMap<String, Object> map = new HashMap<String, Object>();
						if (word0.length > 0) {
							map.put(text.getId(), word0);
							keyWordService.batchAddKeyWord(map, Keyword.RULE_MOHU, Keyword.TYPE_WENBEN);// 批量插入绑定的关键字
						}
						if (word1.length > 0) {
							map.put(text.getId(), word1);
							keyWordService.batchAddKeyWord(map, Keyword.RULE_JINGQUE, Keyword.TYPE_WENBEN);// 批量插入绑定的关键字
						}

					} else if (text.getStatus() == 0) { // 新状态为关闭
						textMaterialServiceImp.deleteInfoForMem(text.getId());// 根据id删除xml
					}
					log.setAct("更新");
					log.setType("文本素材");
					log.setValue(text.getId());
					loggerService.saveLog(log);
					mapJson.clear();
					mapJson.put("mg", "success");

				} else {
					mapJson.clear();
					mapJson.put("mg", "noexist");
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
		text = null;
		return "dft";
	}

	/*
	 * 删除文本素材
	 */

	public String delete() {

		try {

			if (text != null) {

				textMaterialServiceImp.deleteInfoForMem(text.getId() + ""); // 根据id删除相关xml（id,xml）

				Keyword keyword = new Keyword();
				keyword.setMaterialId(text.getId() + "");
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

				text.setStatus(-1);
				textMaterialServiceImp.updatedeault(text);// 根据id和状态值做更新操作

				text = new TextMaterial();
			}
			log.setAct("删除");// 添加日志操作
			log.setType("文本素材");
			log.setValue(text.getId());
			loggerService.saveLog(log);
			
			mapJson.clear();
			mapJson.put("mg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			mapJson.clear();
			mapJson.put("mg", "fail");
		}
		//清理现场
		text = null;
		return "dft";
	}

	/*
	 * 文本素材状态切换
	 */
	
	public String switching() {
		if (text != null) {
			try {
				text.setModifydate(new java.sql.Timestamp(new Date().getTime()));
				text.setModifyuser(TextMaterialServiceImp.param().getAccount().getUsername());
				text.setModifyuserid(TextMaterialServiceImp.param().getAccount().getAccountId());

				textMaterialServiceImp.update(text);
					if (text.getStatus() == 1) { // 新状态为开启
						TextMaterial gp= textMaterialServiceImp.selecttextMaterialById(text);//查询图文xml
						textMaterialServiceImp.setInfoForMem(text.getId(), gp.getWenbenxml());//添加缓存
					} else if (text.getStatus() == 0) {
						textMaterialServiceImp.deleteInfoForMem(text.getId());
						keyword = new Keyword();
						keyword.setMaterialId(text.getId());
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
					}

					log.setAct("修改状态");
					log.setType("文本素材");
					log.setValue(text.getId());
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
		text = null;
		return "dft";
	}
	
	/*
	 * 根据查询条件查询文本素材
	 */

	public String queryall() throws Exception {
		if (page == null || rows == null) {
			page = "1";
			rows = "10";
		}
		if (textMaterial == null) {
			textMaterial = new TextMaterial();
		}

		if (textMaterial.getWaccount_id().equals("100")) {

			System.out.println(textMaterial.getWaccount_id());
			textMaterial.setWaccount_id(null);
			System.out.println(textMaterial.getWaccount_id());

		}

		textMaterial.setStart((Integer.parseInt(page) - 1) * (Integer.parseInt(rows)));
		textMaterial.setEnd(Integer.parseInt(rows));
		try {
			int count = textMaterialServiceImp.countquerytextMaterial(textMaterial);
			List<TextMaterial> list = textMaterialServiceImp.querytextMaterial(textMaterial);

			mapJson.put("total", count);
			mapJson.put("rows", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//清理现场
		page = null;
		rows = null;
		textMaterial = null;
		return "dft";
	}

	
	
	

}
