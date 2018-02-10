package com.ctvit.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import com.ctvit.bean.Account;
import com.ctvit.bean.ChakanBean;
import com.ctvit.bean.FenyeBean;
import com.ctvit.bean.Followers;
import com.ctvit.bean.Interact;
import com.ctvit.bean.InteractExample;
import com.ctvit.bean.InteractExample.Criteria;
import com.ctvit.bean.InteractLocation;
import com.ctvit.bean.Interact_baoliaoExt;
import com.ctvit.bean.MeetingAgenda;
import com.ctvit.bean.MeetingContent;
import com.ctvit.bean.PrizeBean;
import com.ctvit.bean.PrizeNameBean;
import com.ctvit.bean.PrizeTitle;
import com.ctvit.bean.Probability;
import com.ctvit.bean.ProgrammeViewName;
import com.ctvit.bean.QrcodeBean;
import com.ctvit.bean.ReportsBean;
import com.ctvit.dao.FollowersMapper;
import com.ctvit.dao.InteractMapper;
import com.ctvit.dao.RandomUtil;
import com.ctvit.util.ActionUtil;
import com.ctvit.util.ImageRotate;
import com.ctvit.util.KeyGenerator;
import com.ctvit.util.MemcacheUtils;
import com.ctvit.util.Message;
import com.ctvit.util.ResourceLoader;
import com.ctvit.util.VideoRotate;
import com.danga.MemCached.MemCachedClient;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class InteractService {

	private InteractMapper interactMapper;

	private FollowersMapper followersMapper;

	private KeyGenerator keyGenerator;

	private ChakanBean chakanBean;

	/**
	 * 添加数据
	 * 
	 * @param bean
	 * @param waccountId
	 * @throws Exception
	 */
	public void addOrUpdate(Interact bean, String waccountId) throws Exception {
		bean.setWaccountId(waccountId);
		// 判断是否存在openId，如果不存在则先插入followers表,如果存在则更新followers表
		if (!StringUtils.isNotBlank(bean.getOpenId())) {
			Followers followers = new Followers();
			followers.setOpenid(keyGenerator.getKey(followers));
			followers.setWaccountId(waccountId);
			followers.setNickname(bean.getNickname());
			followers.setSex(bean.getSex());
			followers.setCity(bean.getCity());
			followers.setCountry(bean.getCountry());
			followers.setProvince(bean.getProvince());
			followers.setHeadimgurl(bean.getHeadimgurl());
			followersMapper.insertSelective(followers);

			bean.setOpenId(followers.getOpenid());
		} else {
			Followers followers = new Followers();
			followers.setOpenid(bean.getOpenId());
			followers.setNickname(bean.getNickname());
			followers.setSex(bean.getSex());
			followers.setCity(bean.getCity());
			followers.setCountry(bean.getCountry());
			followers.setProvince(bean.getProvince());
			followers.setHeadimgurl(bean.getHeadimgurl());
			followersMapper.updateByPrimaryKeySelective(followers);
		}

		if (StringUtils.isNotBlank(bean.getUpdateTimeStr())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			bean.setUpdateTime(sdf.parse(bean.getUpdateTimeStr()));
		} else {
			bean.setUpdateTime(new Date());
		}

		// 判断bean是否已经有id
		if (bean.getId() != null) {
			interactMapper.updateByPrimaryKeySelective(bean);
		} else {
			interactMapper.insertSelective(bean);
		}

	}

	/**
	 * 回复
	 * 
	 * @param bean
	 * @param waccountId
	 * @throws Exception
	 */
	public void huifu(Interact bean, String waccountId) throws Exception {
		String appid = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_appid");
		String secret = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_secret");
		// String bridgeToken = "44d6caeb98c87318";//(String)MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_bridge");
		String bridgeToken = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_bridge");
		// 查询出openId
		System.out.println("service+bridgeToken:" + bridgeToken);
		Interact interact = this.selectByKey(bean);
		Message message = new Message();
		if (StringUtils.isNotBlank(bridgeToken)) {
			try {
				System.out.println("post天脉");
				message.publishToBridge(bean.getHuifu(), bridgeToken, interact.getOpenId());
				// 更新
				interact.setHuifu(bean.getHuifu());
				this.addOrUpdate(interact, waccountId);
			} catch (Exception e) {
				throw new Exception("huifu error");
			}
		} else {
			String result = message.sendText(appid, secret, interact.getOpenId(), bean.getHuifu());
			if (result.indexOf("ok") != -1) {
				// 更新
				interact.setHuifu(bean.getHuifu());
				this.addOrUpdate(interact, waccountId);
			} else {
				throw new Exception("huifu error");
			}
		}
	}

	/**
	 * 消息推送
	 * 
	 * @param bean
	 * @param waccountId
	 * @throws Exception
	 */
	public void allhuifu(String content, String waccountId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", content);
		String appid = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_appid");
		String secret = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_secret");
		String bridgeToken = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_bridge");
		// 查询出openId
		List<String> openid = this.selectOpenid();
		Message message = new Message();
		interactMapper.insertpushtext(map);
		if (StringUtils.isNotBlank(bridgeToken)) {
			try {
				message.publishToBridgeTextAll(content, bridgeToken, openid);
				// 更新
			} catch (Exception e) {
				throw new Exception("huifu error");
			}
		} else {
			String result = message.sendTextAll(appid, secret, openid, content);
			if (result.indexOf("ok") != -1) {
				// 更新
				// interact.setHuifu(bean.getHuifu());
				// this.addOrUpdate(interact, waccountId);
				System.out.println("消息推送");
			} else {
				throw new Exception("huifu error");
			}
		}
	}

	/**
	 * 通过微信转发互动系统回复
	 * 
	 * @param openId
	 * @param content
	 */
	public void zhuanfa(String openId, String content) throws Exception {
		// 先通过openId从follower表中找到公众号id
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", "'" + openId + "'");
		List<Followers> list = followersMapper.selectByIds(map);
		if (list == null || list.size() == 0) {
			throw new Exception("huifu error");
		}
		String waccountId = list.get(0).getWaccountId();
		String appid = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_appid");
		String secret = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_secret");
		String bridgeToken = (String) MemcacheUtils.getMemCachedClientInstance().get(waccountId + "_bridge");
		Message message = new Message();
		if (StringUtils.isNotBlank(bridgeToken)) {
			try {
				message.publishToBridge(content, bridgeToken, openId);
			} catch (Exception e) {
				throw new Exception("huifu error");
			}
		} else {
			String result = message.sendText(appid, secret, openId, URLDecoder.decode(content, "utf-8"));
			if (result.indexOf("ok") == -1) {
				throw new Exception("huifu error");
			}
		}
	}

	/**
	 * 按照传送过来的rowIds查询要调单的人员资料
	 * 
	 */
	public void tiaodanList(String rowIds) {

		RandomUtil ru = new RandomUtil();
		String[] rowIdArray = rowIds.split(",");
		ru.tiaodan(rowIdArray);

	}

	/**
	 * 按主键查询
	 * 
	 * @param bean
	 * @return
	 */
	public Interact selectByKey(Interact bean) {
		bean = interactMapper.selectByPrimaryKey(bean.getId());
		return bean;
	}

	/**
	 * 查询所有openid
	 * 
	 * @param bean
	 * @return
	 */
	public List<String> selectOpenid() {
		List<String> openidList = interactMapper.selectOpenid();
		return openidList;
	}

	/**
	 * 查询总数
	 * 
	 * @param example
	 * @return
	 */
	public int findCount(InteractExample example) {
		setCondition(example);
		return interactMapper.countByExample(example);
	}

	/**
	 * 分页查询
	 * 
	 * @param example
	 * @return
	 */
	public List<Interact> findListByPaging(InteractExample example) {
		setCondition(example);
		return interactMapper.selectByExampleWithBLOBs(example);
	}

	/**
	 * 更新
	 * 
	 * @param bean
	 * @return
	 */
	public int update(Interact bean) {
		return interactMapper.updateByPrimaryKeySelective(bean);
	}

	public int updateflag(Interact bean) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		if (bean.getFlag() == 1) {
			bean.setUpdateTime(date);
		}
		return interactMapper.updateflag(bean);
	}

	/**
	 * 通过
	 * 
	 * @param bean
	 * @return
	 */
	public int tongguo(Interact bean) {
		bean.setUpdateTime(new Date());
		return interactMapper.updateByPrimaryKeySelective(bean);
	}

	/**
	 * 批量通过
	 * 
	 * @param rowIds
	 * @return
	 */
	public void batchTongguo(String rowIds) throws Exception {
		ApplicationContext ac = ContextLoaderListener.getCurrentWebApplicationContext();
		DefaultSqlSessionFactory sqlSessionFactory = (DefaultSqlSessionFactory) ac.getBean("sqlSessionFactory");
		SqlSession session = sqlSessionFactory.openSession();
		session.getConnection().setAutoCommit(false);
		try {
			InteractMapper interactMapper = session.getMapper(InteractMapper.class);
			String[] rowIdArray = rowIds.split(",");
			Date date = new Date();
			for (String id : rowIdArray) {
				Interact bean = new Interact();
				bean.setId(Integer.parseInt(id));
				bean.setUpdateTime(date);
				bean.setFlag(1);
				interactMapper.updateByPrimaryKeySelective(bean);
				interactMapper.updateflag(bean);
				interactMapper.saveflag(bean);
			}
			session.commit();
		} finally {
			session.close();
		}

	}

	/**
	 * 查询已选内容
	 */
	public List<Interact> batchxuanze(Interact interact) throws Exception {
		ApplicationContext ac = ContextLoaderListener.getCurrentWebApplicationContext();
		DefaultSqlSessionFactory sqlSessionFactory = (DefaultSqlSessionFactory) ac.getBean("sqlSessionFactory");
		SqlSession session = sqlSessionFactory.openSession();
		session.getConnection().setAutoCommit(false);
		try {
			interact.setWaccountId(ActionUtil.findWaccountId());
			InteractMapper interactMapper = session.getMapper(InteractMapper.class);
			// String[] rowIdArray = rowIds.split(",");

			return interactMapper.tiaodanlist(interact);
		} finally {
			session.close();
		}

	}

	/**
	 * 将选择的数据插入tab_programme
	 */
	public int batchinsert(Interact interact) throws Exception {
		interact.setOrderId(System.currentTimeMillis() + "");
		return interactMapper.programme_insert(interact);

	}

	/**
	 * 保存输入的名称和随机ID
	 * 
	 * @param name
	 * @param programmeId
	 */
	public void programmeSaveName(String name, String programmeId, Account account) {
		RandomUtil ru = new RandomUtil();
		System.out.println("name" + name);
		System.out.println("programmeId" + programmeId);
		ru.programmeInsert(name, programmeId, account);
	}

	public void programmeSaveName(ProgrammeViewName programmename) {
		programmename.setWaccountId(ActionUtil.findWaccountId());
		interactMapper.programmeSaveName(programmename);
	}

	/**
	 * 保存输入节目单内容
	 * 
	 * @param name
	 * @param programmeId
	 */
	public void programmeSave(Interact interact) {

		// RandomUtil ru = new RandomUtil();
		// ru.programmeSaveProgrammeId(programmeId);
		interactMapper.programmeInsert(interact);

	}

	/**
	 * 保存成功后清空表
	 */
	public void truncateTable() {

		// RandomUtil ru = new RandomUtil();
		interactMapper.truncateTable();
	}

	public List<Interact> programmeContent(String[] rowIdArray) {

		return interactMapper.listContent(rowIdArray);
	}

	/**
	 * 显示节目单列表
	 */

	public List<ProgrammeViewName> Programmeview(FenyeBean fenyeBean) {
		fenyeBean.setWaccountId(ActionUtil.findWaccountId());
		return interactMapper.programmeView(fenyeBean);
	}

	/***
	 * 查看节目单详情
	 */

	public List<Interact> programmeChankan(ChakanBean chakanBean) {
		System.out.println("bean3  " + chakanBean.getProgramme_save_id());
		return interactMapper.programmeChankan(chakanBean);
	}

	public void updateList(ChakanBean chakanBean) {

		interactMapper.updateList(chakanBean);
	}

	/**
	 * 设置flagx
	 */
	public int updateflagx(Interact interact) {

		return interactMapper.update_flagx(interact);

	}

	/**
	 * 删除setflagx
	 */
	public int shanchu(Interact interact) {

		return interactMapper.shanchu(interact);

	}

	/**
	 * 重新设置flagx值
	 */
	public void setflagx(Interact interact) {

		interactMapper.setflagx(interact);
	}

	/**
	 * 对外用户数据接口
	 * 
	 * @param ids
	 * @return
	 */
	public List<Followers> getUserData(String ids, String startTime, String waccountId) {
		List<Followers> list = new ArrayList<Followers>();
		if (StringUtils.isNotBlank(ids)) {
			ids = ids.replace(",", "','");
			ids = "'" + ids + "'";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("waccountId", waccountId);
		map.put("startTime", startTime);
		list = followersMapper.selectByIds(map);
		return list;
	}

	/**
	 * 对外接口
	 * 
	 * @param startTime
	 * @param rows
	 * @param timesort
	 * @return
	 */
	public List<Interact> getData(String startTime, Integer rows, String timesort, String waccountId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Interact> list = new ArrayList<Interact>();
		InteractExample example = new InteractExample();
		Criteria cri = example.createCriteria();
		// 审核通过
		cri.andFlagEqualTo(1);
		if (waccountId != null && !"".equals(waccountId)) {
			cri.andWaccountIdEqualTo(waccountId);
		}
		if (startTime != null && !"".equals(startTime)) {
			Date date = null;
			try {
				date = sdf.parse(startTime);
			} catch (ParseException e) {
				date = null;
			}
			if (date != null) {
				if ("true".equals(timesort)) {
					cri.andUpdateTimeLessThan(date);
				} else {
					cri.andUpdateTimeGreaterThanOrEqualTo(date);
				}
			}
		}
		if (rows > 0) {
			example.setRows(rows);
			example.setBeginIndex(0);
		}
		list = interactMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	/**
	 * 设置查询条件
	 * 
	 * @param example
	 */
	private void setCondition(InteractExample example) {
		Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(example.getWaccountId())) {
			cri.andWaccountIdEqualTo(example.getWaccountId());
		}
		if (example.getFlag() != null) {
			cri.andFlagEqualTo(example.getFlag());
		}
		if (StringUtils.isNotBlank(example.getNickname())) {
			cri.andNicknameLike('%' + example.getNickname() + '%');
		}
	}

	/**
	 * 图片旋转90度
	 * 
	 * @param imgUrl
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void xuanzhuanImage(String imgUrl) throws Exception {
		String imgPath = ResourceLoader.getInstance().getConfig().getProperty("imageFile") + imgUrl.replace(ResourceLoader.getInstance().getConfig().getProperty("imageUrl"), "");
		ImageRotate.Rotate(imgPath, 90);
	}

	/**
	 * 视频旋转90度
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void xuanzhuanVideo(int id) throws Exception {
		VideoRotate videoRotate = new VideoRotate(id);
		videoRotate.rotate();
	}

	public InteractMapper getInteractMapper() {
		return interactMapper;
	}

	public void setInteractMapper(InteractMapper interactMapper) {
		this.interactMapper = interactMapper;
	}

	public FollowersMapper getFollowersMapper() {
		return followersMapper;
	}

	public void setFollowersMapper(FollowersMapper followersMapper) {
		this.followersMapper = followersMapper;
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	public List<ChakanBean> programmeChankanUtil(String programmeId, int currentPage) {
		chakanBean = new ChakanBean();
		chakanBean.setProgramme_save_id(programmeId);
		chakanBean.setCurrentPage(currentPage);
		List<ChakanBean> chakanbeanlist = interactMapper.chakanbean(chakanBean);
		return chakanbeanlist;
	}

	public void shanchujiemudan(String proId) {
		// RandomUtil ru = new RandomUtil();
		ProgrammeViewName programmeViewName = new ProgrammeViewName();
		programmeViewName.setProgramme_id(proId);
		interactMapper.shanchujiemudan(programmeViewName);

	}

	public void shanchujiemudanneirong(String proId) {
		// RandomUtil ru = new RandomUtil();
		chakanBean = new ChakanBean();
		chakanBean.setProgramme_save_id(proId);
		interactMapper.shanchujiemudanneirong(chakanBean);

	}

	public void xiugainameUtil(String programmename, String programmeId) {
		// RandomUtil ru = new RandomUtil();
		ProgrammeViewName programmeViewName = new ProgrammeViewName();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = dateFormat.format(now);
		programmeViewName.setProgramme_name(programmename);
		programmeViewName.setProgramme_id(programmeId);
		programmeViewName.setProgramme_time(time);
		interactMapper.xiugainame(programmeViewName);

	}

	public List<String> selectProgrammePid(String save_id) {
		RandomUtil ru = new RandomUtil();
		List<String> orderIdlist = ru.selectPid(save_id);
		return orderIdlist;
	}

	public void programmeTongguo(List<String> pidList) throws SQLException {
		ApplicationContext ac = ContextLoaderListener.getCurrentWebApplicationContext();
		DefaultSqlSessionFactory sqlSessionFactory = (DefaultSqlSessionFactory) ac.getBean("sqlSessionFactory");
		SqlSession session = sqlSessionFactory.openSession();
		session.getConnection().setAutoCommit(false);
		try {
			InteractMapper interactMapper = session.getMapper(InteractMapper.class);
			// String[] rowIdArray = rowIds.split(",");
			Date date = new Date();
			for (String id : pidList) {
				Interact bean = new Interact();
				bean.setId(Integer.parseInt(id));
				bean.setUpdateTime(date);
				bean.setFlag(1);
				interactMapper.updateProgrammeName(bean);
				interactMapper.updateByPrimaryKeySelective(bean);
				interactMapper.updateflag(bean);
			}
			session.commit();
		} finally {
			session.close();
		}

	}

	public void programmejiemudantonguo(String programmeId) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		ProgrammeViewName programme = new ProgrammeViewName();
		programme.setFlag(1);
		programme.setProgramme_time(time);
		programme.setProgramme_id(programmeId);
		interactMapper.updateProgrammeViewName(programme);

	}

	public void updateProgramme(Interact bean) {

		interactMapper.updateProgramme(bean);
	}

	public void updateProgrammeSave(Interact bean) {
		interactMapper.updateProgrammeSave(bean);
	}

	public void updatesavaflag(Interact bean) {
		bean.setUpdateTime(new Date());
		interactMapper.updatesavaflag(bean);
	}

	public void shanchujiemu(Interact bean) {
		interactMapper.shanchujiemu(bean);
	}

	public List<ProgrammeViewName> findjiemuByPaging(ProgrammeViewName example) {

		return interactMapper.findjiemuByPaging(example);
	}

	public List<Interact> findyixuanByPaging(Interact bean) {

		return interactMapper.findyixuanByPaging(bean);
	}

	public List<Interact> findchakanByPaging(Interact bean) {

		return interactMapper.findchakanByPaging(bean);
	}

	public int selectPageCount() {

		return interactMapper.selectPageCount();
	}

	public int selectPageCountSave(String programmeId) {
		// RandomUtil ru = new RandomUtil();

		// return ru.selectPageCountSave(programmeId);
		chakanBean = new ChakanBean();
		chakanBean.setProgramme_save_id(programmeId);
		return interactMapper.selectPageCountSave(chakanBean);
	}

	public int ProgrammeCount() {

		return interactMapper.ProgrammeCount();
	}

	public void deleteTable(String[] rowIdArray) {
		interactMapper.deleteTable(rowIdArray);
	}

	public void tianjiainsert(Interact bean, String programmId) {
		chakanBean = new ChakanBean();
		chakanBean.setOrderId(bean.getId() + "");
		chakanBean.setProgramme_save_id(programmId);
		chakanBean.setWaccountId(bean.getWaccountId());
		chakanBean.setOpenId(bean.getOpenId());
		chakanBean.setContent(bean.getContent());
		chakanBean.setImage(bean.getImage());
		chakanBean.setInsertTime(bean.getInsertTime());
		chakanBean.setUpdateTime(bean.getUpdateTime());
		chakanBean.setFlag(bean.getFlag());
		chakanBean.setHuifu(bean.getHuifu());
		chakanBean.setVideo(bean.getVideo());
		chakanBean.setType(bean.getType());
		chakanBean.setAudio(bean.getAudio());
		chakanBean.setNickname(bean.getNickname());
		chakanBean.setXuanzhuan(bean.getXuanzhuan());
		chakanBean.setSex(bean.getSex());
		chakanBean.setCity(bean.getCity());
		chakanBean.setCountry(bean.getCountry());
		chakanBean.setProvince(bean.getProvince());
		chakanBean.setHeadimgurl(bean.getHeadimgurl());
		chakanBean.setPid(bean.getId());

		interactMapper.tianjiainsert(chakanBean);

	}

	public List<ChakanBean> export(ChakanBean chakanBean2) {
		// TODO Auto-generated method stub
		return interactMapper.chakanbean(chakanBean2);
	}

	public void saveInteractLoaction(InteractLocation interactLocation) {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(date);
			interactLocation.setInteractid(keyGenerator.getKey(interactLocation));
			interactLocation.setSend_time(time);
			interactLocation.setFlag(0);
			interactMapper.saveInteractLoaction(interactLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 二维码生成
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public void encode(String contents, int width, int height, String imgPath, String waccountID, String qrcodeType, String qrname) {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			QrcodeBean qrcodeBean = new QrcodeBean();
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(date);
			String qrcodeID = keyGenerator.getKey(qrcodeBean);
			MemCachedClient memCachedClientInstance = MemcacheUtils.getMemCachedClientInstance();
			// qrcodeBean.setQrcodeID(keyGenerator.getKey(qrcodeBean));
			if (qrcodeType.equals("1")) {

				memCachedClientInstance.set("qrcodeID", qrcodeID);
				contents = String.format(contents, qrcodeID);
			}
			if (qrcodeType.equals("2")) {
				String qrid = (String) memCachedClientInstance.get("qrcodeID");
				contents = String.format(contents, qrid);
			}
			qrcodeBean.setQrcodeID(qrcodeID);
			qrcodeBean.setQrcodeName(qrname);
			qrcodeBean.setCreateTime(time);
			qrcodeBean.setWaccountID(waccountID);
			qrcodeBean.setQrcodeUrl(contents);
			qrcodeBean.setQrcodeType(qrcodeType);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
			Path path = new File(imgPath).toPath();
			MatrixToImageWriter.writeToPath(bitMatrix, "png", path);

			interactMapper.saveqrcode(qrcodeBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void main( String[] args ) { //message.publishToBridge(bean.getHuifu(), bridgeToken, interact.getOpenId()); String waccountId = "fllower1278dyfsnjskf"; Interact bean = new Interact(); //bean.setId(34); bean.setHuifu("你好"); bean.setOpenId("o8VcbwPAC9a24fGErCBCsm4UNd7I"); InteractService interactService = new InteractService(); try { interactService.huifu(bean, waccountId); } catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */

	public List<InteractLocation> locationlist(String zhutiid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("zhutiid", zhutiid);
		return interactMapper.locationlist(params);
	}

	public List<PrizeBean> prizeusers() {

		return interactMapper.prizeusers();
	}

	public void createmeeting(MeetingAgenda meetingAgenda) {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(date);
			meetingAgenda.setCreatetime(time);
			meetingAgenda.setWaccountid(ActionUtil.findWaccountId());
			meetingAgenda.setStatus("0");
			interactMapper.createmeeting(meetingAgenda);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public List<MeetingAgenda> meeting(MeetingAgenda meetingAgenda) {
		meetingAgenda.setWaccountid(ActionUtil.findWaccountId());
		List<MeetingAgenda> agenda = interactMapper.meeting(meetingAgenda);

		return agenda;
	}

	public void insertContent(List<MeetingContent> list) {
		interactMapper.insertContent(list);

	}

	public void meetingstate(MeetingAgenda meetingAgenda) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		meetingAgenda.setCreatetime(time);
		interactMapper.meetingstate(meetingAgenda);

	}

	public MeetingAgenda getmeeting(MeetingAgenda meetingAgenda) {
		meetingAgenda.setWaccountid(ActionUtil.findWaccountId());
		meetingAgenda.setStatus("1");
		MeetingAgenda ma = interactMapper.getmeeting(meetingAgenda);
		return ma;
	}

	public List<MeetingContent> findContent(MeetingContent meetingContent) {
		List<MeetingContent> lm = interactMapper.findContent(meetingContent);
		return lm;
	}

	public MeetingAgenda sidmeeting(MeetingAgenda meetingAgenda) {
		MeetingAgenda md = interactMapper.sidmeeting(meetingAgenda);
		return md;
	}

	public void modifymeeting(MeetingAgenda meetingAgenda) {
		interactMapper.modifymeeting(meetingAgenda);

	}

	public MeetingContent getmeetingContent(MeetingContent meetingContent) {
		MeetingContent mc = interactMapper.getmeetingContent(meetingContent);
		return mc;
	}

	public void modifycontent(MeetingContent meetingContent) {
		interactMapper.modifycontent(meetingContent);
	}

	public void shanchucontent(MeetingContent meetingContent) {
		interactMapper.shanchucontent(meetingContent);

	}

	public List<Probability> getprize(Probability probability) {
		probability.setWaccountId(ActionUtil.findWaccountId());
		List<Probability> ls = interactMapper.getprize(probability);
		return ls;
	}

	public List<PrizeTitle> prizelist(PrizeTitle prizeTitle) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		prizeTitle.setWaccountId(ActionUtil.findWaccountId());
		prizeTitle.setCreatetime(time);
		List<PrizeTitle> list = interactMapper.prizelist(prizeTitle);

		return list;
	}

	public void prizetitleFlag(PrizeTitle prizeTitle) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		prizeTitle.setCreatetime(time);
		interactMapper.prizetitleFlag(prizeTitle);

	}

	public PrizeTitle updateprize(PrizeTitle prizeTitle) {
		PrizeTitle pt = interactMapper.updateprize(prizeTitle);
		return pt;
	}

	public void updateprizesave(PrizeTitle prizeTitle) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		prizeTitle.setCreatetime(time);
		interactMapper.updateprizesave(prizeTitle);

	}

	public List<Probability> goodschaxun(Probability probability) {
		List<Probability> list = interactMapper.goodschaxun(probability);

		return list;
	}

	public void goods(Probability probability) {
		interactMapper.goods(probability);

	}

	public void createprize(PrizeTitle prizeTitle) {
		interactMapper.createprize(prizeTitle);

	}

	public void insertprize(List<Probability> list) {
		interactMapper.insertprize(list);

	}

	public PrizeTitle getprizeTitle(PrizeTitle prizeTitle) {

		prizeTitle = interactMapper.getprizeTitle(prizeTitle);
		return prizeTitle;
	}

	public void saveprizeuser(PrizeNameBean prizeBean) {
		interactMapper.saveprizeuser(prizeBean);

	}

	public void goodsCountupdate(Probability py) {
		interactMapper.goodsCountupdate(py);

	}

	public List<ReportsBean> selectReport() {
		List<ReportsBean> lr = interactMapper.selectReport();
		return lr;
	}

	public List<Interact> showRoss(Interact bean) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bean.setFlag(1);
		List<Interact> list = new ArrayList<Interact>();
		list = interactMapper.showRoss(bean);
		return list;
	}

	public void locationFlag(InteractLocation interactLocation) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		interactLocation.setSend_time(time);
		interactMapper.locationFlag(interactLocation);

	}

	public List<InteractLocation> showLocation(InteractLocation interactLocation) {
		interactLocation.setFlag(1);
		List<InteractLocation> list = new ArrayList<InteractLocation>();
		list = interactMapper.showLocation(interactLocation);
		return list;
	}

	public List<InteractLocation> showNewsRoss(InteractLocation interactLocation) {
		interactLocation.setFlag(1);
		List<InteractLocation> list = new ArrayList<InteractLocation>();
		list = interactMapper.showNewsRoss(interactLocation);
		return list;
	}

	public List<InteractLocation> selectALLLocation(InteractLocation interactLocation) {
		List<InteractLocation> list = new ArrayList<InteractLocation>();
		list = interactMapper.selectALLLocation(interactLocation);
		return list;
	}

	public Interact_baoliaoExt getUser(Interact_baoliaoExt interact_baoliaoExt) {
		Interact_baoliaoExt ext = interactMapper.getUser(interact_baoliaoExt);
		return ext;
	}
}
