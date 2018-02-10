package com.ctvit.bean;

public class Words {
    private Integer id;

    private String content;

    private String waccountId;
    
    //分页使用
    private int page;
    
    private int rows;
    
    private int beginIndex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getWaccountId() {
        return waccountId;
    }

    public void setWaccountId(String waccountId) {
        this.waccountId = waccountId == null ? null : waccountId.trim();
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

	public int getBeginIndex() {
		if(page>0&&rows>0){
			beginIndex = (page - 1)*rows;
		}
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
}