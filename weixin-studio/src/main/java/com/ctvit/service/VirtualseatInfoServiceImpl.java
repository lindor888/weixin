package com.ctvit.service;

import com.ctvit.bean.VirtualseatInfo;
import com.ctvit.dao.VirtualseatInfoMapper;
import com.ctvit.util.ActionUtil;
import com.ctvit.util.ConfLoad;

/**
 * 虚拟座位席实现类
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年11月6日 下午2:10:11
 */
public class VirtualseatInfoServiceImpl {

	private VirtualseatInfoMapper virtualseatInfoMapper;

	public VirtualseatInfoMapper getVirtualseatInfoMapper() {
		return virtualseatInfoMapper;
	}

	public void setVirtualseatInfoMapper(VirtualseatInfoMapper virtualseatInfoMapper) {
		this.virtualseatInfoMapper = virtualseatInfoMapper;
	}

	public void addVirtualInfo(String videoID) {
		int columCount = Integer.parseInt(ConfLoad.get("columCount"));
		int lineCount = Integer.parseInt(ConfLoad.get("lineCount"));
		VirtualseatInfo info = new VirtualseatInfo();
		String waccountID = ActionUtil.findWaccountId();
		info.setVirtualSeatID(waccountID + videoID);
		info.setVideoID(videoID);
		info.setWaccountID(waccountID);
		info.setColumnCount(columCount);
		info.setLineCount(lineCount);
		info.setResultSeats(columCount * lineCount);
		this.virtualseatInfoMapper.addVirtualInfo(info);
	}

}
