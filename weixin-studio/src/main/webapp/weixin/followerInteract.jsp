<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>推送消息</title>
    <meta http-equiv="x-ua-compatible" content="ie=7" />
     <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/js/qtip/css/global.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/public.css" />
    <script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
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
	var type = getParameter('type');
	
	$(function(){
		if(type=="1"){
			$.ajax({
				url : '/weixin-studio/interact/selectByKeyInteract',
				type : 'post',
				async : false,
				data : {'bean.id':id},
				success : function(data){
					var data = data.rows;
					var huifu = data.huifu;
					$("#huifu").val(huifu);
					$("#huifu").attr('disabled','disabled');
					$(".button_1 li").eq(0).remove();
				}
			});
			
			
		}
	});
	
	
	function tuisongInteract(){
		var huifu = $("#huifu").val();
		if(huifu==''){
			alert('请填写内容!');
			return;
		}
		
		$.ajax({
			url : '/weixin-studio/follower/tuiFollower',
			type : 'post',
			async : false,
			data : {'content':huifu},
			success : function(data){
				var msg = data.msg;
				if(msg=='success'){
					alert('推送成功');
					window.parent.closeColorBox(false);
				}else{
					alert('推送失败');
				}
			}
		});
	}
	
	function quxiao(){
	history.go(-1);
}
</script>
</head>
<body>
<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<strong>推送消息</strong>
		</div>
	</div>
<div class="h" >
</div>
	<s:form  method="post" namespace="/interact" action="huifuInteract"  id="interactFrom"   theme="simple">
	<p>
		&nbsp;<span style="padding-right: 60px;">内容:</span>
		<s:textarea id="huifu" name="bean.huifu" rows="3" cols="70"></s:textarea>
	</p>
	<div style="height:60px"></div>
	<div style="padding-left:250px;">
		<ul class="button_1" >
				<li><span onclick="tuisongInteract()">确定</span></li>
				<li><span onclick="quxiao();">取消</span></li>
		</ul>
	</div>
	
	</s:form>
  </body>
</html>
