<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>My JSP 'departmentManage.jsp' starting page</title>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="style/css/easyui.css">
 	<link rel="stylesheet" type="text/css" href="style/css/icon.css">
    <link rel="stylesheet" type="text/css" href="style/css/demo.css">
    <link rel="stylesheet" type="text/css" href="style/css/jquery-calendar.css">
  	<script type="text/javascript" src="style/js/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="style/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" language="javascript" src="style/js/GraphicMaterial.js"></script>
    <script type="text/javascript" src="style/js/jquery-calendar.js"></script>
    <script type="text/javascript" src="style/js/jtip.js"></script>
    <script src="style/js/sort.js" type="text/javascript"></script>

<style type="text/css">
  .panel-tool-close{
    display: none;
     display: "";
  }
  .panel-tool{
   display: none;
     display: "";
  }
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

* {
	font-size: 12px;
}

body {
	font-family: helvetica, tahoma, verdana, sans-serif;
	padding: 20px;
	font-size: 13px;
	margin: 0;
}

h2 {
	font-size: 18px;
	font-weight: bold;
	margin: 0;
	margin-bottom: 15px;
}

.demo-info {
	background: #FFFEE6;
	color: #8F5700;
	padding: 12px;
}

.demo-tip {
	width: 16px;
	height: 16px;
	margin-right: 8px;
	float: left;
}
#JT_arrow_left{
	background-image: url(../images/arrow_left.gif);
	background-repeat: no-repeat;
	background-position: left top;
	position: absolute;
	z-index:101;
	left:-12px;
	height:23px;
	width:10px;
    top:-3px;
}

#JT_arrow_right{
	background-image: url(../images/arrow_right.gif);
	background-repeat: no-repeat;
	background-position: left top;
	position: absolute;
	z-index:101;
	height:23px;
	width:11px;
    top:-2px;
}

#JT {
	position: absolute;
	z-index:100;
	border: 2px solid #CCCCCC;
	background-color: #fff;
	font-size: 12px;
}

#JT_copy{
	padding:10px 10px 10px 10px;
	color:#333333;
}

.JT_loader{
	background-image: url(../images/loader.gif);
	background-repeat: no-repeat;
	background-position: center center;
	width:100%;
	height:12px;
}

#JT_close_left{
	background-color: #CCCCCC;
	text-align: left;
	padding-left: 8px;
	padding-bottom: 5px;
	padding-top: 2px;
	font-weight:bold;
}

#JT_close_right{
	background-color: #CCCCCC;
	text-align: left;
	padding-left: 8px;
	padding-bottom: 5px;
	padding-top: 2px;
	font-weight:bold;
}

#JT_copy p{
margin:3px 0;
}

#JT_copy img{
	padding: 1px;
	border: 1px solid #CCCCCC;
}

