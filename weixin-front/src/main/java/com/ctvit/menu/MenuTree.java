package com.ctvit.menu;

import java.util.ArrayList;

import com.ctvit.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;

public class MenuTree {
	private ArrayList<Menu> buttons = new ArrayList<Menu>();

	public void addMenu(final Menu menu) {		
		this.buttons.add(menu);
	}

	public String printTree() {
		StringBuffer sb = new StringBuffer(128);

		sb.append("{\"button\":").append("[");
		int size = buttons.size();
		Menu menu = null;
		for (int i = 0; i < size; i++) {
			menu = buttons.get(i);
			sb.append("{");
			sb.append(menu.printMenu());
			sb.append("}");
			if (i < size - 1) {
				sb.append(",");
			}			
		}
		sb.append("]}");

		return sb.toString();
	}
	
	public static MenuTree xmlToMenuTree(String xml){
		XStream xs = XStreamFactory.init(false);
		xs.alias("xml", MenuTree.class);
		xs.alias("menu", Menu.class);
		xs.addImplicitCollection(MenuTree.class, "buttons");
		xs.addImplicitCollection(Menu.class, "children");
		MenuTree tree =(MenuTree) xs.fromXML(xml);
		return tree;
	}
	
	public static String menuTreeToXml(MenuTree tree){
		XStream xs = XStreamFactory.init(false);
		xs.alias("xml", MenuTree.class);
		xs.alias("menu", Menu.class);
		xs.addImplicitCollection(MenuTree.class, "buttons");
		xs.addImplicitCollection(Menu.class, "children");
		String xml = xs.toXML(tree);
		return xml;
	}	

}
