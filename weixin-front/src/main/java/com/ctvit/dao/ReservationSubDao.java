package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ctvit.bean.ReservationSub;
import com.ctvit.util.JdbcUtil;

/**
 * 订阅节目信息表
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年8月31日 上午10:20:34
 */
public class ReservationSubDao {

	/**
	 * 添加订阅
	 * 
	 * @author guosidi
	 * @date 2015年8月31日 下午1:40:54
	 * @param sub
	 * @return
	 * @return Boolean
	 */
	public Boolean addSub(ReservationSub sub) {
		Connection con = null;
		PreparedStatement ps = null;
		Boolean res = null;
		try {
			String sql = "INSERT INTO tab_reservation_subscription(subscription_id, reservation_id, open_id, flag) VALUES (?,?,?,?);";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, sub.getSubscription_id());
			ps.setString(2, sub.getReservation_id());
			ps.setString(3, sub.getOpen_id());
			ps.setShort(4, sub.getFlag());
			res = ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return res;
	}

	/**
	 * 根据预约 id查找 预约信息
	 * 
	 * @author guosidi
	 * @date 2015年9月1日 上午10:42:28
	 * @param subID
	 * @return
	 * @return ReservationSub
	 */
	public ReservationSub selectOne(String subID) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ReservationSub sub = new ReservationSub();
		try {
			String sql = "SELECT subscription_id, reservation_id, open_id, flag FROM tab_reservation_subscription WHERE subscription_id = ?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, subID);
			rs = ps.executeQuery();
			if (rs.next()) {
				sub.setFlag(rs.getShort("flag"));
				sub.setSubscription_id(rs.getString("subscription_id"));
				sub.setReservation_id(rs.getString("reservation_id"));
				sub.setOpen_id(rs.getString("open_id"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		return sub;
	}

	/**
	 * 根绝 预约id 修改预约状态
	 * 
	 * @author guosidi
	 * @date 2015年9月1日 上午10:41:47
	 * @param flag
	 * @param subID
	 * @return
	 * @return int
	 */
	public int updataSub(short flag, String subID) {
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "Update tab_reservation_subscription set flag=? where subscription_id=?;";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setShort(1, flag);
			ps.setString(2, subID);
			res = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return res;
	}

	/**
	 * 根据 节目id和 用户id 查找订阅的 id
	 * 
	 * @author guosidi
	 * @date 2015年9月1日 上午10:41:05
	 * @param resid
	 * @param openid
	 * @return
	 * @return String
	 */
	public String getSubid(String resid, String openid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String subid = null;
		try {
			String sql = "SELECT subscription_id FROM tab_reservation_subscription WHERE reservation_id = ? AND open_id=? AND flag=1";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, resid);
			ps.setString(2, openid);
			rs = ps.executeQuery();
			if (rs.next()) {
				subid = rs.getString("subscription_id");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		return subid;
	}

	/**
	 * 根据节目id 统计预约该节目的用户总量
	 * 
	 * @author guosidi
	 * @date 2015年9月1日 上午10:40:20
	 * @param epgid
	 * @return
	 * @return int
	 */
	public int getCountByEpgid(String epgid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "SELECT count(subscription_id) FROM tab_reservation_subscription WHERE reservation_id = ? AND flag = 1;";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, epgid);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		return count;
	}

}