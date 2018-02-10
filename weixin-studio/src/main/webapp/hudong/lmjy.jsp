<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>线索列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="btvinteract/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" media="all" href="btvinteract/css/admin.css" />
<link type="text/css" href="btvinteract/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
<link type="text/css" href="btvinteract/css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
<script type="text/javascript" src="btvinteract/js/jquery.js"></script>
<script	type="text/javascript" src="btvinteract/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="btvinteract/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="btvinteract/js/jquery-ui-timepicker-zh-CN.js"></script>
<!-- <script src="lib/videoPlay/swftest/flowplayer-3.2.4.min.js"></script>
<script src="lib/videoPlay/swftest/flowplayerVideo.js" type=text/javascript></script> -->


<script type='text/javascript'>
$(function () {
    $("#datetime1").datetimepicker({
        showSecond: true,
        timeFormat: 'hh:mm:ss',
        stepHour: 1,
        stepMinute: 1,
        stepSecond: 1
    });
    $("#datetime2").datetimepicker({
        showSecond: true,
        timeFormat: 'hh:mm:ss',
        stepHour: 1,
        stepMinute: 1,
        stepSecond: 1
    });
    
});
function setAct(URL){
	window.open(URL,'newwindow','height=452,width=450,top=200,left=280,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
} 
function checkAll(obj){
	var form_obj=document.getElementById(obj);
	var input_obj=form_obj.getElementsByTagName('input');
	for(i=0;i<input_obj.length;i++){
		if(input_obj[i].type=='checkbox' && input_obj[i].name=='checkany[]'){
			if(input_obj[i].checked==true){
				input_obj[i].checked='';
			}else{
				input_obj[i].checked='checked';
			}
		}
	}
}
function passany(){
	$("input[name='status']").val('2');
	$("#data_list").submit();
}
function denyany(){
	$("input[name='status']").val('3');
	$("#data_list").submit();
}
function delall(){
	$("#data_list").submit();
}
function other_reason_show(obj, baoliaoid){
	if($(obj).attr("checked") == true){
		$("input[name='other_reason_" + baoliaoid + "']").show();
	}else{
		$("input[name='other_reason_" + baoliaoid + "']").hide();
	}
}
</script>
</head>
<body>
<div id="maincontent">
  <div class="wrap">
    <div class="crumbs">当前位置 &gt;&gt; 热线列表</div>
    <hr />
	<div class="infobox">
	    <h3>筛选条件
	   
	    </h3>
	    <div class="content">
	    	<form method="get" action="/btvinteract/admin/index.php">
	    	<input type="hidden" name="action" value="baoliao-list"/>
	    	<input type="hidden" name="type" value="news"/>
	    	<input type="hidden" name="parentid" value="9"/>
			<table class="form-table">
				<tbody>
					<tr>
						
					    <th>热线内容</th>
					    <td><input type="text" value="" name="content" id="textcontent" class="small-text" />&nbsp;<font color="red">*</font></td> 
					    <!--<th>用户ID</th>
					    <td><input type="text" value="" name="userid" class="small-text" />&nbsp;<font color="red"></font></td>
				  	--><!--<th>爆料分类</th>
					    <td><select  name="topic">
					    		<option value="">全部爆料</option>
				                <option value="zhengji"  >征集线索</option>
				                <option value="news" >新闻线索</option>
				              	</select>
				              	
				        </td>
				  	--></tr>				
				  	<tr>
					    <!--th width="90px">审核状态</th>
					    <td>
					    <select name="status">
					        <>
					            <option selected="selected" value="0">-全部-</option>
					            <option value="1">待审核</option>
					            <option value="2">已通过</option>
					            <option value="3">不通过</option>
					        <>
					        </select>
						</td-->
				  		
					    <th>时间</th>
					    <td height="20" colspan="3">
					        <input type="text" value="" id="datetime1" name="datetime1" readonly="readonly" class="small-text" /> ~ 
					        <input type="text" value="" id="datetime2" name="datetime2" readonly="readonly" class="small-text" /> (YYYY-MM-DD HH:II:SS)      
						</td>
					    <!--th>用户名(昵称)</th>
					    <td><input type="text" value="<>" name="nicknm" class="small-text" />&nbsp;<font color="red">*</font></td-->
				  	</tr>
				  
				  	<!--tr>
					    <th>台名称</th>
					    <td><select id="Channelselect" name="channel" onchange="SelectTopic()">
					    		<option value="">选择所属台名称</option>
				              	<>
				              		<option value="<CHAL32356841>" <>><新疆新闻台></option>
				              	<></select></td>
					    <th>专题名称</th>
					    <td> <select id="Topicselect" name="topic"></select></td>
				  	</tr-->				  	
				  	<tr><td colspan="2">带 ' <font color="red">*</font> ' 的表示支持模糊查询</td></tr>
 					<tr><td colspan="2"><input type="submit" value="搜索" class="regular-button" /></td></tr>
				</tbody>
			</table>
			</form>
		</div>
	</div>
    <form id="data_list" action="/btvinteract/admin/index.php?action=baoliao-AuditAny" method="post">
    <input type="hidden" name="status" value="0" />
	<input type="hidden" name="type" value="news"/>
	<input type="hidden" name="parentid" value="9"/>
    <div class="infobox"> 
      <h3>热线列表
      
      </h3>
      <div class="content">
        <table class="list_table ">
        	<thead>
        	<tr>
        		<th width="5%">复选</th>
        		<th width="7%">用户</th>
        		<th width="5%">头像</th>
        		<th width="5%">地区</th>
        	
        		<th width="22%">内容</th>
        	
        		<th width="14%">发布时间</th>
        		<!--th width="12%">IP地址</th-->
        		<!--th width="9%">审核状态</th-->
        		<th width="10%">回复操作</th>
        		<!--th width="7%">复选框</th>
        		<th width="10%">用户</th>
        		<th width="27%">内容</th>
        		<th width="27%">图片</th>
        		<th width="20%">发布时间</th>
        		<th width="15%">IP地址</th>
        		<th width="11%">审核状态</th>
        		<th width="10%">回复操作</th-->
        	</tr>
        	</thead>
        	<tbody id="content">
            </tbody>
          </table>
          <div class="pages_bar"><a href="" >上一页</a><a class="current_page">1</a><a href="" >下一页</a></div>
          <div style="text-align: left;"></div>
        <div class="clear"></div>
      </div>
    </div>
    </form>
  </div>
</div>
<script type="text/javascript">	  

var currentPage = 0;
$(function(){
	  getData();
});
function getData(){
currentPage++;
  $.ajax({   
		       url:'/weixin-studio/hudong/selectcommentHudong',
		        type: 'POST',   
		        async: false,
		        data:  {'currentPage':currentPage},
		       dataType: 'json',  
		       success:function(data){ 
		    	   if(data.message == "success"){
		    	
                     var html = '';
                     for(var i=0;i<data.rows.length;i++){
                         html += '<tr>';
                         html += '<td><input type="checkbox" value="332" name="checkany[]" class="checkbox" /></td>';
                         html += '<td>'+data.rows[i].username+'</td>';
                         html += '<td><img src="'+data.rows[i].userhead+'" style="width:35px;height:35px"></td>';
                         html += '<td>'+data.rows[i].topicid+'</td>';
                       
                         html += '<td>'+data.rows[i].comment+'</td>';
                         
                         html += '<td>'+data.rows[i].created.replace("T"," ")+'</td>';
                     }
                         $("#content").html(html);
		    	   } 
		    	  
		        }   
			});
}
function search(){
   var start = $("#datetime1").val();
   var end = $("#datetime2").val();
   var content = $("#textcontent").val();
   $.ajax({   
		       url:'/weixin-studio/hudong/searchcommentHudong',
		        type: 'POST',   
		        async: false,
		        data:  {'comment.start':start,'comment.end':end,'comment.comment':content},
		       dataType: 'json',  
		       success:function(data){ 
		    	   if(data.message == "success"){
		    		   
                     var html = '';
                     for(var i=0;i<data.rows.length;i++){
                             html += '<tr>';
                         html += '<td><input type="checkbox" value="332" name="checkany[]" class="checkbox" /></td>';
                         html += '<td>'+data.rows[i].username+'</td>';
                         html += '<td><img src="'+data.rows[i].userhead+'" style="width:35px;height:35px"></td>';
                         html += '<td>'+data.rows[i].topicid+'</td>';
                       
                         html += '<td>'+data.rows[i].comment+'</td>';
                         
                         html += '<td>'+data.rows[i].created.replace("T"," ")+'</td>';
                     }
                         $("#content").html(html);
		    	   } 
		    	  
		        }   
			});
}
</script>
</body>
</html>