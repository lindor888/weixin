package com.ctvit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ctvit.bean.ChakanBean;
import com.ctvit.bean.FenyeBean;
import com.ctvit.bean.Interact;
import com.ctvit.bean.InteractExample;
import com.ctvit.bean.InteractLocation;
import com.ctvit.bean.Interact_baoliaoExt;
import com.ctvit.bean.MeetingAgenda;
import com.ctvit.bean.MeetingContent;
import com.ctvit.bean.PrizeBean;
import com.ctvit.bean.PrizeNameBean;
import com.ctvit.bean.PrizeTitle;
import com.ctvit.bean.Probability;
import com.ctvit.bean.ProgrammeViewName;
import com.ctvit.bean.QrcodeBean;
import com.ctvit.bean.ReportsBean;

public interface InteractMapper {
	int countByExample(InteractExample example);

	int deleteByExample(InteractExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Interact record);

	int insertSelective(Interact record);

	List<Interact> selectByExampleWithBLOBs(InteractExample example);

	List<Interact> selectByExample(InteractExample example);

	Interact selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Interact record, @Param("example") InteractExample example);

	int updateByExampleWithBLOBs(@Param("record") Interact record, @Param("example") InteractExample example);

	int updateByExample(@Param("record") Interact record, @Param("example") InteractExample example);

	int updateByPrimaryKeySelective(Interact record);

	int updateflag(Interact record);

	int updateByPrimaryKeyWithBLOBs(Interact record);

	int updateByPrimaryKey(Interact record);

	List<Interact> listselect(int ids);

	int programme_insert(Interact interact);

	List<Interact> tiaodanlist(Interact interact);

	int update_flagx(Interact interact);

	int shanchu(Interact interact);

	void setflagx(Interact interact);

	List<Interact> listContent(String[] ids);

	void programmeInsert(Interact interact);

	List<ProgrammeViewName> programmeView(FenyeBean fenyebean);

	List<Interact> programmeChankan(ChakanBean chakanBean);

	void updateProgrammeName(Interact interact);

	void updateProgrammeViewName(ProgrammeViewName programme);

	void updateProgramme(Interact bean);

	void updateProgrammeSave(Interact bean);

	void updatesavaflag(Interact bean);

	void saveflag(Interact bean);

	void shanchujiemu(Interact bean);

	List<ProgrammeViewName> findjiemuByPaging(ProgrammeViewName example);

	List<Interact> findyixuanByPaging(Interact bean);

	List<Interact> findchakanByPaging(Interact bean);

	int selectPageCount();

	int ProgrammeCount();

	void deleteTable(String[] rowIdArray);

	void programmeSaveName(ProgrammeViewName programmename);

	public int selectPageCountSave(ChakanBean chakanBean);

	public List<ChakanBean> chakanbean(ChakanBean chakanBean);

	void shanchujiemudan(ProgrammeViewName programmeViewName);

	void shanchujiemudanneirong(ChakanBean chakanBean);

	void xiugainame(ProgrammeViewName programmeViewName);

	void tianjiainsert(ChakanBean chakanBean);

	Object updateList(ChakanBean chakanBean);

	void truncateTable();

	List<String> selectOpenid();

	void insertpushtext(Map<String, Object> map);

	void saveInteractLoaction(InteractLocation interactLocation);

	void saveqrcode(QrcodeBean qrcodeBean);

	List<InteractLocation> locationlist(Map<String, Object> params);

	List<PrizeBean> prizeusers();

	List<MeetingAgenda> meeting(MeetingAgenda meetingAgenda);

	void createmeeting(MeetingAgenda meetingAgenda);

	void insertContent(List<MeetingContent> list);

	void meetingstate(MeetingAgenda meetingAgenda);

	MeetingAgenda getmeeting(MeetingAgenda meetingAgenda);

	List<MeetingContent> findContent(MeetingContent meetingContent);

	MeetingAgenda sidmeeting(MeetingAgenda meetingAgenda);

	void modifymeeting(MeetingAgenda meetingAgenda);

	MeetingContent getmeetingContent(MeetingContent meetingContent);

	void modifycontent(MeetingContent meetingContent);

	void shanchucontent(MeetingContent meetingContent);

	List<Probability> getprize(Probability probability);

	List<PrizeTitle> prizelist(PrizeTitle prizeTitle);

	void prizetitleFlag(PrizeTitle prizeTitle);

	PrizeTitle updateprize(PrizeTitle prizeTitle);

	void updateprizesave(PrizeTitle prizeTitle);

	List<Probability> goodschaxun(Probability probability);

	void goods(Probability probability);

	void createprize(PrizeTitle prizeTitle);

	void insertprize(List<Probability> list);

	PrizeTitle getprizeTitle(PrizeTitle prizeTitle);

	void saveprizeuser(PrizeNameBean prizeBean);

	void goodsCountupdate(Probability py);

	List<ReportsBean> selectReport();

	List<Interact> showRoss(Interact bean);

	void locationFlag(InteractLocation interactLocation);

	List<InteractLocation> showLocation(InteractLocation interactLocation);

	List<InteractLocation> showNewsRoss(InteractLocation interactLocation);

	List<InteractLocation> selectALLLocation(InteractLocation interactLocation);

	Interact_baoliaoExt getUser(Interact_baoliaoExt interact_baoliaoExt);
}