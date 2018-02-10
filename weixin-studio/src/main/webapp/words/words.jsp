<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>信息审核</title>
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
  	<script src="${web.context.path}/words/general/general.js" type="text/javascript"></script>
    <script src="${web.context.path}/words/words.js" type="text/javascript"></script>
  </head>
 
  <body>
  
<div id="" style="width:90%;padding-left: 10%; margin-top: 10px;">
	<div  style="height: 10px;"></div>
	<%-- <form id="followersInfFrom">
	</form> --%>
	<div  style="height: 20px;"></div>
</div>

 <table id="dataGrid"></table>
  </body>
</html>
