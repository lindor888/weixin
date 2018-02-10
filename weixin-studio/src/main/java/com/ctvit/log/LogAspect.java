package com.ctvit.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Account;
import com.ctvit.util.Const;

public class LogAspect {

	private  HttpServletRequest request=ServletActionContext.getRequest();;
	private  HttpSession session=ServletActionContext.getRequest().getSession();

	/**
	 * 在方法前记录
	 * 
	 * @param jp
	 */

	public void logInfo() {
		String ip = session.getAttribute("ip").toString();
		Account user = (Account) session.getAttribute(Const.SESSION_USER);
		System.out.println("=====================================");
		System.out.println("====ip：" + ip);
		System.out.println("====用户名：" + user.getUsername());
		System.out.println("=====================================");
	}

	/**
	 * 在方法结束后纪录
	 * 
	 * @param jp
	 */
	// @After("execution(* com.flf.service.*.*(..))")
	// public void logInfoAfter(JoinPoint jp) {
	// System.out.println("=====================================");
	// System.out.println("====" + jp.getSignature().getName() + "方法-结束！");
	// System.out.println("=====================================");
	// }

}
