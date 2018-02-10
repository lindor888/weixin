package com.ctvit.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ctvit.util.Base64Util;
import com.ctvit.util.ResourceLoader;
import com.opensymphony.xwork2.ActionSupport;

public class ReceiveWordsAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5074936881577138987L;
	
	private static Logger log = Logger.getLogger(ReceiveWordsAction.class);
	
	private Map<String, Object> queryJson;
	
	private String data;
	
	public String start() throws IOException{
		queryJson = new HashMap<String, Object>();
		try{
			Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR)); 
			String month = String.valueOf(cal.get(Calendar.MONTH)+1);
			String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			String fileName = String.valueOf(System.currentTimeMillis())+".png";
			String values = "/" + year + "/" + month + "/" + day + "/";
			
			String path = ResourceLoader.getInstance().getConfig().getProperty("imageFile") +values+ fileName;
			String urlPath = ResourceLoader.getInstance().getConfig().getProperty("imageUrl") +values + fileName;
			
			File file = new File(path);
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			if(!file.exists()) 
				file.createNewFile();
			
			FileOutputStream ous = new FileOutputStream(file);
			ous.write(Base64Util.decode(data));
			ous.close();
			queryJson.put("data", urlPath);
			
		}catch(Exception e){
			log.error("", e);
		}
		
		
		return "start";
	}
	

	public Map<String, Object> getQueryJson() {
		return queryJson;
	}

	public void setQueryJson(Map<String, Object> queryJson) {
		this.queryJson = queryJson;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
