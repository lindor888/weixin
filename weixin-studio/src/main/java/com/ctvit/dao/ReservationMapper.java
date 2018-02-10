package com.ctvit.dao;

import java.util.List;
import java.util.Map;

import com.ctvit.bean.FenyeBean;
import com.ctvit.bean.Reservation;

/**
 * EGP节目单的 查询数据库接口
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年8月12日 上午10:07:34
 */

public interface ReservationMapper {

	/**
	 * 按照日期 查询该日期内的节目列表
	 * 
	 * @author guosidi
	 * @date 2015年8月12日 上午10:08:17
	 * @param date
	 * @return
	 * @return List<Reservation>
	 */
	List<Reservation> getListByDate(Map map);

	/**
	 * 添加记录
	 * 
	 * @author guosidi
	 * @date 2015年8月13日 下午3:03:33
	 * @param record
	 * @return
	 * @return int
	 */
	void addOne(Reservation record);

	/**
	 * 查询所有节目数据
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 上午9:47:12
	 * @return
	 * @return List<Reservation>
	 */
	List<Reservation> getList(FenyeBean fenyeBean);

	/**
	 * 查询节目总数量
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 上午10:27:14
	 * @param record
	 * @return
	 * @return int
	 */
	int getCount(Reservation record);

	void deleteOne(Reservation record);

	void updateOne(Reservation record);

	Reservation selectOne(String id);

	List<Reservation> queryList(Map map);

	int queryCount(Map map);

	List<Reservation> getListfenye(FenyeBean fenyeBean);
}
