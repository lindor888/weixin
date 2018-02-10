package com.ctvit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;
import com.ctvit.action.InteractAction;
import com.ctvit.bean.GraphicBean;
import com.ctvit.bean.Imagexml;
import com.ctvit.bean.JsonBean;
import com.ctvit.bean.News;
import com.ctvit.bean.TuwenPushBean;
import com.ctvit.dao.GraphicPushMapper;
import com.ctvit.dao.InteractMapper;
import com.ctvit.util.ActionUtil;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.util.Message;

public class GraphicPushService {
	
	HttpServletRequest request = ServletActionContext.getRequest();

	HttpSession session = request.getSession();

	private Logger log = Logger.getLogger(InteractAction.class);
	
	private Map<String, Object> queryJson;
	
    private GraphicBean graphicBean;

    private GraphicPushMapper graphicPushMapper;
    
	private News news;

	public String push(TuwenPushBean image) throws Exception{
		String result = null;
		String waccountId = ActionUtil.findWaccountId();
		String appid = (String)MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_appid");
		String secret = (String)MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_secret");
		String bridgeToken = (String)MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_bridge");
		//查询出openId
		List<String> openidList = this.selectOpenid();
		Message message = new Message();
		JSONObject json = new JSONObject();
		List<JsonBean> listjson = new ArrayList<JsonBean>();
		if (!"".equals(image.getTitle1())) {
			JsonBean jsonBean = new JsonBean(); 
			jsonBean.setTitle(image.getTitle1());
			jsonBean.setUrl(image.getUrl1());
			jsonBean.setPicurl(image.getImageUrl1());
			listjson.add(jsonBean);	
			jsonBean.setDescription(image.getDesc1());
		}
		if (!"".equals(image.getTitle2())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle2());
			jsonBean.setUrl(image.getUrl2());
			jsonBean.setPicurl(image.getImageUrl2());
			listjson.add(jsonBean);	
			jsonBean.setDescription(image.getDesc2());
		}
		if (!"".equals(image.getTitle3())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle3());
			jsonBean.setUrl(image.getUrl3());
			jsonBean.setPicurl(image.getImageUrl3());
			listjson.add(jsonBean);	
			jsonBean.setDescription(image.getDesc3());
		}
		if (!"".equals(image.getTitle4())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle4());
			jsonBean.setUrl(image.getUrl4());
			jsonBean.setPicurl(image.getImageUrl4());
			listjson.add(jsonBean);	
			jsonBean.setDescription(image.getDesc4());
		}
		if (!"".equals(image.getTitle5())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle5());
			jsonBean.setUrl(image.getUrl5());
			jsonBean.setPicurl(image.getImageUrl5());
			listjson.add(jsonBean);	
			jsonBean.setDescription(image.getDesc5());
		}
		if (!"".equals(image.getTitle6())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle6());
			jsonBean.setUrl(image.getUrl6());
			jsonBean.setPicurl(image.getImageUrl6());
			listjson.add(jsonBean);	
			jsonBean.setDescription(image.getDesc6());
		}
		if (!"".equals(image.getTitle7())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle7());
			jsonBean.setUrl(image.getUrl7());
			jsonBean.setPicurl(image.getImageUrl7());
			listjson.add(jsonBean);	
			jsonBean.setDescription(image.getDesc7());
		}
		if (!"".equals(image.getTitle8())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle8());
			jsonBean.setUrl(image.getUrl8());
			jsonBean.setPicurl(image.getImageUrl8());
			listjson.add(jsonBean);	
			jsonBean.setDescription(image.getDesc8());
		}
		if (!"".equals(image.getTitle9())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle9());
			jsonBean.setUrl(image.getUrl9());
			jsonBean.setPicurl(image.getImageUrl9());
			listjson.add(jsonBean);	
			jsonBean.setDescription(image.getDesc9());
		}
		if (!"".equals(image.getTitle10())) {
			JsonBean jsonBean = new JsonBean();
			jsonBean.setTitle(image.getTitle10());
			jsonBean.setUrl(image.getUrl10());
			jsonBean.setPicurl(image.getImageUrl10());
			listjson.add(jsonBean);	
			jsonBean.setDescription(image.getDesc10());
		}
		graphicPushMapper.insertJson(listjson);
		news = new News();
		news.setArticles(listjson);
		graphicBean = new GraphicBean();
		graphicBean.setMsgtype("news");
		graphicBean.setNews(news);
		graphicBean.setTouser(openidList);
		//System.out.println(json.toJSONString(jsonBean));
		String paramJson = json.toJSONString(graphicBean);
		System.out.println(json.toJSONString(news));
		System.out.println(json.toJSONString(graphicBean));
		json.toJSONString(graphicBean);
		//json.toJSONString(jsonBean);
		if(StringUtils.isNotBlank(bridgeToken)){
			try{
				message.publishToBridgetuwenAll(paramJson, bridgeToken, openidList);
				
				//更新
			}catch(Exception e){
				throw new Exception("huifu error");
			}
		}else{
			//String ttt = "7V63QmdY2RlVs7Zqyi5F_7nOxxmNWfQ4Q9P05G1I5YZXX2r08IbMx7BEJApGBerdqW7JHLSZVsOoj9ZbvDsXvbpuZpejlaF10RNhurBVQfQ";
			result = message.sendTwenAll(appid, secret, paramJson);
			
			if(result.indexOf("ok")!=-1){
				//更新  sendtuwenMsgAll
				//interact.setHuifu(bean.getHuifu());
				//this.addOrUpdate(interact, waccountId);
				System.out.println("消息推送");
			}else{
				throw new Exception("huifu error");
			}
		}
		return result;
	}
	
	/**
	 * 查询所有openid
	 * @param bean
	 * @return
	 */
	public List<String> selectOpenid(){
		//List list = new ArrayList();
		//list.add(e)
		List<String> openidList =graphicPushMapper.selectOpenid();
		return openidList;
	}
	public GraphicBean getGraphicBean() {
		return graphicBean;
	}

	public void setGraphicBean(GraphicBean graphicBean) {
		this.graphicBean = graphicBean;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public GraphicPushMapper getGraphicPushMapper() {
		return graphicPushMapper;
	}

	public void setGraphicPushMapper(GraphicPushMapper graphicPushMapper) {
		this.graphicPushMapper = graphicPushMapper;
	}
	
}
