package com.ctvit.weixin;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.ctvit.bean.AccessTokenBean;
import com.ctvit.bean.Followers;
import com.ctvit.dao.FollowerDao;
import com.ctvit.util.EmojiFilter;
import com.ctvit.util.HttpKit;

public class GetWeixinUsers extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1049736902918890767L;
	// 获取用户基本信息接口
	private static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";
	// 从天脉获取accesstoken接口
	private static final String get_AccessToken = "http://mb.mtq.tvm.cn/rest/wxaccesstoken?token=b30738df18&weixin_token=44d6caeb98c87318";
	// 获取用户列表接口
	private static final String get_UserList = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s";
	private static final String get_UserList_Openid = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s";
	private java.util.Timer timer = null;
	private FollowerDao followersDao = new FollowerDao();

	// public String getAccessToken() {
	// String token = null;
	// try {
	// String xml = HttpKit.get(get_AccessToken);
	// token = this.readxml(xml);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return token;
	// }
	@SuppressWarnings("unchecked")
	public void getUserList(String accessToken, String waccountid, String wx_token) {
		try {
			String id = waccountid;
			String url = String.format(get_UserList, accessToken);
			String result = HttpKit.get(url);
			JSONObject resultObj = JSONObject.fromObject(result);
			if (resultObj.getInt("count") > 0) {
				JSONObject array = resultObj.getJSONObject("data");
				List<String> openids = (List<String>) array.get("openid");
				String nextOpenid = resultObj.getString("next_openid");
				for (int i = 0; i < openids.size(); i++) {
					String access_Token = this.getAccessToken(wx_token);
					if (followersDao.selectCount(openids.get(i)) == 0) {
						Followers value = this.getUserInfo(access_Token, openids.get(i));
						value.setGroupsId(1);
						value.setWaccountId(id);
						followersDao.insert(value);
					}
				}
				while (StringUtils.isNotEmpty(nextOpenid)) {
					String access_Token = this.getAccessToken(wx_token);
					String uri = String.format(get_UserList_Openid, access_Token, nextOpenid);
					String str = HttpKit.get(uri);
					JSONObject resultStr = JSONObject.fromObject(str);
					String next = resultStr.getString("next_openid");
					if (StringUtils.isNotEmpty(next)) {
						JSONObject data = resultStr.getJSONObject("data");
						List<String> openIds = (List<String>) data.get("openid");
						for (int j = 0; j < openids.size(); j++) {
							if (followersDao.selectCount(openIds.get(j)) == 0) {
								String as_Token = this.getAccessToken(wx_token);
								Followers fll = this.getUserInfo(as_Token, openIds.get(j));
								fll.setGroupsId(1);
								fll.setWaccountId(id);
								followersDao.insert(fll);
							}
						}
					}
					nextOpenid = next;
				}
			}
		} catch (Exception e) {
			this.waitMinutes();
			e.printStackTrace();
			this.contextInitialized();
		}
	}

	public String getAccessToken(String weixin_token) {
		String accessToken = null;
		try {
			AccessTokenBean atb = new AccessTokenBean();
			atb.setWeixin_token(weixin_token);
			AccessTokenBean tokenBean = followersDao.selectBywxToken(atb);
			accessToken = tokenBean.getAccess_token();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	public Followers getUserInfo(String accessToken, String openid) {
		Map<String, String> params = new HashMap<String, String>();
		String lang = "zh_CN";
		String sex = "";
		params.put("access_token", accessToken);
		params.put("openid", openid);
		params.put("lang", lang);
		Followers followers = new Followers();
		try {
			String jsonStr = HttpKit.get(USER_INFO, params);
			if (StringUtils.isNotEmpty(jsonStr)) {
				JSONObject obj = JSONObject.fromObject(jsonStr);
				followers.setOpenid(openid.toString());
				followers.setSubscribe(tranJson(obj, "subscribe"));
				followers.setNickname(EmojiFilter.filterEmoji(" " + tranJson(obj, "nickname")));
				sex = tranJson(obj, "sex").equals("1") ? "男" : "女";
				followers.setSex(sex);
				followers.setCity(tranJson(obj, "city"));
				followers.setCountry(tranJson(obj, "province"));
				followers.setProvince(tranJson(obj, "country"));
				String headimgurl = tranJson(obj, "headimgurl");
				followers.setHeadimgurl(headimgurl);
				followers.setSubscribeTime(tranJson(obj, "subscribe_time"));
				if (obj.get("errcode") != null) {
					System.out.println(obj.getString("errmsg"));
					throw new Exception(obj.getString("errmsg"));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return followers;
	}

	/**
	 * 解析数据
	 * 
	 * @param object
	 * @param field
	 * @return
	 */
	private static String tranJson(JSONObject object, String field) {
		try {
			String content = object.getString(field);
			return content;
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String[] args) {
		try {
			GetWeixinUsers gwu = new GetWeixinUsers();
			String accessToken = gwu.getAccessToken("7cb1cbd0e1dc");
			gwu.getUserList(accessToken, "Wacc1455634050938107", "7cb1cbd0e1dc");
			System.out.println("导出完成");

			// String url = "http://mb.mtq.tvm.cn/rest/PlatformUserinfo?token=b30738df18&wx_token=ddc78ff9bf341655&count=1";
			// String t = httpGet(url);
			// System.out.println(t);
		} catch (Exception e) {
			System.out.println("导出异常");
			e.printStackTrace();
		}
	}

	public void contextInitialized() {
		try {
			GetWeixinUsers gwu = new GetWeixinUsers();
			String accessToken = gwu.getAccessToken("6384de36ad5f");
			gwu.getUserList(accessToken, "Wacc1455634078571108", "6384de36ad5f");
			System.out.println("导出完成");
		} catch (Exception e) {
			System.out.println("导出异常");
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.contextInitialized();
	}

	// 从天脉获取json结果
	public static String httpGet(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		String strResult = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			request.addHeader("Accept", "text/json");
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				strResult = EntityUtils.toString(response.getEntity());
				/** 把json字符串转换成json对象 **/
				jsonResult = JSONObject.fromObject(strResult);
				url = URLDecoder.decode(url, "UTF-8");
			} else {

			}
		} catch (IOException e) {

		}
		return strResult;

	}

	public void waitMinutes() {
		try {
			TimeUnit.MILLISECONDS.sleep(30 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}