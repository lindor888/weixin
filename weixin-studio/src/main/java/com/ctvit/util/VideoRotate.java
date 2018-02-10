package com.ctvit.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.Interact;
import com.ctvit.service.InteractService;

/**
 * 视频转向工具类
 * @author tqc
 *
 */
public class VideoRotate {
	
	private static final Logger log = Logger.getLogger(VideoRotate.class);
	
	private int id;
	
	
	public VideoRotate(int id){
		this.id = id;
	}
	
	public void rotate() throws Exception{
		try{
			WebApplicationContext wac = ContextLoaderListener.getCurrentWebApplicationContext();
			InteractService interactService = wac.getBean(InteractService.class);
			Interact selectInteract = new Interact();
			selectInteract.setId(id);
			Interact interact = interactService.selectByKey(selectInteract);
			String sourceUrl = interact.getVideo();
			String prePath = ResourceLoader.getInstance().getConfig().getProperty("imageFile");
			String preUrl = ResourceLoader.getInstance().getConfig().getProperty("imageUrl");
			
			String sourcePath = prePath + sourceUrl.replace(preUrl, "");
			String sourceFileName = sourcePath.substring(sourcePath.lastIndexOf("/")+1, sourcePath.length()).split("\\.")[0];
			String targetFileName = System.currentTimeMillis() + "";
			String targetPath = sourcePath.replace(sourceFileName, targetFileName);
			String targetUrl = sourceUrl.replace(sourceFileName, targetFileName);
			
			
			Runtime rt = Runtime.getRuntime();
			String command = "/root/ffmpeg/ffmpeg -i " + sourcePath + " -vcodec h264 -preset slower -crf 18 -threads 4 -vf transpose=1 " +
					"-acodec copy " + targetPath;
			log.info(command);
			log.info(command);
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null)
				sb.append(line);
			int exitVal = proc.waitFor();
			log.info("wait " + exitVal);
			log.info(sb.toString());
			
			//更新文件路径
			interact.setVideo(targetUrl);
			interactService.update(interact);
		}catch(Exception e){
			log.error("", e);
		}
	}
}
