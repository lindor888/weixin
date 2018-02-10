package com.ctvit.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctvit.bean.Followers;
import com.ctvit.util.jdbcUtils;




public class FollowerDao {
	protected static Logger log = LoggerFactory.getLogger(FollowerDao.class);
	//private Followers followers = new Followers();
	/**
	 * 插入关键词库
	 * @param word
	 */
	public void insert(Followers followers){
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			String sql = "insert into tab_wx_followers (subscribe,openId,nickname,sex,city,country,province,headimgurl,subscribe_time) values (?,?,?,?,?,?,?,?,?)";
			System.out.println("=============================================================="+sql);
			con = jdbcUtils.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, followers.getSubscribe());
			ps.setString(2, followers.getOpenid());
			ps.setString(3, followers.getNickname());
			ps.setString(4, followers.getSex());
			ps.setString(5, followers.getCity());
			ps.setString(7, followers.getCountry());
			ps.setString(6, followers.getProvince());
			ps.setString(8, followers.getHeadimgurl());
			ps.setString(9, followers.getSubscribeTime());
			ps.executeUpdate();
			log.info("[text:]FromUserName:"+followers.getOpenid()+"content:insert db sussess!");
		}catch(SQLException sqlException){
			log.error("insert错误",sqlException);
			System.out.println("db errors"+sqlException);
			log.error(followers.getOpenid() + " is exist");
		}finally{
			jdbcUtils.releaseConnection(ps, con);
		}
	}
	
	/**
	 * 查询关键词
	 * @param openId
	 * @return Followers
	 * @throws SQLException 
	 */
	public int selectCount(String openId) throws SQLException{
		//Followers value = new Followers();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		try{
			String sql = "select count(openId) from tab_wx_followers where openId = ?";
			
			con = jdbcUtils.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openId);
			System.out.println(openId);
			System.out.println(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				num =  rs.getInt(1);
				System.out.println("num="+num);
			}
			
			
/*			value.setOpenid(rs.getString("openId"));
			value.setSubscribe(rs.getString("subscribe"));
			value.setNickname(rs.getString("nickname"));
			value.setSex(rs.getString("sex"));
			value.setCity(rs.getString("city"));
			value.setCountry(rs.getString("country"));
			value.setProvince(rs.getString("province"));
			value.setHeadimgurl(rs.getString("headimgurl"));*/
			
			
		}catch(SQLException sqlException){
			log.error("db errors",sqlException);
			System.out.println("db errors"+sqlException);
			return num;
		}finally{
		//	jdbcUtils.releaseConnection(rs ,ps, con);
			jdbcUtils.releaseConnection(ps, con);
		}
		return num;
		
	}
	
	/**
	 * 删除
	 * @param openId
	 * @return
	 */
	public void delete(String openId){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			String sql = "delete from tab_wx_followers where openId = ?";
			con = jdbcUtils.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openId);
			ps.executeUpdate();
			System.out.println("[text:]FromUserName:"+openId+"content:DELETE SUCCESS");
		}catch(SQLException sqlException){
			//log.error("delete错误",sqlException);
			System.out.println("delete db errors"+sqlException);
			log.error(openId + " is not exist");
		}finally{
			jdbcUtils.releaseConnection(ps, con);
		}
	}
	public static void main(String[] args) throws SQLException {
		FollowerDao t = new FollowerDao();
		Followers followers = new Followers();
		followers.setOpenid("assdfsdfesd");
		followers.setNickname("tt");
		followers.setCity("中国");
		followers.setCountry("呼和浩特");
		followers.setSex("女");
		followers.setHeadimgurl("http://wx.qlogo.cn/mmopen/wWbsoX7swp6GlNZKmkd9kGpTOXqmibiaYty9HYf9UbqH8aXUBXM71zWSjcAxB6XXz2RiatiapyibfO1kJdU2aMI9AJS4XXBBIibDvL/0");
		followers.setSubscribe("1");
		followers.setSubscribeTime("1383683895");
		t.insert(followers);
		
		System.out.println(t.selectCount("assdfsdfesd"));
		if(t.selectCount("assdfsdfesd") == 0){
			
			t.insert(followers);
		}else{
			System.out.print("openId已存在");
			
		}
	}

}
