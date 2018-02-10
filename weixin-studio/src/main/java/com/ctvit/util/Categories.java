package com.ctvit.util;
/**
 * 常用静态常量属性
 * 合成模式：COMPOSE_MODE
 * 页面类型：PAGE_TYPE
 * 页面版本：PAGE_VERSION
 * 主键类型：ID_TYPE
 * 发布顺序：PAGE_ISSUE_ORDER
 * 广告位置：CONTENT_AD_LAYOUT
 * 广告层级：CONTENT_AD_LEVEL
 * 广告顺序：CONTENT_AD_POSITION
 * 域名类型：DOMAIN_TYPE
 * 文件名称：FILE_NAME
 * 文件目录：FILE_DIR
 * 发布类型：ISSUE_YPE
 * 文件类型：FILE_TYPE
 * @author liyun
 *
 */
public class Categories {
	/**
	 * 合成模式
	 */
	public static class COMPOSE_MODE {
		public static final String PREVIEW = "0";//预览
		public static final String PUBLISH = "1";//合成
		public static final String MAINTAIN = "2";//维护
	}
	/**
	 * 页面类型
	 * @author liyun
	 *
	 */
	public static class PAGE_TYPE {
		public static final String 	COMMON = "0";//二级页
		public static final String 	BANNER = "1";//banner页
		public static final String 	NAVIGTION = "2";//导航页
		public static final String 	TEMPLATE = "3";//三级页模板
		public static final String 	INCLUDE = "4";//包含页(正文页底部、右侧包含页)
		
		public static final String 	CONTENT = "18";//三级页
		public static final String 	PHOTOALBUM = "6";//影集	
		public static final String 	VIDEO = "7";//视频集
		public static final String 	CSS = "8";//样式
		public static final String 	JS = "9";//脚本
		public static final String 	PHOTOSALL = "10";//图片聚合
		public static final String 	VIDEOALBUM = "11";//视频集
		public static final String 	AUDIOALBUM = "12";//音频集
		public static final String 	VIDEOALBUMLIST = "13";//视频集中视频列表
		public static final String 	VIDEOALBUMCZB = "20";//视频集存整版
		public static final String 	STAR = "14";//人物
		public static final String 	AUDIOALBUMLIST = "15";//音频集同类唱片列表
		public static final String 	AUDIO = "16";//音频
		public static final String 	FULLPAGE = "17";//存正版
		public static final String 	ACTOR = "19";//人物
		//------ new -----2012/5/7 ----------
		public static final String  XMLPAGE = "5"; //xml手机页面
		//------ new -----2013/8/14 ----------
		public static final String 	SQLFULLPAGE = "21";//带SQL模块存整版
	}
	/**
	 * 页面版本
	 * @author liyun
	 *
	 */
	public static class PAGE_VERSION {
		public static final String 	CURR = "0";//当前版本
		public static final String 	OLDE = "1";//当前版本
	}
	/**
	 * 主键类型
	 * @author liyun
	 *
	 */
	public static class ID_TYPE {
		public static final String 	CHAN = "C";//子台
		public static final String 	TOPC = "T";//专题
		public static final String 	PAGE = "P";//页面
		public static final String 	ARTICLE = "ARTI";//正文
		public static final String 	PHOTO_ALBUM = "PHOA";//影集
		public static final String 	VIDEO = "VIDE";//视频
		public static final String 	VIDEO_ALBUM = "VIDA";//视频集
		public static final String 	STAR = "STAR";//影集
	}
	/**
	 * 页面发布顺序
	 * @author liyun
	 *
	 */
	public static class PAGE_ISSUE_ORDER {
		public static final int CSS = 0;
		public static final int JS =4;
		public static final int HTML = 12;
	}
	/**
	 * 内容页广告位置
	 * @author chenlei
	 *
	 */
	public static class CONTENT_AD_LAYOUT {
		public static final String RIGHT = "1";//右侧
		public static final String LEFT = "2";//左侧
		public static final String TOP = "3";//上侧
		public static final String LOWER = "4";//下侧
	}
	
	/**
	 * 内容页广告层级
	 * @author chenlei
	 *
	 */
	public static class CONTENT_AD_LEVEL {
		public static final String ALL = "0";//全网
		public static final String CHANNEL = "1";//频道
		public static final String TOPIC = "2";//专题
		public static final String PAGE = "3";//页面
		
	}
	
	/**
	 * 内容页广告顺序
	 * @author chenlei
	 *
	 */
	public static class CONTENT_AD_POSITION {
		public static final String TOP = "0";//顶部
		public static final String MIDDLE = "1";//中间
		public static final String BOTTOM = "2";//底部
	}
	/**
	 * 域名类型
	 * @author liyun
	 *
	 */
	public static class DOMAIN_TYPE {
		public static final String 	DEFA = "0";//样式
		public static final String 	CSS = "1";//样式
		public static final String 	JS = "2";//脚本
		public static final String 	IMG = "3";//图片
		public static final String 	FLASH = "4";//FLASH
		public static final String 	VIDEO = "5";//视频文件
	}
	/**
	 * 文件名称
	 * @author liyun
	 *
	 */
	public static class FILE_NAME {
		public static final String DEF_FILE_NAME = "index";//默认文件名
		public static final String EXT_HTML = ".shtml";//文件扩展名
		public static final String EXT_XML = ".xml";//配置文件扩展名
		public static final String DEF_HTML = "index.shtml";//默认的文件名
		public static final String DEF_CSS = "style.css";//样式文件名
		public static final String DEF_JS = "main.js";//脚本文件名
		public static final String DEF_TOP = "top.shtml";//include包含页的文件名
		public static final String DEF_MIDDLE = "middle.shtml";//include包含页的文件名
		public static final String DEF_BOTTOM = "bottom.shtml";//include包含页的文件名
		//------ new 2012/5/7--------
		public static final String EDF_XML = "index.xml";    //默认 xml手机页面文件名
	}
	/**
	 * 文件目录
	 * @author liyun
	 *
	 */
	public static class FILE_DIR {
		public static final String INCLUDE = "include";//include文件夹
		public static final String STYLE = "style";//navigation文件夹
		public static final String LAYOUT_RIGHT = "right";
		public static final String LAYOUT_LOWER = "lower";
		public static final String POSITION_TOP = "top";
		public static final String POSITION_MIDDLE = "middle";
		public static final String POSITION_BOTTOM = "bottom";
	}
	/**
	 * 发布类型
	 * @author liyun
	 *
	 */
	public static class ISSUE_YPE {
		public static final String ISSUE = "add";//操作类型：发布
		public static final String DELETE = "del";//操作类型：删除
		public static final String DELCACHE = "delCache";//清除缓存
	}
	/**
	 * 文件类型
	 * @author liyun
	 *
	 */
	public static class FILE_TYPE {
		public static final String CSS = "css";
		public static final String JS = "js";
		public static final String IMAGE_STYLE = "img_style";
		public static final String IMAGE_PHOTO = "img_photo";
		public static final String IMAGE_PAGE = "img_page";
		public static final String HTML = "html";
		public static final String XML = "xml";
		public static final String FLASH = "flash";
		public static final String OTHERS = "others";
	}
	
	/**
	 * 根目录类型
	 * @author liyun
	 *
	 */
	public static class PATH_TYPE {
		public static final String HTML = "0";
		public static final String IMG_PHOTO ="1";
		public static final String IMG_STYLE ="2";
		public static final String IMG_PAGE ="3";
		public static final String IMG_TMPLET ="4";
		public static final String FLASH ="5";
	}
}
