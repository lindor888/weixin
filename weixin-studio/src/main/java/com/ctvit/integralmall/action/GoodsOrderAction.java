package com.ctvit.integralmall.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Waccount;
import com.ctvit.integralmall.entity.GoodsOrder;
import com.ctvit.integralmall.service.GoodsOrderServiceImpl;
import com.ctvit.service.WaccountServiceImpl;
import com.ctvit.util.ActionUtil;
import com.opensymphony.xwork2.ActionSupport;

public class GoodsOrderAction extends ActionSupport {
	private Logger log = Logger.getLogger(GoodsOrderAction.class);
	/** 变量定义 */
	private static final long serialVersionUID = 1L;
	private GoodsOrderServiceImpl goodsOrderServiceImpl;
	private WaccountServiceImpl waccountServiceImpl;
	private Map<String, Object> map;
	private int page;
	private int rows;
	private String name;

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
			paraMap.put("currentPage", (page - 1) * 10);
			paraMap.put("pageSize", rows);
			paraMap.put("app_id", getappid());
			List<GoodsOrder> list = goodsOrderServiceImpl.getList(paraMap);
			int count = goodsOrderServiceImpl.getCount(getappid());
			map.put("rows", list);
			map.put("total", count);
		} catch (Exception e) {
			map.put("message", "error");
			log.error("订单信息获取失败", e);
		}
		return "list";
	}

	// 查询用户订单
	public String selectone() {
		map = new HashMap<String, Object>();
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("name", name);
			List<GoodsOrder> list = goodsOrderServiceImpl.selectone(paraMap);

			map.put("rows", list);
			map.put("message", "success");
		} catch (Exception e) {
			map.put("message", "error");
			log.error("订单信息获取失败", e);
		}
		return "list";
	}

	public String getappid() {
		Waccount waccount = new Waccount();
		waccount = waccountServiceImpl.selectByPrimaryKey(ActionUtil.findWaccountId());
		return waccount.getAppId();
	}

	public GoodsOrderServiceImpl getGoodsOrderServiceImpl() {
		return goodsOrderServiceImpl;
	}

	public void setGoodsOrderServiceImpl(GoodsOrderServiceImpl goodsOrderServiceImpl) {
		this.goodsOrderServiceImpl = goodsOrderServiceImpl;
	}

	public WaccountServiceImpl getWaccountServiceImpl() {
		return waccountServiceImpl;
	}

	public void setWaccountServiceImpl(WaccountServiceImpl waccountServiceImpl) {
		this.waccountServiceImpl = waccountServiceImpl;
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

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	HttpServletRequest request = ServletActionContext.getRequest();
}
