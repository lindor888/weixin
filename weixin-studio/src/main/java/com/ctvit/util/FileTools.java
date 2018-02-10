package com.ctvit.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTools {
	public FileTools() {

	}

	/**
	 * 
	 */
	private void copyStream(InputStream is, OutputStream os) {
		try {
			byte[] buffer = new byte[10240];
			int size = 0;

			while ((size = is.read(buffer)) != -1) {
				os.write(buffer, 0, size);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException ex) {
				// nothing to do
			}
			try {
				os.close();
			} catch (IOException ex) {
				// nothing to do
			}
		}

	}

	/**
	 * 拷贝文件
	 * 
	 * @param srcName
	 * @param destName
	 * @return
	 */
	public boolean copyFile(String srcName, String destName) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(srcName));
			out = new BufferedOutputStream(new FileOutputStream(destName));
			int i = 0;
			byte[] buffer = new byte[2048];
			while ((i = in.read(buffer)) != -1) {
				out.write(buffer, 0, i);
			}
			out.close();
			in.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					
				}
			}
		}

	}

	/** */
	/**
	 * 删除文件
	 * 
	 * @param path
	 *            目录
	 * @param filename
	 *            文件名
	 */
	public boolean deleteFile(String path, String filename) {
		File file = new File(path + File.separator + filename);
		if (file.exists() && file.isFile()) {
			file.delete();
			return true;
		}
		return false;
	}

	public boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteDir(String dir) {
		//如果dir不以文件分隔符结尾，自动添加文件分隔符     
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		//如果dir对应的文件不存在，或者不是一个目录，则退出     
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		
		//删除当前目录     
		if (dirFile.delete()) {
			//System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			//System.out.println("删除目录" + dir + "失败！");
			return false;
		}
	}

	/**
	 * 移动文件夹
	 * @param oldfile
	 * @param newFile
	 * @return
	 */
	public boolean move(File oldfile, File newFile) {
		if (!newFile.getParentFile().exists()) {
			newFile.getParentFile().mkdir();
		}
		boolean success = oldfile.renameTo(newFile);
		if (!success) {
			return false;
		} else {
			return true;
		}
	}

	public boolean move(String oldfile, String newfile) {
		File of = new File(oldfile);
		File nf = new File(newfile);
		return move(of, nf);
	}

	/**
	 * 创建目录
	 * 
	 * @param dir
	 */
	public void createDir(String dir) {
		File dirtemp = new File(dir);
		if (dirtemp.exists()) {
			if (dirtemp.isDirectory()) {
			} else if (dirtemp.isFile()) {
			}
		} else {
			try {
				dirtemp.mkdirs();
			} catch (Exception ef) {
			}
		}
	}

	/**
	 * 检查文件是否存在
	 * 
	 * @param path
	 * @return
	 */
	public boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 检查文件夹是否存在
	 * 
	 * @param path
	 * @return
	 */
	public boolean checkdir(String path) {
		File file = new File(path);
		if (!file.isDirectory()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 创建一个规定格式的字符串
	 * 
	 * @param format
	 * @return
	 */
	public String createDateString(String format) {
		SimpleDateFormat date = new SimpleDateFormat(format);
		Date now = new java.util.Date();
		return date.format(now);
	}
}
