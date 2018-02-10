/**
 * 微信公众平台开发模式(JAVA) SDK
 */
package com.ctvit.inf;

import com.ctvit.bean.InMessage;
import com.ctvit.bean.OutMessage;
import com.ctvit.bean.TextOutMessage;



public abstract class DefaultMessageProcessingHandlerImpl implements MessageProcessingHandler{

	private OutMessage allType(InMessage msg){
		TextOutMessage out = new TextOutMessage();
		out.setContent("你的消息已经收到！");
		return out;
	}
	
	public OutMessage textTypeMsg(InMessage msg) {
		return allType(msg);
	}

	
	public OutMessage locationTypeMsg(InMessage msg) {
		return allType(msg);
	}

	
	public OutMessage imageTypeMsg(InMessage msg) {
		return allType(msg);
	}

	
	public OutMessage linkTypeMsg(InMessage msg) {
		return allType(msg);
	}

	
	public OutMessage eventTypeMsg(InMessage msg) {
		return allType(msg);
	}

}
