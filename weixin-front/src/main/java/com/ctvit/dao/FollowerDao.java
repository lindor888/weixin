package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctvit.bean.AccessTokenBean;
import com.ctvit.bean.Followers;
import com.ctvit.bean.Waccount;
import com.ctvit.util.JdbcUtil;

public class FollowerDao {
	protected static Logger log = LoggerFactory.getLogger(FollowerDao.class);

	// private Followers followers = new Followers();
	/**
	 * 插入关键词库
	 * 
	 * @param word
	 */
	public void insert(Followers followers) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "insert into tab_wx_followers (subscribe,openId,nickname,sex,city,country,province,headimgurl,subscribe_time,waccountId,groupsId) values (?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println("==============================================================" + sql);
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, followers.getSubscribe());
			ps.setString(2, followers.getOpenid());
			System.out.print("+++++++++++++++++++++++++++++++++" + followers.getNickname());
			ps.setString(3, followers.getNickname());
			ps.setString(4, followers.getSex());
			ps.setString(5, followers.getCity());
			ps.setString(7, followers.getCountry());
			ps.setString(6, followers.getProvince());
			ps.setString(8, followers.getHeadimgurl());
			ps.setString(9, followers.getSubscribeTime());
			ps.setString(10, followers.getWaccountId());
			ps.setInt(11, followers.getGroupsId());
			ps.executeUpdate();
			log.info("[text:]FromUserName:" + followers.getNickname() + "content:insert db sussess!");
		} catch (SQLException sqlException) {
			log.error("insert错误", sqlException);
			System.out.println("db errors" + sqlException);
			log.error(followers.getOpenid() + " is exist");
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
	}

	/**
	 * 更新用户信息
	 */
	public void updateUser(Followers followers) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "update tab_wx_followers set subscribe = ?,nickname = ?,sex = ?,city = ?,country = ?,province = ?,headimgurl = ?,subscribe_time = ? where openId = ?";
			System.out.println("============更新用户信息" + sql);
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, followers.getSubscribe());
			// ps.setString(2, followers.getOpenid());
			System.out.print("+++++++" + followers.getNickname());
			ps.setString(2, followers.getNickname());
			ps.setString(3, followers.getSex());
			ps.setString(4, followers.getCity());
			ps.setString(5, followers.getCountry());
			ps.setString(6, followers.getProvince());
			ps.setString(7, followers.getHeadimgurl());
			ps.setString(8, followers.getSubscribeTime());
			ps.setString(9, followers.getOpenid());
			ps.executeUpdate();
			log.info("[text:]FromUserName:" + followers.getNickname() + "content:insert db sussess!");
		} catch (SQLException sqlException) {
			log.error("insert错误", sqlException);
			System.out.println("db errors" + sqlException);
			log.error(followers.getOpenid() + " is exist");
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
	}

	/**
	 * 查询关键词
	 * 
	 * @param openId
	 * @return Followers
	 * @throws SQLException
	 */
	public int selectCount(String openId) throws SQLException {
		// Followers value = new Followers();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		try {
			String sql = "select count(openId) from tab_wx_followers where openId = ?";

			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openId);
			System.out.println(openId);
			System.out.println(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1);
				System.out.println("num=" + num);
			}

			/*
			 * value.setOpenid(rs.getString("openId")); value.setSubscribe(rs.getString("subscribe")); value.setNickname(rs.getString("nickname")); value.setSex(rs.getString("sex")); value.setCity(rs.getString("city")); value.setCountry(rs.getString("country")); value.setProvince(rs.getString("province")); value.setHeadimgurl(rs.getString("headimgurl"));
			 */

		} catch (SQLException sqlException) {
			log.error("db errors", sqlException);
			System.out.println("db errors" + sqlException);
			return num;
		} finally {
			// JdbcUtil.releaseConnection(rs ,ps, con);
			JdbcUtil.releaseConnection(ps, con);
		}
		return num;

	}

	/**
	 * 删除
	 * 
	 * @param openId
	 * @return
	 */
	public void delete(String openId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "delete from tab_wx_followers where openId = ?";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openId);
			ps.executeUpdate();
			System.out.println("[text:]FromUserName:" + openId + "content:DELETE SUCCESS");
		} catch (SQLException sqlException) {
			// log.error("delete错误",sqlException);
			System.out.println("delete db errors" + sqlException);
			log.error(openId + " is not exist");
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
	}

	/**
	 * 查询公众账号
	 * 
	 * @param originalId
	 * @return Waccount
	 * @throws SQLException
	 */

	public Waccount selectWaccountByOriginalId(String originalId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Waccount waccount = new Waccount();
		try {
			String sql = "select waccount_id,originalId,name,token,type,app_id,app_secret from tab_waccount where originalId = ?";

			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, originalId);
			System.out.println(originalId);
			System.out.println(sql);
			rs = ps.executeQuery();

			if (rs.next()) {

				waccount.setWaccountId(rs.getString("waccount_id"));
				// System.out.println(rs.getString(1));
				System.out.println(rs.getString("waccount_id"));
				waccount.setOriginalId(rs.getString("originalId"));
				System.out.println(rs.getString("originalId"));
				waccount.setName(rs.getString("name"));
				waccount.setToken(rs.getString("token"));
				waccount.setType(rs.getByte("type"));
				waccount.setAppId(rs.getString("app_id"));
				waccount.setAppSecret(rs.getString("app_secret"));
			}

			/*
			 * value.setOpenid(rs.getString("openId")); value.setSubscribe(rs.getString("subscribe")); value.setNickname(rs.getString("nickname")); value.setSex(rs.getString("sex")); value.setCity(rs.getString("city")); value.setCountry(rs.getString("country")); value.setProvince(rs.getString("province")); value.setHeadimgurl(rs.getString("headimgurl"));
			 */

		} catch (SQLException sqlException) {
			log.error("db errors", sqlException);
			System.out.println("db errors" + sqlException);
			return waccount;
		} finally {
			// JdbcUtil.releaseConnection(rs ,ps, con);
			JdbcUtil.releaseConnection(ps, con);
		}
		return waccount;

	}

	// 查询服务号个数
	public List<AccessTokenBean> selectAccess() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AccessTokenBean> list = new ArrayList<AccessTokenBean>();
		try {
			String sql = "select id,waccount_id,weixin_token,access_token,get_time from tab_access_token";

			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				AccessTokenBean access = new AccessTokenBean();
				access.setId(rs.getInt("id"));
				access.setAccess_token(rs.getString("access_token"));
				access.setWaccount_id(rs.getString("waccount_id"));
				access.setWeixin_token(rs.getString("weixin_token"));
				access.setGet_time(rs.getString("get_time"));
				list.add(access);
			}
		} catch (SQLException sqlException) {
			log.error("db errors", sqlException);
			System.out.println("db errors" + sqlException);
			return list;
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return list;
	}

	public void updateAccessToken(AccessTokenBean bean) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "update tab_access_token set access_token = ?,get_time = ? where id = ?";
			System.out.println("============更新AccessToken" + sql);
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bean.getAccess_token());
			ps.setString(2, bean.getGet_time());
			ps.setInt(3, bean.getId());
			ps.executeUpdate();
		} catch (SQLException sqlException) {
			log.error("insert错误", sqlException);
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
	}

	public AccessTokenBean selectBywxToken(AccessTokenBean bean) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AccessTokenBean access = new AccessTokenBean();
		try {
			String sql = "select id,waccount_id,weixin_token,access_token,get_time from tab_access_token where weixin_token = ?";

			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bean.getWeixin_token());
			rs = ps.executeQuery();

			if (rs.next()) {
				access.setId(rs.getInt("id"));
				access.setAccess_token(rs.getString("access_token"));
				access.setWaccount_id(rs.getString("waccount_id"));
				access.setWeixin_token(rs.getString("weixin_token"));
				access.setGet_time(rs.getString("get_time"));
			}
		} catch (SQLException sqlException) {
			log.error("db errors", sqlException);
			System.out.println("db errors" + sqlException);
			return access;
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return access;
	}
}
