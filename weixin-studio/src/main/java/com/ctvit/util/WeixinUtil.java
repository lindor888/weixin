package com.ctvit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

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
			String filePath = ResourceLoader.getInstance().getConfig().getProperty("imageFile") + prePath + fileName;
			imgPath = ResourceLoader.getInstance().getConfig().getProperty("imageUrl") + prePath + fileName;
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
	
	/**
	 * 获取二维码图片的ticket
	 * @param accessToken
	 * @param num
	 * @return
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static String getErweiCodeTicket(String accessToken, String num) throws Exception{
		String ticket = "";
		Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> scene = new HashMap<String,Object>();
        Map<String,Object> sceneStr = new HashMap<String,Object>();
        sceneStr.put("scene_str", num);
        scene.put("scene", sceneStr);
        
        json.put("action_name", "QR_LIMIT_STR_SCENE");
        json.put("action_info", scene);
        String result = HttpKit.post("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken, com.alibaba.fastjson.JSONObject.toJSONString(json));
        
        JSONObject j = JSONObject.fromObject(result);
        ticket = j.getString("ticket");
        return ticket;
	}
	
	/**
	 * 下载二维码
	 * @param ticket
	 * @throws Exception
	 */
	public static String getErweiCode(String ticket) throws Exception{
		return downloadImageUrl("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+URLEncoder.encode(ticket, "utf-8"));
	}
	
	
	public static void main(String[] args) throws Exception {
		String accessToken = getAccessToken("wx24f8d7028978ccc9", "74b23ad9b868f888017820860f6b2efb");
		String ticket = getErweiCodeTicket(accessToken, "2");
		System.out.println(ticket);
		System.out.println(getErweiCode(ticket));
		
		String imgurl = WeixinUtil.downloadImageUrl("http://www.hao123.com/r/image/2015-09-04/a58b9d48aa9987c7f1c30a1fa5f2c716.jpg");
        System.out.println(imgurl);
	}
	
	
}
