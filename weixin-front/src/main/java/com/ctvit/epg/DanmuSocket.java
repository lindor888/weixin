package com.ctvit.epg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ctvit.util.sensitiveWord.SensitivewordFilter;

/**
 * 弹幕的websocket
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年9月30日 下午3:24:23
 */
@ServerEndpoint("/danmuSocket")
public class DanmuSocket {

	public static final Map<String, List<Session>> users = new HashMap<String, List<Session>>();
	private Logger LOG = Logger.getLogger(DanmuSocket.class);

	/**
	 * 接收和发送消息
	 * 
	 * @author guosidi
	 * @date 2015年9月30日 下午3:24:41
	 * @param message
	 * @param session
	 * @throws IOException
	 * @throws InterruptedException
	 * @return void
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {

		LOG.info("receive message:" + message);
		JSONObject jObject = JSONObject.parseObject(message);
		String flag = jObject.getString("flag");
		String reservationID = jObject.getString("reservation_id");
		if (flag.equals("on")) {
			// 第一次连接，记录用户
			Iterator keys = users.keySet().iterator();
			List<Session> userList = null;
			if (users.containsKey(reservationID)) {
				userList = users.get(reservationID);
				userList.add(session);
				users.put(reservationID, userList);
			} else {
				userList = new ArrayList<Session>();
				userList.add(session);
				users.put(reservationID, userList);
			}

		} else {
			JSONObject sendMsg = new JSONObject();
			String danmu = jObject.getString("danmu");
			SensitivewordFilter filter = new SensitivewordFilter();
			if (filter.checkWord(danmu)) {
				sendMsg.put("flag", "error");
				sendMsg.put("content", "弹幕中包含敏感词");
				session.getBasicRemote().sendText(sendMsg.toJSONString());
			} else {
				// 群发发弹幕
				sendMsg.put("flag", "success");
				sendMsg.put("content", danmu);
				List<Session> userList = users.get(reservationID);
				for (Session user : userList) {
					user.getBasicRemote().sendText(sendMsg.toJSONString());
				}
			}
		}
	}

	/**
	 * 打开 websocket 链接
	 * 
	 * @author guosidi
	 * @date 2015年9月30日 下午3:25:02
	 * @param session
	 * @return void
	 */
	@OnOpen
	public void onOpen(Session session) {
		LOG.info("Client connected:" + session);
	}

	/**
	 * 关闭websocket链接, 并删除用户
	 * 
	 * @author guosidi
	 * @date 2015年9月30日 下午3:25:25
	 * @param session
	 * @return void
	 */
	@OnClose
	public void onClose(Session session) {

		LOG.info("Connection closed:" + session);
		Iterator reservationIDs = users.keySet().iterator();
		while (reservationIDs.hasNext()) {
			String reservationID = (String) reservationIDs.next();
			List<Session> list = users.get(reservationID);
			if (list.contains(session)) {
				list.remove(session);
				if (list.size() > 0) {
					users.put(reservationID, list);
				} else {
					users.remove(reservationID);
				}
				break;
			}
		}
	}

}
