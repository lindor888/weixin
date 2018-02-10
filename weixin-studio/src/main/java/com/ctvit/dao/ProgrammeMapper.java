package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.ChakanBean;

public interface ProgrammeMapper {

	public int selectPageCountSave(ChakanBean chakanBean);

	public List<ChakanBean> chakanbean(ChakanBean chakanBean);

}
