package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ctvit.bean.VirtualseatChose;
import com.ctvit.util.JdbcUtil;

public class VirtualseatChoseDao {
	private static final Logger LOGGER = Logger.getLogger(VirtualseatChoseDao.class);

	public void addChoseSeat(VirtualseatChose record) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO tab_virtualseat_chose(choseID,openID,virtualSeatID,nickname,headimgurl,lineNum,columnNum) VALUES(?,?,?,?,?,?,?);";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, record.getChoseID());
			ps.setString(2, record.getOpenID());
			ps.setString(3, record.getVirtualSeatID());
			ps.setString(4, record.getNickname());
			ps.setString(5, record.getHeadimgurl());
			ps.setInt(6, record.getLineNum());
			ps.setInt(7, record.getColumnNum());
			ps.execute();
		} catch (Exception e) {
			LOGGER.error(String.format("为用户分配座位席失败，座位号：%s", new Object[] { record.getChoseID() }), e);
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
	}

	public VirtualseatChose selectOne(String openID, String virtualID) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		VirtualseatChose record = new VirtualseatChose();
		try {
			String sql = "SELECT choseID,openID,virtualSeatID,nickname,headimgurl,lineNum,columnNum FROM tab_virtualseat_chose WHERE openID=? and virtualSeatID=? ;";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openID);
			ps.setString(2, virtualID);
			rs = ps.executeQuery();
			if (rs.next()) {
				record.setChoseID(rs.getString("choseID"));
				record.setOpenID(rs.getString("openID"));
				record.setVirtualSeatID(rs.getString("virtualSeatID"));
				record.setNickname(rs.getString("nickname"));
				record.setHeadimgurl(rs.getString("headimgurl"));
				record.setLineNum(rs.getInt("lineNum"));
				record.setColumnNum(rs.getInt("columnNum"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(rs, ps, con);
		}
		return record;
	}

	public List<VirtualseatChose> findList(String virtualseatID) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			String sql = "SELECT choseID,openID,virtualSeatID,nickname,headimgurl,lineNum,columnNum FROM tab_virtualseat_chose WHERE virtualSeatID=? ;";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, virtualseatID);
			rs = ps.executeQuery();
			while (rs.next()) {
				VirtualseatChose record = new VirtualseatChose();
				record.setChoseID(rs.getString("choseID"));
				record.setOpenID(rs.getString("openID"));
				record.setVirtualSeatID(rs.getString("virtualSeatID"));
				record.setNickname(rs.getString("nickname"));
				record.setHeadimgurl(rs.getString("headimgurl"));
				record.setLineNum(rs.getInt("lineNum"));
				record.setColumnNum(rs.getInt("columnNum"));
				list.add(record);
			}
		} catch (Exception e) {
			LOGGER.info("查询已选座位失败：" + e);
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return list;
	}

}