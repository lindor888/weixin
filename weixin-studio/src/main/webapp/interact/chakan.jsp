<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>信息审核</title>
    <meta http-equiv="x-ua-compatible" content="ie=7" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/js/qtip/css/global.css" />
   <script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/qtip/jtip.js" type="text/javascript"></script>
  	<script src="/weixin-studio/interact/general/general.js" type="text/javascript"></script>
    <script src="/weixin-studio/interact/interactchakan.js" type="text/javascript"></script>
    <script type="text/javascript">
  
   
	var mvoveUporderId = "";
	var nextorderId = "";
	var upid = "";
	var todownid="";
    function MoveUp() {
 var selecRow = $("#dataGrid").datagrid("getSelections");
 if(selecRow.length != 1){
  alert("只能选择一条数据");
  return false;
 }
    var row = $("#dataGrid").datagrid('getSelected');
    var index = $("#dataGrid").datagrid('getRowIndex', row);
	mvoveUporderId = row.orderId;
	upid = row.id;
    mysort(index, 'up', 'dataGrid');
    
}
//下移
function MoveDown() {
 var selecRow = $("#dataGrid").datagrid("getSelections");
 if(selecRow.length != 1){
  alert("只能选择一条数据");
  return false;
 }
    var row = $("#dataGrid").datagrid('getSelected');
    var index = $("#dataGrid").datagrid('getRowIndex', row);
    mvoveUporderId = row.orderId;
    upid = row.id;
    mysort(index, 'down', 'dataGrid');
    
}
 
 
function mysort(index, type, gridname) {
    if ("up" == type) {
        if (index != 0) {
            var toup = $('#' + gridname).datagrid('getData').rows[index];
            var todown = $('#' + gridname).datagrid('getData').rows[index - 1];
			nextorderId = todown.orderId;
			todownid = todown.id;
            $('#' + gridname).datagrid('getData').rows[index] = todown;
            $('#' + gridname).datagrid('getData').rows[index - 1] = toup;
            $('#' + gridname).datagrid('refreshRow', index);
            $('#' + gridname).datagrid('refreshRow', index - 1);
            $('#' + gridname).datagrid('selectRow', index - 1);
            $('#' + gridname).datagrid('unselectRow', index);
            changeOrderId(mvoveUporderId,nextorderId,upid,todownid);
            
        }
    } else if ("down" == type) {
        var rows = $('#' + gridname).datagrid('getRows').length;
        if (index != rows - 1) {
            var todown = $('#' + gridname).datagrid('getData').rows[index];
            var toup = $('#' + gridname).datagrid('getData').rows[index + 1];
            nextorderId = toup.orderId;
			todownid = toup.id;
            $('#' + gridname).datagrid('getData').rows[index + 1] = todown;
            $('#' + gridname).datagrid('getData').rows[index] = toup;
            $('#' + gridname).datagrid('refreshRow', index);
            $('#' + gridname).datagrid('refreshRow', index + 1);
            $('#' + gridname).datagrid('selectRow', index + 1);
            $('#' + gridname).datagrid('unselectRow', index);
            changeOrderId(mvoveUporderId,nextorderId,upid,todownid);
        }
    }
 
}
function comfor(){
	var selecRow = $("#dataGrid").datagrid("getSelections");
	 if(selecRow.length != 1){
	  alert("只能选择一条数据");
	  return false;
	 }
	var position = $("#position").val();
	var data=$("#dataGrid").datagrid('getData');
	if(position < 0 || position > data.total){
	 alert("无效位置重新输入");
	 return false;
	}
	//获取选中行数据
    var row = $("#dataGrid").datagrid('getSelected');
    var index = $("#dataGrid").datagrid('getRowIndex', row);
	var selectorderId = row.orderId;
	var selectid = row.id;
	//获取交换行数据
	var rows = $("#dataGrid").datagrid('getRows');
	var positionrow = rows[position-1];
	var positionorderid = positionrow.orderId;
	var positionid = positionrow.id;
	
	changeOrderId(selectorderId,positionorderid,selectid,positionid);
	
}
function changeOrderId(order1,order2,upid,todownid){
	$.ajax({   
       url:'/weixin-studio/interact/changeInteract',
       type: 'POST',   
       async: false,
       data:  {'orderId1':order1,'orderId2':order2,'upid':upid,'todownid':todownid},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   //$.messager.alert('提示','选择成功！');
    		   $('#dataGrid').datagrid('reload');
    	   }else{
    		   
    		   $.messager.alert('提示','删除失败！');
    	   }
        }    
	});
}
    </script>
  </head>
 
  <body onkeydown="xKeyEvent(event)">
  
<div id="" style="width:90%;padding-left: 10%; margin-top: 10px;">
	<div  style="height: 10px;"></div>
	<%-- 审核状态：<select id="flag" style="width:100px;">
			<option value="">全部</option>
			<option value="0">未处理</option>
			<option value="1">审核通过</option>
			<option value="2">审核未通过</option>
		</select>&nbsp;&nbsp;&nbsp; --%>
	昵称：<input type="text" id="nickname" />
	<a class='easyui-linkbutton l-btn l-btn-plain' onclick="selectByPagechakan()" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>查询</span></a>
	<a class='easyui-linkbutton l-btn l-btn-plain' onclick="shuaxin()" ><span class='l-btn-text icon-search' style='padding-left: 20px;'>刷新</span></a><br/><br/> 
	排序：
	<a href="#" class="easyui-linkbutton" onclick = "MoveUp()">上移</a>
	<a href="#" class="easyui-linkbutton" onclick = "MoveDown()">下移</a>
	&nbsp;&nbsp;调至：<input type="text" name="position" id="position" style="width:40px;"/>
	<a href="#" class="easyui-linkbutton" onclick = "comfor()">确定</a>
</div>

 <table id="dataGrid"></table>
  </body>
 
</html>
