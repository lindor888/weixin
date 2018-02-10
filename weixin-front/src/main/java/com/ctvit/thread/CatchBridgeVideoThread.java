package com.ctvit.thread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.ctvit.bean.Attachment;
import com.ctvit.bean.Interact;
import com.ctvit.dao.InteractDao;
import com.ctvit.util.ConfKit;
import com.ctvit.util.FileUtil;
import com.ctvit.util.HttpKit;

/**
 * 从天脉下载视频
 * @author tqc
 *
 */
public class CatchBridgeVideoThread extends Thread{
	
	private static final Logger log = Logger.getLogger(CatchBridgeVideoThread.class);
	
	private String localUrl;
	
	private String waccountId;
	
	private String fromUserName;
	
	private InteractDao interactDao;
	
	
	public CatchBridgeVideoThread(String localUrl, String waccountId, String fromUserName){
		this.localUrl = localUrl;
		this.waccountId = waccountId;
		this.fromUserName = fromUserName;
		interactDao = new InteractDao();
	}
	
	
	@Override
	public void run() {
		
		String videoUrl = "";
		try {
			videoUrl = this.downloadVideo(localUrl, waccountId);
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
	 * @param localUrl
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	private String downloadVideo(String localUrl, String id) throws IOException{
		String prePath = FileUtil.getFilePath();
		
		log.info("localUrl : " + localUrl);
		Attachment a = HttpKit.download(localUrl);
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
