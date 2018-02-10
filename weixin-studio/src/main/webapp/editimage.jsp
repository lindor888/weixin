<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<title>添加或修改图文素材</title>
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
				修改图文素材
			</s:if>
			<s:else>
				添加图文素材
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
			<div>
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="addmore()" style="margin-left:30px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>再加一组新闻</span>
				</a>
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="tiao()" style="margin-left:30px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>挑选素材</span>
				</a>
			</div>
			<div style="height: 5px;"></div>
			<div id="tiao1">
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
					
					&nbsp;&nbsp;排序号:&nbsp;&nbsp;
					<input id="order1" style="width: 150px;" value="1" onblur="checkOrder(this);" \><input type="button" value=" 排序 "/>
					<div style="height: 5px;"></div>
					
					<span style="color: #CC3300"></span>&nbsp;图片地址:
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
				&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title2" id="title2" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					<s:textfield id="desc2"></s:textfield>
				</div>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;url地址:&nbsp;
				<s:textfield name="image.url2" id="url2" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;排序号:&nbsp;&nbsp;
				<input id="order2" style="width: 150px;" value="2" onblur="checkOrder(this);" \><input type="button" value=" 排序 "/>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;图片地址:
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
				&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title3" id="title3" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					<s:textfield id="desc3"></s:textfield>
				</div>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;url地址:&nbsp;
				<s:textfield name="image.url3" id="url3" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;排序号:&nbsp;&nbsp;
				<input id="order3" style="width: 150px;" value="3" onblur="checkOrder(this);" \><input type="button" value=" 排序 "/>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;图片地址:
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
				&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title4" id="title4" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					<s:textfield id="desc4"></s:textfield>
				</div>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;url地址:&nbsp;
				<s:textfield name="image.url4" id="url4" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;排序号:&nbsp;&nbsp;
				<input id="order4" style="width: 150px;" value="4" onblur="checkOrder(this);" \><input type="button" value=" 排序 "/>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;图片地址:
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
				&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title5" id="title5" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					<s:textfield id="desc5"></s:textfield>
				</div>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;url地址:&nbsp;
				<s:textfield name="image.url5" id="url5" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;排序号:&nbsp;&nbsp;
				<input id="order5" style="width: 150px;" value="5" onblur="checkOrder(this);" \><input type="button" value=" 排序 "/>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;图片地址:
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
				&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title6" id="title6" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					<s:textfield id="desc6"></s:textfield>
				</div>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;url地址:&nbsp;
				<s:textfield name="image.url6" id="url6" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;排序号:&nbsp;&nbsp;
				<input id="order6" style="width: 150px;" value="6" onblur="checkOrder(this);" \><input type="button" value=" 排序 "/>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;图片地址:
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
				&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title7" id="title7" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					<s:textfield id="desc7"></s:textfield>
				</div>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;url地址:&nbsp;
				<s:textfield name="image.url7" id="url7" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;排序号:&nbsp;&nbsp;
				<input id="order7" style="width: 150px;" value="7" onblur="checkOrder(this);" \><input type="button" value=" 排序 "/>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;图片地址:
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
				&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title8" id="title8" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					<s:textfield id="desc8"></s:textfield>
				</div>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;url地址:&nbsp;
				<s:textfield name="image.url8" id="url8" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;排序号:&nbsp;&nbsp;
				<input id="order8" style="width: 150px;" value="8" onblur="checkOrder(this);" \><input type="button" value=" 排序 "/>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;图片地址:
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
				&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title9" id="title9" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					<s:textfield id="desc9"></s:textfield>
				</div>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;url地址:&nbsp;
				<s:textfield name="image.url9" id="url9" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;排序号:&nbsp;&nbsp;
				<input id="order9" style="width: 150px;" value="9" onblur="checkOrder(this);" \><input type="button" value=" 排序 "/>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;图片地址:
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
				&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="image.title10" id="title10" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="display:none;">
					<s:textfield id="desc10"></s:textfield>
				</div>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;url地址:&nbsp;
				<s:textfield name="image.url10" id="url10" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;排序号:&nbsp;&nbsp;
				<input id="order10" style="width: 150px;"  value="10" onblur="checkOrder(this);" \><input type="button" value=" 排序 "/>
				<div style="height: 5px;"></div>
				
				&nbsp;&nbsp;图片地址:
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
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="addtxt()" style="margin-left:210px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>添加</span>
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
	</script>
</body>
</html>
