package com.ctvit.dao;
import java.util.List;

import com.ctvit.bean.GraphicMaterial;

/**
 * @日期 2013-11-1
 */
public interface GraphicMaterialMapper {
    int deleteByPrimaryKey(String taparid)throws Exception;
    /**
     * @return
     */
    int deleleByActorId(String actorid)throws Exception;
	/**
	 * @return
	 */
	List<GraphicMaterial> selectgraphicMaterial(GraphicMaterial graphicMaterial)throws Exception;
	/**
	 * @return
	 */    
	GraphicMaterial selectOneGraphicByTitle(GraphicMaterial graphicMaterial)throws Exception;
		   
	String countgraphicMaterial(GraphicMaterial graphicMaterial)throws Exception;
	
	void insert(GraphicMaterial graphicMaterial);
	
	GraphicMaterial selectgraphicById(GraphicMaterial graphicMaterial)throws Exception;
	
	void updategraphic(GraphicMaterial graphicMaterial);
	int update(GraphicMaterial gm)throws Exception;
	int updatedeault(GraphicMaterial gm)throws Exception;
	GraphicMaterial selectgraphicWithKeywordsById(String id);
	
}