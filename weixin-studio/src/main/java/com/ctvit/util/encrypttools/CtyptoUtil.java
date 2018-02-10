package com.ctvit.util.encrypttools;



import java.net.InetAddress;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;

/**
 有密钥协议的加密工具
 */
public class CtyptoUtil {
	static int MD5LEN = 16;

	static int MD5MAXSIZE = 1024;

	/**
	 @roseuid 492E45C902EE
	 */
	public CtyptoUtil() {

	}

	public static String EncryptString(String Source, String Secret)
		{
		try{
			int i = 0, j;
			byte[] Hv = new byte[MD5LEN];
			byte[] Vector = new byte[MD5LEN];
			byte[] Secretb = Secret.getBytes();//密码转换成byte型；
			byte[] Sourcetb = Source.getBytes();
			byte[] dest = new byte[Source.length()];
			byte[] password = new byte[Secret.length() + Vector.length];
			do {
				for (int m = 0; m < Secret.length(); m++)
					password[m] = Secretb[m];
				for (int m = 0; m < Vector.length; m++)
					password[Secret.length() + m] = Vector[m];
				Hv = CtyptoUtil.md5Digest(password);
				for (j = 0; j < MD5LEN; j++)
					if ((i + j) < Source.length()) {
						dest[i + j] = new Integer(Sourcetb[i + j] ^ Hv[j]).byteValue();
					}else
						break;
				i += MD5LEN;
				if (i < Source.length()) {
					Vector = CtyptoUtil.Clear(Vector);
					if ((Source.length() - i + 1) < MD5LEN)
					{
						for (int m = 0; m < Source.length() - i + 1; m++)
							Vector[m] = dest[i - MD5LEN + m];
					}else {
						for (int m = 0; m < MD5LEN; m++)
							Vector[m] = dest[i - MD5LEN + m];
					}
				}
			} while (i <= Source.length());
			return CtyptoUtil.byte2hex(dest);
		}catch(Exception e){
		
		}
		return Secret;
	}

	public static byte[] md5Digest(byte[] password)
			  {
		try{
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update(password);
			byte[] digest = alg.digest();
			return digest;
		}catch(Exception e){
			
		}
		return password;
	}

	public static String byte2hex(byte[] bytes)  {
		try{
			String hs = "";
			String stmp = "";
			for (int i = 0; i < bytes.length; i++) {
				stmp = (java.lang.Integer.toHexString(bytes[i] & 0XFF));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
			}
			return hs.toUpperCase();
		}catch(Exception e){
			
		}
		return null;
	}

	public static byte[] Clear(byte[] ss)  {	
			for (int m = 0; m < ss.length; m++) {
				ss[m] = new Integer(0).byteValue();
			}
			return ss;		
	}
	
	public static byte[] HexToBuf(String auth) {
		int Leth = auth.length() / 2;
		byte[] HexByte = new byte[Leth];
		for (int a = 0; a < Leth; a++) {
			HexByte[a] = (byte) Integer.parseInt(auth.substring(a * 2,
					a * 2 + 2).toLowerCase(), 16);
		}
		return HexByte;
		}
		
	public static String DecryptString(String Cryptograph, String Secret)
											{
		int i = 0, j;
		byte[] Hv = new byte[MD5LEN];
		byte[] Vector = new byte[MD5LEN];
		byte[] Secretb = Secret.getBytes();
		byte[] Source = CtyptoUtil.HexToBuf(Cryptograph);
		byte[] dest = new byte[Source.length];
		byte[] password = new byte[Secret.length() + Vector.length];
		do {
		
			for (int m = 0; m < Secret.length(); m++)
				password[m] = Secretb[m];
		
			for (int m = 0; m < Vector.length; m++)
				password[Secret.length() + m] = Vector[m];
		
			Hv = CtyptoUtil.md5Digest(password);
			for (j = 0; j < MD5LEN; j++)
				if ((i + j) < Source.length)
					dest[i + j] = new Integer(Source[i + j] ^ Hv[j])
							.byteValue();
				else
					break;
			i += MD5LEN;
			if (i < Source.length) {
				Vector = CtyptoUtil.Clear(Vector);
				if ((Source.length - i + 1) < MD5LEN) {
					for (int m = 0; m < Source.length - i + 1; m++)
						Vector[m] = Source[i - MD5LEN + m];
				}
				else {
					for (int m = 0; m < MD5LEN; m++)
						Vector[m] = Source[i - MD5LEN + m];
				}
			}
		} while (i <= Source.length);
		return new String(dest);
	}
	
	
	/**
	 * 获取IP地址
	 * 
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request1){
		// TODO 获取ip地址
		String ipAddress = null;
		// ipAddress = this.getRequest().getRemoteAddr();
		//HttpServletRequest request1 = ServletActionContext.getRequest();
		ipAddress = request1.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request1.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request1.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request1.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	public static void main(String[] args) {
		String pwd = "7C0C6C4AC175FEF292680E3B4F8141EB";
		String md5 = "cntvUserPassWord";
		String ss = CtyptoUtil.DecryptString(pwd, md5);
		System.out.println(ss);
	}
}


