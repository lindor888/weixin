package com.ctvit.service;

import java.util.List;

import com.ctvit.bean.Interact_baoliao;
import com.ctvit.bean.Interact_baoliaoExt;
import com.ctvit.bean.Interact_baoliao_object;
import com.ctvit.bean.Interact_comment;
import com.ctvit.dao.Interact_baoliaoMapper;
import com.ctvit.dao.Interact_baoliao_objectMapper;
import com.ctvit.dao.Interact_commentMapper;

public class BaoLiaoService {

	private Interact_baoliaoMapper baoliaoMapper;

	private Interact_baoliao_objectMapper baoliaoObjectMapper;

	private Interact_commentMapper commentMapper;

	public Interact_baoliaoMapper getBaoliaoMapper() {
		return baoliaoMapper;
	}

	public void setBaoliaoMapper(Interact_baoliaoMapper baoliaoMapper) {
		this.baoliaoMapper = baoliaoMapper;
	}

	public Interact_baoliao_objectMapper getBaoliaoObjectMapper() {
		return baoliaoObjectMapper;
	}

	public void setBaoliaoObjectMapper(Interact_baoliao_objectMapper baoliaoObjectMapper) {
		this.baoliaoObjectMapper = baoliaoObjectMapper;
	}

	public void add(Interact_baoliao baoliao) {
		baoliaoMapper.insert(baoliao);

	}

	public List<Interact_baoliaoExt> select(Interact_baoliao baoliao) {

		List<Interact_baoliaoExt> list = baoliaoMapper.select(baoliao);
		return list;
	}

	public Interact_baoliaoExt getUser(Interact_baoliaoExt interact_baoliaoExt) {
		Interact_baoliaoExt ext = baoliaoMapper.getUser(interact_baoliaoExt);
		return ext;
	}

	public void addcomment(Interact_comment comment) {
		commentMapper.insert(comment);

	}

	public Interact_commentMapper getCommentMapper() {
		return commentMapper;
	}

	public void setCommentMapper(Interact_commentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	public List<Interact_comment> selectcomment(Interact_comment comment) {
		List<Interact_comment> list = commentMapper.selectcomment(comment);
		return list;
	}

	public List<Interact_baoliaoExt> search(Interact_baoliao baoliao) {
		List<Interact_baoliaoExt> list = baoliaoMapper.search(baoliao);
		return list;
	}

	public List<Interact_comment> searchcomment(Interact_comment comment) {
		List<Interact_comment> list = commentMapper.searchcomment(comment);
		return list;
	}

	public void addtitle(Interact_baoliao_object baoliaoObject) {
		baoliaoObjectMapper.insert(baoliaoObject);

	}

	public Interact_baoliao_object getinfo(Interact_baoliao_object baoliaoObject) {
		Interact_baoliao_object getinfo = baoliaoObjectMapper.getinfo(baoliaoObject);
		return getinfo;
	}
}
