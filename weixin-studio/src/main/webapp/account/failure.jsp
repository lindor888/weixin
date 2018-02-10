<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
    <title>错误页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<script  language="JavaScript">
		function success(){
		  var p = window.parent;
		  p.closeColorBox(true);
		}
	</script>
  </head>
  
   <body align="center" style="padding-top: 100px;">
   <div class="cut14">
			<div class="out">
				<div class="in">
				<center><h2 style="color:red"><img src="/weixin-studio/style/icons/close_32.png"/><%=request.getParameter("errorMes")%></h2></center>
				<h3></h3>
				<div class="blank20"></div>
				<ul class="button_2">
					<li><span onclick="success()">确认</span></li>
				</ul>
				<div class="blank20"></div>
				</div>
			</div>
		</div>
  </body>
</html>