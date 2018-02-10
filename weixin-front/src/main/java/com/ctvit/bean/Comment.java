package com.ctvit.bean;

/**
 * 用户评论的实体类
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年9月28日 下午5:36:08
 */

public class Comment {

	// 评论id
	private String comment_id;
	// 用户的openid
	private String openid;
	// 用户头像
	private String userhead;
	// 用户昵称
	private String nickname;
	// 评论时间
	private String comment_time;
	// 评论内容
	private String content;
	// 观看的视频ID
	private String reservation_id;
	// 公众账号的id
	private String account_id;
	// 父级评论，多级评论使用
	private String parent_id;

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUserhead() {
		return userhead;
	}

	public void setUserhead(String userhead) {
		this.userhead = userhead;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

}
