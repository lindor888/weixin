package com.ctvit.writewords.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteWordsActivity {
    private String activityId;

    private String activityTitle;

    private Date beginTime;
    
    private String beginTimeStr;

    private Integer lastTime;

    private Date updateTime;

    private Integer flag;

    private String waccountId;
    
    //分页使用
    private int page;
    
    private int rows;
    
    private int beginIndex;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle == null ? null : activityTitle.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getLastTime() {
        return lastTime;
    }

    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		if(beginTimeStr!=null&&!"".equals(beginTimeStr)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				beginTime = sdf.parse(beginTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		this.beginTimeStr = beginTimeStr;
	}
}