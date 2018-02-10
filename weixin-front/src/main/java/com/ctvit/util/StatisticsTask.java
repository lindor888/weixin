package com.ctvit.util;

import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;

public class StatisticsTask extends TimerTask {
	private static final int STATISTICS_SCHEDULE_HOUR = 0;
	private static boolean isRunning = false;
	private ServletContext context = null;

	public StatisticsTask(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		Calendar cal = Calendar.getInstance();
		// System.out.println(isRunning);
		if (!isRunning) {

			isRunning = true;
			context.log("开始执行指定任务");
			executeTask();
			isRunning = false;
			context.log("指定任务执行结束");
		} else {
			context.log("上一次任务执行还未结束");
		}
	}

	/**
	 * 执行任务
	 */
	public void executeTask() {
		GetAccessToken get = new GetAccessToken();
		get.getToken();
	}
}
