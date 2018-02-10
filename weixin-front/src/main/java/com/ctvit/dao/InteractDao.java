package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctvit.bean.Interact;
import com.ctvit.util.JdbcUtil;

/**
 * 插入互动信息
 * @author Administrator
 *
 */
public class InteractDao {
	
	protected static Logger log = LoggerFactory.getLogger(InteractDao.class);
	
	/**
	 * 插入数据
	 * @param interact
	 */
	public void insert(Interact interact){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			String sql = "insert into tab_interact (waccount_id,open_id,content,image,video,audio,type,update_time) values (?,?,?,?,?,?,?,?)";
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, interact.getWaccountId());
			ps.setString(2, interact.getOpenId());
			ps.setString(3, interact.getContent()==null?"":interact.getContent());
			ps.setString(4, interact.getImage()==null?"":interact.getImage());
			ps.setString(5, interact.getVideo()==null?"":interact.getVideo());
			ps.setString(6, interact.getAudio()==null?"":interact.getAudio());
			ps.setInt(7, interact.getType()==null?0:interact.getType());
			ps.setTimestamp(8, new Timestamp(new Date().getTime()));
			ps.executeUpdate();
		}catch(SQLException sqlException){
			log.error("insert错误",sqlException);
		}finally{
			JdbcUtil.releaseConnection(ps, con);
		}
	}

}
