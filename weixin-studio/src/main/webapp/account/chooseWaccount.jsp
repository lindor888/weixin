<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>挑选微信公共平台账号</title>
	 <meta http-equiv="x-ua-compatible" content="ie=7" />
     <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	 <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
     <link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
     <link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
     <script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	 <script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	 <script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
     <script type="text/javascript" src="/weixin-studio/style/js/jquery.validate.js"></script>
 <!--   <script src="/weixin-studio/js/user/modifyaccount.js" type="text/javascript"></script> -->  
  
<style>
p{height: 40px;}
.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(../images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }

</style>
<script>
var types="";
$(document).ready(function() {
	var actionUrl = "/weixin-studio/account/selectAllfebpeitabwaccount";
	var pramas = {"wid" : $("#accountId").val()};
	$.ajax({
		type : "POST",
		url : actionUrl,
		data : pramas,
		dataType : "json",
		success : function(data) {
			var json1=data.rows;
			for(var i=0;i<json1.length;i++){
				$("#leftSelect").append(
						"<option value=\""+ json1[i].waccountId
							+ "\">"
								+json1[i].name + "</option>");
			}
			var json2=data.reallist;
			
			for(var i=0;i<json2.length;i++){
				$("#rightSelect").append(
						"<option value=\""+ json2[i].waccountId
							+ "\">"
								+json2[i].name + "</option>");
			}
			
			if(json2.length!=0)
				types="del";
		}
	});
	
	
});

function operRight() {
	$("#leftSelect option").each(
			function() {
				if ($(this).attr("selected")) {
					if (checkRight($(this).text())) {
						$("#rightSelect").append(
								"<option value=\"" + $(this).attr("value")
										+ "\">" + $(this).text()
										+ "</option>");
					}
				}
			});
}

function checkRight(channel_name) {
	var flag = true;
	$("#rightSelect option").each(function() {
		if ($(this).text() == channel_name) {
			flag = false;
		}
	});
	return flag;
}

function delRight() {
	// types="del";
	$("#rightSelect option").each(function() {
		if ($(this).attr("selected")) {
			$(this).remove();
		}
	});
}

function getChecked() {
	$.messager.defaults.ok = "确定";
	 
		if ($("#rightSelect option").length == 0) {
			
			if(types!="del"){
			
			$.messager.alert("提示","请选择公众账号！","warning");
			return false;
			}
		} else {
			$("#rightSelect option").each(function() {
				$(this).attr("selected", true);
			});
			$("#leftSelect option").each(function() {
				$(this).attr("selected", false);
			});
		}
	
	
	var cid = new Array();
	$("#rightSelect option").each(function() {
		cid.push($(this).val());
	});

	
	var pramas = {

		"accountId" : $("#accountId").val(),
		"selectedIds" : "[" + cid + "]"
		
	};
	$.messager.defaults.ok = "确定";
	var actionUrl = "/weixin-studio/account/addrelationRelationAction";
	$.ajax({
		type : "POST",
		url : actionUrl,
		data : pramas,
		dataType : "json",
		success : function(data) {
			if (data.message == 'OK') {
				$.messager.alert("提示","分配成功！","warning");
				//parent.$.fn.colorbox.close();
			} else {
				
				$.messager.alert("提示","分配失败，请重试！","warning");
			}
		}
	});
	types="";
}
</script>
</head>
<body>
<!--  	<table id="contentInfo">
	
	</table>
		<div class="blank10"></div><div class="blank1_b w98"></div><div class="blank10"></div>
		<div style="padding-left: 200px;">
	 <ul class="button_1">
				<li><span onclick="submit()">确定</span></li>
				<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
			</ul>
   </div>
	<s:form>
	 <s:textfield type="hidden" id="accountId" name="account.accountId"/>
	 <input type="hidden" id="basePath" value="<%=path%>">
   </s:form>-->
   <center>
   <s:form>
	 <s:textfield type="hidden" id="accountId" name="account.accountId"/>
	
   </s:form>
<div class="Area" style="width:auto;">
	<div class="content" >
		<div class="cut1">
			<div class="blank10"></div>
			<div class="block10" style="border:0px">
				<h1  style="font-size:20px;font-weight: bold;">分配公众账号</h1>
				<input type="hidden" name="tabCustomColumn.label_id" value="<%=request.getParameter("labelId") %>" id="label_id" />
			</div>
			<div class="blank10"></div>
			<div class="sel">
				<table>
				<tr>
				<td ></td>
				<td></td>
				<td></td>
				</tr>
				<tr>
				<td></td>
				<td></td>
				<td></td>
				</tr>
				<tr>
				<td>公众账号（按ctrl或shift可多选）</td>
				<td></td>
				<td>已分配公众账号</td>
				</tr>
				<tr>
				<td><select id="leftSelect" style="width:200px;height: 300px; float: left;" multiple="multiple" name="column_id"></select></td>
				<td><input type="button" value="&gt;&gt;" onclick="operRight()" title="分配账号" /><br/>
				<input type="button" value="&lt;&lt" onclick="delRight()" title="撤销账号"/></td>
				<td><select multiple="multiple" style="width:200px;height: 300px; float: left;" id="rightSelect" name="column_id"></select></td>
				</tr>
				</table>
			</div>	
			<div class="blank10"></div>			
		<!-- <a class="cms_btn"><span class="cms_btn_t"  onclick="getChecked()" >确认</span></a> -->
		  <div style="margin-left: 215px;">
			 <ul class="button_1">
				<li><span onclick="getChecked()">确定</span></li>
				<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
			</ul>
			</div>	
		</div>
	</div>
</div>
</center>
  </body>
</html>
