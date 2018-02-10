package com.ctvit.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.ctvit.bean.Depart;
import com.ctvit.bean.Menu;
import com.ctvit.bean.Page;
import com.ctvit.bean.role;
import com.ctvit.service.DepartServiceImpl;
import com.ctvit.service.MenuServiceImpl;
import com.ctvit.service.RoleServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class DepartAction extends ActionSupport {

	private Depart depart = new Depart();
	private Menu menu;
	private DepartServiceImpl departService;
	private MenuServiceImpl menuService;
	private RoleServiceImpl roleService;
	private Page page;
	private PrintWriter out;
	private String menuids;
	private Map<String,Object> mapJson=new HashMap<String,Object>();

	

	
	public Map<String, Object> getMapJson() {
		return mapJson;
	}

	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public MenuServiceImpl getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuServiceImpl menuService) {
		this.menuService = menuService;
	}

	public RoleServiceImpl getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleServiceImpl roleService) {
		this.roleService = roleService;
	}

	public String getMenuids() {
		return menuids;
	}

	public void setMenuids(String menuids) {
		this.menuids = menuids;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	HttpServletRequest request = ServletActionContext.getRequest();

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 显示用户列表
	 * 
	 * @param user
	 * @return
	 */
	public String list() {
		try {
			depart.setPage(page);
			List<Depart> departList = departService.listPageDepart(depart);
			List<role> roleList = roleService.listAllRoles();

			request.setAttribute("departList", departList);
			request.setAttribute("roleList", roleList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "departs";
	}

	/**
	 * 请求新增部门页面
	 * 
	 * @param model
	 * @return
	 */
	public String toAdd() {
	/*	List<role> roleList = roleService.listAllRoles();
		request.setAttribute("roleList", roleList);*/
		return "depart_info";
	}

	/**
	 * 保存部门信息
	 * 
	 * @param user
	 * @return
	 */
	public String save() {
		try {
 			if (depart.getDepartId().equals("")) {
				if (departService.insertDepart(depart) == false) {
					request.setAttribute("msg", "failed");
				} else {
					request.setAttribute("msg", "success");
				}
			} else {
				departService.updateDepartBaseInfo(depart);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "save_result";
	}

	/**
	 * 请求编辑部门页面
	 * 
	 * @param userId
	 * @return
	 */
	public String toEdit() {
		Depart depart2 = departService.getDepartById(depart.getDepartId());
		List<role> roleList = roleService.listAllRoles();
		request.setAttribute("depart", depart2);
		request.setAttribute("roleList", roleList);
		return "depart_info";
	}
	/**
	 * 跳转分配页面
	 * 
	 * @param userId
	 * @return
	 */
	public String fenpei() {
		return "fenpei";
	}
	
	
	/**
	 * 删除某个部门
	 * 
	 * @param userId
	 * @param out
	 */
	public void delete() {
		departService.deleteDepart(depart.getDepartId());
	}
	
	public String selectUserbydepart(){
		try {
			List<Depart> list=departService.selectUserbydepart(depart);
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}

	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}

	public DepartServiceImpl getDepartService() {
		return departService;
	}

	public void setDepartService(DepartServiceImpl departService) {
		this.departService = departService;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

}
