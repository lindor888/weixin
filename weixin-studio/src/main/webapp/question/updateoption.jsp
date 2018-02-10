<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>问题列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" media="all" href="/weixin-studio/js/question/css/admin.css" />
<link type="text/css" href="/weixin-studio/js/question/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
<link type="text/css" href="/weixin-studio/js/question/css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
<script type="text/javascript" src="/weixin-studio/js/question/js/jquery.js"></script>
<script	type="text/javascript" src="/weixin-studio/js/question/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/weixin-studio/js/question/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="/weixin-studio/js/question/js/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript"> 
var id = location.href.split("?id=")[1];
$(function(){

			 $.ajax({
				url:"/weixin-studio/question/listoptionQuestion?id="+id,
				dataType:"json",
				type:"get",
				async:false,
				success:function(data){
				if (data.rows) {
				var html = '';
				for (var i=0; i<data.rows.length; i++) {
					var row = data.rows[i];
					
					html += '<tr>';
					html +='<td width="65%" style="text-align:center"><span name="black" style="word-break:break-all;word-wrap:break-word;">选项'+(i+1)+'</span></td>';
					html +='<td width="10%" >'+row.opt+'</td>';
					html +='<td width="10%" ><a style="cursor:pointer" id="'+row.optionid+'" onclick = "update(id)">修改</a></td>';
				  
					html += '</tr>';
				}
				}
				$("#conten").html(html);
					//alert(data.rows[0].questionname);
				},
				error:function(data){
					alert("error");
				}
			});
		});
function update(id){
$.ajax({
				url:"/weixin-studio/question/optionQuestion?id="+id,
				dataType:"json",
				type:"get",
				async:false,
				success:function(data){
				if (data.rows) {
				$("#opt").val(data.rows.opt);
				$("#optionid").val(data.rows.optionid);
				}
				$("#conten").html(html);
					//alert(data.rows[0].questionname);
				},
				error:function(data){
					alert("error");
				}
			});
$("#dialog").dialog({
modal:true //模式对话框，屏蔽
});

}
function save(){
var content = $("#opt").val();
var oid =  $("#optionid").val();
$.ajax({   
       url:"/weixin-studio/question/updateQuestion",
        type: 'POST',   
        async: false,
        data:  {'optionBean.opt':content,'optionBean.optionid':oid},
       dataType: 'json',  
       success:function(data){ 
       if(data.msg =="succes"){
    	  $('#dialog').dialog('close');
          location.reload();
          }
    	   }

});
}
</script>

</head>
<body>
<div id="maincontent">
  <div class="wrap">
    <div class="crumbs">当前位置 &gt;&gt; 修改选项</div>
    <div class="crumbs" ><a class="crumbs" href="javascript:history.go(-1);">向上一页</a></div>
    <hr />
    <form id="data_list" action="/btvinteract/admin/index.php?action=baoliaoobj-delany" method="post">
    <input type="hidden" name="status" value="0" />
    <input type="hidden" name="type" value="zhengji" />
    <div class="infobox"> 
      <h3>问题列表
      <font style="color: #E4393C;font-family: verdana;font-weight:normal; font-size:12px; float:right; padding-right:20px;"></font>
      </h3>
      <div class="content">
        <table class="list_table ">
        	<thead>
        		<tr>
        			<th width="40%">选项序号</th>
        			<th width="40%">选项内容</th>
        			<th width="40%">操作</th>
        		</tr>
        	</thead>
        	<tbody id="conten">
                 
                        <tr>
              <td colspan="4" style="text-align:left">
                <input type="button" value="全选/反选" onclick="checkAll('data_list')" id="chkall" name="chkall" class="regular-button" />
                <input type="button" value="批量删除" name="DelAll" onclick="delall();" class="regular-button" />
              </td>
            </tr>
            </tbody>
          </table>
          <div class="pages_bar"></div>
          <div style="text-align: left;"></div>
        <div class="clear"></div>
      </div>
    </div>
    </form>
  </div>
</div>
<div id="dialog" title="选项修改" style="display:none">
<p>
 <form id ="myform" action="" method="post">
  选项：<input type="text" name="opt" id = "opt" value="" />
  <input type="hidden" name="optionid" id="optionid" value="" />
  <br /><br />
  <input type="button" onclick = "save()" value="保存"/>
 </form>

 </p>
</div>
</body>
</html>
