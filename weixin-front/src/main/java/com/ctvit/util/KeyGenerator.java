package com.ctvit.util;

/**
 * @author CTVIT
 *	主键生产器类，为各个数据表生成主键
 */
public class KeyGenerator {
	
	private static int randomNum = 100;
	
	private String preKey = "";
	
	public KeyGenerator() {

	}

	/**
	 * 返回主键  4位类名+13位毫秒数+3位递增数字
	 * @param className  类名s
	 * @return  PK
	 */
	public String getKey(Object baseData) throws Exception {

		String keyStr = "";
		String className = null;
		if (baseData != null) {
			className = baseData.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
//			if (KeyGeneratorNew.isNew(className)) {
//				KeyGeneratorNew kgn = new KeyGeneratorNew();
//				return kgn.getKey(baseData);
//			}
			className = this.getTargetType(className);
		}

		try {
			
			if (className == null) {
				className = "Xxxx";
			} else if (className.length() >= 4) {
				className = className.substring(0, 4);
			} else {
				className = className + "Xxxx";
				className = className.substring(0, 4);
			}

			keyStr = keyStr + className;
			//Date date = new Date();
			synchronized (KeyGenerator.class){
				if (randomNum >= 999) {
					randomNum = 100;
				} else {
					randomNum = randomNum + 1;
				}
				keyStr = keyStr + System.currentTimeMillis() + randomNum;
			}
			//第二次校验key是否与上次的key相同
			if (keyStr.equals(preKey)) {
				Thread.currentThread().sleep(1);
			} else {
				preKey = keyStr;
			}

		} catch (Exception e) {
			keyStr = null;
			throw new Exception("get PK error");
		}
		return keyStr;
	}
	
	public String getTargetType(String className) throws Exception {
		String targetType = "";
		
		try {
			if(className.equals("User")) {targetType = "USRI";}
			else if(className.equals("CustomMenus")) {targetType = "MENU";}
			else if(className.equals("Article")) {targetType = "ARTI";}
			else if(className.equals("MatchingArticle")) {targetType = "MAAT";}
			if(className.equals("Keyword")) {targetType = "TKWD";}
			else {targetType = className;}
			
		}catch(Exception e) {
			targetType = null;
			throw new Exception(e);
		}
		System.out.println(targetType);
		return targetType;
	}
	
	public String getClass(String targetType) throws Exception {
		String className = "";
		
		try {
			if(targetType.equals("USRI")) {	className = "User";}
			else if(className.equals("CustomMenus")) {targetType = "MENU";}
			else if(className.equals("Article")) {targetType = "ARTI";}
			else if(className.equals("MatchingArticle")) {targetType = "MAAT";}
			else {	className = targetType;	}
		}catch(Exception e) {
			className = null;
			throw new Exception(e);
		} 
		
		return className;
	}
}
