/**
 * 微信公众平台开发模式(JAVA) SDK
 */
package com.ctvit.bean;


public class MusicOutMessage extends OutMessage {
	private String	MsgType	= "music";
	private String	MusicUrl;


	public String getMsgType() {
		return MsgType;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}


}
