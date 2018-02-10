<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
String path = request.getContextPath();
%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title></title>
    <meta http-equiv="x-ua-compatible" content="ie=7" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
    <link type="text/css" rel="stylesheet" href="/weixin-studio/style/js/qtip/css/global.css" />
    <link rel="stylesheet" type="text/css" href="/weixin-studio/style/css/custom.css" />
    <link rel="stylesheet" type="text/css" href="/weixin-studio/style/css/public.css" />
   <script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
  
  	<script src="/weixin-studio/style/js/qtip/jtip.js" type="text/javascript"></script>
    <script type="text/javascript" src="/weixin-studio/js/menu/menuManage.js"></script>

<script type="text/javascript">  


//chooseOne()函式，參數為觸發該函式的元素本身   
function chooseOne(cb){   
    //先取得同name的chekcBox的集合物件   
    var obj = document.getElementsByName("ck");  
  
    for (i=0; i<obj.length; i++){  
        //判斷obj集合中的i元素是否為cb，若否則表示未被點選   
        if (obj[i]!=cb) obj[i].checked = false;   
        //若是 但原先未被勾選 則變成勾選；反之 則變為未勾選   
        //else  obj[i].checked = cb.checked;   
        //若要至少勾選一個的話，則把上面那行else拿掉，換用下面那行   
        else obj[i].checked = true;   
    }
}   
 
 
 

</script>

  </head>

  <body>
  <form name="userInfFrom">
  
<s:hidden  id="waccountids"  name="waccount.waccountId"/>
<div id="" style="width:800px;padding-left: 60px; margin-top: 10px;">
<div class="CustomSetup">
			<div class="CustomSetup_top">
				<div class="CustomSetup_top_l">自定义菜单设置</div>
				<div class="CustomSetup_top_r" style="height: 95px;"> 1、一级菜单个数范围：2-3个，二级菜单个数范围：2-5个，菜单最多支持两层。 </br>
					2、点击保存按钮可以对菜单设置进行保存，但最终只有"<b>发布</b>"后才能生效。 </br>
					3、请注意：微信公众平台限制每天可发布100次，请适量操作。发布后请<b>先取消再关注</b>查看实时效果。 </br>
					4、请注意：为菜单按钮添加链接时，请输入<b>完整url地址</b>。例如：http://www.cntv.cn/。 
				</div>
			</div>

	<div class="CustomSetup_left">
				<dl>
					<dt>
						<strong style="color:#59a416; font-size:16px;">
							菜单列表
						</strong>
					</dt>
											<dd>
							<a title="添加一级菜单" onclick="add_menu(1, 0, 0);" href="javascript:void(0);">
								<img width="20" height="20" alt="添加一级菜单" src="http://wecool.socialmedia.cn/public/wecool/images/app/menu/bottom_menu_12.jpg" />
							</a>
						</dd>
									</dl>
							</div>
							
							<div style="text-align: center; font-size: 16px; margin-top: 10px; display: none;" id="fristnote" class="CustomSetup_right">
					你还没有设置菜单，现在开始
					<a style="font-weight:bold;" onclick="add_menu(1, 0, 0);" href="javascript:void(0);">
						添加 
					</a>一个一级菜单吧 
				</div>

<div style="display: block;" id="menuform" class="CustomSetup_right">
				<div class="CustomSetup_lie">
					<dl>
						<dt>菜单名称：</dt>
						<dd class="AutText">
							<input type="text" maxlength="5" class="AuthorizeText2" id="menu_name" name="menu_name">
						</dd>
						<dd id="limitnote">一级菜单可输入5个字符</dd>
					</dl>
				</div>
				<div class="CustomSetup_lie">
					<dl>
						<dt>
							<select class="txt" id="menu_type" name="menu_type" style="border:1px solid #CCC; padding:5px;height: 30px;" onchange="chooseType('true');">
								<!--<option value="click">绑定规则</option>
								--><option value="view">链接</option>
								<option value="text">文本</option>
								<option value="content">图文新闻</option>
							</select>
						</dt>
						<dd class="AutText">
							<input type="text" maxlength="256" class="AuthorizeText2" id="menu_key" name="menu_key" >
						</dd>
						<dd id ="chooseButton">链接限制最长256字符，不允许汉字</dd>
					</dl>
					
					<div  id="bottom_button" class="Custombutton">
					<span>
					 
						<input type="hidden" id="typeid" value="1" />
						<input type="hidden" id="parentid" value="0" />
						<input type="hidden"  id="id" value="0" />
						<input type="hidden"  id="menu_key_id" />
						<input type="button" onclick="save(event);" value="保 存" class="btngreen30" id="savebtn"  style="visibility:hidden;" />
						
						
						
						
					</span>
				</div> 
				</div>
			
				<!--规则框开始-->
				<!--<div class="RuleChoose">
					<dl>
						<dt><b>快捷选择</b></dt>
					</dl>
					<dl>
						<dt>
																																		</dt>
					</dl>
					<dl>
						<dt>
																				</dt>
					</dl>
					<dl>
						<dt><b>我的应用</b></dt>
																																																																	</dl>
				</div>
			--></div>
	<!-- 	<div style="display:none;" id="bottom_button" class="Custombutton">
					<span>
					 
						<input type="hidden" id="typeid" value="1" />
						<input type="hidden" id="parentid" value="0" />
						<input type="hidden" id="id" value="0" />
						<input type="hidden"  id="menu_key_id" />
						<input type="button" onclick="save(event);" value="保 存" class="btngreen30" id="savebtn" />
						<input type="button" onclick="pub(event);" value="发布到微信" class="btngreen30" id="pubbtn" />
						<!--<input type="button" onclick="pub_yixin(event);" value="发布到易信" class="btngreen30" id="pubbtn" />
						
					</span>
				</div> 
				
				 -->

</div>
</div>
<div id="chooseContent" class="easyui-dialog" style="width:570px;height:515px;padding:10px 20px"
    buttons="#dlg-buttons">
    		<label>按默认词查询：&nbsp;&nbsp;</label><input type="text" name="catalogTitle" id = "catalogTitlequeryContent" maxlength="200">&nbsp;
			<input type="button" value="查询" onclick="queryContent();javascript: $('#contentInfo').datagrid('unselectAll');">
			<table id="contentInfo">
			</table>
			<br/>
			<center><input type="button" value="确定" onclick="sureContent();"></center>
	</div>
	<div id="chooseText" class="easyui-dialog"  style="width:570px;height:515 px;padding:10px 20px">
    		<label>按默认词查询：&nbsp;&nbsp;</label><input type="text" name="catalogTitle" id="catalogTitlequeryText" maxlength="200">&nbsp;
			<input   type="button" value="查询" onclick="queryText();javascript: $('#textInfo').datagrid('unselectAll');">
			<table id="textInfo">
			</table>
			<br/>
			<center><input type="button" value="确定" onclick="sureText();"></center>
	</div>
	
	    <input type="hidden" id="basePath" value="<%=path%>">
</form>   
  </body>
</html>
