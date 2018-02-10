package com.ctvit.util.website;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctvit.bean.Followers;
import com.ctvit.util.EmojiFilter;
import com.ctvit.util.HttpKit;
import com.ctvit.util.website.ManagerMenu;

public class ManagerFollowers{
	public static String appid = "wx24f8d7028978ccc9";
	public static String Appsecret = "74b23ad9b868f888017820860f6b2efb";
	public static String token = "";
	
	//获取关注者列表
	//https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENI
	private static  String USER_GET_URI = "https://api.weixin.qq.com/cgi-bin/user/get"; 
	//用户基本信息 
	//https://api.weixin.qq.com/cgi-bin/user/info?access_token=1QPGswoZBQf00QVcoxR6_iRyiUVjstt6p7QZrD8MdduHSfCPQAq4p0Udc0nEd91zJzmCxUcldpDgTikjASLa-g&openid=oucWDjlBvuflblSxoYkZj_8RTa_E&lang=zh_CN
	private static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";
		
		
	/**
	* 获取帐号的关注者列表
	* @param accessToken
	* @param next_openid
	* @return
	*/
	public static JSONObject getFollwersList(String accessToken, String next_openid) throws Exception{
	
		Map<String, String> params = new HashMap<String, String>();
	
		params.put("access_token", accessToken);
	
		if(!"".equals(next_openid)){
			params.put("next_openid", next_openid);
		}
		String jsonStr = HttpKit.get(USER_GET_URI, params);
		if(StringUtils.isNotEmpty(jsonStr)){
			JSONObject obj = JSONObject.parseObject(jsonStr);
		if(obj.get("errcode") != null){
			throw new Exception(obj.getString("errmsg"));
		}
			return obj;
		}
		return null;
	}
	
	/**
	* 获取帐号的关注者数量
	* @param accessToken
	* @param next_openid
	* @return
	*/
	public int getFollwersCount()  throws Exception{
		ManagerMenu manamge = new ManagerMenu();
		int num = 0;
		token = manamge.getaccess_token(appid, Appsecret);
		JSONObject value = getFollwersList(token,null);
		//关注这数量
		String countStr = value.getString("count");
		num = Integer.parseInt(countStr);
		return num;
	}
	
public static Followers getUserInfo(String openid) throws Exception{
	    
	    String sex = "";
	    ManagerMenu managerMenu = new ManagerMenu();
	    String accessToken = managerMenu.getaccess_token(appid, Appsecret);
        System.out.println("accessToken="+accessToken);
	    String lang = "zh_CN";
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("openid", openid);
		params.put("lang", lang);
		String jsonStr = HttpKit.get(USER_INFO, params);
	//	System.out.println("------------------------------->"+USER_INFO+"<---------------------------------------------");
		Followers followers = new Followers();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++v "+  jsonStr);
		if(StringUtils.isNotEmpty(jsonStr)){
            
			JSONObject obj = JSONObject.parseObject(jsonStr);
		
		if(obj.get("errcode") != null){
			System.out.println(obj.getString("errmsg"));
			throw new Exception(obj.getString("errmsg"));
		
		}else{
//					JSONObject obj = JSONObject.fromObject(jsonStr);
					followers.setOpenid(openid.toString());
					System.out.println("openid2 =  "+ openid);
					followers.setSubscribe(obj.getString("subscribe"));
					followers.setNickname(EmojiFilter.filterEmoji(" "+obj.getString("nickname")));
					//followers.setNickname(obj.getString("nickname").replace("👑", "?").replace("🎀", "?").replace("🌞", "?").replace("🐯", "?").replace("💐", "?").replace("🐱", "?").replace("🐘🍎🍅", "???").replace("🍀", "?").replace("👸", "?")
					//		.replace("💄", "?").replace("🇩🇪", "??").replace("💋", "?").replace("💋", "?").replace("🌺", "?"));
					sex = obj.getString("sex").equals("1")?"男":"女";
				
					followers.setSex(sex);
					
					followers.setCity(obj.getString("city"));
			
					followers.setCountry(obj.getString("country"));
					
					followers.setProvince(obj.getString("province"));
					
					followers.setHeadimgurl(obj.getString("headimgurl"));
			
					followers.setSubscribeTime(obj.getString("subscribe_time"));	
		}
			return followers;
		}
		return followers;
	}
	public static void main(String[] args) {
		ManagerMenu manamge = new ManagerMenu();
		ManagerFollowers tt = new ManagerFollowers();
		token = manamge.getaccess_token(appid, Appsecret);
		try {
			JSONObject value = getFollwersList(token,null);
			/*//关注这数量
			String count = value.getString("count");
			//关注者list
			String data = value.getString("data");
			JSONObject obj = JSONObject.parseObject(data);
			 JSONArray s = obj.getJSONArray("openid");
			String listStr = obj.getString("openid");
			System.out.println(listStr);
			String[] listArray = listStr.split(",");
			List<String> vallist = Arrays.asList(listArray);
			int i =0;
			for(String val:vallist){
				i++;
				val = val.replace("[","");
				val = val.replace("\"", "");
				val = val.replace("]", "");
				Followers s1 =ManagerFollowers.getUserInfo(val);
			//	System.out.println("val = " + s1.getNickname());
				System.out.println("i = " + i);
			}
			System.out.println("tt = " + tt.getFollwersCount());*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(token);
	}


}
