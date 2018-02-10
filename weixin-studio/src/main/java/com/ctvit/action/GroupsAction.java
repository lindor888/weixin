package com.ctvit.action;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Followers;
import com.ctvit.bean.FollowersExample;
import com.ctvit.bean.Groups;
import com.ctvit.bean.GroupsExample;
import com.ctvit.bean.Waccount;
import com.ctvit.service.GroupsServiceImp;
import com.ctvit.service.WaccountServiceImpl;
import com.ctvit.util.ActionUtil;
import com.ctvit.util.website.ManagerGroups;
import com.opensymphony.xwork2.ActionSupport;

public class GroupsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(FollowersAction.class);
	private GroupsServiceImp groupsService;//账户服务类
	private ManagerGroups managerGroups;
	private Map<String, Object> queryJson;
	private HttpSession session=ServletActionContext.getRequest().getSession();
	HttpServletResponse response = ServletActionContext.getResponse();
	private WaccountServiceImpl	waccountService;
	private Map<String, Object> mapJson;
	Groups groups = new Groups();
	
	public Map<String, Object> getMapJson() {
		return mapJson;
	}
	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}
	public String getWaccountId() {
		return waccountId;
	}
	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId;
	}
	public GroupsExample getQueryData() {
		return queryData;
	}
	public void setQueryData(GroupsExample queryData) {
		this.queryData = queryData;
	}

	public String waccountId="";
	
	public Logger getLog() {
		return log;
	}
	public void setLog(Logger log) {
		this.log = log;
	}
	public GroupsServiceImp getGroupsService() {
		return groupsService;
	}
	public void setGroupsService(GroupsServiceImp groupsService) {
		this.groupsService = groupsService;
	}
	public Map<String, Object> getQueryJson() {
		return queryJson;
	}
	public void setQueryJson(Map<String, Object> queryJson) {
		this.queryJson = queryJson;
	}
	public WaccountServiceImpl getWaccountService() {
		return waccountService;
	}
	public void setWaccountService(WaccountServiceImpl waccountService) {
		this.waccountService = waccountService;
	}
	public ManagerGroups getManagerGroups() {
		return managerGroups;
	}
	public void setManagerGroups(ManagerGroups managerGroups) {
		this.managerGroups = managerGroups;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	private GroupsExample queryData = new GroupsExample();
	
	public String index() {
		System.out.println("+++++++++++++++++++++++++++++++seeion "+((Account)session.getAttribute("user")).getAccountId());
		System.out.println("waccountId: "+ActionUtil.findWaccountId());
		//String waccountId = this.findWaccountId();
		return "index";
	}
	
	public String selectAll(){
		mapJson = new Hashtable<String, Object>();
//		queryData.setPage(page);
//		queryData.setRows(rows);
	//	queryData.setWaccountid(this.findWaccountId());
		
		queryData.setWaccountId("Wacc1403147211500101");
		int total = groupsService.countByExample(queryData);
		System.out.println("=========================total> "+queryData.getWaccountId());
		List<Groups> l2 = groupsService.selectByExample(queryData); 
		
		mapJson.put("total", total);
		mapJson.put("rows", l2);
		return "selectAll";
	}
	
	public Groups getGroups() {
		return groups;
	}
	public void setGroups(Groups groups) {
		this.groups = groups;
	}
	public void save(){
		boolean flag = true;
		PrintWriter out = null;
		managerGroups = new ManagerGroups();
		try {
			out = response.getWriter();
			groups.setWaccountId(ActionUtil.findWaccountId());
			Waccount waccount=waccountService.selectByPrimaryKey(ActionUtil.findWaccountId());
			if (groups.getGroupsId() != null) {
				flag = groupsService.updateByPrimaryKey(groups);
			} else {    
			    String appid = waccount.getAppId();
			    String Appsecret = waccount.getAppSecret();
			    System.out.println("=============================> "+groups.getName());
				String creatResult = managerGroups.createGroups(groups.getName(), appid, Appsecret);
				JSONObject obj = JSONObject.fromObject(creatResult);
				if(obj.get("errcode") != null){
					System.out.println(obj.getString("errmsg"));
					throw new Exception(obj.getString("errmsg"));
				}else{
					String getGroupsStr = obj.getString("group");
					JSONObject getIdOb = JSONObject.fromObject(getGroupsStr);
					String groupsId = getIdOb.getString("id");
					groups.setGroupsId(groupsId);
					groups.setName(groups.getName());
					groups.setCount(0);
					flag = groupsService.insert(groups);
				}
				//
			}
			if (flag) {
				out.write("success");
			} else {
				out.write("failed");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
