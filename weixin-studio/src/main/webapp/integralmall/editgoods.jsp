<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

    <title>修改商品信息</title>
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
	<script type="text/javascript" src="/weixin-studio/style/js/switch.js"></script>
  	<script type="text/javascript" src="/weixin-studio/style/js/jquery.uploadify.v2.1.4.min.js"></script>
  	<style type="text/css">
  	tr { height: 45px;}
  	</style>
  	  	<script>
	function add(){
			var goodsName = $("#goodsName").val();
			if(goodsName==''){
				alert('请填写商品名称!');
				return;
			}
			var goodsUnitPoint = $("#goodsUnitPoint").val();
			if(goodsUnitPoint==""){
				alert('请填写商品单价!');
				return;
			}else if(goodsUnitPoint!=''&&isNaN(goodsUnitPoint)){
				alert("商品单价必须为数字，请重新输入！");
				return;
			}
			var inventory = $("#inventory").val();
			if(inventory==""){
				alert('请填写商品库存!');
				return;
			}else if(inventory!=''&&isNaN(inventory)){
				alert("商品库存必须为数字，请重新输入！");
				return;
			}
			$.ajax({
				url : '/weixin-studio/goods/addOneGoods',
				type : 'post',
				async : false,
				data : {'record.goodsName':goodsName,
					    'record.goodsDescript':$("#goodsDescript").val(),
					    'record.goodsTypeID':$("#goodsTypeID").val(),
					    'record.goodsUnitPoint':goodsUnitPoint,
					    'record.inventory':inventory,
					    'record.goodsImageName':$("#goodsImageName").val()
					    },
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
			var goodsName = $("#goodsName").val();
			if(goodsName==''){
				alert('请填写商品名称!');
				return;
			}
			var goodsUnitPoint = $("#goodsUnitPoint").val();
			if(goodsUnitPoint==""){
				alert('请填写商品单价!');
				return;
			}else if(goodsUnitPoint!=''&&isNaN(goodsUnitPoint)){
				alert("商品单价必须为数字，请重新输入！");
				return;
			}
			var inventory = $("#inventory").val();
			if(inventory==""){
				alert('请填写商品库存!');
				return;
			}else if(inventory!=''&&isNaN(inventory)){
				alert("商品库存必须为数字，请重新输入！");
				return;
			}
			/* var live_url = $("#live_url").val();
			if(live_url==''){
				alert('请填写节目播放链接!');
				return;
			} */
			$.ajax({
				url : '/weixin-studio/goods/updateGoods',
				type : 'post',
				async : false,
				data : {'record.goodsID':$("#goodsID").val(),
						'record.goodsName':goodsName,
					    'record.goodsDescript':$("#goodsDescript").val(),
					    'record.goodsTypeID':$("#goodsTypeID").val(),
					    'record.goodsUnitPoint':goodsUnitPoint,
					    'record.inventory':inventory,
					    'record.goodsImageName':$("#goodsImageName").val()
				    	},
				success : function(data){
					if(data.res=='success'){
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
		$(document).ready(function() {
			upload('#fileInput1','/weixin-studio/goods/uploadUpload', '1');
		})
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
			'fileDesc' : 'jpg、gif、jpeg、png类型文件',
			'fileExt' : '*.jpg;*.gif;*.png;*.jpeg',
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
	        	$("#goodsImageName").val(obj.image);
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
	</script>
</head>
<body>

	
	<div style="margin: 15px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<s:if test="record. !=null">
				修改商品信息
			</s:if>
			<s:else>
				添加商品
			</s:else>
		</div>
	</div>
	<div style="padding-left: 30px; padding-top: 5px">
		<form action="" name="editEpg" id="editEpg">
			<div style="display: none;">
				<s:textfield id="goodsID" name="record.goodsID"></s:textfield>
			</div>
		<table style="padding-left: 30px; width: 80%">
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;商品名称：</td>
				<td><s:textfield theme="simple"  name="record.goodsName" id="goodsName" cssStyle="width: 250px;" maxLength="200"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>&nbsp;商品描述：</td>
				<td><s:textfield theme="simple"  name="record.goodsDescript" id="goodsDescript" cssStyle="width: 250px;" maxLength="200"></s:textfield>
				</td>
			</tr>
			<tr>
			<td>&nbsp;商品类型:</td>
			<td>
				<s:select list="#request.goodsTypeList" id="goodsTypeID"                        
				 name="goodsTypeID" theme="simple"  
				 listKey="goodsTypeID" listValue="goodsTypeName"
				  value="record.goodsTypeID"                
					headerKey="" headerValue="请选择">  
				</s:select>  
			</td>
			</tr>
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;商品单价(积分)：</td>
				<td><s:textfield theme="simple"  name="record.goodsUnitPoint" id="goodsUnitPoint" cssStyle="width: 250px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;库存：</td>
				<td><s:textfield theme="simple"  name="record.inventory" id="inventory" cssStyle="width: 250px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>&nbsp;图片地址：</td>
				<td><s:textfield theme="simple"  name="record.goodsImageName" id="goodsImageName" cssStyle="width: 250px;" maxLength="200"></s:textfield>
				</td>
			</tr>
			<tr>
				<td><input type="file" name="fileInput1" id="fileInput1" style="width:156px;"/></td>
				<td>
				<a class="easyui-linkbutton" href="javascript:$('#fileInput1').uploadifyUpload();">上传</a>
				</td>
			</tr>
			<tr style="height: 5px;"></tr>
			<tr>
				<td colspan="2" align="center">
				<s:if test="record.goodsID !=null">
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
