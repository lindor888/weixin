package com.ctvit.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import com.ctvit.util.BaseData;

public class Keyword implements Serializable,BaseData{
	private String id;
	private String keywordName;
	private Integer keywordRule;
	private Integer keywordWeight;
	private String materialId;
	private String waccount_id;
	private String createuser;
	private String createuserid;
	private Timestamp createdate;
	private String modifyuser;
	private String modifyuserid;
	private Timestamp modifydate;
	private Integer status;
	private Integer type;
	private String name;
	private String operate;
	private String catalogTitle;
	private int start;
	private int end;
	private String cacheTitle;
	private Account user;
	private String[] waccountIds;
	private String userId;
	
	//模糊
	public static final int RULE_MOHU = 0;
	//精确
	public static final int RULE_JINGQUE = 1;
	//图文
	public static final int TYPE_TUWEN = 1;
	//文本
	public static final int TYPE_WENBEN = 0;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeywordName() {
		return keywordName;
	}
	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}
	public Integer getKeywordRule() {
		return keywordRule;
	}
	public void setKeywordRule(Integer keywordRule) {
		this.keywordRule = keywordRule;
	}
	public Integer getKeywordWeight() {
		return keywordWeight;
	}
	public void setKeywordWeight(Integer keywordWeight) {
		this.keywordWeight = keywordWeight;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getWaccount_id() {
		return waccount_id;
	}
	public void setWaccount_id(String waccount_id) {
		this.waccount_id = waccount_id;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getCatalogTitle() {
		return catalogTitle;
	}
	public void setCatalogTitle(String catalogTitle) {
		this.catalogTitle = catalogTitle;
	}
	public String getCacheTitle() {
		return cacheTitle;
	}
	public void setCacheTitle(String cacheTitle) {
		this.cacheTitle = cacheTitle;
	}
	public Account getUser() {
		return user;
	}
	public void setUser(Account user) {
		this.user = user;
	}
	public String[] getWaccountIds() {
		return waccountIds;
	}
	public void setWaccountIds(String[] waccountIds) {
		this.waccountIds = waccountIds;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
