<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>生成二维码</title>
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
     var imageurl;
	function shengcheng(){
		
		var val = $("#type").val();
		var qrname = $("#qrname").val();
		var content = val.split("?type=")[0];
		var type = val.split("?type=")[1];
		var date = new Date();
		if(qrname =='' || qrname == null){
		alert("请添加名称");
		
		return;
		}
		if(type =='1'){
		  //param = "Qrco" + date.getTime();
		  //content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfab418ef0ef51787&redirect_uri=http://www.4uplus.com/weixinopen/reports&response_type=code&scope=snsapi_base&state="+param+"#wechat_redirect";
		}
		if(type =='2'){
		 // param = date.getTime();
		  //content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfab418ef0ef51787&redirect_uri=http://www.4uplus.com/weixinopen/reports&response_type=code&scope=snsapi_base&state="+param+"#wechat_redirect";
		}
		$.ajax({
			url : '/weixin-studio/interact/qrcodeInteract',
			type : 'post',
			async : false,
			//data : {'content':content,'qrtype':type},
			data : {'qrtype':type,'qrname':qrname},
			success : function(data){
				var msg = data.msg;
				if(msg=='success'){
					alert('生成成功');
					window.parent.closeColorBox(false);
			     imageurl=data.imgurl;
			     srctool();
				}else{
					alert('生成失败');
				}
			}
		});
	}
	
	function srctool() {
	
		document.getElementById("pic1").src = imageurl;
		$("#pic1").show();
	}
	
	
	
	function downloadServlet(){
       var imgname = document.getElementById("pic1").src.split("WEIXIN")[1];
      
       if(imgname !="" && imgname != null){
       window.location="http://localhost:8080/weixin-studio/downloadServlet.do?imgname="+imgname;	
	   }else{
	   alert("请生成二维码！");
	   }
	}
	
	
	
	
	
	
	
	
</script>
</head>
<body>
<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<strong>生成二维码</strong>
		</div>
	</div>
<div class="h" >
</div>
	<form  method="post"  action=""  id="interactFrom" >
		<s:hidden id="openId"></s:hidden>
	<p>&nbsp;<span style="padding-right: 60px;">二维码类型</span>
	
	<select  name="type" id="type">
	<option selected value='http://www.baidu.com?type=1'>签到二维码</option>
	<option value='http://www.qq.com?type=2'>抽奖二维码</option>  
	</select><br /><br />
	&nbsp;<span style="padding-right: 60px;">二维名称：</span>
	<input type="text" name="qrname" id="qrname" maxLength="500" cssStyle="width: 150px;" />
	</p>
	<br /><br />
	<p>&nbsp;<span style="padding-right: 60px;"></span></br></br></br>
	        <div style="position:relative;">
				<img src="" id="pic1" style="display: none; margin-left:30px;" width="200" height="200" onclick="showImg(1);" />
			</div>
			</br>
		<a style="position:relative;" class="easyui-linkbutton" onclick="shengcheng()">生成</a>
	
			
	</p>
	<p style="height:20px"></p>
	
	<input type="hidden" value="" id="qrcodeID"/>
	<div style="padding-left:500px;"><ul class="button_1" >
			<li><span onclick="downloadServlet()">下载</span></li>
	</ul></div>
	
	</form>
  </body>
</html>
