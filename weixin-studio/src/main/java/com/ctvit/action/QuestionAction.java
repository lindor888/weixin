package com.ctvit.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.HeadtitleBean;
import com.ctvit.bean.OptionBean;
import com.ctvit.bean.OptionBeans;
import com.ctvit.bean.QuestionBean;
import com.ctvit.bean.QuestionBeans;
import com.ctvit.bean.QuestionStat;
import com.ctvit.bean.ReceiveQuestionBean;
import com.ctvit.service.QuestionService;
import com.ctvit.util.KeyGenerator;
import com.opensymphony.xwork2.ActionSupport;

public class QuestionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1795820657360471958L;

	HttpServletRequest request = ServletActionContext.getRequest();

	HttpSession session = request.getSession();
	private Logger log = Logger.getLogger(QuestionAction.class);
	// 大主题
	private HeadtitleBean headtitleBean;
	// 小标题
	private QuestionBean questionBean;
	// 选项
	private OptionBean optionBean;
	// 选项选择记录
	private QuestionStat questionStat;

	private Map<String, Object> queryJson;

	private QuestionService questionService;

	private KeyGenerator keyGenerator;

	private QuestionBeans questionBeans;
	// 选项
	private OptionBeans optionBeans;

	private ReceiveQuestionBean receiveQuestionBean;
	private String id;

	/**
	 * 保存添加的答题信息
	 * 
	 * @return
	 */
	public String save() {
		queryJson = new HashMap<String, Object>();
		try {

			questionService.saveheadtitle(headtitleBean, questionBeans, optionBeans);
			queryJson.put("message", "success");

		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}

		return "saveq";
	}

	/**
	 * 问题列表
	 * 
	 * @return
	 */
	public String select() {
		queryJson = new HashMap<String, Object>();
		try {
			List<HeadtitleBean> list = questionService.findList();
			int total = 2;
			queryJson.put("total", total);
			queryJson.put("rows", list);
		} catch (Exception e) {
			log.error("", e);
		}

		return "select";
	}

	/**
	 * 启用与禁用
	 */
	public String questionstate() {
		queryJson = new HashMap<String, Object>();
		try {
			questionService.questionstate(headtitleBean);
			int total = 2;
			queryJson.put("total", total);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}

		return "select";
	}

	/**
	 * 标题列表
	 * 
	 * @return
	 */
	public String list() {
		queryJson = new HashMap<String, Object>();
		try {

			questionBean.setHeadtitleid(id);
			List<QuestionBean> list = questionService.findquestion(questionBean);
			int total = 2;
			queryJson.put("total", total);
			queryJson.put("rows", list);
		} catch (Exception e) {
			log.error("", e);
		}

		return "list";
	}

	/**
	 * 选项列表
	 * 
	 * @return
	 */
	public String listoption() {
		queryJson = new HashMap<String, Object>();
		try {
			optionBean.setQuestionid(id);
			List<OptionBean> list = questionService.listoption(optionBean);
			int total = 2;
			queryJson.put("total", total);
			queryJson.put("rows", list);
		} catch (Exception e) {
			log.error("", e);
		}

		return "listoption";
	}

	/**
	 * 选项
	 * 
	 * @return
	 */
	public String option() {
		queryJson = new HashMap<String, Object>();
		try {

			optionBean = questionService.option(id);
			int total = 2;
			queryJson.put("total", total);
			queryJson.put("rows", optionBean);
		} catch (Exception e) {
			log.error("", e);
		}

		return "option";
	}

	/**
	 * 选项修改
	 * 
	 * @return
	 */
	public String update() {
		queryJson = new HashMap<String, Object>();
		try {

			questionService.update(optionBean);
			int total = 2;
			queryJson.put("msg", "succes");
			// queryJson.put("rows", total);
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}

		return "update";
	}

	/**
	 * 统计用户答题选项列表
	 * 
	 * @return
	 */
	public String getquestionStat() {
		queryJson = new HashMap<String, Object>();
		try {
			questionStat.setQuestionid(id);
			List<QuestionStat> list = questionService.getquestionStat(questionStat);
			int total = 2;
			queryJson.put("total", total);
			queryJson.put("rows", list);
		} catch (Exception e) {
			log.error("", e);
		}

		return "getquestion";
	}

	/**
	 * 答题提交方法
	 * 
	 * @return
	 */
	public String questionsub() {
		queryJson = new HashMap<String, Object>();
		try {
			String[] questionid = receiveQuestionBean.getReceivequestionid().split(",");
			String[] opt = receiveQuestionBean.getReceiveoptions().split(",");
			for (int i = 0; i < questionid.length; i++) {
				optionBean.setQuestionid(questionid[i]);
				optionBean.setOpt(opt[i]);
				optionBean = questionService.updateoption(optionBean);
				int option_total = optionBean.getOption_total() + 1;
				optionBean.setOption_total(option_total);
				questionStat.setQuestionid(optionBean.getQuestionid());
				questionStat.setOptid(optionBean.getOptionid());
				questionStat.setOptionname(optionBean.getOpt());
				questionService.insertstat(questionStat);
				questionService.updatetotal(optionBean);
			}
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}

		return "getquestion";
	}

	/**
	 * 答题提交方法
	 * 
	 * @return
	 */
	public String datisub() {
		queryJson = new HashMap<String, Object>();
		try {
			String[] optionids = receiveQuestionBean.getOptionids().split(",");
			for (int i = 0; i < optionids.length; i++) {
				optionBean = questionService.option(optionids[i]);
				int option_total = optionBean.getOption_total() + 1;
				optionBean.setOption_total(option_total);
				questionStat.setQuestionid(optionBean.getQuestionid());
				questionStat.setOptid(optionBean.getOptionid());
				questionStat.setOptionname(optionBean.getOpt());
				questionService.insertstat(questionStat);
				questionService.updatetotal(optionBean);
			}
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}

		return "getquestion";
	}

	/**
	 * 答题页面
	 * 
	 * @return
	 */
	public String getlist() {
		queryJson = new HashMap<String, Object>();
		try {

			headtitleBean = questionService.getlist();
			if (headtitleBean != null) {
				questionBean.setHeadtitleid(headtitleBean.getHeadtitleid());
				List<QuestionBean> list = questionService.findquestion(questionBean);
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						optionBean.setQuestionid(list.get(i).getQuestionid());
						List<OptionBean> olist = questionService.listoption(optionBean);
						list.get(i).setOptionBean(olist);
					}
				}
				headtitleBean.setQuestionBean(list);
			}
			int total = 2;
			queryJson.put("msg", "success");
			queryJson.put("rows", headtitleBean);
		} catch (Exception e) {
			log.error("", e);
		}

		return "getquestion";
	}

	public HeadtitleBean getHeadtitleBean() {
		return headtitleBean;
	}

	public void setHeadtitleBean(HeadtitleBean headtitleBean) {
		this.headtitleBean = headtitleBean;
	}

	public QuestionBean getQuestionBean() {
		return questionBean;
	}

	public void setQuestionBean(QuestionBean questionBean) {
		this.questionBean = questionBean;
	}

	public OptionBean getOptionBean() {
		return optionBean;
	}

	public void setOptionBean(OptionBean optionBean) {
		this.optionBean = optionBean;
	}

	public QuestionStat getQuestionStat() {
		return questionStat;
	}

	public void setQuestionStat(QuestionStat questionStat) {
		this.questionStat = questionStat;
	}

	public Map<String, Object> getQueryJson() {
		return queryJson;
	}

	public void setQueryJson(Map<String, Object> queryJson) {
		this.queryJson = queryJson;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	public QuestionBeans getQuestionBeans() {
		return questionBeans;
	}

	public void setQuestionBeans(QuestionBeans questionBeans) {
		this.questionBeans = questionBeans;
	}

	public OptionBeans getOptionBeans() {
		return optionBeans;
	}

	public void setOptionBeans(OptionBeans optionBeans) {
		this.optionBeans = optionBeans;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ReceiveQuestionBean getReceiveQuestionBean() {
		return receiveQuestionBean;
	}

	public void setReceiveQuestionBean(ReceiveQuestionBean receiveQuestionBean) {
		this.receiveQuestionBean = receiveQuestionBean;
	}

}
