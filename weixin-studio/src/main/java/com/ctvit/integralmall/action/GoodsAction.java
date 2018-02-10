package com.ctvit.integralmall.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Waccount;
import com.ctvit.integralmall.entity.Goods;
import com.ctvit.integralmall.entity.GoodsType;
import com.ctvit.integralmall.service.GoodsServiceImpl;
import com.ctvit.service.WaccountServiceImpl;
import com.ctvit.util.ActionUtil;
import com.opensymphony.xwork2.ActionSupport;

public class GoodsAction extends ActionSupport {
	private Logger log = Logger.getLogger(GoodsAction.class);
	/** 变量定义 */
	private static final long serialVersionUID = 1L;
	private GoodsServiceImpl goodsServiceImpl;
	private WaccountServiceImpl waccountServiceImpl;
	private Goods record = new Goods();
	private Map<String, Object> map;
	private int page;
	private int rows;

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

	public String init() {
		return "init";
	}

	/**
	 * 点击商品管理加载所有商品信息
	 * 
	 * @author yaonana
	 * @date 2015年10月9日 下午3:32:40
	 * @return
	 * @return String
	 */
	public String selectall() {
		map = new HashMap<String, Object>();
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			// fenyeBean = new FenyeBean();
			// fenyeBean.setCurrentPage((page - 1) * 10);
			// fenyeBean.setPageSize(rows);
			paraMap.put("currentPage", (page - 1) * 10);
			paraMap.put("pageSize", rows);
			paraMap.put("app_id", getappid());
			List<Goods> list = goodsServiceImpl.getList(paraMap);
			int count = goodsServiceImpl.getCount(getappid());
			map.put("rows", list);
			map.put("total", count);
		} catch (Exception e) {
			map.put("message", "error");
			log.error("", e);
		}
		return "list";
	}

	public String getappid() {
		Waccount waccount = new Waccount();
		waccount = waccountServiceImpl.selectByPrimaryKey(ActionUtil.findWaccountId());
		return waccount.getAppId();
	}

	/**
	 * 请求新增商品页
	 * 
	 * @author yaonana
	 * @date 2015年10月9日 下午4:29:21
	 * @return
	 * @return String
	 */
	public String toAdd() {
		List<GoodsType> typeList = goodsServiceImpl.getGoodsTypes();
		request.setAttribute("goodsTypeList", typeList);
		return "toupdate";
	}

	public String addOne() {
		map = new HashMap<String, Object>();
		record.setWaccountID(getappid());
		goodsServiceImpl.insertGoods(record);
		map.put("res", "success");
		return "add";
	}

	public String toUpdate() {
		List<GoodsType> typeList = goodsServiceImpl.getGoodsTypes();
		request.setAttribute("goodsTypeList", typeList);
		record = goodsServiceImpl.getGoodsInfo(record.getGoodsID());
		return "toupdate";
	}

	public String update() {
		map = new HashMap<String, Object>();
		goodsServiceImpl.updateGoods(record);
		map.put("res", "success");
		return "update";
	}

	public String deleteOne() {
		map = new HashMap<String, Object>();
		int result = goodsServiceImpl.deleteGoods(record.getGoodsID());
		if (result > 0) {
			map.put("msg", "success");
		} else {
			map.put("msg", "fail");
		}
		return "delete";
	}

	public GoodsServiceImpl getGoodsServiceImpl() {
		return goodsServiceImpl;
	}

	public void setGoodsServiceImpl(GoodsServiceImpl goodsServiceImpl) {
		this.goodsServiceImpl = goodsServiceImpl;
	}

	public Goods getRecord() {
		return record;
	}

	public void setRecord(Goods record) {
		this.record = record;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
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

	public WaccountServiceImpl getWaccountServiceImpl() {
		return waccountServiceImpl;
	}

	public void setWaccountServiceImpl(WaccountServiceImpl waccountServiceImpl) {
		this.waccountServiceImpl = waccountServiceImpl;
	}
}
