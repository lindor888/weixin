<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>图文素材管理</title>
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
    <script src="/weixin-studio/js/tuwenmaterial/tuwenMaterial.js" type="text/javascript"></script>
</head>
<body>
<div id="" style="width:90%;padding-left: 10%; margin-top: 10px;">
	<div style="height: 10px;"></div>
	<form id="TextInfFrom">
		素材名称:&nbsp;&nbsp;<s:textfield  id="imageMaterialName"></s:textfield>&nbsp;&nbsp;&nbsp;
		素材状态:<s:select list="#{'':'全部','1':'启用','0':'停用'}" cssStyle="width:150px" id="zhuangtai"></s:select><br /><br />
		关联关键字:<s:textfield  id="kwordname"></s:textfield>&nbsp;&nbsp;&nbsp;
		<a class='easyui-linkbutton l-btn l-btn-plain' onclick="teQuery()" style="margin-left: 250px;"><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
	</form>
</div>
<div style="height: 20px;"></div>
<table id="tuwenMaterial"></table>
 
 </body>
</html>
