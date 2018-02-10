package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctvit.util.JdbcUtil;

public class WaccountDao {

	protected static Logger log = LoggerFactory.getLogger(WaccountDao.class);

	/**
	 * 通过天脉token获取公众号id
	 * 
	 * @param menuId
	 * @return
	 */
	public String selectWaccountIdByBridgeToken(String bridgeToken) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from tab_waccount where bridge_token=?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bridgeToken);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("waccount_id");
			}
			return null;
		} catch (SQLException sqlException) {
			log.error("db errors", sqlException);
			return null;
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}

	}

	public String getAppSecret(String appid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from tab_waccount where app_id =?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, appid);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("app_secret");
			}
			return null;
		} catch (SQLException sqlException) {
			log.error("db errors", sqlException);
			return null;
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}

	}

}
