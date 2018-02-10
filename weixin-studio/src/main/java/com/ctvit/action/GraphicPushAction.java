package com.ctvit.action;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ctvit.bean.Imagexml;
import com.ctvit.bean.TuwenPushBean;
import com.ctvit.service.GraphicPushService;

public class GraphicPushAction {
	
	
	private Logger log = Logger.getLogger(GraphicPushAction.class);
	
	private Map<String, Object> queryJson;
	
	private GraphicPushService graphicPushService;
	
	private TuwenPushBean image;
	
	
	
	public String graphicpush() {
		queryJson = new HashMap<String, Object>();
//		graphicPushService = new GraphicPushService();
		try {
			
			graphicPushService.push(image);	
			queryJson.put("message", "success");
		return "push";	
			
		} catch (Exception e) {
			log.error("", e);
			queryJson.put("message", "fail");
		}
		return "push";
	}
	
	
	public TuwenPushBean getImage() {
		return image;
	}
	public void setImage(TuwenPushBean image) {
		this.image = image;
	}
	public GraphicPushService getGraphicPushService() {
		return graphicPushService;
	}
	public void setGraphicPushService(GraphicPushService graphicPushService) {
		this.graphicPushService = graphicPushService;
	}

	
	
	
}	
	
	
	

