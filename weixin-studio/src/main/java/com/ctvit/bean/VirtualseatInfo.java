package com.ctvit.bean;

/**
 * 虚拟座位席的信息表的实体类
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年11月6日 上午11:46:13
 */

public class VirtualseatInfo {
	private String virtualSeatID;
	private String waccountID;
	private String videoID;
	private int lineCount;
	private int columnCount;
	private int resultSeats;

	public String getVirtualSeatID() {
		return this.virtualSeatID;
	}

	public void setVirtualSeatID(String virtualSeatID) {
		this.virtualSeatID = virtualSeatID;
	}

	public String getWaccountID() {
		return this.waccountID;
	}

	public void setWaccountID(String waccountID) {
		this.waccountID = waccountID;
	}

	public String getVideoID() {
		return this.videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

	public int getLineCount() {
		return this.lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	public int getColumnCount() {
		return this.columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public int getResultSeats() {
		return this.resultSeats;
	}

	public void setResultSeats(int resultSeats) {
		this.resultSeats = resultSeats;
	}
}