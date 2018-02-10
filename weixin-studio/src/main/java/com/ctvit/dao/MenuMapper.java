package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.Menu;

public interface MenuMapper {
	Menu getByName(Menu menu)throws Exception;
	List<Menu> listAllParentMenu();
	List<Menu> listSubMenuByParentId(Integer parentId);
	Menu getMenuById(Integer menuId);
	void insertMenu(Menu menu);
	void updateMenu(Menu menu);
	void deleteMenuById(Integer menuId) throws Exception;
	List<Menu> listAllSubMenu();
}
