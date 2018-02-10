<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>挑选角色</title>
	 <meta http-equiv="x-ua-compatible" content="ie=7" />
     <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	 <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
     <link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
     <link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
     <script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	 <script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	 <script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
     <script type="text/javascript" src="/weixin-studio/style/js/jquery.validate.js"></script>
     <script src="/weixin-studio/js/fenpei.js" type="text/javascript"></script>
  	<style>
p{height: 40px;}
.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(../images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }
</style>
</head>
<body>
	<table id="contentInfo">
	
	</table>
		<div class="blank10"></div><div class="blank1_b w98"></div><div class="blank10"></div>
		<div style="padding-left: 200px;">
	 <ul class="button_1">
				<li><span onclick="submit()">确定</span></li>
				<li><span onclick="quxiao()">取消</span></li>
			</ul>
   </div>
	<s:form>
	 <s:textfield type="hidden" id="departId" name="depart.departId"/>
   </s:form>
  </body>
</html>
