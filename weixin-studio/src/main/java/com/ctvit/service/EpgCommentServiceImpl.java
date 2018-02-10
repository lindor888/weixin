package com.ctvit.service;

import java.util.List;
import java.util.Map;

import com.ctvit.bean.EpgComment;
import com.ctvit.bean.FenyeBean;
import com.ctvit.dao.EpgCommentMapper;

/**
 * 视频评论业务层
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年10月20日 下午2:10:33
 */

public class EpgCommentServiceImpl {
	
	private EpgCommentMapper epgCommentMapper;
	
	public EpgCommentMapper getEpgCommentMapper() {
		return epgCommentMapper;
	}

	public void setEpgCommentMapper(EpgCommentMapper epgCommentMapper) {
		this.epgCommentMapper = epgCommentMapper;
	}

	/**
	 * 获得分页的列表数据
	 * @author guosidi
	 * @date 2015年10月20日 下午3:45:00
	 * @param fenyeBean
	 * @return
	 * @return List<EpgComment>
	 */
	public List<EpgComment> getList(FenyeBean fenyeBean) {
		return epgCommentMapper.getList(fenyeBean);
	}
	
	/**
	 * 获得分页的列表数据
	 * @author guosidi
	 * @date 2015年10月20日 下午3:45:00
	 * @param fenyeBean
	 * @return
	 * @return List<EpgComment>
	 */
	public int getCount() {
		return epgCommentMapper.getCount();
	}
	
	/**
	 * 带条件查询列表
	 * @author guosidi
	 * @date 2015年10月20日 下午6:47:08
	 * @param map
	 * @return
	 * @return List<EpgComment>
	 */
	public List<EpgComment> queryList(Map map) {
		return epgCommentMapper.queryList(map);
	}
	
	/**
	 * 带条件查询总量
	 * @author guosidi
	 * @date 2015年10月20日 下午6:47:08
	 * @param map
	 * @return
	 * @return List<EpgComment>
	 */
	public int queryCount(Map map){
		return epgCommentMapper.queryCount(map);
	}
	
	public List<EpgComment> getListfenye(FenyeBean fenyeBean) {
		List<EpgComment> epgComment = epgCommentMapper.getListfenye(fenyeBean);
		return epgComment;
	}

}
