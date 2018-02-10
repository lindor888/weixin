package com.ctvit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;

import com.ctvit.bean.WxCustomizeMenus;
import com.ctvit.dao.QueryDataBean;
import com.ctvit.dao.WxCustomizeMenusMapper;
import com.ctvit.util.KeyGenerator;





public class CustomizeMenuServiceImpl implements WxCustomizeMenusMapper {

	private WxCustomizeMenusMapper customizemenumapper;
	private SqlSessionFactory sqlSessionFactory;
	private KeyGenerator keyGenerator;
	
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return customizemenumapper.deleteByPrimaryKey(id);
	}

	public int insert(WxCustomizeMenus record) {
		// TODO Auto-generated method stub
		record.setCreattime(new Timestamp((new Date()).getTime()));
		record.setModifitime(new Timestamp((new Date()).getTime()));
		try{
			String id=keyGenerator.getKey(record);
			record.setId(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return customizemenumapper.insert(record);
	}

	public int insertSelective(WxCustomizeMenus record) {
		// TODO Auto-generated method stub
		return customizemenumapper.insertSelective(record);
	}

	public WxCustomizeMenus selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return customizemenumapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(WxCustomizeMenus record) {
		// TODO Auto-generated method stub
		return customizemenumapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(WxCustomizeMenus record) {
		// TODO Auto-generated method stub
		return customizemenumapper.updateByPrimaryKey(record);
	}
	public List<WxCustomizeMenus> selectByExample(QueryDataBean queryData) {
		// TODO Auto-generated method stub
		    
		return customizemenumapper.selectByExample(queryData);
	}
	
	
	
	public int menuCount(String id) {
		// TODO Auto-generated method stub
		return customizemenumapper.menuCount(id);
	}

	public int menuparentCount(String id) {
		// TODO Auto-generated method stub
		return  customizemenumapper.menuparentCount(id);
	}

	public List<Map<String,Object>> selectAll(QueryDataBean queryData){
		List<Map<String,Object>> maplist=new ArrayList<Map<String,Object>>();
		try{
		List<WxCustomizeMenus> list=this.selectByExample(queryData);
		
		if(null!=list&&list.size()>0){
			for (int i=0;i<list.size();i++){
				WxCustomizeMenus m =list.get(i);
				Map<String,Object> map = new LinkedHashMap<String,Object>();
				map.put("id", m.getId());
				map.put("parentid", m.getParentid());
				map.put("name", m.getName());
				                               
			
				if(m.getContenttype()==1||m.getContenttype()==2){
					String CatalogTitle=((WxCustomizeMenus)this.selectById(m.getId())).getCatalogTitle();
				 //  map.put("MaterialId", CatalogTitle);//素材内容id
				
				   if(m.getContenttype()==1){
						map.put("contentType", "text");//素材类型
						}else {
						map.put("contentType", "content");
						
						}
				}else if (m.getContenttype()==3){
					map.put("contentType", "view");
					}
				map.put("MaterialId", m.getMaterialid());//素材内容id
				map.put("sub_button",new ArrayList());//
				map.put("menu_key", m.getState());//没有素材内容
				maplist.add(map);
				
			}
		}
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return maplist  ;
	}

	
	
	
	public int menuparentCountByName(WxCustomizeMenus record) {
		// TODO Auto-generated method stub
		return customizemenumapper.menuparentCountByName( record);
	}

	public WxCustomizeMenus selectById(String id) {
		// TODO Auto-generated method stub
		return customizemenumapper.selectById(id);

	}

	public WxCustomizeMenusMapper getCustomizemenumapper() {
		return customizemenumapper;
	}

	public void setCustomizemenumapper(WxCustomizeMenusMapper customizemenumapper) {
		this.customizemenumapper = customizemenumapper;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}


	
}
