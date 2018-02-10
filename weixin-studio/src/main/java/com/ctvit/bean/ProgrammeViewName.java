package com.ctvit.bean;

public class ProgrammeViewName {

	private int id;

	private int flag;

	private String programme_name;

	private String programme_id;

	private String programme_time;

	private String programme_account_name;

	private String waccountId;

	public String getProgramme_account_name() {
		return programme_account_name;
	}

	public void setProgramme_account_name(String programme_account_name) {
		this.programme_account_name = programme_account_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getProgramme_name() {
		return programme_name;
	}

	public void setProgramme_name(String programme_name) {
		this.programme_name = programme_name;
	}

	public String getProgramme_id() {
		return programme_id;
	}

	public void setProgramme_id(String programme_id) {
		this.programme_id = programme_id;
	}

	public String getProgramme_time() {
		return programme_time;
	}

	public void setProgramme_time(String programme_time) {
		this.programme_time = programme_time;
	}

	public String getWaccountId() {
		return waccountId;
	}

	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId;
	}

}
