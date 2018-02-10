package com.ctvit.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.Depart;
import com.ctvit.bean.DepartRelation;
import com.ctvit.bean.Logger;
import com.ctvit.bean.role;
import com.ctvit.dao.QueryDataBean;
import com.ctvit.service.AccountRelationServiceImpl;
import com.ctvit.service.AccountServiceImp;
import com.ctvit.service.DepartRelationServiceImpl;
import com.ctvit.service.DepartServiceImpl;
import com.ctvit.service.LoggerServiceImpl;
import com.ctvit.service.RoleServiceImpl;
import com.ctvit.util.Const;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.util.ResourceLoader;
import com.ctvit.util.encrypttools.CtyptoUtil;
import com.ctvit.util.encrypttools.SysConfig;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 权限——用户管理类
 * 
 * @author haotp
 * 
 */
public class AccountAction extends ActionSupport {
	private int page;
	private int rows;
	private String method;
	private boolean message;
	private String deparment;
	private String newPassword;
	private String errorMes;// 返回错误信息
	private QueryDataBean queryData;
	private static String md5key;// 密码钥匙
	private static String sysDefaultPasswd;// 默认密码
	private List<role> rolelist;
	private List<Depart> departlist;
	 List<DepartRelation>	DepartRelationlist ;
	private Account account;
	private AccountServiceImp accountService;
	private RoleServiceImpl RoleService;
	private LoggerServiceImpl loggerService;
	private Logger log = new Logger();
	private AccountRelationServiceImpl accountRelationService;
	private DepartRelationServiceImpl departRelationService;
	private DepartServiceImpl departService;
	private Map<String, Object> mapJson = new Hashtable<String, Object>();
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private static final String MD5KEY_FOR_PASSWORD = "MD5KEY_FOR_PASSWORD";
	private static final String DEFAULT_USER_PASSWORD = "DEFAULT_USER_PASSWORD";
	private String chooseUrl;
	private JSON retMap;

	static {
		md5key = SysConfig.getSysParam(MD5KEY_FOR_PASSWORD);
		if (null == md5key || md5key.trim().length() == 0) {
			md5key = "ctvit.com.cn";
		}
		sysDefaultPasswd = SysConfig.getSysParam(DEFAULT_USER_PASSWORD);
		if (null == sysDefaultPasswd || sysDefaultPasswd.trim().length() == 0) {
			sysDefaultPasswd = "Rf67qa_89Eetjy02";
		}
	}

	public String input() {
		String remsg = "";
		log.setType("用户管理");

		log.setValue(((Account) session.getAttribute("user")).getAccountId());

		try {
			if (("update").equals(method)) {
				accountService.updateByPrimaryKeySelective(account); // 编辑用户
				
				// 权限变更
				Integer roleV = (Integer) MemcacheUtils.getMemCachedClientInstance().get(
						Const.USER_ROLE_VERSION + account.getAccountId());
				roleV = (roleV == null) ? 0 : roleV;
				MemcacheUtils.getMemCachedClientInstance().set(Const.USER_ROLE_VERSION + account.getAccountId(),
						(roleV - 1));

				log.setAct("update");
				return "success";
			} else if (("add").equals(method)) {
				if(account.getAccountId()==null||"".equals(account.getAccountId())){
					// 初始密码11111
					account.setPassword(CtyptoUtil.EncryptString(sysDefaultPasswd, md5key));// 创建用户
				}
				accountService.insert(account);
				return "success";
			} else if (("delete").equals(method)) {

				
				// 删除微信公共账号关系表
//				AccountRelation accountrelation = new AccountRelation();
//				accountrelation.setAccountId(account.getAccountId());
//				accountRelationService.deleteByaccount(accountrelation);
				// 删除用户只更改状态为‘停用’
				// Byte i=1;
				// account.setState(i);
				accountService.updateByPrimaryKeySelective(account);
				log.setAct("delete");
				remsg = "true";
				mapJson.put("message", remsg);
				
				//权限变更
				if (account.getState() == 1) {
					MemcacheUtils.getMemCachedClientInstance().set(Const.USER_ROLE_VERSION + account.getAccountId(), -2);
				}
				
				return "ok";
			} else if (("revisepwdbyuser").equals(method)) {

				if (newPassword != null && newPassword.length() > 0) {

					account.setPassword(CtyptoUtil.EncryptString(newPassword, md5key));
				} else {
					// 修正：没有输入新密码时，重新将已转换为明码的密码转换为密文密码
					account.setPassword(CtyptoUtil.EncryptString(account.getPassword(), md5key));
				}
				log.setAct("resetpassword");
				accountService.updateByPrimaryKeySelective(account);// 密码修改
				return "revisepwdbyuser";
			}

			errorMes = "操作失败！";
			return "filerror";
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().indexOf("tab_account_login_unique") > -1) {
				errorMes = "该用户名已存在！";
			}
			return "filerror";
		} finally {
			loggerService.saveLog(log);
		}

	}
