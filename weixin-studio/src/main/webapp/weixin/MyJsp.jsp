<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">

<meta charset="utf-8">
<script type="text/javascript">

</script>
<title>微信用户管理</title>
<link href="/weixin-studio/weixin/images/favicon218877.ico" rel="Shortcut Icon">
<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="/weixin-studio/weixin/css/layout_head2880f5.css"/>
<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="/weixin-studio/weixin/css/base2968da.css"/>
<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="/weixin-studio/weixin/css/lib2968da.css"/>           
<link onerror="wx_loaderror(this)" rel="stylesheet" href="/weixin-studio/weixin/css/page_user27ab39.css" />
<link rel="stylesheet" type="text/css" href="/weixin-studio/weixin/css/emoji218878.css" />
<link rel="stylesheet" type="text/css" href="/weixin-studio/weixin/css/rich_buddy27898a.css" />
<link rel="stylesheet" type="text/css" href="/weixin-studio/weixin/css/pagination218878.css" />
<link rel="stylesheet" type="text/css" href="/weixin-studio/weixin/css/dropdown218878.css" />
  <link rel="stylesheet" href="/weixin-studio/weixin/css/common.css"/><!-- 基本样式 -->
	<link rel="stylesheet" href="/weixin-studio/weixin/css/animate.min.css"/> <!-- 动画效果 -->
	<script src="/weixin-studio/weixin/js/jquery.hDialog.min.js"></script>
	<script src="/weixin-studio/weixin/js/jquery.min.js"></script>
<style type="text/css">
.frmcheckbox { 
	width:20px;height:20px;
	color:Red;cursor:pointer;
	text-align:center;
	
	display:inline-block;
	border-radius:.2em;
	-webkit-box-shadow:inset .08em .08em .1em #000;
	background-color:#FF4500;
}
</style>
 <script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
    </head>
    <body class="zh_CN">
        <div class="head" id="header">
            <script type="text/javascript">
    //上报测速 --css加载完成点
    window._points&&(window._points[1]=+new Date());
</script>
<div class="head_box">
   <!-- 头部信息 -->
   <!--  <div class="inner wrp">
        <h1 class="logo"><a href="/" title="微信公众平台"></a></h1>
                                <div class="account">
                        <div class="account_meta account_info account_meta_primary">
                                <a href="/cgi-bin/settingpage?t=setting/index&action=index&token=653645996&lang=zh_CN" class="nickname">i聚</a>
                                <span class="type_wrp">
                                        <a href="/cgi-bin/settingpage?t=setting/index&action=index&token=653645996&lang=zh_CN" class="type icon_service_label">服务号</a>
                                        
                                        <a href="/merchant/store?action=detail&t=wxverify/detail&info=verify&lang=zh_CN&token=653645996" class="type icon_verify_label success">已认证</a>
                                        
                </span>
                                <a href="/cgi-bin/settingpage?t=setting/index&action=index&token=653645996&lang=zh_CN"><img src="/misc/getheadimg?fakeid=3223017457&token=653645996&lang=zh_CN" class="avatar"></a>
                            </div>
            <div id="accountArea" class="account_meta account_inbox account_meta_primary">
                                <a href="/cgi-bin/frame?t=notification/index_frame&lang=zh_CN&token=653645996" class="account_inbox_switch">
                                        <i class="icon_inbox">通知</i>&nbsp;
                                    </a>
                            </div>
                <div class="account_meta account_logout account_meta_primary"><a id="logout" href="/cgi-bin/logout?t=wxm-logout&lang=zh_CN&token=653645996">退出</a></div>
                                </div>
                    </div> -->
</div>

        </div>
        <div id="body" class="body page_user">
            <div id="js_container_box" class="container_box cell_layout side_l">
      
      <!-- 左侧导航栏           -->
<!--                                 <div class="col_side">
                    <div class="menu_box" id="menuBar">
