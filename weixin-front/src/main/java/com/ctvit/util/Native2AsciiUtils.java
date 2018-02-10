package com.ctvit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author
 * 
 */
public class Native2AsciiUtils {

	private static String PREFIX = "\\u";
	
	public static String encode(String str){
		return native2Ascii(decode(str));
	}

	private static String native2Ascii(String str) {
		char[] chars = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			sb.append(char2Ascii(chars[i]));
		}
		return sb.toString();
	}

	private static String char2Ascii(char c) {
		if (c > 255) {
			StringBuilder sb = new StringBuilder();
			sb.append(PREFIX);
			int code = (c >> 8);
			String tmp = Integer.toHexString(code);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			code = (c & 0xFF);
			tmp = Integer.toHexString(code);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			return sb.toString();
		} else {
			String sp = Character.toString(c); //modify hgh 对 34 "进行转义
			//if( c==34 ) sp = "\\"+sp;
			return sp;
		}
	}
	
	private static String decode(String input) {
		input=	input.replace("\"", "\\\"");
		input = input.replace("&#34;", "\\&#34;");//10数据的阿语英文双引号
		String regex = "&#([0-9]*);";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb,
					(char) (Integer.parseInt(matcher.group(1))) + "");
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
}
