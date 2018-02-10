package com.ctvit.epg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.ctvit.dao.LocationDao;
import com.ctvit.util.ConfKit;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.weixin.ScopeUtils;
import com.danga.MemCached.MemCachedClient;

public class To extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final EgpRunnableAction egpRunnableAction = new EgpRunnableAction();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		if (!egpRunnableAction.isAlive()) {
			egpRunnableAction.start();
		}
		String code = request.getParameter("code");
		String url = ConfKit.get("openurl");
		int state = Integer.parseInt(request.getParameter("state"));
		String qrcodeID = request.getParameter("qrcodeID");
		if ((qrcodeID == "") || (qrcodeID == null)) {
			qrcodeID = (String) memCachedClientInstance.get("qrcodeID");
		}
		String openid = "";
		if (StringUtils.isNotEmpty(code)) {
			openid = new ScopeUtils().getOpenid(code, "");
		}
		int num = 0;
		if ((openid != "") && (openid != null)) {
			LocationDao dao = new LocationDao();
			num = dao.selectReports(openid);
		}

		num = 2;
		switch (state) {
		case 1:
			String redurl = "epg/epg.html?openid=" + openid + "&waccountid=" + memCachedClientInstance.get("initialize_id");
			System.out.println("redurl+++" + redurl);
			response.sendRedirect(redurl);
			break;
		case 2:
			response.sendRedirect(url + "toupiao2/baoliao/xwxs.html?openId=" + openid);
			break;
		case 3:
			response.sendRedirect(url + "toupiao2/baoliao/zjxs.html?openId=" + openid);
			break;
		case 4:
			response.sendRedirect(url + "toupiao2/comment/huatilist.html?openId=" + openid);
			break;
		case 5:
			response.sendRedirect(url + "toupiao2/diaocha.html?openId=" + openid);
			break;
		case 6:
			response.sendRedirect(url + "toupiao2/jifen/index.html?openId=" + openid);
			break;
		case 7:
			response.sendRedirect(url + "toupiao2/falvzixun.html?zhutiId=21&openId=" + openid);
			break;
		case 8:
			response.sendRedirect(url + "toupiao2/live/btv/index.html?token=d8655228ec57a752&sig=3dd0dc1d316272536c96bab794c2bb7b&zhutiId=22&openId=" + openid);
			break;
		case 9:
			if (num == 0)
				response.sendRedirect(url + "weixinopen/epg/activity_error.html?openId=" + openid);
			else {
				response.sendRedirect(url + "weixinopen/epg/activity/index.html?qrcodeID=" + qrcodeID + "&openId=" + openid);
			}
			break;
		case 10:
			response.sendRedirect(url + "toupiao2/test/tuya/test/baiban.html?openId=" + openid);
			break;
		case 11:
			response.sendRedirect(url + "weixin-studio/question/dati.html?openId=" + openid);
		}
	}
}