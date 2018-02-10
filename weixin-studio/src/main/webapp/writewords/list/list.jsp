<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>写字活动列表</title>
    <meta http-equiv="x-ua-compatible" content="ie=7" />
    <link type="text/css" rel="stylesheet" href="${web.context.path}/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="${web.context.path}/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="${web.context.path}/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="${web.context.path}/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="${web.context.path}/style/colorBox/colorbox.css" />
    <link type="text/css" rel="stylesheet" href="${web.context.path}/style/js/qtip/css/global.css" />
   <script src="${web.context.path}/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="${web.context.path}/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="${web.context.path}/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
  	<script src="${web.context.path}/style/js/qtip/jtip.js" type="text/javascript"></script>
  	<script language="javascript" src="${web.context.path}/style/My97DatePicker/WdatePicker.js"></script>
  	<script src="${web.context.path}/writewords/list/general/general.js" type="text/javascript"></script>
    <script src="${web.context.path}/writewords/list/list.js" type="text/javascript"></script>
  </head>
 
  <body>
  
<div id="" style="width:80%;padding-left: 10%; margin-top: 10px;">
	<div  style="height: 10px;"></div>
	活动名称：<input type="text" id="activityTitle" />
	活动开始时间：<input type="text" id="beginTimeStr" class="text1 Wdate startDate" style="width:150px;" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" />
	<a class='easyui-linkbutton l-btn l-btn-plain' onclick="selectByPage()" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
</div>

 <table id="dataGrid"></table>
  </body>
</html>
