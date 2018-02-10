package com.ctvit.service;

import java.util.Date;
import java.util.List;

import com.ctvit.bean.Depart;
import com.ctvit.dao.DepartMapper;


public class DepartServiceImpl {

	private DepartMapper departMapper;
	

	public DepartMapper getDepartMapper() {
		return departMapper;
	}

	public void setDepartMapper(DepartMapper departMapper) {
		this.departMapper = departMapper;
	}

	public void updateDepartBaseInfo(Depart depart) {
		depart.setModifyDate(new Date());
		departMapper.updateDepartBaseInfo(depart);
	}

	public boolean insertDepart(Depart depart) {
		int count=departMapper.getCountByName(depart);
		if(count>0){
			return false;
		}else{
			departMapper.insertDepart(depart);
			return true;
		}
	}

	public List<Depart> listPageDepart(Depart depart) {
		return departMapper.listPageDepart(depart);
	}

	public List<Depart> listAllDepart() {
		return departMapper.listAllDepart();
	}

	public void deleteDepart(String string) {
		departMapper.deleteDepart(string);
	}

	public Depart getDepartById(String string) {
		return departMapper.getDepartById(string);
	}

	public void updateDepartRights(Depart depart) {
		departMapper.updateDepartRights(depart);
	}
	public List<Depart> selectUserbydepart(Depart depar)throws Exception{
		List<Depart> list=departMapper.selectUserbydepart(depar);
		return list;
	}
}
