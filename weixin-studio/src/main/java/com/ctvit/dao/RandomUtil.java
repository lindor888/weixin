package com.ctvit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.ctvit.action.NumberLotteryGameAction;
import com.ctvit.bean.Account;
import com.ctvit.bean.ChakanBean;
import com.ctvit.bean.Followers;
import com.ctvit.bean.nickname_user;

public class RandomUtil {

	/**
	 * @param args
	 */
	private HttpServletRequest request;
	private Statement st = null;
	private ResultSet rs = null;
	private Connection ct = null;
	PreparedStatement ps = null;
	Random r = new Random();
	NumberLotteryGameAction nlg = new NumberLotteryGameAction();
	List<Integer> list = new ArrayList<Integer>();
	List<String> list2 = new ArrayList<String>();
	nickname_user user = new nickname_user();

	/**
	 * 查询总共有多少用户
	 * 
	 * @return
	 */
	public int setSeed() {
		try {
			String sql = "select cid from tab_nickname_users";
			System.out.println("==============================================================" + sql);
			ct = new connDB().getConn();
			st = ct.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				list.add(new Integer(rs.getInt(1)));
			}
			Iterator<Integer> it = list.iterator();
			// while(it.hasNext()){

			// System.out.println(it.next());

			// }

			// System.out.println(it.);
			// for(int i=0;i<4;i++){
			// System.out.println(r.nextInt(list.size()));
			// }
			// 4.查询
			// System.out.println(rs);

		} catch (SQLException sqlException) {

			System.out.println("db errors" + sqlException);

		} finally {
			if (rs != null) {
				try {
					rs.close();
					if (st != null) {
						st.close();
					}
					if (ct != null) {
						ct.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return list.size();

	}

	/**
	 * 
	 * @return 查询多个人名
	 */
	public List SelectName() {

		List cid = nlg.namelist();
		String sql = null;
		System.out.println("================" + cid.size());
		ct = new connDB().getConn();
		if (cid.size() > 5)
			try {
				for (int i = 0; i < cid.size(); i++) {
					// System.out.println(cid.get(i));
					sql = "select nickname from tab_nickname_users where cid=" + cid.get(i) + "";

					// String sql = "select cid from tab_nickname_users";
					// System.out.println("=============================================================="+sql);
					st = ct.createStatement();
					rs = st.executeQuery(sql);

					if (rs.next()) {

						// list.add(rs.getString("nickname"));
						list2.add(rs.getString("nickname"));
						System.out.print(rs.getString("nickname") + " ");

					}

					System.out.println("set g" + list2.size());
				}
			} catch (SQLException sqlException) {

				System.out.println("db errors" + sqlException);

			} finally {
				if (rs != null) {
					try {
						rs.close();
						if (st != null) {
							st.close();
						}
						if (ct != null) {
							ct.close();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		return list2;
	}

	/**
	 * 查询单个人名
	 * 
	 * @return
	 */
	public List goodluckname(int number) {

		System.out.println("numberggg " + number);
		try {
			String sql = "select nickname,imageurl from tab_nickname_users where cid=" + number + "";
			System.out.println("==============================================================" + sql);
			ct = new connDB().getConn();
			st = ct.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				list2.add(rs.getString("nickname"));
				user.setCid(number);
				user.setNickname(rs.getString("nickname"));
				user.setImageurl(rs.getString("imageurl"));
			}

		} catch (SQLException sqlException) {

			System.out.println("db errors" + sqlException);

		} finally {
			if (rs != null) {
				try {
					rs.close();
					if (st != null) {
						st.close();
					}
					if (ct != null) {
						ct.close();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return list2;

	}

	/**
	 * 将中奖用户插入表
	 */
	public void insert() {

		System.out.println("user.getCid() ++++" + user.getCid());
		String insertsql = "insert into tab_prize_user (prize_name,prize_cid,prize_url) values (?,?,?)";

		try {
			ct = new connDB().getConn();
			ps = ct.prepareStatement(insertsql);
			ps.setString(1, user.getNickname());
			ps.setInt(2, user.getCid());
			// ps.setString(3, user.getImageurl());
			ps.setString(3, "http://localhost:8080/weixin-studio/images/12.jpg");
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("finally")
	public ArrayList<nickname_user> getUser() {

		ArrayList<nickname_user> al = new ArrayList<nickname_user>();

		try {
			ct = new connDB().getConn();
			// 3.获得statment对象

			st = ct.createStatement();
			// 4.查询

			rs = st.executeQuery("select * from tab_prize_user");
			while (rs.next()) {

				nickname_user nu = new nickname_user();
				nu.setCid(rs.getInt("prize_cid"));
				nu.setNickname(rs.getString("prize_name"));
				nu.setImageurl(rs.getString("prize_url"));
				al.add(nu);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return al;

	}

	public List<Followers> tiaodan(String[] string) {

		ArrayList<Followers> al = new ArrayList<Followers>();

		try {
			if (string.length > 0) {

				ct = new connDB().getConn();
				// 3.获得statment对象

				st = ct.createStatement();
				// 4.查询
				for (int i = 0; i < string.length; i++) {
					String tsql = "select * from (select a.id,a.content,a.image,b.nickname,b.sex,b.city from tab_interact a , tab_wx_followers b  where a.open_id=b.openId) as d where d.id =" + Integer.parseInt(string[i]);
					rs = st.executeQuery(tsql);
					while (rs.next()) {

						Followers follower = new Followers();
						follower.setCity(rs.getString("city"));
						follower.setNickname(rs.getString("nickname"));
						follower.setSex(rs.getString("sex"));
						al.add(follower);

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return al;

	}

	/**
	 * 保存节目单名，插入表
	 */
	public void programmeInsert(String name, String programmeId, Account account) {

		System.out.println("--------------------------------" + account.getUsername());
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(now);
		String insertsql = "insert into tab_programme_name (programme_name,programme_id,programme_time,programme_account_name,flag) values (?,?,?,?,?)";

		try {
			ct = new connDB().getConn();
			ps = ct.prepareStatement(insertsql);
			ps.setString(1, name);
			ps.setString(2, programmeId);
			ps.setString(3, time);
			ps.setString(4, account.getUsername());
			ps.setInt(5, 0);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 保存节目单名，插入表
	 * 
	 * @param sql
	 */
	public void truncateTable() {

		String sql = "truncate table tab_programme";

		try {
			ct = new connDB().getConn();
			ps = ct.prepareStatement(sql);

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void programmeSaveProgrammeId(String programmeId) {

		// String insertsql = "insert into tab_programme_save (waccountId,content,image,insertTime,updateTime,flag,huifu,video,audio,type,xuanzhuan,nickname,sex,city,country, province,headimgurl) select waccountId,content,image,insertTime,updateTime,flag,huifu,video,audio,type,xuanzhuan,nickname,sex,city,country, province,headimgurl from tab_programme";
		String sql = "insert into tab_programme_save (programme_save_id) values (?);";
		try {
			ct = new connDB().getConn();
			ps = ct.prepareStatement(sql);
			ps.setString(1, programmeId);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * 查询节目单Chakanbean
	 */

	public List<ChakanBean> chakanbean(String programmeId, int currentPage) {

		ArrayList<ChakanBean> al = new ArrayList<ChakanBean>();

		System.out.println("ct  " + currentPage);
		try {

			ct = new connDB().getConn();
			// 3.获得statment对象

			st = ct.createStatement();
			// 4.查询

			String sql = "select * from tab_programme_save where programme_save_id ='" + programmeId + "' order by orderId desc limit " + currentPage + ",10";
			System.out.println("sql ======" + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {

				ChakanBean chakanBean = new ChakanBean();
				chakanBean.setId(rs.getInt("id"));
				chakanBean.setOrderId(rs.getString("orderId"));
				chakanBean.setProgramme_save_id(rs.getString("programme_save_id"));
				chakanBean.setWaccountId("waccountId");
				chakanBean.setOpenId(rs.getString("openId"));
				chakanBean.setContent(rs.getString("content"));
				chakanBean.setImage(rs.getString("image"));
				chakanBean.setInsertTime(rs.getDate("insertTime"));
				chakanBean.setUpdateTime(rs.getDate("updateTime"));
				chakanBean.setFlag(rs.getInt("flag"));
				chakanBean.setHuifu(rs.getString("huifu"));
				chakanBean.setVideo(rs.getString("video"));
				chakanBean.setAudio(rs.getString("audio"));
				chakanBean.setType(rs.getInt("type"));
				chakanBean.setXuanzhuan(rs.getInt("xuanzhuan"));
				chakanBean.setNickname(rs.getString("nickname"));
				chakanBean.setSex(rs.getString("sex"));
				chakanBean.setCity(rs.getString("city"));
				chakanBean.setCountry(rs.getString("country"));
				chakanBean.setHeadimgurl(rs.getString("headimgurl"));
				chakanBean.setPid(rs.getInt("pid"));

				al.add(chakanBean);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return al;

	}

	public void shanchujiemudan(String proId) {
		// delete from tab_programme_save where programme_save_id
		String sql = "delete from tab_programme_name where  programme_id='" + proId + "'";
		try {
			ct = new connDB().getConn();
			ps = ct.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void shanchujiemudanneirong(String proId) {
		// delete from tab_programme_save where programme_save_id
		String sql = "delete from tab_programme_save where programme_save_id='" + proId + "'";
		try {
			ct = new connDB().getConn();
			ps = ct.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void xiugainame(String programmename, String programmeId) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = dateFormat.format(now);
		String sql = "update tab_programme_name set programme_name = '" + programmename + "',programme_time = '" + time + "' where programme_id='" + programmeId + "'";
		try {
			ct = new connDB().getConn();
			ps = ct.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public List<String> selectPid(String save_id) {
		ArrayList<String> al = new ArrayList<String>();

		try {

			ct = new connDB().getConn();
			// 3.获得statment对象

			st = ct.createStatement();
			// 4.查询

			String sql = "select pid from tab_programme_save where programme_save_id ='" + save_id + "' ";
			System.out.println("sql ======" + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {

				al.add(rs.getString("pid"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return al;
	}

	public int selectPageCountSave(String programmeId) {
		int rowCount = 0;
		String sql = "select count(*) from tab_programme_save where programme_save_id='" + programmeId + "'";
		try {
			ct = new connDB().getConn();
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {

				rowCount = rs.getInt(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	public List<ChakanBean> chakanbean(String programme_id) {
		ArrayList<ChakanBean> al = new ArrayList<ChakanBean>();

		try {

			ct = new connDB().getConn();
			// 3.获得statment对象

			st = ct.createStatement();
			// 4.查询

			String sql = "select * from tab_programme_save where programme_save_id ='" + programme_id + "' order by orderId desc ";
			System.out.println("sql ======" + sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {

				ChakanBean chakanBean = new ChakanBean();
				chakanBean.setId(rs.getInt("id"));
				chakanBean.setOrderId(rs.getString("orderId"));
				chakanBean.setProgramme_save_id(rs.getString("programme_save_id"));
				chakanBean.setWaccountId("waccountId");
				chakanBean.setOpenId(rs.getString("openId"));
				chakanBean.setContent(rs.getString("content"));
				chakanBean.setImage(rs.getString("image"));
				chakanBean.setInsertTime(rs.getDate("insertTime"));
				chakanBean.setUpdateTime(rs.getDate("updateTime"));
				chakanBean.setFlag(rs.getInt("flag"));
				chakanBean.setHuifu(rs.getString("huifu"));
				chakanBean.setVideo(rs.getString("video"));
				chakanBean.setAudio(rs.getString("audio"));
				chakanBean.setType(rs.getInt("type"));
				chakanBean.setXuanzhuan(rs.getInt("xuanzhuan"));
				chakanBean.setNickname(rs.getString("nickname"));
				chakanBean.setSex(rs.getString("sex"));
				chakanBean.setCity(rs.getString("city"));
				chakanBean.setCountry(rs.getString("country"));
				chakanBean.setHeadimgurl(rs.getString("headimgurl"));
				chakanBean.setPid(rs.getInt("pid"));

				al.add(chakanBean);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ct != null) {
					ct.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return al;

	}

}
