package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctvit.bean.Menus;
import com.ctvit.bean.ViewMenu;
import com.ctvit.util.JdbcUtil;


/**
 * 查询菜单
 * @author Administrator
 *
 */
public class MenusDao {
	
	protected static Logger log = LoggerFactory.getLogger(MenusDao.class);
	
	/**
	 * 查询
	 * @param account
	 * @param materialId
	 * @return
	 */
	public Menus select(String account, String materialId){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			String sql = "select * from tab_wxcustomizemenus where account=? and MaterialId=?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, materialId);
			rs = ps.executeQuery();
			if(rs.next()){
				Menus menus = new Menus();
				menus.setAccount(rs.getString("account"));
				menus.setMaterialId(rs.getString("MaterialId"));
				menus.setName(rs.getString("name"));
				return menus;
			}
			return null;
		}catch(SQLException sqlException){
			log.error("db errors",sqlException);
			return null;
		}finally{
			JdbcUtil.releaseConnection(rs, ps, con);
		}
	}
	
	/**
	 * 查询
	 * @param account
	 * @param materialId
	 * @return
	 */
	public Menus selectByName(String account, String name){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			String sql = "select * from tab_wxcustomizemenus where account=? and Name=?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, name);
			rs = ps.executeQuery();
			if(rs.next()){
				Menus menus = new Menus();
				menus.setAccount(rs.getString("account"));
				menus.setMaterialId(rs.getString("MaterialId"));
				menus.setName(rs.getString("name"));
				return menus;
			}
			return null;
		}catch(SQLException sqlException){
			log.error("db errors",sqlException);
			return null;
		}finally{
			JdbcUtil.releaseConnection(rs, ps, con);
		}
	}
	
	/**
	 * 获取viewMenu
	 * @param menuId
	 * @return
	 */
	public ViewMenu selectViewMenu(String menuId){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			String sql = "select * from tab_wxcustomizemenus where id=?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, menuId);
			rs = ps.executeQuery();
			if(rs.next()){
				ViewMenu viewMenu = new ViewMenu();
				String waccountId = rs.getString("account");
				String url = rs.getString("MaterialId");
				viewMenu.setWaccountId(waccountId);
				viewMenu.setUrl(url);
				return viewMenu;
			}
			return null;
		}catch(SQLException sqlException){
			log.error("db errors",sqlException);
			return null;
		}finally{
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		
		
	}

}
