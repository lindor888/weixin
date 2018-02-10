<%@page import="com.ctvit.util.Const"%>
<%@page import="com.ctvit.bean.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	Account account = (Account)request.getSession().getAttribute(Const.SESSION_USER);
	if(account==null){
		response.sendRedirect("login.jsp");
		return;
	}

%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>微信管理平台</title>
<link type="text/css" rel="stylesheet" href="/weixin-studio/css/index.css"/>
<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
<script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
<script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
<style type="text/css">
body{overflow:auto;}
</style>
<script type="text/javascript">

var wacId="";
$(document).ready(function(){
	 $('#choosewaccountContent').dialog('close');
	//保证在最外层
	if(window.parent!=window) {
		top.location.href = "/weixin-studio/index.jsp";
		return;
	}
	$.messager.defaults.ok = "确定";
    $.messager.defaults.cancel = "取消";
    
	getTime();
	window.setInterval(getTime, 1000);
	$("h1 a").bind("click",function(){
		var obj = $(this).parent().next();
		obj.slideToggle("fast","linear");
		//obj.fadeToggle("fast","linear");
	});
	$(".menu_line a").bind("click",function(){
		$(".content_title").html($(this).html());
		//$("#mainFrame").attr("src","");
		//$(".menu_line a").css("background-image","url('images/menu_bg1.gif')");
		$(".menu_line a").css("color","#333333");
		$(".menu_line a").css("font-weight","normal");
		$(".menu_line a").bind({
			mouseover:function(){
				//$(this).css("background-image","url('images/menu_bg2.gif')");
				$(this).css("color","#006600");
				$(this).css("font-weight","bold");
			},
			mouseout:function(){
				//$(this).css("background-image","url('images/menu_bg1.gif')");
				$(this).css("color","#333333");
				$(this).css("font-weight","normal");
			}
		});
		//$(this).css("background-image","url('images/menu_bg2.gif')");
		$(this).css("color","#006600");
		$(this).css("font-weight","bold");
		$(this).unbind("mouseout");
	});
	initRightContentHeightAndWidth();
});
function updatepasswordbyuser(){
	 //var url = "/weixin-studio/account/checkdepartmentAccount";
	    
		//$.ajax({url:url,async: false,dataType: "json",type:"POST",data: "account.accountId=2",
		//success:function(data, textStatus){
			
			//if(data.message=='TRUE'){
			//window.parent.colorBox("/weixin-mx/account/findurlAccount?method=resetpwdbyuser");
				$.fn.colorbox({iframe:true,innerWidth:550,innerHeight:350,href:"/weixin-studio/account/findurlAccount?method=resetpwdbyuser",overlayClose:false,onClosed:function(){
					
				}});
			//}else if(data.message=='ERROR'){
			//	$.messager.alert("提示","用户是公司内部人员，请去CMS修改密码！","warning");
			//}
		//}});
	}
//关闭弹出层
function closeColorBox(isReload){
	$.fn.colorbox.close();
	//if(isReload)
	if(isReload) {
		if (window.frames['frame2'] == null) window.frames[2].document.location.href = window.frames[2].document.location.href;
		else window.frames['frame2'].document.location.href = window.frames['frame2'].document.location.href;
	}
}

function initRightContentHeightAndWidth(){
	var height = $(window).height()<$(document).height()?$(window).height():$(document).height();
	var width = $(window).width()<$(document).width()?$(window).width():$(document).width();
	$(".content_right table").width(width-$(".content_left").width()-$(".content_center").width());
	$(".content_right table tr:eq(1) td").height(height-$(".main_header").height()-$(".sys_bottom").height()-46);
}

function getTime(){
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	var h = date.getHours();
	var i = date.getMinutes();
	var s = date.getSeconds();
	$("#sysTime").html(y+"年"+(m>9?m:("0"+m))+"月"+(d>9?d:("0"+d))+"日 "+(h>9?h:("0"+h))+":"+(i>9?i:("0"+i))+":"+(s>9?s:("0"+s)));
}

function logout(){
	if(confirm("确定要退出吗？")){
		document.location = "/weixin-studio/login/logoutLoginAction";
	}
}
function _href(menuUrl) {
	var waccountId = $("#roleId").val();
	if(waccountId == null || waccountId == '' || waccountId == 'null') {
		if(menuUrl.indexOf('account/selectroleAccount') >= 0 
				|| menuUrl.indexOf('menu/listMenu') >= 0
				|| menuUrl.indexOf('role/listRole') >= 0
				|| menuUrl.indexOf('depart/listDepart') >= 0
				|| menuUrl.indexOf('account/listfindurltabwaccount') >=0) {
			$("#mainFrame").attr("src", menuUrl);
		} else {
			$("#mainFrame").attr("src", '/weixin-studio/style/error/nowaccount.jsp');
		}
	} else {
		$("#mainFrame").attr("src", menuUrl);
	}
}

function chooesWaccount(){
	$('#choosewaccountContent').dialog('open').dialog({
		title: '公众帐号',
	    height:250,
	    width: 350,
	    left: 450,
	    top:100
	   // modal: true
	});
	
	var url = "/weixin-studio/login/checkwaccountLoginAction?random="+Math.round(Math.random()*10000);
	$.ajax({url:url,async: false,dataType: "json",type:"POST",data: "",
		success:function(data, textStatus){
			$("#roleId").empty();
			for(var i=0;i<data.relation.length;i++){
				$("#roleId").append("<option value='"+data.relation[i].waccountId+"' >"+data.relation[i].name+"</option>");
			}
		}
	});
	
}

