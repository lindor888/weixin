package com.ctvit.util;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.sql.*;
import org.apache.log4j.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class JdbcUtil {
	public static Timestamp StringToTime(String str) {
		System.out.println(str);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		format.setLenient(false);
		Timestamp ts = null;
		try {
			ts = new Timestamp(format.parse(str).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;

	}
	public static Timestamp StringToTime1(String str) {
		System.out.println(str);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		Timestamp ts = null;
		try {
			ts = new Timestamp(format.parse(str).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;

	}
	public static String StringCheck(String str){
		if(null == str || "null".equals(str)){
			return "";
		}else{
			return str;
		}
	}
	public static boolean insertObject(String sql){
		
		return false;
	}
}
