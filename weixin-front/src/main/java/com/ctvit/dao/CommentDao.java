package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ctvit.bean.Comment;
import com.ctvit.util.JdbcUtil;

public class CommentDao {

	private static Logger LOG = Logger.getLogger(CommentDao.class);

	/**
	 * 获得评论
	 * 
	 * @author guosidi
	 * @date 2015年9月29日 上午9:39:07
	 * @param accountID
	 * @param reservationID
	 * @return
	 * @return List<Comment>
	 */
	public List<Comment> getList(String reservationID) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList<Comment>();

		try {
			String sql = "SELECT comment_id,openid,userhead,nickname,comment_time,content FROM tab_epg_comment WHERE reservation_id=? AND parent_id='0' ORDER BY comment_time DESC;";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ps.setString(1, accountID);
			ps.setString(1, reservationID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setComment_id(rs.getString("comment_id"));
				comment.setUserhead(rs.getString("userhead"));
				comment.setNickname(rs.getString("nickname"));
				comment.setContent(rs.getString("content"));
				comment.setComment_time(rs.getString("comment_time"));
				list.add(comment);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("查询评论错误");
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return list;
	}

	/**
	 * 
	 * @author guosidi
	 * @date 2015年10月10日 下午5:28:11
	 * @param reservationID
	 * @return
	 * @return List<Comment>
	 */
	public List<Comment> getReplayList(String parentid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList<Comment>();

		try {
			String sql = "SELECT comment_id,openid,userhead,nickname,comment_time,content FROM tab_epg_comment WHERE parent_id=? ORDER BY comment_time DESC;";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ps.setString(1, accountID);
			ps.setString(1, parentid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setComment_id(rs.getString("comment_id"));
				comment.setUserhead(rs.getString("userhead"));
				comment.setNickname(rs.getString("nickname"));
				comment.setContent(rs.getString("content"));
				comment.setComment_time(rs.getString("comment_time"));
				comment.setParent_id(parentid);
				list.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("查询评论回复错误");
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return list;
	}

	/**
	 * 
	 * @author guosidi
	 * @date 2015年9月29日 上午9:50:39
	 * @param comment
	 * @return void
	 */
	public boolean addComment(Comment comment) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = false;
		List<Comment> list = new ArrayList<Comment>();
		try {
			String sql = "INSERT INTO tab_epg_comment(comment_id,openid,userhead,nickname,content,comment_time,reservation_id,account_id,parent_id) VALUES (?,?,?,?,?,NOW(),?,?,?)";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, comment.getComment_id());
			ps.setString(2, comment.getOpenid());
			ps.setString(3, comment.getUserhead());
			ps.setString(4, comment.getNickname());
			ps.setString(5, comment.getContent());
			ps.setString(6, comment.getReservation_id());
			ps.setString(7, comment.getAccount_id());
			ps.setString(8, comment.getParent_id());
			result = ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("插入评论错误");
		} finally {
			JdbcUtil.releaseConnection(ps, con);
		}
		return result;
	}
}
