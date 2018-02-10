<%@page import="com.ctvit.util.Const"%>
<%@page import="com.ctvit.bean.Account"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
	Account account = (Account)request.getSession().getAttribute(Const.SESSION_USER);
	if(account!=null){
		response.sendRedirect("index.jsp");
		return;
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link  href="/weixin-studio/style/css/login.css" rel="stylesheet"/>
<script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="/weixin-studio/js/login/login.js" type="text/javascript"></script>
<title>微信登录</title>
</head>

<body style="background:#015792">
	<form action="/weixin-studio/login/loginLoginAction" method="post" id="loginForm">
		<div class="bg">
			<div class="wrapper">
				<div class="loginall">
					<div class="login">
						<div class="name">
							<input type="text" class="namebox" id="userName" name="account.login" value="${account.login}" />
						</div>
						<div class="password">
							<input type="password" class="pdbox" id="passWord" name="account.password" />
						</div>
						<div class="vspace"></div>
						<div class="loginbtn">
							<a onclick="login()" id="login"><img src="/weixin-studio/style/images/bt.jpg" width="57" height="23" /></a>
							&nbsp;&nbsp;&nbsp;
							<span id="msgs" style="color: red">
								<s:if test="mapJson.msg == 'fail'">
									用户名或密码错误
								</s:if>
								<s:elseif test="mapJson.msg =='isExits'">
									用户不存在
								</s:elseif>
								<s:elseif test="mapJson.msg =='inuser'">
									内部用户请从CMS登录
								</s:elseif>
								<s:else>
									${mspJson.msg}
								</s:else>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
