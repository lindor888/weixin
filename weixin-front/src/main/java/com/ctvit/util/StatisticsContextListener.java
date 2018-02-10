package com.ctvit.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StatisticsContextListener implements ServletContextListener {
	private java.util.Timer timer = null;

	// 从天脉获取token接口 法制微信token 44d6caeb98c87318

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("定时器销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		GetAccessToken get = new GetAccessToken();
		get.getToken();
		System.out.println("第一个执行");
		timer = new java.util.Timer(true);
		event.getServletContext().log("定时器已启动");
		timer.schedule(new StatisticsTask(event.getServletContext()), 0, 10 * 60 * 1000);// 每隔1小时
		event.getServletContext().log("已经添加任务调度表");
	}

}