<dl class="menu no_extra">
    <dt class="menu_title">
        <i class="icon_menu" style="background:url(
        https://res.wx.qq.com/mpres/htmledition/images/icon/menu/icon_menu_function.png
        ) no-repeat;">
    </i>功能
                </dt> 

				<dd class="menu_item "><a data-id="10005" href="/cgi-bin/masssendpage?t=mass/send&token=653645996&lang=zh_CN" >群发功能        </a></dd>
					<dd class="menu_item "><a】】【
					 data-id="10006" href="/advanced/autoreply?t=ivr/reply&action=beadded&token=653645996&lang=zh_CN" >自动回复        </a></dd>
					<dd class="menu_item "><a data-id="10007" href="/advanced/selfmenu?action=index&t=advanced/menu-setting&token=653645996&lang=zh_CN" >自定义菜单        </a></dd>
					<dd class="menu_item "><a data-id="10032" href="/merchant/entityshop?action=list&token=653645996&lang=zh_CN" >门店管理        </a></dd>
					<dd class="menu_item "><a data-id="10038" href="/cgi-bin/newoperatevote?action=list&token=653645996&lang=zh_CN" >投票管理        </a></dd>
				    <dd class='menu_plugins'><a class='btn_plugins_add'  data-id='10025' href="/cgi-bin/plugincenter?t=service/plugins&act=all&token=653645996&lang=zh_CN">添加功能插件</a></dd>
		</dl>
<dl class="menu ">
    <dt class="menu_title clickable">
        <a href="/cgi-bin/frame?nav=10010&t=business/index_frame&iframe=%2Fpaymch%2Fbusiness%3Faction%3Dfirstentry&token=653645996&lang=zh_CN">
        <i class="icon_menu" style="background:url(
        https://res.wx.qq.com/mpres/htmledition/images/icon/menu/icon_menu_wxpay_v2.png
        ) no-repeat;">
    </i>微信支付
            </a>
        </dt> 

	</dl>
<dl class="menu ">
    <dt class="menu_title">
        <i class="icon_menu" style="background:url(
        https://res.wx.qq.com/mpres/htmledition/images/icon/menu/icon_menu_management.png
        ) no-repeat;">
    </i>管理
                </dt> 

				<dd class="menu_item "><a data-id="10012" href="/cgi-bin/message?t=message/list&count=20&day=7&token=653645996&lang=zh_CN" >消息管理        </a></dd>
					<dd class="menu_item selected"><a data-id="10013" href="/cgi-bin/contactmanage?t=user/index&pageidx=0&type=0&token=653645996&lang=zh_CN" >用户管理        </a></dd>
					<dd class="menu_item "><a data-id="10014" href="/cgi-bin/appmsg?begin=0&count=10&t=media/appmsg_list&type=11&action=list&token=653645996&lang=zh_CN" >素材管理        </a></dd>
		</dl>
<dl class="menu ">
    <dt class="menu_title">
        <i class="icon_menu" style="background:url(
        https://res.wx.qq.com/mpres/htmledition/images/icon/menu/icon_menu_ad.png
        ) no-repeat;">
    </i>推广
                </dt> 

				<dd class="menu_item "><a data-id="10026" href="/merchant/ad_client_index?t=ad_system/client_index&token=653645996&lang=zh_CN" >广告主        </a></dd>
					<dd class="menu_item "><a data-id="10027" href="/merchant/ad_host_index?t=ad_system/host_index&token=653645996&lang=zh_CN" >流量主        </a></dd>
		</dl>
<dl class="menu ">
    <dt class="menu_title">
        <i class="icon_menu" style="background:url(
        https://res.wx.qq.com/mpres/htmledition/images/icon/menu/icon_menu_statistics.png
        ) no-repeat;">
    </i>统计
                </dt> 

				<dd class="menu_item "><a data-id="10015" href="/misc/useranalysis?&token=653645996&lang=zh_CN" >用户分析        </a></dd>
					<dd class="menu_item "><a data-id="10016" href="/misc/appmsganalysis?action=all&order_direction=2&token=653645996&lang=zh_CN" >图文分析        </a></dd>
					<dd class="menu_item "><a data-id="10017" href="/misc/messageanalysis?type=daily&t=statistics/msg&token=653645996&lang=zh_CN" >消息分析        </a></dd>
					<dd class="menu_item "><a data-id="10018" href="/misc/interfaceanalysis?type=daily&token=653645996&lang=zh_CN" >接口分析        </a></dd>
		</dl>
<dl class="menu ">
    <dt class="menu_title">
        <i class="icon_menu" style="background:url(
        https://res.wx.qq.com/mpres/htmledition/images/icon/menu/icon_menu_setup.png
        ) no-repeat;">
    </i>设置
                </dt> 

				<dd class="menu_item "><a data-id="10019" href="/cgi-bin/settingpage?t=setting/index&action=index&token=653645996&lang=zh_CN" >公众号设置        </a></dd>
					<dd class="menu_item "><a data-id="10020" href="/merchant/store?action=detail&t=wxverify/detail&info=verify&token=653645996&lang=zh_CN" >微信认证        </a></dd>
					<dd class="menu_item "><a data-id="10022" href="/cgi-bin/safecenterstatus?action=view&t=setting/safe-index&token=653645996&lang=zh_CN" >安全中心        </a></dd>
					<dd class="menu_item "><a data-id="10045" href="/cgi-bin/illegalrecord?count=10&token=653645996&lang=zh_CN" >违规记录        </a></dd>
		</dl>
