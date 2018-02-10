<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>会议日程管理</title>
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
  	<script src="/weixin-studio/interact/general/general.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/qtip/jtip.js" type="text/javascript"></script>
    <script src="/weixin-studio/location/prizelist.js" type="text/javascript"></script>
      	<script type="text/javascript" src="/weixin-studio/style/My97DatePicker/WdatePicker.js"></script>
      	<script type="text/javascript">
      
      	</script>
</head>
<body>
<div id="" style="width:90%;padding-left: 10%; margin-top: 10px;">
	<div style="height: 10px;"></div>
	<form id="">
		节目名称：&nbsp;&nbsp;<s:textfield  id="program_name" name="program_name"></s:textfield>&nbsp;&nbsp;&nbsp;
		播放日期：&nbsp;&nbsp;<s:textfield theme="simple"  id="start_time" name="start_time" maxLength="500"  class="Wdate" cssStyle="width: 182px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readonly:true,isShowClear:true})" ></s:textfield>
		<a class='easyui-linkbutton l-btn l-btn-plain' onclick="query()" style="margin-left: 250px;"><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
	</form>
</div>
<div style="height: 20px;"></div>
<table id="dataGrid"></table>

 </body>
</html>