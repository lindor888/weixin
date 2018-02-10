package com.ctvit.weixin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReceiveServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6839560808606234297L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse rep)
			throws ServletException, IOException {
		try{
			String content = req.getParameter("content");
			String id = req.getParameter("id");
			ReceiveMessage receiveMessage = new ReceiveMessage();
			receiveMessage.sendMessage(content, id);
			rep.getWriter().print("success");
		}catch(Exception e){
			e.printStackTrace();
			rep.getWriter().print("error");
		}
	}
}
