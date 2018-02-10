<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

    <title>关键字管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
  	<script src="/weixin-studio/js/waccount/general/general.js" type="text/javascript"></script>
    <script src="/weixin-studio/js/keyword/keyword.js" type="text/javascript"></script>
  </head>
  <body>
  
<div id="" style="width:90%;padding-left: 10%; margin-top: 10px;">
	<div style="height: 10px;"></div>
	<form id="userInfFrom">
		关键字名称:<s:textfield  id="kweName"></s:textfield>&nbsp;&nbsp;&nbsp;
		关键字状态:<s:select list="#{'':'全部','1':'启用','0':'停用'}" cssStyle="width:155px" id="zhuangtai"></s:select>&nbsp;&nbsp;&nbsp;<br /><br />
		匹配规则:&nbsp;&nbsp;<s:select list="#{'':'全部','0':'模糊','1':'精确'}" cssStyle="width:155px" id="rules"></s:select>&nbsp;&nbsp;&nbsp; 
		匹配类型:&nbsp;&nbsp;<s:select list="#{'':'全部','0':'文本','1':'图文'}" cssStyle="width:155px" id="type"></s:select>&nbsp;&nbsp;&nbsp;
		<a class='easyui-linkbutton l-btn l-btn-plain' onclick="kwdQuery()" style="margin-left: 100px;" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
	</form>
	<div style="height: 20px;"></div>
</div>

<table id="keyword"></table>

  </body>
</html>
