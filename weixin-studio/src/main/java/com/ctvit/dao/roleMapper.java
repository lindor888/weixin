package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.GraphicMaterial;
import com.ctvit.bean.role;


public interface roleMapper {
	List<role> listAllRoles();
	role getRoleById(String string);
	void insertRole(role role);
	void updateRoleBaseInfo(role role);
	void deleteRoleById(String string);
	int getCountByName(role role);
	void updateRoleRights(role role);
	String countAllrole(role role)throws Exception;
	List<role> selectAllrole(role role);
	List<role> selectUserByrole(role role)throws Exception;
}