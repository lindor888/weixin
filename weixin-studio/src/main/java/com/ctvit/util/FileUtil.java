package com.ctvit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
	public static String getFilePath(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sdf.format(new Date());
		String retPath = now.substring(0,4) + "/";
		retPath += now.substring(4, 6) + "/";
		retPath += now.substring(6, 8) + "/";
    	return retPath;
    }
}
