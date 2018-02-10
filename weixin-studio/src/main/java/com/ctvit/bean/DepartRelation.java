package com.ctvit.bean;



import java.util.Date;

import com.ctvit.util.BaseData;

public class DepartRelation   implements BaseData {
	 private String relationId;

	  private String departId;

	  private String roleId;

	    private Date operateTime;
	    private String operateAccount;
	    
	    private String rolename;
	    
	    
	    

		public String getRolename() {
			return rolename;
		}

		public void setRolename(String rolename) {
			this.rolename = rolename;
		}

		public String getOperateAccount() {
			return operateAccount;
		}

		public void setOperateAccount(String operateAccount) {
			this.operateAccount = operateAccount;
		}

		public String getRelationId() {
			return relationId;
		}

		public void setRelationId(String relationId) {
			this.relationId = relationId;
		}

		public String getDepartId() {
			return departId;
		}

		public void setDepartId(String departId) {
			this.departId = departId;
		}

		public String getRoleId() {
			return roleId;
		}

		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}

		public Date getOperateTime() {
			return operateTime;
		}

		public void setOperateTime(Date operateTime) {
			this.operateTime = operateTime;
		}

	    
	    

}
