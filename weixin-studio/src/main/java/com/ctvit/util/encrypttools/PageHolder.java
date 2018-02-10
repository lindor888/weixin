package com.ctvit.util.encrypttools;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;


/**
 * 页面帮助类，主要提供了各种页面路径的获取、系统参数的获取、路径的校验、域名的获取、包含页的解析
 * @author liyun
 *
 */
public class PageHolder {
	private static Log log = LogFactory.getLog(PageHolder.class);
	private static String[] nums = {"〇","一","二","三","四","五","六","七","八","九","十"};

	/**
	 * 获取二级页面weburl
	 * @param domain
	 * @param pagePath
	 * @return
	 */
//	public static String getPagePathByType(String domain ,String pagePath) {
//		if(domain == null || pagePath == null) {
//			return null;
//		}
//		String rootPath = domain + pagePath + "/" + Categories.FILE_NAME.DEF_HTML;
//		
//		return rootPath;
//	}
//	public static String getLocalPagePathByType(String pagePath ,String elementId) {
//		if(pagePath == null || pagePath == null) {
//			return null;
//		}
//		String rootPath = pagePath + "/" + elementId + Categories.FILE_NAME.EXT_HTML;
//		return rootPath.replaceAll("([^:])/{2,}", "$1/");
//	}
//	/**
//	 * 获取三级页的总页数
//	 * @return
//	 */
//	public static int getTotalPage(String contentId) {
//		WebApplicationContext wac = ContextLoaderListener.getCurrentWebApplicationContext();
//		if (contentId.startsWith("VIDE")) {
//			VideoManageService service = (VideoManageService)wac.getBean("videoManageService");
//			return service.getTotalPage(contentId);
//		} else {
//			ArticleService service = (ArticleService)wac.getBean("ArticleService");
//			return service.getTotalPage(contentId);
//		}
//	}
//	
	/**
	 * 根据三级页的主键获取三级页的路径
	 * @param pageId：页面主键
	 * @return
	 */
//	public static String getContentPagePath(String pageId) {
//		String retPath = null;
//		if (pageId != null) {
//			String temp = pageId;
//			temp = temp.substring(4,temp.length()-3);
//			Date date = new Date(Long.parseLong(temp));
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//			String now = sdf.format(date);
//			retPath = File.separator + now.substring(0,4) + File.separator;
//			retPath += now.substring(4, 6) + File.separator;
//			retPath += now.substring(6, 8) + File.separator;
//			retPath += pageId + Categories.FILE_NAME.EXT_HTML;
//		}
//		return retPath;
//	}
	/**
	 * 根据三级页的主键获取完整的带域名的三级页的路径
	 * @param domain：域名
	 * @param pageId：页面主键
	 * @return
	 */
//	public static String getContentPageUrl(String domain,String pageId) {
//		if (isLocal()) {
//			return FileHelper.replaceSeparatorForLinux(domain + getContentPagePath(pageId));//windows下用
//		}
//		
//		return domain + getContentPagePath(pageId);
//	}
	/**
	 * 获取三级页当前预览页的页面链接
	 * @param contentId：正文页主键
	 * @param currentPage：当前页号
	 * @return
	 */
	public static String getCurrentContentPagePreviewUrl(String contentId, String currentPage) {
		String contextPath = ContextLoaderListener.getCurrentWebApplicationContext().getServletContext().getContextPath();
		if (contentId.startsWith("VIDE")) {
			return contextPath + "/content/showManageVideo?tabVideo.videoId="+contentId+ "&currentPage=" + currentPage;
		} else {
			return contextPath + "/content/showArticle?tabArticle.articleId=" + contentId + "&currentPage=" + currentPage;
		}
	}
	/**
	 *  获取三级页当前发布页的页面链接
	 * @param url：页面外网链接
	 * @param currentPage：当前页号
	 * @return
	 */
//	public static String getCurrentContentPagePath(String url,String currentPage) {
//		if (!currentPage.equals("1")) {
//			return url.replace(Categories.FILE_NAME.EXT_HTML, "") + "_" + currentPage + Categories.FILE_NAME.EXT_HTML;
//		}
//		return url;
//	}
//	/**
//	 * 根据正文页id，获取其所在台的id
//	 * @param contentId
//	 * @return
//	 */
//	public static Map getChannelIdByContentId(String contentId) {
//		Map map = new HashMap();
//		String channelId = "";
//		WebApplicationContext wac = ContextLoaderListener.getCurrentWebApplicationContext();//获取应用上下文
//		PageManager pageManager = (PageManager)wac.getBean("pageManager");
//		TopicBiz topicBiz = (TopicBiz)wac.getBean("TopicBiz");
//		String primaryPageId = "";
//		if (contentId.startsWith("PHOA")) {
//			primaryPageId = pageManager.getPrimaryId(contentId, ComposeContext.PAGE.TEMPLET_PHOTO_ALBUM);
//		} else if (contentId.startsWith("VIDE")) {
//			primaryPageId = pageManager.getPrimaryId(contentId, ComposeContext.PAGE.TEMPLET_VIDEO);
//		} else if (contentId.startsWith("VIDA")) {
//			primaryPageId = pageManager.getPrimaryId(contentId, ComposeContext.PAGE.TEMPLET_VIDEO_ALBUM);
//		} else {
//			primaryPageId = pageManager.getPrimaryId(contentId, ComposeContext.PAGE.TEMPLET_ARTICLE);
//		}
//		List list = topicBiz.TreeDataList(primaryPageId, 3);//获得页面树
//		if(list != null && list.size() > 0) {
//			Topic topic = (Topic)list.get(0);//专题
//			map.put("channelId", topic.getChannelID());
//			map.put("channelName", topic.getChannel().getName());
//		}
//		return map;
//	}
//	/** 
//	 * 获取包含页的url
//	 * @param id:channelId/topicId/pageId
//	 * @param level：0全网；1:台；2：专题、栏目；4:页面
//	 * @param layout:1右侧；2底部
//	 * @param position：0顶部；1：中间；2：底部
//	 * @return
//	 */
//	public static String getIncludeUrlByCondition(String id,String level,String layout,String position) {
//		String retUrl = null;
//		String channelPath = null;
//		String topicPath = null;
//		String pagePath = null;
//		
//		if (level.equals(Categories.CONTENT_AD_LEVEL.ALL)) {//全网
//			retUrl = getIncludePagePath(level, layout, position, channelPath, topicPath, pagePath);
//		} else if (level.equals(Categories.CONTENT_AD_LEVEL.CHANNEL)) {//频道
//			WebApplicationContext wac = ContextLoaderListener.getCurrentWebApplicationContext();//获取应用上下文
//			ChannelMapper mapper = (ChannelMapper)wac.getBean("ChannelMapper");
//			Channel channel = mapper.selectChannelByID(id);
//			if (channel != null) {
//				channelPath = channel.getUrl();
//			}
//
//			retUrl = getIncludePagePath(level, layout, position, channelPath, topicPath, pagePath);
//		} else if (level.equals(Categories.CONTENT_AD_LEVEL.TOPIC)) {//专题
//			WebApplicationContext wac = ContextLoaderListener.getCurrentWebApplicationContext();//获取应用上下文
//			TopicDao topicDao = (TopicDao)wac.getBean("TopicDao");
//			ChannelMapper channelMapper = (ChannelMapper)wac.getBean("ChannelMapper");
//			Topic topic = topicDao.selectTopicByID(id);
//			if (topic != null) {
//				topicPath = topic.getUrl();
//			}
//
//			String channelId = topic.getChannelID();
//			Channel channel = channelMapper.selectChannelByID(channelId);
//			if (channel != null) {
//				channelPath = channel.getUrl();
//			}
//
//			retUrl = getIncludePagePath(level, layout, position, channelPath, topicPath, pagePath);
//		} else {//页面
//			WebApplicationContext wac = ContextLoaderListener.getCurrentWebApplicationContext();//获取应用上下文
//			TopicBiz topicBiz = (TopicBiz)wac.getBean("TopicBiz");
//			PageDao pageDao = (PageDao) wac.getBean("PageDao");
//			
//			
//			com.ctvit.icms.standard.website.site.entity.Page page = pageDao.selectPageById(id);
//			if (page != null) {
//				pagePath = page.getUrl();
//			}
//			
//			List<Topic> list = topicBiz.TreeDataList(id, 3);
//			if (list != null && list.size() > 0) {
//				Topic topic = list.get(0);
//				if (topic != null) {
//					topicPath = topic.getUrl();
//				}
//				Channel channel = topic.getChannel();
//				if (channel != null) {
//					channelPath = channel.getUrl();
//				}
//			
//			}
//			
//			
//			retUrl = getIncludePagePath(level, layout, position, channelPath, topicPath, pagePath);
//			
//		}
//		return retUrl;
//	}
//	/**
//	 * 获取包含页的路径
//	 * @param level
//	 * @param layout
//	 * @param position
//	 * @param channelPath
//	 * @param topicPath
////	 * @param pagePath
////	 * @return
//	 */
//	public static String getIncludePagePath(String level,String layout,String position,String channelPath,String topicPath,String pagePath) {
//		String retPath = null;
//		String filename = Categories.FILE_NAME.DEF_TOP;//文件名
//		if(layout.equals(Categories.CONTENT_AD_LAYOUT.RIGHT)) {
//			layout = Categories.FILE_DIR.LAYOUT_RIGHT;
//		} else {
//			layout = Categories.FILE_DIR.LAYOUT_LOWER;
//		}
//		//文件名
//		if (position.equals(Categories.CONTENT_AD_POSITION.MIDDLE)) {
//			filename = Categories.FILE_NAME.DEF_MIDDLE;
//		} else if (position.equals(Categories.CONTENT_AD_POSITION.BOTTOM)){
//			filename = Categories.FILE_NAME.DEF_BOTTOM;
//		}
//		
//		channelPath = FileHelper.checkPath(channelPath);
//		topicPath = FileHelper.checkPath(topicPath);
//		pagePath = FileHelper.checkPath(pagePath);
//		
//		retPath = File.separator + Categories.FILE_DIR.INCLUDE + File.separator + layout + channelPath + topicPath + pagePath + File.separator + filename;
//		if (isLocal()) {
//			retPath = FileHelper.replaceSeparatorForLinux(retPath);//windows下用
//		}
//		
//		return retPath;
//	}
//	
	/**
	 * 获取包含页的url
	 * @param id:channelId/topicId/pageId
	 * @param layout:1右侧；2底部
	 * @param position：0顶部；1：中间；2：底部
	 * @return
	 */
//	public static String getIncludeUrlByCondition(String id,String layout,String position) {
//		if (id != null) {
//			if (id.equals("")) {
//				return getIncludeUrlByCondition(id, Categories.CONTENT_AD_LEVEL.ALL, layout, position);
//			}
//			if (id.toUpperCase().startsWith(Categories.ID_TYPE.CHAN)) {
//				return getIncludeUrlByCondition(id, Categories.CONTENT_AD_LEVEL.CHANNEL, layout, position);
//			}
//			if (id.toUpperCase().startsWith(Categories.ID_TYPE.TOPC)) {
//				return getIncludeUrlByCondition(id, Categories.CONTENT_AD_LEVEL.TOPIC, layout, position);
//			}
//			if (id.toUpperCase().startsWith(Categories.ID_TYPE.PAGE)) {
//				return getIncludeUrlByCondition(id, Categories.CONTENT_AD_LEVEL.PAGE, layout, position);
//			}
//		}
//		return null;
//	}
//	/**
//	 * 根据路径获取页面的内容
//	 * @param path
//	 * @param domainPath
//	 * @return
//	 */
//	public static String getContentByUrl(String path ,String domainPath) {
//		String file = PageHolder.getUploadRootPath() + PageHolder.getPrefixPath(Categories.PATH_TYPE.HTML) + domainPath + path;
//		return FileHelper.readHTMLFromFile(file);
//	}
//
//	/**
//	 * 解析包含页
//	 * @param content：页面内容
//	 * @return
//	 */
//	public static String parseIncludePage(String content) {
//		try {
//			content = content.replace("<!--#include virtual=\"/include/bottompage/head.shtml\" -->", FileHelper.readHTMLFromFile(PageHolder.getUploadRootPath() + PageHolder.getPrefixPath(Categories.PATH_TYPE.HTML) + "/include/bottompage/head.shtml"));
//			content = content.replace("$", "\\$");
//			StringBuffer resultHTML = new StringBuffer();
//			String regex = "(<!--#include\\s+?virtual=[\",\'](.+?)[\",\']\\s*?-->)";
//			Pattern p = Pattern.compile(regex,Pattern.MULTILINE);
//			Matcher m = p.matcher(content);
//			while(m.find()) {
//				try {
//					String temp = FileHelper.readHTMLFromFile(PageHolder.getUploadRootPath() + PageHolder.getPrefixPath(Categories.PATH_TYPE.HTML) + m.group(2));
//					Matcher m1 = p.matcher(temp);
//					if(m1.find()) {
//						temp = parseIncludePage(temp);
//					}
//					temp = temp.replace("$", "\\$");
//					m.appendReplacement(resultHTML, temp);
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//			}
//			m.appendTail(resultHTML);
//			content = resultHTML.toString();
//			content = content.replace("\\$", "$");
//			return content;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("parase include page error!--:"+e.getMessage());
//			return content;
//		}
//		
//	}

