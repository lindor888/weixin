<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<title>图文推送</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/uploadify.css" />
<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
<script type="text/javascript" src="/weixin-studio/style/js/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="/weixin-studio/style/js/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="/weixin-studio/style/js/jquery.colorbox-min.js" ></script>
<script type="text/javascript" src="/weixin-studio/js/waccount/general/general.js" ></script>
<script type="text/javascript" src="/weixin-studio/js/tuwenmaterial/tuwenMaterialOperate.js" ></script>
<script type="text/javascript" src="/weixin-studio/js/tuwenmaterial/sort.js" ></script>
<script type="text/javascript" src="/weixin-studio/style/js/swfobject.js"></script>
<script type="text/javascript" src="/weixin-studio/style/js/switch.js"></script>
<script type="text/javascript" src="/weixin-studio/style/js/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript" src="/weixin-studio/js/upload/upload.js"></script>
<script type="text/javascript" src="/weixin-studio/js/tuwenmaterial/tuwenpush.js"></script>
<style type="text/css">
tr { height: 30px;}
</style>

<script type="text/javascript">

var basePath = '<%=basePath%>';
$(document).ready(function() {
	for ( var j = 0; j < $('#imagevalue').val(); j++) {
		document.getElementById("tiao" + (j + 1)).style.display = 'block';
		document.getElementById("pic" + (j + 1)).style.display = 'block';
		document.getElementById("pic" + (j + 1)).src = $('#imageUrl' + (j + 1)).val();
	}

	upload('#fileInput1','/weixin-studio/upload/uploadUploadAction', '1');
	upload('#fileInput2','/weixin-studio/upload/uploadUploadAction', '2');
	upload('#fileInput3','/weixin-studio/upload/uploadUploadAction', '3');
	upload('#fileInput4','/weixin-studio/upload/uploadUploadAction', '4');
	upload('#fileInput5','/weixin-studio/upload/uploadUploadAction', '5');
	upload('#fileInput6','/weixin-studio/upload/uploadUploadAction', '6');
	upload('#fileInput7','/weixin-studio/upload/uploadUploadAction', '7');
	upload('#fileInput8','/weixin-studio/upload/uploadUploadAction', '8');
	upload('#fileInput9','/weixin-studio/upload/uploadUploadAction', '9');
	upload('#fileInput10','/weixin-studio/upload/uploadUploadAction', '10');
});
</script>
</head>
<body>
	<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<s:if test="gm.id !=null&&image!=null">
				图文推送
			</s:if>
			<s:else>
				图文推送
			</s:else>
		</div>
	</div>
	<div style="padding-left: 50px;">
		<form action="" name="editKeywords" id="editKeywords">
			<div style="display: none;">
				<s:textfield id="textId" name="gm.id"></s:textfield>
				<s:textfield id="waccount_id" name="gm.waccount_id"></s:textfield>
				<s:textfield id="imagevalue" name="image.i"></s:textfield>
			</div>
			<!-- 
		<table border="0" cellspacing="0">
			<tr>
				<td width="100"><span style="color: #CC0000;">*</span>&nbsp;列表标题:</td>
				<td><s:textfield theme="simple" name="gm.catalogTitle" id="catalogTitle" cssStyle="width: 300px;"></s:textfield></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;精确关键词:</td>
				<td><s:textfield theme="simple" name="gm.keyword1" id="keyword1" cssStyle="width: 300px;"></s:textfield>
					<span style="font-size:12px;">(多个关键词之间请用半角逗号隔开)</span></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;模糊关键词:</td>
				<td><s:textfield theme="simple" name="gm.keyword0" id="keyword0" cssStyle="width: 300px;"></s:textfield>
				<span style="font-size:12px;">(多个关键词之间请用半角逗号隔开)</span></td>
			</tr>
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;态:</td>
				<td>
					<s:if test="gm.status ==0">
						<input type="radio" value="1" name="status" />启用 &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="0" name="status" checked="checked" />停用
					</s:if>
					<s:else>
						<input type="radio" value="1" name="status" checked="checked" />启用 &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="0" name="status" />停用
					</s:else>
				</td>
			</tr>
		</table>
		 -->
			<div>
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="addmore()" style="margin-left:30px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>再加一组新闻</span>
				</a>
				<!-- 
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="tiao()" style="margin-left:30px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>挑选素材</span>
				</a>
				 -->
			</div>
			<div style="height: 5px;"></div>
			<div style="display: block;" id="tiao1">
				<div>
					<span style="font-weight: bold;">【头条】</span>
					<div style="height: 5px;"></div>
					
					<span style="color: #CC3300">*</span>&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
					<s:textfield name="image.title1" id="title1" maxLength="500" cssStyle="width: 250px;"></s:textfield>
					<div style="height: 5px;"></div>
					
					<span style="color: #CC3300">*</span>&nbsp;描&nbsp;&nbsp;&nbsp;&nbsp;述:
					<s:textfield name="image.desc1" id="desc1" maxLength="500" cssStyle="width: 250px;"></s:textfield>
					<div style="height: 5px;"></div>
					
					<span style="color: #CC3300">*</span>&nbsp;url地址:&nbsp;
					<s:textfield name="image.url1" id="url1" maxLength="500" cssStyle="width: 250px;"></s:textfield>
					<div style="height: 5px;"></div>
					<div style="height: 5px;"></div>
					
					<span style="color: #CC3300">*</span>&nbsp;图片地址:
					<s:textfield name="image.imageUrl1" id="imageUrl1" maxLength="500" cssStyle="width: 150px;" Onchange="srctool(1)"></s:textfield>
					<div style="height: 5px;"></div>
					
					<input type="file" name="fileInput1" id="fileInput1" />
					<a class="easyui-linkbutton" href="javascript:$('#fileInput1').uploadifyUpload();">上传</a>
					<div style="height: 5px;"></div>
				</div>
					
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic1" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(1);" />
					</div>
				</div>
			</div>
			<div style="display: none;" id="tiao2">
				【第二条】<div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title2" id="title2" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;url地址:&nbsp;
				<s:textfield name="image.url2" id="url2" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;描&nbsp;&nbsp;&nbsp;&nbsp;述:
					<s:textfield name="image.desc2" id="desc2" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;图片地址:
				<s:textfield name="image.imageUrl2" id="imageUrl2" maxLength="500" cssStyle="width: 250px;" Onchange="srctool(2)"></s:textfield>
				<div style="height: 5px;"></div>
				
				<input type="file" name="fileInput2" id="fileInput2" />
				<a class="easyui-linkbutton" href="javascript:$('#fileInput2').uploadifyUpload();">上传</a>
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic2" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(2);" />
					</div>
				</div>
			</div>
			
			<div style="display: none;" id="tiao3">
				【第三条】 <div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title3" id="title3" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;url地址:&nbsp;
				<s:textfield name="image.url3" id="url3" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;描&nbsp;&nbsp;&nbsp;&nbsp;述:
					<s:textfield name="image.desc3" id="desc3" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;图片地址:
				<s:textfield name="image.imageUrl3" id="imageUrl3" maxLength="500" cssStyle="width: 150px;" Onchange="srctool(3)"></s:textfield>
				<div style="height: 5px;"></div>
				
				<input type="file" name="fileInput3" id="fileInput3" />
				<a class="easyui-linkbutton" href="javascript:$('#fileInput3').uploadifyUpload();">上传</a>
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic3" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(3);" />
					</div>
				</div>
			</div>
			<div style="display: none;" id="tiao4">
				【第四条】 <div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title4" id="title4" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;url地址:&nbsp;
				<s:textfield name="image.url4" id="url4" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;描&nbsp;&nbsp;&nbsp;&nbsp;述:
					<s:textfield name="image.desc4" id="desc4" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;图片地址:
				<s:textfield name="image.imageUrl4" id="imageUrl4" maxLength="500" cssStyle="width: 250px;" Onchange="srctool(4)"></s:textfield>
				<div style="height: 5px;"></div>
				
				<input type="file" name="fileInput4" id="fileInput4" />
				<a class="easyui-linkbutton" href="javascript:$('#fileInput4').uploadifyUpload();">上传</a>
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic4" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(4);" />
					</div>
				</div>
			</div>


			<div style="display: none;" id="tiao5">
				【第五条】 <div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title5" id="title5" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;url地址:&nbsp;
				<s:textfield name="image.url5" id="url5" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;描&nbsp;&nbsp;&nbsp;&nbsp;述:
					<s:textfield name="image.desc5" id="desc5" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;图片地址:
				<s:textfield name="image.imageUrl5" id="imageUrl5" maxLength="500" cssStyle="width: 250px;" Onchange="srctool(5)"></s:textfield>
				<div style="height: 5px;"></div>
				
				<input type="file" name="fileInput5" id="fileInput5" />
				<a class="easyui-linkbutton" href="javascript:$('#fileInput5').uploadifyUpload();">上传</a>
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic5" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(5);" />
					</div>
				</div>
			</div>

			<div style="display: none;" id="tiao6">
				【第六条】<div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title6" id="title6" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;url地址:&nbsp;
				<s:textfield name="image.url6" id="url6" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;描&nbsp;&nbsp;&nbsp;&nbsp;述:
					<s:textfield name="image.desc6" id="desc6" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;图片地址:
				<s:textfield name="image.imageUrl6" id="imageUrl6" maxLength="500" cssStyle="width: 250px;" Onchange="srctool(6)"></s:textfield>
				<div style="height: 5px;"></div>
				
				<input type="file" name="fileInput6" id="fileInput6" />
				<a class="easyui-linkbutton" href="javascript:$('#fileInput6').uploadifyUpload();">上传</a>
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic6" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(6);" />
					</div>
				</div>
			</div>

			<div style="display: none;" id="tiao7">
				【第七条】 <div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title7" id="title7" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;url地址:&nbsp;
				<s:textfield name="image.url7" id="url7" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;描&nbsp;&nbsp;&nbsp;&nbsp;述:
					<s:textfield name="image.desc7" id="desc7" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;图片地址:
				<s:textfield name="image.imageUrl7" id="imageUrl7" maxLength="500" cssStyle="width: 250px;" Onchange="srctool(7)"></s:textfield>
				<div style="height: 5px;"></div>
				
				<input type="file" name="fileInput7" id="fileInput7" />
				<a class="easyui-linkbutton" href="javascript:$('#fileInput7').uploadifyUpload();">上传</a>
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic7" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(7);" />
					</div>
				</div>
			</div>

			<div style="display: none;" id="tiao8">
				【第八条】<div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title8" id="title8" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
				
				</div>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;url地址:&nbsp;
				<s:textfield name="image.url8" id="url8" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;描&nbsp;&nbsp;&nbsp;&nbsp;述:
					<s:textfield name="image.desc8" id="desc8" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;图片地址:
				<s:textfield name="image.imageUrl8" id="imageUrl8" maxLength="500" cssStyle="width: 250px;" Onchange="srctool(8)"></s:textfield>
				<div style="height: 5px;"></div>
				
				<input type="file" name="fileInput8" id="fileInput8" />
				<a class="easyui-linkbutton" href="javascript:$('#fileInput8').uploadifyUpload();">上传</a>
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic8" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(8);" />
					</div>
				</div>
			</div>

			<div style="display: none;" id="tiao9">
				【第九条】<div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title9" id="title9" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;url地址:&nbsp;
				<s:textfield name="image.url9" id="url9" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;描&nbsp;&nbsp;&nbsp;&nbsp;述:
					<s:textfield name="image.desc9" id="desc9" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;图片地址:
				<s:textfield name="image.imageUrl9" id="imageUrl9" maxLength="500" cssStyle="width: 250px;" Onchange="srctool(9)"></s:textfield>
				<div style="height: 5px;"></div>
				
				<input type="file" name="fileInput9" id="fileInput9" />
				<a class="easyui-linkbutton" href="javascript:$('#fileInput9').uploadifyUpload();">上传</a>
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic9" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(9);" />
					</div>
				</div>
			</div>

			<div style="display: none;" id="tiao10">
				【第十条】 <div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title10" id="title10" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;url地址:&nbsp;
				<s:textfield name="image.url10" id="url10" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;描&nbsp;&nbsp;&nbsp;&nbsp;述:
					<s:textfield name="image.desc10" id="desc10" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				<span style="color: #CC3300">*</span>&nbsp;图片地址:
				<s:textfield name="image.imageUrl10" id="imageUrl10" maxLength="500" cssStyle="width: 250px;" Onchange="srctool(10)"></s:textfield>
				<div style="height: 5px;"></div>
				
				<input type="file" name="fileInput10" id="fileInput10" />
				<a class="easyui-linkbutton" href="javascript:$('#fileInput10').uploadifyUpload();">上传</a>
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic10" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(10);" />
					</div>
				</div>
			</div>


			<s:if test="gm.id !=null">
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="updatetxt()" style="margin-left:210px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>修改</span>
				</a>
			</s:if>
			<s:else>
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="push()" style="margin-left:210px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>推送</span>
				</a>
			</s:else>
			<a class='easyui-linkbutton l-btn l-btn-plain' onclick="quxiao()" style="margin-left:80px;">
			<span class='l-btn-text icon-cancel' style='padding-left: 20px;'>取消</span></a>
		
		</form>
	</div>
	<script>
		function tiao() {
			$.fn.colorbox({
				iframe : true,
				innerWidth : 690,
				innerHeight : 440,
				href : '/weixin-studio/relatedQuery.jsp',
				overlayClose : false
			});
		}

		function srctool(id) {
			document.getElementById("pic" + id).src = $('#imageUrl' + id).val();
		}
		function push(){
        //debugger;
		var b = true;
	    for(var i=1;i<11;i++){
		var value=$('#tiao'+i).attr('style');
		    if(value.indexOf("block") > -1){
		     var title = $('#title'+i).val();
		     var url = $('#url'+i).val();      
		     var desc = $('#desc'+i).val();	       
		     var imageUrl = $('#imageUrl'+i).val();
		     if(title =="" || url == "" || desc == "" || imageUrl == ""){
		      $.messager.alert("提示","有必填项未填！");
		      b = false;
		       break;
		     } 
		    
		    }
			
	}
	if(b){
	var param={};	
	var title1=$.trim($('#title1').val());
	var url1=$.trim($('#url1').val());
	var desc1=$.trim($('#desc1').val());
	var imageUrl1=$.trim($('#imageUrl1').val());
	
	
	var title2=$.trim($('#title2').val());
	var url2=$.trim($('#url2').val());
	var imageUrl2=$.trim($('#imageUrl2').val());
	var desc2=$.trim($('#desc2').val());
	
	var title3=$.trim($('#title3').val());
	var url3=$.trim($('#url3').val());
	var imageUrl3=$.trim($('#imageUrl3').val());
	var desc3=$.trim($('#desc3').val());
	
	var title4=$.trim($('#title4').val());
	var url4=$.trim($('#url4').val());
	var imageUrl4=$.trim($('#imageUrl4').val());
	var desc4=$.trim($('#desc4').val());

	var title5=$.trim($('#title5').val());
	var url5=$.trim($('#url5').val());
	var imageUrl5=$.trim($('#imageUrl5').val());
	var desc5=$.trim($('#desc5').val());
	
	var title6=$.trim($('#title6').val());
	var url6=$.trim($('#url6').val());
	var imageUrl6=$.trim($('#imageUrl6').val());
    var desc6=$.trim($('#desc6').val());
	
	var title7=$.trim($('#title7').val());
	var url7=$.trim($('#url7').val());
	var imageUrl7=$.trim($('#imageUrl7').val());
	var desc7=$.trim($('#desc7').val());
	
	
	var title8=$.trim($('#title8').val());
	var url8=$.trim($('#url8').val());
	var imageUrl8=$.trim($('#imageUrl8').val());
	var desc8=$.trim($('#desc8').val());
	
	var title9=$.trim($('#title9').val());
	var url9=$.trim($('#url9').val());
	var imageUrl9=$.trim($('#imageUrl9').val());
	var desc9=$.trim($('#desc9').val());
	
	var title10=$.trim($('#title10').val());
	var url10=$.trim($('#url10').val());
	var imageUrl10=$.trim($('#imageUrl10').val());
	var desc10=$.trim($('#desc10').val());
	
	
		param={
				"image.title1":title1,"image.url1":url1,"image.imageUrl1":imageUrl1,"image.desc1":desc1,
				"image.title2":title2,"image.url2":url2,"image.imageUrl2":imageUrl2,"image.desc2":desc2,
				"image.title3":title3,"image.url3":url3,"image.imageUrl3":imageUrl3,"image.desc3":desc3,
				"image.title4":title4,"image.url4":url4,"image.imageUrl4":imageUrl4,"image.desc4":desc4,
				"image.title5":title5,"image.url5":url5,"image.imageUrl5":imageUrl5,"image.desc5":desc5,
				"image.title6":title6,"image.url6":url6,"image.imageUrl6":imageUrl6,"image.desc6":desc6,
				"image.title7":title7,"image.url7":url7,"image.imageUrl7":imageUrl7,"image.desc7":desc7,
				"image.title8":title8,"image.url8":url8,"image.imageUrl8":imageUrl8,"image.desc8":desc8,
				"image.title9":title9,"image.url9":url9,"image.imageUrl9":imageUrl9,"image.desc9":desc9,
				"image.title10":title10,"image.url10":url10,"image.imageUrl10":imageUrl10,"image.desc10":desc10,
			
				
				};
		$.ajax({   
				url:'/weixin-studio/graphicPush/graphicpushAction?random='+Math.round(Math.random()*10000),
		        type: 'POST',   
		        async: false,
		        data:  param,
		       dataType: 'json',  
		       success:function(data){ 
		           if(data == null){
		               $.messager.alert("提示","发送失败！");
		           }else if(data.message=="success"){		    		   
		    		   $.messager.alert("提示","发送成功！");	    		  
		    	   }else{
		    	       $.messager.alert("提示","发送失败！");
		    	   }
		       }
		    });
	}
	}
	</script>
</body>
</html>
