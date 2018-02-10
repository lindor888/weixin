package com.ctvit.util;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountRelation;
import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Menu;
import com.ctvit.bean.Waccount;
import com.ctvit.bean.role;
import com.ctvit.service.AccountRelationServiceImpl;
import com.ctvit.service.LoginService;
import com.ctvit.service.MenuServiceImpl;
import com.ctvit.service.RoleServiceImpl;
import com.ctvit.service.WaccountServiceImpl;

public class SSOUtil extends SessionHandler{
	
	/**
	 * 单点登录session处理 只接受到用户id 通过用户id从库里查出用户信息放入session 
	 */
	@Override
	public void processSession(String accountId, String username,HttpServletRequest req,HttpServletResponse resp) {
		LoginService loginService = (LoginService)Const.WEB_APP_CONTEXT.getBean("loginService");
		Account account = loginService.ssoLogin(accountId);
		if(account!=null){
			req.getSession().setAttribute(Const.SESSION_USER, account);
			req.getSession().setAttribute("ip", req.getRemoteAddr());
			RoleServiceImpl roleService = (RoleServiceImpl)Const.WEB_APP_CONTEXT.getBean("RoleService");
			role role = roleService.getRoleById(account.getRoleId());
			String roleRights = role != null ? role.getRights() : "";
			// 避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
			req.getSession().setAttribute(Const.SESSION_ROLE_RIGHTS, roleRights); // 将角色权限存入session
			
			MenuServiceImpl menuService = (MenuServiceImpl)Const.WEB_APP_CONTEXT.getBean("menuService");
			List<Menu> menuList = menuService.listAllMenu();
			List<String> subList=new ArrayList<String>();
			if (Tools.notEmpty(roleRights)) {
				for (Menu menu : menuList) {
					menu.setHasMenu(RightsHelper.testRights(roleRights, menu.getMenuId()));
					if (menu.isHasMenu()) {
						List<Menu> subMenuList = menu.getSubMenu();
						for (Menu sub : subMenuList) {
							sub.setHasMenu(RightsHelper.testRights(roleRights, sub.getMenuId()));
							if(sub.isHasMenu()){
								subList.add(sub.getMenuUrl());
							}
						}
					}
				}
			}
			req.getSession().setAttribute("user", account);
			req.getSession().setAttribute("menuList", menuList);
			req.getSession().setAttribute("subList", subList);
			AccountRelation a=new AccountRelation();
			a.setAccountId(account.getAccountId());
			AccountRelationServiceImpl accountRelationService = (AccountRelationServiceImpl)Const.WEB_APP_CONTEXT.getBean("accountRelationService");
			List<AccountRelation> accountList=accountRelationService.selectByExample(a);
			List<Waccount> relationList=new ArrayList<Waccount>();
			WaccountServiceImpl waccountService = (WaccountServiceImpl)Const.WEB_APP_CONTEXT.getBean("waccountService");
			for(int i=0;i<accountList.size();i++){
				relationList.add(waccountService.selectByPrimaryKey(accountList.get(i).getWaccountId()));
			}
			req.getSession().setAttribute("Relation", relationList);
			WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
			AccountSessionBean accountSessionBean =  (AccountSessionBean)applicationContext.getBean("accountSessionBean");
			accountSessionBean.setAccount(account);
			accountSessionBean.setIp(req.getRemoteHost());
			
			//角色版本同步
			if(role != null) {
				Integer roleVersion = (Integer) MemcacheUtils.getMemCachedClientInstance().get(
						Const.ROLE_VERSION + account.getRoleId());
				if (roleVersion != null) {
					MemcacheUtils.getMemCachedClientInstance().set(Const.USER_ROLE_VERSION + account.getAccountId(),
							roleVersion);
				}
			}
		}
	}

	@Override
	public boolean ifHaveSession(HttpServletRequest req) {
		Account account = (Account)req.getSession().getAttribute(Const.SESSION_USER);
		if(account==null){
			return false;
		}
		return true;
	}
}
