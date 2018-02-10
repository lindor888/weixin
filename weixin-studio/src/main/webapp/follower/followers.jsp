<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>关注者列表</title>
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
  	<script src="/weixin-studio/js/user/general/general.js" type="text/javascript"></script>
    <script src="/weixin-studio/js/user/followers.js" type="text/javascript"></script>

  <script language="JavaScript">
  //页面的查询按钮 
	function selectFollowersByPage(){
		//alert("search");
		searchForm('followersInfFrom','dataGrid','/weixin-studio/follower/selectAllFollower');
	}
  </script>
  </head>
 
  <body>
  
<div id="" style="width:90%;padding-left: 10%; margin-top: 10px;">
	<div  style="height: 10px;"></div>
	<form id="followersInfFrom">
		昵称：&nbsp;&nbsp;&nbsp;<input name="queryData.nickname" style="width:155px"/>&nbsp;&nbsp;&nbsp;
		城市：<input name="queryData.province"/>&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;
	 	性别：&nbsp;&nbsp;&nbsp;
		<select name="queryData.sex" style="width:155px">
			<option value="100" selected="selected">全部</option>
			<option value="0" >男</option>
			<option value="1">女</option>
		</select> 
		&nbsp;&nbsp;	
		<a class='easyui-linkbutton l-btn l-btn-plain' onclick="selectFollowersByPage()" style="margin-left: 100px;" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
	
	</form>
	<div  style="height: 20px;"></div>
</div>

 <table id="dataGrid"></table>
   
  </body>
</html>
