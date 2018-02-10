<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>修改用户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="x-ua-compatible" content="ie=7" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
    <script type="text/javascript" src="/weixin-studio/style/js/jquery-1.4.2.min.js"></script>
  	<script type="text/javascript" src="/weixin-studio/style/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/weixin-studio/style/js/jquery.validate.js"></script>
<style>
p{height: 20px;}
.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(../images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }

</style>
<script>
	//验证初始化
	$(document).ready(function(){
    $("#accountInfFrom").validate({   
				rules:{
				"account.login":{required:true,remote:{url:"/weixin-studio/account/checkUsernameAccount?account.accountId="+$("#accountid").val(),type:"post"},minlength:1,maxlength:20},
				"account.username": {required:true,minlength:1,maxlength:20}
				
			},
			messages:{
				"account.login": {required:"用户名不能为空，请重新输入",remote:"用户名已经存在，请重新输入",minlength:"至少1个字符",maxlength:"最多20个字符"},
				"account.username":{required:"真实姓名不能为空，请重新输入",minlength:"至少1个字符",maxlength:"最多20个字符"}
				
			}
		});
	
	});
	
	//用户添加表单提交
	function updateUser(formId){
		var depart=$("#department").val();
		if(depart==0||depart==''){
			$.messager.alert("提示","请先选择部门！","warning");
			return;
		}
		var accountId = $("#accountid").val();
		var text = "";
		var option = document.getElementById("department").options;
		for(var i=0;i<option.length;i++){
			if(option[i].selected== true ){
				text = option[i].text;
				break;
			}
		}
		//内部
		if(accountId.indexOf('USIF')!=-1){
			if(text.indexOf('内部')==-1){
				$.messager.alert("提示","内部用户只能选择内部部门!","warning");
				return;
			}
		//外部
		}else{
			if(text.indexOf('外部')==-1){
				$.messager.alert("提示","外部用户只能选择外部部门!","warning");
				return;
			}
		}
		$("#"+formId).submit();
	}
	function checkpartent(){
		$.messager.defaults.ok = "确定";
		var depart=$("#department").val();
		if(depart!=null&&depart!=0){
			var url = "/weixin-studio/depart/selectdepartmentRelationAction";
			$.ajax({url:url,async: false,dataType: "json",type:"POST",data: "id="+depart,
				success:function(data, textStatus){
					$("#roleid").empty();
					$("#roleid").append("<option value='0'>请选择</option>");
					if(data.relation.length>0) {
						
						for(var i=0;i<data.relation.length;i++) {
							$("#roleid").append("<option value='"+data.relation[i].roleId+"'>"+data.relation[i].rolename+"</option>");
						}
					} 
				}
			});
		}else{
			$("#roleid").empty();
			$("#roleid").append("<option value='0'>请选择</option>");
		}
		
	}
</script>
  </head>
  <body>
  <div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<strong>添加用户</strong>
		</div>
	</div>
<div class="cut2">
	<div class="block2" id="subnav_0">


</div>
</div>
<div>
	<s:form  method="post" namespace="/account" action="inputAccount?method=update"  id="accountInfFrom"   theme="simple">
	<p><i>*</i>&nbsp;&nbsp;用户名：&nbsp;&nbsp;<s:textfield name="account.login"/></p>	
	<p></p>
	<p><i>*</i>&nbsp;&nbsp;真实姓名：<s:textfield name="account.username"/>	</p>	
		<p></p>
	<p>&nbsp;&nbsp;&nbsp;部门：&nbsp;&nbsp;&nbsp;&nbsp;<s:select onchange="checkpartent()" id="department" name="account.department" list="departlist" listKey="departId" listValue="departName" /></p>	
		<p></p>	
	<p>	&nbsp;&nbsp;&nbsp;角色：&nbsp;&nbsp;&nbsp;&nbsp;<s:select id="roleid" name="account.roleId"  list="DepartRelationlist"   listKey="roleId"  listValue="rolename" headerKey="0" headerValue="请选择"/></p>
   	<p></p>
	<p><s:textfield id="accountid" name="account.accountId" type="hidden"/></p>	
	 <ul class="button_1">
				<li><span onclick="updateUser('accountInfFrom')">确定</span></li>
				<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
			</ul>
	</s:form>
	 </div>
	
   
  </body>
</html>
