package com.ctvit.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ctvit.util.DownloadThread;
import com.ctvit.util.HttpKit;
import com.ctvit.util.ResourceLoader;

public class WeiboZhuanfaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7309914072777624216L;
	
	private final String RECEIVE_URL = ResourceLoader.getInstance().getConfig().getProperty("weiboReceiveUrl");
	private final String PRE_SYSTEM = ResourceLoader.getInstance().getConfig().getProperty("preSystem");
	private final String REPLACE_URL = ResourceLoader.getInstance().getConfig().getProperty("replaceUrl");

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String startTime = req.getParameter("startTime")==null?"":req.getParameter("startTime");
		String timesort = req.getParameter("timesort")==null?"":req.getParameter("timesort");
		String rows = req.getParameter("rows")==null?"":req.getParameter("rows");
		String taskWeiboId = req.getParameter("taskWeiboId")==null?"":req.getParameter("taskWeiboId");
		
		String servletUrl = RECEIVE_URL + "?startTime=" + URLEncoder.encode(startTime,"utf-8") + "&timesort=" + timesort + "&rows=" + rows + "&taskWeiboId=" + taskWeiboId;
		
		try{
			String result = HttpKit.get(servletUrl);
			JSONObject jsonResult = JSONObject.fromObject(result);
			JSONArray jsonArray = jsonResult.getJSONArray("rows");
			
			for(int i=0;i<jsonArray.size();i++){
				JSONObject o = jsonArray.getJSONObject(i);
				
				String face = o.getString("face");
				DownloadThread t = new DownloadThread(face);
				t.start();
				o.put("face", face.replace(PRE_SYSTEM, REPLACE_URL));
				
				String picUrls = o.getString("picUrls");
				if(StringUtils.isNotBlank(picUrls)){
					String[] picUrlArray = picUrls.split(",");
					for(String picUrl : picUrlArray){
						t = new DownloadThread(picUrl);
						t.start();
					}
					
					o.put("picUrls", picUrls.replaceAll(PRE_SYSTEM, REPLACE_URL));
				}
			}
			
			jsonResult.put("rows", jsonArray);
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json");
			resp.getWriter().print(jsonResult.toString());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
