package com.ctvit.menu;

import com.ctvit.bean.Articles;
import com.ctvit.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;

public class MenuTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenuTree tree = new MenuTree();
		Menu menu = new Menu();
		menu.setKey("KEY_001");
		menu.setName("今日歌曲");
		tree.addMenu(menu);

		menu = new Menu();
		menu.setKey("KEY_002");
		menu.setName("歌手简介");
		tree.addMenu(menu);

		menu = new Menu();
		menu.setKey("KEY_003");
		menu.setName("菜单");
		tree.addMenu(menu);	
		
		Menu sub_menu = new Menu();
		sub_menu.setKey("KEY_003_001");
		sub_menu.setName("征集");
		menu.add(sub_menu);	
		
		sub_menu = new Menu();
		sub_menu.setKey("KEY_003_002");
		sub_menu.setName("赞一下我们");
		menu.add(sub_menu);	
		
		//System.out.println(tree.printTree());
		String xml = tree.menuTreeToXml(tree);		
		System.out.println(xml);
		
		MenuTree tree1 = tree.xmlToMenuTree(xml);
		
		System.out.println(tree1.printTree());
	}

}
