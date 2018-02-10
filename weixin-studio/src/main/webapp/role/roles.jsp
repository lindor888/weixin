<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色</title>
<link type="text/css" rel="stylesheet" href="../css/main.css" />
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
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>角色名称</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty roleList}">
				<c:forEach items="${roleList}" var="role" varStatus="vs">
					<tr class="main_info">
						<td>${vs.index+1}</td>
						<td id="roleNameTd${role.roleId }">${role.roleName }</td>
						<td><a href="javascript:editRole('${role.roleId }');">修改</a> |
							<a href="javascript:delRole('${role.roleId }');">删除</a> | <a
							href="javascript:editRights('${role.roleId }');">权限</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="3">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn">
		<div>
			<a href="javascript:addRole();" class="myBtn"><em>新增</em></a>
		</div>
	</div>

	<script type="text/javascript"
		src="../js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".main_info:even").addClass("main_table_even");
		});

		function addRole() {
			var dg = new $.dialog(
					{
						title : '新增角色',
						id : 'role_new',
						width : 300,
						height : 130,
						iconTitle : false,
						cover : true,
						maxBtn : false,
						xButton : true,
						resize : false,
						html : '<div style="width:100%;height:40px;line-height:40px;text-align:center;"><span style="color: #4f4f4f;font-size: 13px;font-weight: bolder;display:inline-block;vertical-align:middle;">角色名称：</span><input type="text" name="role.roleName" id="roleName" style="vertical-align: middle;"/></div>'
					});
			dg.ShowDialog();
			dg.addBtn('ok', '保存', function() {
				var url = "/weixin-studio/role/saveRole";
				$.post(url,{"role.roleName":$("#roleName").val()}, function(data) {
					if ((data+'') == 'success') {
						dg.curWin.alert("添加成功");
						dg.curWin.location.reload();
						dg.cancel();
					} else {
						alert('角色名重复，保存失败！');
						$("#roleName").focus();
						$("#roleName").select();
					}
				},'text');
			});
		}

		function editRole(roleId) {
			var roleName = $("#roleNameTd"+roleId).text();
			var dg = new $.dialog(
					{
						title : '修改角色',
						id : 'role_edit',
						width : 300,
						height : 130,
						iconTitle : false,
						cover : true,
						maxBtn : false,
						xButton : true,
						resize : false,
						html : '<div style="height:40px;line-height:40px;text-align:center;"><span style="color: #4f4f4f;font-size: 13px;font-weight: bold;display:inline-block;vertical-align:middle;">角色名称：</span><input type="text" name="roleName" id="roleName" value="'+roleName+'" style="vertical-align: middle;"/></div>'
					});
			dg.ShowDialog();
			dg.addBtn('ok', '保存', function() {
				var url = "/weixin-studio/role/saveRole";
				$.post(url, {"role.roleName":$("#roleName").val() ,"role.roleId":roleId }, function(data) {
					if (data == 'success') {
						dg.curWin.alert("修改成功");
						dg.curWin.location.reload();
						dg.cancel();
					} else {
						alert('角色名重复，保存失败！');
						$("#roleName").focus();
						$("#roleName").select();
					}
				},'text');
			});
		}
		
		function delRole(roleId){
			$.messager.defaults.ok = "确定";
		    $.messager.defaults.cancel = "取消";
		    $.messager.confirm('操作提示', '<span style="color:red">确定要删除吗？</span>', function(r){
				 if (r){
					 var param={"role.roleId":roleId};
					 $.ajax({   
					       url:'/weixin-studio/role/selectUserByroleRole?random='+Math.round(Math.random()*10000),
					        type: 'POST',   
					        async: false,
					        data:  param,
					       dataType: 'json',  
					       success:function(data){ 
					    	  if(data.msg=='fail'){
					    		  $.messager.alert("操作提示","请确保该角色下无用户再进行相关操作！","warning");
					    	  }else{
					    		  var url = "/weixin-studio/role/deleteRole?role.roleId="+roleId;
					    		  $.get(url,function(data){
										if(data=="success"){
											$.messager.alert("操作提示","删除成功！","info",function(){
												 document.location.reload();
												});
										} else {
											alert("删除失败");
										}
									},'text');
					    	  }
					        }   
					    });
				 }
		    });
		}

		function editRights(roleId) {
			var dg = new $.dialog({
				title : '角色授权',
				id : 'auth',
				width : 280,
				height : 370,
				iconTitle : false,
				cover : true,
				maxBtn : false,
				resize : false,
				page : '/weixin-studio/role/authRole?role.roleId=' + roleId
			});
			dg.ShowDialog();
		}
	</script>
</body>
</html>