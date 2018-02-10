<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

    <title>添加或修改文本素材</title>
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
  	<script src="/weixin-studio/js/wenbenmaterial/wenbenMaterialOperate.js" type="text/javascript"></script>
  	
  	<style type="text/css">
  	tr { height: 45px;}
  	</style>
</head>
<body>

	<div style="margin: 15px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<s:if test="text.id !=null">
				修改文本素材
			</s:if>
			<s:else>
				添加文本素材
			</s:else>
		</div>
	</div>
	<div style="padding-left: 30px; padding-top: 5px">
		<form action="" name="editKeywords" id="editKeywords">
			<div style="display: none;">
				<s:textfield id="textId" name="text.id"></s:textfield>
				<s:textfield id="waccount_id" name="text.waccount_id"></s:textfield>
			</div>
		<table style="padding-left: 30px; width: 80%">
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;题:</td>
				<td><s:textfield theme="simple" name="text.catalogTitle" id="catalogTitle" cssStyle="width: 250px;" maxLength="200"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;精确关键词:</td>
				<td><s:textfield theme="simple" name="text.keyword1" id="keyword1" cssStyle="width: 250px;"></s:textfield>
					<br/><span style="font-size:12px;">(多个关键词之间请用半角逗号隔开)</span></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;模糊关键词:</td>
				<td><s:textfield theme="simple" name="text.keyword0" id="keyword0" cssStyle="width: 250px;"></s:textfield>
					<br/><span style="font-size:12px;">(多个关键词之间请用半角逗号隔开)</span></td>
			</tr>
			<tr>
				<td><span style="color:#CC3300">*</span>&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;态:</td>
				<td>
				<s:if test="text.status ==0">
					<input type="radio" value="1" name="status"/>启用 &nbsp;&nbsp;<input type="radio" value="0" name="status" checked="checked"/>停用
				</s:if>
				<s:else>
					<input type="radio" value="1" name="status" checked="checked"/>启用 &nbsp;&nbsp;<input type="radio" value="0" name="status"/>停用
				</s:else>
				</td>
			</tr>
			<tr>
				<td><span style="color:#CC3300">*</span>&nbsp;&nbsp;内&nbsp;&nbsp;&nbsp;&nbsp;容:</td>
				<td><s:textarea theme="simple" name="text.wenbenxml" id="wenbenxml" cssStyle="width:250px;resize:none;" rows="6"></s:textarea>
				</td>
			</tr>
			<tr style="height: 20px;"></tr>
			<tr>
				<td colspan="2" align="center">
				<s:if test="text.id !=null">
					<a class='easyui-linkbutton l-btn l-btn-plain' onclick="updatetxt()">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>修改</span>
					</a>&nbsp;&nbsp;
				</s:if>
				<s:else>
					<a class='easyui-linkbutton l-btn l-btn-plain' onclick="addtxt()">
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
