package com.ctvit.location.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.log4j.Logger;

import com.ctvit.util.HttpKit;

public class BaiduMapUtils {

	private static final Logger LOGGER = Logger.getLogger(BaiduMapUtils.class);

	/** 坐标转换URL */
	private static final String GEOCONV_URL = "http://api.map.baidu.com/geoconv/v1/?coords=%s&from=3&to=5&output=json&ak=%s";
	/** 车联网周边检索URL */
	private static final String LOCAL_URL = "http://api.map.baidu.com/telematics/v3/local?location=%s&keyWord=%s&output=json&ak=%s";
	/** Place区域检索URL */
	private static final String SEARCH_URL = "http://api.map.baidu.com/place/v2/search?location=%s&query=%s&page_size=%s&page_num=%s&scope=2&radius=2000&output=json&ak=%s";

	/**
	 * 坐标转换
	 * 
	 * @author heyingcheng
	 * @date 2015年8月12日 下午5:12:10
	 * @param coords
	 * @param ak
	 * @return
	 * @return String
	 */
	public static String geoconv(String coords, String ak) {
		String json = "";
		try {
			json = HttpKit.get(String.format(GEOCONV_URL, coords, ak));
			return json;
		} catch (KeyManagementException e) {
			LOGGER.error("BaiduMapUtils.geoconv.KeyManagementException", e);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("BaiduMapUtils.geoconv.NoSuchAlgorithmException", e);
		} catch (NoSuchProviderException e) {
			LOGGER.error("BaiduMapUtils.geoconv.NoSuchProviderException", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("BaiduMapUtils.geoconv.UnsupportedEncodingException", e);
		} catch (IOException e) {
			//LOGGER.error("BaiduMapUtils.geoconv.IOException", e);
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("BaiduMapUtils.geoconv.Exception", e);
		}
		return json;
	}

	/**
	 * 车联网周边检索
	 * 
	 * @author heyingcheng
	 * @date 2015年8月12日 下午5:27:06
	 * @param location
	 * @param keyWord
	 * @param ak
	 * @return
	 * @return String
	 */
	public static String local(String location, String keyWord, String ak) {
		String json = "";
		try {
			keyWord = URLEncoder.encode(keyWord, "UTF-8");
			json = HttpKit.get(String.format(LOCAL_URL, location, keyWord, ak));
			return json;
		} catch (KeyManagementException e) {
			LOGGER.error("BaiduMapUtils.local.KeyManagementException", e);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("BaiduMapUtils.local.NoSuchAlgorithmException", e);
		} catch (NoSuchProviderException e) {
			LOGGER.error("BaiduMapUtils.local.NoSuchProviderException", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("BaiduMapUtils.local.UnsupportedEncodingException", e);
		} catch (IOException e) {
			//LOGGER.error("BaiduMapUtils.local.IOException", e);
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("BaiduMapUtils.local.Exception", e);
		}
		return json;
	}

	/**
	 * Place区域检索
	 * 
	 * @author heyingcheng
	 * @date 2015年8月12日 下午5:27:06
	 * @param location
	 * @param keyWord
	 * @param ak
	 * @return
	 * @return String
	 */
	public static String search(String location, String query, int pageSize, int pageNum, String ak) {
		String json = "";
		try {
			query = URLEncoder.encode(query, "UTF-8");
			json = HttpKit.get(String.format(SEARCH_URL, location, query, pageSize, pageNum, ak));
			return json;
		} catch (KeyManagementException e) {
			LOGGER.error("BaiduMapUtils.search.KeyManagementException", e);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("BaiduMapUtils.search.NoSuchAlgorithmException", e);
		} catch (NoSuchProviderException e) {
			LOGGER.error("BaiduMapUtils.search.NoSuchProviderException", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("BaiduMapUtils.search.UnsupportedEncodingException", e);
		} catch (IOException e) {
			LOGGER.error("BaiduMapUtils.search.IOException", e);
		} catch (Exception e) {
			LOGGER.error("BaiduMapUtils.search.Exception", e);
		}
		return json;
	}

	/** 
     * 坐标转换，腾讯地图转换成百度地图坐标 
     * @param lat 腾讯纬度 
     * @param lon 腾讯经度 
     * @return 返回结果：经度,纬度 
     */  
    public static String map_tx2bd(double lat, double lon){  
        double bd_lat;  
        double bd_lon;  
        double x_pi=3.14159265358979324;  
        double x = lon, y = lat;  
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);  
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);  
        bd_lon = z * Math.cos(theta) + 0.0065;  
        bd_lat = z * Math.sin(theta) + 0.006;  
          
        System.out.println("bd_lat:"+bd_lat);  
        System.out.println("bd_lon:"+bd_lon);  
        
        String json =" {"+
                "     \"status\":\"0\"," +
				"     \"result\":["+
				"    {	"+
				"          \"x\":" + bd_lat + ","+
				"          \"y\":" + bd_lon + ","+
				"     }]"+
				" }";

       // return bd_lon+","+bd_lat;  
        return json;
    }  
}
