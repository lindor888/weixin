package com.ctvit.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctvit.bean.Account;
import com.ctvit.bean.Followers;
import com.ctvit.bean.FollowersExample;
import com.ctvit.bean.FollowersExt;
import com.ctvit.bean.GroupsBean;
import com.ctvit.bean.Interact;
import com.ctvit.bean.ReplayBean;
import com.ctvit.bean.TuwenPushBean;
import com.ctvit.bean.Waccount;
import com.ctvit.dao.FollowerDao;
import com.ctvit.service.FollowersServiceImp;
import com.ctvit.service.WaccountServiceImpl;
import com.ctvit.util.ActionUtil;
import com.ctvit.util.website.ManagerFollowers;
import com.ctvit.util.website.ManagerMenu;
import com.opensymphony.xwork2.ActionSupport;

public class FollowersAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(FollowersAction.class);
	private FollowersServiceImp followersService;// 账户服务类
	private ManagerFollowers managerFollowers;
	private Map<String, Object> queryJson;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private WaccountServiceImpl waccountService;
	private Followers bean;
	private ReplayBean replayBean;
	private FollowersExt followersExt;
	private GroupsBean groupsBean;
	private TuwenPushBean image;
	public String waccountId = "";
	private String openId;
	private String groupsId;
	private String content;
	private String tname;
	private int currentPage;

	public WaccountServiceImpl getWaccountService() {
		return waccountService;
	}

	public void setWaccountService(WaccountServiceImpl waccountService) {
		this.waccountService = waccountService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	private int page;

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

	public FollowersExample getQueryData() {
		return queryData;
	}

	public void setQueryData(FollowersExample queryData) {
		this.queryData = queryData;
	}

	private int rows;
	private FollowersExample queryData = new FollowersExample();
	private Map<String, Object> mapJson;

	public Map<String, Object> getMapJson() {
		return mapJson;
	}

	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}

	public Map<String, Object> getQueryJson() {
		return queryJson;
	}

	public void setQueryJson(Map<String, Object> queryJson) {
		this.queryJson = queryJson;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public FollowersServiceImp getFollowersService() {
		return followersService;
	}

	public void setFollowersService(FollowersServiceImp followersService) {
		this.followersService = followersService;
	}

	public ManagerFollowers getManagerFollowers() {
		return managerFollowers;
	}

	public void setManagerFollowers(ManagerFollowers managerFollowers) {
		this.managerFollowers = managerFollowers;
	}

	public String index() {
		System.out.println("+++++++++++++++++++++++++++++++seeion " + ((Account) session.getAttribute("user")).getAccountId());
		System.out.println("waccountId: " + ActionUtil.findWaccountId());
		waccountId = ActionUtil.findWaccountId();
		return "index";
	}

	public String selectAll() throws Exception {
		mapJson = new Hashtable<String, Object>();
		// queryData = new FollowersExample();
		queryData.setPage(page);
		queryData.setRows(rows);
		// queryData.setNickname("刘");
		System.out.println(queryData.getNickname());
		queryData.setPagination(true);
		if (queryData.getSex() != null) {
			if (queryData.getSex().equals("0")) {
				queryData.setSex("男");
			} else if (queryData.getSex().equals("1")) {
				queryData.setSex("女");
			} else if (queryData.getSex().equals("100")) {
				queryData.setSex("");
			}
		} else {
			queryData.setSex("");
		}
		queryData.setWaccountId(ActionUtil.findWaccountId());
		int total = followersService.countByExample(queryData);

		// queryData.setPagination(true);
		// queryData.setPage(page);
		// queryData.setRows(rows);
		System.out.println("=========================total> " + total);

		// System.out.println(queryData.getSex());
		List<Followers> l2 = followersService.selectByExample(queryData);
		// System.out.println("=========l2> "+l2.size());
		mapJson.put("total", total);
		mapJson.put("rows", l2);

		return "selectAll";
	}

	public String updateAll() throws Exception {
		mapJson = new Hashtable<String, Object>();
		queryData = new FollowersExample();
		waccountId = ActionUtil.findWaccountId();
		Waccount waccount = waccountService.selectByPrimaryKey(waccountId);
		// String appid = "wx24f8d7028978ccc9";
		// String Appsecret = "74b23ad9b868f888017820860f6b2efb";

		String appid = "";
		String Appsecret = "";
		if (!waccount.getAppId().equals("") || !waccount.getAppSecret().equals("")) {
			appid = waccount.getAppId();
			Appsecret = waccount.getAppSecret();
			ManagerMenu manamge = new ManagerMenu();
			// ManagerFollowers mangerFollowers = new ManagerFollowers();
			queryData.setWaccountId(waccountId);
			// 清空库表重新录入
			followersService.deleteWaccountId(waccountId);
			String token = manamge.getaccess_token(appid, Appsecret);
			JSONObject value = ManagerFollowers.getFollwersList(token, null);
			// 关注这数量
			// String count = value.getString("count");
			// 关注者list
			String data = value.getString("data");
			JSONObject obj = JSONObject.parseObject(data);
			JSONArray s = obj.getJSONArray("openid");
			String listStr = obj.getString("openid");
			// System.out.println(listStr);
			String[] listArray = listStr.split(",");
			List<String> vallist = Arrays.asList(listArray);
			int i = 0;
			// FollowerDao dao = new FollowerDao();
			// String id="oucWDjr1YsNuvqIhZxIWV_50XyhQ";
			// Followers s1 = ManagerFollowers.getUserInfo(id);
			// followersService.insert(s1);
			for (String val : vallist) {
				i++;
				val = val.replace("[", "");
				val = val.replace("\"", "");
				val = val.replace("]", "");
				System.out.println("=================>" + val + "<===============================");
				Followers s1 = ManagerFollowers.getUserInfo(val);
				// System.out.println("val = " + s1.getNickname());
				Followers s2 = followersService.selectByPrimaryKey(s1.getOpenid());
				if (s2 != null) {
					System.out.println(s1.getNickname() + "已存在");
					// mapJson.put("message", "ERROR");
				} else {
					try {
						s1.setWaccountId(waccount.getWaccountId());
						followersService.insert(s1);
						System.out.println(s1.getNickname() + "入库");
					} catch (Exception e) {
						System.out.println(s1.getNickname() + "连接断开");
						mapJson.put("message", "ERROR");
					}
				}
				mapJson.put("message", "OK");
				System.out.println("i = " + i);
			}
		} else {
			mapJson.put("message", "FAIL");
		}

		return "updateAll";

	}

	/**
	 * 查询自己添加的用户
	 * 
	 * @return
	 */
	public String selectMy() {
		mapJson = new Hashtable<String, Object>();
		try {
			List<Followers> list = followersService.selectMy(ActionUtil.findWaccountId());
			mapJson.put("rows", list);

		} catch (Exception e) {
			log.error("", e);
		}
		return "selectMy";
	}

	/**
	 * 按主键查询
	 * 
	 * @return
	 */
	public String selectByKey() {
		mapJson = new Hashtable<String, Object>();
		try {
			bean = followersService.selectByPrimaryKey(bean.getOpenid());
			mapJson.put("rows", bean);
		} catch (Exception e) {
			log.error("", e);
		}
		return "selectByKey";
	}

	public String deleteAll() throws Exception {
		return "deleteAll";
	}

	/**
	 * 网友用户分组查询
	 * 
	 * @return
	 */
	public String netInit() {

		mapJson = new Hashtable<String, Object>();
		try {
			// Followers bean
			int pageSize = 20;// 每页显示条数
			followersExt.setGroupsId(Integer.parseInt(groupsId));
			followersExt.setWaccountId(ActionUtil.findWaccountId());
			int totalSize = followersService.usercount(followersExt);// 总记录数
			int totalPageNum = totalSize / pageSize;// 总页数
			if (totalSize % pageSize > 0) {
				totalPageNum = totalSize / pageSize + 1;
			}
			int rowStart = (currentPage - 1) * pageSize;
			followersExt.setRowStart(rowStart);
			followersExt.setPageSize(pageSize - 1);
			List<FollowersExt> followers = followersService.netInit(followersExt);

			mapJson.put("rows", followers);
			mapJson.put("msg", "success");
			mapJson.put("totalPageNum", totalPageNum);
		} catch (Exception e) {
			mapJson.put("msg", "error");
			log.error("", e);
		}
		return "netfriend";
	}

	/**
	 * 网友用户分组管理
	 * 
	 * @return
	 */
	public String netfriend() {

		mapJson = new Hashtable<String, Object>();
		try {
			int pageSize = 20;// 每页显示条数
			Map<String, Object> queryJson = new HashMap<String, Object>();
			queryJson.put("waccountid", ActionUtil.findWaccountId());
			// Followers bean
			followersExt.setGroupsId(1);
			// followersExt.setWaccountId(ActionUtil.findWaccountId());
			followersExt.setWaccountId(ActionUtil.findWaccountId());
			int totalSize = followersService.usercount(followersExt);// 总记录数
			int totalPageNum = totalSize / pageSize;// 总页数
			if (totalSize % pageSize > 0) {
				totalPageNum = totalSize / pageSize + 1;
			}
			int rowStart = (currentPage - 1) * pageSize;
			followersExt.setRowStart(rowStart);
			followersExt.setPageSize(pageSize - 1);
			List<ReplayBean> replayList = followersService.selectReplay(queryJson);
			List<FollowersExt> followers = followersService.netfriend(followersExt);
			for (int i = 0; i < followers.size(); i++) {
				List<ReplayBean> list = new ArrayList<ReplayBean>();
				for (int j = 0; j < replayList.size(); j++) {

					if (followers.get(i).getOpenId().equals(replayList.get(j).getOpenid())) {

						list.add(replayList.get(j));

					}
					followers.get(i).setReplayBean(list);
				}
			}
			mapJson.put("rows", followers);
			mapJson.put("msg", "success");
			mapJson.put("totalPageNum", totalPageNum);
		} catch (Exception e) {
			mapJson.put("msg", "error");
			log.error("", e);
		}
		return "netfriend";
	}

	/**
	 * 查询所有组
	 * 
	 * @return
	 */
	public String groups() {

		mapJson = new Hashtable<String, Object>();
		try {
			// Followers bean
			Followers fl = new Followers();
			fl.setWaccountId(ActionUtil.findWaccountId());
			fl.setGroupsId("0");
			List<GroupsBean> groups = followersService.findgroups(groupsBean);
			for (int i = 0; i < groups.size(); i++) {
				FollowersExt followersExt = new FollowersExt();
				followersExt.setWaccountId(ActionUtil.findWaccountId());
				followersExt.setGroupsId(groups.get(i).getGroupsId());
				int count = followersService.usercount(followersExt);
				groups.get(i).setCount(count);
			}
			int all = followersService.alluser(fl);
			int blacklist = followersService.blacklist(fl);
			mapJson.put("rows", groups);
			mapJson.put("msg", "success");
			mapJson.put("all", all);
			mapJson.put("blacklist", blacklist);
		} catch (Exception e) {
			mapJson.put("msg", "error");
			log.error("", e);
		}
		return "netfriend";
	}

	/**
	 * 将用户放入组
	 * 
	 * @return savegroup
	 */
	public String useraddgroup() {

		mapJson = new Hashtable<String, Object>();
		try {
			// ActionUtil.findWaccountId()
			String[] optionids = openId.split(",");
			List<Followers> list = new ArrayList<Followers>();
			for (int i = 0; i < optionids.length; i++) {
				Interact interact = new Interact();
				Followers fl = new Followers();
				fl.setGroupsId(groupsId);
				fl.setOpenid(optionids[i]);
				fl.setWaccountId(ActionUtil.findWaccountId());
				followersService.useraddgroup(fl);
				if (groupsId.equals("0")) {
					interact.setOpenId(optionids[i]);
					followersService.addblackUser(interact);
				}
			}

			// mapJson.put("rows", groups);
			mapJson.put("msg", "success");
		} catch (Exception e) {
			mapJson.put("msg", "error");
			log.error("", e);
		}
		return "netfriend";
	}

	/**
	 * 新建分组
	 * 
	 * @return savegroup
	 */
	public String savegroup() {

		mapJson = new Hashtable<String, Object>();
		try {
			// ActionUtil.findWaccountId()
			groupsBean.setName(groupsId);
			groupsBean.setWaccountId(ActionUtil.findWaccountId());
			followersService.insertgroup(groupsBean);
			mapJson.put("msg", "success");
		} catch (Exception e) {
			mapJson.put("msg", "error");
			log.error("", e);
		}
		return "netfriend";
	}

	/**
	 * 发消息
	 * 
	 * @param args
	 */
	public String fasong() {
		mapJson = new Hashtable<String, Object>();
		try {
			Interact interact = new Interact();
			interact.setOpenId(openId);
			interact.setContent(content);
			replayBean.setOpenid(openId);
			replayBean.setReplaycontent(content);
			followersService.saveContent(replayBean);
			mapJson = followersService.fasong(interact, ActionUtil.findWaccountId());

		} catch (Exception e) {

			log.error("", e);
		}
		return "netfriend";
	}

	public String message() {
		mapJson = new Hashtable<String, Object>();
		try {
			session.setAttribute("openIdlist", openId);
			if (tname.equals("tuwen")) {
				mapJson.put("msg", "/weixin-studio/weixin/followertuwenInteract.jsp");
			} else {
				mapJson.put("msg", "/weixin-studio/weixin/followerInteract.jsp");
			}
		} catch (Exception e) {

			log.error("tui", e);
		}
		return "netfriend";
	}

	public String tui() {
		mapJson = new Hashtable<String, Object>();
		try {
			String openIds = (String) session.getAttribute("openIdlist");
			System.out.println(openIds);
			String[] oid = openIds.split(",");
			List<String> openid = new ArrayList<String>();
			for (String id : oid) {
				openid.add(id);
			}
			mapJson = followersService.tui(openid, content, ActionUtil.findWaccountId());
			// mapJson.put("msg", "/weixin-studio/interact/tuisongInteract.jsp");
		} catch (Exception e) {

			log.error("tui", e);
		}
		return "netfriend";
	}

	public String tuwentui() {
		mapJson = new Hashtable<String, Object>();
		try {
			String openIds = (String) session.getAttribute("openIdlist");
			System.out.println(openIds);
			String[] oid = openIds.split(",");
			List<String> openid = new ArrayList<String>();
			for (String id : oid) {
				openid.add(id);
			}
			mapJson = followersService.tuwentui(openid, image, ActionUtil.findWaccountId());
			// mapJson.put("msg", "/weixin-studio/interact/tuisongInteract.jsp");
			mapJson.put("msg", "success");
		} catch (Exception e) {

			log.error("tui", e);
		}
		return "netfriend";
	}

	/**
	 * 查询用户
	 * 
	 * @param args
	 */
	public String usearch() {

		mapJson = new Hashtable<String, Object>();
		try {
			// Followers bean
			followersExt.setNickname(tname);
			followersExt.setWaccountId(ActionUtil.findWaccountId());
			List<FollowersExt> followers = followersService.usearch(followersExt);

			mapJson.put("rows", followers);
			mapJson.put("msg", "success");
		} catch (Exception e) {
			mapJson.put("msg", "error");
			log.error("", e);
		}
		return "netfriend";
	}

	public static void main(String[] args) {
		// int count = followersService.getCount();//数据库的数量
		// System.out.println(count);
		FollowerDao t = new FollowerDao();
		Followers followers = new Followers();
		followers.setOpenid("assdfsdfesd");
		followers.setNickname("💋迪迪儿💋");
		followers.setCity("中国");
		followers.setCountry("呼和浩特");
		followers.setSex("女");
		followers.setHeadimgurl("http://wx.qlogo.cn/mmopen/wWbsoX7swp6GlNZKmkd9kGpTOXqmibiaYty9HYf9UbqH8aXUBXM71zWSjcAxB6XXz2RiatiapyibfO1kJdU2aMI9AJS4XXBBIibDvL/0");
		followers.setSubscribe("1");
		followers.setSubscribeTime("1383683895");
		// followersService.insert(followers);

	}

	public Followers getBean() {
		return bean;
	}

	public void setBean(Followers bean) {
		this.bean = bean;
	}

	public FollowersExt getFollowersExt() {
		return followersExt;
	}

	public void setFollowersExt(FollowersExt followersExt) {
		this.followersExt = followersExt;
	}

	public GroupsBean getGroupsBean() {
		return groupsBean;
	}

	public void setGroupsBean(GroupsBean groupsBean) {
		this.groupsBean = groupsBean;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getGroupsId() {
		return groupsId;
	}

	public void setGroupsId(String groupsId) {
		this.groupsId = groupsId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public TuwenPushBean getImage() {
		return image;
	}

	public void setImage(TuwenPushBean image) {
		this.image = image;
	}

	public ReplayBean getReplayBean() {
		return replayBean;
	}

	public void setReplayBean(ReplayBean replayBean) {
		this.replayBean = replayBean;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
