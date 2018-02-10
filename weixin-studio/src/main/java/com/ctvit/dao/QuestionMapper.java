package com.ctvit.dao;

import java.util.List;
import java.util.Map;

import com.ctvit.bean.HeadtitleBean;
import com.ctvit.bean.OptionBean;
import com.ctvit.bean.QuestionBean;
import com.ctvit.bean.QuestionStat;

public interface QuestionMapper {

	void saveheadtitle(HeadtitleBean headtitleBean);

	void savequestion(List<QuestionBean> listQuestion);

	void saveoption(List<OptionBean> listOption);

	List<HeadtitleBean> findList();

	List<QuestionBean> findquestion(QuestionBean questionBean);

	List<OptionBean> listoption(OptionBean optionBean);

	List<QuestionStat> getquestionStat(QuestionStat questionStat);

	OptionBean option(Map<String, Object> map);

	void update(OptionBean optionBean);

	HeadtitleBean getlist();

	void questionstate(HeadtitleBean headtitleBean);

	OptionBean updateoption(OptionBean optionBean);

	void updatetotal(OptionBean optionBean);

	void insertstat(QuestionStat questionStat);

}