<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>添加敏感词</title>
    <meta http-equiv="x-ua-compatible" content="ie=7" />
     <link type="text/css" rel="stylesheet" href="${web.context.path}/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="${web.context.path}/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="${web.context.path}/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="${web.context.path}/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="${web.context.path}/style/colorBox/colorbox.css" />
    <link type="text/css" rel="stylesheet" href="${web.context.path}/style/js/qtip/css/global.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/public.css" />
    <script src="${web.context.path}/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="${web.context.path}/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="${web.context.path}/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
  	<script src="${web.context.path}/style/js/qtip/jtip.js" type="text/javascript"></script>
    <script type="text/javascript" src="${web.context.path}/style/js/jquery.validate.js"></script>
    <script src="${web.context.path}/js/waccount/choosematerial.js" type="text/javascript"></script>
    
<style>
table tr{ height: 40px; }
p{ height: 40px; padding-left: 20px; }
.h{ height: 30px}

.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(../images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }

</style>

<script>
	//用户添加表单提交
	function insertWords(formId){
		var id = $("#id").val();
		var content = $("#content").val();
		if(content==''){
			alert('请填写敏感词!');
			return;
		}
		
		$.ajax({
			url : '${web.context.path}/words/updateWords',
			type : 'post',
			async : 'false',
			data : {'bean.content':content,'bean.id':id},
			success : function(data){
				var msg = data.msg;
				if(msg=='success'){
					alert('修改成功');
					window.parent.closeColorBox(false);
				}else if(msg=='limit'){
					alert('该敏感词已存在');
				}else{
					alert('修改失败');
				}
			}
		});
	}
	
</script>
</head>
<body>
<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<strong>修改敏感词</strong>
		</div>
	</div>
<div class="h" >

</div>
	<s:form  method="post" namespace="/words" action="addWords"  id="wordsFrom"   theme="simple">
	<s:hidden name="bean.id" id="id"></s:hidden>
	<p><i>*</i>&nbsp;<span style="padding-right: 12px;">敏感词:</span><s:textfield id="content" name="bean.content" size="40" /><span id="tishi"></span></p>
	<div style="padding-left: 250px;"><ul class="button_1" >
			<li><span onclick="insertWords('wordsFrom')">保存</span></li>
			<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
	</ul></div>
	
	</s:form>
  </body>
</html>
