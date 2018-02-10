<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.springframework.web.context.ContextLoaderListener" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  xml:lang="zh-CN" lang="zh-CN">
<html>
<head>
		<title>挑选用户</title>
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
  	<script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<style>
p{height: 40px;}
.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }
</style>
</head>
<body>
<script language="javascript">
	var QueryAction="/weixin-studio/account/chooseAccount";
	var total = 10;
	$(document).ready(function(){
		$('#selected').datagrid({
			width:688,
			pageNumber:1,
			pageSize:10,
			pageList:[10,20,30],
			url:"",
			striped: true,
			idField:"id",
			frozenColumns:[[{field:'ck',checkbox:true}]],
			onLoadSuccess:function(data){
				if($('.datagrid-row-selected').length>=20){
					$('#selected').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", true);	
				}else{
					$('#selected').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", false);	
				}	
			},
			onSelect:function(rowIndex, rowData){
				var selected = $('.datagrid-row-selected').length/2;
				if(total==selected)
					$('#selected').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", true); 
			},
			onUnselect:function(rowIndex, rowData){
				var selected = $('.datagrid-row-selected').length/2;
				if(total!=selected)
					$('#selected').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", false); 
			},
			columns:[[				
				{field:"id",title:'用户id',width:200,align:'center'},
				{field:"loginName",title:'用户名',width:200,align:'center'},
				{field:"name",title:'真实姓名',width:200,align:'center'}
				]],
			rownumbers:true,
			pagination:true,
			remoteSort:false
		});
		initPagination('selected');
	});
	
	
	function initPagination(Id){
		var _pag = $('#'+Id).datagrid('getPager');
		if (_pag){
			$(_pag).pagination({
				showPageList:false,
				beforePageText:'第',
				afterPageText:'页  共{pages}页',
				displayMsg:'第 {from} 至 {to}条  共 {total} 条'
			});
		}
    }
	/*验证是否选中*/
  	function checkSelect(tname){
  		var row = $(tname).datagrid('getSelections');
  		if (row==''){
  			$.messager.alert('警告','请选中一行记录！');
  			return false;
  		}else{
  			return row;
  		}
  	}
	
  	/*加入到固化列表*/
	function join(){
		var row = checkSelect("#selected");
		var len = row.length;
		if(len>1){
			$.messager.alert('警告','只能选择一行记录！');
		}else{
			var data = row[0];
			window.parent.setUser(data);
			window.parent.closeColorBox();
		}
	}
  	function getData(){
		$("#selected").datagrid({
			url:QueryAction,
			pageNumber:1,
			remoteSort:false
		});
		initPagination('selected');
	}
	
  	$(function(){
  		getData();
  	});
	
</script>
<div class="Area" style="width:auto;">
	<div class="content" >
		<div class="cut17">
		<div class="blank10"></div>
		<div class="block2" style="border:0px;">  
		<table id='selected'></table>
		</div>
		<div class="blank10"></div>  
		<div style="padding-left: 230px;">
		<ul class="button_1">
				<li><span onclick="join();">确定</span></li>
				<li><span onclick="window.parent.closeColorBox();">取消</span></li>
			</ul>
		</div>
		<div class="blank10"></div>
		</div>
	</div>
</div>
</body>
</html>