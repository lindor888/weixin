package com.ctvit.service;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import com.ctvit.bean.Groups;
import com.ctvit.bean.GroupsExample;
import com.ctvit.dao.GroupsMapper;

public class GroupsServiceImp implements GroupsMapper {
	
	public GroupsMapper groupsMapper;
	
	public GroupsMapper getGroupsMapper() {
		return groupsMapper;
	}

	public void setGroupsMapper(GroupsMapper groupsMapper) {
		this.groupsMapper = groupsMapper;
	}

	private SqlSessionFactory sqlSessionFactory;

	public int countByExample(GroupsExample example) {
		// TODO Auto-generated method stub
		return groupsMapper.countByExample(example);
	}

	public int deleteByExample(GroupsExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteByPrimaryKey(String groupsid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean insert(Groups record) {
		// TODO Auto-generated method stub
		if(groupsMapper.countByName(record.getName())>0){
			return false;
		}else{
			groupsMapper.insert(record);
			return true;
		}		
	}

	public int insertSelective(Groups record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Groups> selectByExample(GroupsExample example) {
		// TODO Auto-generated method stub
		return groupsMapper.selectByExample(example);
	}

	public Groups selectByPrimaryKey(String groupsid) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByExampleSelective(Groups record, GroupsExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByExample(Groups record, GroupsExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKeySelective(Groups record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean updateByPrimaryKey(Groups record) {
		// TODO Auto-generated method stub
		if(groupsMapper.countByName(record.getName())>0){
			return false;
		}else{
			groupsMapper.updateByPrimaryKey(record);
			return true;
		}	 
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public int countByName(String name) {
		// TODO Auto-generated method stub
		return groupsMapper.countByName(name);
	}

}
