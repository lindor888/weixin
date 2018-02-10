package com.ctvit.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class GetHttpUtil {
	
	public static String get(String url, Map<String ,String> parameters) {
		URL _url = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		HttpURLConnection connection = null;
		try {
			_url = new URL(url + "?" + formatFormParameters(parameters));
			connection = (HttpURLConnection) _url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(connection
					.getInputStream(), "UTF-8"));
			for (String str = br.readLine(); str != null; str = br.readLine()) {
				sb.append(str);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				} finally {
					if (connection != null) {
						connection.disconnect();
					}
				}
			}
		}
		return sb.toString();
	}
	public static String getNoInput(String url) {
		URL _url = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		HttpURLConnection connection = null;
		try {
			_url = new URL(url);
			connection = (HttpURLConnection) _url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(connection
					.getInputStream(), "UTF-8"));
			for (String str = br.readLine(); str != null; str = br.readLine()) {
				sb.append(str);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				} finally {
					if (connection != null) {
						connection.disconnect();
					}
				}
			}
		}
		return sb.toString();
	}

	public static String formatFormParameters(Map<String,String> map) {
		if (map == null) {
			throw new IllegalArgumentException("args is null!");
		}
		StringBuffer sb = new StringBuffer();
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			sb.append(entry.getKey()).append("=")
					.append(
							entry.getValue() != null ? entry.getValue()
									.toString() : "");
			if (iter.hasNext()) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

}
