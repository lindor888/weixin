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
<script type="text/javascript" src="btvinteract/admin/js/html5media.min.js"></script>

<script>
$(function () {
    $("#endtime").datetimepicker({
        showSecond: true,
        timeFormat: 'hh:mm:ss',
        stepHour: 1,
        stepMinute: 1,
        stepSecond: 1
    });
    $("#votetitle").keyup(function(){
    	if($("#votetitle").val().length <= 28){
    		$("#votetitle_notice").html("还可以输入"+ (28-$("#votetitle").val().length) +"字");
    	}else{
    		$("#votetitle_notice").html("已超过"+ ($("#votetitle").val().length-28) +"字");
    	}
    });
    /*$("#votequestion").keyup(function(){
    	if($("#votequestion").val().length <= 28){
    		$("#votequestion_notice").html("还可以输入"+ (28-$("#votequestion").val().length) +"字");
    	}else{
    		$("#votequestion_notice").html("已超过"+ ($("#votequestion").val().length-28) +"字");
    	}
    });*/
    $("#votebrief").keyup(function(){
    	if($("#votebrief").val().length <= 28){
    		$("#votebrief_notice").html("还可以输入"+ (45-$("#votebrief").val().length) +"字");
    	}else{
    		$("#votebrief_notice").html("已超过"+ ($("#votebrief").val().length-45) +"字");
    	}
    });
});
function check_form(){
    
	if(document.getElementById('votetitle').value==''){
		alert('请填写 标题！');
		document.getElementById('votetitle').focus();
		return false;
	}
	if(document.getElementById('votetitle').value.length>28){
		alert('标题不得超过28个字符显示！');
		document.getElementById('votetitle').focus();
		return false;
	}
	/*if(document.getElementById('voteimage').value==''){
	    alert('请选择 爆料LOGO！');
	    return false;
	}*/
	
	if(document.getElementById('votebrief').value.length>45){
		alert('爆料简介不能超过45个字符');
		document.getElementById('votebrief').focus();
		return false;
	}
	if($(':hidden[name=id]').val() == ''){
	if(document.getElementById('activity_name').value != '' || document.getElementById('activity_unitscore').value != ''){
		if(document.getElementById('activity_name').value == '' || document.getElementById('activity_unitscore').value == ''){
			return false;
		}
	}
	}
    var votetitle= $('#votetitle').val();
	if(votetitle != ''){
		/* $.get('/btvinteract/admin/index.php?action=baoliaoobj-checktitle',{title:votetitle}, function(str) {
			if(str=="y"){
				alert("爆料标题 已经存在！");
				return false;
			}else{
				document.form.submit();  
			}        
		}); */
		document.form.submit();
	}
}
</script>
</head>
<body>
<div id="maincontent">
  <div class="wrap">
    <div class="crumbs">当前位置 &gt;&gt; <a	href="/btvinteract/admin/index.php?action=baoliaoobj-list&type=zhengji">征集线索管理</a>&gt;&gt;添加征集线索信息</div>
    <hr />
    <div class="infobox">
      <div class="content">
        <form name="form" id="form" method="post"  enctype="multipart/form-data" action="/weixin-studio/hudong/addtitleHudong">
          <input type="hidden" name="id" value="0"/>
          <input type="hidden" name="type" value="zhengji"/>
             <table class="form-table">
            <tr>
              <th width="90">征集线索标题<span style="color:#F00">*</span></th>
              <td><input type="text" name="baoliaoObject.title" id="votetitle" style="width:305px; height:20px;" value="" />&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3c3c3c" id="votetitle_notice"></font></td>
            </tr>
            <tr>
              <th width="90">LOGO<span style="color:#F00">*</span></th>
              <td><input type="file" name="fileInput" id="voteimage" value="" onchange="showpic(this,'img1');"/>
                <div id="pic1" class="img"></div>
                <img src="" alt="" width="143" height="143" id="img1" style="display:none" /></td>
            </tr>  
           
            <tr>
              <th width="90">发布状态<span style="color:#F00">*</span></th>
              <td><input type="radio" name="baoliaoObject.is_show" value="2"  checked="checked"  />未发布&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="baoliaoObject.is_show" value="1" />发布
              </td>
            </tr>
            <!--tr>
	            <th>爆料类型</th>
	            <td>
	            	<select  name="type">
						<option value="zhengji"  <>>征集线索</option>
						<option value="news"  <>>新闻线索</option>
					</select>
	            </td>
            </tr-->
            <tr>
              <th width="90">征集线索简介</th>
              <td><textarea name="baoliaoObject.b_desc" style="width: 305px; height: 131px;" id="votebrief"></textarea>&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3c3c3c" id="votebrief_notice"></font></td>
            </tr>
                        
            <table id="jfset" style="display:none" class="form-table">
	            <tr>
	              <th width="90">活动名称<span style="color:#F00">*</span></th>
	              <td><input type="text" name="activity_name" id="activity_name" style="width:200px; height:20px;" value="" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            </tr>
	            <tr>
	              <th width="90">单次分值<span style="color:#F00">*</span></th>
	              <td><input type="text" name="activity_unitscore" id="activity_unitscore" style="width:30px; height:20px;" value="" />&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3c3c3c" id="topictitle_notice"></font></td>
	            </tr>
	            <tr>
	              <th width="90">分值计算类型<span style="color:#F00">*</span></th>
	              <td>
	              <select name="activity_plusorsubtract" id="activity_plusorsubtract">
	              	<option value="1">加积分</option>
	              	<option value="0">减积分</option>
	              </select>
	              </td>
	            </tr>
	            <tr>
	              <th width="90">活动说明<span style="color:#F00"></span></th>
	              <td><textarea name="activitycontent" style="width: 305px; height: 131px;"></textarea></td>
	            </tr>
	            </table>
	                       <tr>
              <th width="90"></th>
              <td><input type="button" value="确定" name="add"  class='btn btn-success' onclick="check_form();" /></td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">	 
function showpic(file,img){
	var dFile = document.getElementById(file.id);
	var dImg = document.getElementById(img);
	var dInfo = document.getElementById('img');
	if(!dFile.value.match(/.jpg|.gif|.png|.bmp/i)){
   		alert('图片类型必须是: .jpg, .gif, .bmp or .png !');
   		return;
	}
	if(dFile.files){
		dImg.style.display='block';
  		dImg.src = window.URL.createObjectURL(dFile.files[0]);  
	}else{
		/*这步骤是用来在ie6,ie7中显示图片的*/
		var from=img.indexOf('g')+1;
		var to=img.length;
		var pic='pic'+img.substring(from,to);
		var newPreview = document.getElementById(pic);
		newPreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dFile.value;
		dImg.style.display='none';
     	newPreview.style.width = "155px";
     	newPreview.style.height = "105px";
    }
}
function showjfset(){
	var dis = $("#jfset").css("display");
	if(dis == 'none'){
		$("#jfset").show();
	}else{
		$("#jfset").hide();
	}
}
/*function SelectTopic(channelid){
	$("#Topicselect").empty();
	var channelid = $("#Channelselect option:selected").val();
	$.get('/btvinteract/admin/index.php?action=vote-getTopicList',{channelid:channelid}, function(str) {
		if (str != ''){
			var obj = eval(str);
			for(var i = 0; i < obj.length; i++ )
			{
				var option = "<option value='" + obj[i].TOPIC_ID + "'>" + obj[i].NAME + "</option>";
				$("#Topicselect").append(option);
			}
		}
	});
}
window.onload=SelectTopic();//载入时先查询一下当前台对应的专题*/
</script>
</body>
</html>