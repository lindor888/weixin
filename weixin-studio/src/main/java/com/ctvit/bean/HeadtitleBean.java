package com.ctvit.bean;

import java.util.List;

public class HeadtitleBean {

	private String headtitleid;
	private String headtitlename;
	private String headtitletype;
	private String status;
	private String createtime;
	private List<QuestionBean> questionBean;
	private String waccountId;

	public String getWaccountId() {
		return waccountId;
	}

	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId;
	}

	public String getHeadtitleid() {
		return headtitleid;
	}

	public void setHeadtitleid(String headtitleid) {
		this.headtitleid = headtitleid;
	}

	public String getHeadtitlename() {
		return headtitlename;
	}

	public void setHeadtitlename(String headtitlename) {
		this.headtitlename = headtitlename;
	}

	public String getHeadtitletype() {
		return headtitletype;
	}

	public void setHeadtitletype(String headtitletype) {
		this.headtitletype = headtitletype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatetime() {
		return createtime;
	}

	public List<QuestionBean> getQuestionBean() {
		return questionBean;
	}

	public void setQuestionBean(List<QuestionBean> questionBean) {
		this.questionBean = questionBean;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
