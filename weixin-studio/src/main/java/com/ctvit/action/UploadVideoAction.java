package com.ctvit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ctvit.util.FileTools;
import com.ctvit.util.ResourceLoader;
import com.opensymphony.xwork2.ActionSupport;

public class UploadVideoAction extends ActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;
	private Logger LOGGER = Logger.getLogger(UploadImageAction.class);
	private Map<String, Object> mapJson;
	private FileTools filetool;
	private File fileInput;
	private String fileInputFileName;

	public Map<String, Object> getMapJson() {
		return mapJson;
	}

	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}

	public FileTools getFiletool() {
		return filetool;
	}

	public void setFiletool(FileTools filetool) {
		this.filetool = filetool;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public String video() {
		try {
			String fileName = getFileInputFileName();
			String suffixname = fileName.substring(fileName.indexOf("."));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String newName = "EPGVideo" + sdf.format(new Date()) + suffixname;
			LOGGER.info(ResourceLoader.getInstance().getConfig().getProperty("videoFilePath") + "==" + ResourceLoader.getInstance().getConfig().getProperty("videoUrl"));
			String filePath = ResourceLoader.getInstance().getConfig().getProperty("videoFilePath") + newName;
			String urlPath = ResourceLoader.getInstance().getConfig().getProperty("videoUrl") + newName;
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				File file = new File(filePath);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				if (!file.exists()) {
					file.createNewFile();
				}
				fos = new FileOutputStream(filePath);
				// 建立文件上传流
				fis = new FileInputStream(fileInput);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				fis.close();
				fos.close();
			}
			mapJson = new HashMap<String, Object>();
			mapJson.put("webUrl", urlPath);
			mapJson.put("status", 0);
		} catch (IOException e) {
			mapJson.put("status", 1);
		}
		return "dft";
	}

}
