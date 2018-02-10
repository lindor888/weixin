package com.ctvit.util;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;





public class jdbcUtils {
	/**
	 * JNDI名称
	 */
	private static String jndiName;
	/**
	 * 数据库连接地址
	 */
	private static String url;
	/**
	 * 数据库连接用户名
	 */
	private static String userName;
	/**
	 * 数据库连接密码
	 */
	private static String password;
	
	
	static{
		jndiName = ConfKit.get("jndiName");
		url = ConfKit.get("url");
		userName = ConfKit.get("userName");
		password = ConfKit.get("password");
	}
	
	/**
	 * 根据JNDI名称拿到数据库链接
	 * @param jndiName -- JNDI名称
	 * @return
	 * @throws Exception
	 */
	private static Connection getConnectionByJNDI(String jndiName) throws Exception{
		Connection con = null;
		
		InitialContext ctx = null;
		DataSource ds = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(jndiName);
			con = ds.getConnection();
		} catch (NamingException e) {
			throw new Exception(e);
		} catch (SQLException e) {
			throw new Exception(e);
		}
		
		return con;
	}
	/**
	 * 通过驱动拿到数据库链接
	 * @param url
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static Connection getConnectionByDriver(String url,String userName,String password) throws Exception{
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,userName,password);
		} catch (ClassNotFoundException e) {
			throw new Exception(e);
		} catch (SQLException e) {
			throw new Exception(e);
		}
		
		return con;
	}
	/**
	 * 拿到数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(){
		Connection con = null;
		int num = 0;
		while(con==null&&num<3){
			try{
				if(jndiName!=null&&jndiName.trim().length()>0){
					//通过JNDI拿链接
					con = jdbcUtils.getConnectionByJNDI(jndiName);
				}else{
					//通过驱动拿链接
					con = jdbcUtils.getConnectionByDriver(url, userName, password);
				}
			}catch(Exception e){
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				num++;
				continue;
			}
		}
		return con;
	}
	/**
	 * 释放数据库连接
	 * @param rs -- 结果集
	 * @param ps -- PreparedStatement
	 * @param con -- 数据库连接
	 * @throws Exception
	 */
	public static void releaseConnection(ResultSet rs,PreparedStatement ps,Connection con){
		try {
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(ps!=null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(con!=null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 释放数据库连接
	 * @param ps
	 * @param con
	 * @throws Exception
	 */
	public static void releaseConnection(PreparedStatement ps,Connection con){
		jdbcUtils.releaseConnection(null, ps, con);
	}
}
