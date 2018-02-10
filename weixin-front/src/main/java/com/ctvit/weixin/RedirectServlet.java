package com.ctvit.weixin;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctvit.bean.ViewMenu;
import com.ctvit.dao.MenusDao;
import com.ctvit.util.HttpKit;
import com.ctvit.util.MemcacheUtils;

public class RedirectServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5721404543435516300L;
	
	protected static Logger log = LoggerFactory.getLogger(RedirectServlet.class);
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		log.info(code);
		log.info(state);
		//通过state从菜单表中查询出公众号id
		MenusDao menusDao = new MenusDao();
		ViewMenu viewMenu = menusDao.selectViewMenu(state);
		String appid = (String)MemcacheUtils.getMemCachedClientInstance().get(viewMenu.getWaccountId()+"_appid");
		String secret = (String)MemcacheUtils.getMemCachedClientInstance().get(viewMenu.getWaccountId()+"_secret");
		
		//通过code获取accesstoken
		String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ appid +
								"&secret="+ secret +
								"&code="+code+"&grant_type=authorization_code";
		String openId = "";
		try {
			String content = HttpKit.get(accessTokenUrl);
			log.info(content);
			if(!"".equals(content)){
				JSONObject json = JSONObject.fromObject(content);
				openId = json.getString("openid");
				log.info(openId);
			}
			resp.sendRedirect(viewMenu.getUrl()+"?openId="+openId);
			
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
