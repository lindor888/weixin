package com.ctvit.dao;

import com.ctvit.bean.Groups;
import com.ctvit.bean.GroupsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupsMapper {
    int countByExample(GroupsExample example);
    
    int countByName(String name);

    int deleteByExample(GroupsExample example);

    int deleteByPrimaryKey(String groupsid);

    boolean insert(Groups record);

    int insertSelective(Groups record);

    List<Groups> selectByExample(GroupsExample example);

    Groups selectByPrimaryKey(String groupsid);

    int updateByExampleSelective(@Param("record") Groups record, @Param("example") GroupsExample example);

    int updateByExample(@Param("record") Groups record, @Param("example") GroupsExample example);

    int updateByPrimaryKeySelective(Groups record);

    boolean updateByPrimaryKey(Groups record);
}