	/**
	 * 获取上传文件的根目录
	 * @return
	 */
	public static String getUploadRootPath() {
		if (isLocal()) {
			return getSysParm("root_path_local"); 
		} else {
			return getSysParm("root_path_server");
		}
	}
	/**
	 * 获取发布文件临时路径的根目录
	 * @return
	 */
	public static String getIssueTempRootPath() {
		if (PageHolder.isLocal()) {
			return PageHolder.getSysParm("root_path_issue_local");
		} else {
			return PageHolder.getSysParm("root_path_issue_server");
		}
	}
	/**
	 * 获取发布文件路径的根目录
	 * @return
	 */
	public static String getIssueRootPath() {
		if (PageHolder.isLocal()) {
			return PageHolder.getSysParm("root_path_issue_temp_local");
		} else {
			return PageHolder.getSysParm("root_path_issue_temp_server");
		}
	}
	/**
	 * 获取存放路径的前缀
	 * @param type
	 * @return
	 */
//	public static String getPrefixPath(String type) {
//		if (type.equals(Categories.PATH_TYPE.HTML)) {
//			if (isLocal()) {
//				return getSysParm("path_html_local");
//			} else {
//				return getSysParm("path_html_server");
//			}
//			
//		}
//		if (type.equals(Categories.PATH_TYPE.IMG_PAGE)) {
//			if (isLocal()) {
//				return getSysParm("path_image_page_local");
//			} else {
//				return getSysParm("path_image_page_server");
//			}
//		}
//		if (type.equals(Categories.PATH_TYPE.IMG_STYLE)) {
//			if (isLocal()) {
//				return getSysParm("path_image_style_local");
//			} else {
//				return getSysParm("path_image_style_server");
//			}
//		}
//		if (type.equals(Categories.PATH_TYPE.IMG_TMPLET)) {
//			if (isLocal()) {
//				return getSysParm("path_image_templet_local");
//			} else {
//				return getSysParm("path_image_templet_server");
//			}
//		}
//		if (type.equals(Categories.PATH_TYPE.IMG_PHOTO)) {
//			if (isLocal()) {
//				return getSysParm("path_image_photo_local");
//			} else {
//				return getSysParm("path_image_photo_server");
//			}
//		}
//		if (type.equals(Categories.PATH_TYPE.FLASH)) {
//			if (isLocal()) {
//				return getSysParm("path_flash_local");
//			} else {
//				return getSysParm("path_flash_server");
//			}
//		}
//		return "";
//	}
	/**
	 * 根据操作系统类型替换文件分隔符
	 * @param path：文件路径
	 * @return
	 */
//	public static String replaceSeparatorByOs(String path) {
//		if (isLocal()) {
//			return FileHelper.replaceSeparatorForWindow(path);
//		} else {
//			return FileHelper.replaceSeparatorForLinux(path);
//		}
//	}

