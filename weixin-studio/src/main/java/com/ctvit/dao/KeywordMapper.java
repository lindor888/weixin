package com.ctvit.dao;

import java.util.List;
import java.util.Map;

import com.ctvit.bean.Keyword;

public interface KeywordMapper {
		List<Keyword> selectKeyWordList(Keyword keyword)throws Exception;
		void insert(Keyword keyword)throws Exception;
		void delete(Keyword keyword)throws Exception;
		Keyword selectKeyWordById(Keyword keyword)throws Exception;
		Keyword selectKeyWordText(Keyword keyword)throws Exception;
		Keyword selectKeyWordTuWen(Keyword keyword)throws Exception;
		int selectKeyWordCount(Keyword keyword)throws Exception;
		Keyword selectkwdByTitle(Keyword keyword)throws Exception;
		Keyword selectkwdTextByTitle(Keyword keyword)throws Exception;
		Keyword selectkwdTuWenByTitle(Keyword keyword)throws Exception;
		void update(Keyword keyword)throws Exception;
		Keyword selectwordByName(Keyword keyword)throws Exception;
		List<Keyword> selectKeyWordListByMaterial(Keyword keyword)throws Exception;
		void deleteKeyWordByMaterial(Keyword keyword)throws Exception;
		void batchAddKeyWord(List<Keyword> keywords)throws Exception;
		String selectBothRuleByMaterial(Map<String, Object> params)throws Exception;
		List<Keyword>selectKeyWordRule(String waccountId)throws Exception;
		List<Keyword>selectKwdListbyGraphic(Keyword keyword)throws Exception;
		Integer countByNameAndWaccount(Map<String, Object> param) throws Exception;
		int countByNameNotMaterial(Map<String, Object> param);
}
