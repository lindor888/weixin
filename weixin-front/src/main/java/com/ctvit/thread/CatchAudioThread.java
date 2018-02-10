package com.ctvit.thread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ctvit.bean.Attachment;
import com.ctvit.bean.Interact;
import com.ctvit.dao.InteractDao;
import com.ctvit.util.AmrToMp3Util;
import com.ctvit.util.ConfKit;
import com.ctvit.util.FileUtil;
import com.ctvit.util.HttpKit;
import com.ctvit.util.LoadedText;
import com.ctvit.util.WeixinUtil;

public class CatchAudioThread extends Thread {

	private String mediaId;

	private String waccountId;

	private String fromUserName;

	private InteractDao interactDao;

	public CatchAudioThread(String mediaId, String waccountId, String fromUserName) {
		this.mediaId = mediaId;
		this.waccountId = waccountId;
		this.fromUserName = fromUserName;
		interactDao = new InteractDao();
	}

	@Override
	public void run() {
		String audioUrl = "";
		try {
			audioUrl = downloadAudio(mediaId, waccountId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!"".equals(audioUrl)) {
			// 入库
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
	 * 
	 * @param mediaId
	 * @param loadedText
	 * @return
	 * @throws IOException
	 */
	private String downloadAudio(String mediaId, String id) throws IOException {
		LoadedText loadText = new LoadedText();
		String appId = loadText.getAppId(id);
		String secret = loadText.getSecret(id);
		String accessToken = WeixinUtil.getAccessToken(appId, secret);
		String prePath = FileUtil.getFilePath();
		Attachment a = HttpKit.download("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + mediaId);
		String sourcePath = ConfKit.get("filePath") + prePath + a.getFullName();
		FileOutputStream fos = null;
		File file = new File(sourcePath);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		if (!file.exists())
			file.createNewFile();
		BufferedInputStream is = a.getFileStream();

		fos = new FileOutputStream(sourcePath);
		int ch = 0;
		try {
			while ((ch = is.read()) != -1) {
				fos.write(ch);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fos.close();
			is.close();
		}
		// 转码
		String targetPath = ConfKit.get("filePath") + prePath + a.getFullName().split("\\.")[0] + ".mp3";
		AmrToMp3Util amrToMp3Util = new AmrToMp3Util(sourcePath, targetPath);
		amrToMp3Util.transForm();
		String fileUrl = ConfKit.get("fileWebUrl") + prePath + a.getFullName().split("\\.")[0] + ".mp3";
		return fileUrl;
	}
}
