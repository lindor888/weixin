<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>获奖名单</title>
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
  	<script src="/weixin-studio/interact/general/general.js" type="text/javascript"></script>
    <script src="/weixin-studio/location/prizeusers.js" type="text/javascript"></script>
    <link type="text/css" href="/weixin-studio/js/question/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
<link type="text/css" href="/weixin-studio/js/question/css/jquery-ui-timepicker-addon.css" rel="stylesheet" />

<script	type="text/javascript" src="/weixin-studio/js/question/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/weixin-studio/js/question/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="/weixin-studio/js/question/js/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript">
    var openId = "";
   function weixin(name){
		openId = name;
		
		$("#dialog").dialog({
		modal:true//模式对话框，屏蔽
		});
	}
	function fasong(){
	var content = $("#opt").val().trim();
	if(content==""){
	alert("内容不能为空！");
	return;
	}
	$.ajax({   
		       url:'/weixin-studio/follower/fasongFollower',
		        type: 'POST',   
		        async: false,
		        data:  {'openId':openId,'content':content},
		       dataType: 'json',  
		       success:function(data){ 
		    	   if(data.msg == "success"){
		    	   alert("发送成功！");
		    	   }
		    	    
		        }   
		});
}
    </script>
  </head>
 
  <body onkeydown="xKeyEvent(event)">
  
<div id="" style="width:90%;padding-left: 10%; margin-top: 10px;">
	<%-- <div  style="height: 10px;"></div>
	审核状态：<select id="flag" style="width:100px;">
			<option value="">全部</option>
			<option value="0">未处理</option>
			<option value="1">审核通过</option>
			<option value="2">审核未通过</option>
		</select>&nbsp;&nbsp;&nbsp;
	昵称：<input type="text" id="nickname" />
	<a class='easyui-linkbutton l-btn l-btn-plain' onclick="selectByPage()" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
	<a class='easyui-linkbutton l-btn l-btn-plain' onclick="shuaxin()" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>刷新</span></a>
	<br />
	<div  style="height: 5px;"></div>
	消息推送：
	<a class='easyui-linkbutton l-btn l-btn-plain' onclick="tuisong()" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>文本消息推送</span></a>
	<a class='easyui-linkbutton l-btn l-btn-plain' onclick="tuwentuisong()" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>图文消息推送</span></a>
	<br /> --%>
</div>

 <table id="dataGrid"></table>
 <div id="dialog" title="消息发送" style="width:900px;display:none">
<p>
     <form id ="weixin" action="" method="post">
  内容：<textarea type="text" name="opt" value="" id = "opt"></textarea>
  <br /><br />
  <input type="button" onclick = "fasong()" value="发送" style="float:right"/>
 </form>
  </p>
</div>
  </body>
</html>