	/**
	 * 获取配置文件的属性值
	 * @param name：参数名
	 * @return
	 */
	public static String getSysParm(String name) {
		return SysConfig.getSysParam(name);
//		ResourceBundle rb = ResourceBundle.getBundle("config/sysrnconfig") ;
//		try {
//			return rb.getString(name);
//		} catch(Exception e) {
//			log.info(name + ": is not found in sysconfig!");
//			return null;
//		}
	}

	/**
	 * 判断系统类型
	 * @return：true为windows系统
	 */
	public static boolean isLocal() {
		if (System.getProperty("os.name").contains("Windows")) {
			return true;
		}
		log.info("os is : " + System.getProperty("os.name"));
		return false;
	}
	/**
	 * 根据类型获取域名
	 * @param type：由DOMAIN_TYPE类获取
	 * @return: (eg:/var/www/html/temp/img)
	 */
//	public static String getDomain(String type) { 
//		if (type != null) {
//			if (type.equals(Categories.DOMAIN_TYPE.CSS)) {
//				return getSysParm("domain_css");
//			} else if (type.equals(Categories.DOMAIN_TYPE.JS)){
//				return getSysParm("domain_js");
//			} else if (type.equals(Categories.DOMAIN_TYPE.IMG)) {
//				long radom = Math.round(1000*Math.random());
//				radom = radom%5;
//				return getSysParm("domain_imge_"+radom);
//			} else if (type.equals(Categories.DOMAIN_TYPE.VIDEO)) {
//				return getSysParm("domain_video");
//			}  else {
//				return getSysParm("domain_default");
//			}
//		}
//		return getSysParm("domain_default");
//	}
//	/**
//	 * 根据域名返回域名所对应的路径
//	 * @param name
//	 * @return
//	 */
//	public static String getDomainPath(String name) {
//		WebApplicationContext wac = ContextLoaderListener.getCurrentWebApplicationContext();//获取应用上下文
//		DomainMapper mapper = (DomainMapper)wac.getBean("domainMapper");
//		List<Domain> list = mapper.selectDomainByUrl(name);
//		if (list != null && list.size() > 0) {
//			Domain domain = (Domain)mapper.selectDomainByUrl(name).get(0);
//			return domain.getFilePath();
//		}
//		
//		return "";
//	}
//	/**
//	 * 根据weburl查询域名path
//	 * @param weburl
//	 * @return
//	 */
//	public static String getDomainPathByWeburl(String weburl) {
//		WebApplicationContext wac = ContextLoaderListener.getCurrentWebApplicationContext();//获取应用上下文
//		DomainMapper mapper = (DomainMapper)wac.getBean("domainMapper");
//		String name = weburl.replace("http://", "");
//		name = name.substring(0, name.indexOf("/"));
//		name = "http://"+name;
//		List<Domain> list = mapper.selectDomainByUrl(name);
//		if (list != null && list.size() > 0) {
//			Domain domain = (Domain)mapper.selectDomainByUrl(name).get(0);
//			return domain.getFilePath();
//		}
//		
//		return "";
//	}
//	/**
//	 * 根据pageId获取域名
//	 * @param pageId
//	 * @return
//	 */
//	public static String getDomainById(String pageId) {
//		String domainId = "";
//		Domain domain = null;
//		WebApplicationContext wac = ContextLoaderListener.getCurrentWebApplicationContext();//获取应用上下文
//		TopicBiz topicBiz = (TopicBiz)wac.getBean("TopicBiz");
//		List list = topicBiz.TreeDataList(pageId, 3);//获得页面树
//		if(list != null && list.size() > 0) {
//			Topic topic = (Topic)list.get(0);//专题
//			domainId = topic.getDomainID();
//			if (domainId != null && !domainId.equals("")) {
//				DomainMapper mapper = (DomainMapper)wac.getBean("domainMapper");
//				domain = (Domain)mapper.selectAllDomainByID(domainId).get(0);
//			}
//			Channel channel = topic.getChannel();
//			if (channel != null) {//频道
//				if (domainId == null || domainId.equals("")) {
//					domainId = channel.getWebSiteID();
//					DomainMapper mapper = (DomainMapper)wac.getBean("domainMapper");
//					domain = (Domain)mapper.selectAllDomainByID(domainId).get(0);
//				}
//			}
//		}
//		return domain.getUrl();
//	}
//	/**
//	 *  替换页面中的图片链接，将内外路径替换成外网路径
//	 * @param content
//	 * @return
//	 */
//	public static String replaceHtmlImageDomain(String content) {
//		try {
//			StringBuffer sb = new StringBuffer();
//			String regex = "((<[iI][mM][gG]\\s+?.*?\\s+?src\\s*?=\\s*?([\'\"]))(.*?)(\\3[^>]*?>))";
//			Pattern p = Pattern.compile(regex,Pattern.MULTILINE);
//			Matcher m = p.matcher(content);
//			while(m.find()) {
//				String url = m.group(4);
//				if (!url.startsWith("http://")) {
//					url = url.startsWith(File.separator) ? url : File.separator + url;
//					url = getDomain(Categories.DOMAIN_TYPE.IMG) + url;
//				}
//				m.appendReplacement(sb, m.group(2) + url + m.group(5));
//			}
//			m.appendTail(sb);
//			log.info("replace html img domain successful!");
//			return sb.toString();
//		} catch (Exception e) {
//			log.info("replace html img domain failed!");
//			return "";
//		}
//	}
//	/**
//	 * 替换样式中的图片链接，将内外路径替换成外网路径
//	 * @param content
//	 * @return
//	 */
//	public static String replaceStyleImageDomain(String content) {
//		try {
//			StringBuffer sb = new StringBuffer();
//			String regex = "((url\\(\"?)(.+?\\..+?)(\"?\\)))";
//			Pattern p = Pattern.compile(regex,Pattern.MULTILINE);
//			Matcher m = p.matcher(content);
//			while(m.find()) {
//				String url = m.group(3);
//				if (!url.startsWith("http://")) {
//					url = url.startsWith(File.separator) ? url : File.separator + url;
//					url = getDomain(Categories.DOMAIN_TYPE.IMG) + url;
//				}
//				m.appendReplacement(sb, m.group(2)+url+m.group(4));
//			}
//			m.appendTail(sb);
//			return sb.toString();
//		} catch (Exception e) {
//			System.out.println("replace style img domain failed!");
//			return "";
//		}
//	}
//	/**
//	 * 将阿拉伯数字转换成大写字母
//	 * @param num
//	 * @return
//	 */
//	public static String changNum(String num) {
//		int key = Integer.valueOf(num);
//		return nums[key-1];
//	}
//	
//	public static String arrayToString(String[] urls) {
//		StringBuffer sb = new StringBuffer();
//		if (urls != null) {
//			for (String url : urls) {
//				sb.append(url).append(",");
//			}
//		}
//		String temp = sb.toString();
//		if (temp.endsWith(",")){
//			temp = temp.substring(1,temp.lastIndexOf(","));
//		}
//		
//		return temp;
//	}
//	
//	public static String[] stringToArray(String urls) {
//		return urls.split(",");
//	}
//	
//	public static String getDatePagePath(String filename) {
//		String suffix = filename.substring(filename.lastIndexOf("."));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String now = sdf.format(new Date());
//		return filename.replace(suffix, "") + "/" + now + suffix;
//	}
}