//	
//	public String deleteacctount(){
//		try{
//		 accountService.deleteByPrimaryKey(account.getAccountId());//删除用户
//		 AccountRelation accountrelation=new AccountRelation();
//	     accountrelation.setAccountId(account.getAccountId().trim());
//		 accountRelationService.deleteByaccount(accountrelation);//删除公共账号关系
//		                                                           
//		 mapJson.put("message", "true");
//		 }catch(Exception e){
//			 e.printStackTrace();
//			 mapJson.put("message", "error");
//		 }
//		return "deletesuccess";
//	}

	/**
	 * 管理首页
	 * @return
	 */
	public String selectrole() {
		rolelist = RoleService.listAllRoles();
		role e=new role();
		e.setRoleId("0");
		e.setRoleName("无");
		rolelist.add(e);
		departlist = departService.listAllDepart();
		if(null!=departlist&&departlist.size()>0){
		for (int i = 0; i < departlist.size(); i++) {
			departlist.get(i).setDepartName(
					departlist.get(i).getDepartName() + "(" + departlist.get(i).getInorout() + ")");
		}
		}	
		return "selectrole";
	}

	public String selectAll() throws Exception {

		queryData = new QueryDataBean();
		queryData.setPagination(false);
		int total = accountService.countByExample(queryData);
		queryData.setPagination(true);
		queryData.setPage(page);
		queryData.setRows(rows);
		List<Account> l2 = accountService.selectByExample(queryData);
		mapJson.put("total", total);
		mapJson.put("rows", l2);

		return "selectAll";
	}

	public String selectAllBystate() throws Exception {

		queryData.setState(Byte.valueOf(queryData.getType()));
		queryData.setPagination(false);
		int total = accountService.countByExample(queryData);
		queryData.setPagination(true);
		queryData.setPage(page);
		queryData.setRows(rows);
		List<Account> l2 = accountService.selectByExample(queryData);
		mapJson.put("total", total);
		mapJson.put("rows", l2);
		return "selectAll";
	}

	// 页面跳转
	public String findurl() {
		String remsg = "";
		try {
			departlist = departService.listAllDepart();
			for (int i = 0; i < departlist.size(); i++) {
				departlist.get(i).setDepartName(
						departlist.get(i).getDepartName() + "(" + departlist.get(i).getInorout() + ")");
			}

			if ("add".equals(method)) {

				// mapJson.put("rows", rolelist); // 添加用户-
				mapJson.put("departlist", departlist);

				remsg = "adduser";
			} else if ("update".equals(method)) {
				account = accountService.selectByPrimaryKey(account.getAccountId().trim());
				if(null!=account.getDepartment())
				DepartRelationlist = departRelationService.selectByDepartment(account.getDepartment().toString());

				mapJson.put("rows", account);
				remsg = "updateuser";// 编辑用户

			} else if ("resetpassword".equals(method)) {
				remsg = "resetpassword";// 密码重置
			} else if ("resetpwdbyuser".equals(method)) {
				// 密码修改
				account = (Account) session.getAttribute("user");
				account = accountService.selectByPrimaryKey(account.getAccountId());
				account.setPassword(CtyptoUtil.DecryptString(account.getPassword(), md5key));
				remsg = "resetpwdbyuser";
			} else if ("allotrelation".equals(method)) {
				remsg = "allotrelation";
			}
			return remsg;
		} catch (Exception e) {
			e.printStackTrace();
			errorMes = "操作失败！";
			return "filerror";
		}

	}

	public String checkdepartment() {

		String remsg = "";
		// 校验是否是公司内部员工?
		account = accountService.checkdepartment(((Account) session.getAttribute("user")).getAccountId());
		if (null != account) {
			if (account.getIsinside().equals("外部")) {
				remsg = "TRUE";
			} else {
				remsg = "ERROR";
			}
		}

		mapJson.put("message", remsg);
		return "checkdepartment";
	}

	// 用户是否已经存在
	public String checkUsername() {

		Account userInf = accountService.selectCheckUserNameByPrimaryKey(account);
		if (userInf == null) {
			message = true;
		} else {
			message = false;
		}
		return "checkUsername";
	}

	// 更改系统默认密码
	public String rePasswordById() {
		String resultStr = "";
		try {
			if (newPassword != null) {
				account.setPassword(CtyptoUtil.EncryptString(sysDefaultPasswd, md5key));
				// account.setPassword(CtyptoUtil.EncryptString("111111",
				// md5key));

				accountService.updateByPrimaryKeySelective(account);
				resultStr = "OK";
			} else {
				resultStr = "ERROR";
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultStr = "ERROR";
		}
		mapJson.put("message", resultStr);
		return "rePasswordById";
	}
	
	/**
	 * 获取挑选的用户数据
	 * @return
	 */
	public String choose(){
		retMap = getUserInfo();
		return "choose";
	}
	
	private JSON getUserInfo(){
		URL url = null;
		HttpURLConnection httpConn = null;
		InputStream in = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		String urlStr = ResourceLoader.getInstance().getConfig().getProperty("userInfoAddress");
		try {
			url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("POST");

			in = httpConn.getInputStream();
			br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			for (String str = br.readLine(); str != null; str = br.readLine()) {
				sb.append(str);
			}
			JSONObject json = JSONObject.fromObject(sb.toString());
			JSONArray array = json.getJSONArray("rows");
			int total = json.getInt("total");
			
			int start = (page -1) * rows;
			int end = (start + rows) > total ? total:(start + rows);
			
			List<?> list = array.subList(start, end);
			json.put("rows", list);
			
			return (JSON) json;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				httpConn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
		
	}

	public QueryDataBean getQueryData() {
		return queryData;
	}

	public void setQueryData(QueryDataBean queryData) {
		this.queryData = queryData;
	}

	public AccountServiceImp getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountServiceImp accountService) {
		this.accountService = accountService;
	}

	public Map<String, Object> getMapJson() {
		return mapJson;
	}

	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isMessage() {
		return message;
	}

	public void setMessage(boolean message) {
		this.message = message;
	}

	public static String getDefaultUserPassword() {
		return DEFAULT_USER_PASSWORD;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public RoleServiceImpl getRoleService() {
		return RoleService;
	}

	public void setRoleService(RoleServiceImpl roleService) {
		RoleService = roleService;
	}

	public List<role> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<role> rolelist) {
		this.rolelist = rolelist;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public LoggerServiceImpl getLoggerService() {
		return loggerService;
	}

	public void setLoggerService(LoggerServiceImpl loggerService) {
		this.loggerService = loggerService;
	}

	public AccountRelationServiceImpl getAccountRelationService() {
		return accountRelationService;
	}

	public void setAccountRelationService(AccountRelationServiceImpl accountRelationService) {
		this.accountRelationService = accountRelationService;
	}

	public String getErrorMes() {
		return errorMes;
	}

	public void setErrorMes(String errorMes) {
		this.errorMes = errorMes;
	}

	public List<Depart> getDepartlist() {
		return departlist;
	}

	public void setDepartlist(List<Depart> departlist) {
		this.departlist = departlist;
	}

	public DepartServiceImpl getDepartService() {
		return departService;
	}

	public void setDepartService(DepartServiceImpl departService) {
		this.departService = departService;
	}

	public DepartRelationServiceImpl getDepartRelationService() {
		return departRelationService;
	}

	public void setDepartRelationService(
			DepartRelationServiceImpl departRelationService) {
		this.departRelationService = departRelationService;
	}

	public List<DepartRelation> getDepartRelationlist() {
		return DepartRelationlist;
	}

	public void setDepartRelationlist(List<DepartRelation> departRelationlist) {
		DepartRelationlist = departRelationlist;
	}

	public String getChooseUrl() {
		return chooseUrl;
	}

	public void setChooseUrl(String chooseUrl) {
		this.chooseUrl = chooseUrl;
	}

	public JSON getRetMap() {
		return retMap;
	}

	public void setRetMap(JSON retMap) {
		this.retMap = retMap;
	}
	
	
	

}
