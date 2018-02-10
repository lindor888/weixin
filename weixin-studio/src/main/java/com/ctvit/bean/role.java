package com.ctvit.bean;

import com.ctvit.util.BaseData;

public class role implements BaseData {
	
	private String roleId;
	private String roleName;
	private String rights;
	private int start;
	private int end;
	

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String string) {
		this.roleId = string;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}
}