.jTip{
cursor:help;
}
</style>
</head>
<body>
	<form id="sendMail">
		<label>素材名称</label><input type="text" name="catalogTitle" id = "catalogTitlequery">
		<label>创建日期</label>
		<input  type="text"  value=""  name="createdate" id="createdatequery" maxlength="10" onfocus="$(this).calendar()" readonly/>
		<label>素材状态:</label> 
		<select id="statusquery" name="status">
			<option value="2"  selected="selected">全部</option>
			<option value="1" >启用</option>
			<option value="0">停用</option>
		</select>
		<label>所属公众号:</label> 
		<select id="waccountquery" name="waccount">
			<option value="2"  selected="selected">全部</option>
			<option value="" >央视新闻</option>
			<option value="">悦动新闻</option>
		</select>
		
		<label>关联关键词</label><input type="text" name="keyword" id = "keywordquery">
		
		<input type="button" value="查询" onclick="query();javascript: $('#messageInfo').datagrid('unselectAll');">
		<table id="messageInfo">
		</table>
		<input type="hidden" value="" name="clientname" id="clientnamequery">
		<input type="hidden" value="" name="createuser" id="createuserquery">
		<input type="hidden" value="" name="modifyuser" id="modifyuserquery">
	</form>

	<div id="dlg" class="easyui-dialog" style="width:550px;height:600px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
    		<div class="ftitle">图文素材管理</div>
    		<b>数据区</b>
		<form id="fm" method="post" novalidate>
			<br/>
			
			<div class="fitem">
				<label>列表标题:</label> 
					 <input name="catalogTitle" style="width: 300px" id=catalogtitle class="easyui-validatebox" required="true" missingMessage="列表标题不能为空">
			</div>
			<div class="fitem">
				<label>关键词:</label> 
					 <input name="keywordName" style="width: 300px" id=keywordName class="easyui-validatebox" required="true" missingMessage="关键词不能为空">
			</div>
			<div class="ftitle">【头条】 </div> 
			<div class="fitem" id="pp"  height:auto; float:left; display:inline" >
			<input type="button" value="再加一组新闻">
			</div>
			<div class="fitem">
				<label>标题:</label> 
					 <input name="title" style="width: 300px" id="title" class="easyui-validatebox" required="true" missingMessage="标题不能为空">
			</div>
				<div class="fitem">
				<label>描述:</label> 
				<input name="describes" style="width: 300px" id="describes" class="easyui-validatebox" required="true" missingMessage="描述不能为空">
			</div>
				<div class="fitem">
				<label>图片地址:</label> 
				<input name="imageUrl" style="width: 300px" id="imageUrl" class="easyui-validatebox" required="true" validType="url" missingMessage="图片地址不能为空" invalidMessage="请输入有效的图片地址">
			</div>
			<div class="fitem">
				<label>url地址:</label> 
					 <input name="url" style="width: 300px" id="url" class="easyui-validatebox" required="true" validType="url" missingMessage="url地址不能为空" invalidMessage="请输入有效的url地址">
			</div>
			<div class="fitem">
				<label>排序号:</label> 
					 <input name="order" style="width: 300px" id="order" class="easyui-validatebox"  onmouseout ="ordertool()"  >
			</div>
			<div class="ftitle">【二条】</div>
				<div class="fitem">
				<label>标题:</label> 
					 <input name="title1" style="width: 300px" id="title1">
			</div>
			
			<div class="fitem">
				
				<input name="describes1"  type="hidden"  style="width: 300px" id="describes1" >
			</div>
				<!--<div class="fitem">
				<label>描述1:</label> 
			</div>
				-->
				<div class="fitem">
				<label>图片地址:</label> 
				<input name="imageUrl1" style="width: 300px" id="imageUrl1"  class="easyui-validatebox"  validType="url"  invalidMessage="请输入有效的图片地址">
			</div>
			<div class="fitem">
				<label>url地址:</label> 
					 <input name="url1" style="width: 300px" id="url1" class="easyui-validatebox"  validType="url"  invalidMessage="请输入有效的URL地址">
			</div>
			<div class="fitem">
				<label>排序号:</label> 
					 <input name="order1" style="width: 300px" id="order1" class="easyui-validatebox"  onmouseout ="ordertool()"    >
			</div>
			<div class="ftitle">【三条】</div>
				<div class="fitem">
				<label>标题:</label> 
					 <input name="title2" style="width: 300px" id="title2">
			</div>
			<div class="fitem">
				<input name="describes2"   type="hidden"  style="width: 300px" id="describes2" >
			</div>
			<div class="fitem">
			 <label>图片地址:</label> 
				<input name="imageUrl2" style="width: 300px" id="imageUrl2" class="easyui-validatebox"  validType="url"  invalidMessage="请输入有效的图片地址">
			</div>
			<div class="fitem">
				<label>url地址:</label> 
					 <input name="url2" style="width: 300px" id="url2" class="easyui-validatebox"  validType="url"  invalidMessage="请输入有效的URL地址">
			</div>	
			<div class="fitem">
				<label>排序号:</label> 
					 <input name="order2" style="width: 300px" id="order2" class="easyui-validatebox"  onmouseout ="ordertool()"   >
			</div>
			<div class="ftitle">【四条】</div>
			<div class="fitem">
				<label>标题:</label> 
					 <input name="title3" style="width: 300px" id="title3">
			</div>
			<div class="fitem">
				<input name="describes3"   type="hidden"  style="width: 300px" id="describes3" >
			</div>
				<!--<div class="fitem">
				<label>描述3:</label> 
			</div>
				-->
				<div class="fitem">
				<label>图片地址:</label> 
				<input name="imageUrl3" style="width: 300px" id="imageUrl3" class="easyui-validatebox"  validType="url"  invalidMessage="请输入有效的图片地址">
			</div>
			<div class="fitem">
				<label>url地址:</label> 
					 <input name="url3" style="width: 300px" id="url3" class="easyui-validatebox"  validType="url"  invalidMessage="请输入有效的URL地址">
			</div>
			<div class="fitem">
				<label>排序号:</label> 
			<input name="order3" style="width: 300px" id="order3" class="easyui-validatebox"  onmouseout ="ordertool()"  >
			</div>
			<br>
			<br>
			<!-- onmouseout="ordertool()" <input type="button" onmouseout ="ordertool()" value="排序">-->
		
			<div class="fitem">
				<label>状&nbsp;&nbsp;&nbsp;&nbsp;态:</label> 
				<input type ="radio" name="status" value="1"  id = "status1">
				<label>启用</label>
				<input type ="radio" name="status" value="0" id="status0">
				<label>停用</label>
			</div>
			<div class="fitem">
				<input type="hidden" name="id" id="defaultInfoId"/>
			</div>
		</form>
	</div>
	 <div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close');javascript: $('#matchingInfo').datagrid('unselectAll');">取消</a>
    </div>
	<div id="matchingmanage" class="easyui-dialog" style="width:540px;height:450px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
    		<table id="matchingInfo">
		</table>
	</div>
	 <div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#matchingmanage').dialog('close');javascript:$('#matchingmanageoper').dialog('close');javascript: $('#matchingInfo').datagrid('unselectAll');">关闭</a>
    </div>
	<div id="matchingmanageoper" class="easyui-dialog" style="width:200px;height:130px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
     <form method="post" id="oper" novalidate>
     	  <input type="hidden" name="defaultInfoId" id="adddefaultinfoid">
     	  <input type="hidden" id="matchingid" name="id">
     	  <input type="text" name="word" id="word"  class="easyui-validatebox" required="true" missingMessage="匹配词不能为空">
     </form>
    	
	</div>
    <div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="savematching()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#matchingmanageoper').dialog('close');javascript: $('#matchingInfo').datagrid('unselectAll');">取消</a>
    </div>
  	
</body>
</html>