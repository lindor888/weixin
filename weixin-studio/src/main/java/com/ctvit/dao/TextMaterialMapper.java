package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.TextMaterial;

public interface TextMaterialMapper {
	List<TextMaterial> selectTextMaterialList(TextMaterial textMaterial)throws Exception;
	List<TextMaterial> selectBytitle(TextMaterial textMaterial)throws Exception;
	int counttextMaterial(TextMaterial textMaterial)throws Exception;
	List<TextMaterial> selecttextMaterial(TextMaterial textMaterial)throws Exception;
	TextMaterial selecttextMaterialById(TextMaterial textMaterial)throws Exception;
	TextMaterial selecttextMaterialWithKeywordsById(TextMaterial textMaterial)throws Exception;
	TextMaterial selecttextByTitle(TextMaterial text)throws Exception;
	int update(TextMaterial text)throws Exception;
	void insertText(TextMaterial text) throws Exception;
	int updatedeault(TextMaterial text)throws Exception;
	List<TextMaterial> querytextMaterial(TextMaterial textMaterial)throws Exception;
	int countquerytextMaterial(TextMaterial textMaterial)throws Exception;
	int selectTextCount(TextMaterial textMaterial)throws Exception;
	TextMaterial selecttextByTitleNotSelf(TextMaterial text) throws Exception;
	int existInWaccount(TextMaterial text) throws Exception;
}
