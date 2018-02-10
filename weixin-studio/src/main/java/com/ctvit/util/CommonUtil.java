package com.ctvit.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class CommonUtil {
	private static final Logger logger = LogManager.getLogger(CommonUtil.class);
	  /**
	   * 校验字符是否为空
	   * @param str
	   * @return
	   */
	  public static final boolean isNullStr(String str) {
		    if ( (str == null) || (str.length() == 0)) {
		      return (true);
		    }
		    return (false);
	  }
	  public static String GenerateUUID() {
			return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
	  }
	  /**
	   * 获取当前时间
	   * @param 
	   * @return 时间格式 2010-10-4 15:22:13
	   */
	  public static String getNowTime(){
		Date now = new Date(); 
		return DateFormat.getDateTimeInstance().format(now);
		  
	  }
	  public static String date2String(Date date , String formate){
			String ret = null;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formate);
			ret = simpleDateFormat.format(date);
			return ret;
		}
	  /**
	   * 获取当前时间
	   * @param 
	   * @return 时间格式 2010-10-4 15:22:13
	   */
	  public static String getTodayDate(String format){
		Date now = new Date(); 
		DateFormat df = new SimpleDateFormat(format);
		return df.format(now);
	  }
	  /**
	   * 获取当前时间N分钟后前时间
	   * @param 
	   * @return 时间格式 2010-10-4 15:22:13
	   */
	  public static String getTodayDate(String format,int minute ){
		Date now = new Date(); 
		DateFormat df = new SimpleDateFormat(format);
		return df.format(now.getTime()-minute *60*100);
	  }
		 /**
		 * author:weixing
		 * 用时间加随机数的方式产生一个唯一id
		 * @return long 16位一个随机数
		 */
		public static long getId() {
			long result = 0;
			long id = 0;
			String total = "";
			id = System.currentTimeMillis();
			Random random = new Random();
			int ranNum = random.nextInt(1000);
			if (ranNum < 10) {
				total = "" + id + "00" + ranNum;
			} else if (ranNum < 100){
				total = "" + id + "0" + ranNum;
			} else {
				total = "" + id + ranNum;
			}
			result = Long.parseLong(total);
			return result;
		}
		
		/**
		 * 单个文件复制
		 * @param oldPath
		 * @param newPath
		 * @return
		 */
		public static boolean copyFile(String oldPath, String newPath) {
			boolean ok = true;
			InputStream inStream = null;
			FileOutputStream fs = null;
			try {
				int byteread = 0;
				File oldfile = new File(oldPath);
				if (oldfile.exists()) {
					inStream = new FileInputStream(oldPath);
					fs = new FileOutputStream(newPath);
					byte[] buffer = new byte[1025*5];
					while ((byteread = inStream.read(buffer)) != -1) {
						fs.write(buffer, 0, byteread);
					}
				}
			} catch (Exception e) {
				logger.error("单个文件复制报错："+e);
				logger.error("oldPath："+oldPath);
				logger.error("newPath："+newPath);
				ok = false;
			} finally {
				try {
					if(fs != null) {
						fs.close();
					}
					if(inStream != null) {
						inStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return ok;
		}
		/**
		 * 重命名单个文件
		 * @param filePathAndName
		 * @return
		 */
		public static boolean renameFile(String oldPath, String newName) {
			boolean ok = true;
			try {
				File   file   =   new   File(oldPath); 
				file.renameTo(new  File(newName)); 
			} catch (Exception e) {
				//System.out.println("单个文件重命名操作出错 ");
				ok = false;
			}
			return ok;
		}

		/**
		 * 删除单个文件
		 * @param filePathAndName
		 * @return
		 */
		public static boolean delFile(String filePathAndName) {
			boolean ok = true;
			try {
				String filePath = filePathAndName;
				filePath = filePath.toString();
				java.io.File myDelFile = new java.io.File(filePath);
				myDelFile.delete();

			} catch (Exception e) {
				logger.error("删除单个文件报错："+e);
				logger.error("filePathAndName："+filePathAndName);
				ok = false;
			}
			return ok;
		}
		/**
		 * 
		 * @param bytes
		 * @param filepath
		 * @return
		 * @throws Exception
		 */
		
		  public static boolean writeBytestoFile(byte[] bytes, String filepath)throws Exception {
				boolean ok = true;
				FileOutputStream fos = null;
				try {
					File f = new File(filepath);
					if (!f.getParentFile().exists()) {
						f.getParentFile().mkdirs();
					}
					fos = new FileOutputStream(f);
					fos.write(bytes);
					fos.close();
				}
				catch (IOException e) {
					ok = false;
				}finally{
					if (fos != null)
						fos.close();
				}
				return ok;
			}
		  
		  /**  
		     * 判断ip是否在指定网段中  
		     * @author dh  
		     * @param iparea  
		     * @param ip  
		     * @return boolean  
		     */  
		    public static boolean ipIsInNet(String iparea, String ip) {   
		        if (iparea == null)   
		            throw new NullPointerException("IP段不能为空！");   
		        if (ip == null)   
		            throw new NullPointerException("IP不能为空！");   
		        iparea = iparea.trim();   
		        ip = ip.trim();   
		        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";   
		        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;   
		        if (!iparea.matches(REGX_IPB) || !ip.matches(REGX_IP))   
		            return false;   
		        int idx = iparea.indexOf('-');   
		        String[] sips = iparea.substring(0, idx).split("\\.");   
		        String[] sipe = iparea.substring(idx + 1).split("\\.");   
		        String[] sipt = ip.split("\\.");   
		        long ips = 0L, ipe = 0L, ipt = 0L;   
		        for (int i = 0; i < 4; ++i) {   
		            ips = ips << 8 | Integer.parseInt(sips[i]);   
		            ipe = ipe << 8 | Integer.parseInt(sipe[i]);   
		            ipt = ipt << 8 | Integer.parseInt(sipt[i]);   
		        }   
		        if (ips > ipe) {   
		            long t = ips;   
		            ips = ipe;   
		            ipe = t;   
		        }   
		        return ips <= ipt && ipt <= ipe;   
		    }
		    
	    /**
	     * 获取服务器请求Ip
	     * @throws UnknownHostException 
	     * */
	    public static String getServerIp() {
	    	InetAddress address = null;
			try {
				address = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
	    	return address.getHostAddress();
	    }
	    // 读取文件
	    public static String readFile(String path) throws FileNotFoundException,IOException {
			  File file=new File(path);
			  StringBuffer sb = new StringBuffer();
//			  FileReader fl = new FileReader(file);
			  FileInputStream in= new FileInputStream(file);
			  
			  BufferedReader bf = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			  String context = null;
			  do {
			   context = bf.readLine();
			   if (context == null) {
			    break;
			   } else if (!context.trim().equals("")) {
				   context = context.replaceAll("<br>", "\n");
				  // context = context.replaceAll("\"", "\\\"");
			    sb.append(context + " ");
			   }
			  } while (context != null);
			  	bf.close();
			  	return sb.toString();
			 }   
		public static String  getXMLName(String type,final String dateStr,String path){
			File file = new File(path+type);
			final Long prefix = Long.parseLong(dateStr);
			System.out.println(prefix);
			System.out.println(path+type);
			String[] fns = file.list(new FilenameFilter() {    
	            public boolean accept(File dir, String name) {    
	                if (!name.equals("monitor.xml")&&Long.parseLong(name.split("_")[1].substring(0, 14))<prefix) {    
	                    return true;    
	                 } else {    
	                    return false;    
	                 }    
	             }    
			}); 
			for(String str:fns){
				System.out.println(str);
			}
			if(fns.length>0){
				return fns[fns.length-1];
			}else{
				return "0";
			}
			
		}
		/**
		 * Base64 编码
		 * @param bstr
		 * @return String
		 */
		public static String encodeBase64(String bstr){
			if(bstr!=null&&!bstr.trim().equals("")){
				return new sun.misc.BASE64Encoder().encode(bstr.getBytes());
			}
			return "";
			
		}

		/**
		 * 解码
		 * @param str
		 * @return string
		 */
		public static String decodeBase64(String str){
			String result = null;
			if(str!=null&&!str.trim().equals("")){
				byte[] bt = null;
				try {
					sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
					bt = decoder.decodeBuffer( str );
					result = new String(bt,"utf-8");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		//去除空格
		public static  String replaceBlank(String str) {
	   		String dest = "";
	   		if (str!=null) {
	   			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	   			Matcher m = p.matcher(str);
	   			dest = m.replaceAll("");
	   		}
	   		return dest;
	   	}
		
		public static String getHMS(Integer duration){
			String hour="";
			if(duration/3600<10){
				hour="0"+duration/3600;
			}else{
				hour=""+duration/3600;
			}
			String minute="";
			if(duration%3600/60<10){
				minute="0"+duration%3600/60;
			}else{
				minute=""+duration%3600/60;
			}
			String second="";
			if(duration%3600%60<10){
				second="0"+duration%3600%60;
			}else{
				second=""+duration%3600%60;
			}
			String durationStr=hour+":"+minute+":"+second;
			return durationStr;
			
		}

}
