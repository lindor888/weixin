/**
 * 
 */
package com.ctvit.util;


/**
 * 公共方法工具类
 * 
 * @author heyingcheng
 * @email heyingcheng@ctvit.com.cn
 * @date 2015-5-25 下午6:38:05
 */
public class CreateTableid {

	/**
	 * 生成数据表主键
	 * 
	 * @author heyingcheng
	 * @date 2015-5-25 下午6:45:00
	 * @return
	 * @return String
	 */
	public static String createTableID(Class<?> clazz) {
		return clazz.getSimpleName() + System.currentTimeMillis() + (int) ((Math.random() * 9 + 1) * 10000);
	}

	/**
	 * 文件大小转换为B,KB,MB,GB
	 * 
	 * @author yaonana
	 * @date 2015年8月11日 上午11:48:23
	 * @param size
	 * @return
	 * @return String
	 */
	public static String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;

		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else
			return String.format("%d B", size);
	}

}
