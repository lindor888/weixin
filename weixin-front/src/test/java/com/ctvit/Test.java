package com.ctvit;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Test {
	public static String httpPost(String url, String content) {
		String returnValue = null;
		URL uri = null;
		try {
			uri = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type",
					"application/x-www-form-urlencoded");
			//长度是实体的二进制长度
			conn.setRequestProperty("Content-Length", String.valueOf(content.toString().length()));
			conn.connect();
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(content.getBytes());
			out.flush();
			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(),"utf-8"));
			String lines;
			StringBuffer sb = new StringBuffer();
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			reader.close();
			returnValue = sb.toString();
			// 关闭连接
			conn.disconnect();
			return returnValue;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean send(String urlAddr, Map<String, Object> map) throws Exception {   
        boolean isSuccess = false;   
        StringBuffer params = new StringBuffer();   

        Iterator it = map.entrySet().iterator();   
        while(it.hasNext()){   
             Entry element = (Entry)it.next();   
             params.append(element.getKey());   
             params.append("=");   
             params.append(element.getValue());   
             params.append("&");   
         }   
       
        if(params.length() > 0){   
             params.deleteCharAt(params.length()-1);   
         }   
        HttpURLConnection conn = null;
        try{   
             URL url = new URL(urlAddr);   
             conn = (HttpURLConnection)url.openConnection();   

             conn.setDoOutput(true);   
             conn.setRequestMethod("POST");   
             conn.setUseCaches(false);   
             conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");   
             conn.setRequestProperty("Content-Length", String.valueOf(params.length()));   
             conn.setDoInput(true);   
             conn.connect();   

             OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");   
             out.write(params.toString());   
             out.flush();   
             out.close();   
           
            int code = conn.getResponseCode();   
            if (code != 200) {   
                 System.out.println("ERROR===" + code);   
             } else {   
                 isSuccess = true;   
                 System.out.println("Success!");   
             }   
         }catch(Exception ex){   
             ex.printStackTrace();   
         }finally{   
             conn.disconnect();   
         }   
        return isSuccess;   
	}   
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wid", "Wacc1422263269505101");
		map.put("message", "<xml><ToUserName><![CDATA[gh_e79cab7937cd]]></ToUserName><FromUserName><![CDATA[oucWDjvh-unwyVEKhPhT1HWOgTAg]]></FromUserName><CreateTime>1416881134</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[123]]></Content><MsgId>6085458133051187984</MsgId></xml>");
		send("http://localhost:8080/weixin-front/bridge", map);
	}
}
