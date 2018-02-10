package com.ctvit.thread;



import com.ctvit.bean.Interact;
import com.ctvit.dao.InteractDao;
import com.ctvit.util.WeixinUtil;

/**
 * 抓取图片
 * @author tqc
 *
 */
public class CatchImageThread extends Thread{
	
	private String waccountId;
	
	private String fromUserName;
	
	private String imgUrl;
	
	private InteractDao interactDao;
	
	public CatchImageThread(String imgUrl,String waccountId,String fromUserName){
		this.imgUrl = imgUrl;
		this.waccountId = waccountId;
		this.fromUserName = fromUserName;
		this.interactDao = new InteractDao();
	}
	
	
	
	@Override
	public void run() {
		String picUrl = WeixinUtil.downloadImageUrl(imgUrl);
		Interact interact = new Interact();
		interact.setWaccountId(waccountId);
		interact.setOpenId(fromUserName);
		interact.setImage(picUrl);
		interact.setType(Interact.TYPE_IMAGE);
		interactDao.insert(interact);
	}



	
}
