/**
 * 微信公众平台开发模式(JAVA) SDK
 */
package com.ctvit.bean;

/**
 * 多图文消息
 *
 *
 */
public class Articles {
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	private String  Content;
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
}
