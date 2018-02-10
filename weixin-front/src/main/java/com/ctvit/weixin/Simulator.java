package com.ctvit.weixin;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class Simulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       String url="http://115.182.35.49/weixinopen/receiveMessage/receiveMessage.jsp";

		String responeJsonStr = "<xml><ToUserName><![CDATA[111111111]]></ToUserName>"+
 "<FromUserName><![CDATA[222222222]]></FromUserName>"+ 
 "<CreateTime>1348831860</CreateTime>"+ 
 " <MsgType><![CDATA[text]]></MsgType>"+ 
 "<Content><![CDATA[4]]></Content>"+ 
 "<MsgId>1234567890123456</MsgId></xml>";
	HttpClient client = new HttpClient(); 
	PostMethod post = new PostMethod(url);
	post.setRequestBody(responeJsonStr);
	post.getParams().setContentCharset("utf-8");
	//发送http请求
	String respStr = "";
	try {
		client.executeMethod(post);
		BufferedInputStream in = new BufferedInputStream( post.getResponseBodyAsStream());
		java.io.BufferedReader reader = new BufferedReader( new InputStreamReader(in));
//		System.out.println(""+reader.readLine());
		int line=0;
		while( (respStr=reader.readLine())!=null){
			line++;
//			System.out.println("line["+line+"]=["+respStr+"]");
			System.out.println(respStr);
		}
		reader.close();
//		respStr=post.getResponseBodyAsString();
	} catch (HttpException e) {
		e.printStackTrace(System.out);
	} catch (IOException e) {
		e.printStackTrace(System.out);
	}
//	System.out.println(responeJsonStr);
//	System.out.println(respStr);	
	}

}
