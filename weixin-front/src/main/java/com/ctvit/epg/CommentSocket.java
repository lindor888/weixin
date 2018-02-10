package com.ctvit.epg;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctvit.bean.Comment;
import com.ctvit.dao.CommentDao;
import com.ctvit.util.CreateTableid;
import com.ctvit.util.sensitiveWord.SensitivewordFilter;

/**
 * 评论的websocket
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年10月9日 下午4:00:04
 */

@ServerEndpoint("/commentSocket")
public class CommentSocket {

	private CommentDao commentDao = new CommentDao();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final Map<String, List<Session>> users = new HashMap<String, List<Session>>();
	private Logger LOG = Logger.getLogger(CommentSocket.class);

	/**
	 * 接收消息&& 添加首次进入的用户
	 * 
	 * @author guosidi
	 * @date 2015年10月9日 下午4:00:32
	 * @param message
	 *            {flag: on/off, userInfo: {openid: ******, nickname: *****,
	 *            userhead: ******}, commentInfo:{comment: *****, parentid:
	 *            ******, reservationid: ****** }}
	 * @param session
	 * @throws IOException
	 * @throws InterruptedException
	 * @return {flag: list, userInfo: {openid: ******, nickname: *****,
	 *         userhead: ******}, comment:{comment: *****, parentid: ******,
	 *         reservationid: ****** }}
	 * 
	 *         返回一条评论 {flag: one, userInfo: {openid: ******, nickname: *****,
	 *         userhead: ******}, commentInfo:{comment: *****, commentTime:
	 *         *****, commentid: ******, parentid: ****** }}
	 * 
	 *         包含敏感词 {flag: error, course: ********}
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {

		LOG.info("receive message:" + message);
		JSONObject reMsg = JSONObject.parseObject(message);
		String flag = reMsg.getString("flag");
		JSONObject commentInfo = reMsg.getJSONObject("commentInfo");
		Comment comment = new Comment();
		comment.setReservation_id(commentInfo.getString("reservationid"));
		// 用户首次进入，需要加载评论列表
		if (flag.equals("on")) {
			// 添加用户至对应的视频用户组
			addUser(session, comment.getReservation_id());
			// 发送视频的评论至该用户
			session.getBasicRemote().sendText(showCommentList(comment.getReservation_id()));

		} else if (flag.equals("off")) {// 非首次进入，直接转发消息

			// 检测敏感词
			SensitivewordFilter filter = new SensitivewordFilter();
			if (filter.checkWord(commentInfo.getString("comment"))) {
				// 包含敏感词
				JSONObject sendMsg = new JSONObject();
				sendMsg.put("flag", "error");
				sendMsg.put("course", "评论中包含敏感词不能通过");
				session.getBasicRemote().sendText(sendMsg.toString());

			} else {
				// 不包含敏感词
				JSONObject userInfo = reMsg.getJSONObject("userInfo");
				comment.setOpenid(userInfo.getString("openid"));
				comment.setNickname(userInfo.getString("nickname"));
				comment.setUserhead(userInfo.getString("userhead"));
				comment.setContent(commentInfo.getString("comment"));
				comment.setParent_id(commentInfo.getString("parentid"));
				comment.setComment_id(CreateTableid.createTableID(Comment.class));
				// 评论消息入库
				commentDao.addComment(comment);

				// 封装消息群发
				JSONObject sendComment = new JSONObject();
				sendComment.put("comment", comment.getContent());
				sendComment.put("commentTime", sdf.format(new Date()));
				sendComment.put("commentid", comment.getComment_id());
				sendComment.put("parentid", comment.getParent_id());

				JSONObject sendMsg = new JSONObject();
				sendMsg.put("flag", "one");
				sendMsg.put("userInfo", userInfo);
				sendMsg.put("commentInfo", sendComment);

				// 群发
				List<Session> userList = users.get(comment.getReservation_id());
				for (Session user : userList) {
					user.getBasicRemote().sendText(sendMsg.toString());
				}
			}

		}
	}

	/**
	 * 打开连接
	 * 
	 * @author guosidi
	 * @date 2015年10月9日 下午4:00:54
	 * @param session
	 * @throws IOException
	 * @return void
	 */
	@OnOpen
	public void onOpen(Session session) throws IOException {
		LOG.info("Client connected:" + session);
	}

	/**
	 * 关闭连接并删除用户
	 * 
	 * @author guosidi
	 * @date 2015年10月9日 下午4:01:17
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

	/**
	 * 将用户添加至对应的视频用户组
	 * 
	 * @author guosidi
	 * @date 2015年10月10日 上午11:00:28
	 * @param session
	 * @param reservationid
	 * @return void
	 */
	private void addUser(Session session, String reservationid) {

		// 第一次连接，记录用户
		Iterator keys = users.keySet().iterator();
		List<Session> userList = null;
		if (users.containsKey(reservationid)) {
			userList = users.get(reservationid);
			userList.add(session);
			users.put(reservationid, userList);
		} else {
			userList = new ArrayList<Session>();
			userList.add(session);
			users.put(reservationid, userList);
		}

	}

	/**
	 * 封装评论列表
	 * 
	 * @author guosidi
	 * @date 2015年10月10日 上午11:24:39
	 * @param reservationid
	 * @return
	 * @return String
	 */
	private String showCommentList(String reservationid) {
		List<Comment> rows = commentDao.getList(reservationid);
		JSONObject dataObject = new JSONObject();
		dataObject.put("flag", "list");
		JSONArray jArray = new JSONArray();
		for (Comment row : rows) {
			// 查找一级评论下的二级回复
			List<Comment> replyRows = commentDao.getReplayList(row.getComment_id());
			JSONArray replylist = new JSONArray();
			for (Comment comment : replyRows) {
				JSONObject replay = new JSONObject();
				replay.put("comment", comment.getContent());
				replay.put("commentid", comment.getComment_id());
				replay.put("nickname", comment.getNickname());
				replay.put("userhead", comment.getUserhead());
				replay.put("commentTime", comment.getComment_time());
				replay.put("parentid", comment.getParent_id());
				replylist.add(replay);
			}
			// 封装评论列表
			JSONObject object = new JSONObject();
			object.put("comment", row.getContent());
			object.put("commentid", row.getComment_id());
			object.put("nickname", row.getNickname());
			object.put("userhead", row.getUserhead());
			object.put("commentTime", row.getComment_time());
			if (replylist.size() > 0) {
				object.put("replylist", replylist);
			}
			jArray.add(object);

		}
		dataObject.put("commentlist", jArray);
		return dataObject.toJSONString();
	}

}
