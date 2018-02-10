package com.ctvit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Menu;
import com.ctvit.bean.role;
import com.ctvit.service.MenuServiceImpl;
import com.ctvit.service.RoleServiceImpl;
import com.ctvit.util.Const;
import com.ctvit.util.KeyGenerator;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.util.RightsHelper;
import com.ctvit.util.Tools;

public class RoleAction {

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	private RoleServiceImpl roleService;
	private MenuServiceImpl menuService;
	private KeyGenerator keyGenerator;
	private Map<String, Object> mapJson = new Hashtable<String, Object>();
	private role role ;
	private String menuIds;
	private String page;
	private String rows;
	

	public Map<String, Object> getMapJson() {
		return mapJson;
	}

	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public RoleServiceImpl getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleServiceImpl roleService) {
		this.roleService = roleService;
	}

	public MenuServiceImpl getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuServiceImpl menuService) {
		this.menuService = menuService;
	}

	public role getRole() {
		return role;
	}

	public void setRole(role role) {
		this.role = role;
	}

	/**
	 * 显示角色列表
	 * 
	 * @param map
	 * @return
	 */
	public String list() {
		List<role> roleList = roleService.listAllRoles();
		request.setAttribute("roleList", roleList);
		return "roles";
	}

	/**
	 * 角色列表
	 * @return
	 */
	public String querylist() {
		if (page == null || rows == null) {
			page = "1";
			rows = "10";
		}
		if (role == null) {
			role = new role();
		}
		role.setStart((Integer.parseInt(page) - 1) * (Integer.parseInt(rows)));
		role.setEnd(Integer.parseInt(rows));
		try {
			String count = roleService.countAllrole(role);
			List<role> list = roleService.selectAllrole(role);
			mapJson.put("total", count);
			mapJson.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//清理现场
		page = null;
		rows = null;
		role = null;

		return "dft";
	}
	
	
	/**
	 * 保存角色信息
	 * 
	 * @param out
	 * @param role
	 */
	public void save() {
		boolean flag = true;
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (role.getRoleId() != null) {
				flag = roleService.updateRoleBaseInfo(role);
			} else {
				role.setRoleId(keyGenerator.getKey(role));
				flag = roleService.insertRole(role);
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
	
	
	public void delete(){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			roleService.deleteRoleById(role.getRoleId());
			out.write("success");
			
			//修改角色版本号
			Integer roleV = (Integer) MemcacheUtils.getMemCachedClientInstance().get(Const.ROLE_VERSION + role.getRoleId());
			roleV = (roleV == null) ? 0 : roleV;
			MemcacheUtils.getMemCachedClientInstance().set(Const.ROLE_VERSION + role.getRoleId(), (roleV + 1));
		} catch (Exception e) {
			e.printStackTrace();
			out.write("fail");
		}
		out.flush();
		out.close();
	}

	public String selectUserByrole(){
		try {
			List<role> list=roleService.selectUserByrole(role);
			if(list.size()>0){
				mapJson.put("msg", "fail");
			}else{
				mapJson.put("msg", "sucess");
			}
		} catch (Exception e) {
			mapJson.put("msg", "fail");
			e.printStackTrace();
		}
		return "dft";
	}
	
	/**
	 * 请求角色授权页面
	 * 
	 * @param roleId
	 * @param model
	 * @return
	 */
	public String auth() {
		List<Menu> menuList = menuService.listAllMenu();
		role role2 = roleService.getRoleById(role.getRoleId());
		String roleRights = role2.getRights();
		if (Tools.notEmpty(roleRights)) {
			for (Menu menu : menuList) {
				menu.setHasMenu(RightsHelper.testRights(roleRights,
						menu.getMenuId()));
				if (menu.isHasMenu()) {
					List<Menu> subMenuList = menu.getSubMenu();
					for (Menu sub : subMenuList) {
						sub.setHasMenu(RightsHelper.testRights(roleRights,
								sub.getMenuId()));
					}
				}
			}
		}
		JSONArray arr = JSONArray.fromObject(menuList);
		String json = arr.toString();
		json = json.replaceAll("menuId", "id").replaceAll("menuName", "name")
				.replaceAll("subMenu", "nodes")
				.replaceAll("hasMenu", "checked");
		request.setAttribute("zTreeNodes", json);
		request.setAttribute("roleId", role2.getRoleId());
		return "authorization";
	}

	/**
	 * 保存角色权限
	 * 
	 * @param roleId
	 * @param menuIds
	 * @param out
	 */
	public void saveAuth() {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			BigInteger rights = RightsHelper.sumRights(Tools.str2StrArray(menuIds));
			role role2 = roleService.getRoleById(role.getRoleId());
			role2.setRights(rights.toString());
			roleService.updateRoleRights(role2);

			//修改角色版本号
			Integer roleV = (Integer) MemcacheUtils.getMemCachedClientInstance().get(Const.ROLE_VERSION
					+ role.getRoleId());
			roleV = (roleV == null) ? 0 : roleV;
			MemcacheUtils.getMemCachedClientInstance().set(Const.ROLE_VERSION + role.getRoleId(), (roleV + 1));
			
			out.write("success");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}
	
	
}
