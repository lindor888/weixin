package com.ctvit.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.ChakanBean;
import com.ctvit.bean.FenyeBean;
import com.ctvit.bean.Followers;
import com.ctvit.bean.Interact;
import com.ctvit.bean.InteractExample;
import com.ctvit.bean.InteractLocation;
import com.ctvit.bean.MeetingAgenda;
import com.ctvit.bean.MeetingContent;
import com.ctvit.bean.PrizeBean;
import com.ctvit.bean.PrizeNameBean;
import com.ctvit.bean.PrizeTitle;
import com.ctvit.bean.Probability;
import com.ctvit.bean.ProgrammeViewName;
import com.ctvit.bean.ReportsBean;
import com.ctvit.bean.Words;
import com.ctvit.bean.WordsExample;
import com.ctvit.service.InteractService;
import com.ctvit.service.WordsService;
import com.ctvit.util.ActionUtil;
import com.ctvit.util.HttpKit;
import com.ctvit.util.KeyGenerator;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.util.ResourceLoader;
import com.ctvit.util.SensitiveWordFilter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;

public class InteractAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1795820657360471958L;

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	private Logger log = Logger.getLogger(InteractAction.class);
	private String programmeId;
	private String qrtype;
	private String qrname;
	int flag;
	private Interact bean;
	private ProgrammeViewName programmename;
	private ChakanBean chakanBean;
	private InteractExample example;
	private Map<String, Object> queryJson;
	private InteractService interactService;
	private WordsService wordsService;
	private int page;
	private int rows;
	private FenyeBean fenyeBean;
	private String startTime;
	private String timesort;
	private String waccountId;
	// 互动通过openIds查询用户详细信息
	private String ids;
	// 通过微信转发互动系统的回复内容
	// 微信用户id
	private String openId;
	// 回复的内容
	private String content;
	// 批量通过
	private String rowIds;
	private String type;
	private String zhutiid;
	private InteractLocation interactLocation;
	private MeetingAgenda meetingAgenda;
	private MeetingContent meetingContent;
	private PrizeTitle prizeTitle;
	private String mcontent;
	private String goodsId;
	private String qrcodeID;
	private Probability probability;

	public String init() {
		return "init";
	}

	public String bianji() {
		return "bianji";
	}

	public String jiemudanView() {
		return "jiemudanView";
	}

	public String xiugai() {

		return "xiugai";
	}

	public String tianjia() {
		session.setAttribute("programme_id", programmeId);
		return "tianjia";
	}

	/**
	 * 生成节目单需要的ID
	 */
	public String jiemudan() {
		session.setAttribute("rowId", rowIds);
		System.out.println("保存ID" + rowIds);
		return "jiemudan";
	}

	/**
	 * 查看节目单详情
	 */

	public String chakan() {

		return "chakan";
	}

	/**
	 * 通过微信转发互动系统的回复内容
	 * 
	 * @return
	 */
	public String zhuanfa() {
		queryJson = new HashMap<String, Object>();
		HttpServletRequest request = (HttpServletRequest) (ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST));
		String method = request.getMethod().toLowerCase();
		if ("get".equals(method)) {
			queryJson.put("message", "must use post method");
			return "zhuanfa";
		}
		try {
			if (StringUtils.isBlank(openId) || StringUtils.isBlank(content)) {
				queryJson.put("message", "lack params");
				return "zhuanfa";
			}
			interactService.zhuanfa(openId, content);
			queryJson.put("message", "success");
		} catch (Exception e) {
			log.error("", e);
			queryJson.put("message", "fail");
		}
		return "zhuanfa";
	}

	/**
	 * 数据的对外接口
	 * 
	 * @return
	 */
	public String show() {
		queryJson = new HashMap<String, Object>();
		String imageUrl = ResourceLoader.getInstance().getConfig().getProperty("imageUrl");
		String imageWaibuUrl = ResourceLoader.getInstance().getConfig().getProperty("imageWaibuUrl");
		try {
			List<Interact> list = interactService.getData(startTime, rows, timesort, waccountId);
			// 解决历史数据问题，将图片的外网地址替换为内网地址
			List<Interact> resultList = new ArrayList<Interact>();
			for (Interact i : list) {
				i.setHeadimgurl(i.getHeadimgurl().replace(imageWaibuUrl, imageUrl));
				resultList.add(i);
			}
			queryJson.put("total", resultList.size());
			queryJson.put("rows", list);
		} catch (Exception e) {
			log.error("", e);
		}
		return "show";
	}

	/**
	 * 其他系统通过id获取用户信息
	 * 
	 * @return
	 */
	public String showU() {
		queryJson = new HashMap<String, Object>();
		try {
			List<Followers> list = interactService.getUserData(ids, startTime, waccountId);
			// 这里需要将图片的内网地址替换为外网地址
			String imageUrl = ResourceLoader.getInstance().getConfig().getProperty("imageUrl");
			String imageWaibuUrl = ResourceLoader.getInstance().getConfig().getProperty("imageWaibuUrl") + "/";

			List<Followers> resultList = new ArrayList<Followers>();
			for (Followers f : list) {
				f.setHeadimgurl(f.getHeadimgurl().replaceAll(imageUrl, imageWaibuUrl));
				resultList.add(f);
			}
			queryJson.put("rows", resultList);
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}

		return "showU";
	}

	/**
	 * 获得当天参与投票的所有人
	 * 
	 * @return
	 */
	public String showUVote() {
		queryJson = new HashMap<String, Object>();
		try {
			ids = "";
			String strFromVoteInterface = HttpKit.get(ResourceLoader.getInstance().getConfig().getProperty("votePersonIdUrl"));
			JSONObject jsonObject = JSONObject.fromObject(strFromVoteInterface);
			JSONArray jsonArray = jsonObject.getJSONArray("ids");

			List<Followers> resultList = new ArrayList<Followers>();
			if (jsonArray.size() > 0) {
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject object = jsonArray.getJSONObject(i);
					ids += object.getString("name") + ",";
				}
				ids = ids.substring(0, ids.length() - 1);
				List<Followers> list = interactService.getUserData(ids, startTime, waccountId);
				// 这里需要将图片的内网地址替换为外网地址
				String imageUrl = ResourceLoader.getInstance().getConfig().getProperty("imageUrl");
				String imageWaibuUrl = ResourceLoader.getInstance().getConfig().getProperty("imageWaibuUrl") + "/";

				for (Followers f : list) {
					f.setHeadimgurl(f.getHeadimgurl().replaceAll(imageUrl, imageWaibuUrl));
					resultList.add(f);
				}
			}
			queryJson.put("message", "success");
			queryJson.put("rows", resultList);

		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}

		return "showU";
	}

	/**
	 * 获取当天中奖用户的信息
	 * 
	 * @return
	 */
	public String showUChoujiang() {
		queryJson = new HashMap<String, Object>();
		try {
			ids = "";
			String strFromVoteInterface = HttpKit.get(ResourceLoader.getInstance().getConfig().getProperty("votePersonIdUrl"));
			JSONObject jsonObject = JSONObject.fromObject(strFromVoteInterface);
			JSONArray jsonArray = jsonObject.getJSONArray("ids");

			if (jsonArray.size() > 0) {
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject object = jsonArray.getJSONObject(i);
					ids += object.getString("name") + ",";
				}
				ids = ids.substring(0, ids.length() - 1);
				List<Followers> list = interactService.getUserData(ids, startTime, waccountId);
				// 这里需要将图片的内网地址替换为外网地址
				String imageUrl = ResourceLoader.getInstance().getConfig().getProperty("imageUrl");
				String imageWaibuUrl = ResourceLoader.getInstance().getConfig().getProperty("imageWaibuUrl") + "/";

				List<Followers> resultList = new ArrayList<Followers>();
				for (Followers f : list) {
					f.setHeadimgurl(f.getHeadimgurl().replaceAll(imageUrl, imageWaibuUrl));
					resultList.add(f);
				}
				// 一次中5个
				int num = 5;
				List<Followers> perZhongjiangList = new ArrayList<Followers>();
				num = resultList.size() > num ? num : resultList.size();
				// 抽奖
				for (int i = 0; i < num; i++) {
					while (true) {
						int index = (int) (Math.random() * resultList.size());
						Followers zhongjiang = resultList.get(index);
						boolean yizhongjiang = true;
						for (int j = 0; j < perZhongjiangList.size(); j++) {
							if (zhongjiang.getOpenid().equals(perZhongjiangList.get(j).getOpenid())) {
								yizhongjiang = false;
								break;
							}
						}
						if (yizhongjiang) {
							perZhongjiangList.add(zhongjiang);
							break;
						}
					}
				}
				MemcacheUtils.getMemCachedClientInstance().set("zhongjiang_" + startTime, perZhongjiangList);

				queryJson.put("message", "success");
				queryJson.put("list", perZhongjiangList);

			} else {
				queryJson.put("message", "nouser");
			}
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}

		return "showU";
	}

	/**
	 * 清除某日中奖名单
	 * 
	 * @return
	 */
	public String clearZhongjiang() {
		queryJson = new HashMap<String, Object>();
		try {
			List<Followers> list = new ArrayList<Followers>();
			MemcacheUtils.getMemCachedClientInstance().set("zhongjiang_" + startTime, list);
			queryJson.put("message", "success");
		} catch (Exception e) {
			queryJson.put("message", "fail");
		}
		return "showU";
	}

	/**
	 * 取当天已中奖的名单
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showUYizhongjiang() {
		queryJson = new HashMap<String, Object>();
		try {
			List<Followers> list = new ArrayList<Followers>();
			try {
				list = (List<Followers>) MemcacheUtils.getMemCachedClientInstance().get("zhongjiang_" + startTime) == null ? new ArrayList<Followers>() : (List<Followers>) MemcacheUtils.getMemCachedClientInstance().get("zhongjiang_" + startTime);
			} catch (Exception e) {
				list = new ArrayList<Followers>();
			}
			queryJson.put("message", "success");
			queryJson.put("list", list);

		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}

		return "showU";
	}

	/**
	 * 查询selectjiemu
	 * 
	 * @return
	 */
	public String select() {
		queryJson = new HashMap<String, Object>();
		example.setPage(page);
		example.setRows(rows);
		example.setWaccountId(ActionUtil.findWaccountId());

		try {
			int total = interactService.findCount(example);
			List<Interact> list = interactService.findListByPaging(example);
			// 这里需要过滤敏感词
			WordsExample wordsExample = new WordsExample();
			wordsExample.setWaccountId(example.getWaccountId());
			List<Words> wordsList = wordsService.findListByPaging(wordsExample);
			List<String> contentList = new ArrayList<String>();
			for (Words w : wordsList) {
				contentList.add(w.getContent());
			}

			SensitiveWordFilter filter = new SensitiveWordFilter();
			filter.addKeywords(contentList);

			for (Interact i : list) {
				String content = i.getContent();
				if (content != null && !"".equals(content)) {
					Set<String> set = filter.getTxtKeyWords(content);
					for (String t : set) {
						content = content.replaceAll(t, "<font color=blue>" + t + "</font>");
					}
					i.setContent(content);
				}
			}

			queryJson.put("total", total);
			queryJson.put("rows", list);
		} catch (Exception e) {
			log.error("", e);
		}

		return "select";
	}

	/**
	 * 查询节目单
	 * 
	 * @return
	 */
	public String selectbianji() {
		System.out.println("flag  ====" + programmename.getFlag());
		queryJson = new HashMap<String, Object>();
		// example.setPage(page);
		// example.setRows(rows);
		// example.setWaccountId(ActionUtil.findWaccountId());
		try {
			List<ProgrammeViewName> list = interactService.findjiemuByPaging(programmename);
			int total = 2;
			queryJson.put("total", total);
			queryJson.put("rows", list);
		} catch (Exception e) {
			log.error("", e);
		}

		return "selectbianji";
	}

	public String selectbianji2() {
		System.out.println("flag  ====" + bean.getFlag());
		queryJson = new HashMap<String, Object>();
		// example.setPage(page);
		// example.setRows(rows);
		// example.setWaccountId(ActionUtil.findWaccountId());
		try {
			List<Interact> list = interactService.findyixuanByPaging(bean);
			int total = 2;
			queryJson.put("total", total);
			queryJson.put("rows", list);
		} catch (Exception e) {
			log.error("", e);
		}

		return "selectbianji";
	}

	public String selectchakan() {
		System.out.println("flag  ====" + bean.getFlag());
		queryJson = new HashMap<String, Object>();
		// example.setPage(page);
		// example.setRows(rows);
		// example.setWaccountId(ActionUtil.findWaccountId());
		try {

			List<Interact> list = interactService.findchakanByPaging(bean);
			int total = 2;
			queryJson.put("total", total);
			queryJson.put("rows", list);
		} catch (Exception e) {
			log.error("", e);
		}

		return "selectbianji";
	}

	/**
	 * 按照传送过来的rowIds查询要调单的人员资料
	 * 
	 */
	public String tiaodanList() {
		System.out.println("进来调单了");
		queryJson = new HashMap<String, Object>();
		try {
			Interact interact = new Interact();
			List<Interact> list = new ArrayList<Interact>();

			list = interactService.batchxuanze(interact);
			queryJson.put("total", 2);
			queryJson.put("rows", list);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "tiaodan";
	}

	/**
	 * 显示节目单列表
	 * 
	 * @return
	 */
	public String view() {
		fenyeBean = new FenyeBean();
		fenyeBean.setCurrentPage((page - 1) * rows);
		fenyeBean.setPageSize(rows);
		queryJson = new HashMap<String, Object>();
		try {
			List<Integer> pidList = new ArrayList<Integer>();
			int pageCount = interactService.selectPageCount();
			List<ProgrammeViewName> list = new ArrayList<ProgrammeViewName>();
			list = interactService.Programmeview(fenyeBean);
			queryJson.put("total", pageCount);
			queryJson.put("rows", list);
			queryJson.put("pidList", pidList);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "view";
	}

	public String shanchujiemudan() {
		queryJson = new HashMap<String, Object>();
		try {
			String proId = programmename.getProgramme_id();
			interactService.shanchujiemudan(proId);
			interactService.shanchujiemudanneirong(proId);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "shanchujiemudan";
	}

	/**
	 * 把传送过来的rowIds存入数据库
	 * 
	 */
	public String sessionAdd() {
		queryJson = new HashMap<String, Object>();
		try {
			bean = interactService.selectByKey(bean);
			interactService.batchinsert(bean);
			interactService.updateflagx(bean);
			queryJson.put("rows", bean);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "sessionAdd";
	}

	/**
	 * 从节目单里添加互动信息
	 */
	public String xuanze() {
		queryJson = new HashMap<String, Object>();
		try {
			String programmId = (String) session.getAttribute("programme_id");
			bean = interactService.selectByKey(bean);
			interactService.tianjiainsert(bean, programmId);
			interactService.updateflagx(bean);
			queryJson.put("rows", bean);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}

		return "sessionAdd";
	}

	/**
	 * 根据ID删除互动单条目
	 */
	public String shanchu() {
		queryJson = new HashMap<String, Object>();

		try {
			interactService.shanchu(bean);
			// interactService.batchinsert(bean);
			interactService.setflagx(bean);
			queryJson.put("rows", bean);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "shanchu";
	}

	/**
	 * 根据ID删除节目单里互动单条目
	 */
	public String shanchujiemu() {
		queryJson = new HashMap<String, Object>();

		try {
			interactService.shanchujiemu(bean);
			queryJson.put("rows", bean);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "shanchu";
	}

	/**
	 * 修改节目单名称
	 */

	public String xiugainame() {
		queryJson = new HashMap<String, Object>();
		try {
			String programmename_name = programmename.getProgramme_name();
			String programmename_id = programmename.getProgramme_id();
			interactService.xiugainameUtil(programmename_name, programmename_id);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "xiugainame";
	}

	/**
	 * 
	 * @return
	 */
	public String chankanList() {
		int currentPage = (page - 1) * 10;
		queryJson = new HashMap<String, Object>();
		try {
			int rowCount = interactService.selectPageCountSave(programmeId);
			List<ChakanBean> programmeList = interactService.programmeChankanUtil(programmeId, currentPage);
			queryJson.put("total", rowCount);
			queryJson.put("rows", programmeList);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "chankanList";
	}

	/**
	 * 
	 * @return
	 */
	public String change() {
		chakanBean = new ChakanBean();
		ChakanBean up = new ChakanBean();
		queryJson = new HashMap<String, Object>();
		try {
			String orderId1 = request.getParameter("orderId1");
			String orderId2 = request.getParameter("orderId2");
			String upid = request.getParameter("upid");
			String todownid = request.getParameter("todownid");
			chakanBean.setId(Integer.parseInt(upid));
			chakanBean.setOrderId(orderId2);
			chakanBean.setProgramme_save_id(programmeId);
			up.setId(Integer.parseInt(todownid));
			up.setOrderId(orderId1);
			interactService.updateList(chakanBean);
			interactService.updateList(up);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "chankanList";
	}

	public String savejiemudan() {
		queryJson = new HashMap<String, Object>();
		programmename = new ProgrammeViewName();
		try {
			String name = request.getParameter("name");
			String programmeId = request.getParameter("programmeId");
			Account account = (Account) session.getAttribute("user");
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(now);
			programmename.setProgramme_name(name);
			programmename.setProgramme_id(programmeId);
			programmename.setProgramme_account_name(account.getUsername());
			programmename.setProgramme_time(time);
			programmename.setFlag(0);
			String ids = (String) session.getAttribute("rowId");
			String[] rowIdArray = ids.split(",");
			List<Interact> InteractList = interactService.programmeContent(rowIdArray);
			int count = interactService.ProgrammeCount();
			interactService.programmeSaveName(programmename);
			for (Interact interact : InteractList) {
				interact.setProgrammeId(programmeId);

				interactService.programmeSave(interact);
			}
			if (count == rowIdArray.length) {
				interactService.truncateTable();
			} else {
				interactService.deleteTable(rowIdArray);
			}
			queryJson.put("msg", "success");
			session.removeAttribute("rowId");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "savejiemudan";
	}

	/**
	 * 拒绝
	 * 
	 * @return
	 */
	public String update() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.update(bean);
			interactService.updateflag(bean);
			interactService.updatesavaflag(bean);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "update";
	}

	/**
	 * 节目单里的拒绝
	 * 
	 * @return
	 */
	public String jiemudanupdate() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.update(bean);
			interactService.updateflag(bean);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "update";
	}

	/**
	 * 节目单列表里的更新
	 * 
	 * @return
	 */
	public String updateliebiao() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.update(bean);
			interactService.updateflag(bean);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "update";
	}

	/**
	 * 批量通过
	 * 
	 * @return
	 */
	public String batchTongguo() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.batchTongguo(rowIds);
			this.createXml();
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "update";
	}

	/**
	 * 通过
	 * 
	 * @return
	 */
	public String tongguo() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.tongguo(bean);
			interactService.updateflag(bean);
			interactService.updatesavaflag(bean);
			// interactService.updateflag(bean);
			this.createXml();
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "update";
	}

	/**
	 * 通过节目
	 * 
	 * @return
	 */
	public String tongguojiemu() {
		queryJson = new HashMap<String, Object>();
		try {

			interactService.updateflag(bean);
			interactService.tongguo(bean);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "update";
	}

	/**
	 * 节目单通过
	 * 
	 * @return
	 */
	public String programmeTongguo() {
		queryJson = new HashMap<String, Object>();
		try {
			List<String> pidList = interactService.selectProgrammePid(programmeId);
			interactService.programmejiemudantonguo(programmeId);
			interactService.programmeTongguo(pidList);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "update";
	}

	/**
	 * 添加updatejiemudan
	 * 
	 * @return
	 */
	public String addOrUpdate() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.addOrUpdate(bean, ActionUtil.findWaccountId());
			interactService.updateProgramme(bean);
			interactService.updateProgrammeSave(bean);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "add";
	}

	/**
	 * 节目单内容里面的修改
	 * 
	 * @return
	 */
	public String updatejiemudan() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.updateProgrammeSave(bean);
			// 修改interact页面信息
			// interactService.addOrUpdate(bean, ActionUtil.findWaccountId());
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "add";
	}

	/**
	 * 回复
	 * 
	 * @return
	 */
	public String huifu() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.huifu(bean, ActionUtil.findWaccountId());
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "add";
	}

	/**
	 * 消息推送
	 * 
	 * @return
	 */
	public String allhuifu() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.allhuifu(content, ActionUtil.findWaccountId());
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "addall";
	}

	/**
	 * 按主键查询
	 * 
	 * @return
	 */
	public String selectByKey() {
		queryJson = new HashMap<String, Object>();
		try {
			bean = interactService.selectByKey(bean);
			queryJson.put("rows", bean);
		} catch (Exception e) {
			log.error("", e);
		}
		return "selectByKey";
	}

	/**
	 * 旋转图片
	 * 
	 * @return
	 */
	public String xuanzhuanImage() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.xuanzhuanImage(bean.getImage());
			queryJson.put("url", bean.getImage());
		} catch (Exception e) {
			log.error("", e);
		}
		return "xuanzhuanImage";
	}

	public String xuanzhuanVideo() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.xuanzhuanVideo(bean.getId());
			queryJson.put("message", "success");
		} catch (Exception e) {
			log.error("", e);
		}
		return "xuanzhuanImage";
	}

	/**
	 * 保存位置 pointLocation
	 * 
	 * @return
	 */
	public String pointLocation() {
		System.out.println("ajax");
		queryJson = new HashMap<String, Object>();
		// interactLocation = new InteractLocation();
		try {
			interactService.saveInteractLoaction(interactLocation);
			System.out.println("ajax");
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return null;
	}

	/**
	 * 查询所有用户位置
	 * 
	 * @return
	 */
	public String selectALLLocation() {
		queryJson = new HashMap<String, Object>();
		interactLocation = new InteractLocation();
		try {
			// List<InteractLocation> list = new ArrayList<InteractLocation>();

			interactLocation.setWaccount_id(ActionUtil.findWaccountId());
			List<InteractLocation> list = interactService.selectALLLocation(interactLocation);
			queryJson.put("total", 2);
			queryJson.put("rows", list);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			log.error("", e);
		}
		return "prizeuser";
	}

	/**
	 * 二维码生成
	 * 
	 * @return
	 */
	public String qrcode() {
		try {
			queryJson = new HashMap<String, Object>();
			String m = String.valueOf(System.currentTimeMillis());
			int rand = (new Random()).nextInt(1000);
			String fileName = "WEIXIN" + m + "_" + rand + ".png";
			// 二维码生成地址
			String QRimgPath = ResourceLoader.getInstance().getConfig().getProperty("QRimgPath") + fileName;
			// 二维码下载地址
			String urlPath = ResourceLoader.getInstance().getConfig().getProperty("QRimageUrl");
			// String content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfab418ef0ef51787&redirect_uri=http://218.247.184.209/weixinopen/scanQrcode&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			int width = 600, height = 600;
			if (qrtype.equals("1")) {
				content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfab418ef0ef51787&redirect_uri=http://www.4uplus.com/weixinopen/reports&response_type=code&scope=snsapi_base&state=%s#wechat_redirect";
			}
			if (qrtype.equals("2")) {
				content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfab418ef0ef51787&redirect_uri=http://www.4uplus.com/weixinopen/toepg&response_type=code&qrcodeID=%s&scope=snsapi_base&state=9#wechat_redirect";
			}
			interactService.encode(content, width, height, QRimgPath, ActionUtil.findWaccountId(), qrtype, qrname);
			queryJson.put("msg", "success");
			queryJson.put("imgurl", urlPath + fileName);
		} catch (Exception e) {
			queryJson.put("msg", "fail");
			e.printStackTrace();
		}
		return "qrcode";
	}

	/**
	 * 查询用户位置
	 * 
	 * @return
	 */
	public String selectLocation() {
		queryJson = new HashMap<String, Object>();
		try {
			// String zhutiid = "20";
			List<InteractLocation> list = new ArrayList<InteractLocation>();
			list = interactService.locationlist(zhutiid);
			queryJson.put("total", 2);
			queryJson.put("rows", list);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "location";

	}

	/**
	 * 用户位置通过拒绝
	 * 
	 * @return
	 */
	public String locationFlag() {
		queryJson = new HashMap<String, Object>();
		try {
			// String zhutiid = "20";
			interactService.locationFlag(interactLocation);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "location";

	}

	/**
	 * 查询获奖用户
	 * 
	 * @return
	 */
	public String prizeusers() {
		queryJson = new HashMap<String, Object>();
		try {
			// String zhutiid = "20";
			List<PrizeBean> list = new ArrayList<PrizeBean>();
			list = interactService.prizeusers();
			queryJson.put("total", 2);
			queryJson.put("rows", list);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "prizeuser";

	}

	/**
	 * 创建会议日程
	 * 
	 * @return
	 */
	public String createmeeting() {
		queryJson = new HashMap<String, Object>();
		String meetingid = "";
		try {
			KeyGenerator keyGenerator = new KeyGenerator();
			JSONObject localJObj = JSONObject.fromObject(mcontent);
			String meetingtheme = localJObj.getString("meetingtheme");
			String meetingdata = localJObj.getString("meetingdata");
			String starttime = localJObj.getString("starttime");
			String meetingaddress = localJObj.getString("meetingaddress");
			meetingid = keyGenerator.getKey(meetingAgenda);
			Account account = (Account) session.getAttribute("user");
			meetingAgenda.setMeetingid(meetingid);
			meetingAgenda.setMeetingtheme(meetingtheme);
			meetingAgenda.setMeetingdata(meetingdata);
			meetingAgenda.setStarttime(starttime);
			meetingAgenda.setMeetingaddress(meetingaddress);
			meetingAgenda.setCreateuser(account.getUsername());
			JSONArray contentArray = localJObj.getJSONArray("meetingContent");
			List<MeetingContent> list = new ArrayList<MeetingContent>();
			for (int i = 0; i < contentArray.size(); i++) {
				JSONObject mcontent = contentArray.getJSONObject(i);
				String contentserial = mcontent.getString("contentserial");
				String contenttime = mcontent.getString("contenttime");
				String mtcontent = mcontent.getString("content");
				String contentpersion = mcontent.getString("contentpersion");
				String contentgroup = mcontent.getString("contentgroup");
				MeetingContent meetingContent = new MeetingContent();
				meetingContent.setMeetingid(meetingid);
				meetingContent.setContent(mtcontent);
				meetingContent.setContentserial(contentserial);
				// meetingContent.setMeetingcontentid(keyGenerator.getKey(meetingContent));
				meetingContent.setContentgroup(contentgroup);
				meetingContent.setContentpersion(contentpersion);
				meetingContent.setContenttime(contenttime);
				list.add(meetingContent);
			}
			interactService.createmeeting(meetingAgenda);
			interactService.insertContent(list);
			queryJson.put("total", 2);
			// queryJson.put("rows", list);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "prizeuser";

	}

	/**
	 * 启用与禁用
	 */
	public String meetingstate() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.meetingstate(meetingAgenda);
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
	 * 会议列表
	 * 
	 * @return
	 */
	public String meeting() {
		queryJson = new HashMap<String, Object>();
		try {
			// meetingAgenda.setStatus("1");
			// meetingAgenda.setWaccountid("3");
			List<MeetingAgenda> meeting = interactService.meeting(meetingAgenda);
			queryJson.put("total", 2);
			queryJson.put("rows", meeting);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "prizeuser";

	}

	/**
	 * html展示内容
	 * 
	 * @return
	 */
	public String querymeeting() {
		queryJson = new HashMap<String, Object>();
		try {
			meetingAgenda = interactService.getmeeting(meetingAgenda);
			if (meetingAgenda != null) {
				meetingContent.setMeetingid(meetingAgenda.getMeetingid());
				// questionBean.setHeadtitleid(headtitleBean.getHeadtitleid());
				List<MeetingContent> list = interactService.findContent(meetingContent);
				meetingAgenda.setMeetingContent(list);
			}
			int total = 2;
			queryJson.put("msg", "success");
			queryJson.put("rows", meetingAgenda);
		} catch (Exception e) {
			log.error("", e);
		}
		return "prizeuser";

	}

	public String meetingcontent() {

		queryJson = new HashMap<String, Object>();
		try {
			meetingContent.setMeetingid(type);
			List<MeetingContent> list = interactService.findContent(meetingContent);
			queryJson.put("msg", "success");
			queryJson.put("rows", list);
		} catch (Exception e) {
			log.error("", e);
		}
		return "prizeuser";
	}

	public String updatemeeting() {
		meetingAgenda.setMeetingid(type);
		meetingAgenda = interactService.sidmeeting(meetingAgenda);
		return "updatemeeting";
	}

	public String contentupdate() {
		int contentid = Integer.parseInt(ids);
		meetingContent.setMeetingcontentid(contentid);
		meetingContent = interactService.getmeetingContent(meetingContent);
		return "contentupdate";
	}

	public String shanchucontent() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.shanchucontent(meetingContent);
			queryJson.put("msg", "success");
			// queryJson.put("rows", list);
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "prizeuser";
	}

	public String modifymeeting() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.modifymeeting(meetingAgenda);
			queryJson.put("msg", "success");
			// queryJson.put("rows", list);
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "prizeuser";
	}

	public String modifycontent() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.modifycontent(meetingContent);
			queryJson.put("msg", "success");
			// queryJson.put("rows", list);
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "prizeuser";
	}

	/**
	 * 添加奖品
	 * 
	 * @return
	 */
	public String createprize() {
		queryJson = new HashMap<String, Object>();
		String prizeid = "";
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(date);
			KeyGenerator keyGenerator = new KeyGenerator();
			JSONObject localJObj = JSONObject.fromObject(mcontent);
			String theme = localJObj.getString("theme");
			String starttime = localJObj.getString("starTime");
			String endtime = localJObj.getString("endTime");
			prizeid = keyGenerator.getKey(prizeTitle);
			prizeTitle.setPrizetitleId(prizeid);
			prizeTitle.setPrizetitleName(theme);
			prizeTitle.setStarTime(starttime);
			prizeTitle.setEndTime(endtime);
			prizeTitle.setCreatetime(time);
			prizeTitle.setWaccountId(ActionUtil.findWaccountId());
			prizeTitle.setFlag(0);
			JSONArray contentArray = localJObj.getJSONArray("prize");
			List<Probability> list = new ArrayList<Probability>();
			for (int i = 0; i < contentArray.size(); i++) {
				JSONObject mcontent = contentArray.getJSONObject(i);
				String prizename = mcontent.getString("prizename");
				String prizenb = mcontent.getString("prizenb");
				String probability = mcontent.getString("probability");
				// /int goodsprobability = 100000 * Integer.parseInt(probability);
				double goodsprobability = Double.parseDouble(probability);
				double count = 100000 * goodsprobability;
				Probability pb = new Probability();
				pb.setGoodsProbability(probability);
				pb.setPrize_goodName(prizename);
				pb.setPrize_goodsCount(Integer.parseInt(prizenb));
				pb.setPrizetitleId(prizeid);
				pb.setGoodsProbability(probability);
				pb.setPrize_goodsProbability(goodsprobability);
				pb.setProbabilityNumber(count);
				// pb.setGoodsProbability(goodsprobability);
				pb.setWaccountId(ActionUtil.findWaccountId());

				list.add(pb);
			}
			interactService.createprize(prizeTitle);
			interactService.insertprize(list);
			queryJson.put("total", 2);
			// queryJson.put("rows", list);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "prizeuser";

	}

	/**
	 * createprize 转盘抽奖获取奖品
	 * 
	 * @return
	 */
	public String getprizeall() {
		queryJson = new HashMap<String, Object>();
		try {
			// PrizeTitle prize = new PrizeTitle();
			// Probability probability = new Probability();
			prizeTitle.setWaccountId(waccountId);
			prizeTitle = interactService.getprizeTitle(prizeTitle);
			probability.setWaccountId(prizeTitle.getWaccountId());
			probability.setPrizetitleId(prizeTitle.getPrizetitleId());
			List<Probability> list = interactService.getprize(probability);
			Probability py = new Probability();
			py.setPrize_goodName("谢谢参与");
			list.add(py);
			String[] restaraunts = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				restaraunts[i] = list.get(i).getPrize_goodName();
			}
			queryJson.put("msg", "success");
			queryJson.put("restaraunts", restaraunts);
			// queryJson.put("rows", list);
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "prizeuser";
	}

	public String listprize() {
		queryJson = new HashMap<String, Object>();
		try {
			probability.setPrizetitleId(ids);
			List<Probability> list = interactService.getprize(probability);
			/*
			 * for (int i = 0; i < list.size(); i++) { list.get(i).setGoodsProbability(list.get(i).getPrize_goodsProbability() + ""); System.out.println(list.get(i).getPrize_goodsProbability()); }
			 */
			queryJson.put("msg", "success");
			queryJson.put("rows", list);
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "prizeuser";
	}

	public String goodschaxun() {
		queryJson = new HashMap<String, Object>();
		try {
			probability.setPrize_goodsId(Integer.parseInt(goodsId));
			List<Probability> list = interactService.goodschaxun(probability);
			queryJson.put("msg", "success");
			queryJson.put("rows", list);
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "prizeuser";
	}

	/**
	 * 奖品修改保存
	 * 
	 * @return
	 */
	public String goods() {
		queryJson = new HashMap<String, Object>();
		try {
			double goodsprobability = Double.parseDouble(probability.getGoodsProbability());
			double count = 100000 * goodsprobability;
			probability.setPrize_goodsProbability(goodsprobability);
			probability.setProbabilityNumber(count);
			probability.setGoodsProbability(probability.getGoodsProbability());
			interactService.goods(probability);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "prizeuser";
	}

	/**
	 * 概率抽奖
	 * 
	 * @return
	 */
	public String getprizeone() {
		queryJson = new HashMap<String, Object>();
		try {
			String result = null;
			int status = 0;
			probability.setWaccountId(waccountId);
			List<Probability> list = interactService.getprize(probability);
			for (int i = 0; i < list.size(); i++) {// 概率数组循环
				int randomNum = new Random().nextInt(100000);// 随机生成1到sum的整数
				if (randomNum < list.get(i).getProbabilityNumber() && list.get(i).getPrize_goodsCount() > 0) {// 中奖
					result = list.get(i).getPrize_goodName();
					queryJson.put("result", result);
					queryJson.put("item", i);
					// LocationDao dao = new LocationDao();
					Date date = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = dateFormat.format(date);
					PrizeNameBean prizeBean = new PrizeNameBean();
					KeyGenerator keyGenerator = new KeyGenerator();
					prizeBean.setPrizeID(keyGenerator.getKey(prizeBean));
					prizeBean.setPrizeTime(time);
					prizeBean.setOpenID(openId);
					prizeBean.setPrizeName(result);
					prizeBean.setWaccountID(waccountId);
					prizeBean.setQrcodeID(qrcodeID);
					interactService.saveprizeuser(prizeBean);
					Probability py = new Probability();
					py.setPrize_goodsId(list.get(i).getPrize_goodsId());
					py.setPrize_goodsCount(list.get(i).getPrize_goodsCount() - 1);
					interactService.goodsCountupdate(py);
					break;
				} else {
					// LocationDao dao = new LocationDao();
					Date date = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = dateFormat.format(date);
					PrizeNameBean prizeBean = new PrizeNameBean();
					KeyGenerator keyGenerator = new KeyGenerator();
					prizeBean.setPrizeID(keyGenerator.getKey(prizeBean));
					prizeBean.setPrizeTime(time);
					prizeBean.setOpenID(openId);
					prizeBean.setPrizeName("谢谢参与");
					prizeBean.setWaccountID(waccountId);
					prizeBean.setQrcodeID(qrcodeID);
					if (status == 0) {
						interactService.saveprizeuser(prizeBean);
						status = 1;
					}
				}
			}
			queryJson.put("msg", "success");
			// queryJson.put("result", result);
			// queryJson.put("rows", list);
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "prizeuser";
	}

	/**
	 * 奖品标题列表
	 * 
	 * @return
	 */
	public String prizelist() {
		queryJson = new HashMap<String, Object>();
		try {
			// meetingAgenda.setStatus("1");
			// meetingAgenda.setWaccountid("3");
			List<PrizeTitle> list = interactService.prizelist(prizeTitle);
			queryJson.put("total", 2);
			queryJson.put("rows", list);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "prizeuser";

	}

	/**
	 * 奖品标题列表启用与禁用
	 */
	public String prizetitleFlag() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.prizetitleFlag(prizeTitle);
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
	 * 奖品列表修改
	 * 
	 * @return
	 */
	public String updateprize() {
		prizeTitle.setPrizetitleId(type);
		prizeTitle = interactService.updateprize(prizeTitle);
		return "updateprize";
	}

	/**
	 * 签到用户查询
	 * 
	 * @return selectReport
	 */
	public String selectReport() {
		queryJson = new HashMap<String, Object>();
		try {
			// meetingAgenda.setStatus("1");
			// meetingAgenda.setWaccountid("3");
			List<ReportsBean> list = interactService.selectReport();
			queryJson.put("total", 2);
			queryJson.put("rows", list);
			queryJson.put("msg", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "prizeuser";
	}

	public String updateprizesave() {
		queryJson = new HashMap<String, Object>();
		try {
			interactService.updateprizesave(prizeTitle);
			queryJson.put("msg", "success");
			// queryJson.put("rows", list);
		} catch (Exception e) {
			queryJson.put("msg", "error");
			log.error("", e);
		}
		return "prizeuser";
	}

	/**
	 * ross xml 接口
	 * 
	 * @return
	 */
	public void showRoss() {
		queryJson = new HashMap<String, Object>();
		// String imageUrl = ResourceLoader.getInstance().getConfig().getProperty("imageUrl");
		// String imageWaibuUrl = ResourceLoader.getInstance().getConfig().getProperty("imageWaibuUrl");
		// waccountId = ActionUtil.findWaccountId();
		Interact interact = new Interact();
		// interact.setWaccountId(ActionUtil.findWaccountId());
		try {
			List<Interact> interacts = interactService.showRoss(interact);

			// Interacts interacts = new Interacts();
			// interacts.setInteracts(list);
			XStream xstream = new XStream();
			xstream.alias("interact", Interact.class);
			xstream.alias("interacts", List.class);
			// xstream.alias("interacts", Interacts.class);
			String xml = "<?xml version='1.0' encoding='UTF-8' ?>";
			xml += xstream.toXML(interacts);
			// System.out.println(xml);
			// 解决历史数据问题，将图片的外网地址替换为内网地址
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			// new PrintWriter(new OutputStreamWriter(new (json, "UTF-8"));
			// out.print(xml);
			out.write(xml);
			out.flush();
			out.close();
			queryJson.put("msg", "success");
			queryJson.put("rows", interacts);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * ross xml 接口 征集线索
	 * 
	 * @return
	 */
	public void showLocationRoss() {
		queryJson = new HashMap<String, Object>();
		// String imageUrl = ResourceLoader.getInstance().getConfig().getProperty("imageUrl");
		// String imageWaibuUrl = ResourceLoader.getInstance().getConfig().getProperty("imageWaibuUrl");
		// waccountId = ActionUtil.findWaccountId();
		InteractLocation interactLocation = new InteractLocation();
		// interact.setWaccountId(ActionUtil.findWaccountId());
		try {
			List<InteractLocation> location = interactService.showLocation(interactLocation);

			// Interacts interacts = new Interacts();
			// interacts.setInteracts(list);
			XStream xstream = new XStream();
			xstream.alias("interactLocation", InteractLocation.class);
			// xstream.alias("interacts", List.class);
			// xstream.alias("interacts", Interacts.class);
			String xml = "<?xml version='1.0' encoding='UTF-8' ?>";
			xml += xstream.toXML(location);
			// System.out.println(xml);
			// 解决历史数据问题，将图片的外网地址替换为内网地址
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			// new PrintWriter(new OutputStreamWriter(new (json, "UTF-8"));
			// out.print(xml);
			out.write(xml);
			out.flush();
			out.close();
			queryJson.put("msg", "success");
			queryJson.put("rows", location);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * ross xml 接口 新闻线索
	 * 
	 * @return
	 */
	public void showNewsRoss() {
		queryJson = new HashMap<String, Object>();
		// String imageUrl = ResourceLoader.getInstance().getConfig().getProperty("imageUrl");
		// String imageWaibuUrl = ResourceLoader.getInstance().getConfig().getProperty("imageWaibuUrl");
		// waccountId = ActionUtil.findWaccountId();
		InteractLocation interactLocation = new InteractLocation();
		// interact.setWaccountId(ActionUtil.findWaccountId());
		try {
			List<InteractLocation> location = interactService.showNewsRoss(interactLocation);

			// Interacts interacts = new Interacts();
			// interacts.setInteracts(list);
			XStream xstream = new XStream();
			xstream.alias("newsInteractLocation", InteractLocation.class);
			// xstream.alias("interacts", List.class);
			// xstream.alias("interacts", Interacts.class);
			String xml = "<?xml version='1.0' encoding='UTF-8' ?>";
			xml += xstream.toXML(location);
			// System.out.println(xml);
			// 解决历史数据问题，将图片的外网地址替换为内网地址
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			// new PrintWriter(new OutputStreamWriter(new (json, "UTF-8"));
			// out.print(xml);
			out.write(xml);
			out.flush();
			out.close();
			queryJson.put("msg", "success");
			queryJson.put("rows", location);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * xml文件生成
	 * 
	 * @return
	 */
	public void createXml() {
		try {
			Interact interact = new Interact();
			List<Interact> interacts = interactService.showRoss(interact);
			// XStream xstream = new XStream();
			// xstream.alias("interact", Interact.class);
			// String xml = "<?xml version='1.0' encoding='UTF-8' ?>";
			// xml += "<dataroot xmlns:od='urn:schemas-microsoft-com:officedata' generated='2015-09-30T15:59:58'>";
			// xml += xstream.toXML(interacts);
			// xml += "</dataroot>";
			String path = ResourceLoader.getInstance().getConfig().getProperty("XmlPath");
			XmlCreate create = new XmlCreate();
			create.createXml(interacts, path);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public Interact getBean() {
		return bean;
	}

	public void setBean(Interact bean) {
		this.bean = bean;
	}

	public FenyeBean getFenyeBean() {
		return fenyeBean;
	}

	public void setFenyeBean(FenyeBean fenyeBean) {
		this.fenyeBean = fenyeBean;
	}

	public ProgrammeViewName getProgrammename() {
		return programmename;
	}

	public void setProgrammename(ProgrammeViewName programmename) {
		this.programmename = programmename;
	}

	public Map<String, Object> getQueryJson() {
		return queryJson;
	}

	public void setQueryJson(Map<String, Object> queryJson) {
		this.queryJson = queryJson;
	}

	public InteractService getInteractService() {
		return interactService;
	}

	public void setInteractService(InteractService interactService) {
		this.interactService = interactService;
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

	public InteractExample getExample() {
		return example;
	}

	public void setExample(InteractExample example) {
		this.example = example;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTimesort() {
		return timesort;
	}

	public void setTimesort(String timesort) {
		this.timesort = timesort;
	}

	public WordsService getWordsService() {
		return wordsService;
	}

	public void setWordsService(WordsService wordsService) {
		this.wordsService = wordsService;
	}

	public String getWaccountId() {
		return waccountId;
	}

	public void setWaccountId(String waccountId) {
		this.waccountId = waccountId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRowIds() {
		return rowIds;
	}

	public void setRowIds(String rowIds) {
		this.rowIds = rowIds;
	}

	public String getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}

	public InteractLocation getInteractLocation() {
		return interactLocation;
	}

	public void setInteractLocation(InteractLocation interactLocation) {
		this.interactLocation = interactLocation;
	}

	public String getQrtype() {
		return qrtype;
	}

	public void setQrtype(String qrtype) {
		this.qrtype = qrtype;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getZhutiid() {
		return zhutiid;
	}

	public void setZhutiid(String zhutiid) {
		this.zhutiid = zhutiid;
	}

	public MeetingAgenda getMeetingAgenda() {
		return meetingAgenda;
	}

	public void setMeetingAgenda(MeetingAgenda meetingAgenda) {
		this.meetingAgenda = meetingAgenda;
	}

	public MeetingContent getMeetingContent() {
		return meetingContent;
	}

	public void setMeetingContent(MeetingContent meetingContent) {
		this.meetingContent = meetingContent;
	}

	public String getMcontent() {
		return mcontent;
	}

	public void setMcontent(String mcontent) {
		this.mcontent = mcontent;
	}

	public Probability getProbability() {
		return probability;
	}

	public void setProbability(Probability probability) {
		this.probability = probability;
	}

	public String getQrname() {
		return qrname;
	}

	public void setQrname(String qrname) {
		this.qrname = qrname;
	}

	public PrizeTitle getPrizeTitle() {
		return prizeTitle;
	}

	public void setPrizeTitle(PrizeTitle prizeTitle) {
		this.prizeTitle = prizeTitle;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getQrcodeID() {
		return qrcodeID;
	}

	public void setQrcodeID(String qrcodeID) {
		this.qrcodeID = qrcodeID;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
