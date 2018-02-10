<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

    <title>修改会议日程</title>
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
  	<script type="text/javascript" src="/weixin-studio/style/My97DatePicker/WdatePicker.js"></script>
  	<style type="text/css">
  	tr { height: 45px;}
  	</style>
  	  	<script>
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
				url : '/weixin-studio/epg/addOneEpg',
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
		    var meetingcontentid = $("#meetingcontentid").val();
			var contenttime = $("#contenttime").val();
			var content = $("#content").val();
			var contentpersion = $("#contentpersion").val();
			var contentgroup = $("#contentgroup").val();
			var contentserial = $("#contentserial").val();
			if(contentgroup=='' || contenttime=='' || content=='' || contentpersion=='' || contentserial==''){
				alert('您有必填项未填!');
				return;
			}
			$.ajax({
				url : '/weixin-studio/interact/modifycontentInteract',
				type : 'post',
				async : false,
				data : {'meetingContent.meetingcontentid':meetingcontentid,'meetingContent.contenttime':contenttime,'meetingContent.content':content,'meetingContent.contentpersion':contentpersion,'meetingContent.contentgroup':contentgroup,'meetingContent.contentserial':contentserial},
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
			<s:if test="meetingAgenda !=null">
				修改会议日程
			</s:if>
			<s:else>
				修改会议日程
			</s:else>
		</div>
	</div>
	<div style="padding-left: 30px; padding-top: 5px">
		<form action="" name="editEpg" id="editEpg">
			<div style="display: none;">
				<s:textfield id="meetingcontentid" name="meetingContent.meetingcontentid"></s:textfield>
			</div>
		<table style="padding-left: 30px; width: 80%">
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;序号：</td>
				<td><s:textfield theme="simple"  name="meetingContent.contentserial" id="contentserial" cssStyle="width: 250px;" maxLength="200"></s:textfield>
				</td>
			</tr>
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;时间：</td>
				<td><s:textfield theme="simple"  id="contenttime" maxLength="500"  name="meetingContent.contenttime" cssStyle="width: 182px;" >
				
				</s:textfield>
				</td>
			</tr>
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;内容：</td>
				<td><s:textfield theme="simple"  name="meetingContent.content" id="content" cssStyle="width: 250px;" maxLength="500"></s:textfield>
				</td>
			</tr>
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;发言人：</td>
				<td><s:textfield theme="simple"   name="meetingContent.contentpersion" id="contentpersion" maxLength="200" cssStyle="width: 250px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td><span style="color: #CC3300">*</span>&nbsp;项目组成员：</td>
				<td><s:textfield theme="simple"   name="meetingContent.contentgroup" id="contentgroup" maxLength="500" cssStyle="width: 250px;"></s:textfield>
				</td>
			</tr>
			<tr style="height: 20px;"></tr>
			<tr>
				<td colspan="2" align="center">
				
					<a class='easyui-linkbutton l-btn l-btn-plain' onclick="update()">
					<span class='l-btn-text icon-edit' style='padding-left: 20px;'>保存</span>
					</a>&nbsp;&nbsp;
				 <a class='easyui-linkbutton l-btn l-btn-plain' onclick="quxiao()"><span class='l-btn-text icon-cancel' style='padding-left: 20px;'>取消</span></a>
				</td>
			</tr>
		</table>
	</form>
	</div>
	
</body>
</html>
