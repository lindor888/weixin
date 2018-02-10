package com.ctvit.service;

import java.util.List;
import com.ctvit.bean.role;
import com.ctvit.dao.roleMapper;

public class RoleServiceImpl {

	private roleMapper roleMapper;
	
	public List<role> listAllRoles() {
		// TODO Auto-generated method stub
		return roleMapper.listAllRoles();
	}

	public void deleteRoleById(String string) {
		// TODO Auto-generated method stub
		roleMapper.deleteRoleById(string);
	}

	public role getRoleById(String string) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleById(string);
	}

	public boolean insertRole(role role) {
		// TODO Auto-generated method stub
		if(roleMapper.getCountByName(role)>0)
			return false;
		else{
			roleMapper.insertRole(role);
			return true;
		}
	}

	public boolean updateRoleBaseInfo(role role) {
		if(roleMapper.getCountByName(role)>0)
			return false;
		else{
			roleMapper.updateRoleBaseInfo(role);
			return true;
		}
	}
	
	public List<role> selectUserByrole(role role)throws Exception{
		List<role> list=roleMapper.selectUserByrole(role);
		return list;
	}
	
	public void updateRoleRights(role role) {
		roleMapper.updateRoleRights(role);
	}

	public roleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(roleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	public String countAllrole(role role) throws Exception {
		// TODO Auto-generated method stub
		return (String)roleMapper.countAllrole(role);
	}

	public List<role> selectAllrole(role role)  throws Exception{
		// TODO Auto-generated method stub
		return roleMapper.selectAllrole(role);
	}

}
