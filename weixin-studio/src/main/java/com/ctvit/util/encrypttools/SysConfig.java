package com.ctvit.util.encrypttools;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;







/**
 * 
 * @author kuangjz (修改)
 * 
 * 系统配置数据应在数据库中保存，而非放置到配置文件中，随后版本将进行此类修改
 *
 */

public class SysConfig {
	
	/**
	 * 将本类定义为singleton类
	 */
	private SysConfig(){		
	}
	private static SysConfig mConfig= new SysConfig();
	private Map<String,String> params = null;
	private String CONFIG_FILE="/conf.properties";
	//private String CONFIG_FILE="conf.properties";
	
	private static boolean hasInit = false;
	private Map<String,String> params1 = null;
	public static String getSysParam(String param) {
//		if (getAllParamsFromDB().get(param) != null) {
//			return getAllParamsFromDB().get(param) ;
//		}
		return getAllParams().get(param);		
	}
	
	public static Map<String,String> getAllParams() {
		synchronized(mConfig){
			if(mConfig.params==null){				
				mConfig.readConfig();
			}			
		}
		return mConfig.params;
	}
	
	private void readConfig() {
		mConfig.params = new HashMap<String,String>();
		
		try {			
			Properties props = new Properties();
			props.load(SysConfig.class.getResourceAsStream(CONFIG_FILE));
			Enumeration  e = props.propertyNames();
			while(e.hasMoreElements()) {
				String key = (String)e.nextElement();
				String value = props.getProperty(key);
				params.put(key, value);
			}	
			if(PageHolder.isLocal()) {
				params.put("root_path", params.get("root_path_local"));
				params.put("root_path_issue_temp", params.get("root_path_issue_temp_local"));
				params.put("root_path_issue", params.get("root_path_issue_local"));
			} else {
				params.put("root_path", params.get("root_path_server"));
				params.put("root_path_issue_temp", params.get("root_path_issue_temp_server"));
				params.put("root_path_issue", params.get("root_path_issue_server"));
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
