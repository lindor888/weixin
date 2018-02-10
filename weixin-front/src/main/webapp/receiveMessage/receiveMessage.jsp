<%@page import="com.ctvit.weixin.WeixinHandler"%><%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%
	WeixinHandler handler=new WeixinHandler(request,response);
	handler.valid();%>
