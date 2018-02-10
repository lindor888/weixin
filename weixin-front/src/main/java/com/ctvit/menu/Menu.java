/**
 * 微信公众平台开发模式(JAVA) SDK
 */
package com.ctvit.menu;

import java.util.ArrayList;

public class Menu {
	private ArrayList<Menu> children= new ArrayList<Menu>();
	private String name;
	private String key;	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void add(Menu menu){
		this.children.add(menu);
	}

	public String printMenu(){
		StringBuffer sb=new StringBuffer(128);
		if(children==null || children.size()==0){
			sb.append("\"type\":\"click\",");
			sb.append("\"name\":\"").append(name).append("\",");
			sb.append("\"key\":\"").append(key).append("\"");
		}else{
			sb.append("\"name\":\"").append(name).append("\",");
			sb.append("\"sub_button\":").append("[");
			int size=children.size();
			Menu menu=null;
			for(int i=0;i<size;i++){
				menu=children.get(i);

				sb.append("{");
				sb.append(menu.printMenu());
				sb.append("}");
				if(i<size -1){
					sb.append(",");
				}					
			}
			sb.append("]");
		}
		return sb.toString();		
	}
}
