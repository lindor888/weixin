package com.ctvit.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceLoader {
	protected static Logger log = LoggerFactory.getLogger(ResourceLoader.class);
	private static ResourceLoader instance=null;
	Properties config=new Properties();
	private ResourceLoader(){
	        java.io.InputStream in =ResourceLoader.class.getClassLoader().getResourceAsStream("conf.properties");
	        try {
	        	config.load(in);
	        	in.close();
			} catch (IOException e) {
				 log.info("加载conf.properties失败.");
				e.printStackTrace();
			}
	}
	public static synchronized ResourceLoader getInstance() {
		 if(instance == null){
	            instance = new ResourceLoader();
	            log.info("===初始化conf.properties======");
	        }   
	     return instance;   
	}
	public Properties getConfig(){
		return config;
	}
}
