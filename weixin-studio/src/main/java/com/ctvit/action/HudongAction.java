package com.ctvit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Followers;
import com.ctvit.bean.Interact_baoliao;
import com.ctvit.bean.Interact_baoliaoExt;
import com.ctvit.bean.Interact_baoliao_object;
import com.ctvit.bean.Interact_comment;
import com.ctvit.dao.GetUserDao;
import com.ctvit.service.BaoLiaoService;
import com.ctvit.util.ActionUtil;
import com.ctvit.util.HttpKit;
import com.ctvit.util.ResourceLoader;

public class HudongAction {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	private Logger log = Logger.getLogger(InteractAction.class);

	private Interact_baoliao baoliao;

	private Interact_comment comment;

	private Map<String, Object> queryJson;

	private BaoLiaoService baoLiaoService;

	private Interact_baoliao_object baoliaoObject;

	private String waccountid;

	private String content;

	private int currentPage;

	private int pageSize = 10;

	private int parentid;
	private File fileInput;
	private String fileInputFileName;

	public String add() {
		queryJson = new HashMap<String, Object>();
		try {
			baoliao.setVid_url(waccountid);
			baoliao.setCtime(new Date());
			baoliao.setStatus(true);
			baoLiaoService.add(baoliao);

			queryJson.put("message", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "list";
	}

	public String addtitle() {
		queryJson = new HashMap<String, Object>();
		try {
			baoliaoObject.setJf_id(ActionUtil.findWaccountId());
			baoliaoObject.setCtime((int) (new Date().getTime()));
			String imgPath = this.upload();
			baoliaoObject.setImg(imgPath);
			baoliaoObject.setType("zhengji");
			baoLiaoService.addtitle(baoliaoObject);

			queryJson.put("message", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "list";
	}

	public String getinfo() {
		queryJson = new HashMap<String, Object>();
		try {
			baoliaoObject.setJf_id(waccountid);
			Interact_baoliao_object baoliaoObj = baoLiaoService.getinfo(baoliaoObject);
			queryJson.put("message", "success");
			queryJson.put("rows", baoliaoObj);
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "list";
	}

	public String select() {
		queryJson = new HashMap<String, Object>();
		try {
			String url = "http://localhost:8080/weixin-studio/interact/showUInteract?ids=";
			baoliao.setVid_url(ActionUtil.findWaccountId());
			int startPage = (currentPage - 1) * pageSize;
			baoliao.setCurrentPage(startPage);
			baoliao.setPageSize(pageSize);
			baoliao.setParentid(parentid);
			List<Interact_baoliaoExt> list = baoLiaoService.select(baoliao);
			GetUserDao dao = new GetUserDao();
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					// String result = HttpKit.get(url + list.get(i).getUserid());
					// JSONObject localJObj = JSONObject.fromObject(result);
					// JSONArray array = localJObj.getJSONArray("rows");
					// JSONObject msg = array.getJSONObject(0);
					// list.get(i).setCity(msg.getString("city"));
					// list.get(i).setHeadimgurl(msg.getString("headimgurl"));
					// list.get(i).setNickname(msg.getString("nickname"));
					// list.get(i).setTitle(list.get(i).getTitle().split("=|")[0]);
					Followers f = dao.getU(list.get(i).getUserid());
					list.get(i).setHeadimgurl(f.getHeadimgurl());
					list.get(i).setCity(f.getCity());
					list.get(i).setNickname(f.getNickname());
				}
			}
			queryJson.put("rows", list);
			// System.out.println(list.get(0).getTitle()); search
			queryJson.put("message", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "list";
	}

	public String search() {
		queryJson = new HashMap<String, Object>();
		try {
			String url = "http://localhost:8080/weixin-studio/interact/showUInteract?ids=";
			baoliao.setVid_url(ActionUtil.findWaccountId());

			List<Interact_baoliaoExt> list = baoLiaoService.search(baoliao);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String result = HttpKit.get(url + list.get(i).getUserid());
					JSONObject localJObj = JSONObject.fromObject(result);
					JSONArray array = localJObj.getJSONArray("rows");
					JSONObject msg = array.getJSONObject(0);
					list.get(i).setCity(msg.getString("city"));
					list.get(i).setHeadimgurl(msg.getString("headimgurl"));
					list.get(i).setNickname(msg.getString("nickname"));
					list.get(i).setTitle(list.get(i).getTitle().split("=|")[0]);
				}
			}
			queryJson.put("rows", list);
			// System.out.println(list.get(0).getTitle()); search
			queryJson.put("message", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "list";
	}

	public String addcomment() {
		queryJson = new HashMap<String, Object>();
		try {
			comment.setCreated(new Date());
			comment.setParentid("21");
			comment.setStatus(true);
			baoLiaoService.addcomment(comment);

			queryJson.put("message", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "list";
	}

	public String selectcomment() {
		queryJson = new HashMap<String, Object>();
		try {
			comment.setUsername(ActionUtil.findWaccountId());
			int startPage = (currentPage - 1) * pageSize;
			comment.setCurrentPage(startPage);
			comment.setPageSize(pageSize);
			List<Interact_comment> list = baoLiaoService.selectcomment(comment);
			GetUserDao dao = new GetUserDao();
			for (int i = 0; i < list.size(); i++) {
				Followers f = dao.getU(list.get(i).getUserid());
				list.get(i).setUsername(f.getNickname());
				list.get(i).setTopicid(f.getCity());
				list.get(i).setUserhead(f.getHeadimgurl());
			}
			queryJson.put("rows", list);
			queryJson.put("message", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "list";
	}

	public String searchcomment() {
		queryJson = new HashMap<String, Object>();
		try {
			comment.setItemtype(ActionUtil.findWaccountId());

			List<Interact_comment> list = baoLiaoService.searchcomment(comment);

			queryJson.put("rows", list);
			queryJson.put("message", "success");
		} catch (Exception e) {
			queryJson.put("message", "error");
			log.error("", e);
		}
		return "list";
	}

	public Interact_baoliao getBaoliao() {
		return baoliao;
	}

	public void setBaoliao(Interact_baoliao baoliao) {
		this.baoliao = baoliao;
	}

	public Map<String, Object> getQueryJson() {
		return queryJson;
	}

	public void setQueryJson(Map<String, Object> queryJson) {
		this.queryJson = queryJson;
	}

	public BaoLiaoService getBaoLiaoService() {
		return baoLiaoService;
	}

	public void setBaoLiaoService(BaoLiaoService baoLiaoService) {
		this.baoLiaoService = baoLiaoService;
	}

	public Interact_baoliao_object getBaoliaoObject() {
		return baoliaoObject;
	}

	public void setBaoliaoObject(Interact_baoliao_object baoliaoObject) {
		this.baoliaoObject = baoliaoObject;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getWaccountid() {
		return waccountid;
	}

	public void setWaccountid(String waccountid) {
		this.waccountid = waccountid;
	}

	public Interact_comment getComment() {
		return comment;
	}

	public void setComment(Interact_comment comment) {
		this.comment = comment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	/**
	 * 文件上传方法
	 * 
	 * @return
	 */
	public String upload() {
		String urlPath = null;
		try {
			String v = getFileInputFileName();
			String vv = v.substring(v.lastIndexOf(".")).toLowerCase();
			Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			String m = String.valueOf(System.currentTimeMillis());
			int rand = (new Random()).nextInt(1000);
			String values = "/" + year + "/" + month + "/" + day + "/";
			// String values = "/";
			String fileName = "WEIXIN" + m + "_" + rand + vv;
			String path = ResourceLoader.getInstance().getConfig().getProperty("imageFile") + values + fileName;
			urlPath = ResourceLoader.getInstance().getConfig().getProperty("imageUrl") + values + fileName;

			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				File file = new File(path);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				if (!file.exists())
					file.createNewFile();
				fos = new FileOutputStream(path);
				// 建立文件上传流
				fis = new FileInputStream(fileInput);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				fis.close();
				fos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return urlPath;
	}

}
