package com.ctvit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Menu;
import com.ctvit.service.MenuServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class MenuAction extends ActionSupport implements Preparable{

	private MenuServiceImpl menuService;
	private Map<String, Object> mapJson = new Hashtable<String, Object>();
	private Menu menu;
	
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


	HttpServletResponse response = ServletActionContext.getResponse();
	public HttpServletResponse getResponse() {
		return response;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	/**
	 * 显示菜单列表
	 * @param model
	 * @return
	 */
	public String list(){
		List<Menu> menuList= menuService.listAllParentMenu();
		request.setAttribute("menuList", menuList);
		return "menus";
	}
	

	/**
	 * 请求新增菜单页面
	 * @param model
	 * @return
	 */
	public String toAdd(){
		List<Menu> menuList = menuService.listAllParentMenu();
		request.setAttribute("menuList", menuList);;
		return "menus_info";
	}
	
	/**
	 * 请求编辑菜单页面
	 * @param menuId
	 * @param model
	 * @return
	 */
	public String toEdit(){
		Menu menu2 = menuService.getMenuById(menu.getMenuId());
		request.setAttribute("menu", menu2);
		if(menu2.getParentId()!=null && menu2.getParentId().intValue()>0){
			List<Menu> menuList = menuService.listAllParentMenu();
			request.setAttribute("menuList", menuList);
		}
		return "menus_info";
	}
	
	/**
	 * 保存菜单信息
	 * @param menu
	 * @param model
	 * @return
	 */
	public String save(){
		try {
			if(menu == null || menu.getMenuName() == null || menu.getMenuName().equals("")) {
				mapJson.put("msg", "fail");
			} else if(menuService.getByName(menu) !=null) {
				mapJson.put("msg", "exist");
			} else {
				menuService.saveMenu(menu);
				mapJson.put("msg", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapJson.put("msg", "fail");
		}
		return "save_result";
	}
	
	/**
	 * 获取当前菜单的所有子菜单
	 * @param menuId
	 * @param response
	 */
	public void selectBySub(){
		List<Menu> subMenu = menuService.listSubMenuByParentId(menu.getMenuId());
		JSONArray arr = JSONArray.fromObject(subMenu);
		PrintWriter out;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");  
			out = response.getWriter();
			String json = arr.toString();
//			new PrintWriter(new OutputStreamWriter(new (json, "UTF-8")); 
			System.out.println(json);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除菜单
	 * @param menuId
	 * @param out
	 */
	public String delete(){
		try {
			menuService.deleteMenuById(menu.getMenuId());
			mapJson.put("msg", "delSucess");
		} catch (Exception e) {
			mapJson.put("msg", "delFail");
			e.printStackTrace();
		}
		return "save_result";
	}
	
	public MenuServiceImpl getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuServiceImpl menuService) {
		this.menuService = menuService;
	}


	public Map<String, Object> getMapJson() {
		return mapJson;
	}


	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}

	public void prepare() throws Exception {
		System.out.println("hello================");
		
	}
	
}
