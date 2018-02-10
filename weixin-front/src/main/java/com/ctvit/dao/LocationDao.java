package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctvit.bean.InteractLocation;
import com.ctvit.bean.LocationMessage;
import com.ctvit.bean.PrizeBean;
import com.ctvit.bean.ReportsBean;
import com.ctvit.util.DBconn;
import com.ctvit.util.JdbcUtil;

public class LocationDao {

	protected static Logger log = LoggerFactory.getLogger(LocationDao.class);

	/**
	 * 插入地理位置信息
	 * 
	 * @param locationMessage
	 */
	public void insert(LocationMessage locationMessage) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "insert into tab_location (ToUserName,FromUserName,CreateTime,MsgType,Event,Latitude,Longitude) values (?,?,?,?,?,?,?)";
			System.out.println("==============================================================" + sql);
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, locationMessage.getToUserName());
			ps.setString(2, locationMessage.getFromUserName());
			ps.setString(3, locationMessage.getCreateTime());
			ps.setString(4, locationMessage.getMsgType());
			ps.setString(5, locationMessage.getEvent());
			ps.setString(6, locationMessage.getLatitude());
			ps.setString(7, locationMessage.getLongitude());
			// ps.setString(8, locationMessage.getPrecision());
			ps.executeUpdate();
			log.info("[text:]FromUserName:" + locationMessage.getToUserName() + "content:insert db sussess!");
		} catch (SQLException sqlException) {
			log.error("insert错误", sqlException);
			System.out.println("db errors" + sqlException);
			log.error(locationMessage.getFromUserName() + " is exist");
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
	}

	/**
	 * 更新地理位置信息
	 */
	public void update(LocationMessage locationMessage) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "update tab_location set ToUserName = ?,CreateTime = ?,MsgType = ?,Event = ?,Latitude = ?,Longitude = ? where FromUserName = ?";
			System.out.println("============更新用户信息" + sql);
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, locationMessage.getToUserName());
			ps.setString(2, locationMessage.getCreateTime());
			ps.setString(3, locationMessage.getMsgType());
			ps.setString(4, locationMessage.getEvent());
			ps.setString(5, locationMessage.getLatitude());
			ps.setString(6, locationMessage.getLongitude());
			ps.setString(7, locationMessage.getFromUserName());
			ps.executeUpdate();
			log.info("[text:]FromUserName:" + locationMessage.getToUserName() + "content:insert db sussess!");
		} catch (SQLException sqlException) {
			log.error("insert错误", sqlException);
			System.out.println("db errors" + sqlException);
			log.error(locationMessage.getFromUserName() + " is exist");
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
	}

	public int select(String openId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(FromUserName) from tab_location where FromUserName = ?";

			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openId);
			System.out.println(openId);
			System.out.println(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
				System.out.println("num=" + count);
			}

		} catch (SQLException sqlException) {
			log.error("db errors", sqlException);
			System.out.println("db errors" + sqlException);
			return count;
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		return count;

	}

	public int selectintegralAddtime(String activeId, String openid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(new Date());
			String createtime = "%" + time + "%";
			String sql = "select CreateTime from tab_integraladd where ActiveId =? and UserName=? and CreateTime like ?";
			con = DBconn.getJFConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, activeId);
			ps.setString(2, openid);
			ps.setString(3, createtime);
			rs = ps.executeQuery();

			if (rs.next()) {
				count++;
				System.out.println("num=" + count);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		return count;
	}

	public String selectScore(String appid, String openid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String score = null;
		try {
			String sql = "select * from tab_integral where AppId =? and UserName=?";
			con = DBconn.getJFConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, appid);
			ps.setString(2, openid);
			rs = ps.executeQuery();

			if (rs.next()) {
				score = rs.getString("Score");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		return score;
	}

	public String getWaccountId(String openid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String waccountId = null;
		try {
			String sql = "select waccountId from tab_wx_followers where openId =?";
			con = DBconn.getJFConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openid);
			rs = ps.executeQuery();

			if (rs.next()) {
				waccountId = rs.getString("waccountId");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		return waccountId;
	}

	public void saveReports(ReportsBean reportsBean) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "insert into tab_reports (reportsID,qrcodeID,openID,reportsTime,nickName,headImg,city,waccount) values (?,?,?,?,?,?,?,?)";
			System.out.println("=====" + sql);
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, reportsBean.getReportsID());
			ps.setString(2, reportsBean.getQrcodeID());
			ps.setString(3, reportsBean.getOpenID());
			ps.setString(4, reportsBean.getReportsTime());
			ps.setString(5, reportsBean.getNickName());
			ps.setString(6, reportsBean.getHeadImg());
			ps.setString(7, reportsBean.getCity());
			ps.setString(8, reportsBean.getWaccount());
			ps.executeUpdate();
		} catch (SQLException sqlException) {
			log.error("insert错误", sqlException);
			System.out.println("db errors" + sqlException);
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
	}

	public int selectReports(String openid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(new Date());
			String createtime = "%" + time + "%";
			String sql = "select reportsTime from tab_reports where openID=? and reportsTime like ?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openid);
			ps.setString(2, createtime);
			rs = ps.executeQuery();

			if (rs.next()) {
				count++;
				System.out.println("num=" + count);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		return count;
	}

	public void savePrize(PrizeBean prizeBean) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "insert into tab_prize (prizeID,waccountID,openID,prizeName,prizeTime) values (?,?,?,?,?)";
			System.out.println("==============================================================" + sql);
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, prizeBean.getPrizeID());
			ps.setString(2, prizeBean.getWaccountID());
			ps.setString(3, prizeBean.getOpenID());
			ps.setString(4, prizeBean.getPrizeName());
			ps.setString(5, prizeBean.getPrizeTime());
			ps.executeUpdate();
		} catch (SQLException sqlException) {
			log.error("insert错误", sqlException);
			System.out.println("db errors" + sqlException);
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}

	}

	public int checkuser(String openid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(new Date());
			String createtime = "%" + time + "%";
			String sql = "select openID from tab_prize where openID=? and prizeTime like ?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openid);
			ps.setString(2, createtime);
			rs = ps.executeQuery();

			if (rs.next()) {
				count++;
				System.out.println("num=" + count);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}

		return count;
	}

	public List<InteractLocation> contentlList(String zhutiid) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<InteractLocation> contentlList = new ArrayList<InteractLocation>();
		try {
			String sql = "select * from tab_interact_location where zhutiid=?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, zhutiid);
			rs = ps.executeQuery();
			while (rs.next()) {
				InteractLocation interactLocation = new InteractLocation();
				interactLocation.setInteractid(rs.getString("interactid"));
				interactLocation.setWaccount_id(rs.getString("waccount_id"));
				interactLocation.setOpenid(rs.getString("openid"));
				interactLocation.setZhutiid(rs.getString("zhutiid"));
				interactLocation.setLatitude(rs.getString("Latitude"));
				interactLocation.setLongitude(rs.getString("Longitude"));
				interactLocation.setSend_time(rs.getString("send_time"));
				interactLocation.setTitle(rs.getString("title"));
				interactLocation.setContent(rs.getString("content"));
				interactLocation.setNickname(rs.getString("nickname"));
				contentlList.add(interactLocation);
			}

		} catch (SQLException sqlException) {
			log.error("db errors", sqlException);
			return null;
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		return contentlList;
	}
}
