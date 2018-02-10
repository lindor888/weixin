package com.ctvit.util;

/**
 * 加载本地微信信息相关配置文件
 * @author 
 *
 */

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ctvit.bean.Articles;
import com.ctvit.bean.NewsOutMessage;
import com.ctvit.bean.OutMessage;
import com.ctvit.dao.ReservationDao;
import com.danga.MemCached.MemCachedClient;
import com.thoughtworks.xstream.XStream;

public class LoadedText {
	private static final Logger logger = Logger.getLogger(LoadedText.class);

	/**
	 * 适配回复内容
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getOutMessage(String str, String id) {

		if (str != null && !"".equals(str) && str.indexOf("#") == 0) {
			logger.info("[liuyan:]" + str);
			return huiFuMessage();
		}

		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();

		// 是否全部走应急策略
		// 如果是showErro
		String flagError = (String) memCachedClientInstance.get(id + "_qiangzhifanhui");
		if (flagError != null && !"".equals(flagError) && "true".equals(flagError)) {
			return showErro();
		}
		// 返回
		String md5String = EncryptUtils.toMessageDigest(id + "_" + str + "");
		String md5ReValue = (String) memCachedClientInstance.get(md5String);

		if (md5ReValue != null && !"".equals(md5ReValue)) {
			System.out.println("md5ReValue" + md5ReValue);
			if (md5ReValue.contains("MsgType")) {
				return md5ReValue;
			} else {
				ReservationDao reservationDao = new ReservationDao();
				String omg = reservationDao.selectomg(md5ReValue);
				return omg;
				// return (String) memCachedClientInstance.get(md5ReValue);
			}
		}

		try {
			Map<String, String> listTuWen = (Map<String, String>) memCachedClientInstance.get(id + "_mohu");
			String tuWenKey = ifHavaFirstInput(listTuWen, str);
			if (tuWenKey != null) {
				ReservationDao reservationDao = new ReservationDao();
				String omg = reservationDao.selectomg(tuWenKey);
				return omg;
				// return (String)memCachedClientInstance.get(tuWenKey);
			}
		} catch (Exception e) {
			logger.error("[getlistTuWenError:]" + e.getLocalizedMessage());
		}
		return "";

		// try {
		// Map<String, String> listWenBen = (Map<String, String>) memCachedClientInstance
		// .get("wenbenlist");
		// String wenBenKey = ifHavaFirstInput(listWenBen, str);
		//
		// // TODO
		// if (wenBenKey != null) {
		// return (String) memCachedClientInstance.get(wenBenKey);
		// }
		//
		// } catch (Exception e) {
		// logger.error("[getlistWenBenError:]" + e.getLocalizedMessage());
		// }

		// 走大网检索
		/*
		 * try { String omg = getSoMessage(str,id); String timeLong = ConfKit.get("cacheDate"); memCachedClientInstance.set(md5String, omg, new Date(Long.parseLong(timeLong))); return omg; } catch (Exception e) { logger.error("[getOutMessageError:]" + e.getLocalizedMessage()); e.printStackTrace(); return showErro(); }
		 */
	}

	/**
	 * 获得公众号的appId
	 * 
	 * @param id
	 * @return
	 */
	public String getAppId(String id) {
		try {
			MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
			return (String) memCachedClientInstance.get(id + "_appid");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获得公众号的secret
	 * 
	 * @param id
	 * @return
	 */
	public String getSecret(String id) {
		try {
			MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
			return (String) memCachedClientInstance.get(id + "_secret");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 适配回复内容
	 * 
	 * 
	 */
	public String getClickOutMessage(String str, String id) {

		if (str != null && !"".equals(str) && str.indexOf("#") == 0) {
			logger.info("[liuyan:]" + str);
			return huiFuMessage();
		}
		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		// 返回
		String md5ReValue = (String) memCachedClientInstance.get(str);
		if (md5ReValue != null && !"".equals(md5ReValue)) {
			return md5ReValue;
		} else {
			return showNoMessage(id);
		}

	}

	/**
	 * 错误返回
	 * 
	 * @return
	 */
	public static String showErro() {

		return ConfKit.get("errormessage");
	}

	/**
	 * 无消息返回
	 * 
	 * @return
	 */
	public static String showNoMessage(String id) {
		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		return (String) memCachedClientInstance.get((String) memCachedClientInstance.get(id + "_wufanhuijieguotishiyu"));
		// return ConfKit.get("nomessage");
	}

	/**
	 * 欢迎语
	 * 
	 * @return
	 */
	public static String showwelcome(String id) {
		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		// TODO 获取缓存内信息
		return (String) memCachedClientInstance.get((String) memCachedClientInstance.get(id + "_huanyingyu"));
		// return ConfKit.get("welcome");
	}

	/**
	 * 强制返回
	 * 
	 * @return
	 */
	public static String huiFuMessage() {
		return ConfKit.get("huifu");
	}

	/**
	 * 获取检索信息
	 * 
	 * @param request
	 * @return
	 */
	private static String getSoMessage(String request, String id) {
		String omg = "";
		if (request == null || "".equals(request)) {
			return omg;
		}
		HashMap<String, String> map = new HashMap<String, String>();
		List<Articles> list = new ArrayList<Articles>();
		OutMessage oms = new NewsOutMessage();
		oms.setToUserName("%1$s");
		oms.setFromUserName("%2$s");
		oms.setCreateTime(new Date().getTime());

		try {
			map.put("qtext", URLEncoder.encode(request, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// TODO 此处是否是否需要增加配置

		map.put("page", "1");
		map.put("highlight", "0");
		map.put("sort", "relevance");
		map.put("pagesize", "4");
		String result = GetHttpUtil.get("http://so.cntv.cn/service/mobile_news_web_photo.php", map);
		JSONObject obj = JSONObject.fromObject(result);
		if (obj.get("flag").toString().equals("ok")) {
			String suggest = obj.get("items").toString();
			JSONArray array = JSONArray.fromObject(suggest);

			if (array.size() > 0) {
				for (int i = 0; i < array.size(); i++) {
					// TODO 此处转换等待需求细化后修改逻辑
					Articles arti = new Articles();
					if (i == 0) {
						arti.setDescription(array.getJSONObject(i).getString("WDES1").equals("") ? array.getJSONObject(i).getString("brief") : array.getJSONObject(i).getString("WDES1"));
						arti.setPicUrl(array.getJSONObject(i).getString("WIMAGE").equals("") ? array.getJSONObject(i).getString("itemImage").replace("{\"imgUrl1\":\"", "").replace("\"}", "") : array.getJSONObject(i).getString("WIMAGE"));
						arti.setTitle(array.getJSONObject(i).getString("WSTITLE").equals("") ? array.getJSONObject(i).getString("itemTitle") : array.getJSONObject(i).getString("WSTITLE"));
						arti.setUrl(ConfKit.get("weburl") + array.getJSONObject(i).getString("itemID"));
					} else {
						arti.setDescription(array.getJSONObject(i).getString("brief"));
						arti.setPicUrl(array.getJSONObject(i).getString("WIMAGE").equals("") ? array.getJSONObject(i).getString("itemImage").replace("{\"imgUrl1\":\"", "").replace("\"}", "") : array.getJSONObject(i).getString("WIMAGE"));
						arti.setTitle(array.getJSONObject(i).getString("WTITILE").equals("") ? array.getJSONObject(i).getString("itemTitle") : array.getJSONObject(i).getString("WTITILE"));
						arti.setUrl(ConfKit.get("weburl") + array.getJSONObject(i).getString("itemID"));
					}
					list.add(arti);
				}
			}
		}
		// DOTO 不足4条怎么补充
		if (list.size() < 1) {
			return showNoMessage(id);
		}
		// if(list.size()<4){
		// list =addArticleNum(list);
		// }
		((NewsOutMessage) oms).setArticles(list);
		XStream xs = XStreamFactory.init(false);

		xs = XStreamFactory.init(true);
		xs.alias("xml", oms.getClass());
		xs.alias("item", Articles.class);
		omg = xs.toXML(oms);

		return omg;
	}

	@SuppressWarnings("unchecked")
	private static List<Articles> addArticleNum(List<Articles> list) {
		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		List<Map<String, String>> memArticle = (List<Map<String, String>>) memCachedClientInstance.get("morenshuju");
		if (memArticle == null || memArticle.isEmpty()) {
			System.out.println("memCachedClientInstance+morenshuju" + null);
			memArticle = getRaMessage();
			if (memArticle != null && !memArticle.isEmpty() && memArticle.size() > 2) {
				String timeLong = ConfKit.get("radCacheDate");
				memCachedClientInstance.set("morenshuju", memArticle, new Date(Long.parseLong(timeLong)));
			} else {
				return list;
			}
		}
		int y = 0;
		for (int i = list.size(); i < 4; i++) {
			Articles arti = new Articles();
			arti.setDescription(memArticle.get(y).get("dec"));
			arti.setPicUrl(memArticle.get(y).get("pic"));
			arti.setTitle(memArticle.get(y).get("title"));
			arti.setUrl(memArticle.get(y).get("url"));
			y++;
			list.add(arti);
		}
		return list;
	}

	private static List<Map<String, String>> getRaMessage() {
		List<Map<String, String>> memArticle = new ArrayList<Map<String, String>>();
		String result = GetHttpUtil.getNoInput("http://hot.news.cntv.cn/index.php?controller=content&action=getHotInfo");
		JSONObject obj = JSONObject.fromObject(result);
		String suggest = obj.get("itemList").toString();
		JSONArray array = JSONArray.fromObject(suggest);
		if (array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				// TODO 此处转换等待需求细化后修改逻辑
				Map<String, String> map = new HashMap<String, String>();
				map.put("dec", array.getJSONObject(i).getString("tagDesc"));
				map.put("pic", array.getJSONObject(i).getString("itemImage").replace("{\"imgUrl1\":\"", "").replaceAll("\"\\S*\"\"}", ""));
				map.put("title", array.getJSONObject(i).getString("itemTitle"));
				map.put("url", ConfKit.get("weburl") + array.getJSONObject(i).getString("itemID"));

				memArticle.add(map);
			}
		}

		return memArticle;
	}

	/**
	 * 前匹配
	 * 
	 * @param listTuWen
	 * @param inputString
	 * @return
	 */
	private static String ifHavaFirstInput(Map<String, String> map, String inputString) {
		String value = null;
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			logger.info("entry.getKey()=" + entry.getKey());
			if (inputString != null && !"".equals(inputString) && inputString.indexOf(entry.getKey()) == 0) {

				value = entry.getValue();
				return value;
			}
		}
		return value;
	}

	/**
	 * 全匹配
	 * 
	 * @param listTuWen
	 * @param inputString
	 * @return
	 */
	private static String ifHavaInput(Map<String, String> map, String inputString) {

		String value = null;
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			logger.info("entry.getKey()=" + entry.getKey());
			if (inputString != null && !"".equals(inputString) && inputString.contains(entry.getKey())) {

				value = entry.getValue();
				return value;
			}
		}
		return value;
	}

	public static void main(String[] args) {

		// System.out.println(getSoMessage("中国"));
		String str = "#4564564#564564";
		if (str.indexOf("#") == 0) {
			System.out.println("====");
		}
	}
}
