package com.ctvit.epg;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class Appidservlet extends HttpServlet {
	
	private HttpServletRequest request;
	
	public void setAppid(String appid){
		
		request.getSession().setAttribute("accessID", appid);
	}
    public void getAppid(){
		System.out.println("session+============="+
		request.getSession().getAttribute("accessID"));
		
	}
}
