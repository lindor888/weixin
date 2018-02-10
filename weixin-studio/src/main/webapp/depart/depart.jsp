<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>My Test</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
 <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/js/qtip/css/global.css" />
  	<script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
</head>
<body>
	<form action="/weixin-studio/depart/listDepart" method="post" name="departForm" id="departForm">
	<div class="search_div">
		部门名称：<input type="text" name="depart.departName" value="${depart.departName }"/>
		内外：<select name="depart.inorout" style="width:155px;">
			<option value="">全部</option>
			<option value="内部">内部</option>
			<option value="外部">外部</option>
		</select>
		角色：<select name="depart.roleId" id="roleId" style="width:155px;">
			<option value="">全部</option>
			<c:forEach items="${roleList}" var="role">
			<option value="${role.roleId }" <c:if test="${depart.roleId==role.roleId}">selected</c:if>>${role.roleName }</option>
			</c:forEach>
		</select><a href="javascript:search();" class="myBtn" style="margin-left: 85px;"><em>查询</em></a>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/></th>
			<th>序号</th>
			<th>部门名</th>
			<th>内外</th>
			<th>角色</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty departList}">
				<c:forEach items="${departList}" var="depart" varStatus="vs">
				<tr class="main_info">
				<td><input type="checkbox" name="departIds" id="departIds${depart.departId }" value="${depart.departId }"/></td>
				<td>${vs.index+1}</td>
				<td>${depart.departName }</td>
				<td>${depart.inorout }</td>
				<td>${depart.roleNames }</td>
				<td><a href="javascript:editUser(${depart.departId });">修改</a> | <a href="javascript:delDepart(${depart.departId });">删除</a> 
				<%-- <a href="/weixin-studio/fenpei.jsp"   onclick="test_Click">分配角色</a> --%>
				<a href="javascript:void(0)"  onclick="openClick(${depart.departId });">分配角色</a>
				
				</td>
				
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="7">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn">
		<div>
			<a href="javascript:addUser();" class="myBtn"><em>新增</em></a>
			<!-- <a href="javascript:exportUser();" class="myBtn"><em>导出</em></a> -->
		</div>
		${depart.page.pageStr }
	</div>
	</form>
	<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		
		function sltAllUser(){
			if($("#sltAll").attr("checked")){
				$("input[name='departIds']").attr("checked",true);
			}else{
				$("input[name='departIds']").attr("checked",false);
			}
		}
		function openClick(departId){
			var _href ='/weixin-studio/depart/fenpeiDepart?depart.departId='+departId;
			$.fn.colorbox({iframe:true,innerWidth:436, innerHeight:420, href:_href,overlayClose:false,onClosed:function(){
				search();
			}});
		}
		
		function addUser(){
			//$(".shadow").show();
			var dg = new $.dialog({
				title:'新增用户',
				id:'user_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'/weixin-studio/depart/toAddDepart'
				});
    		dg.ShowDialog();
		}
		
		function editUser(departId){
			var dg = new $.dialog({
				title:'修改用户',
				id:'user_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'/weixin-studio/depart/toEditDepart?depart.departId='+departId
				});
    		dg.ShowDialog();
		}
		
		function delDepart(departId){
			$.messager.defaults.ok = "确定";
		    $.messager.defaults.cancel = "取消";
		    $.messager.confirm('操作提示','<span style="color:red">确定要删除吗？</span>',function(r){
				 if (r){
					 var param={"depart.departId":departId};
					 $.ajax({   
					       url:'/weixin-studio/depart/selectUserbydepartDepart?random='+Math.round(Math.random()*10000),
					        type: 'POST',   
					        async: false,
					        data:  param,
					       dataType: 'json',  
					       success:function(data){ 
					    	  if(data.msg=='fail'){
					    		  $.messager.alert("操作提示","请确保部门下无人员再进行相关操作！","warning");
					    	  }else{
					    		  var url = "/weixin-studio/depart/deleteDepart?depart.departId="+departId;
									$.get(url,function(data){
										$.messager.alert("操作提示","删除成功！","info",function(){
										 document.location.reload();
										});
									});
					    	  }
					        }   
					    });
				 }
		    });
		    }
	
		
		function search(){
			var input = document.createElement("input");
			input.type="hidden";
			input.name="random";
			input.value=Math.random();
			$("#departForm").append(input);
			$("#departForm").submit();
		}
		
		function exportUser(){
			document.location = "user/excel.html";
		}
	</script>
</body>
</html>