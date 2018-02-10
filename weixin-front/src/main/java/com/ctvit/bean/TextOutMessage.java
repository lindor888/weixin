/**
 * 微信公众平台开发模式(JAVA) SDK
 */
package com.ctvit.bean;


public class TextOutMessage extends OutMessage {

	private String	MsgType	= "text"; 	
	// 文本消息
	private String	Content;//回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示） 
	
	public TextOutMessage(){
	}
	
	public TextOutMessage(String content) {
		Content = content;
	}

	public String getMsgType() {
		return MsgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
