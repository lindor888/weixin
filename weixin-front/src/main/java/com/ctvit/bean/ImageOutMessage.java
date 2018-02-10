/**
 * 微信公众平台开发模式(JAVA) SDK
 */
package com.ctvit.bean;

/**
 * 回复图片消息
 * @author lizhixue
 *
 */
public class ImageOutMessage extends OutMessage {
	
	private String MsgType="image";

	public String getMsgType() {
		return MsgType;
	}

}
