<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>写字活动</title>
    <meta http-equiv="x-ua-compatible" content="ie=7" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/js/qtip/css/global.css" />
   <script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/qtip/jtip.js" type="text/javascript"></script>
  	<script src="/weixin-studio/writewords/activity/general/general.js" type="text/javascript"></script>
    <script src="/weixin-studio/writewords/activity/activity.js" type="text/javascript"></script>
  </head>
 
  <body>
  
<div id="" style="width:80%;padding-left: 10%; margin-top: 10px;">
	<div  style="height: 10px;"></div>
	状态：<select id="flag" style="width:100px;">
			<option value="">全部</option>
			<option value="0">未发布</option>
			<option value="1">已发布</option>
		</select>&nbsp;&nbsp;&nbsp;
	活动名称：<input type="text" id="activityTitle" />
	<a class='easyui-linkbutton l-btn l-btn-plain' onclick="selectByPage()" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
</div>

 <table id="dataGrid"></table>
  </body>
</html>
