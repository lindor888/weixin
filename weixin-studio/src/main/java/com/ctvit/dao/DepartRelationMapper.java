package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.AccountRelation;
import com.ctvit.bean.DepartRelation;

public interface DepartRelationMapper {

  List<DepartRelation>  selectByExample(DepartRelation record);

int deleteBydepart(DepartRelation departrelation) throws Exception;

int insert(DepartRelation relation) throws Exception;

List<DepartRelation> selectByDepartment(String id);
   

}