<dl class="menu ">
    <dt class="menu_title clickable">
        <a href="/advanced/advanced?action=dev&t=advanced/dev&token=653645996&lang=zh_CN">
        <i class="icon_menu" style="background:url(
        https://res.wx.qq.com/mpres/htmledition/images/icon/menu/icon_menu_developer.png
        ) no-repeat;">
    </i>开发者中心
            </a>
        </dt> 

	</dl>
</div>

<script type="text/javascript">
    var _new_comment_num=""||0;
</script>
                </div> -->
                                
                <div class="col_main">
  <a href="javascript:;" class="demo0">带标题的</a>                  
<div class="main_hd">
    <h2>用户管理</h2>
    <div class="title_tab" id="topTab"></div>
</div>
<div class="main_bd">

    <div class="global_mod user_global_opr float_layout">
        <div class="global_info">
            <div class="search_bar" id="searchBar"><span class="frm_input_box search with_del append ">
    <a class="del_btn jsSearchInputClose" href="javascript:" style="display:none">
        <i class="icon_search_del"></i>&nbsp;
    </a>
    <a href="javascript:" class="frm_input_append jsSearchInputBt">
    	<i class="icon16_common search_gray">搜索</i>&nbsp;
    </a>
    <input type="text" value="" class="frm_input jsSearchInput" placeholder="用户昵称">
</span></div>
        </div>
        <div class="global_extra">
                            <a href="javascript:;" data-y="3" class="btn btn_primary btn_add" name="js_groupAdd" onclick="change(name)"><i class="icon14_common add_white"></i>新建分组</a>
                    </div>
    </div>


    <div class="inner_container_box side_r cell_layout ">
        <div class="inner_main">
            <div class="bd">
                
                                <div class="global_mod user_group_opr">
                    <span class="group_name" id="js_groupName"></span>
                                    </div>
                <div class="table_wrp user_list">
                    <table class="table" cellspacing="0">
                        <thead class="thead">
                            <tr>
                                <th class="table_cell user no_extra" colspan="2">
                                    <div class="group_select">
                                        <label for="selectAll" class="group_select_label frm_checkbox_label">
                                            <i class="icon_checkbox"></i>
                                            <input type="checkbox" class="frm_checkbox" id="selectAll">
                                            全选                                        </label>&nbsp;
                                        <div id="allGroup"  class="dropdown_wrp dropdown_menu"><a name ="all" onclick="change(name)" href="javascript:;" class="btn dropdown_switch jsDropdownBt"><label class="jsBtLabel">添加到</label><i class="arrow"></i></a>
<div id ="all" class="dropdown_data_container jsDropdownList" style="display: none;">
    <ul id ="alllist" class="dropdown_data_list">
        
            
           
                    
        
    </ul>
</div>
</div>
                                        <a name="0" class="btn btn_default js_allToBlackList_btn" data-gid="0" onclick="userlistgroup(name)">加入黑名单</a>
                                    </div>
                                </th>
                            </tr>
                        </thead>
                        <tbody class="tbody" id="userGroups">




                      </tbody>
                    </table>
                </div>
                <div class="tool_area">
                    <div class="pagination_wrp js_pageNavigator"></div>
                </div>
                            </div>
        </div>
                <div class="inner_side">
            <div class="bd">
                <div class="group_list">
                    <div class="inner_menu_box" id="groupsList"><dl class="inner_menu" id="inner_menu" >
   
   
    
</dl>


<dl class="inner_menu no_extra" id="unuser">
    
</dl>
</div>
                </div>
            </div>
        </div>
            </div>
