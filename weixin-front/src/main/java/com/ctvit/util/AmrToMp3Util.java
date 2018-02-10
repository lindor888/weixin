package com.ctvit.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * 音频转码
 * @author tqc
 *
 */
public class AmrToMp3Util{
	
	private static final Logger log = Logger.getLogger(AmrToMp3Util.class);
	
	private String sourcePath;
	
	private String targetPath;
	
	public AmrToMp3Util(String sourcePath, String targetPath){
		this.sourcePath = sourcePath;
		this.targetPath = targetPath;
	}

	/**
	 * 转码
	 * @throws IOException 
	 */
	public void transForm() {
		try{
			Runtime rt = Runtime.getRuntime();
			String command = "ffmpeg -i " + sourcePath + " " + targetPath;
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
		} catch (Exception e){
			log.error("", e);
		} 
	}
}
