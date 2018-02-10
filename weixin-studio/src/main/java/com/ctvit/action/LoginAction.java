package com.ctvit.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountRelation;
import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Menu;
import com.ctvit.bean.Waccount;
import com.ctvit.bean.role;
import com.ctvit.service.AccountRelationServiceImpl;
import com.ctvit.service.AccountServiceImp;
import com.ctvit.service.LoginService;
import com.ctvit.service.MenuServiceImpl;
import com.ctvit.service.RoleServiceImpl;
import com.ctvit.service.WaccountServiceImpl;
import com.ctvit.util.Const;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.util.RightsHelper;
import com.ctvit.util.Tools;
import com.ctvit.util.encrypttools.CtyptoUtil;
import com.ctvit.util.encrypttools.SysConfig;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private LoginService loginService;
	private AccountServiceImp accountService;
	private RoleServiceImpl roleService;
	private MenuServiceImpl menuService;
	private AccountRelationServiceImpl accountRelationService;
	private WaccountServiceImpl waccountService;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private Map<String, Object> mapJson = new Hashtable<String, Object>();
	private Account account;
	private String waccountId;
	private static String md5key;// 密码钥匙
	private static final String MD5KEY_FOR_PASSWORD = "MD5KEY_FOR_PASSWORD";
	static {
		md5key = SysConfig.getSysParam(MD5KEY_FOR_PASSWORD);
		if (null == md5key || md5key.trim().length() == 0) {
			md5key = "ctvit.com.cn";
		}

	}

	public String getWaccountId() {
		return waccountId;
	}

	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId;
	}

	HttpServletRequest request = ServletActionContext.getRequest();

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public AccountRelationServiceImpl getAccountRelationService() {
		return accountRelationService;
	}

	public void setAccountRelationService(AccountRelationServiceImpl accountRelationService) {
		this.accountRelationService = accountRelationService;
	}

	public MenuServiceImpl getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuServiceImpl menuService) {
		this.menuService = menuService;
	}

	public String login() {
		if (account != null) {
			try {
				Account isUser = loginService.selectUserbyName(account);
				if(isUser!=null){
					if(isUser.getState()==1){
						mapJson.put("msg", "isExits");
						return "login";
					}
					//演播室版本去掉外部内部的区分
					/*if("内部".equals(isUser.getIsinside())){
						mapJson.put("msg", "inuser");
						return "login";
					}*/
				}
				account.setPassword(CtyptoUtil.EncryptString(account.getPassword(), md5key));
				Account user = loginService.login(account);
				if (user != null) {
					if (user.getState() != 0) {
						mapJson.put("msg", "isExits");
						return "login";
					}
					session.setAttribute(Const.SESSION_USER, user);
					session.setAttribute("ip", request.getRemoteAddr());
					// session.removeAttribute(Const.SESSION_SECURITY_CODE);
					role role = roleService.getRoleById(user.getRoleId());
					String roleRights = role != null ? role.getRights() : "";
					// 避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
					session.setAttribute(Const.SESSION_ROLE_RIGHTS, roleRights); // 将角色权限存入session

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
					session.setAttribute("user", user);
					session.setAttribute("menuList", menuList);
					session.setAttribute("subList", subList);
					AccountRelation a = new AccountRelation();
					a.setAccountId(user.getAccountId());
					List<AccountRelation> accountList = accountRelationService.selectByExample(a);
					List<Waccount> relationList = new ArrayList<Waccount>();
					for (int i = 0; i < accountList.size(); i++) {
						relationList.add(waccountService.selectByPrimaryKey(accountList.get(i).getWaccountId()));
					}
					session.setAttribute("Relation", relationList);
					WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
					AccountSessionBean accountSessionBean = (AccountSessionBean) applicationContext
							.getBean("accountSessionBean");
					accountSessionBean.setAccount(user);
					accountSessionBean.setIp(request.getRemoteHost());

					//角色版本同步
					if(role != null) {
						Integer roleVersion = (Integer) MemcacheUtils.getMemCachedClientInstance().get(
								Const.ROLE_VERSION + user.getRoleId());
						if (roleVersion != null) {
							MemcacheUtils.getMemCachedClientInstance().set(Const.USER_ROLE_VERSION + user.getAccountId(),
									roleVersion);
						}
					}
					
					mapJson.put("msg", "success");
				} else {
					mapJson.put("msg", "fail");
					return "login";
				}
			} catch (Exception e) {
				e.printStackTrace();
				mapJson.put("msg", "系统错误，请联系系统管理员");
				return "login";
			}
		} else {
			mapJson.put("msg", "fail");
			return "login";
		}
		return "index";
	}

	public void addWaccountID() {
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		AccountSessionBean accountSessionBean = (AccountSessionBean) applicationContext.getBean("accountSessionBean");
		accountSessionBean.setWaccountId(waccountId);
		session.setAttribute("waccountId", waccountId);
	}

	public String checkwaccount() {

		Account account = (Account) session.getAttribute("user");
		AccountRelation a = new AccountRelation();
		a.setAccountId(account.getAccountId());
		List<AccountRelation> accountList = accountRelationService.selectByExample(a);
		List<Waccount> relationList = new ArrayList<Waccount>();
		for (int i = 0; i < accountList.size(); i++) {
			relationList.add(waccountService.selectByPrimaryKey(accountList.get(i).getWaccountId()));
		}
		mapJson.put("relation", relationList);
		
		/*System.out.println("===============================================");
		List<AccountRelation> relationlist = accountRelationService.selectBywaccount(account.getAccountId());
		mapJson.put("relation", relationlist);*/

		return "succesrelation";
	}

	public String logout() {
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		AccountSessionBean accountSessionBean = (AccountSessionBean) applicationContext.getBean("accountSessionBean");
		accountSessionBean.setAccount(null);
		accountSessionBean.setIp(null);
		session.invalidate();
		return "login";
	}

	public AccountServiceImp getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountServiceImp accountService) {
		this.accountService = accountService;
	}

	public void prepare() throws Exception {

	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Map<String, Object> getMapJson() {
		return mapJson;
	}

	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}

	public WaccountServiceImpl getWaccountService() {
		return waccountService;
	}

	public void setWaccountService(WaccountServiceImpl waccountService) {
		this.waccountService = waccountService;
	}

	/**
	 * @return the roleService
	 */
	public RoleServiceImpl getRoleService() {
		return roleService;
	}

	/**
	 * @param roleService the roleService to set
	 */
	public void setRoleService(RoleServiceImpl roleService) {
		this.roleService = roleService;
	}

}
