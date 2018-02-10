package com.ctvit.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 密文
 * @author liudianhe
 * @date 2014年12月25日 上午10:03:50
 */
public class CiphertextUtil {
	
	protected static Logger log = LoggerFactory.getLogger(CiphertextUtil.class);

	/**
	 * 用MD5加密，生成32位十六进制字符串
	 * @param String
	 * @return String
	 */
	public static String encryptMD5(String s){
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成32位十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
        	log.error(e + "  ,  param:" + s);
            e.printStackTrace();
            return null;
        }
	}
	
	/**
	 * MD5解密
	 * @param String
	 * @return String
	 */
	public static String decryptMD5(String str){
		
		return null;
	}
}
