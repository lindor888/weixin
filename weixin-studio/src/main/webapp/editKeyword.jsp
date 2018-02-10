<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

    <title>添加或修改关键字</title>
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
  	<script src="/weixin-studio/js/keyword/keywordOperate.js" type="text/javascript"></script>
</head>
<body>
	<s:if test="eidtkeyword.id !=null">
	<div  style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#000000;">修改关键字</div>
	</s:if>
	<s:else>
	<div  style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#000000;">添加关键字</div>
	</s:else>
		<div style="padding-left: 100px;padding-top: 50px">
			<form action="" name="editKeywords" id="editKeywords">
				<div style="display: none;">
					<s:textfield id="id" name="id"></s:textfield>
					<s:textfield id="eidtkeywordId" name="eidtkeyword.id"></s:textfield>
					<s:textfield id="kwdMaterialId" name="eidtkeyword.materialId"></s:textfield>
				</div>
				<span style=" color:#CC3300">*</span>关键字名称:&nbsp;&nbsp;<s:textfield name="eidtkeyword.keywordName" id="keywordName" maxLength="100"></s:textfield><br/><br/><br/>
				<s:if test="eidtkeyword.keywordRule ==0">
					<span style=" color:#CC3300">*</span>匹配规则:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="1" name="keywordRule" onclick="rule('1');"/>精确 &nbsp;&nbsp;<input type="radio" value="0" name="keywordRule" checked="checked" onclick="rule('0');"/>模糊<br/><br/><br/>
				</s:if>
				<s:else>
					<span style=" color:#CC3300">*</span>匹配规则:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="1" name="keywordRule" checked="checked" onclick="rule('1');"/>精确 &nbsp;&nbsp;<input type="radio" value="0" name="keywordRule" onclick="rule('0');"/>模糊<br/><br/><br/>
				</s:else>
				<s:if test="eidtkeyword.status ==0">
					<span style=" color:#CC3300">*</span>状&nbsp;&nbsp;&nbsp;&nbsp;态:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="1" name="status"/>启用 &nbsp;&nbsp;<input type="radio" value="0" name="status" checked="checked"/>停用<br/><br/><br/>
				</s:if>
				<s:else>
					<span style=" color:#CC3300">*</span>状&nbsp;&nbsp;&nbsp;&nbsp;态:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="1" name="status" checked="checked"/>启用 &nbsp;&nbsp;<input type="radio" value="0" name="status"/>停用<br/><br/><br/>
				</s:else>
				<s:if test="eidtkeyword.keywordRule ==0">
					<span style=" color:#CC3300">*</span>关键字权重:&nbsp;&nbsp;<s:textfield name="eidtkeyword.keywordWeight" id="keywordWeight" maxLength="2"  disabled="disabled"></s:textfield><br/><br/><br/>
				</s:if>
				<s:else>
					<span style=" color:#CC3300">*</span>关键字权重:&nbsp;&nbsp;<s:textfield name="eidtkeyword.keywordWeight" id="keywordWeight" maxLength="2" value="1" disabled="true"></s:textfield><br/><br/><br/>
				</s:else>
				<span style=" color:#CC3300">*</span>匹配类型:&nbsp;&nbsp;&nbsp;&nbsp;<s:select list="#{'1':'图文','0':'文本'}" cssStyle="width:150px" name="eidtkeyword.type" id="type" onchange="qingkong()"></s:select> <br/><br/><br/>
				<span style=" color:#CC3300">*</span>关联数据:&nbsp;&nbsp;&nbsp;<s:textfield name="eidtkeyword.catalogTitle" id="sucaiTitle"  disabled="true"></s:textfield><a href="#" onclick="tiaoxuan();">挑选素材</a><br/><br/><br/>
				<s:if test="eidtkeyword.id !=null">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class='easyui-linkbutton l-btn l-btn-plain' onclick="updateKwd()"><span class='l-btn-text icon-edit' style='padding-left: 20px;'>修改</span></a>&nbsp;&nbsp;
				</s:if>
				<s:else>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class='easyui-linkbutton l-btn l-btn-plain' onclick="addKeyWord()"><span class='l-btn-text icon-ok' style='padding-left: 20px;'>添加</span></a>&nbsp;&nbsp;
				</s:else>
				 <a class='easyui-linkbutton l-btn l-btn-plain' onclick="quxiao()"><span class='l-btn-text icon-cancel' style='padding-left: 20px;'>取消</span></a>
			</form>
	</div>
	<script>
	function tiaoxuan(){
		var material=$('#type').val();
		$.fn.colorbox({iframe:true,innerWidth:450, innerHeight:400, href:'/weixin-studio/keyword/tiaoxuanKeywordAction?material='+material,overlayClose:false,onClosed:function(){$('#keyword').datagrid('reload');}});
	}
	</script>
</body>
</html>
