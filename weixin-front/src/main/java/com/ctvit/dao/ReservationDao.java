package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctvit.bean.LocationMessage;
import com.ctvit.bean.Mapkeyword;
import com.ctvit.bean.Reservation;
import com.ctvit.bean.ReservationSub;
import com.ctvit.util.JdbcUtil;

/**
 * epg 节目单数据查询接口
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年9月2日 上午10:14:47
 */

public class ReservationDao {
	private static Logger log = LoggerFactory.getLogger(FollowerDao.class);

	/**
	 * 查询时间段的节目列表，时间段是指一天内
	 * 
	 * @author guosidi
	 * @date 2015年9月2日 上午10:15:41
	 * @param start
	 * @param end
	 * @return
	 * @return List<Reservation>
	 */
	public List<Reservation> getListByDate(String start, String end) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Reservation> list = new ArrayList<Reservation>();
		try {
			String sql = "SELECT reservation_id,waccount_id,program_name,start_time,end_time,push_time,live_url FROM tab_reservation WHERE start_time between ? AND ?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, start);
			ps.setString(2, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				Reservation reservation = new Reservation();
				reservation.setLive_url(rs.getString("live_url"));
				reservation.setProgram_name(rs.getString("program_name"));
				reservation.setReservation_id(rs.getString("reservation_id"));
				reservation.setStart_time(rs.getTimestamp("start_time"));
				reservation.setPush_time(rs.getTimestamp("push_time"));
				reservation.setEnd_time(rs.getTimestamp("end_time"));
				reservation.setWaccount_id(rs.getString("waccount_id"));
				list.add(reservation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return list;
	}

	/**
	 * 查询一条 节目信息
	 * 
	 * @author guosidi
	 * @date 2015年9月2日 上午10:16:41
	 * @param epgid
	 * @return
	 * @return Reservation
	 */
	public Reservation getOne(String epgid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reservation reservation = new Reservation();
		List<Reservation> list = new ArrayList<Reservation>();
		try {
			String sql = "SELECT reservation_id,waccount_id,program_name,start_time,end_time,push_time,live_url FROM tab_reservation WHERE reservation_id=?;";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, epgid);
			rs = ps.executeQuery();
			if (rs.next()) {
				reservation.setLive_url(rs.getString("live_url"));
				reservation.setProgram_name(rs.getString("program_name"));
				reservation.setReservation_id(rs.getString("reservation_id"));
				reservation.setStart_time(rs.getTimestamp("start_time"));
				reservation.setPush_time(rs.getTimestamp("push_time"));
				reservation.setEnd_time(rs.getTimestamp("end_time"));
				reservation.setWaccount_id(rs.getString("waccount_id"));
				list.add(reservation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return reservation;
	}

	public List<ReservationSub> getReservationSubList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ReservationSub> list = new ArrayList<ReservationSub>();
		try {
			String sql = "select * from tab_reservation_subscription where flag = ?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			while (rs.next()) {
				ReservationSub reservationSub = new ReservationSub();
				reservationSub.setReservation_id(rs.getString("reservation_id"));
				reservationSub.setSubscription_id(rs.getString("subscription_id"));
				reservationSub.setOpen_id(rs.getString("open_id"));
				reservationSub.setFlag(rs.getShort("flag"));
				list.add(reservationSub);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return list;
	}

	public Reservation getreservation(ReservationSub reservationSub) {
		Reservation reservations = new Reservation();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from tab_reservation where reservation_id = ?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, reservationSub.getReservation_id());
			rs = ps.executeQuery();
			while (rs.next()) {

				reservations.setLive_url(rs.getString("live_url"));
				reservations.setProgram_name(rs.getString("program_name"));
				reservations.setReservation_id(rs.getString("reservation_id"));
				reservations.setStart_time(rs.getTimestamp("start_time"));
				reservations.setPush_time(rs.getTimestamp("push_time"));
				reservations.setEnd_time(rs.getTimestamp("end_time"));
				reservations.setWaccount_id(rs.getString("waccount_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return reservations;
	}

	public void setFlag(String subscription_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "update tab_reservation_subscription set flag =? where subscription_id =?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, 2);
			ps.setString(2, subscription_id);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}

	}

	public void insertAppid(String accessID, String appid, String secret) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "update tab_appid set param_id = ?, appid = ? , secret = ? where waccount_id = ?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, accessID);
			ps.setString(2, appid);
			ps.setString(3, secret);
			ps.setString(4, "1");
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}

	}

	public String selectAppid() {
		Connection con = null;
		String param_id = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select param_id from tab_appid where waccount_id = ?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, "1");
			rs = ps.executeQuery();
			while (rs.next()) {
				param_id = rs.getString("param_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return param_id;
	}

	public List<Mapkeyword> getMapKey() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Mapkeyword> list = new ArrayList<Mapkeyword>();
		try {
			String sql = "select * from tab_mapkeyword";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Mapkeyword mapkeyword = new Mapkeyword();
				mapkeyword.setMapKeywordID(rs.getString("mapKeywordID"));
				mapkeyword.setMapKeywordName(rs.getString("mapKeywordName"));
				mapkeyword.setCreateuser(rs.getString("createuser"));
				mapkeyword.setCreatetime(rs.getString("createtime"));
				mapkeyword.setMapstatus(rs.getInt("mapstatus"));
				list.add(mapkeyword);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return list;
	}

	public LocationMessage selectLocation(String fromUser, String toUser) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LocationMessage locationMessage = new LocationMessage();
		try {
			String sql = "select * from tab_location where FromUserName=? and ToUserName=?";

			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, fromUser);
			ps.setString(2, toUser);
			rs = ps.executeQuery();
			if (rs.next()) {
				locationMessage.setLatitude(rs.getString("Latitude"));
				locationMessage.setLongitude(rs.getString("Longitude"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return locationMessage;
	}

	public String selectomg(String id) {
		Connection con = null;
		String param = null;
		String getString = null;
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (id.indexOf("Grap") > -1) {
				sql = "select tuwenxml from tab_wx_graphicmaterial where id = ?";
				getString = "tuwenxml";
			}
			if (id.indexOf("Text") > -1) {
				sql = "select wenbenxml from tab_wx_textmaterial where id = ?";
				getString = "wenbenxml";
			}
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				param = rs.getString(getString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return param;
	}
}
