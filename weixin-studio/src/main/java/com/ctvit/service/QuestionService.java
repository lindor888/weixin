package com.ctvit.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ctvit.bean.HeadtitleBean;
import com.ctvit.bean.OptionBean;
import com.ctvit.bean.OptionBeans;
import com.ctvit.bean.QuestionBean;
import com.ctvit.bean.QuestionBeans;
import com.ctvit.bean.QuestionStat;
import com.ctvit.dao.QuestionMapper;
import com.ctvit.util.ActionUtil;
import com.ctvit.util.KeyGenerator;

public class QuestionService {

	private KeyGenerator keyGenerator;

	private QuestionMapper questionMapper;

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	public QuestionMapper getQuestionMapper() {
		return questionMapper;
	}

	public void setQuestionMapper(QuestionMapper questionMapper) {
		this.questionMapper = questionMapper;
	}

	public void saveheadtitle(HeadtitleBean headtitleBean, QuestionBeans questionBeans, OptionBeans optionBeans) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		String waccountId = ActionUtil.findWaccountId();
		headtitleBean.setWaccountId(waccountId);
		try {
			String headtitleKey = keyGenerator.getKey(headtitleBean);
			headtitleBean.setHeadtitleid(headtitleKey);
			headtitleBean.setStatus("0");
			headtitleBean.setCreatetime(time);
			List<QuestionBean> listQuestion = new ArrayList<QuestionBean>();
			List<OptionBean> listOption = new ArrayList<OptionBean>();
			if (!"".equals(questionBeans.getQuestionname0())) {
				QuestionBean questionBean = new QuestionBean();

				String qbkey = keyGenerator.getKey(questionBean);
				questionBean.setHeadtitleid(headtitleKey);
				questionBean.setQuestionid(qbkey);
				questionBean.setQuestionname(questionBeans.getQuestionname0());

				if (StringUtils.isNotBlank(optionBeans.getOption_one1())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_one1());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_two1())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_two1());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_three1())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_three1());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_four1())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_four1());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_five1())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_five1());
					listOption.add(optionBean);
				}
				listQuestion.add(questionBean);
			}
			if (!"".equals(questionBeans.getQuestionname1())) {
				QuestionBean questionBean = new QuestionBean();

				String qbkey = keyGenerator.getKey(questionBean);
				questionBean.setHeadtitleid(headtitleKey);
				questionBean.setQuestionid(qbkey);
				questionBean.setQuestionname(questionBeans.getQuestionname1());

				if (StringUtils.isNotBlank(optionBeans.getOption_one2())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_one2());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_two2())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_two2());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_three2())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_three2());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_four2())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_four2());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_five2())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_five2());
					listOption.add(optionBean);
				}
				listQuestion.add(questionBean);
			}
			if (!"".equals(questionBeans.getQuestionname2())) {
				QuestionBean questionBean = new QuestionBean();

				String qbkey = keyGenerator.getKey(questionBean);
				questionBean.setHeadtitleid(headtitleKey);
				questionBean.setQuestionid(qbkey);
				questionBean.setQuestionname(questionBeans.getQuestionname2());

				if (StringUtils.isNotBlank(optionBeans.getOption_one3())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_one3());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_two3())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_two3());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_three3())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_three3());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_four3())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_four3());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_five3())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_five3());
					listOption.add(optionBean);
				}
				listQuestion.add(questionBean);
			}
			if (!"".equals(questionBeans.getQuestionname3())) {
				QuestionBean questionBean = new QuestionBean();

				String qbkey = keyGenerator.getKey(questionBean);
				questionBean.setHeadtitleid(headtitleKey);
				questionBean.setQuestionid(qbkey);
				questionBean.setQuestionname(questionBeans.getQuestionname3());

				if (StringUtils.isNotBlank(optionBeans.getOption_one4())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_one4());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_two4())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_two4());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_three4())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_three4());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_four4())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_four4());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_five4())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_five4());
					listOption.add(optionBean);
				}
				listQuestion.add(questionBean);
			}
			if (!"".equals(questionBeans.getQuestionname4())) {
				QuestionBean questionBean = new QuestionBean();

				String qbkey = keyGenerator.getKey(questionBean);
				questionBean.setHeadtitleid(headtitleKey);
				questionBean.setQuestionid(qbkey);
				questionBean.setQuestionname(questionBeans.getQuestionname4());

				if (StringUtils.isNotBlank(optionBeans.getOption_one5())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_one5());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_two5())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_two5());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_three5())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_three5());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_four5())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_four5());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_five5())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_five5());
					listOption.add(optionBean);
				}
				listQuestion.add(questionBean);
			}
			if (!"".equals(questionBeans.getQuestionname5())) {
				QuestionBean questionBean = new QuestionBean();

				String qbkey = keyGenerator.getKey(questionBean);
				questionBean.setHeadtitleid(headtitleKey);
				questionBean.setQuestionid(qbkey);
				questionBean.setQuestionname(questionBeans.getQuestionname5());

				if (StringUtils.isNotBlank(optionBeans.getOption_one6())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_one6());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_two6())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_two6());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_three6())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_three6());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_four6())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_four6());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_five6())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_five6());
					listOption.add(optionBean);
				}
				listQuestion.add(questionBean);
			}
			if (!"".equals(questionBeans.getQuestionname6())) {
				QuestionBean questionBean = new QuestionBean();

				String qbkey = keyGenerator.getKey(questionBean);
				questionBean.setHeadtitleid(headtitleKey);
				questionBean.setQuestionid(qbkey);
				questionBean.setQuestionname(questionBeans.getQuestionname6());

				if (StringUtils.isNotBlank(optionBeans.getOption_one7())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_one7());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_two7())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_two7());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_three7())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_three7());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_four7())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_four7());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_five7())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_five7());
					listOption.add(optionBean);
				}
				listQuestion.add(questionBean);
			}
			if (!"".equals(questionBeans.getQuestionname7())) {
				QuestionBean questionBean = new QuestionBean();

				String qbkey = keyGenerator.getKey(questionBean);
				questionBean.setHeadtitleid(headtitleKey);
				questionBean.setQuestionid(qbkey);
				questionBean.setQuestionname(questionBeans.getQuestionname7());

				if (StringUtils.isNotBlank(optionBeans.getOption_one8())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_one8());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_two8())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_two8());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_three8())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_three8());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_four8())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_four8());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_five8())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_five8());
					listOption.add(optionBean);
				}
				listQuestion.add(questionBean);
			}
			if (!"".equals(questionBeans.getQuestionname8())) {
				QuestionBean questionBean = new QuestionBean();

				String qbkey = keyGenerator.getKey(questionBean);
				questionBean.setHeadtitleid(headtitleKey);
				questionBean.setQuestionid(qbkey);
				questionBean.setQuestionname(questionBeans.getQuestionname8());

				if (StringUtils.isNotBlank(optionBeans.getOption_one9())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_one9());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_two9())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_two9());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_three9())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_three9());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_four9())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_four9());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_five9())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_five1());
					listOption.add(optionBean);
				}
				listQuestion.add(questionBean);
			}
			if (!"".equals(questionBeans.getQuestionname9())) {
				QuestionBean questionBean = new QuestionBean();

				String qbkey = keyGenerator.getKey(questionBean);
				questionBean.setHeadtitleid(headtitleKey);
				questionBean.setQuestionid(qbkey);
				questionBean.setQuestionname(questionBeans.getQuestionname9());

				if (StringUtils.isNotBlank(optionBeans.getOption_one10())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_one10());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_two10())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_two10());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_three10())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_three10());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_four10())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_four10());
					listOption.add(optionBean);
				}
				if (StringUtils.isNotBlank(optionBeans.getOption_five10())) {
					OptionBean optionBean = new OptionBean();
					optionBean.setOptionid(keyGenerator.getKey(optionBean));
					optionBean.setQuestionid(qbkey);
					optionBean.setOpt(optionBeans.getOption_five10());
					listOption.add(optionBean);
				}
				listQuestion.add(questionBean);
			}

			questionMapper.saveheadtitle(headtitleBean);
			questionMapper.savequestion(listQuestion);
			questionMapper.saveoption(listOption);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<HeadtitleBean> findList() {
		List<HeadtitleBean> list = questionMapper.findList();
		return list;
	}

	public List<QuestionBean> findquestion(QuestionBean questionBean) {
		List<QuestionBean> quest = questionMapper.findquestion(questionBean);
		return quest;
	}

	public List<OptionBean> listoption(OptionBean optionBean) {
		List<OptionBean> opt = questionMapper.listoption(optionBean);
		return opt;
	}

	public List<QuestionStat> getquestionStat(QuestionStat questionStat) {
		List<QuestionStat> stat = questionMapper.getquestionStat(questionStat);
		return stat;
	}

	public OptionBean option(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("optionid", id);
		OptionBean op = questionMapper.option(map);
		return op;
	}

	public void update(OptionBean optionBean) {
		questionMapper.update(optionBean);
	}

	public HeadtitleBean getlist() {
		HeadtitleBean headtitleBean = questionMapper.getlist();
		return headtitleBean;
	}

	public void questionstate(HeadtitleBean headtitleBean) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		headtitleBean.setCreatetime(time);
		questionMapper.questionstate(headtitleBean);

	}

	public OptionBean updateoption(OptionBean optionBean) {
		OptionBean listop = questionMapper.updateoption(optionBean);
		return listop;
	}

	public void updatetotal(OptionBean optionBean) {
		questionMapper.updatetotal(optionBean);

	}

	public void insertstat(QuestionStat questionStat) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		try {
			questionStat.setQuestionstatid(keyGenerator.getKey(questionStat));
			questionStat.setCreatetime(time);
			questionMapper.insertstat(questionStat);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
