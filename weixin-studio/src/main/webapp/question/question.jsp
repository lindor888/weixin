<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>答题功能</title>


<link rel="stylesheet" type="text/css" href="/weixin-studio/js/question/css/style.css" />

<style type="text/css">
.demo{width:760px; margin:60px auto 10px auto}
</style>

<script type="text/javascript" src="/weixin-studio/js/question/jquery.min.js"></script>
<script type="text/javascript" src="/weixin-studio/js/question/quiz.js"></script>
<script type="text/javascript">
   

	</script>
 </head>
 <body>
 <div class="demo">
	<div id='quiz-container'></div>
</div>
<div>
<img style="display: none;" src="" id="userInfo_img">
<input type="hidden" name="userid" value="2" id="userid">
<input type="hidden" name="username" value="2" id="username">

</div>
	
	
 </body>
</html>
