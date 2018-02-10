package com.ctvit.epg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ctvit.dao.LocationDao;
import com.ctvit.util.ConfKit;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.weixin.ScopeUtils;
import com.danga.MemCached.MemCachedClient;

/**
 * 通过静默授权，获取到用户的openid,并把openid 拼接到epg.html 的链接后
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年9月2日 上午10:13:18
 */

public class ToepgServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(ToepgServlet.class);
	private static final long serialVersionUID = 1L;
	private static final EgpRunnableAction egpRunnableAction = new EgpRunnableAction();;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		if (!egpRunnableAction.isAlive()) {
			egpRunnableAction.start();
		}
		String code = request.getParameter("code");
		logger.info("code+" + code);
		String url = ConfKit.get("openurl");
		String param = request.getParameter("state");
		String[] str = param.split("-");
		int state = Integer.parseInt(str[0]);
		logger.info("state+" + str[0]);
		logger.info("appid+" + str[1]);
		String qrcodeID = request.getParameter("qrcodeID");
		if (qrcodeID == "" || qrcodeID == null) {
			qrcodeID = (String) memCachedClientInstance.get("qrcodeID");
		}
		String openid = "";

		if (StringUtils.isNotEmpty(code)) {
			openid = new ScopeUtils().getOpenid(code, str[1]);
		}
		int num = 0;
		if (openid != "" && openid != null) {

			LocationDao dao = new LocationDao();
			num = dao.selectReports(openid);
		}
		switch (state) {
		case 1:
			// epg
			String redurl = "epg/epg.html?openid=" + openid + "&waccountid=" + memCachedClientInstance.get("initialize_id");
			System.out.println("redurl+++" + redurl);
			response.sendRedirect(redurl);
			break;
		case 2:
			// 新闻线索
			response.sendRedirect(url + "toupiao2/baoliao/xwxs.html?openId=" + openid);
			break;
		case 3:
			// 征询线索
			response.sendRedirect(url + "toupiao2/baoliao/zjxs.html?openId=" + openid);
			break;
		case 4:
			// 话题广场
			response.sendRedirect(url + "toupiao2/comment/huatilist.html?openId=" + openid);
			break;
		case 5:
			// 投票
			response.sendRedirect(url + "toupiao2/diaocha.html?openId=" + openid);
			break;
		case 6:
			// 个人中心
			response.sendRedirect(url + "toupiao2/jifen/index.html?openId=" + openid);
			break;
		case 7:
			// 栏目建议
			response.sendRedirect(url + "toupiao2/falvzixun.html?zhutiId=21&openId=" + openid);
			break;
		case 8:
			// 边看边聊
			response.sendRedirect(url + "toupiao2/live/btv/index.html?token=d8655228ec57a752&sig=3dd0dc1d316272536c96bab794c2bb7b&zhutiId=22&openId=" + openid);
			break;
		// 转盘抽奖
		case 9:
			// response.sendRedirect(url + "weixinopen/epg/activity/index.html?qrcodeID=" + qrcodeID + "&openId=" + openid);
			if (num == 0) {
				response.sendRedirect(url + "weixinopen/epg/activity_error.html?openId=" + openid);
			} else {
				response.sendRedirect(url + "weixinopen/epg/activity/index.html?qrcodeID=" + qrcodeID + "&openId=" + openid);
			}
			break;
		// 写字活动
		case 10:
			response.sendRedirect(url + "toupiao2/test/tuya/test/baiban.html?openId=" + openid);
			break;
		// 答题
		case 11:
			response.sendRedirect(url + "weixin-studio/question/dati.html?openId=" + openid);
			break;
		}

		// MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
		// response.sendRedirect("epg/epg.html?openid=" + openid + "&waccountid=" + memCachedClientInstance.get("initialize_id", 1));

	}
}
