package com.ctvit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
/**
 * ftp帮助类，提供了上传、下载、删除、创建、重命名等功能
 * @author CTVIT
 *
 */
public class FtpClientUtil {
	private FTPClient ftp;
	private Logger logger = Logger.getLogger(FtpClientUtil.class);
	/**
	 * 连接FTP服务器
	 * @param serverIP
	 * @param user
	 * @param password
	 * @return boolean
	*/
	public boolean connectFtpServer(String serverIP, String user,String password){
		ftp = new FTPClient();
		try {
			ftp.connect(serverIP);
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				logger.error("Ftp connect to " + serverIP + "error==> can not connert to :" + serverIP);
				System.out.println("Ftp connect to " + serverIP + "error==> can not connert to :" + serverIP);
				return false;
			}
			if (!ftp.login(user, password)) {
				disconnect();
				logger.error("Ftp connect to " + serverIP + "error==> the account:" + user +"/" + password +" is error");
				System.out.println("Ftp connect to " + serverIP + "error==> the account:" + user +"/" + password +" is error");
				return false;
			}
		} catch (IOException e) {
			logger.error("Ftp connect to " + serverIP + "use account:" + user +"/" + password +"error==>"+e.getMessage());
			return false;
		}
		return true;
	}

   /**
	 * 创建文件夹
	 * @param path
	 * @return boolean
	*/
	public boolean createDirectory(String path) {
		try {
			String[] dirArray = path.split("/");
			for (int i = 0; i < dirArray.length; i++) {
				if (dirArray[i] != null) {
					if (!ftp.changeWorkingDirectory(dirArray[i])) {
						ftp.makeDirectory(dirArray[i]);
						ftp.changeWorkingDirectory(dirArray[i]);
					}
				}     
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
	}
		return true;
	}

	/**
	 * 下载文件
	 * @param local(eg:/ss/22/11.txt)
	 * @param remote(eg:/dd/2s/22.txt)
	 * @return boolean
	*/
	public boolean downloadFile(String local, String remote) {
		OutputStream output = null;
		try {
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			String[] dirArray = remote.split("/");
			if (dirArray.length > 1) {
				for (int i = 0; i < dirArray.length - 1; i++) {
					if (dirArray[i] != null) {
						ftp.changeWorkingDirectory(dirArray[i]);
					}
				}
			}
			File file = new File(local);
			output = new FileOutputStream(file);
			ftp.retrieveFile(dirArray[dirArray.length - 1], output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 上传文件，文件夹不存在时创建文件夹
	 * @param local(eg:/ss/22/11.txt)
	 * @param remote(eg:/dd/2s/22.txt)
	 * @return boolean
	*/
	public boolean uploadFile(String local, String remote) throws Exception{
		InputStream input = null;
		boolean success = true;
		if (ftp != null) {
			try {
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				ftp.setControlEncoding("utf-8");
				ftp.enterLocalPassiveMode();
				ftp.cwd("/");//返回到当前用户的根目录
				String[] dirArray = remote.split("/");
				if (dirArray.length > 1) {
					for (int i = 0; i < dirArray.length - 1; i++) {
						if (dirArray[i] != null) {
							if (!ftp.changeWorkingDirectory(dirArray[i])) {//文件夹不存在时创建文件夹
								if(ftp.makeDirectory(dirArray[i])) {
									ftp.changeWorkingDirectory(dirArray[i]);
								}
							}
						}
					}
				}
				input = new FileInputStream(local);
				//System.out.println(dirArray[dirArray.length - 1]);
				ftp.storeFile(dirArray[dirArray.length - 1], input);
			} catch (IOException e) {
				e.printStackTrace();
				success = false;
				throw (new Exception("upload file:" + local + " to ftp failed!"+e.getMessage()));
			} finally {
				if (input != null) {
				try {
						input.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		} else {
			success = false;
//			System.out.println("local:"+local + "copy to remote:"+remote +" error===>ftp is null!");
			throw (new Exception("Ftp connect error!"));
		}
		
		return success;
	}

	/**
	 * 删除文件
	 * @param filePath
	 * @return boolean
	*/
	public boolean deleteFile(String filePath) {
		try {
			String[] dirArray = filePath.split("/");
			if (dirArray.length > 1) {
				for (int i = 0; i < dirArray.length - 1; i++) {
					if (dirArray[i] != null) {
						ftp.changeWorkingDirectory(dirArray[i]);
					}
				}
			}
			ftp.deleteFile(dirArray[dirArray.length - 1]);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除文件夹
	 * @param path
	 * @return boolean
	*/
	public boolean removeDirectory(String path) {
		try {
			String[] dirArray = path.split("/");
			for (int i = 0; i < dirArray.length; i++) {
				if (dirArray[i] != null) {
					ftp.changeWorkingDirectory(dirArray[i]);
				}
			}
			FTPFile[] files = ftp.listFiles();
			for (int j = 0; j < files.length; j++) {
				if (files[j].isDirectory()) { // 递归删除子文件夹
					removeDirectory(files[j].getName());
				}
				ftp.deleteFile(files[j].getName());
			}
			ftp.changeToParentDirectory();
			ftp.removeDirectory(dirArray[dirArray.length - 1]);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 重命名文件、文件夹
	 * @param from
	 * @param to
	 * @return boolean
	*/
	public boolean rename(String from, String to) {
		try {
			String[] dirArray = from.split("/");
			if (dirArray.length > 1) {
				for (int i = 0; i < dirArray.length - 1; i++) {
					if (dirArray[i] != null) {
						ftp.changeWorkingDirectory(dirArray[i]);
					}
				}
			}
			ftp.rename(dirArray[dirArray.length - 1], to);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 *  断开连接
	 */
	public void disconnect() {
		if (ftp != null && ftp.isConnected()) {
			try {
				ftp.logout();	
			} catch (IOException e) {
				System.out.println("ftp log out error==>"+e.getMessage());
			} finally {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					System.out.println("ftp disconnect error==>"+e.getMessage());
				}
			}
		}
	}
}
