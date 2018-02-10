<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
<title>图片上传</title>
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
  	<link type="text/css" rel="stylesheet"  href="/weixin-studio/style/css/uploadify.css"/>
  	<script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
 	<script src="/weixin-studio/style/js/jquery-ui.min.js" type="text/javascript"></script>
  	<script type="text/javascript"  src="/weixin-studio/style/js/swfobject.js"></script>
  	<script type="text/javascript"  src="/weixin-studio/style/js/switch.js"></script>
    <script type="text/javascript"  src="/weixin-studio/style/js/jquery.uploadify.v2.1.4.min.js"></script>
   	<script type="text/javascript" src="/weixin-studio/js/upload/upload.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function() { 
	upload('#fileInput','/weixin-studio/upload/uploadUploadAction','');
});
</script>
<body>
	<div align="center">
		<p> 
	  		<input type="file" name="fileInput" id="fileInput" />  
		</p>
		<p>
			<a class="easyui-linkbutton" href="javascript:$('#fileInput').uploadifyUpload();">上传</a>
			<a href="javascript:$('#fileInput').uploadifyClearQueue();" class="easyui-linkbutton" >取消</a>
		</p>
	</div>
	

</body>
</html>
