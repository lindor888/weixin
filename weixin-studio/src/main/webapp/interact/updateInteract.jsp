<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>修改互动信息</title>
    <meta http-equiv="x-ua-compatible" content="ie=7" />
     <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/js/qtip/css/global.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/public.css" />
    <script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/qtip/jtip.js" type="text/javascript"></script>
    <script type="text/javascript" src="/weixin-studio/style/js/jquery.validate.js"></script>
    <script type="text/javascript" src="/weixin-studio/style/js/swfobject.js"></script>
    <script type="text/javascript" src="/weixin-studio/js/tuwenmaterial/tuwenMaterialOperate.js" ></script>
    <script type="text/javascript" src="/weixin-studio/style/js/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript" src="/weixin-studio/js/upload/upload.js"></script>
	<script language="javascript" src="/weixin-studio/style/My97DatePicker/WdatePicker.js"></script>
    
<style>
table tr{ height: 40px; }
p{ height: 30px; padding-left: 20px; }
.h{ height: 30px}

.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(../images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }

</style>

<script>
	function getParameter(args){
		var url = window.location.href;
		var paraString = url.substring(url.indexOf('?') + 1, url.length).split('&');//截取出url?后面的字符以&的字符
		var paraObj = {};
		for (var i = 0; j = paraString[i]; i++) {
			paraObj[j.substring(0, j.indexOf('=')).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
		}
		var returnValue = paraObj[args.toLowerCase()];
		if (typeof(returnValue) == 'undefined') {
			return "";
		}
		else {
			return returnValue;
		}
	}
	
	var id = getParameter('id');
	function initData(){
		$.ajax({
			url : '/weixin-studio/interact/selectByKeyInteract',
			type : 'post',
			async : false,
			data : {'bean.id':id},
			success : function(data){
				var data = data.rows;
				var nickname = data.nickname;
				var sex = data.sex;
				var country = data.country;
				var province = data.province;
				var city = data.city;
				var headimgurl = data.headimgurl;
				var openId = data.openId;
				
				var content = data.content;
				var image = data.image;
				var updateTimeStr = data.updateTime;
				
				$("#nickname").val(nickname);
				$("#sex").val(sex);
				$("#country").val(country);
				$("#province").val(province);
				$("#city").val(city);
				$("#imageUrl1").val(headimgurl);
				$("#pic1").show();
				$("#pic1").attr('src',headimgurl);
				$("#userList").val(openId);
				$("#openId").val(openId);
				$("#updateTimeStr").val(updateTimeStr.replace('T',' '));
				
				
				if(content!=''){
					$("#content").val(content);
					$("input[name='type']").eq(0).attr("checked",'checked');
					changeType(0);
				}
				
				if(image!=''){
					$("#imageUrl2").val(image);
					$("#pic2").show();
					$("#pic2").attr('src',image);
					$("input[name='type']").eq(1).attr("checked",'checked');
					changeType(1);
				}
				
			}
		});
	}
	
	function updateInteract(){
		var openId = $("#openId").val();
		
		var nickname = $("#nickname").val();
		if(nickname==''){
			alert('请输入昵称');
			return;
		}
		var sex = $("#sex").val();
		var country = $("#country").val();
		var province = $("#province").val();
		var city = $("#city").val();
		var imageUrl1 = $("#imageUrl1").val();
		if(imageUrl1==''){
			alert('请上传头像');
			return;
		}
		
		var type = $("input[name='type']:checked").val();
		var content = "";
		var image = "";
		
		if(type==0){
			content = $("#content").val();
			if(content==''){
				alert('请输入内容');
				return;
			}
		}else if(type==1){
			image = $("#imageUrl2").val();
			if(image==''){
				alert('请上传图片');
				return;
			}
		}
		var updateTimeStr = $("#updateTimeStr").val();
		
		$.ajax({
			url : '/weixin-studio/interact/addOrUpdateInteract',
			type : 'post',
			async : false,
			data : {'bean.openId':openId,'bean.nickname':nickname,'bean.sex':sex,'bean.country':country,'bean.province':province,
				'bean.city':city,'bean.headimgurl':imageUrl1,'bean.content':content,'bean.image':image,'bean.id':id,'bean.updateTimeStr':updateTimeStr,'bean.type':type},
			success : function(data){
				var msg = data.msg;
				if(msg=='success'){
					alert('修改成功');
					window.parent.closeColorBox(false);
				}else{
					alert('修改失败');
				}
			}
		});
	}
	
	function srctool(id) {
		document.getElementById("pic" + id).src = $('#imageUrl'+id).val();
		$("#pic"+id).show();
	}
	
	function changeType(type){
		$("#type"+type).css('display','');
		$("#type"+parseInt(1-type)).css('display','none');
		
	}
	
	function initUserList(){
		$.ajax({
			url : '/weixin-studio/follower/selectMyFollower',
			type : 'post',
			async : false,
			success : function(data){
				var data = data.rows;
				var html = '<option value="">请挑选</option>';
				for(var i=0;i<data.length;i++){
					html += '<option value="'+data[i].openid+'">'+data[i].nickname + '</option>';
				}
				
				$("#userList").html(html);
			}
		});
		
	}
	
	function getUser(id){
		if(id!=''){
			$.ajax({
				url : '/weixin-studio/follower/selectByKeyFollower',
				type : 'post',
				async : false,
				data : {'bean.openid':id},
				success : function(data){
					var data = data.rows;
					var nickname = data.nickname;
					var sex = data.sex;
					var country = data.country;
					var province = data.province;
					var city = data.city;
					var headimgurl = data.headimgurl;
					
					$("#nickname").val(nickname);
					$("#sex").val(sex);
					$("#country").val(country);
					$("#province").val(province);
					$("#city").val(city);
					$("#imageUrl1").val(headimgurl);
					$("#pic1").show();
					$("#pic1").attr('src',headimgurl);
					$("#openId").val(id);
				}
			});
		}else{
			$("#nickname").val('');
			$("#sex").val('男');
			$("#country").val('');
			$("#province").val('');
			$("#city").val('');
			$("#imageUrl1").val('');
			$("#pic1").hide();
			$("#pic1").attr('src','');
			$("#openId").val('');
		}
		
	}
	
	$(function(){
		upload('#fileInput1','/weixin-studio/upload/uploadUploadAction', '1');
		upload('#fileInput2','/weixin-studio/upload/uploadUploadAction', '2');
		initUserList();
		initData(id);
	});
	
	//图片旋转
	function xuanzhuanImage(){
		var imgUrl = $("#imageUrl2").val();
		if(imgUrl==''){
			alert('请先上传图片');
			return;
		}
		$.ajax({
			url : '/weixin-studio/interact/xuanzhuanImageInteract',
			type : 'post',
			async : 'false',
			data : {'bean.image':imgUrl},
			success : function(data){
				var url = data.url;
				$("#pic2").attr('src',url+"?r="+Math.random());
			}
		});
	}
	
</script>
</head>
<body>
<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<strong>添加互动信息</strong>
		</div>
	</div>
<div class="h" >
</div>
	<s:form  method="post" namespace="/interact" action="addInteract"  id="interactFrom"   theme="simple">
		<s:hidden id="openId"></s:hidden>
	<p>&nbsp;<span style="padding-right: 25px;">挑选用户</span>
		<select id="userList" onchange="getUser(this.value)"></select>
	</p>
	<p><i>*</i>&nbsp;<span style="padding-right: 50px;">昵称</span><s:textfield id="nickname" name="bean.nickname" size="30"/></p>
	<p>&nbsp;<span style="padding-right: 60px;">性别</span><s:select list="#{'男':'男','女':'女'}" id="sex" name="bean.sex"></s:select></p>
	<p>&nbsp;<span style="padding-right: 60px;">国家</span><s:textfield id="country" name="bean.country" size="20"/></p>
	<p>&nbsp;<span style="padding-right: 75px;">省</span><s:textfield id="province" name="bean.province" size="20"/></p>
	<p>&nbsp;<span style="padding-right: 75px;">市</span><s:textfield id="city" name="bean.city" size="20"/></p>
	<p>&nbsp;<span style="padding-right: 28px;">更新时间</span>
	<input type="text" id="updateTimeStr" class="text1 Wdate startDate" style="width:150px;" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></p>
	<p>&nbsp;<span style="padding-right: 60px;">头像</span><s:textfield name="bean.headimgurl" id="imageUrl1" maxLength="500" cssStyle="width: 150px;" Onchange="srctool(1)"></s:textfield></p>
	<p>&nbsp;<span style="padding-right: 60px;">
		<input type="file" name="fileInput1" id="fileInput1" width="50" height="30"/>
		<a class="easyui-linkbutton" href="javascript:$('#fileInput1').uploadifyUpload();">上传</a>
		<div style="height: 10px;">
			<div style="position:relative; top:-160px; left:340px; width:150px;">
				<img src="" id="pic1" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(1);" />
			</div>
		</div>
	</p>
	<p style="height:20px"></p>
	<p>
	<input type="radio" value="0" name="type" onclick="changeType(this.value)" checked />内容
	<input type="radio" value="1" name="type" onclick="changeType(this.value)" style="margin-left:15px;"/>图片
	</p>
	<p id="type0">
		&nbsp;<span style="padding-right: 60px;">内容</span>
		<s:textarea id="content" name="bean.content" rows="3" cols="50"></s:textarea>
	</p>
	<div id="type1" style="display:none">
		<p>&nbsp;<span style="padding-right: 60px;">图片</span>
		<s:textfield name="bean.headimgurl" id="imageUrl2" maxLength="500" cssStyle="width: 150px;" Onchange="srctool(1)"></s:textfield>
		<a href="javascript:void(0)" title="向右旋转90度" onclick="xuanzhuanImage()"><img src="/weixin-studio/style/images/rotate.png" /></a>
		</p>
		<p>&nbsp;<span style="padding-right: 60px;">
			<input type="file" name="fileInput2" id="fileInput2" width="50" height="30"/>
			<a class="easyui-linkbutton" href="javascript:$('#fileInput2').uploadifyUpload();">上传</a>
			<div style="height: 10px;">
				<div style="position:relative; top:-160px; left:340px; width:150px;">
					<img src="" id="pic2" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(2);" />
				</div>
			</div>
		</p>
	</div>
	
	<div style="padding-left:500px;"><ul class="button_1" >
			<li><span onclick="updateInteract()">保存</span></li>
			<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
	</ul></div>
	
	</s:form>
  </body>
</html>
