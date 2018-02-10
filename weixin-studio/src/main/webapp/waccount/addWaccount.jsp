<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>添加公共账号</title>
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
    <script src="/weixin-studio/js/waccount/choosematerial.js" type="text/javascript"></script>
    
<style>
table tr{ height: 40px; }
p{ height: 40px; padding-left: 20px; }
.h{ height: 30px}

.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(../images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }

</style>

<script>

$(document).ready(function(){
	
	
$("#userInfFrom").validate({   
						rules:{
						"waccount.name":{required:true,remote:{url:"/weixin-studio/account/checkwaccounttabwaccount",
								
							data:{
					 			"waccount.name":function(){
					 				
									return $("#waccountname").val();
					 			}
							}	,
								type:"post"},minlength:1,maxlength:20}
				},
					messages:{
						"waccount.name": {required:"用户名不能为空，请重新输入",remote:"用户名已经存在，请重新输入",minlength:"至少1个字符",maxlength:"最多20个字符"}

					}
				});
		    
		    
		 
			
			});	


//用户添加表单提交
	function insertUser(formId){
		var radtype = $('input[name="waccount.type"]:checked').val();
		if(radtype=='1'){
			var bridgeToken = $("#bridgeToken").val();
			if(bridgeToken==''){
				var waccountappid = $("#waccountappid").val();
				if(waccountappid.length<1){
					alert("请填写AppId");
					return;
				}
				var waccountsecret = $("#waccountsecret").val();
				if(waccountsecret.length<1){
					alert("请填写Appsecret");
					return;
				}
			}
		}
		$("#"+formId).submit();
		
	}
	
	function changeType(){
		var radtype = $('input[name="waccount.type"]:checked').val();
		if(radtype=='0'){
			dingyue();
		}else{
			fuwu();
		}
	}
		
	function dingyue(){
		var appidHtml = $("#appid").html();
		appidHtml = appidHtml.replace('<i>*</i>','');
		$("#appid").html(appidHtml);
		
		var appsecretHtml = $("#appsecret").html();
		appsecretHtml = appsecretHtml.replace('<i>*</i>','');
		$("#appsecret").html(appsecretHtml);
		
	}
	
	function fuwu(){
		var appidHtml = $("#appid").html();
		if(appidHtml.indexOf('<i>*</i>')==-1){
			appidHtml = '<i>*</i>' + appidHtml;
			$("#appid").html(appidHtml);
		}
		
		var appsecretHtml = $("#appsecret").html();
		if(appsecretHtml.indexOf('<i>*</i>')==-1){
			appsecretHtml = '<i>*</i>' + appsecretHtml;
			$("#appsecret").html(appsecretHtml);
		}
	}

	function tiaoxuansucai(){
		$.messager.alert("提示","暂无素材！","warning");	
	}

</script>
</head>
<body>
<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<strong>添加公共账号</strong>
		</div>
	</div>
