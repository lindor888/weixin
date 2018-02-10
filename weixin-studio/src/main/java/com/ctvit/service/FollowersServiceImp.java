package com.ctvit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.alibaba.fastjson.JSONObject;
import com.ctvit.bean.Followers;
import com.ctvit.bean.FollowersExample;
import com.ctvit.bean.FollowersExt;
import com.ctvit.bean.GraphicBean;
import com.ctvit.bean.GraphicBeanString;
import com.ctvit.bean.GroupsBean;
import com.ctvit.bean.Interact;
import com.ctvit.bean.JsonBean;
import com.ctvit.bean.News;
import com.ctvit.bean.ReplayBean;
import com.ctvit.bean.TuwenPushBean;
import com.ctvit.dao.FollowersMapper;
import com.ctvit.dao.GraphicPushMapper;
import com.ctvit.util.ActionUtil;
import com.ctvit.util.KeyGenerator;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.util.Message;

@SuppressWarnings("unused")
public class FollowersServiceImp implements FollowersMapper {

	public FollowersMapper followersMapper;
	private SqlSessionFactory sqlSessionFactory;
	private GraphicBeanString graphicBeanString;
	private GraphicPushMapper graphicPushMapper;
	private GraphicBean graphicBean;

	public GraphicBean getGraphicBean() {
		return graphicBean;
	}

	public void setGraphicBean(GraphicBean graphicBean) {
		this.graphicBean = graphicBean;
	}

	public GraphicBeanString getGraphicBeanString() {
		return graphicBeanString;
	}

	public void setGraphicBeanString(GraphicBeanString graphicBeanString) {
		this.graphicBeanString = graphicBeanString;
	}

	public GraphicPushMapper getGraphicPushMapper() {
		return graphicPushMapper;
	}

