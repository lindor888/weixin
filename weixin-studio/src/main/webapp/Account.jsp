<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>用户管理</title>
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
    <script src="/weixin-studio/js/user/user.js" type="text/javascript"></script>


  </head>
 
  <body>
  
<div id="" style="width:90%;padding-left: 10%; margin-top: 10px;">
	<div  style="height: 10px;"></div>
	<form id="userInfFrom">
		用户名：&nbsp;&nbsp;&nbsp;<input name="queryData.login" style="width:155px"/>&nbsp;&nbsp;&nbsp;
		真实姓名：<input name="queryData.username"/>&nbsp;&nbsp;&nbsp;
		部门：
		<select name="queryData.department" style="width:155px">
			<option value="0">全部</option>
			<c:forEach items="${departlist}" var="depart">
				<option value="${depart.departId }">${depart.departName}</option>
			</c:forEach>
		</select>
		<br/><br/>
		所属角色： 
		<select name="queryData.roleId" id="roleId" style="width:155px">
			<option value="">全部</option>
			<c:forEach items="${rolelist}" var="role">
				<option value="${role.roleId }">${role.roleName}</option>
			</c:forEach>
		</select>
		&nbsp;&nbsp;
		状态：&nbsp;&nbsp;&nbsp;
		<select name="queryData.type" style="width:155px">
			<option value="100">全部</option>
			<option value="0" selected="selected">正常</option>
			<option value="1">停用</option>
		</select>
		&nbsp;&nbsp;	
		<a class='easyui-linkbutton l-btn l-btn-plain' onclick="selectUserByPage()" style="margin-left: 100px;" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
	
	</form>
	<div  style="height: 20px;"></div>
</div>

 <table id="dataGrid"></table>
   
  </body>
</html>
