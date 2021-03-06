package com.ctvit.util;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 获取accessToken的定时任务
 * 
 * @author Admini
 * 
 */
public class QuartzJobWeixin extends QuartzJobBean {

	private int timeout;
	private static int i = 0;

	// 调度工厂实例化后，经过timeout时间开始执行调度
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * 要调度的具体任务
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("定时任务执行中…");
	}

}
