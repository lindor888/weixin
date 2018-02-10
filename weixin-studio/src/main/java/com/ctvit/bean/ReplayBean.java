package com.ctvit.bean;

import java.util.Date;

public class ReplayBean {

	private String replayid;
	private String waccountid;
	private String openid;
	private String replaycontent;
	private Date replaytime;
	private int replaytype;

	public String getReplayid() {
		return replayid;
	}

	public void setReplayid(String replayid) {
		this.replayid = replayid;
	}

	public String getWaccountid() {
		return waccountid;
	}

	public void setWaccountid(String waccountid) {
		this.waccountid = waccountid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getReplaycontent() {
		return replaycontent;
	}

	public void setReplaycontent(String replaycontent) {
		this.replaycontent = replaycontent;
	}

	public Date getReplaytime() {
		return replaytime;
	}

	public void setReplaytime(Date replaytime) {
		this.replaytime = replaytime;
	}

	public int getReolaytype() {
		return replaytype;
	}

	public void setReolaytype(int replaytype) {
		this.replaytype = replaytype;
	}

}