</div>
	<s:form  method="post" namespace="/account" action="insertwaccounttabwaccount"  id="userInfFrom"   theme="simple">
	<s:textfield id="waccountId" type="hidden" name="waccount.waccountId"/>  
	<p><i>*</i>&nbsp;<span style="padding-right: 12px;">微信公众平台用户名:</span><s:textfield id="waccountname" name="waccount.name" size="40"/><span id="tishi"></span></p>
	<p><span style="padding-right: 108px;">Token:</span><s:textfield   name="waccount.token" size="40"/></p> 
	<p><span style="padding-right: 60px;">公共号类型：</span>
	<!--   <s:radio  id="radtype" name="waccount.type" list="#{'0':'订阅号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', '1':'服务号  '}" required="true" requiredposition="right"   value="0" onclick="hiddenAppid()" ></s:radio></p>
	-->
	<input type="radio" id="radtype" name="waccount.type" value="0" checked="checked" onclick="changeType()">订阅号</input>
	<input type="radio" id="radtype" name="waccount.type" value="1"  onclick="changeType()">服务号</input>
	<p><span style="padding-right: 60px;">关注欢迎语：</span>
	<select id="welcomeContented"  >
	<option value="1">文本</option>
	<option value="2">图文新闻</option>
	</select>
	<s:textfield id="comtitle" readonly = "true" size="25" name="waccount.comcatalogTitle"/>  
	<s:textfield id="welcomeContent" name="waccount.welcomeContent" type="hidden"/>
	<span style="font-size: 10px;"><a href="javascript:void(0);" onclick="tiaoxuansucai();">挑选素材</a></span></p>
	<p><span style="padding-right: 15px;">无返回结果提示语：</span>
	<select id="noreplyed"   >
	<option value="1">文本</option>
	<option value="2">图文新闻</option>
	</select>
	<s:textfield id="notitle" size="25" name="waccount.nocatalogTitle" readonly = "true"/>&nbsp;
	<s:textfield id="noReplyContent" name="waccount.noReplyContent" type="hidden" />
	<span style="font-size: 10px;"><a href="javascript:void(0);" onclick="tiaoxuansucai();">挑选素材</a></span></p>
	<%-- <p><span style="padding-right: 45px;">是否压缩图片：</span> 
	<s:radio list="#{'0':'是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', '1':'否'}" required="true" requiredposition="right"  name="waccount.ifCompressImage" value="0"></s:radio></p> --%>
	
	<p><span style="padding-right: 125px;">url:</span><s:textfield  readonly = "true" id="url" name="waccount.url" size="40"/> &nbsp; </span></p>
	<div id="all">
	<div id="appidandsecret">
	<p id="appid">&nbsp;<span style="padding-right: 90px;">AppId:</span><s:textfield id="waccountappid" name="waccount.appId" size="40"/>&nbsp;<span id="appidvalue" style="font-size: 10px;">公众平台申请到的AppId</span></p>
	<p id="appsecret">&nbsp;<span style="padding-right: 53px;">Appsecret：</span><s:textfield id="waccountsecret" name="waccount.appSecret" size="40"/>&nbsp;<span id="appsecretvalue"  style="font-size: 10px;">公众平台申请到的AppSecret<span></p>
	</div>
	
	<div>
		<p>&nbsp;<span style="padding-right: 62px;">天脉Token:</span><s:textfield id="bridgeToken" name="waccount.bridgeToken" size="40"/>&nbsp;<span style="font-size: 10px;">天脉系统提供Token</span></p>
	</div>
	
	</div>
	<div style="padding-left: 250px;"><ul class="button_1" >
				<li><span onclick="insertUser('userInfFrom')">保存</span></li>
				<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
	</ul></div>
	
	</s:form>
	
	
	
	
	<div id="choosewaccountText" class="easyui-dialog" style="width:520px;height:450px;padding:10px 20px"
     buttons="#dlg-buttons"  >
    		 <input type="hidden"  id="textbacktype"/>
    		<label>按默认词查询：&nbsp;&nbsp;</label><input type="text" name="catalogTitle" id = "catalogTitlequeryContent" maxlength="200">&nbsp;
			<input type="button" value="查询" onclick="queryContent();javascript: $('#contentInfo').datagrid('unselectAll');">
			<table id="textInfo">
			</table>
			<br/>
			<center><input type="button" value="确定" onclick="sureText();"></center>
	</div>
	
	
		
	
	<div id="choosewaccountContent" class="easyui-dialog" style="width:520px;height:450px;padding:10px 20px"
     buttons="#dlg-buttons"  >
    		 <input type="hidden" id="conterbacktype"/>
    		<label>按默认词查询：&nbsp;&nbsp;</label><input type="text" name="catalogTitle" id = "catalogTitlequeryContent" maxlength="200">&nbsp;
			<input type="button" value="查询" onclick="queryContent();javascript: $('#contentInfo').datagrid('unselectAll');">
			<table id="contentInfo">
			</table>
			<br/>
			<center><input type="button" value="确定" onclick="sureContent();"></center>
	</div>

	 <input type="hidden" id="basePath" value="<%=path%>">
	
   
   
  </body>
</html>
