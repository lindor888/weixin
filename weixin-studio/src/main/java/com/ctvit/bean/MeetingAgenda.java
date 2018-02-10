package com.ctvit.bean;

import java.util.List;

/**
 * 会议日程标题
 * 
 * @author Admini
 * 
 */
public class MeetingAgenda {

	private String meetingid;
	private String meetingtheme;
	private String meetingdata;
	private String meetingaddress;
	private String createtime;
	private String starttime;
	private String createuser;
	private String waccountid;
	private String status;
	private List<MeetingContent> meetingContent;

	public String getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(String meetingid) {
		this.meetingid = meetingid;
	}

	public String getMeetingtheme() {
		return meetingtheme;
	}

	public void setMeetingtheme(String meetingtheme) {
		this.meetingtheme = meetingtheme;
	}

	public String getMeetingdata() {
		return meetingdata;
	}

	public void setMeetingdata(String meetingdata) {
		this.meetingdata = meetingdata;
	}

	public String getMeetingaddress() {
		return meetingaddress;
	}

	public void setMeetingaddress(String meetingaddress) {
		this.meetingaddress = meetingaddress;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getWaccountid() {
		return waccountid;
	}

	public void setWaccountid(String waccountid) {
		this.waccountid = waccountid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MeetingContent> getMeetingContent() {
		return meetingContent;
	}

	public void setMeetingContent(List<MeetingContent> meetingContent) {
		this.meetingContent = meetingContent;
	}

}
