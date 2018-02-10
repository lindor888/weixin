<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>修改写字活动</title>
    <meta http-equiv="x-ua-compatible" content="ie=7" />
     <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/js/qtip/css/global.css" />
    <link rel="stylesheet" type="text/css" href="/weixin-studio/style/css/custom.css" />
    <link rel="stylesheet" type="text/css" href="/weixin-studio/style/css/public.css" />
    <script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
  	<script language="javascript" src="/weixin-studio/style/My97DatePicker/WdatePicker.js"></script>
  	<script src="/weixin-studio/style/js/qtip/jtip.js" type="text/javascript"></script>
    <script type="text/javascript" src="/weixin-studio/style/js/jquery.validate.js"></script>
    
<style>
table tr{ height: 40px; }
p{ height: 30px; padding-left: 20px; }
.h{ height: 30px}

.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(../images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }

</style>

<script>
	function getParameter(args){
		var url = window.location.href;
		var paraString = url.substring(url.indexOf('?') + 1, url.length).split('&');//截取出url?后面的字符以&的字符
		var paraObj = {};
		for (var i = 0; j = paraString[i]; i++) {
			paraObj[j.substring(0, j.indexOf('=')).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
		}
		var returnValue = paraObj[args.toLowerCase()];
		if (typeof(returnValue) == 'undefined') {
			return "";
		}
		else {
			return returnValue;
		}
	}
	
	var id = getParameter('id');
	
	function initData(){
		$.ajax({
			url : '${web.context.path}/writewords/selectByKeyWriteWordsActivity',
			type : 'post',
			async : false,
			data : {'bean.activityId':id},
			success : function(data){
				var data = data.rows;
				var activityTitle = data.activityTitle;
				var beginTime = data.beginTime;
				var lastTime = data.lastTime;
				
				$("#activityTitle").val(activityTitle);
				$("#beginTimeStr").val(beginTime.replace('T',' '));
				$("#lastTime").val(lastTime);
			}
		});
	}
	
	$(function(){
		initData();
	});
	
	function updateWriteWordsActivity(){
		var activityTitle = $("#activityTitle").val();
		if(activityTitle==''){
			alert('请输入活动名称');
			return;
		}
		var beginTimeStr = $("#beginTimeStr").val();
		var lastTime = $("#lastTime").val();
		
		$.ajax({
			url : '${web.context.path}/writewords/addOrUpdateWriteWordsActivity',
			type : 'post',
			async : false,
			data : {'bean.activityTitle':activityTitle,'bean.beginTimeStr':beginTimeStr,'bean.lastTime':lastTime,'bean.activityId':id},
			success : function(data){
				var msg = data.msg;
				if(msg=='success'){
					alert('修改成功');
					window.parent.closeColorBox(false);
				}else{
					alert('修改失败');
				}
			}
		});
	}
	
</script>
</head>
<body>
<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<strong>修改写字活动</strong>
		</div>
	</div>
<div class="h" >
</div>
	<s:form  method="post" id="writeWordsActivityFrom"   theme="simple">
	<p><i>*</i>&nbsp;<span style="padding-right: 50px;">活动名称</span><s:textfield id="activityTitle" size="30"/></p>
	<p><i>*</i>&nbsp;<span style="padding-right: 45px;">开始时间</span>
	<input type="text" id="beginTimeStr" class="text1 Wdate startDate" style="width:150px;" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
	</p>
	<p><i>*</i>&nbsp;<span style="padding-right: 25px;">持续时间(秒)</span><s:textfield id="lastTime" size="20"/></p>
	
	<div style="padding-left:200px;"><ul class="button_1" >
			<li><span onclick="updateWriteWordsActivity()">保存</span></li>
			<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
	</ul></div>
	
	</s:form>
  </body>
</html>
