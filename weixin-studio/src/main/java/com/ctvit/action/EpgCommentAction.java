package com.ctvit.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.ctvit.bean.EpgComment;
import com.ctvit.bean.FenyeBean;
import com.ctvit.service.EpgCommentServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
/**
 * EPG 节目单的里视频的评论 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年10月20日 下午2:05:29
 */
public class EpgCommentAction extends ActionSupport implements Preparable  {

	/**  */
	private static final long serialVersionUID = 1L;

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}
	private Logger log = Logger.getLogger(EpgCommentAction.class);
	private FenyeBean fenyeBean;
	private EpgComment record = new EpgComment();
	private EpgCommentServiceImpl epgCommentServiceImpl;
	private int page;
	private int rows;
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Map<String, Object> map;
	
	/**
	 * 获得分页列表数据
	 * @author guosidi
	 * @date 2015年10月20日 下午3:47:10
	 * @return
	 * @return String
	 */
	public String getList(){
		map = new HashMap<String, Object>();
		try {
			fenyeBean = new FenyeBean();
			fenyeBean.setCurrentPage((page - 1) * 10);
			fenyeBean.setPageSize(rows);
			List<EpgComment> list = epgCommentServiceImpl.getList(fenyeBean);
			int count = epgCommentServiceImpl.getCount();
			map.put("rows", list);
			map.put("total", count);
		} catch (Exception e) {
			map.put("message", "error");
			log.error("", e);
		}
		return "list";
	}
	/**
	 * 按照某天查询评论
	 * @author guosidi
	 * @date 2015年10月20日 下午6:42:21
	 * @return
	 * @return String
	 */
	public String query(){
		map = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("currentPage", page);
		queryMap.put("pageSize", rows);
		if (StringUtils.isNotEmpty(record.getComment_time().toString())) {
			queryMap.put("starttime", record.getComment_time());
			try {
				queryMap.put("endtime", sdf.format(DateUtils.addDays(sdf.parse(record.getComment_time()), 1)));
			} catch (ParseException e) {
				log.error("", e);
			}
		}
		List<EpgComment> list = epgCommentServiceImpl.queryList(queryMap);
		int count = epgCommentServiceImpl.queryCount(queryMap);
		map.put("rows", list);
		map.put("total", count);
		return "query";
	}
	
	
	
	public EpgCommentServiceImpl getEpgCommentServiceImpl() {
		return epgCommentServiceImpl;
	}
	public void setEpgCommentServiceImpl(EpgCommentServiceImpl epgCommentServiceImpl) {
		this.epgCommentServiceImpl = epgCommentServiceImpl;
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



	public FenyeBean getFenyeBean() {
		return fenyeBean;
	}
	public void setFenyeBean(FenyeBean fenyeBean) {
		this.fenyeBean = fenyeBean;
	}
	public EpgComment getRecord() {
		return record;
	}
	public void setRecord(EpgComment record) {
		this.record = record;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
}
