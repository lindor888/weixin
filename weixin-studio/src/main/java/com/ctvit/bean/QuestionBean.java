package com.ctvit.bean;

import java.util.List;

public class QuestionBean {

	private String questionid;
	private String headtitleid;
	private String questionname;
	private List<OptionBean> optionBean;

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getHeadtitleid() {
		return headtitleid;
	}

	public void setHeadtitleid(String headtitleid) {
		this.headtitleid = headtitleid;
	}

	public String getQuestionname() {
		return questionname;
	}

	public void setQuestionname(String questionname) {
		this.questionname = questionname;
	}

	public List<OptionBean> getOptionBean() {
		return optionBean;
	}

	public void setOptionBean(List<OptionBean> optionBean) {
		this.optionBean = optionBean;
	}

}
