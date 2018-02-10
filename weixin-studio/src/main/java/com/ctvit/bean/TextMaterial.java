package com.ctvit.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import com.ctvit.util.BaseData;

public class TextMaterial implements Serializable,BaseData{

	private String id;
	private String name;
	private String catalogTitle;
	private String waccount_id;
	private String wenbenxml;
	private String createuser;
	private String createuserid;
	private Timestamp createdate;
	private String modifyuser;
	private String modifyuserid;
	private Timestamp modifydate;
	private String operate;
	private int page;
	private int rows;
	private Integer status;
	private int start;
	private int end;
	private String keyword0;
	private String keyword1;
	
	
	
	
	
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public String getKeyword0() {
		return keyword0;
	}
	public void setKeyword0(String keyword0) {
		this.keyword0 = keyword0;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWaccount_id() {
		return waccount_id;
	}
	public void setWaccount_id(String waccount_id) {
		this.waccount_id = waccount_id;
	}
	public String getWenbenxml() {
		return wenbenxml;
	}
	public void setWenbenxml(String wenbenxml) {
		this.wenbenxml = wenbenxml;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public String getModifyuserid() {
		return modifyuserid;
	}
	public void setModifyuserid(String modifyuserid) {
		this.modifyuserid = modifyuserid;
	}
	public Timestamp getModifydate() {
		return modifydate;
	}
	public void setModifydate(Timestamp modifydate) {
		this.modifydate = modifydate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getCatalogTitle() {
		return catalogTitle;
	}
	public void setCatalogTitle(String catalogTitle) {
		this.catalogTitle = catalogTitle;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
}
