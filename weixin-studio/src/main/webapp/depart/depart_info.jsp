<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form action="/weixin-studio/depart/saveDepart" name="departForm" id="departForm" target="result" method="post" onsubmit="return checkInfo();">
		<input type="hidden" name="depart.departId" id="departId" value="${depart.departId }"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>部门名:</th>
			<td><input type="text" name="depart.departName" id="departName" class="input_txt" value="${depart.departName }"/></td>
		</tr>
		<tr class="info">
			<th>内　外:</th>
			<td>
			<select name="depart.inorout" id="inorout" class="input_txt">
				<option value="">请选择</option>
				<option value="内部">内部</option>
				<option value="外部">外部</option>
			</select>
			</td>
		</tr>
		<tr class="info">
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	
	<script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript">
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				$("#departForm").submit();
			});
 			if($("#departId").val()!=""){
				var inorout = "${depart.inorout}";
				if(inorout!=""){
					$("#inorout").val(inorout);
				}
			} 
 			
		});
		
		function checkInfo(){
			if($("#departId").val()=="" && $("#departName").val()==""){//新增
				alert("请输入部门名!");
				$("#departName").focus();
				return false;
			}
			if($("#inorout").val()=="" && $("#inorout").val()==""){//新增
				alert("请选择内部外部!");
				$("#inorout").focus();
				return false;
			}
			return true;
		}
		
		function success(){
			if(dg.curWin.document.forms[0]){
				dg.curWin.document.forms[0].action = dg.curWin.location+"";
				dg.curWin.document.forms[0].submit();
			}else{
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
		
		function failed(){
			alert("新增失败，该部门已存在！");
			$("#departName").select();
			$("#departName").focus();
		}
	</script>
</body>
</html>