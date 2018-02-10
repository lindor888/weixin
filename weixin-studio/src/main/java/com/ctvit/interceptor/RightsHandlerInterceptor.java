package com.ctvit.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Account;
import com.ctvit.util.Const;
import com.ctvit.util.MemcacheUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class RightsHandlerInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getServletPath();
		if (path.indexOf("logout") >= 0) {
			return invocation.invoke();
		}

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute(Const.SESSION_USER);
		// 权限是否被改动判断
		if (account == null) {
			if (path.equals("/login/loginLoginAction") || path.equals("/upload/uploadUploadAction") || path.equals("/interact/showInteract") || path.equals("/interact/showUInteract") || path.equals("/interact/showUChoujiangInteract") || path.equals("/interact/zhuanfaInteract") || path.equals("/interact/showUVoteInteract") || path.equals("/interact/showUYizhongjiangInteract") || path.equals("/receivewords/startReceiveWords") || path.equals("/interact/clearZhongjiangInteract")
					|| path.equals("/writewords/receiveWriteWordsList") || path.equals("/writewords/showWriteWordsList") || path.equals("/epg/toEpg") || path.equals("/epg/getDataEpg") || path.equals("/interact/pointLocationInteract") || path.equals("/question/getlistQuestion") || path.equals("/question/questionsubQuestion") || path.equals("/question/datisubQuestion") || path.equals("/interact/querymeetingInteract") || path.equals("/interact/getprizeallInteract")
					|| path.equals("/interact/getprizeoneInteract") || path.equals("/interact/showRossInteract") || path.equals("/interact/showLocationRossInteract") || path.equals("/interact/showNewsRossInteract") || path.equals("/hudong/addHudong") || path.equals("/hudong/addcomment")) {
				return invocation.invoke();
			} else {
				// 跳转到登陆页面，这样跳转是为了避免登录页面显示在 index.jsp的iframe中
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("<script>window.parent.location.href='" + ServletActionContext.getRequest().getContextPath() + "/index.jsp';</script>");
				return null;
			}
		}
		Integer roleV1 = (Integer) MemcacheUtils.getMemCachedClientInstance().get(Const.ROLE_VERSION + account.getRoleId());
		roleV1 = (roleV1 == null) ? 0 : roleV1;
		Integer roleV2 = (Integer) MemcacheUtils.getMemCachedClientInstance().get(Const.USER_ROLE_VERSION + account.getAccountId());
		roleV2 = (roleV2 == null) ? 0 : roleV2;

		// 角色权限被修改
		if (roleV1.intValue() == roleV2.intValue()) {
			return invocation.invoke();
		}
		List subList = (ArrayList) session.getAttribute("subList");
		session.invalidate();
		MemcacheUtils.getMemCachedClientInstance().delete(Const.USER_ROLE_VERSION + account.getAccountId());
		if (subList != null) {
			String ppp = Const.SESSION_MENU_RIGHTS + path;
			System.out.println(ppp);
			if (subList.contains(Const.SESSION_MENU_RIGHTS + path)) {
				return Action.LOGIN;
			}
		}
		return "noperm";
	}

}
