package com.ctvit.writewords.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ctvit.util.Base64Util;
import com.ctvit.util.ResourceLoader;
import com.ctvit.writewords.dao.WriteWordsActivityMapper;
import com.ctvit.writewords.dao.WriteWordsListMapper;
import com.ctvit.writewords.entity.WriteWordsActivity;
import com.ctvit.writewords.entity.WriteWordsActivityExample;
import com.ctvit.writewords.entity.WriteWordsList;
import com.ctvit.writewords.entity.WriteWordsListExample;
import com.ctvit.writewords.entity.WriteWordsListExample.Criteria;

public class WriteWordsListService {
	
	private WriteWordsListMapper writeWordsListMapper;
	
	private WriteWordsActivityMapper writeWordsActivityMapper;
	
	/**
	 * 添加
	 * @param bean
	 */
	public void add(WriteWordsList bean){
		writeWordsListMapper.insertSelective(bean);
	}
	
	/**
	 * 按主键查询
	 * @param bean
	 * @return
	 */
	public WriteWordsList selectByKey(WriteWordsList bean){
		return writeWordsListMapper.selectByPrimaryKey(bean.getId());
	}
	
	/**
	 * 查询总数
	 * @param example
	 * @return
	 */
	public int findCount(WriteWordsListExample example, String waccountId){
		example.setWaccountId(waccountId);
		setCondition(example);
		return writeWordsListMapper.countByExample(example);
	}
	
	/**
	 * 分页查询
	 * @param example
	 * @return
	 */
	public List<WriteWordsList> findListByPaging(WriteWordsListExample example, String waccountId){
		example.setWaccountId(waccountId);
		setCondition(example);
		example.setOrderByClause("a.insert_time desc");
		return writeWordsListMapper.selectByExample(example);
	}
	
	/**
	 * 设置查询条件
	 * @param example
	 */
	private void setCondition(WriteWordsListExample example){
		Criteria cri = example.createCriteria();
		if(StringUtils.isNotBlank(example.getActivityTitle())){
			cri.andActivityTitleLike("%" + example.getActivityTitle() + "%");
		}
		if(example.getBeginTime()!=null){
			Date endTime = new Date(example.getBeginTime().getTime() + 1000 * 60 * 60 * 24); 
			cri.andBeginTimeBetween(example.getBeginTime(), endTime);
		}
		if(StringUtils.isNotBlank(example.getActivityId())){
			cri.andActivityIdEqualTo(example.getActivityId());
		}
		if(StringUtils.isNotBlank(example.getWaccountId())){
			cri.andWaccountIdEqualTo(example.getWaccountId());
		}
	}
	
	/**
	 * 接收提交的汉字并入库
	 * @param bean
	 * @param data
	 * @throws IOException 
	 */
	public void receive(WriteWordsList bean,String data) throws IOException{
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR)); 
		String month = String.valueOf(cal.get(Calendar.MONTH)+1);
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		String fileName = String.valueOf(System.currentTimeMillis())+".png";
		String values = "/" + year + "/" + month + "/" + day + "/";
		
		String path = ResourceLoader.getInstance().getConfig().getProperty("imageFile") +values+ fileName;
		String urlPath = ResourceLoader.getInstance().getConfig().getProperty("imageUrl") +values + fileName;
		
		File file = new File(path);
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		if(!file.exists()) 
			file.createNewFile();
		
		FileOutputStream ous = new FileOutputStream(file);
		ous.write(Base64Util.decode(data));
		ous.close();
		
		//入库（activityId,openId,image）
		bean.setImage(urlPath);
		writeWordsListMapper.insertSelective(bean);
		
	}
	
	/**
	 * 展示最近活动的数据（对外接口）
	 * @return
	 */
	public List<WriteWordsList> show(){
		List<WriteWordsList> list = new ArrayList<WriteWordsList>();
		//先找出最近的活动
		WriteWordsActivityExample writeWordsActivityExample = new WriteWordsActivityExample();
		writeWordsActivityExample.setOrderByClause("update_time desc");
		WriteWordsActivity writeWordsActivity = null;
		try{
			writeWordsActivity = writeWordsActivityMapper.selectByExample(writeWordsActivityExample).get(0);
		}catch(Exception e){
			writeWordsActivity = null;
		}
		
		if(writeWordsActivity==null){
			return list;
		}
		
		String activityId = writeWordsActivity.getActivityId();
		
		WriteWordsListExample example = new WriteWordsListExample();
		example.setActivityId(activityId);
		setCondition(example);
		example.setOrderByClause("a.insert_time desc");
		list = writeWordsListMapper.selectByExample(example);
		return list;
	}
	

	public WriteWordsListMapper getWriteWordsListMapper() {
		return writeWordsListMapper;
	}

	public void setWriteWordsListMapper(WriteWordsListMapper writeWordsListMapper) {
		this.writeWordsListMapper = writeWordsListMapper;
	}

	public WriteWordsActivityMapper getWriteWordsActivityMapper() {
		return writeWordsActivityMapper;
	}

	public void setWriteWordsActivityMapper(
			WriteWordsActivityMapper writeWordsActivityMapper) {
		this.writeWordsActivityMapper = writeWordsActivityMapper;
	}
	
	
	

}