	public void setGraphicPushMapper(GraphicPushMapper graphicPushMapper) {
		this.graphicPushMapper = graphicPushMapper;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	private News news;

	public void setFollowersMapper(FollowersMapper followersMapper) {
		this.followersMapper = followersMapper;
	}

	public FollowersMapper getFollowersMapper() {
		return followersMapper;
	}

	/*
	 * public FollowersExample setQueryConditions(FollowersExample query){ if(query.getLogin()!=null && query.getLogin().equals(""))query.setLogin(null);
	 * 
	 * if(query.getUsername()!=null && query.getUsername().equals(""))query.setUsername(null); if(query.getDepartment()!=null && query.getDepartment().equals(""))query.setDepartment(null); if(query.getGroupId()!=null && query.getGroupId().equals(""))query.setGroupId(null); return query; }
	 */

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public int countByExample(FollowersExample query) {

		return followersMapper.countByExample(query);
	}

	public int deleteByExample(FollowersExample example) {

		return followersMapper.deleteByExample(example);
	}

	public int deleteByPrimaryKey(String openid) {

		return 0;
	}

	public int insert(Followers record) {

		return followersMapper.insert(record);
	}

	public int insertSelective(Followers record) {

		return 0;
	}

	// public List<Followers> selectByExample(QueryDataBean query) {

	// int page = query.getPage();
	// int rows = query.getRows();
	// int start = (page-1)*rows;
	// query.setPage(start);
	// List<Followers> result = null;
	// SqlSession session = sqlSessionFactory.openSession();
	// query = setQueryConditions(query);
	// try {
	// if(query.isPagination()){
	// result = session.selectList("com.ctvit.dao.	FollowerMapper.selectByExamplepage",query);
	// }else{
	// result = session.selectList("com.ctvit.dao.FollowersMapper.selectByExample",query);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }finally{
	// session.close();
	// }
	// return result;
	// }

	public Followers selectByPrimaryKey(String openid) {

		return followersMapper.selectByPrimaryKey(openid);
	}

	public int updateByExampleSelective(Followers record, FollowersExample example) {

		return 0;
	}

	public int updateByExample(Followers record, FollowersExample example) {

		return 0;
	}

	public int updateByPrimaryKeySelective(Followers record) {

		return 0;
	}

	public int updateByPrimaryKey(Followers record) {

		return followersMapper.updateByPrimaryKey(record);
	}

	@SuppressWarnings("unchecked")
	public List<Followers> selectByExample(FollowersExample query) {

		// return followersMapper.selectByExample(query);
		// int page = query.getPage();
		int page = query.getPage();

		int rows = query.getRows();
		int start = (page - 1) * rows;
		query.setPage(start);
		List<Followers> result = null;
		SqlSession session = sqlSessionFactory.openSession();
		// query = setQueryConditions(query);
		try {
			if (query.isPagination()) {
				result = session.selectList("com.ctvit.dao.FollowersMapper.selectListPage", query);
				// result = followersMapper.selectByExample(query);
			} else {
				// result = followersMapper.selectByExample(query);
				result = session.selectList("com.ctvit.dao.FollowersMapper.selectByExample", query);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * 查询自己添加的数据
	 */
	public List<Followers> selectMy(String waccountId) {
		return followersMapper.selectMy(waccountId);
	}

	public List<Followers> selectListPage(FollowersExample example) {
		return followersMapper.selectListPage(example);
	}

	public int deleteWaccountId(String waccountId) {

		return followersMapper.deleteWaccountId(waccountId);
	}

	public List<Followers> selectByWaccountId(String waccountId) {

		return null;
	}

	public List<Followers> selectByIds(Map<String, Object> map) {

		return followersMapper.selectByIds(map);
	}

	public List<FollowersExt> netfriend(FollowersExt bean) {
		List<FollowersExt> fw = followersMapper.netfriend(bean);
		return fw;
	}

	// public int insert(Follwers record) {

	public List<GroupsBean> findgroups(GroupsBean groupsBean) {
		List<GroupsBean> gb = followersMapper.findgroups(groupsBean);
		return gb;
	}

	public void useraddgroup(Followers list) {
		followersMapper.useraddgroup(list);

	}

	public List<FollowersExt> netInit(FollowersExt followersExt) {
		List<FollowersExt> fw = followersMapper.netInit(followersExt);
		return fw;
	}

	public int alluser(Followers followers) {
		int alluser = followersMapper.alluser(followers);
		return alluser;
	}

	public int usercount(FollowersExt followersExt) {
		int usercount = followersMapper.usercount(followersExt);
		return usercount;
	}

	public void insertgroup(GroupsBean groupsBean) {
		followersMapper.insertgroup(groupsBean);

	}

	public int blacklist(Followers fl) {
		int blacklist = followersMapper.blacklist(fl);
		return blacklist;
	}

	public Map<String, Object> fasong(Interact interact, String waccountId) {
		Map<String, Object> mapJson = new Hashtable<String, Object>();
		try {
			Message message = new Message();
			String appid = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_appid");
			String secret = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_secret");
			String result = message.sendText(appid, secret, interact.getOpenId(), interact.getContent());
			if (result.indexOf("ok") != -1) {
				mapJson.put("msg", "success");
				System.out.println("发送成功！");
			}
		} catch (Exception e) {
			mapJson.put("msg", "error");
			System.out.println("发送失败！");
			e.printStackTrace();
		}
		return mapJson;
	}

	public Map<String, Object> tui(List<String> openid, String content, String waccountId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = null;
		try {

			map.put("cnt", content);
			String appid = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_appid");
			String secret = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_secret");
			String bridgeToken = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_bridge");
			Message message = new Message();
			// interactMapper.insertpushtext(map);
			// String result = message.sendTextAll(appid, secret, openid, content);
			if (StringUtils.isNotBlank(bridgeToken)) {
				try {
					message.publishToBridgeTextAll(content, bridgeToken, openid);
					map.put("msg", "success");
					System.out.println("文本消息发送成功");
				} catch (Exception e) {
					map.put("msg", "error");
					System.out.println("文本消息发送失败");
					throw new Exception("huifu error");
				}
			} else {
				if (openid.size() == 1) {
					result = message.sendText(appid, secret, openid.get(0), content);
				} else {
					result = message.sendTextAll(appid, secret, openid, content);
				}
				if (result.indexOf("ok") != -1) {
					map.put("msg", "success");
					System.out.println("文本消息发送成功");
				} else {
					map.put("msg", "error");
					System.out.println("文本消息发送失败");
					throw new Exception("huifu error");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public Map<String, Object> tuwentui(List<String> openid, TuwenPushBean image, String waccountId) {
		String result = null;
		String paramJson = null;
		Map<String, Object> map = new HashMap<String, Object>();
		String appid = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_appid");
		String secret = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_secret");
		String bridgeToken = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_bridge");
		Message message = new Message();
		JSONObject json = new JSONObject();
		List<JsonBean> listjson = new ArrayList<JsonBean>();
		if (!"".equals(image.getTitle1())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle1());
			jsonBean.setUrl(image.getUrl1());
			jsonBean.setPicurl(image.getImageUrl1());
			listjson.add(jsonBean);
			jsonBean.setDescription(image.getDesc1());
		}
		if (!"".equals(image.getTitle2())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle2());
			jsonBean.setUrl(image.getUrl2());
			jsonBean.setPicurl(image.getImageUrl2());
			listjson.add(jsonBean);
			jsonBean.setDescription(image.getDesc2());
		}
		if (!"".equals(image.getTitle3())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle3());
			jsonBean.setUrl(image.getUrl3());
			jsonBean.setPicurl(image.getImageUrl3());
			listjson.add(jsonBean);
			jsonBean.setDescription(image.getDesc3());
		}
		if (!"".equals(image.getTitle4())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle4());
			jsonBean.setUrl(image.getUrl4());
			jsonBean.setPicurl(image.getImageUrl4());
			listjson.add(jsonBean);
			jsonBean.setDescription(image.getDesc4());
		}
		if (!"".equals(image.getTitle5())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle5());
			jsonBean.setUrl(image.getUrl5());
			jsonBean.setPicurl(image.getImageUrl5());
			listjson.add(jsonBean);
			jsonBean.setDescription(image.getDesc5());
		}
		if (!"".equals(image.getTitle6())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle6());
			jsonBean.setUrl(image.getUrl6());
			jsonBean.setPicurl(image.getImageUrl6());
			listjson.add(jsonBean);
			jsonBean.setDescription(image.getDesc6());
		}
		if (!"".equals(image.getTitle7())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle7());
			jsonBean.setUrl(image.getUrl7());
			jsonBean.setPicurl(image.getImageUrl7());
			listjson.add(jsonBean);
			jsonBean.setDescription(image.getDesc7());
		}
		if (!"".equals(image.getTitle8())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle8());
			jsonBean.setUrl(image.getUrl8());
			jsonBean.setPicurl(image.getImageUrl8());
			listjson.add(jsonBean);
			jsonBean.setDescription(image.getDesc8());
		}
		if (!"".equals(image.getTitle9())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle9());
			jsonBean.setUrl(image.getUrl9());
			jsonBean.setPicurl(image.getImageUrl9());
			listjson.add(jsonBean);
			jsonBean.setDescription(image.getDesc9());
		}
		if (!"".equals(image.getTitle10())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle10());
			jsonBean.setUrl(image.getUrl10());
			jsonBean.setPicurl(image.getImageUrl10());
			listjson.add(jsonBean);
			jsonBean.setDescription(image.getDesc10());
		}
		// graphicPushMapper.insertJson(listjson);
		news = new News();
		news.setArticles(listjson);
		if (openid.size() == 1) {
			graphicBeanString = new GraphicBeanString();
			graphicBeanString.setMsgtype("news");
			graphicBeanString.setNews(news);
			graphicBeanString.setTouser(openid.get(0));
			paramJson = json.toJSONString(graphicBeanString);
		} else {
			graphicBean = new GraphicBean();
			graphicBean.setMsgtype("news");
			graphicBean.setNews(news);
			graphicBean.setTouser(openid);
			paramJson = json.toJSONString(graphicBean);
		}

		// System.out.println(json.toJSONString(jsonBean));

		System.out.println(json.toJSONString(news));
		System.out.println(json.toJSONString(graphicBean));
		json.toJSONString(graphicBean);
		// json.toJSONString(jsonBean);
		if (StringUtils.isNotBlank(bridgeToken)) {
			try {
				message.publishToBridgetuwenAll(paramJson, bridgeToken, openid);
				map.put("msg", "success");
				// 更新
			} catch (Exception e) {
				// throw new Exception("huifu error");
				map.put("msg", "error");
			}
		} else {
			try {
				result = message.sendTwen(appid, secret, paramJson, openid);
				if (result.indexOf("ok") != -1) {
					map.put("msg", "success");
					System.out.println("图文发送成功");
				} else {
					map.put("msg", "error");
					System.out.println("图文发送失败");
				}
			} catch (Exception e) {
				map.put("msg", "error");
				e.printStackTrace();
			}

		}
		return map;
	}

	public List<FollowersExt> usearch(FollowersExt followersExt) {
		List<FollowersExt> fw = followersMapper.usearch(followersExt);
		return fw;
	}

	public void addblackUser(Interact interact) {
		interact.setFlagx(5);
		followersMapper.addblackUser(interact);

	}

	public void saveContent(ReplayBean replayBean) {
		try {
			KeyGenerator keyGenerator = new KeyGenerator();
			replayBean.setWaccountid(ActionUtil.findWaccountId());
			replayBean.setReolaytype(1);
			replayBean.setReplaytime(new Date());
			replayBean.setReplayid(keyGenerator.getKey(replayBean));
			followersMapper.saveContent(replayBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ReplayBean> selectReplay(Map<String, Object> queryJson) {
		List<ReplayBean> list = followersMapper.selectReplay(queryJson);
		return list;
	}

	// return follwersMapper.insert(record);
	// }
	//
	// public int insertSfollwersMapperelective(Follwers record) {

	// return 0;
	// }
	//
	// public List<Follwers> selectByExample(FollwersExample example) {

	// return null;
	// }
	//
	// public Follwers selectByPrimaryKey(String openid) {

	// return follwersMapper.selectByPrimaryKey(openid);
	// }
	//
	// public int updateByExampleSelective(Follwers record, FollwersExample example) {

	// return 0;
	// }
	//
	// public int updateByExample(Follwers record, FollwersExample example) {

	// return 0;
	// }
	//
	// public int updateByPrimaryKeySelective(Follwers record) {

	// return 0;
	// }
	//
	// public int updateByPrimaryKey(Follwers record) {

	// return 0;
	// }
	//
	// public int insertSelective(Follwers record) {

	// return 0;
	// }
	//
	//
}
