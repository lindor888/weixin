package com.ctvit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import com.ctvit.util.Categories;
import com.ctvit.util.FileTools;
import com.ctvit.util.IssueUtil;
import com.ctvit.util.PageHolder;
import com.ctvit.util.ResourceLoader;
import com.opensymphony.xwork2.ActionSupport;
public class UploadImageAction extends ActionSupport   {
	private Map<String,Object> mapJson;
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
	
	public String upload1(){
		String v =getFileInputFileName();
		String vv=v.substring(v.indexOf("."));
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");
		SimpleDateFormat dateFormat1=new SimpleDateFormat("MM");
		SimpleDateFormat dateFormat2=new SimpleDateFormat("dd");
		
		Date today = new Date();
		String year=dateFormat.format(today);
		String month=dateFormat1.format(today);
		String day=dateFormat2.format(today);
		String m = String.valueOf(System.currentTimeMillis());
		
		String values = File.separator + year + File.separator + month + File.separator + day + File.separator + m;
		
		Properties pro = ResourceLoader.getInstance().getConfig();
		String localRoot = pro.getProperty("root_path_server");
		String localPreDir = File.separator + "photoAlbum" + File.separator + "page" + File.separator + "performance"
				+ File.separator + "weiXinImg";
		String localPath = localRoot + localPreDir;
		
		String filepath = values+vv;
		String currentFilePath = localPath + filepath;
        File f=new File(localPath);
        if(!f.exists()){
        	f.mkdirs();
        }
		try {
			FileUtils.copyFile(fileInput, new File(currentFilePath));
			
			mapJson = new HashMap<String,Object>();
			IssueUtil util = new IssueUtil();
			if(util.issue(localPath, filepath, pro.getProperty("ftpUser"), pro.getProperty("ftpPwd"))) {
				String tmpfilepath = filepath.replaceAll("\\\\", "/");
				mapJson.put("image", tmpfilepath);
				mapJson.put("status", 0);
			} else {
				mapJson.put("status", 1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "dft";
	}
	
	/**
	 * 直接上传的形式
	 * @return
	 */
	public String upload(){
		try{
			String v = getFileInputFileName();
			String vv = v.substring(v.indexOf(".")).toLowerCase();
			Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR)); 
			String month = String.valueOf(cal.get(Calendar.MONTH)+1);
			String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			String m = String.valueOf(System.currentTimeMillis());
			int rand = (new Random()).nextInt(1000);
			String values = "/" + year + "/" + month + "/" + day + "/";
			//String values = "/";
			String fileName="WEIXIN"+m+"_"+rand+vv;
			String path = ResourceLoader.getInstance().getConfig().getProperty("imageFile") +values+ fileName;
			String urlPath = ResourceLoader.getInstance().getConfig().getProperty("imageUrl") +values + fileName;
			
			FileOutputStream fos = null;
	        FileInputStream fis = null;
			try {
				File file = new File(path);
				if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
				if(!file.exists()) 
				file.createNewFile();
				fos = new FileOutputStream(path);
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
			mapJson = new HashMap<String,Object>();
			mapJson.put("webUrl", urlPath);
			mapJson.put("image", urlPath);
			mapJson.put("status", 0);
		}catch(Exception e){
			mapJson.put("status", 1);
		}
		
		
		return "dft";
	}
	
	
	/*public String upload(){
		String v = getFileInputFileName();
		String vv = v.substring(v.indexOf("."));
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR)); 
		String month = String.valueOf(cal.get(Calendar.MONTH)+1);
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		
		String m = String.valueOf(System.currentTimeMillis());
		
		int rand = (new Random()).nextInt(1000);
		String values = File.separator + year + File.separator + month + File.separator + day + File.separator;
		String fileName="WEIXIN"+m+"_"+rand+vv;
		
		String localPreDir = File.separator + "photoAlbum" + File.separator + "page" + File.separator + "performance"
				+ File.separator + "img";
		String localPreDir1 ="photoAlbum" + File.separator + "page" + File.separator + "performance"
				+ File.separator + "img";
		Properties pro = ResourceLoader.getInstance().getConfig();
		String webPath = pro.getProperty("path_image_page_server");
		String url1 = localPreDir1+values;
		String localRoot = pro.getProperty("root_path_server");
		String remote = localPreDir + values;;
		String currentFilePath = localRoot + remote;
		File fl=new File(currentFilePath);
		if(!fl.exists()){
			fl.mkdirs();
		}
		try {
			FileUtils.copyFile(fileInput, new File(currentFilePath+fileName));
			
			//FileTools toos=new FileTools();
			System.out.println("服务器本地地址："+currentFilePath);
			System.out.println("本地径路："+fileInput.getPath());
			//toos.copyFile(fileInput.getPath(),currentFilePath);
			mapJson = new HashMap<String,Object>();
			IssueUtil util = new IssueUtil();
			if(util.issue(currentFilePath+fileName, localPreDir + values+fileName, pro.getProperty("ftpUser"), pro.getProperty("ftpPwd"))) {
				String webUrl=PageHolder.getDomain(Categories.DOMAIN_TYPE.IMG);
				mapJson.put("webUrl", webUrl+localPreDir + values+fileName);
				mapJson.put("image", localPreDir + values+fileName);
				mapJson.put("status", 0);
			} else {
				mapJson.put("status", 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dft";
	}*/
		
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR)); 
		String month = String.valueOf(cal.get(Calendar.MONTH)+1);
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
	}
}
