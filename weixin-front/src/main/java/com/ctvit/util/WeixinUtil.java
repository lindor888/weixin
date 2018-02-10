package com.ctvit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class WeixinUtil {
	/**
	 * 获取token
	 * @return
	 */
	public static String getAccessToken(String appid, String secret){
		String access_token=null;
		String ccess_token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&&appid="+appid+"&&secret="+secret;
		try {
			String tokenJson = InterfaceManagementClient.httpClientGetStream("", ccess_token_url);
			JSONObject json = JSONObject.fromObject(tokenJson);
			access_token = json.getString("access_token");
			
			if(access_token==null){
				System.out.println("验证失败！");
			}else{
				System.out.println("验证成功！access_token"+access_token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	} 
	
	/**
	 * 下载图片
	 * @param imgUrl
	 * @return
	 */
	public static String downloadImageUrl(String imgUrl){
		KeyGenerator keyGenerator = new KeyGenerator();
		String imgPath = "";
        try {
			URL url = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
			conn.setRequestMethod("GET");  
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();  
			String fileName = "WEIXIN" + keyGenerator.getKey(new Object())+".jpg";
			String prePath = FileUtil.getFilePath();
			String filePath = ConfKit.get("filePath") + prePath + fileName;
			imgPath = ConfKit.get("fileWebUrl") + prePath + fileName;
			FileOutputStream fos = null;
			File file = new File(filePath);
			if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
			if(!file.exists()) 
			file.createNewFile();
			fos = new FileOutputStream(filePath); 
			int ch =0 ;
			try{
				while((ch=inStream.read())!=-1){
					fos.write(ch);
				}
			} catch(Exception e){
				e.printStackTrace();
			} finally{
				fos.close();
				inStream.close();
			}
		} catch (Exception e) {
			return "";
		}
        return imgPath;
	}
}
