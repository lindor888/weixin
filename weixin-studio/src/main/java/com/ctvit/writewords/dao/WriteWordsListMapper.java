package com.ctvit.writewords.dao;

import com.ctvit.writewords.entity.WriteWordsList;
import com.ctvit.writewords.entity.WriteWordsListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WriteWordsListMapper {
    int countByExample(WriteWordsListExample example);

    int deleteByExample(WriteWordsListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WriteWordsList record);

    int insertSelective(WriteWordsList record);

    List<WriteWordsList> selectByExample(WriteWordsListExample example);

    WriteWordsList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WriteWordsList record, @Param("example") WriteWordsListExample example);

    int updateByExample(@Param("record") WriteWordsList record, @Param("example") WriteWordsListExample example);

    int updateByPrimaryKeySelective(WriteWordsList record);

    int updateByPrimaryKey(WriteWordsList record);
}