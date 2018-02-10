<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>修改互节目单</title>
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
    <script type="text/javascript" src="/weixin-studio/style/js/swfobject.js"></script>
    <script type="text/javascript" src="/weixin-studio/js/tuwenmaterial/tuwenMaterialOperate.js" ></script>
    <script type="text/javascript" src="/weixin-studio/style/js/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript" src="/weixin-studio/js/upload/upload.js"></script>
	<script language="javascript" src="/weixin-studio/style/My97DatePicker/WdatePicker.js"></script>
    
<style>
table tr{ height: 40px; }
p{ height: 30px; padding-left: 20px; }
.h{ height: 30px}

.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(../images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }

</style>

<script>
	
	function updateInteract(){
		var url = window.location.href;
		var programmeId = url.split("programme_id=")[1];
		var programme_name = $("#programme_name").val();
		if(programme_name==''){
			alert('请输入昵称');
			return;
		}else{
		
		$.ajax({
			url : '/weixin-studio/interact/xiugainameInteract',
			type : 'post',
			async : false,
			data : {'programmename.programme_name':programme_name,'programmename.programme_id':programmeId},
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
	}
	function srctool(id) {
		document.getElementById("pic" + id).src = $('#imageUrl'+id).val();
		$("#pic"+id).show();
	}
	
	function changeType(type){
		$("#type"+type).css('display','');
		$("#type"+parseInt(1-type)).css('display','none');
		
	}
	



	
	
</script>
</head>
<body>
<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<strong>添加互动信息</strong>
		</div>
	</div>
<div class="h" >
</div>
	<s:form  method="post" namespace="/interact" action="addInteract"  id="interactFrom"   theme="simple">
	
	
	<p><i>*</i>&nbsp;<span style="padding-right: 50px;">节目单名称</span><input type="text" id="programme_name" name="programme_name" value=""/></p>
	
	<div style="padding-left:500px;">
	<ul class="button_1" >
			<li><span onclick="updateInteract()">保存</span></li>
			<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
	</ul>
	</div>
	
	</s:form>
  </body>
</html>
