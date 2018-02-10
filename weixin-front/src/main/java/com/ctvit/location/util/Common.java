package com.ctvit.location.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * 公共方法工具类
 * 
 * @author heyingcheng
 * @email heyingcheng@ctvit.com.cn
 * @date 2015-5-25 下午6:38:05
 */
public class Common {

	/**
	 * 对象数组转换成字符串
	 * 
	 * @author heyingcheng
	 * @date 2015年8月12日 下午4:59:45
	 * @param objects
	 * @return
	 * @return String
	 */
	public static String objectsToString(char sp, Object... objects) {
		if (objects == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < objects.length; i++) {
			Object obj = objects[i];
			if (obj == null || "".equals(obj.toString())) {
				continue;
			}
			sb.append(obj.toString());
			if (i == (objects.length - 1))
				return sb.toString();
			sb.append(sp);
		}
		return sb.toString();
	}

	/**
	 * 集合转换字符串
	 * 
	 * @author heyingcheng
	 * @param c
	 * @date 2015年7月6日 下午6:59:24
	 * @param collection
	 * @return
	 * @return String
	 */
	public static <E> String collectionToString(Collection<E> collection) {
		return collectionToString(',', collection);
	}

	/**
	 * 集合转换字符串
	 * 
	 * @author heyingcheng
	 * @date 2015年7月6日 下午6:59:24
	 * @param collection
	 * @return
	 * @return String
	 */
	public static <E> String collectionToSpaceString(Collection<E> collection) {
		return collectionToString(' ', collection);
	}

	/**
	 * 集合转换字符串
	 * 
	 * @author heyingcheng
	 * @date 2015年7月6日 下午6:59:24
	 * @param collection
	 * @return
	 * @return String
	 */
	public static <E> String collectionToString(char c, Collection<E> collection) {
		Iterator<E> it = collection.iterator();
		if (!it.hasNext())
			return "";
		StringBuilder sb = new StringBuilder();
		for (;;) {
			E e = it.next();
			sb.append(e);
			if (!it.hasNext())
				return sb.toString();
			sb.append(c);
		}
	}

}
