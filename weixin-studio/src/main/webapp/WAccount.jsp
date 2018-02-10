<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>微信公共平台账号</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="x-ua-compatible" content="ie=7" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />

   <script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/qtip/jtip.js" type="text/javascript"></script>
  	<script src="/weixin-studio/js/waccount/general/general.js" type="text/javascript"></script>
    <script src="/weixin-studio/js/waccount/waccount.js" type="text/javascript"></script>

  </head>
  
  <body>

  <div style="margin-top: 10px; padding-left: 110px;" >
  	<div style="height: 10px;"></div>
	<form id="userInfFrom">
  	账号名称：<input id="username" name="queryData.username"/>&nbsp;&nbsp;&nbsp;  	&nbsp;&nbsp;&nbsp;
	账号状态：<select name="queryData.state" style="width:100px;">
		<option value="100">全部</option>
		<option value="0" selected="selected">正常</option>
		<option value="1">停用</option>
		</select>&nbsp;&nbsp;&nbsp;
	<br/><br/>
	应急状态：<select name="queryData.type" style="width:100px;">
		<option value="100">全部</option>
		<option value="0">正常</option>
		<option value="1">应急</option>
		</select>&nbsp;&nbsp;&nbsp;
		
		<a class='easyui-linkbutton l-btn l-btn-plain' onclick="selectUserByPage()" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
	</form>
  </div>
  <div  style="height: 20px;"></div>
  <table id="dataGrid"></table>
   
  </body>
</html>