</div>

                </div>
            </div>
            
                                    <div class="faq">
             

            </div>
                                    
        </div>
        <div id="js_groupAdd" class="popover  pos_center" style="top: 162px; left: 1100px;display:none;">
        <form action="" id="myform">
    <div class="popover_inner">
        <div class="popover_content jsPopOverContent"><h4 class="popover_title">分组名称</h4>
    <span class="frm_input_box">
        <input type="text" id="groupname" class="frm_input js_name" placeholder="" value="" data-gid="">
    </span></div>
		<!--#0001#-->
        
        <!--%0001%-->

        <div class="popover_bar"><a onclick="savegroup()" class="btn btn_primary jsPopoverBt">确定</a>&nbsp;<a  onclick="hide()" class="btn btn_default jsPopoverBt">取消</a></div>
    </div>
    </form>
   
    <i class="popover_arrow popover_arrow_out"></i>
    <i class="popover_arrow popover_arrow_in"></i> 
</div>

 <div id="HBox">
			<form action="" method="post" onsubmit="return false;">
				<ul class="list">
					<li>
						<strong>昵 称  <font color="#ff0000">*</font></strong>
						<div class="fl"><input type="text" name="nickname" value="" class="ipt nickname" /></div>
					</li>
					
					<li><input type="submit" value="确认提交" class="submitBtn" /></li>
				</ul>
			</form>
		</div>
        <div class="foot" id="footer">
	
    
	
</div>
<script type="text/javascript">
  
  

  var div='';
  var getJSON = function(){
			var url = '';
			var gstr = '';
			var users='';
			url = 'http://localhost:8080/weixin-studio/follower/netfriendFollower';
			
			$.ajax({
				url:url,
				dataType:"json",
				type:"get",
				error:function(){
					console.log("getJSON  error方法");
				},
				success:function(data){
					if (data.msg == "success")
					{
					  users = data.rows;
					  $.ajax({
								url:'http://localhost:8080/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error方法");
								},
								success:function(data){
									if (data.msg == "success")
									{
										gstr = data.rows;
										
										diaochaSucessHandler(users,gstr);
									}else{
										console.log("getJSON  获得数据异常");
									}
								}
							});
					    
						
					}else{
						console.log("getJSON  获得数据异常");
					}
				}
			});
		}

		var diaochaSucessHandler = function(voteData,gData){
		
            var html='';
           
            var thtml='';
       
            if(voteData != "" && voteData != null){
			 for(var i= 0 ;i<voteData.length;i++){
			 var groupData='';
			 html += '<tr><td class="table_cell user"><div class="user_info"> <a target="_blank" onclick="" class="remark_name">'+voteData[i].nickname+'</a>';
			 html += ' <a target="_blank" class="avatar"><img style="cursor:pointer" name="'+voteData[i].openId+'" src="'+voteData[i].headimgurl+'" data-fakeid="1738122015" class="js_msgSenderAvatar" onclick="weixin(name)"></a>';
			 html += ' <label class="frm_checkbox_label"><input onclick="selected(id)" class="frmcheckbox" type="checkbox"  value="'+voteData[i].openId+'" id="'+i+'"></label>';
			 html += '</td>';
			 html += ' <td class="table_cell user_opr">';
			 
			 html += ' <div id="selectArea2858113980" class="js_selectArea dropdown_menu"><a name="'+voteData[i].openId+i+'" onclick="change(name);" href="javascript:;" class="btn dropdown_switch jsDropdownBt"><label class="jsBtLabel">默认组</label><i class="arrow"></i></a>';
			 html += ' <div id="'+voteData[i].openId+i+'" class="dropdown_data_container jsDropdownList" style="display: none;">';
			 html += '  <ul class="dropdown_data_list">';
			 if(gData != null && gData != ""){
			 for(var j=0;j<gData.length;j++){
              groupData += '   <li class="dropdown_data_item "> ';
			  groupData += '   <a id="'+gData[j].groupsId+'gr'+i+'" name="'+voteData[i].openId+'" onclick="usergroup(id)" class="jsDropdownItem" data-value="0" data-index="0" data-name="'+gData[j].name+'">'+gData[j].name+'</a> ';
              groupData += '    </li> ';
              }
              html += groupData;
                   }
			
             html += '   </ul></div></div></td> ';
			 html += '</tr>';
			 }
			
			
			}
			
			$("#userGroups").html(html);
			 for(var t=0;t<gData.length;t++){
              thtml += '   <li class="dropdown_data_item "> ';
			  thtml += '   <a id="'+gData[t].groupsId+'group" name="'+gData[t].groupsId+'" onclick="userlistgroup(name)" class="jsDropdownItem" data-value="0" data-index="0" data-name="'+gData[t].name+'">'+gData[t].name+'</a> ';
              thtml += '    </li> ';
              }
			$("#alllist").html(thtml);
  }
  var groups = function(){
			var url = 'http://localhost:8080/weixin-studio/follower/groupsFollower';
			
			$.ajax({
				url:url,
				dataType:"json",
				type:"get",
				error:function(){
					console.log("getJSON  error方法");
				},
				success:function(data){
					if (data.msg == "success")
					{
						groupsHandler(data);
					}else{
						console.log("getJSON  获得数据异常");
					}
				}
			});
		}
  	var groupsHandler = function(data){
            var str='';
            var shtml='';
            var groupsData = data.rows;
            str += '<dt class="inner_menu_item selected"><a href="/weixin-studio/weixin/usermanager.jsp" class="inner_menu_link" title="全部用户"><strong>全部用户</strong><em class="num">('+data.all+')</em></a></dt>';
            if(groupsData != "" && groupsData != null){
			 for(var i= 0 ;i<groupsData.length;i++){
			str += '<dd class="inner_menu_item ">';
	        str += '<a name="'+groupsData[i].groupsId+'" onclick = "netInit(name)" class="inner_menu_link" title="'+groupsData[i].name+'">';
	        var count = 0;
	        if(groupsData[i].count != null){
	          count = groupsData[i].count;
	        }
		    str += '<strong>'+groupsData[i].name+'</strong><em class="num">('+count+')</em>';
            str += '</a></dd>';
        
			 }
			shtml += '<dt class="inner_menu_item selected"><a name="0" onclick = "netInit(name)" class="inner_menu_link" title="黑名单"><strong>黑名单</strong><em class="num">('+data.blacklist+')</em></a></dt>'
			$("#inner_menu").append(str);
			$("#unuser").append(shtml);
			}
  }
  $(function(){
		getJSON(1);
		groups();
		});
		
