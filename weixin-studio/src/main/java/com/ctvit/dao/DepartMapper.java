package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.Depart;



public interface DepartMapper {
	
	String saveDepart(Depart depart);

	void updateDepartBaseInfo(Depart depart);
	
	void deleteDepart(String string);
	
	void insertDepart(Depart depart);
	
	List<Depart> listPageDepart(Depart depart);
	
	List<Depart> listAllDepart();
	
	Depart getDepartById(String string);

	void updateDepartRights(Depart depart);

	int getCountByName(Depart depart);
	
	List<Depart> selectUserbydepart(Depart depart)throws Exception;
}
