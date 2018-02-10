package com.ctvit.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class InterfaceManagementClient {
	
	private static final Logger logger = LogManager.getLogger(InterfaceManagementClient.class);
	
	
	public String executeHttpPost() throws Exception {
		BufferedReader in = null;
		try {
			// 定义HttpClient
			HttpClient client = new DefaultHttpClient();
					 
			// 实例化HTTP方法
			HttpPost request = new HttpPost("****");
			
			// 创建名/值组列表
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("***", "***"));
			parameters.add(new BasicNameValuePair("***", "***"));
			

			// 创建UrlEncodedFormEntity对象
			UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(
					parameters);
			request.setEntity(formEntiry);
			// 执行请求
			HttpResponse response = client.execute(request);

			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			String result = sb.toString();
			return result;
	 
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String httpClientPostStream(String data, String path) throws Exception {
		HttpClient httpClient = new DefaultHttpClient(); 
		
		X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager 
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            public X509Certificate[] getAcceptedIssuers() { return null; } 
        };
		
		String respStr = "";
		HttpPost  httppost = new HttpPost (path);
		
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("xml", "data"));
		
		
		UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(
				parameters);
		httppost.setEntity(formEntiry);
		
		try {
            SSLContext ctx = SSLContext.getInstance("TLS"); 
             
            //使用TrustManager来初始化该上下
            ctx.init(null, new TrustManager[]{xtm}, null); 
             
            //创建SSLSocketFactory 
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx); 
             
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上 
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443)); 
			
            // 发送请求  
            HttpResponse httpresponse = httpClient.execute(httppost);  
            // 获取返回数据  
            HttpEntity entity = httpresponse.getEntity();  
            respStr = EntityUtils.toString(entity);  
            if (entity != null) {  
                entity.consumeContent();  
            }  
//			respStr=post.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} 
		return respStr;	
	}
	
	public static String httpClientGetStream(String data, String path) throws Exception {
		HttpClient client = new DefaultHttpClient(); 
		
		//HttpClient httpClient = new DefaultHttpClient(); 
        X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager 
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            public X509Certificate[] getAcceptedIssuers() { return null; } 
        };
		
		String respStr = "";
		 HttpGet  get = new HttpGet (path);
		try {
			
            SSLContext ctx = SSLContext.getInstance("TLS"); 
           
            ctx.init(null, new TrustManager[]{xtm}, null); 
             
            //创建SSLSocketFactory 
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx); 
             
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上 
            client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443)); 
			
			HttpResponse httpresponse = client.execute(get);  
			HttpEntity entity = httpresponse.getEntity();  
			respStr = EntityUtils.toString(entity);  
            if (entity != null) {  
                entity.consumeContent();  
            }  
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} 
		return respStr;	
	}
}