function selected(id){

document.getElementById(id).setAttribute("checked","checked");
// $(".icon_checkbox").parent().children(":last").attr("checked","checked");
}
/* $(".btn dropdown_switch jsDropdownBt").click(function(){
  $(".btn dropdown_switch jsDropdownBt").next().attr("style","display:block");
}); */
function change(id){
if(id == "js_groupAdd"){
$("#"+id).attr("style","top:162px;left:1100px;display:block;");
}else{
$("#"+id).attr("style","display:block");
}

//document.getElementById(id).setAttribute("style","display:block");
}
function hide(){
$("#js_groupAdd").attr("style","top:162px;left:1100px;display:none;");
}

function usergroup(id){
var openId = $("#"+id).attr("name");
var gid = id.split("gr")[0];
       $.ajax({   
	       url:'http://localhost:8080/weixin-studio/follower/useraddgroupFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'openId':openId,'groupsId':gid},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg == "success"){
	    	    location.reload();
	    	   }
	        }   
		});
}
function userlistgroup(id){
var openIdlist='';
var list = $("input[checked='checked']");
if(list.length == 0){
alert("请选择！");
return;
}
for(var i= 0;i<list.length;i++){
    if(i == list.length-1){
     openIdlist += list[i].value;
    }else{
     openIdlist += list[i].value+',';
     }
    $.ajax({   
	       url:'http://localhost:8080/weixin-studio/follower/useraddgroupFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'openId':openIdlist,'groupsId':id},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg == "success"){
	    	    location.reload();
	    	   }
	        }   
		});
}

}
function netInit(groupsId){
   $.ajax({   
	       url:'http://localhost:8080/weixin-studio/follower/netInitFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'groupsId':groupsId},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg == "success"){
	    	      users = data.rows;
					  $.ajax({
								url:'http://localhost:8080/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error方法");
								},
								success:function(data){
									if (data.msg == "success")
									{
										gstr = data.rows;
										
										diaochaSucessHandler(users,gstr);
									}else{
										console.log("getJSON  获得数据异常");
									}
								}
							});
					    
						
					}else{
						console.log("getJSON  获得数据异常");
					}
	    	   }
	         
		});
}
function savegroup(){
var name = $("#groupname").val();
alert(name);
if(name != '' && name != null){
 $.ajax({   
	       url:'http://localhost:8080/weixin-studio/follower/savegroupFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'groupsId':name},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg == "success"){
	    	   alert("新建成功！");
	    	   location.reload();
	    	   }
	        }   
		});
		}
}
function weixin(name){
$("#dialog").dialog({
modal:true//模式对话框，屏蔽
});
}
$(function(){
	
	//带标题的
	$('.demo0').hDialog({title: '测试弹框标题',height: 300});
	

});
</script>


    </body>
</html>


