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
				url:"/weixin-studio/question/getquestionStatQuestion?id="+id,
				dataType:"json",
				type:"get",
				async:false,
				success:function(data){
				if (data.rows) {
				var html = '';
				for (var i=0; i<data.rows.length; i++) {
					var row = data.rows[i];
					
					html += '<tr>';
					html +='<td width="10%" ><span name="black" style="word-break:break-all;word-wrap:break-word;">'+row.nickname+'</span></td>';
					html +='<td width="65%" style="text-align:center"><span name="black" style="word-break:break-all;word-wrap:break-word;"><img style="width:35px;height:35px" src="'+row.headimg+'" /></span></td>';
				    html +='<td width="40%" ><span name="black" style="word-break:break-all;word-wrap:break-word;">'+row.optionname+'</span></td>';
				    html +='<td width="40%" ><span name="black" style="word-break:break-all;word-wrap:break-word;">'+row.createtime+'</span></td>';
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

</script>

</head>
<body>
<div id="maincontent">
  <div class="wrap">
    <div class="crumbs">当前位置 &gt;&gt; 答题结果</div>
     <div class="crumbs" ><a class="crumbs" href="javascript:history.go(-1);">向上一页</a></div>
    <hr />
	
    <form id="data_list" action="" method="post">
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
        			<th width="40%">用户昵称</th>
        			<th width="40%">用户头像</th>
        			<th width="40%">选项名称</th>
        			<th width="40%">选择时间</th>
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

</body>
</html>
