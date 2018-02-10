/**
 * 微信公众平台开发模式(JAVA) SDK
 */
package com.ctvit;

import com.ctvit.bean.InMessage;
import com.ctvit.bean.OutMessage;
import com.ctvit.bean.TextOutMessage;
import com.ctvit.inf.MessageProcessingHandler;

/**
 * 自定义实现消息处理
 * 
 *
 */
public class MessageProcessingHandlerImpl implements MessageProcessingHandler {

	public OutMessage textTypeMsg(InMessage msg) {
		TextOutMessage oms = new TextOutMessage();
		oms.setContent(msg.getContent());
		return oms;
	}


	public OutMessage locationTypeMsg(InMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}

	public OutMessage imageTypeMsg(InMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}


	public OutMessage linkTypeMsg(InMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}

	public OutMessage eventTypeMsg(InMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}

	public OutMessage voiceTypeMsg(InMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}
}
