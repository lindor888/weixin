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

/**
 * 从天脉下载音频
 * @author tqc
 *
 */
public class CatchBridgeAudioThread extends Thread{
	
	private String localUrl;
	
	private String waccountId;
	
	private String fromUserName;
	
	private InteractDao interactDao;
	
	public CatchBridgeAudioThread(String localUrl, String waccountId, String fromUserName){
		this.localUrl = localUrl;
		this.waccountId = waccountId;
		this.fromUserName = fromUserName;
		interactDao = new InteractDao();
	}
	
	@Override
	public void run() {
		String audioUrl = "";
		try {
			audioUrl = downloadAudio(localUrl, waccountId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!"".equals(audioUrl)){
			//入库
			Interact interact = new Interact();
			interact.setWaccountId(waccountId);
			interact.setOpenId(fromUserName);
			interact.setAudio(audioUrl);
			interact.setType(Interact.TYPE_AUDIO);
			interactDao.insert(interact);
		}
		
	}


	/**
	 * 下载音频和转码
	 * @param mediaId
	 * @param loadedText
	 * @return
	 * @throws IOException 
	 */
	private String downloadAudio(String localUrl, String id) throws IOException{
		String prePath = FileUtil.getFilePath();
		
		
		
		
		
		Attachment a = HttpKit.download(localUrl);
		String sourcePath = ConfKit.get("filePath") + prePath + a.getFullName();
		FileOutputStream fos = null;
		File file = new File(sourcePath);
		if(!file.getParentFile().exists())
		file.getParentFile().mkdirs();
		if(!file.exists()) 
		file.createNewFile();
		BufferedInputStream is = a.getFileStream();
		
		fos = new FileOutputStream(sourcePath); 
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
		//转码
		/*String targetPath = ConfKit.get("filePath") + prePath + a.getFullName().split("\\.")[0] +".mp3";
		AmrToMp3Util amrToMp3Util = new AmrToMp3Util(sourcePath, targetPath);
		amrToMp3Util.transForm();*/
		String fileUrl = ConfKit.get("fileWebUrl") + prePath + a.getFullName();
		return fileUrl;
	}
}
