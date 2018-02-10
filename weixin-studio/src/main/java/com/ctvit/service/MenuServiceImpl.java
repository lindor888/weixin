package com.ctvit.service;

import java.util.List;

import com.ctvit.bean.Logger;
import com.ctvit.bean.Menu;
import com.ctvit.dao.MenuMapper;

public class MenuServiceImpl {

	private MenuMapper menuMapper;
	private LoggerServiceImpl loggerService;
	private Logger log = new Logger();

	public LoggerServiceImpl getLoggerService() {
		return loggerService;
	}

	public void setLoggerService(LoggerServiceImpl loggerService) {
		this.loggerService = loggerService;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public List<Menu> listAllParentMenu() {
		// TODO Auto-generated method stub
		return menuMapper.listAllParentMenu();
	}

	public Menu getMenuById(Integer menuId) {
		// TODO Auto-generated method stub
		return menuMapper.getMenuById(menuId);
	}
	
	public Menu getByName(Menu menu) throws Exception{
		Menu menu1 = menuMapper.getByName(menu);
		return menu1;
	}

	public void saveMenu(Menu menu) {
		// TODO Auto-generated method stub
		if(menu.getMenuId()!=null&& menu.getMenuId().intValue()>0){
			menuMapper.updateMenu(menu);
		}else{
			menuMapper.insertMenu(menu);
		}
		log.setAct("增加");
		log.setType("菜单");
		loggerService.saveLog(log);
		
	}

	public List<Menu> listSubMenuByParentId(Integer menuId) {

		// TODO Auto-generated method stub
		return menuMapper.listSubMenuByParentId(menuId);
	}

	public List<Menu> listAllMenu() {
		// TODO Auto-generated method stub
		List<Menu> rl = this.listAllParentMenu();
		for (Menu menu : rl) {
			List<Menu> subList = this.listSubMenuByParentId(menu.getMenuId());
			menu.setSubMenu(subList);
		}
		return rl;
	}

	public MenuMapper getMenuMapper() {
		return menuMapper;
	}

	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	public void deleteMenuById(Integer menuId) throws Exception{
		// TODO Auto-generated method stub
		menuMapper.deleteMenuById(menuId);
	}

}
