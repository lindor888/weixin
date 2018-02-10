package com.ctvit.bean;

public class AccessTokenBean {

	private int id;
	private String waccount_id;
	private String weixin_token;
	private String access_token;
	private String get_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWaccount_id() {
		return waccount_id;
	}

	public void setWaccount_id(String waccount_id) {
		this.waccount_id = waccount_id;
	}

	public String getWeixin_token() {
		return weixin_token;
	}

	public void setWeixin_token(String weixin_token) {
		this.weixin_token = weixin_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getGet_time() {
		return get_time;
	}

	public void setGet_time(String get_time) {
		this.get_time = get_time;
	}

}
