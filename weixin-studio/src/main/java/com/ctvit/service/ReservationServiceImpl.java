package com.ctvit.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ctvit.bean.FenyeBean;
import com.ctvit.bean.Reservation;
import com.ctvit.dao.ReservationMapper;
import com.ctvit.util.ActionUtil;

/**
 * EGP节目单 业务层
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年8月12日 上午11:46:23
 */
public class ReservationServiceImpl {

	private ReservationMapper reservationMapper;

	public ReservationMapper getReservationMapper() {
		return reservationMapper;
	}

	public void setReservationMapper(ReservationMapper reservationMapper) {
		this.reservationMapper = reservationMapper;
	}

	/**
	 * 按日期获取节目列表
	 * 
	 * @author guosidi
	 * @date 2015年8月13日 下午2:59:18
	 * @param start
	 * @param end
	 * @return
	 * @return List<Reservation>
	 */
	public List<Reservation> getListByDate(Date start, Date end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		List<Reservation> rows = reservationMapper.getListByDate(map);
		return rows;
	}

	/**
	 * 添加一条节目
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 上午9:44:43
	 * @param record
	 * @return void
	 */
	public String addReservation(Reservation record) {

		String videoid = Reservation.class.getSimpleName() + (new Date()).getTime();
		record.setReservation_id(videoid);
		record.setWaccount_id(ActionUtil.findWaccountId());
		record.setSend_flag((short) 0);
		record.setDelete_flag((short) 0);
		Date starttime = record.getStart_time();
		Date pushttime = new Date(starttime.getTime() - 10 * 60 * 1000);
		record.setPush_time(pushttime);
		reservationMapper.addOne(record);
		return videoid;
	}

	/**
	 * 获取列表
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 上午11:07:38
	 * @param record
	 * @return
	 * @return List<Reservation>
	 */
	public List<Reservation> getList(FenyeBean fenyeBean) {
		return reservationMapper.getList(fenyeBean);
	}

	/**
	 * 获取总量
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 上午11:08:09
	 * @param record
	 * @return
	 * @return int
	 */
	public int getCount(Reservation record) {
		return reservationMapper.getCount(record);
	}

	public void deleteOne(Reservation record) {
		reservationMapper.deleteOne(record);
	}

	public void updateOne(Reservation record) {
		Date starttime = record.getStart_time();
		Date pushttime = new Date(starttime.getTime() - 10 * 60 * 1000);
		record.setPush_time(pushttime);
		reservationMapper.updateOne(record);
	}

	/**
	 * 查询某一条节目
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 下午1:35:05
	 * @param record
	 * @return void
	 */
	public Reservation selectOne(String id) {
		return reservationMapper.selectOne(id);
	}

	public List<Reservation> queryList(Map map) {
		return reservationMapper.queryList(map);
	}

	public int queryCount(Map map) {
		return reservationMapper.queryCount(map);
	}

	public List<Reservation> getListfenye(FenyeBean fenyeBean) {
		List<Reservation> reservation = reservationMapper.getListfenye(fenyeBean);
		return reservation;
	}
}
