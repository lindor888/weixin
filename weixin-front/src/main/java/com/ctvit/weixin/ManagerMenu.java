package com.ctvit.weixin;
/**
 * 管理自定义菜单
 * @author yangshijia
 *
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;


import com.ctvit.util.CommonUtil;
import com.ctvit.util.InterfaceManagementClient;

public class ManagerMenu {
	String menupath="/export/home/weblogic/Oracle/Middleware/user_projects/domains/mydomain/vms/weixin/menu.txt";
	//String menupath="D:\\weixin\\menu.txt";
	
	/*
	 * 添加菜单
	 * */
	
	public String addMenu(){
		
		String  access_token=this.getaccess_token();
		//String  access_token="L_p70UxXENSmvCiIH80KisCrouqFBAmmnKj6reVdGo38Fj_V-T3Lhu_vlb2V0bZfmoRJ4YtRCwWmwerD5MPk-fV0vgVyeHW064hoJuwGZxy11dVFl6VUR_MjZvQNEiovHLQKeAPjyCnoJovy7cPJHA";
		
		String addMenuURL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
		try {
			String menuData=CommonUtil.readFile(menupath);
			
			System.out.println("menuData="+menuData);
			
			String rusult=InterfaceManagementClient.httpClientPostStream(menuData, addMenuURL);
			
			System.out.println("rusult="+rusult);
			
			JSONObject json = JSONObject.fromObject(rusult);
			
			String errcode = json.getString("errcode");
			
			if("0".equals(errcode)){
				System.out.println("菜单创建成功！");
			}else if("40018".equals(errcode)){
				System.out.println("菜单创建失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	/*
	 * 查询菜单
	 * */
	public String getMenu(){
		String getMenuURL="https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+getaccess_token();
		String rusult="";
		try {
			rusult=InterfaceManagementClient.httpClientGetStream("", getMenuURL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rusult;
	}
	
	/*
	 * 删除菜单
	 * */
	
	public void removeMenu(){
		String getMenuURL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+getaccess_token();
		String rusult="";
		try {
			rusult=InterfaceManagementClient.httpClientGetStream("", getMenuURL);
			
			JSONObject json = JSONObject.fromObject(rusult);
			
			String errcode = json.getString("errcode");
			
			if("0".equals(errcode)){
				System.out.println("菜单删除成功！");
			}{
				System.out.println("菜单删除失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * access_token验证
	 * */
	public String getaccess_token(){
		
		String appid="wx24f8d7028978ccc9";
		String secret="74b23ad9b868f888017820860f6b2efb";
		String access_token=null;
		String ccess_token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&&appid="+appid+"&&secret="+secret;
		
		
		try {
			String tokenJson = InterfaceManagementClient.httpClientGetStream("", ccess_token_url);
			System.out.println(tokenJson);
			JSONObject json = JSONObject.fromObject(tokenJson);
			
			access_token = json.getString("access_token");
			
			if(access_token==null){
				System.out.println("验证失败！");
			}else{
				System.out.println("验证成功！access_token"+access_token);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return access_token;
	} 
	
	 public void createMenu() {
			String  accessToken=this.getaccess_token();			
	        StringBuffer bufferRes = new StringBuffer();
	        try {
				String menuData=CommonUtil.readFile(menupath);	  
				System.out.println("menuData="+menuData);					
	            URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ accessToken);
	            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
	             
	            // 连接超时
	            conn.setConnectTimeout(25000);
	            // 读取超时 --服务器响应比较慢，增大时间
	            conn.setReadTimeout(25000);
	             
	            HttpURLConnection.setFollowRedirects(true);
	            // 请求方式
	            conn.setRequestMethod("GET");
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
	            conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
	            conn.connect();
	            // 获取URLConnection对象对应的输出流
	            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
	            // 发送请求参数
	            //out.write(URLEncoder.encode(params,"UTF-8"));
	            out.write(menuData);
	            out.flush();
	            out.close();
	             
	            InputStream in = conn.getInputStream();
	            BufferedReader read = new BufferedReader(new InputStreamReader(in,"UTF-8"));
	            String valueString = null;
	            while ((valueString=read.readLine())!=null){
	                bufferRes.append(valueString);
	            }
	            System.out.println(bufferRes.toString());
	            in.close();
	            if (conn != null) {
	                // 关闭连接
	                conn.disconnect();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }	
	
	public static void main(String[] args) {
		ManagerMenu managerMenu = new ManagerMenu();
		managerMenu.addMenu();
		//		managerMenu.createMenu();
		System.out.println(managerMenu.getMenu());
	}
}
