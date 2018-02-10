<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>添加写字活动</title>
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
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	};
	
	$(function() {
		var nowStr = new Date().Format('yyyy-MM-dd hh:mm:ss');
		$("#beginTimeStr").val(nowStr);
	});
	
	function addWriteWordsActivity(){
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
			data : {'bean.activityTitle':activityTitle,'bean.beginTimeStr':beginTimeStr,'bean.lastTime':lastTime},
			success : function(data){
				var msg = data.msg;
				if(msg=='success'){
					alert('添加成功');
					window.parent.closeColorBox(false);
				}else{
					alert('添加失败');
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
			<strong>创建写字活动</strong>
		</div>
	</div>
<div class="h" >
</div>
	<s:form  method="post" id="writeWordsActivityFrom"   theme="simple">
	<p><i>*</i>&nbsp;<span style="padding-right: 50px;">活动名称</span><s:textfield id="activityTitle" size="30"/></p>
	<p><i>*</i>&nbsp;<span style="padding-right: 45px;">开始时间</span>
	<input type="text" id="beginTimeStr" class="text1 Wdate startDate" style="width:150px;" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
	</p>
	<p><i>*</i>&nbsp;<span style="padding-right: 25px;">持续时间(秒)</span><s:textfield id="lastTime" size="30"/></p>
	
	<div style="padding-left:200px;"><ul class="button_1" >
			<li><span onclick="addWriteWordsActivity()">保存</span></li>
			<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
	</ul></div>
	
	</s:form>
  </body>
</html>
