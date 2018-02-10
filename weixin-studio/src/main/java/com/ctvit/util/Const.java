package com.ctvit.util;

import org.springframework.context.ApplicationContext;

public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)).*";	//不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	public static final String ROLE_VERSION = "wx_role_v_";//缓存中角色版本的key前缀
	public static final String USER_ROLE_VERSION = "wx_user_role_";//缓存中用户的角色版本的key前缀
	public static final String SESSION_MENU_RIGHTS = "/weixin-mc";//缓存二级菜单 用于修改角色权限时候左侧菜单跳转登录页
}
