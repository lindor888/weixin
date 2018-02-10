package com.ctvit.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 页面帮助类，主要提供了各种页面路径的获取、系统参数的获取、路径的校验、域名的获取、包含页的解析
 * @author liyun
 *
 */
public class PageHolder {
	private static Log log = LogFactory.getLog(PageHolder.class);
	private static String[] nums = {"〇","一","二","三","四","五","六","七","八","九","十"};


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
	public static String getPrefixPath(String type) {
		if (type.equals(Categories.PATH_TYPE.HTML)) {
			if (isLocal()) {
				return getSysParm("path_html_local");
			} else {
				return getSysParm("path_html_server");
			}
			
		}
		if (type.equals(Categories.PATH_TYPE.IMG_PAGE)) {
			if (isLocal()) {
				return getSysParm("path_image_page_local");
			} else {
				return getSysParm("path_image_page_server");
			}
		}
		if (type.equals(Categories.PATH_TYPE.IMG_STYLE)) {
			if (isLocal()) {
				return getSysParm("path_image_style_local");
			} else {
				return getSysParm("path_image_style_server");
			}
		}
		if (type.equals(Categories.PATH_TYPE.IMG_TMPLET)) {
			if (isLocal()) {
				return getSysParm("path_image_templet_local");
			} else {
				return getSysParm("path_image_templet_server");
			}
		}
		if (type.equals(Categories.PATH_TYPE.IMG_PHOTO)) {
			if (isLocal()) {
				return getSysParm("path_image_photo_local");
			} else {
				return getSysParm("path_image_photo_server");
			}
		}
		if (type.equals(Categories.PATH_TYPE.FLASH)) {
			if (isLocal()) {
				return getSysParm("path_flash_local");
			} else {
				return getSysParm("path_flash_server");
			}
		}
		return "";
	}

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
	public static String getDomain(String type) { 
		if (type != null) {
			if (type.equals(Categories.DOMAIN_TYPE.CSS)) {
				return getSysParm("domain_css");
			} else if (type.equals(Categories.DOMAIN_TYPE.JS)){
				return getSysParm("domain_js");
			} else if (type.equals(Categories.DOMAIN_TYPE.IMG)) {
				long radom = Math.round(1000*Math.random());
				radom = radom%5;
				return getSysParm("domain_imge_"+radom);
			} else if (type.equals(Categories.DOMAIN_TYPE.VIDEO)) {
				return getSysParm("domain_video");
			}  else {
				return getSysParm("domain_default");
			}
		}
		return getSysParm("domain_default");
	}
	
	
	public static String[] stringToArray(String urls) {
		return urls.split(",");
	}
	
	public static String getDatePagePath(String filename) {
		String suffix = filename.substring(filename.lastIndexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String now = sdf.format(new Date());
		return filename.replace(suffix, "") + "/" + now + suffix;
	}
}
