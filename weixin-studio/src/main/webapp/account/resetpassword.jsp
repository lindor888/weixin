<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <head>
    <title>修改密码</title>
    <meta http-equiv="x-ua-compatible" content="ie=7" />
	<link type="text/css" rel="stylesheet" href="<%=path %>/style/easyui/easyui.css" />
	<link type="text/css" rel="stylesheet" href="<%=path %>/style/colorBox/colorbox.css" />
 	<link type="text/css" rel="stylesheet" href="<%=path %>/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="<%=path %>/style/css/style.css" />
	<script src="<%=path %>/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="<%=path %>/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="<%=path %>/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=path %>/style/js/jquery.validate.js"></script>
  
<style>

.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(../images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }
.h{height: 20px; }

</style>
<script>

function checkPassword(password){
	var numReg=/[0-9]+/;  			//必须有数字
	var chrReg=/[A-Za-z]+/;			//必须有字母
	var signReg=/\w+/;				//必须有特殊符号
	var chrDReg=/[A-Z]+/;			//必须有大写字母
	var chrXReg=/[a-z]+/;			//必须有小写字母
	
	var reg=/^\w+$/;
	
	var numflg=numReg.test(password);
	var chrflg=chrReg.test(password);
	var signflg=signReg.test(password);
	var chrDflg=chrDReg.test(password);
	var chrXflg=chrXReg.test(password);
	
	var flg=reg.test(password);
	
	//alert("numflg  "+numflg);alert("chrflg  "+chrflg);alert("signflg  "+signflg);alert("forbidflg  "+forbidflg);
	if(numflg && chrDflg && chrXflg){
		return true;
	}else{
		return false;
	}
}

function forbidSign(password){
	var forbidReg=/[\s\t\v%&\'\"]+/;	//不能有数据库限制的字符 ' " % & ，制表符 和 看不见的字符
	var forbidflg=forbidReg.test(password);
	if(!forbidflg){
		return true;
	}else{
		return false;
	}
}
$(document).ready(function(){
	// 字符验证
	jQuery.validator.addMethod("myPassWordValidate", function(value, element) {
	  return this.optional(element) || checkPassword(value);			//^\w+$/.test(value);
	}, "密码必须由大小写英文字母和数字组成。");
	jQuery.validator.addMethod("myPassForbid", function(value, element) {
	  return this.optional(element) || forbidSign(value);
	}, "不能包含特殊限制字符（比如制表符 空格 % & ‘ “ 等）");
	//验证初始化
	$("#resetpwdFrom").validate({
			groups:{username:"newPassword reuserPassword"},
			errorPlacement:function(error,element){
			if (element.attr("name") == "newPassword" || element.attr("name") == "reuserPassword")
				error.insertAfter("input[name='newPassword']");
			else
				error.insertAfter(element);
			},
			rules:{
			    
				"oldPassword":{equalTo:"input[name='account.password']",minlength:6,maxlength:32},
				"newPassword":{myPassWordValidate:true,myPassForbid:true,minlength:16,maxlength:32},
				"reuserPassword":{myPassWordValidate:true,myPassForbid:true,equalTo:"input[name='newPassword']",minlength:16,maxlength:32}
			},
			messages:{
			   
				"oldPassword":{equalTo:"原始密码不一致，请重新输入",minlength:"至少6个字符",maxlength:"最多32个字符"},
				"newPassword":{equalTo:"密码不一致，请重新输入",minlength:"至少16个字符",maxlength:"最多32个字符"},
				"reuserPassword":{equalTo:"密码不一致，请重新输入",minlength:"至少16个字符",maxlength:"最多32个字符"}
			}
		});
	});
//用户更新表单提交
function updatepwd(formId){
	
	var loginName = $("#newPassword").val();
	if(loginName=='' || loginName.length<1){
		return;
		}	
	$("#"+formId).submit();
}

function resetpwd(formId){
	$("#beforepasswors").val("");
	$("#newPassword").val("");
	$("#resetpassword").val("");

	
}

function closeColorBox(isReload){
	
	$.fn.colorbox.close();
	$.fn.colorbox.overlayClose(true);
	
}
</script>

</head>
 
<body>
<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<strong>修改密码</strong>
		</div>
	</div>
<div class="cut2">
	<div class="block2" id="subnav_0">

<div class="h">

</div>
	<s:form  method="post" namespace="/account" action="inputAccount?method=revisepwdbyuser"  id="resetpwdFrom"   theme="simple">
	 <s:hidden  name="account.accountId" />
	<s:hidden  name="account.password" id="oldPassword" />
	
	
	&nbsp;&nbsp;&nbsp;&nbsp;当前用户：<s:property value="account.username"/>
	<p></p>	
	<p><i>*</i>&nbsp;原密码：&nbsp;&nbsp;&nbsp;&nbsp;<s:password id="beforepasswors" name="oldPassword" size="25"/></p>	
	<p><i>*</i>&nbsp;新密码：&nbsp;&nbsp;&nbsp;&nbsp;<s:password id="newPassword" name="newPassword" size="25"/>	</p>	
	<p><i>*</i>&nbsp;新密码确认：<s:password id="resetpassword" name="reuserPassword" size="25"/></p>	
	<p></p>	<p></p>
	
	 <ul class="button_1">
				<li><span onclick="updatepwd('resetpwdFrom')">修改</span></li>
				<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
			</ul>
	</s:form>
	 </div>
	 </div>
   
  </body>
</html>
