package com.ctvit.service;

import java.util.List;

import com.ctvit.bean.DepartRelation;
import com.ctvit.dao.DepartRelationMapper;

public class DepartRelationServiceImpl implements DepartRelationMapper{
	private DepartRelationMapper departRelationMapper;

	public DepartRelationMapper getDepartRelationMapper() {
		return departRelationMapper;
	}

	public void setDepartRelationMapper(DepartRelationMapper departRelationMapper) {
		this.departRelationMapper = departRelationMapper;
	}

	public List<DepartRelation> selectByExample(DepartRelation record) {
		return departRelationMapper.selectByExample(  record);
	}

	public  int insert(DepartRelation relation) throws Exception {
			
			return departRelationMapper.insert(relation);
		}

	public int deleteBydepart(DepartRelation departrelation) throws Exception {
		// TODO Auto-generated method stub
		return departRelationMapper.deleteBydepart(departrelation);
	}
	
	
	public List<DepartRelation> selectByDepartment(String id) {
		// TODO Auto-generated method stub
		return departRelationMapper.selectByDepartment(id);
	}
	
}
