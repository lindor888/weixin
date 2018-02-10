package com.ctvit.bean;

import java.io.Serializable;
import java.util.Date;

import com.ctvit.util.BaseData;

public class Account implements BaseData,Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1279325059181978000L;

	private String accountId;

    private String login;

    private String password;

    private String username;

    private String roleId;

    private Date createTime;
    
    private Byte state;
    
    private Integer department;
    
    private String roleName;//角色
    
    private String createtimestring;
    
    private Depart depart;
    
    private String isinside;
    
    private String   departName;
    
	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}


	public String getIsinside() {
		return isinside;
	}

	public void setIsinside(String isinside) {
		this.isinside = isinside;
	}

	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}

	public String getCreatetimestring() {
		return createtimestring;
	}

	public void setCreatetimestring(String createtimestring) {
		this.createtimestring = createtimestring;
	}

	public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
   
}