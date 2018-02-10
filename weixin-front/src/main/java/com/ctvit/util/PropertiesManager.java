package com.ctvit.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	static Properties pro = new Properties();
	private PropertiesManager(){}
	
	static {
		try {
			pro.load(PropertiesManager.class.getClassLoader().getResourceAsStream("systemConfig.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperties(String key) {
		return pro.getProperty(key);
	}
	
	
   public static void main(String[] args) {  
	   System.out.println(PropertiesManager.getProperties("perPageNum"));  
   }
}
