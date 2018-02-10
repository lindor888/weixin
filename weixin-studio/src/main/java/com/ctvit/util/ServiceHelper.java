package com.ctvit.util;

import com.ctvit.service.MenuServiceImpl;


/**
 * @author Administrator
 * 获取Spring容器中的service bean
 */
public final class ServiceHelper {
	
	public static Object getService(String serviceName){
		//WebApplicationContextUtils.
		return Const.WEB_APP_CONTEXT.getBean(serviceName);
	}
	
	public static MenuServiceImpl getMenuService(){
		return (MenuServiceImpl) getService("menuService");
	}
}
