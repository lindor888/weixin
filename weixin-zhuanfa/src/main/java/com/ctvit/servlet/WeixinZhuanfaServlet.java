package com.ctvit.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ctvit.util.DownloadThread;
import com.ctvit.util.HttpKit;
import com.ctvit.util.ResourceLoader;

public class WeixinZhuanfaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3605441407504691024L;
	
	private final String RECEIVE_URL = ResourceLoader.getInstance().getConfig().getProperty("weixinReceiveUrl");
	private final String PRE_SYSTEM = ResourceLoader.getInstance().getConfig().getProperty("preSystem");
	private final String REPLACE_URL = ResourceLoader.getInstance().getConfig().getProperty("replaceUrl");
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String startTime = req.getParameter("startTime")==null?"":req.getParameter("startTime");
		String timesort = req.getParameter("timesort")==null?"":req.getParameter("timesort");
		String rows = req.getParameter("rows")==null?"":req.getParameter("rows");
		String waccountId = req.getParameter("waccountId")==null?"":req.getParameter("waccountId");
		
		String servletUrl = RECEIVE_URL + "?startTime=" + URLEncoder.encode(startTime,"utf-8") + "&timesort=" + timesort + "&rows=" + rows + "&waccountId=" + waccountId;
		
		try {
			String result = HttpKit.get(servletUrl);
			JSONObject jsonResult = JSONObject.fromObject(result);
			JSONArray jsonArray = jsonResult.getJSONArray("rows");
			
			for(int i=0;i<jsonArray.size();i++){
				JSONObject o = jsonArray.getJSONObject(i);
				
				String headimgurl = o.getString("headimgurl");
				DownloadThread t = new DownloadThread(headimgurl);
				t.start();
				o.put("headimgurl", headimgurl.replace(PRE_SYSTEM, REPLACE_URL));
				
				int type = o.getInt("type");
				if(type==1){
					String image = o.getString("image");
					t = new DownloadThread(headimgurl);
					t.start();
					o.put("image", image.replace(PRE_SYSTEM, REPLACE_URL));
				}else if(type==2){
					String audio = o.getString("audio");
					t = new DownloadThread(headimgurl);
					t.start();
					o.put("audio", audio.replace(PRE_SYSTEM, REPLACE_URL));
				}else if(type==3){
					String video = o.getString("video");
					t = new DownloadThread(headimgurl);
					t.start();
					o.put("video", video.replace(PRE_SYSTEM, REPLACE_URL));
				}
			}
			jsonResult.put("rows", jsonArray);
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json");
			resp.getWriter().print(jsonResult.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	
}
