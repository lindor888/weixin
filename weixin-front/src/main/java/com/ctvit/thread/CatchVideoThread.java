package com.ctvit.thread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ctvit.bean.Attachment;
import com.ctvit.bean.Interact;
import com.ctvit.dao.InteractDao;
import com.ctvit.util.ConfKit;
import com.ctvit.util.FileUtil;
import com.ctvit.util.HttpKit;
import com.ctvit.util.LoadedText;
import com.ctvit.util.WeixinUtil;

public class CatchVideoThread extends Thread{
	
	private String mediaId;
	
	private String waccountId;
	
	private String fromUserName;
	
	private InteractDao interactDao;
	
	
	public CatchVideoThread(String mediaId, String waccountId, String fromUserName){
		this.mediaId = mediaId;
		this.waccountId = waccountId;
		this.fromUserName = fromUserName;
		interactDao = new InteractDao();
	}
	
	
	@Override
	public void run() {
		
		String videoUrl = "";
		try {
			videoUrl = this.downloadVideo(mediaId, waccountId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//入库
		if(!"".equals(videoUrl)){
			Interact interact = new Interact();
			interact.setWaccountId(waccountId);
			interact.setOpenId(fromUserName);
			interact.setVideo(videoUrl);
			interact.setType(Interact.TYPE_VIDEO);
			interactDao.insert(interact);
		}
	}

	/**
	 * 下载视频
	 * @param mediaId
	 * @param loadedText
	 * @return
	 * @throws IOException 
	 */
	private String downloadVideo(String mediaId, String id) throws IOException{
		LoadedText loadText = new LoadedText();
		String appId = loadText.getAppId(id);
		String secret = loadText.getSecret(id);
		String accessToken = WeixinUtil.getAccessToken(appId, secret);
		String prePath = FileUtil.getFilePath();
		Attachment a = HttpKit.download("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+accessToken+"&media_id="
				+mediaId);
		String filePath = ConfKit.get("filePath") + prePath + a.getFullName();
		String fileUrl = ConfKit.get("fileWebUrl") + prePath + a.getFullName();
		FileOutputStream fos = null;
		File file = new File(filePath);
		if(!file.getParentFile().exists())
		file.getParentFile().mkdirs();
		if(!file.exists()) 
		file.createNewFile();
		BufferedInputStream is = a.getFileStream();
		
		fos = new FileOutputStream(filePath); 
		int ch =0 ;
		try{
			while((ch=is.read())!=-1){
				fos.write(ch);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			fos.close();
			is.close();
		}
		
		return fileUrl;
	}

}
