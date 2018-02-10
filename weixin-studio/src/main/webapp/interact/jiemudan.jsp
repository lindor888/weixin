<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>节目单</title>
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
    <script type="text/javascript">
    //生成32位随机字符串
    function randomString(len) {
		en = len || 32;
		var $chars = 'ABC1DEFGH2IJK3LMNOQP4RSTU5VWXYZab6cdef8ghij7kmlnopq9rest0uvwxyz';
		var maxPos = $chars.length;
		var pwd = '';
		for (i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
		}
		return pwd;
		}
    
    
    
    
    function saveInteract(){
    
        var name = document.getElementById("name").value;
        if(name != ""){
		$.ajax({
			url : '/weixin-studio/interact/savejiemudanInteract',
			type : 'post',
			async : false,
			data : {'name':name,'programmeId':randomString(32)},
			success : function(data){
				var msg = data.msg;
				if(msg=="success"){
					alert('保存成功');
					window.parent.closeColorBox(false);
				}else{
					alert('保存失败');
				}
			}
		});
		}else{
		     alert("名称不能为空");
		}
		}
    </script>
<style>
table tr{ height: 40px; }
p{ height: 30px; padding-left: 20px; }
.h{ height: 30px}

.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(../images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }

</style>


</head>
<body>
<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<strong>生成节目单</strong>
		</div>
	</div>

<div class="h" >
</div>
	<s:form  method="post"  action=''  id="jiemudan"  theme="simple">
		<s:hidden id="openId"></s:hidden>

	<p>&nbsp;<span style="padding-right:30px;">节目单名称</span><s:textfield id="name" name="name" size="20"/></p>
	
	<p style="height:20px"></p>
	
	<div style="padding-left:500px;margin-bottom:5px;"><ul class="button_1" >
			<li><span onclick="saveInteract()">保存</span></li>
			<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
	</ul></div>
	
	</s:form>
  </body>
</html>
