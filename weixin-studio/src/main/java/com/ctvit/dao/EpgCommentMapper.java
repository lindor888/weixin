package com.ctvit.dao;

import java.util.List;
import java.util.Map;

import com.ctvit.bean.EpgComment;
import com.ctvit.bean.FenyeBean;

/**
 * EGP节目单 实现层接口
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年8月12日 上午11:46:23
 */

public interface EpgCommentMapper {
	
	List<EpgComment> getList(FenyeBean fenyeBean);
	int getCount();
	List<EpgComment> queryList(Map map);
	int queryCount(Map map);
	List<EpgComment> getListfenye(FenyeBean fenyeBean);

}
