package com.ctvit.bean;

import java.util.Date;

/**
 * 节目单预约
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年8月11日 下午6:23:07
 */

public class Reservation {

	// 预约标识
	private String reservation_id;
	// 公众号id
	private String waccount_id;
	// 节目名称
	private String program_name;
	// 开始时间
	private Date start_time;
	// 结束时间
	private Date end_time;
	// 结束时间
	private Date push_time;
	// 直播地址
	private String live_url;
	// 发送标记（0-未发送，1-已发送）
	private short send_flag;

	// 删除标识位（0-正常，1-被删除）
	private short delete_flag;

	public String getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}

	public String getWaccount_id() {
		return waccount_id;
	}

	public void setWaccount_id(String waccount_id) {
		this.waccount_id = waccount_id;
	}

	public String getProgram_name() {
		return program_name;
	}

	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Date getPush_time() {
		return push_time;
	}

	public void setPush_time(Date push_time) {
		this.push_time = push_time;
	}

	public String getLive_url() {
		return live_url;
	}

	public void setLive_url(String live_url) {
		this.live_url = live_url;
	}

	public short getSend_flag() {
		return send_flag;
	}

	public void setSend_flag(short send_flag) {
		this.send_flag = send_flag;
	}

	public short getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(short delete_flag) {
		this.delete_flag = delete_flag;
	}

}
