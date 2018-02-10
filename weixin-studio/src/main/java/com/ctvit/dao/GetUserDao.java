package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ctvit.bean.Followers;
import com.ctvit.util.DBconn;

/**
 * 查询用户信息
 * 
 * @author Administrator
 * 
 */
public class GetUserDao {

	/**
	 * 插入数据
	 * 
	 * @param interact
	 * @throws Exception
	 */
	public Followers getU(String openid) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Followers followers = new Followers();
		try {
			String sql = "select wx.openId,wx.city,wx.nickname,wx.headimgurl from  tab_wx_followers wx where wx.openId=?";
			con = DBconn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openid);
			System.out.println(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				followers.setOpenid(rs.getString("openId"));
				followers.setCity(rs.getString("city"));
				followers.setNickname(rs.getString("nickname"));
				followers.setHeadimgurl(rs.getString("headimgurl"));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				DBconn.closeConnection(ps, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return followers;
	}

}
