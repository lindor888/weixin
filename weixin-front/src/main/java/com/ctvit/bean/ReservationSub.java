package com.ctvit.bean;

/**
 * 订阅 和 取消订阅的信息表
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年8月31日 上午10:15:40
 */
public class ReservationSub {

	private String subscription_id; // '订阅标识',
	private String reservation_id; // '预约标识',
	private String open_id; // '用户的标识，对当前公众号唯一',
	private short flag;

	public String getSubscription_id() {
		return subscription_id;
	}

	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}

	public String getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public short getFlag() {
		return flag;
	}

	public void setFlag(short flag) {
		this.flag = flag;
	}

}
