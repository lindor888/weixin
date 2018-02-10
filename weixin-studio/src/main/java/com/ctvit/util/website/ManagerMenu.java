package com.ctvit.util.website;



import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;



import net.sf.json.JSONObject;


import com.ctvit.bean.Waccount;
import com.ctvit.util.InterfaceManagementClient;


public class ManagerMenu {

	/*
	 * access_token验证
	 * */
	public String getaccess_token(String appid,String Appsecret){
		
		//String appid="wx24f8d7028978ccc9";中视广信公众号
		//String secret="74b23ad9b868f888017820860f6b2efb";
		//String appid="wx0fe0c45ffbfdff5a";微信公众测试平台
		//String secret="0c945c1bee5c0d92df3d2f36225c8527";
		String access_token=null;
		String ccess_token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&&appid="+appid+"&&secret="+Appsecret;
		
		
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
	
	 /**
		 * 创建菜单  
		 * @param json
		 * @return
		 *  查询菜单
	     * https://api.weixin.qq.com/cgi-bin/menu/get?access_token=2eNnzIrUPaLRMGU0XsGh7sZQm-YtnNOxhsPupMAvbdBs0kUUF8bgV01_NZo7NhncLSgcqaYvh0IMt90EgKsGPg
	     *
		 * @throws Exception
		 */
		public String createMenus(String json,String appid,String appSecret)throws Exception{
			//String result= "";
			
			//Waccount  waccount=new Waccount();
			
			//ManagerMenu m=new ManagerMenu();
			String  accessToken=this.getaccess_token(appid,appSecret);	
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken);
			/*模拟数据
			 * String json =" {"+
					"     \"button\":["+
					"    {	"+
					"          \"type\":\"click\","+
					"          \"name\":\"今日歌1\","+
					"          \"key\":\"V1001_TODAY_MUSIC\""+
					"      },"+
					"      {"+
					"           \"type\":\"click\","+
					"           \"name\":\"歌手简介\","+
					"           \"key\":\"V1001_TODAY_SINGER\""+
					"      },"+
					"      {"+
					"           \"name\":\"菜单\","+
					"           \"sub_button\":["+
					"           {	"+
					"               \"type\":\"view\","+
					"               \"name\":\"搜索\","+
					"               \"url\":\"http://www.soso.com/\""+
					"            },"+
					"            {"+
					"               \"type\":\"view\","+
					"               \"name\":\"视频\","+
					"               \"url\":\"http://v.qq.com/\""+
					"            },"+
					"            {"+
					"               \"type\":\"click\","+
					"               \"name\":\"赞一下我们\","+
					"               \"key\":\"V1001_GOOD\""+
					"            }]"+
					"       }]"+
					" }";*/
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
		
		
		/*
		 * 删除菜单
		 * */
		
		public void removeMenu(String appid,String Appsecret){
			String getMenuURL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+getaccess_token(appid,Appsecret);
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
		public static void main(String[] args) {
			String  str="{\"access_token\":\"EANVzi3Tfpp2DyeHrO2Rc8_jd8jJVF9Nm0FvNVeYUomFpTkWlRKrBVwXVQgU7nkrriP4XYHacofHf_MiDJ-vaQ\",\"expires_in\":7200}";
           JSONObject json = JSONObject.fromObject(str);
           String json2 =" {"+
					"     \"button\":["+
					"    {	"+
					"          \"type\":\"click\","+
					"          \"name\":\"今日歌1\","+
					"          \"key\":\"V1001_TODAY_MUSIC\""+
					"      },"+
					"      {"+
					"           \"type\":\"click\","+
					"           \"name\":\"歌手简介\","+
					"           \"key\":\"V1001_TODAY_SINGER\""+
					"      },"+
					"      {"+
					"           \"name\":\"菜单\","+
					"           \"sub_button\":["+
					"           {	"+
					"               \"type\":\"view\","+
					"               \"name\":\"搜索\","+
					"               \"url\":\"http://www.soso.com/\""+
					"            },"+
					"            {"+
					"               \"type\":\"view\","+
					"               \"name\":\"视频\","+
					"               \"url\":\"http://v.qq.com/\""+
					"            },"+
					"            {"+
					"               \"type\":\"click\","+
					"               \"name\":\"赞一下我们\","+
					"               \"key\":\"V1001_GOOD\""+
					"            }]"+
					"       }]"+
					" }";
			System.out.println(json.getString("access_token"));
			ManagerMenu managerMenu = new ManagerMenu();
			try {
				managerMenu.createMenus(json2, "wx1de225a0ede77b42", "3fc8b5fbb0a160c33b94538aaf7b42e8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
