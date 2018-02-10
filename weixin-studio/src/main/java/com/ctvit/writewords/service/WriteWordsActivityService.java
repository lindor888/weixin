package com.ctvit.writewords.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.ctvit.util.KeyGenerator;
import com.ctvit.util.ResourceLoader;
import com.ctvit.writewords.dao.WriteWordsActivityMapper;
import com.ctvit.writewords.entity.WriteWordsActivity;
import com.ctvit.writewords.entity.WriteWordsActivityExample;
import com.ctvit.writewords.entity.WriteWordsActivityExample.Criteria;

public class WriteWordsActivityService {
	
	private WriteWordsActivityMapper writeWordsActivityMapper;
	
	private KeyGenerator keyGenerator;
	
	/**
	 * 添加或者更新数据
	 * @param bean
	 * @throws Exception 
	 */
	public void addOrUpdate(WriteWordsActivity bean,String waccountId) throws Exception{
		bean.setWaccountId(waccountId);
		//添加
		if(!StringUtils.isNotBlank(bean.getActivityId())){
			String activityId = keyGenerator.getKey(bean);
			bean.setActivityId(activityId);
			writeWordsActivityMapper.insertSelective(bean);
		//更新
		}else{
			writeWordsActivityMapper.updateByPrimaryKeySelective(bean);
		}
	}
	
	/**
	 * 按主键查询
	 * @param bean
	 * @return
	 */
	public WriteWordsActivity selectByKey(WriteWordsActivity bean){
		bean = writeWordsActivityMapper.selectByPrimaryKey(bean.getActivityId());
		return bean;
	}
	
	/**
	 * 查询总数
	 * @param example
	 * @return
	 */
	public int findCount(WriteWordsActivityExample example,String waccountId){
		example.setWaccountId(waccountId);
		setCondition(example);
		return writeWordsActivityMapper.countByExample(example);
	}
	
	/**
	 * 分页查询
	 * @param example
	 * @return
	 */
	public List<WriteWordsActivity> findListByPaging(WriteWordsActivityExample example,String waccountId){
		example.setWaccountId(waccountId);
		setCondition(example);
		return writeWordsActivityMapper.selectByExample(example);
	}
	
	/**
	 * 设置查询条件
	 * @param example
	 */
	private void setCondition(WriteWordsActivityExample example){
		Criteria cri = example.createCriteria();
		if(StringUtils.isNotBlank(example.getActivityTitle())){
			cri.andActivityTitleLike("%" + example.getActivityTitle() +"%");
		}
		if(example.getFlag()!=null){
			cri.andFlagEqualTo(example.getFlag());
		}
		if(StringUtils.isNotBlank(example.getWaccountId())){
			cri.andWaccountIdEqualTo(example.getWaccountId());
		}
	}
	
	/**
	 * 发布，需要更新数据时间以及静态数据文件
	 * @param bean
	 * @throws IOException 
	 */
	public void fabu(WriteWordsActivity bean) throws IOException{
		//先做更新操作
		bean.setUpdateTime(new Date());
		writeWordsActivityMapper.updateByPrimaryKeySelective(bean);
		//发布到静态文件
		bean = writeWordsActivityMapper.selectByPrimaryKey(bean.getActivityId());
		this.createJsonFile(bean);
	}
	
	/**
	 * 回收，需要更新数据时间以及静态数据文件
	 * @param bean
	 * @throws IOException 
	 */
	public void huishou(WriteWordsActivity bean) throws IOException{
		//先做更新操作
		bean.setUpdateTime(new Date());
		writeWordsActivityMapper.updateByPrimaryKeySelective(bean);
		//查询最新的数据
		WriteWordsActivityExample example = new WriteWordsActivityExample();
		example.setFlag(1);
		example.setOrderByClause("update_time desc");
		setCondition(example);
		try{
			bean = writeWordsActivityMapper.selectByExample(example).get(0);
		}catch(Exception e){
			bean = null;
		}
		this.createJsonFile(bean);
	}
	
	/**
	 * 生成静态文件
	 * @param bean
	 * @throws IOException 
	 */
	private void createJsonFile(WriteWordsActivity bean) throws IOException{
		String fileName = "activity.json";
		String filePath = ResourceLoader.getInstance().getConfig().getProperty("imageFile");
		String fileFullPath = filePath + fileName;
		File file = new File(fileFullPath);
		if(!file.getParentFile().exists())
		file.getParentFile().mkdirs();
		if(!file.exists()) 
		file.createNewFile();
		
		JSONObject content = new JSONObject();
		JSONObject tjo = new JSONObject();
		
		if(bean!=null){
			tjo.put("activityId", bean.getActivityId());
			tjo.put("activityTitle", bean.getActivityTitle());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String beginTimeStr = sdf.format(bean.getBeginTime());
			tjo.put("beginTime", beginTimeStr);
			tjo.put("lastTime", bean.getLastTime());
		}
		content.put("activity", tjo);
		
		OutputStream ous = new FileOutputStream(file);
		ous.write(("var data = " + content.toString()).getBytes());
		
		ous.close();
	}
	

	public WriteWordsActivityMapper getWriteWordsActivityMapper() {
		return writeWordsActivityMapper;
	}

	public void setWriteWordsActivityMapper(WriteWordsActivityMapper writeWordsActivityMapper) {
		this.writeWordsActivityMapper = writeWordsActivityMapper;
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

}
