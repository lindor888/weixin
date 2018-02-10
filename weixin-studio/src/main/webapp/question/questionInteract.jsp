<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<title>添加问卷</title>
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
<script type="text/javascript" src="/weixin-studio/js/question/question.js"></script>
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
			<p>问卷标题</p>
			
		</div>
	</div>
	<div style="padding-left: 50px;">
		<form action="" name="editKeywords" id="editKeywords">
			<div style="display: none;">
				<s:textfield id="textId" name="gm.id"></s:textfield>
				<s:textfield id="waccount_id" name="gm.waccount_id"></s:textfield>
				<s:textfield id="imagevalue" name="image.i"></s:textfield>
			</div>
			<div>
			<span style="color: #CC3300">*</span>&nbsp;问卷标题:
			<s:textfield name="headtitleBean.headtitlename" id="headtitlename" maxLength="500" cssStyle="width: 250px;"></s:textfield>
			类型：<select id = "headtitletype">
				  <option value ="单选">单选</option>
				  <option value ="多选">多选</option>
				</select>
			</div>
			<br />
			<div>
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="addmore()" style="margin-left:30px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>再加一组答题</span>
				</a>
				<!-- 
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="tiao()" style="margin-left:30px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>挑选素材</span>
				</a>
				 -->
			</div>
			<div style="height: 5px;"></div>
			<div style="display: block;" id="tiao0">
				<div>
					<span>【问题一】</span>
					<div style="height: 5px;"></div>
					
					<span style="color: #CC3300">*</span>&nbsp;问&nbsp;&nbsp;&nbsp;&nbsp;题:
					<s:textfield name="questionBeans.questionname0" id="questionname0" maxLength="500" cssStyle="width: 250px;"></s:textfield>
					<a class='easyui-linkbutton l-btn l-btn-plain' id ="opt" onclick="addoption(id)" style="margin-left:30px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>添加选项</span>
				    </a>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "opt1">
					<span style="color: #CC3300">*</span>&nbsp;选项1&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_one1" id="option_one1" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "opt2">
					<span style="color: #CC3300">*</span>&nbsp;选项2&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_two1" id="option_two1" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "opt3">
					
					<span style="color: #CC3300">*</span>&nbsp;选项3&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_three1" id="option_three1" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "opt4">
					<span style="color: #CC3300">*</span>&nbsp;选项4&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_four1" id="option_four1" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "opt5">
					<span style="color: #CC3300">*</span>&nbsp;选项5&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_five1" id="option_five1" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
						<div style="height: 5px;"></div>
				
					<div style="height: 5px;"></div>
				</div>
					
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic1" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(1);" />
					</div>
				</div>
			</div>
			<div style="display: none;" id="tiao1">
				【问题二】<div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;问&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="questionBeans.questionname1" id="questionname1" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<a class='easyui-linkbutton l-btn l-btn-plain' id = "option" onclick="addoption(id)" style="margin-left:30px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>添加选项</span>
				</a>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				<div style="display:none" id = "option1">
				<span style="color: #CC3300">*</span>&nbsp;选项1&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_one2" id="option_one2" maxLength="500" cssStyle="width: 150px;"></s:textfield>
				</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "option2">
					<span style="color: #CC3300">*</span>&nbsp;选项2&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_two2" id="option_two2" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "option3">
					
					<span style="color: #CC3300">*</span>&nbsp;选项3&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_three2" id="option_three2" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "option4">
					<span style="color: #CC3300">*</span>&nbsp;选项4&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_four2" id="option_four2" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "option5">
					<span style="color: #CC3300">*</span>&nbsp;选项5&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_five2" id="option_five2" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
				<div style="height: 5px;"></div>
			
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic2" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(2);" />
					</div>
				</div>
			</div>
			
			<div style="display: none;" id="tiao2">
				【问题三】 <div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;问&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="questionBeans.questionname2" id="questionname2" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<a class='easyui-linkbutton l-btn l-btn-plain' id = "qopt" onclick="addoption(id)" style="margin-left:30px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>添加选项</span>
				</a>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				<div style="display:none" id = "qopt1">
				<span style="color: #CC3300">*</span>&nbsp;选项1&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_one3" id="option_one3" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "qopt2">
					<span style="color: #CC3300">*</span>&nbsp;选项2&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_two3" id="option_two3" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "qopt3">
					
					<span style="color: #CC3300">*</span>&nbsp;选项3&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_three3" id="option_three3" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "qopt4">
					<span style="color: #CC3300">*</span>&nbsp;选项4&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_four3" id="option_four3" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "qopt5">
					<span style="color: #CC3300">*</span>&nbsp;选项5&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_five3" id="option_five3" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
				<div style="height: 5px;"></div>
				
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic3" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(3);" />
					</div>
				</div>
			</div>
			<div style="display: none;" id="tiao3">
				【问题四】 <div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;问&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="questionBeans.questionname3" id="questionname3" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<a class='easyui-linkbutton l-btn l-btn-plain' id = "fouropt" onclick="addoption(id)" style="margin-left:30px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>添加选项</span>
				</a>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				<div style="display:none" id = "fouropt1">
				<span style="color: #CC3300">*</span>&nbsp;选项1&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_one4" id="option_one4" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "fouropt2">
					<span style="color: #CC3300">*</span>&nbsp;选项2&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_two4" id="option_two4" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "fouropt3">
					
					<span style="color: #CC3300">*</span>&nbsp;选项3&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_three4" id="option_three4" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "fouropt4">
					<span style="color: #CC3300">*</span>&nbsp;选项4&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_four4" id="option_four4" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "fouropt5">
					<span style="color: #CC3300">*</span>&nbsp;选项5&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_five4" id="option_five4" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
				<div style="height: 5px;"></div>
			
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic4" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(4);" />
					</div>
				</div>
			</div>


			<div style="display: none;" id="tiao4">
				【问题五】 <div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;问&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="questionBeans.questionname4" id="questionname4" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<a class='easyui-linkbutton l-btn l-btn-plain' id = "fiveopt" onclick="addoption(id)" style="margin-left:30px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>添加选项</span>
				</a>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				<div style="display:none" id = "fiveopt1">
				<span style="color: #CC3300">*</span>&nbsp;选项1&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_one5" id="option_one5" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "fiveopt2">
					<span style="color: #CC3300">*</span>&nbsp;选项2&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_two5" id="option_two5" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "fiveopt3">
					
					<span style="color: #CC3300">*</span>&nbsp;选项3&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_three5" id="option_three5" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "fiveopt4">
					<span style="color: #CC3300">*</span>&nbsp;选项4&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_four5" id="option_four5" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "fiveopt5">
					<span style="color: #CC3300">*</span>&nbsp;选项5&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_five5" id="option_five5" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
				<div style="height: 5px;"></div>
		
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic5" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(5);" />
					</div>
				</div>
			</div>

			<div style="display: none;" id="tiao5">
				【问题六】<div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;问&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="questionBeans.questionname5" id="questionname5" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<a class='easyui-linkbutton l-btn l-btn-plain' id = "sixopt" onclick="addoption(id)" style="margin-left:30px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>添加选项</span>
				</a>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				<div style="display:none" id = "sixopt1">
				<span style="color: #CC3300">*</span>&nbsp;选项1&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_one6" id="option_one6" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "sixopt2">
					<span style="color: #CC3300">*</span>&nbsp;选项2&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_two6" id="option_two6" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "sixopt3">
					
					<span style="color: #CC3300">*</span>&nbsp;选项3&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_three6" id="option_three6" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "sixopt4">
					<span style="color: #CC3300">*</span>&nbsp;选项4&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_four6" id="option_four6" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "sixopt5">
					<span style="color: #CC3300">*</span>&nbsp;选项5&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_five6" id="option_five6" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
				<div style="height: 5px;"></div>
			
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic6" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(6);" />
					</div>
				</div>
			</div>

			<div style="display: none;" id="tiao6">
				【问题七】 <div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;问&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="questionBeans.questionname6" id="questionname6" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<a class='easyui-linkbutton l-btn l-btn-plain' id = "sevenopt" onclick="addoption(id)" style="margin-left:30px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>添加选项</span>
				</a>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				<div style="display:none" id = "sevenopt1">
				<span style="color: #CC3300">*</span>&nbsp;选项1&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_one7" id="option_one7" maxLength="500" cssStyle="width: 150px;"></s:textfield>
				</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "sevenopt2">
					<span style="color: #CC3300">*</span>&nbsp;选项2&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_two7" id="option_two7" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "sevenopt3">
                    <span style="color: #CC3300">*</span>&nbsp;选项3&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_three7" id="option_three7" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "sevenopt4">
					<span style="color: #CC3300">*</span>&nbsp;选项4&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_four7" id="option_four7" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "sevenopt5">
					<span style="color: #CC3300">*</span>&nbsp;选项5&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_five7" id="option_five7" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
				<div style="height: 5px;"></div>
				
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic7" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(7);" />
					</div>
				</div>
			</div>

			<div style="display: none;" id="tiao7">
				【问题八】<div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;问&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="questionBeans.questionname7" id="questionname7" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<a class='easyui-linkbutton l-btn l-btn-plain' id = "eitentopt" onclick="addoption(id)" style="margin-left:30px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>添加选项</span>
				</a>
				<div style="display:none;">
				
				</div>
				<div style="height: 5px;"></div>
				<div style="display:none" id = "eitentopt1">
				<span style="color: #CC3300">*</span>&nbsp;选项1&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_one8" id="option_one8" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "eitentopt2">
					<span style="color: #CC3300">*</span>&nbsp;选项2&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_two8" id="option_two8" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "eitentopt3">
					
					<span style="color: #CC3300">*</span>&nbsp;选项3&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_three8" id="option_three8" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "eitentopt4">
					<span style="color: #CC3300">*</span>&nbsp;选项4&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_four8" id="option_four8" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "eitentopt5">
					<span style="color: #CC3300">*</span>&nbsp;选项5&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_five8" id="option_five8" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
				<div style="height: 5px;"></div>
				
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic8" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(8);" />
					</div>
				</div>
			</div>

			<div style="display: none;" id="tiao8">
				【问题九】<div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;问&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="questionBeans.questionname8" id="questionname8" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<a class='easyui-linkbutton l-btn l-btn-plain' id = "ninetopt" onclick="addoption(id)" style="margin-left:30px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>添加选项</span>
				</a>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				<div style="display:none" id = "ninetopt1">
				<span style="color: #CC3300">*</span>&nbsp;选项1&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_one9" id="option_one9" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "ninetopt2">
					<span style="color: #CC3300">*</span>&nbsp;选项2&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_two9" id="option_two9" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "ninetopt3">
					
					<span style="color: #CC3300">*</span>&nbsp;选项3&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_three9" id="option_three9" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "ninetopt4">
					<span style="color: #CC3300">*</span>&nbsp;选项4&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_four9" id="option_four9" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "ninetopt5">
					<span style="color: #CC3300">*</span>&nbsp;选项5&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_five9" id="option_five9" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
				<div style="height: 5px;"></div>
				
				<div style="height: 5px;"></div>
				
				<div style="height: 10px;">
					<div style="position:relative; top:-160px; left:340px; width:150px;">
						<img src="" id="pic9" style="display: none; margin-left:30px;" width="100" height="100" onclick="showImg(9);" />
					</div>
				</div>
			</div>

			<div style="display: none;" id="tiao9">
				【问题十】 <div style="height: 5px;"></div>
				<span style="color: #CC3300">*</span>&nbsp;问&nbsp;&nbsp;&nbsp;&nbsp;题:
				<s:textfield name="questionBeans.questionname9" id="questionname9" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				<a class='easyui-linkbutton l-btn l-btn-plain' id = "tentopt" onclick="addoption(id)" style="margin-left:30px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>添加选项</span>
				</a>
				<div style="display:none;">
					
				</div>
				<div style="height: 5px;"></div>
				<div style="display:none" id = "tentopt1">
				<span style="color: #CC3300">*</span>&nbsp;选项1&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_one10" id="option_one10" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "tentopt2">
					<span style="color: #CC3300">*</span>&nbsp;选项2&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_two10" id="option_two10" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "tentopt3">
					
					<span style="color: #CC3300">*</span>&nbsp;选项3&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_three10" id="option_three10" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "tentopt4">
					<span style="color: #CC3300">*</span>&nbsp;选项4&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_four10" id="option_four10" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div style="display:none" id = "tentopt5">
					<span style="color: #CC3300">*</span>&nbsp;选项5&nbsp;&nbsp;&nbsp;:
					<s:textfield name="optionBeans.option_five10" id="option_five10" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
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
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="push()" style="margin-left:310px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>保存</span>
				</a>
			</s:else>
			<%-- <a class='easyui-linkbutton l-btn l-btn-plain' onclick="quxiao()" style="margin-left:80px;">
			<span class='l-btn-text icon-cancel' style='padding-left: 20px;'>取消</span></a>
		 --%>
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
  	var param={};	
  	var headtitlename = $.trim($('#headtitlename').val());
  	var headtitletype = $.trim($('#headtitletype').val());
	var title1=$.trim($('#questionname0').val());
	var option_one1=$.trim($('#option_one1').val());
	var option_two1=$.trim($('#option_two1').val());
	var option_three1=$.trim($('#option_three1').val());
	var option_four1=$.trim($('#option_four1').val());
	var option_five1=$.trim($('#option_five1').val());
	
	var title2=$.trim($('#questionname1').val());
	var option_one2=$.trim($('#option_one2').val());
	var option_two2=$.trim($('#option_two2').val());
	var option_three2=$.trim($('#option_three2').val());
	var option_four2=$.trim($('#option_four2').val());
	var option_five2=$.trim($('#option_five2').val());
	
	var title3=$.trim($('#questionname2').val());
	var option_one3=$.trim($('#option_one3').val());
	var option_two3=$.trim($('#option_two3').val());
	var option_three3=$.trim($('#option_three3').val());
	var option_four3=$.trim($('#option_four3').val());
	var option_five3=$.trim($('#option_five3').val());
	
	var title4=$.trim($('#questionname3').val());
	var option_one4=$.trim($('#option_one4').val());
	var option_two4=$.trim($('#option_two4').val());
	var option_three4=$.trim($('#option_three4').val());
	var option_four4=$.trim($('#option_four4').val());
	var option_five4=$.trim($('#option_five4').val());
	
	var title5=$.trim($('#questionname4').val());
	var option_one5=$.trim($('#option_one5').val());
	var option_two5=$.trim($('#option_two5').val());
	var option_three5=$.trim($('#option_three5').val());
	var option_four5=$.trim($('#option_four5').val());
	var option_five5=$.trim($('#option_five5').val());
	
	var title6=$.trim($('#questionname5').val());
	var option_one6=$.trim($('#option_one6').val());
	var option_two6=$.trim($('#option_two6').val());
	var option_three6=$.trim($('#option_three6').val());
	var option_four6=$.trim($('#option_four6').val());
	var option_five6=$.trim($('#option_five6').val());
	
	var title7=$.trim($('#questionname6').val());
	var option_one7=$.trim($('#option_one7').val());
	var option_two7=$.trim($('#option_two7').val());
	var option_three7=$.trim($('#option_three7').val());
	var option_four7=$.trim($('#option_four7').val());
	var option_five7=$.trim($('#option_five7').val());
	
	var title8=$.trim($('#questionname7').val());
	var option_one8=$.trim($('#option_one8').val());
	var option_two8=$.trim($('#option_two8').val());
	var option_three8=$.trim($('#option_three8').val());
	var option_four8=$.trim($('#option_four8').val());
	var option_five8=$.trim($('#option_five8').val());
	
	var title9=$.trim($('#questionname8').val());
	var option_one9=$.trim($('#option_one9').val());
	var option_two9=$.trim($('#option_two9').val());
	var option_three9=$.trim($('#option_three9').val());
	var option_four9=$.trim($('#option_four9').val());
	var option_five9=$.trim($('#option_five9').val());
	
	var title10=$.trim($('#questionname9').val());
	var option_one10=$.trim($('#option_one10').val());
	var option_two10=$.trim($('#option_two10').val());
	var option_three10=$.trim($('#option_three10').val());
	var option_four10=$.trim($('#option_four10').val());
	var option_five10=$.trim($('#option_five10').val());
	
		param={ "headtitleBean.headtitlename":headtitlename,"headtitleBean.headtitletype":headtitletype,
				"questionBeans.questionname0":title1,"optionBeans.option_one1":option_one1,"optionBeans.option_two1":option_two1,"optionBeans.option_three1":option_three1,"optionBeans.option_four1":option_four1,"optionBeans.option_five1":option_five1,
				"questionBeans.questionname1":title2,"optionBeans.option_one2":option_one2,"optionBeans.option_two2":option_two2,"optionBeans.option_three2":option_three2,"optionBeans.option_four2":option_four2,"optionBeans.option_five2":option_five2,
				"questionBeans.questionname2":title3,"optionBeans.option_one3":option_one3,"optionBeans.option_two3":option_two3,"optionBeans.option_three3":option_three3,"optionBeans.option_four3":option_four3,"optionBeans.option_five3":option_five3,
				"questionBeans.questionname3":title4,"optionBeans.option_one4":option_one4,"optionBeans.option_two4":option_two4,"optionBeans.option_three4":option_three4,"optionBeans.option_four4":option_four4,"optionBeans.option_five4":option_five4,
				"questionBeans.questionname4":title5,"optionBeans.option_one5":option_one5,"optionBeans.option_two5":option_two5,"optionBeans.option_three5":option_three5,"optionBeans.option_four5":option_four5,"optionBeans.option_five5":option_five5,
				"questionBeans.questionname5":title6,"optionBeans.option_one6":option_one6,"optionBeans.option_two6":option_two6,"optionBeans.option_three6":option_three6,"optionBeans.option_four6":option_four6,"optionBeans.option_five6":option_five6,
				"questionBeans.questionname6":title7,"optionBeans.option_one7":option_one7,"optionBeans.option_two7":option_two7,"optionBeans.option_three7":option_three7,"optionBeans.option_four7":option_four7,"optionBeans.option_five7":option_five7,
				"questionBeans.questionname7":title8,"optionBeans.option_one8":option_one8,"optionBeans.option_two8":option_two8,"optionBeans.option_three8":option_three8,"optionBeans.option_four8":option_four8,"optionBeans.option_five8":option_five8,
				"questionBeans.questionname8":title9,"optionBeans.option_one9":option_one9,"optionBeans.option_two9":option_two9,"optionBeans.option_three9":option_three9,"optionBeans.option_four9":option_four9,"optionBeans.option_five9":option_five9,
				"questionBeans.questionname9":title10,"optionBeans.option_one10":option_one10,"optionBeans.option_two10":option_two10,"optionBeans.option_three10":option_three10,"optionBeans.option_four10":option_four10,"optionBeans.option_five10":option_five10,
				
				};
	
		$.ajax({   
				url:'/weixin-studio/question/saveQuestion',
		        type: 'POST',   
		        async: false,
		        data:  param,
		       dataType: 'json',  
		       success:function(data){ 
		    	   if(data == null){
		               $.messager.alert("提示","保存失败！");
		           }else if(data.message=="success"){		    		   
		    		   $.messager.alert("提示","保存成功！");	    		  
		    	   }else{
		    	       $.messager.alert("提示","保存失败！");
		    	   }
		       }
		    });
			
		
	}
	function addmore(){
	for(var i=0;i<10;i++){
		var value=$('#questionname'+i).val();
		if(value==null||value==""){
			var tiao="tiao"+i;
			break;
		}
	}
 	
 	var stiao=document.getElementById(tiao);
 	if(stiao != null) {
 		stiao.style.display='block';
 	}
	if(i > 10) {
		$.messager.alert("提示","最多只能添加10条！","warning");
	}
}

  function addoption(id){

  for(var i=1;i<6;i++){
  
		var value=document.getElementById(id+i).style.display;
	
		if(value=="none"){
			document.getElementById(id+i).style.display="block";
			break;
		}
	}
	
	if(i > 5) {
		$.messager.alert("提示","最多只能添加5条！","warning");
	}
  }
	</script>
</body>
</html>