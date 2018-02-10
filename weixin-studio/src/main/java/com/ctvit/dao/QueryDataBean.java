package com.ctvit.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("rawtypes")
public class QueryDataBean {
	private boolean pagination;
	private String startDate;
	private String endDate;
	private int page;
	private int rows;
	private String title;
	private String login;
	private String waccountId;
	private String username;
	private Integer department;
	private String password;
	private String groupId;
	private String groupName;
	private String type;
	private Byte state;
	private String roleId;
	private String catalogTitle;
	
	private HashMap<String, Object> map;
	private List list;
	private Date create_time;
	//公告信息标题颜色标识
	private String noticeColorFlag;

	public String getNoticeColorFlag() {
		return noticeColorFlag;
	}

	public void setNoticeColorFlag(String noticeColorFlag) {
		this.noticeColorFlag = noticeColorFlag;
	}

	public Date getStartDate() {
		if(startDate == null||startDate.equals("")){return null;}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
		try {
			return simpleDateFormat.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		if(endDate == null||endDate.equals("")){return null;}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
		try {
			return simpleDateFormat.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}





	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public HashMap<String, Object> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}





	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	
	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCatalogTitle() {
		return catalogTitle;
	}

	public void setCatalogTitle(String catalogTitle) {
		this.catalogTitle = catalogTitle;
	}

	public String getWaccountId() {
		return waccountId;
	}

	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId;
	}
}
