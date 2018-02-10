package com.ctvit.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.ctvit.bean.FenyeBean;
import com.ctvit.bean.Reservation;
import com.ctvit.service.ReservationServiceImpl;
import com.ctvit.service.VirtualseatInfoServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class EpgAction extends ActionSupport implements Preparable {

	/**  */
	private Logger log = Logger.getLogger(EpgAction.class);
	private static final long serialVersionUID = 1L;
	private FenyeBean fenyeBean;
	private ReservationServiceImpl reservationServiceImpl;
	private VirtualseatInfoServiceImpl virtualseatInfoServiceImpl;
	private Reservation record = new Reservation();
	private JSONObject dataJson = new JSONObject();
	private Map<String, Object> map;
	private String rowIds;
	private int page;
	private int rows;
	private String epg_day;
	private String channel_id;
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	HttpServletRequest request = ServletActionContext.getRequest();

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	HttpServletResponse response = ServletActionContext.getResponse();

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 添加节目单
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 上午9:42:34
	 * @return
	 * @return String
	 */
	public String init() {
		return "init";
	}

	/**
	 * 添加节目单
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 上午9:42:34
	 * @return
	 * @return String
	 */
	public String addReservation() {
		map = new HashMap<String, Object>();
		String videoid = reservationServiceImpl.addReservation(record);
		virtualseatInfoServiceImpl.addVirtualInfo(videoid);
		map.put("res", "success");
		return "add";
	}

	/**
	 * 列表数据
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 上午10:48:24
	 * @return
	 * @return String
	 */
	public String getList() {
		map = new HashMap<String, Object>();
		try {
			fenyeBean = new FenyeBean();
			fenyeBean.setCurrentPage((page - 1) * 10);
			fenyeBean.setPageSize(rows);
			List<Reservation> list = reservationServiceImpl.getList(fenyeBean);
			int count = reservationServiceImpl.getCount(record);
			map.put("rows", list);
			map.put("total", count);
		} catch (Exception e) {
			map.put("message", "error");
			log.error("", e);
		}
		return "list";
	}

	public String deleteOne() {
		map = new HashMap<String, Object>();
		reservationServiceImpl.deleteOne(record);
		map.put("msg", "success");
		return "delete";
	}

	/**
	 * 更新节目
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 下午1:33:19
	 * @return
	 * @return String
	 */
	public String update() {
		map = new HashMap<String, Object>();
		reservationServiceImpl.updateOne(record);
		map.put("msg", "success");
		return "update";
	}

	/**
	 * 到修改页面
	 * 
	 * @author guosidi
	 * @date 2015年8月14日 下午1:33:19
	 * @return
	 * @return String
	 */
	public String toUpdate() {
		record = reservationServiceImpl.selectOne(record.getReservation_id());
		// Date start_time = record.getStart_time();
		// record.setStart_time(start_time);
		return "toupdate";
	}

	/**
	 * 根据日期或者节目名称查询
	 * 
	 * @author guosidi
	 * @date 2015年8月17日 下午2:38:12
	 * @return
	 * @return String
	 */
	public String query() {
		map = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("start", (page - 1) * rows);
		queryMap.put("end", rows);
		if (StringUtils.isNotEmpty(record.getProgram_name())) {
			queryMap.put("program_name", record.getProgram_name());
		}
		if (StringUtils.isNotEmpty(record.getStart_time().toString())) {
			queryMap.put("starttime", record.getStart_time());
			queryMap.put("endtime", DateUtils.addDays(record.getStart_time(), 1));
		}

		List<Reservation> list = reservationServiceImpl.queryList(queryMap);
		int count = reservationServiceImpl.queryCount(queryMap);
		map.put("rows", list);
		map.put("total", count);
		return "query";
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public JSONObject getDataJson() {
		return dataJson;
	}

	public void setDataJson(JSONObject dataJson) {
		this.dataJson = dataJson;
	}

	public String getEpg_day() {
		return epg_day;
	}

	public void setEpg_day(String epg_day) {
		this.epg_day = epg_day;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public ReservationServiceImpl getReservationServiceImpl() {
		return reservationServiceImpl;
	}

	public void setReservationServiceImpl(ReservationServiceImpl reservationServiceImpl) {
		this.reservationServiceImpl = reservationServiceImpl;
	}

	public Reservation getRecord() {
		return record;
	}

	public void setRecord(Reservation record) {
		this.record = record;
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

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getRowIds() {
		return rowIds;
	}

	public void setRowIds(String rowIds) {
		this.rowIds = rowIds;
	}

	public VirtualseatInfoServiceImpl getVirtualseatInfoServiceImpl() {
		return virtualseatInfoServiceImpl;
	}

	public void setVirtualseatInfoServiceImpl(VirtualseatInfoServiceImpl virtualseatInfoServiceImpl) {
		this.virtualseatInfoServiceImpl = virtualseatInfoServiceImpl;
	}

}