function qiehuan(){
	 $('#qiehuanWaccount').text( $("#roleId").find("option:selected").text());
	 $.post("/weixin-studio/login/addWaccountIDLoginAction?waccountId="+$('#roleId').val());
	 $("#mainFrame").attr("src", "/weixin-studio/default.jsp");
	 $(".content_title").html("首页"); 
	 $('#choosewaccountContent').dialog('close');
	
}

window.onload= function(){ 
	$.post("/weixin-studio/login/addWaccountIDLoginAction?waccountId="+$('#roleId').val());
	$(".menu_line").attr("style","display:none");
};
</script>
</head>
<body>
	<div class="main_header">
		<div class="header_left"></div>
		<div class="header_right">
			<span id="waccountid">	
				<div style="float:left;margin-top:5px;" >当前公共账号：
				<c:if test="${Relation!=null && waccountId!=null}">
					<c:forEach items="${Relation}" var="waccount">
						<c:if test="${waccount.waccountId==waccountId}">
							<span id="qiehuanWaccount">${waccount.name}</span>
							<div style="display: none;" id="morenWaccountId">${waccount.waccountId}</div>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${waccountId==null}">
					<span id="qiehuanWaccount">${Relation[0].name}</span>
					<div style="display: none;" id="morenWaccountId">${Relation[0].waccountId}</div>
				</c:if>
				</div>
				<div style="float: left;margin-left:5px;">
					<span><input type="button" value="切换公众帐号" onclick="chooesWaccount()" style="height: 20px;"/></span>
				</div>
					
			</span>
			&nbsp;&nbsp;&nbsp;
			<span><a href="javascript:void(0);"   onclick="updatepasswordbyuser();" style="color: white;">修改密码</a></span>
			<span id="sysTime"></span>
			<span>${user.username }，您好！</span>
			<%-- <% 
				if(!account.getAccountId().startsWith("USIF")){
			%> --%>
			<a href="javascript:logout();"><img src="/weixin-studio/images/out.gif" border="0"/></a>
			<%-- <% 
				}
			%> --%>
		</div>
	</div>
	<div class="main_content">
		<div class="content_left">
			<c:forEach items="${menuList}" var="menu">
				<c:if test="${menu.hasMenu}">
				<h1><a>${menu.menuName }</a></h1>
				<div class="menu_line">
					<ul>
					<c:forEach items="${menu.subMenu}" var="sub">
						<c:if test="${sub.hasMenu}">
						<c:choose>
							<c:when test="${not empty sub.menuUrl}">
							<li><a href="javascript:_href('${sub.menuUrl }');">${sub.menuName }</a></li>
							</c:when>
							<c:otherwise>
							<li><a href="/weixin-studio/style/error/rolesResourceError.jsp" target="mainFrame">${sub.menuName }</a></li>
							</c:otherwise>
						</c:choose>
						</c:if>
					</c:forEach>
					</ul>
				</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="content_center">&nbsp;</div>
		<div class="content_right" style="position: absolute;left:182px;">
			<table cellspacing="0" cellpadding="0">
				<tr style="height:29px;background: url('/weixin-studio/images/content-bg.gif') repeat-x;">
					<td style="height:29px;width:17px;background: url('/weixin-studio/images/left-top-right.gif') no-repeat;"></td>
					<td style="height:29px;line-height:29px;"><div class="content_title">首页</div></td>
					<td style="height:29px;width:16px;background: url('/weixin-studio/images/nav-right-bg.gif') no-repeat;"></td>
				</tr>
				<tr>
					<td style="background:url('/weixin-studio/images/mail_leftbg.gif') repeat-y;"></td>
					<td style="background-color:#FAFBFD;">
						<iframe name="mainFrame" id="mainFrame" frameborder="0" src="/weixin-studio/default.jsp" style="width:100%;height:100%;"></iframe>
					</td>
					<td style="background:url('/weixin-studio/images/mail_rightbg.gif') repeat-y;"></td>
				</tr>
				<tr style="background: url('/weixin-studio/images/buttom_bgs.gif') repeat-x;">
					<td style="height:17px;background: url('/weixin-studio/images/buttom_left2.gif') no-repeat;"></td>
					<td></td>
					<td style="height:17px;background: url('/weixin-studio/images/buttom_right2.gif') no-repeat;"></td>
				</tr>
			</table>
			<div id="choosewaccountContent" class="easyui-dialog" buttons="#dlg-buttons">
	    		<center>
	    			<select name="depart.roleId" id="roleId" class="input_txt"  >
						 <c:forEach items="${Relation}" var="waccount">
							 <option value="${waccount.waccountId }">${waccount.name}</option>
						 </c:forEach>
					</select>
				</center>
				<center>
					<div style="padding-top: 100px;">
						 <a class='easyui-linkbutton l-btn l-btn-plain' onclick="qiehuan()"><span class='l-btn-text icon-ok' style='padding-left: 20px;'>确定</span></a>
					</div>
				</center>
			
			</div>
			<center>
				<div class="sys_bottom"> 欢迎使用，微信管理平台!</div>
			</center>
		</div>
	</div>
</body>
</html>