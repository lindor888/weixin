<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

    <title>素材管理</title>
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
  	 <script src="/weixin-studio/js/keyword/keywordOperate.js" type="text/javascript"></script>
  	
    <script src="/weixin-studio/js/sucai/sucai.js" type="text/javascript"></script>
</head>
<body>
	<s:hidden name="material" id="material"> </s:hidden>
		<center>
		<s:textfield id="sucaiTitle"></s:textfield><a class='easyui-linkbutton l-btn l-btn-plain' onclick="query()"><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
		<a class='easyui-linkbutton l-btn l-btn-plain' onclick="sucaiEvent()"><span class='l-btn-text icon-ok' style='padding-left: 20px;'>插入</span></a>
			<table id="sucai"></table>
		</center>
	
</body>
</html>
