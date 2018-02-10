package com.ctvit.writewords.dao;

import com.ctvit.writewords.entity.WriteWordsActivity;
import com.ctvit.writewords.entity.WriteWordsActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WriteWordsActivityMapper {
    int countByExample(WriteWordsActivityExample example);

    int deleteByExample(WriteWordsActivityExample example);

    int deleteByPrimaryKey(String activityId);

    int insert(WriteWordsActivity record);

    int insertSelective(WriteWordsActivity record);

    List<WriteWordsActivity> selectByExample(WriteWordsActivityExample example);

    WriteWordsActivity selectByPrimaryKey(String activityId);

    int updateByExampleSelective(@Param("record") WriteWordsActivity record, @Param("example") WriteWordsActivityExample example);

    int updateByExample(@Param("record") WriteWordsActivity record, @Param("example") WriteWordsActivityExample example);

    int updateByPrimaryKeySelective(WriteWordsActivity record);

    int updateByPrimaryKey(WriteWordsActivity record);
}