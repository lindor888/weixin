package com.ctvit.util;

public class IssueUtil {
	public boolean issue(String local, String remote, String usr, String pwd) {
		boolean retValue = false;
		// String usr = "ncpa-classic.cntv.cn";
		// String pwd = "7mprpx3C6DxI361r";
		// String ftp_ip = "10.64.0.163";
		// String ftp_ip_temp = "10.64.0.11";
		// String usr = "cmstest";
		// String pwd = "cmstest@!@#$";
		String ftp_ip = "10.64.0.11";
		String ftp_ip_temp = "10.64.0.163";
		FtpClientUtil ftpcu = new FtpClientUtil();
		FtpClientUtil ftpcu_temp = new FtpClientUtil();
		try {
			if (ftpcu.connectFtpServer(ftp_ip, usr, pwd)) {
				ftpcu.uploadFile(local, remote);
			} else {
				System.out.println("connect to ftp==>" + ftp_ip + "error");
			}
			if (ftpcu_temp.connectFtpServer(ftp_ip_temp, usr, pwd)) {
				ftpcu_temp.uploadFile(local, remote);
			} else {
				System.out.println("connect to ftp==>" + ftp_ip_temp + "error");
			}
			retValue = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpcu != null) {
				ftpcu.disconnect();
			}
			if (ftpcu_temp != null) {
				ftpcu_temp.disconnect();
			}
		}
		return retValue;
	}
}
