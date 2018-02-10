package com.ctvit.dao;

import java.sql.DriverManager;

import com.ctvit.util.ConfLoad;
import com.mysql.jdbc.Connection;

public class connDB {

private Connection ct = null;
	
	public Connection getConn(){
		
		try {
		    String username = ConfLoad.get("jdbc_username");
		    String password = ConfLoad.get("jdbc_password");
			//1. 注册驱动
		    Class.forName("com.mysql.jdbc.Driver");
		     
		    //2.获得数据库连接
		    
		    ct = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/weixinstudio",username,password);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return ct;
		
		
	}
	
}
