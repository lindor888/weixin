/**
 * 微信公众平台开发模式(JAVA) SDK
 */
package com.ctvit.bean;

public class OutMessage {

	private String	ToUserName;//接收方帐号（收到的OpenID） 
	private String	FromUserName;// 	开发者微信号 
	private Long	CreateTime;// 	消息创建时间 （整型） 

	public String getToUserName() {
		return ToUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
}