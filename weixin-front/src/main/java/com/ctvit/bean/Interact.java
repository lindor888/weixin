package com.ctvit.bean;

import java.util.Date;

/**
 * 微信用户的互动信息
 * @author tqc
 *
 */
public class Interact {
	
	public static int TYPE_TEXT = 0;
	
	public static int TYPE_IMAGE = 1;
	/**
	 * 音频
	 */
	public static int TYPE_AUDIO = 2;
	/**
	 * 视频
	 */
	public static int TYPE_VIDEO = 3;
	
	private int id;
	
	private String waccountId;
	
	private String openId;
	
	private String content;
	
	private String image;
	
	private String video;
	
	private String audio;
	
	private Date insertTime;
	
	private Date updateTime;
	
	private int flag;
	
	private Integer type; //0文字 1图片 2视频 3音频

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWaccountId() {
		return waccountId;
	}

	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
