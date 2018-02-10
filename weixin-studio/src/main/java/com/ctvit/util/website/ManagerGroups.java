package com.ctvit.util.website;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.AccountSessionBean;
import com.ctvit.util.HttpKit;


public class ManagerGroups {
	//获得分组列表
	//https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN
	private static  String GROUPS_GET_URL = "https://api.weixin.qq.com/cgi-bin/groups/get"; 
	//创建组
	//https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN
	private static String GROUPS_GRATE = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=";
	//需改组名
	//https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN
	private static String GROUPS_UPDATE_NAME = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=";
	//移动用户分组
	//https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN
	private static String USERS_TO_GROUP="https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=";
	
	private ManagerMenu manamge = new ManagerMenu();
	
	private String accessToken = "";
	
	public String findWaccountId(){
		
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		AccountSessionBean accountSessionBean =  (AccountSessionBean)applicationContext.getBean("accountSessionBean");
      
		return accountSessionBean.getWaccountId();
	
	}
	
	public JSONObject getGroupsList(String appid,String Appsecret) throws Exception{
		JSONObject groups = null;
		accessToken = manamge.getaccess_token(appid, Appsecret);
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		String jsonStr = HttpKit.get(GROUPS_GET_URL, params);
		if(StringUtils.isNotEmpty(jsonStr)){
			JSONObject obj = JSONObject.fromObject(jsonStr);
			if(obj.get("errcode") != null){
				System.out.println(obj.getString("errmsg"));
				throw new Exception(obj.getString("errmsg"));
			}else{
				String getGroupsStr = obj.getString("groups");
				getGroupsStr=getGroupsStr.replace("[", "").replace("]", "");
				System.out.println("ooooo"+getGroupsStr);
				groups = JSONObject.fromObject(getGroupsStr);
			}
			//return groups;
		}
		return groups;
		
	}
	
	 /**
	 * 创建组
	 * @param json
	 * {"group":{"name":"分组名字（30个字符以内）"}} 
	 * @return
     *
	 */
	public String createGroups(String name,String appid,String Appsecret)throws Exception{
		String  accessToken=manamge.getaccess_token(appid,Appsecret);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(GROUPS_GRATE+accessToken);
		System.out.println(GROUPS_GRATE+accessToken);
		String json =" {"+
				"     \"group\":"+
				"    {	"+
				"          \"name\":\""+name+"\""+
				"      }"+
				" }";
		System.out.println(json);
		StringEntity myEntity = new StringEntity(json, "UTF-8");
		httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httppost.setEntity(myEntity);
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(
				resEntity.getContent(), "UTF-8"));
		String tmp = null;
		String responseJson = "";
		while ((tmp = bufReader.readLine()) != null) {
			responseJson = responseJson + tmp;
		}
		bufReader.close();
		System.out.println(responseJson);
		httpclient.getConnectionManager().shutdown();
		return responseJson;
	}
	
	 /**
	 * 修改组
	 * @param json
	 * {"group":{"id":108,"name":"test2_modify2"}}
	 * @return
	 * https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN
	 * @result {"errcode": 0, "errmsg": "ok"}
	 */
	public String moeifyGroups(String id,String newName,String appid,String Appsecret)throws Exception{
		String  accessToken=manamge.getaccess_token(appid,Appsecret);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(GROUPS_UPDATE_NAME+accessToken);
		System.out.println(GROUPS_UPDATE_NAME+accessToken);
		String json =" {"+
				"     \"group\":"+
				"    {	"+
				"		   \"id\":"+id+","+
				"          \"name\":\""+newName+"\""+
				"      }"+
				" }";
		System.out.println(json);
		StringEntity myEntity = new StringEntity(json, "UTF-8");
		httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httppost.setEntity(myEntity);
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(
				resEntity.getContent(), "UTF-8"));
		String tmp = null;
		String responseJson = "";
		while ((tmp = bufReader.readLine()) != null) {
			responseJson = responseJson + tmp;
		}
		bufReader.close();
		System.out.println(responseJson);
		httpclient.getConnectionManager().shutdown();
		return responseJson;
	}
	
	 /**
	 * 用户分配到组，（移动用户分组）
	 * @param json
	 * {"openid":"oDF3iYx0ro3_7jD4HFRDfrjdCM58","to_groupid":108}
	 * @return
	 * https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN
	 * @result {"errcode": 0, "errmsg": "ok"}
	 */
	public String UserToGroups(String openid,String id,String appid,String Appsecret)throws Exception{
		String  accessToken=manamge.getaccess_token(appid,Appsecret);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(USERS_TO_GROUP+accessToken);
		System.out.println(USERS_TO_GROUP+accessToken);
		String json ="    {	"+
				"          \"openid\":\""+openid+"\","+
				"		   \"to_groupid\":"+id+ 
				" }";
		System.out.println(json);
		StringEntity myEntity = new StringEntity(json, "UTF-8");
		httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httppost.setEntity(myEntity);
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(
				resEntity.getContent(), "UTF-8"));
		String tmp = null;
		String responseJson = "";
		while ((tmp = bufReader.readLine()) != null) {
			responseJson = responseJson + tmp;
		}
		bufReader.close();
		System.out.println(responseJson);
		httpclient.getConnectionManager().shutdown();
		return responseJson;
	}
	public static void main(String[] args) {
		String appid = "wx24f8d7028978ccc9";
		String Appsecret = "74b23ad9b868f888017820860f6b2efb";	
		ManagerGroups v = new ManagerGroups();
		try {
//		  	v.getGroupsList(appid, Appsecret);
//			JSONObject listStr = v.getGroupsList(appid, Appsecret);
			//JSONArray listArray = listStr.toJSONArray(null);
			//System.out.println(listArray.size());
			String ss = v.createGroups("测试123ssds",appid, Appsecret);
			System.out.print("ssssssss" + ss);
			//v.moeifyGroups("103", "测试test1234s", appid, Appsecret);
			//v.UserToGroups("oucWDjlBvuflblSxoYkZj_8RTa_E", "103", appid, Appsecret);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
