package com.ctvit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ctvit.bean.Followers;
import com.ctvit.bean.FollowersExample;
import com.ctvit.bean.FollowersExt;
import com.ctvit.bean.GroupsBean;
import com.ctvit.bean.Interact;
import com.ctvit.bean.ReplayBean;

public interface FollowersMapper {
	int countByExample(FollowersExample query);

	int deleteByExample(FollowersExample example);

	int deleteByPrimaryKey(String openid);

	int deleteWaccountId(String waccountId);

	int insert(Followers record);

	int insertSelective(Followers record);

	List<Followers> selectByExample(FollowersExample example);

	List<Followers> selectListPage(FollowersExample example);

	List<Followers> selectByWaccountId(String waccountId);

	Followers selectByPrimaryKey(String openid);

	int updateByExampleSelective(@Param("record") Followers record, @Param("example") FollowersExample example);

	int updateByExample(@Param("record") Followers record, @Param("example") FollowersExample example);

	int updateByPrimaryKeySelective(Followers record);

	int updateByPrimaryKey(Followers record);

	List<Followers> selectMy(String waccountId);

	List<Followers> selectByIds(Map<String, Object> map);

	List<FollowersExt> netfriend(FollowersExt bean);

	List<GroupsBean> findgroups(GroupsBean groupsBean);

	void useraddgroup(Followers list);

	List<FollowersExt> netInit(FollowersExt followersExt);

	int alluser(Followers followers);

	int usercount(FollowersExt followersExt);

	void insertgroup(GroupsBean groupsBean);

	int blacklist(Followers fl);

	List<FollowersExt> usearch(FollowersExt followersExt);

	void addblackUser(Interact interact);

	void saveContent(ReplayBean replayBean);

	List<ReplayBean> selectReplay(Map<String, Object> queryJson);
}