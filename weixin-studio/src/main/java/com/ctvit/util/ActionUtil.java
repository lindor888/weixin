package com.ctvit.util;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.AccountSessionBean;

public class ActionUtil {
	/**
	 * 获取session中公众号id
	 * @return
	 */
	public static String findWaccountId(){
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		AccountSessionBean accountSessionBean =  (AccountSessionBean)applicationContext.getBean("accountSessionBean");
		return accountSessionBean.getWaccountId();
	}
}
