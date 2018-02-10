<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<title>添加奖品</title>
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
<script language="javascript" src="/weixin-studio/style/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/weixin-studio/js/question/question.js"></script>
<style type="text/css">
tr { height: 30px;}
</style>


</head>
<body>
	<div style="margin: 10px; background: #D8D8D8; height: 30px; vertical-align: middle;">
		<div style="padding-top:8px; font-family: Arial; font-size: 13px; font-weight: bold;">
			<p>添加奖品</p>
			
		</div>
	</div>
	<div style="padding-left: 50px;" id="meeting">
		<form action="" name="editKeywords" id="editKeywords">
			<div style="display: none;">
				<s:textfield id="textId" name="gm.id"></s:textfield>
				<s:textfield id="waccount_id" name="gm.waccount_id"></s:textfield>
				<s:textfield id="imagevalue" name="image.i"></s:textfield>
			</div>
			<div>
			<span style="color: #CC3300">*</span>&nbsp;活动主题&nbsp;:
			<s:textfield  id="theme" maxLength="500" cssStyle="width: 150px;"></s:textfield>
			</div>
			<div style="height: 5px;"></div>
			<div style="height: 5px;"></div>
			<div  id = "opt1">
			<span style="color: #CC3300">*</span>&nbsp;开始时间&nbsp;:
			<s:textfield theme="simple"  id="starTime" maxLength="500"  name="prizeTitle.starTime" class="text1 Wdate startDate" cssStyle="width: 182px;" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
				</s:textfield>
			</div>
			<div style="height: 5px;"></div>
			<div  id = "opt2">
			<span style="color: #CC3300">*</span>&nbsp;结束时间&nbsp;:
		<s:textfield theme="simple"  id="endTime" maxLength="500"  name="prizeTitle.starTime" class="text1 Wdate startDate" cssStyle="width: 182px;" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
				</s:textfield>
			</div>
			<br />
			<div>
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="addmore()" style="margin-left:30px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>再加一组内容</span>
				</a>
				<!-- 
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="tiao()" style="margin-left:30px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>挑选素材</span>
				</a>
				 -->
			</div>
			<div style="height: 5px;"></div>
			<div style="display: block;" class="content" id="content1">
				<div>
					<span>【序号1】</span>
					<div style="height: 5px;"></div>
					<input type="hidden" value="序号1" id="contentserial1"/>
					<div>
					<span style="color: #CC3300">*</span>&nbsp;奖品名称:
					<s:textfield  id="prizename1" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div>
					<span style="color: #CC3300">*</span>&nbsp;奖品数量:
					<s:textfield  id="prizenb1" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
					<div>
					<span style="color: #CC3300">*</span>&nbsp;中奖率&nbsp;&nbsp;:
					<s:textfield  id="probability1" maxLength="500" cssStyle="width: 150px;"></s:textfield>
					</div>
					<div style="height: 5px;"></div>
				</div>
					
				
			</div>
			


			<s:if test="gm.id !=null">
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="updatetxt()" style="margin-left:210px;">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>修改</span>
				</a>
			</s:if>
			<s:else>
				<a class='easyui-linkbutton l-btn l-btn-plain' onclick="save()" style="margin-left:310px;">
					<span class='l-btn-text icon-ok' style='padding-left: 20px;'>保存</span>
				</a>
			</s:else>
			<%-- <a class='easyui-linkbutton l-btn l-btn-plain' onclick="quxiao()" style="margin-left:80px;">
			<span class='l-btn-text icon-cancel' style='padding-left: 20px;'>取消</span></a>
		 --%>
		</form>
	</div>
	<script>
	
		function save(){
		var contentlength = $(".content").length;
  	    var param='';
  	    var json='';	
  	    var theme = $("#theme").val();
  	    var starTime = $("#starTime").val();
  	    var endTime = $("#endTime").val();
  	  //debugger; 
  	    json +='[';
  	    for(var i=1;i<=contentlength;i++){
  	    var prizename = $("#prizename"+i).val();
  	    alert(prizename);
  	    var prizenb = $("#prizenb"+i).val();
  	    var probability = $("#probability"+i).val();
  	   
  	    if(theme=="" || starTime=="" ||endTime=="" || prizename == "" || prizenb=="" || probability==""){
  	     $.messager.alert('提示','您有必填项未填！');
  	     return;
  	    }
  	    if(i == contentlength){
  	     json +='{"prizename":"'+prizename+'","prizenb":"'+prizenb+'","probability":"'+probability+'"}'; 
  	    }
  	    else{
  	     json +='{"prizename":"'+prizename+'","prizenb":"'+prizenb+'","probability":"'+probability+'"},'; 
  	    }
  	   
  	    }
  	    json += ']';
  	  // alert(json);
  	    var jsonObj=$.parseJSON(json);
  	    // alert(jsonObj);
		param +='{';
		param += '"theme":"'+theme+'","starTime":"'+starTime+'","endTime":"'+endTime+'","prize":'+json+'';
		param += '}';
	    alert(param);
		$.ajax({   
				url:'/weixin-studio/interact/createprizeInteract',
		        type: 'POST',   
		        async: false,
		        data:  {"mcontent":param},
		       dataType: 'json',  
		       success:function(data){ 
		    	   if(data == null){
		               $.messager.alert("提示","保存失败！");
		           }else if(data.msg=="success"){		    		   
		    		   $.messager.alert("提示","保存成功！","info",function(r){
				    		   parent.$.fn.colorbox.close();
				    		   $('#dataGrid').datagrid('reload');   
				    		    });		  
		    	   }else{
		    	       $.messager.alert("提示","保存失败！");
		    	   }
		       }
		    });
			
		
	}
	function addmore(){
	var html='';
	var i = $(".content").length+1;
	html +='<div style="display: block;" class="content" id="content'+i+'">';
	html +='<div>';
	html +='<span>【序号'+i+'】</span>';
	html +='<div style="height: 5px;"></div>';
	html +='<input type="hidden" value="序号'+i+'" id="contentserial'+i+'"/>';
	
	html +=	'<div style="height: 5px;"></div>';			
	html +=	'<div>';		
	html +=	'<span style="color: #CC3300">*</span>&nbsp;奖品名称&nbsp;&nbsp;:'	;		
	html +=	'<input type="text"  id="prizename'+i+'" maxLength="500" cssStyle="width: 150px;"></textArea>';		
	html +=	'</div>';		
	html +=	'<div style="height: 5px;"></div>';	
	html +=	'<div>';		
	html +=	'<span style="color: #CC3300">*</span>&nbsp;奖品数量&nbsp;&nbsp;:'	;		
	html +=	'<input type="text"  id="prizenb'+i+'" maxLength="500" cssStyle="width: 150px;"></textArea>';		
	html +=	'</div>';		
	html +=	'<div style="height: 5px;"></div>';	
	html +=	'<div>';		
	html +=	'<span style="color: #CC3300">*</span>&nbsp;中奖率&nbsp;&nbsp;:'	;		
	html +=	'<input type="text"  id="probability'+i+'" maxLength="500" cssStyle="width: 150px;"></textArea>';		
	html +=	'</div>';		
	html +=	'</div>';										
	html +=	'</div><br /><br />';
	$("#meeting").append(html);
}

	</script>
</body>
</html>