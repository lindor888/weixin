package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.ctvit.bean.VirtualseatInfo;
import com.ctvit.util.JdbcUtil;

public class VirtualseatInfoDao {
	private static final Logger LOGGER = Logger.getLogger(VirtualseatInfoDao.class);

	public void addVirtualSeat(String waccountID, String videoID) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO tab_virtualseat_info(virtualSeatID,waccountID,videoID,lineCount,columnCount,resultSeats) VALUES(?,?,?,?,?,?);";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, waccountID + videoID);
			ps.setString(2, waccountID);
			ps.setString(3, videoID);
			ps.setInt(4, 6);
			ps.setInt(5, 8);
			ps.setInt(6, 48);
			ps.execute();
		} catch (Exception e) {
			LOGGER.error(String.format("添加座位席失败，公众账号：%s，视频ID：%s", new Object[] { waccountID, videoID }), e);
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
	}

	public void updateResultSeats(String virtualSeatID) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE tab_virtualseat_info SET resultSeats=resultSeats-1 WHERE virtualSeatID=?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, virtualSeatID);
			ps.executeUpdate();
		} catch (Exception e) {
			LOGGER.error(String.format("更新座位席余量失败，座位席ID：%s", new Object[] { virtualSeatID }), e);
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
	}

	public VirtualseatInfo findOne(String virtualseatID) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		VirtualseatInfo record = new VirtualseatInfo();
		try {
			String sql = "SELECT virtualSeatID,waccountID,videoID,lineCount,columnCount,resultSeats FROM tab_virtualseat_info WHERE virtualSeatID=?;";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, virtualseatID);
			rs = ps.executeQuery();
			if (rs.next()) {
				record.setVirtualSeatID(rs.getString("virtualSeatID"));
				record.setWaccountID(rs.getString("waccountID"));
				record.setVideoID(rs.getString("videoID"));
				record.setLineCount(rs.getInt("lineCount"));
				record.setColumnCount(rs.getInt("columnCount"));
				record.setResultSeats(rs.getInt("resultSeats"));
			}
		} catch (Exception e) {
			LOGGER.info("查询已选座位失败：" + e);
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return record;
	}
}