<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

    <title>修改节目单</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
  	<script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/js/waccount/general/general.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/qtip/jtip.js" type="text/javascript"></script>
  	<script type="text/javascript" src="/weixin-studio/style/js/swfobject.js"></script>
  	<script type="text/javascript" src="/weixin-studio/style/js/jquery.uploadify.v2.1.4.min.js"></script>
  	<script type="text/javascript" src="/weixin-studio/style/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="/weixin-studio/style/js/switch.js"></script>
  	<style type="text/css">
  	tr { height: 45px;}
  	</style>
  	  	<script>
  	  $(document).ready(function() {
  			upload('#fileInput1','/weixin-studio/epg/videoUploadAction', '1');
  		});
  		function upload(element,url,index){
  			var sessionIda = getCookie("JSESSIONID");
  			$(element).uploadify({
  		    	'uploader'       : '/weixin-studio/style/js/uploadify.swf',  
  				'script'         : url+';jsessionid='+sessionIda,
  				'cancelImg'      : '/weixin-studio/style/images/cancel.png', 
  				'buttonImg' 	 : '/weixin-studio/style/images/upload.jpg', 
  		        'fileDataName'   : 'fileInput',  
  		        // 'queueID': '', 
  		        'method'         : 'POST',
  		      'sizeLimit'      : 13073741824,
  				'width': 50,
  				'height': 30,  
  				'fileDesc' : 'mp4、rmvb、avi类型文件',
  				'fileExt' : '*.mp4;*.rmvb;*.avi',
  		        'auto': false,
  		        'multi': false,
  		        'simUploadLimit' : 1,  
  		        //'buttonText': "上传",  
  		        //'displayData': 'percentage',  
  		        'onSelectOnce' :function(event,data){
  		        	        	       	        	       		
  				}, 
  				'onSelect':function(event,queueID,fileObject){
  					
  				},
  				'onError':function(event,queueId,fileObj,errorObj){
  					
  				},  
  		        'onComplete': function (event, queueID, fileObj, response, data){
  		        	/* uploadComplete(response, index); */
  		        	var obj = JSON.parse(response);
  		        	if(obj.status == 0){
  		        		$("#live_url").val(obj.webUrl);
  		        	}
  		        },
  				'onAllComplete'  : function(event,data){
  				} 
  			}); 
  		}
  		function getCookie(name){
  			var strcookie=document.cookie;
  			var arrcookie=strcookie.split("; ");
  			for(var i=0;i<arrcookie.length;i++){
  				var arr=arrcookie[i].split("=");
  				if(arr[0]==name)
  				return unescape(arr[1]);
  			}
  			return "";
  		} 
		function add(){
			var program_name = $("#program_name").val();
			if(program_name==''){
				alert('请填写节目名称!');
				return;
			}
			var start_time = $("#start_time").val();
			if(start_time==''){
				alert('请填写节目开始时间!');
				return;
			}
			var end_time = $("#end_time").val();
			if(end_time==''){
				alert('请填写节目结束时间!');
				return;
			}
			var live_url = $("#live_url").val();
			if(live_url==''){
				alert('请填写节目播放链接!');
				return;
			}
			
			$.ajax({
				url : '/weixin-studio/epg/addReservationEpg',
				type : 'post',
				async : false,
				data : {'record.program_name':program_name,'record.start_time':start_time,'record.end_time':end_time,'record.live_url':live_url},
				success : function(data){
					if(data.res=='success'){
						alert('添加成功');
						window.parent.closeColorBox(false);
					}else{
						alert('添加失败');
					}
				}
			});
		}
		function update(){
			var program_name = $("#program_name").val();
			if(program_name==''){
				alert('请填写节目名称!');
				return;
			}
			var start_time = $("#start_time").val();
			if(start_time==''){
				alert('请填写节目开始时间!');
				return;
			}
			var end_time = $("#end_time").val();
			if(end_time==''){
				alert('请填写节目结束时间!');
				return;
			}
			var live_url = $("#live_url").val();
			if(live_url==''){
				alert('请填写节目播放链接!');
				return;
			}
			$.ajax({
				url : '/weixin-studio/epg/updateEpg',
				type : 'post',
				async : false,
				data : {'record.reservation_id':$('#reservation_id').val(),'record.program_name':program_name,'record.start_time':start_time,'record.end_time':end_time,'record.live_url':live_url},
				success : function(data){
					if(data.msg=='success'){
						alert('修改成功');
						window.parent.closeColorBox(false);
					}else{
						alert('操作失败');
					}
				}
			});
		}
		function quxiao(){
			window.parent.closeColorBox(false);
		}
		
	</script>
</head>
<body>

	
	<div style="margin: 15px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<s:if test="record. !=null">
				修改文本素材
			</s:if>
			<s:else>
				添加文本素材
			</s:else>
		</div>
	</div>
	<div style="padding-left: 30px; padding-top: 5px">
		<form action="" name="editEpg" id="editEpg">
			<div style="display: none;">
				<s:textfield id="reservation_id" name="record.reservation_id"></s:textfield>
			</div>
		<table style="padding-left: 30px; width: 80%">
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;节目名称：</td>
				<td><s:textfield theme="simple"  name="record.program_name" id="program_name" cssStyle="width: 250px;" maxLength="200"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>&nbsp;开始时间：</td>
				<td><s:textfield theme="simple"  id="start_time" maxLength="500"  class="Wdate" cssStyle="width: 182px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readonly:true,isShowClear:true})" >
				<s:param name="value" > <s:date name="record.start_time" format="yyyy-MM-dd HH:mm:ss" /></s:param>
				</s:textfield>
				</td>
			</tr>
			<tr>
				<td>&nbsp;结束时间：</td>
				<td><s:textfield theme="simple"  name="record.end_time" id="end_time" maxLength="500" class="Wdate" cssStyle="width: 182px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readonly:true,isShowClear:true})" >
					<s:param name="value" > <s:date name="record.end_time" format="yyyy-MM-dd HH:mm:ss" /></s:param>
				</s:textfield>
				</td>
			</tr>
			<s:if test="record ==null">
			<tr>
				<td>&nbsp;请选择文件：</td>
				<td>
					<input type="file" name="fileInput1" id="fileInput1"  width="50" height="30"/>
					<a class="easyui-linkbutton"  href="javascript:$('#fileInput1').uploadifyUpload();">上传</a>
				</td>
			</tr> 
			</s:if>
			<tr>
				<td>&nbsp;播放链接：</td>
				<td><s:textfield theme="simple"   name="record.live_url" id="live_url" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				</td>
			</tr>
			<tr style="height: 20px;"></tr>
			<tr>
				<td colspan="2" align="center">
				<s:if test="record.reservation_id !=null">
					<a class='easyui-linkbutton l-btn l-btn-plain' onclick="update()">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>修改</span>
					</a>&nbsp;&nbsp;
				</s:if>
				<s:else>
					<a class='easyui-linkbutton l-btn l-btn-plain' onclick="add()">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>添加</span>
					</a>&nbsp;&nbsp;
				</s:else>
				 <a class='easyui-linkbutton l-btn l-btn-plain' onclick="quxiao()"><span class='l-btn-text icon-cancel' style='padding-left: 20px;'>取消</span></a>
				</td>
			</tr>
		</table>
	</form>
	</div>
	
</body>
</html>
