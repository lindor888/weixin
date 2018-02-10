package com.ctvit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库链接
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年9月2日 上午10:18:31
 */
public class DBconn {

	// 1. 获取数据库连接 加载驱动，获得连接
	public static String dbDriver = "com.mysql.jdbc.Driver";// PropertiesManager.getProperties("dbDriver");
	public static String dbURL = "jdbc:mysql://127.0.0.1:3306/weixinstudio?generateSimpleParameterMetadata=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=round";// PropertiesManager.getProperties("dbURL");
	public static String username = "root";// PropertiesManager.getProperties("username");
	public static String password = "root";// PropertiesManager.getProperties("password");

	public static Connection getConnection() throws Exception {

		Connection conn = null;
		// String dbDriver = "com.mysql.jdbc.Driver";

		// String dbURL =
		// "jdbc:mysql://127.0.0.1:3306/weixinstudio?useUnicode=true&characterEncoding=UTF-8&&zeroDateTimeBehavior=convertToNull";
		// String dbURL =
		// "jdbc:mysql://192.168.168.172:3306/weixinstudio?useUnicode=true&characterEncoding=UTF-8&&zeroDateTimeBehavior=convertToNull";
		// String username = "root";
		// String password = "root"; // 本地
		// String password = "ctvit.com"; // 172
		Class.forName(dbDriver);
		conn = DriverManager.getConnection(dbURL, username, password);
		return conn;
	}

	// 2. 关闭数据库连接

	public static void closeConnection(PreparedStatement pstmt, Connection conn) throws SQLException {

		if (pstmt != null) {
			pstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
	}
}
