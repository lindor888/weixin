/**
 * 
 */
package com.ctvit.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;


/**
 * @作者 lizidong
 * @日期 2014.05.21
 */
public class ReadProperties {
	private static Logger logger = Logger.getLogger(ReadProperties.class);
	private static ReadProperties instance = new ReadProperties();
	private static Map<String,Properties> map=new HashMap<String,Properties>();
	//private static Properties pros = null;

	ReadProperties() {
	}

	/**
	 * 根据文件名读取属性文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return Properties对象
	 * @throws IOException
	 */

	public Properties  readProperties(String fileName) throws IOException{
		
		if(map.get(fileName)==null){
			Properties pros = new Properties();
	logger.debug("读取配置文件："+fileName);
		pros= new Properties();
		InputStream systemResourceAsStream = ReadProperties.class.getResourceAsStream("/"+fileName);
		pros.load(systemResourceAsStream);

		map.put(fileName, pros);
		}
		return map.get(fileName);
	}

	/**
	 * 获取ReadProperties实例对象
	 * 
	 * @return
	 */
	public static ReadProperties getInstance() {

		return instance;
	}
}
