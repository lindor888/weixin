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
 
<link type="text/css" href="/weixin-studio/js/question/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
<link type="text/css" href="/weixin-studio/js/question/css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
<script type="text/javascript" src="/weixin-studio/js/question/js/jquery.js"></script>
<script	type="text/javascript" src="/weixin-studio/js/question/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/weixin-studio/js/question/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="/weixin-studio/js/question/js/jquery-ui-timepicker-zh-CN.js"></script>
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
    <a onclick="usearch()" class="frm_input_append jsSearchInputBt"  style="cursor:pointer">
    	<i class="icon16_common search_gray">搜索</i>&nbsp;
    </a>
    <input id = "uname" type="text" value="" class="frm_input jsSearchInput" placeholder="用户昵称">
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
                                          <!--   <i class="icon_checkbox"></i> -->
                                            <input type="checkbox" class="frm-checkbox" id="selectAll" />
                                            全选                                        </label>&nbsp;
                                        <div id="allGroup"  class="dropdown_wrp dropdown_menu"><a name ="all" onclick="change(name)" href="javascript:;" class="btn dropdown_switch jsDropdownBt"><label class="jsBtLabel">添加到</label><i class="arrow"></i></a>
<div id ="all" class="dropdown_data_container jsDropdownList" style="display: none;">
    <ul id ="alllist" class="dropdown_data_list">
        
            
           
                    
        
    </ul>
</div>
</div>
                                        <a name="0" class="btn btn_default js_allToBlackList_btn" data-gid="0" onclick="userlistgroup(name)">加入黑名单</a>
                                        <a name="wenben" class="btn btn_default js_allToBlackList_btn" data-gid="0" onclick="open_win(name)">文本消息</a>
                                        <a name="tuwen" class="btn btn_default js_allToBlackList_btn" data-gid="0" onclick="open_win(name)">图文消息</a>
                                        
                                    </div>
                                </th>
                            </tr>
                        </thead>
                        <tbody class="tbody" id="userGroups">




                      </tbody>
                    </table>
                </div>
                <div class="tool_area">
                    <div class="pagination_wrp js_pageNavigator"><div class="pagination" id="wxPagebar_1449020361123">
    <span class="page_nav_area">
        <a href="javascript:void(0);" class="btn page_first" style="display: none;"></a>
        <a id="page_prev" href="javascript:page_prev();" class="btn page_prev" ><i class="arrow"></i></a>
        
            <span class="page_num">
                <label id="currentPage">1</label>
                <span class="num_gap">/</span>
                <label id="totalPageNum">2</label>
            </span>
        
        <a id="page_next" href="javascript:page_next();" class="btn page_next"><i class="arrow"></i></a>
        <a href="javascript:void(0);" class="btn page_last" style="display: none;"></a>            
    </span>
    
    <span class="goto_area">
        <input id = "goto_area" type="text">
        <a href="javascript:goto_area();" class="btn page_go">跳转</a>
    </span>
    
</div>
</div>
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
 <div id="dialog" title="消息发送" style="width:900px;display:none">
<p>
     <form id ="weixin" action="" method="post">
  内容：<textarea type="text" name="opt" value="" id = "opt"></textarea>
  <br /><br />
  <input type="button" onclick = "fasong()" value="发送" style="float:right"/>
 </form>
  </p>
</div>
<div id="message" title="兑换信息" style="width:1200px;height:900px;display:none">
<p>
     <form id ="weixin" action="" method="post">
     <div id="exchange">
	   
    </div>
 </form>
  </p>
</div>
        <div class="foot" id="footer">
	
    
	
</div>
<script type="text/javascript">
  
  
  var userid='';
  var div='';
  var currentPage = 1;
  var totalPage='';
  var currentPagegroups = 1;
  var getJSON = function(){
			var url = '';
			var gstr = '';
			var users='';
			currentPage = 1;
			url = '/weixin-studio/follower/netfriendFollower?currentPage='+currentPage;
			
			$.ajax({
				url:url,
				dataType:"json",
				type:"get",
				error:function(){
					console.log("getJSON  error1方法");
				},
				success:function(data){
					if (data.msg == "success")
					{  
					 $("#totalPageNum").html(data.totalPageNum);
					 $("#currentPage").html(currentPage);
					 totalPage = data.totalPageNum;
					  users = data.rows;
					  $.ajax({
								url:'/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error2方法");
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
			 html += '<tr><td class="table_cell user"><div class="user_info"> <a  name="'+voteData[i].openId+'" target="_blank" onclick="mallmessage(name)" class="remark_name">'+voteData[i].nickname+'</a>';
			 html += ' <a target="_blank" class="avatar"><img style="cursor:pointer" name="'+voteData[i].openId+'" src="'+voteData[i].headimgurl+'" data-fakeid="1738122015" class="js_msgSenderAvatar" onclick="weixin(name)"></a>';
			 html += ' <label class="frm_checkbox_label"><input name="subBox" onclick="selected(id)" class="frmcheckbox" type="checkbox"  value="'+voteData[i].openId+'" id="'+i+'"></label>';
			 if(voteData[i].replayBean != "" &&voteData[i].replayBean != null){
			 if(voteData[i].replayBean.length > 0){
			 for(var r= 0;r<voteData[i].replayBean.length;r++){
			 html += '<br/>&nbsp;&nbsp;&nbsp;<a  style="color:#CD9B9B" name="huifu" target="_blank" class="remark_name">&nbsp;&nbsp;回复:'+voteData[i].replayBean[r].replaycontent+'</a>';
			 }
			 }
			 }
			 html += '</td>';
			 html += ' <td class="table_cell user_opr">';
			 
			 html += ' <div id="selectArea2858113980" class="js_selectArea dropdown_menu"><a name="'+voteData[i].openId+i+'" id="'+voteData[i].openId+'"onclick="change(name);" href="javascript:;" class="btn dropdown_switch jsDropdownBt"><label class="jsBtLabel">分组项</label><i class="arrow"></i></a>';
			 html += ' <div id="'+voteData[i].openId+i+'" class="dropdown_data_container jsDropdownList" style="display:none;">';
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
  //查询所有组
  var groups = function(){
			var url = '/weixin-studio/follower/groupsFollower';
			
			$.ajax({
				url:url,
				dataType:"json",
				type:"get",
				error:function(){
					console.log("getJSON  error3方法");
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
            str += '<dt class="inner_menu_item selected"><a onclick="allUser()" class="inner_menu_link" title="全部用户"><strong>全部用户</strong><em class="num">('+data.all+')</em></a></dt>';
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
		getJSON();
		groups();
		});	
function selected(id){
document.getElementById(id).setAttribute("checked","checked");
}
//新建分组和添加到
function change(id){
//debugger;
if(id == "js_groupAdd"){
$("#"+id).attr("style","top:162px;left:1000px;display:block;");
}else{
if($("#"+id).attr("style").indexOf("none") > -1){
$("#"+id).attr("style","display:block");
}else{
$("#"+id).attr("style","display:none");
}
}

//document.getElementById(id).setAttribute("style","display:block");
//新建分组取消
}
function hide(){
$("#js_groupAdd").attr("style","top:162px;left:1000px;display:none;");
}
//单个用户添加组
function usergroup(id){
var openId = $("#"+id).attr("name");
var gid = id.split("gr")[0];
       $.ajax({   
	       url:'/weixin-studio/follower/useraddgroupFollower',
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
//多个用户添加组
function userlistgroup(id){
var arrList = [];
var openIdlist='';
var list = $("input[name='subBox']");
for(var i=0;i<list.length;i++){ 
			if(list[i].checked){
			arrList.push(list[i]);
			}
		} 

//alert(list.length);
if(arrList.length == 0){
alert("请选择！");
return;
}
for(var i= 0;i<arrList.length;i++){
    if(i == arrList.length-1){
     openIdlist += arrList[i].value;
    }else{
     openIdlist += arrList[i].value+',';
     }
    $.ajax({   
	       url:'/weixin-studio/follower/useraddgroupFollower',
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
//点击切换组
function netInit(groupsId){
  currentPagegroups = 1;
   $.ajax({   
	       url:'/weixin-studio/follower/netInitFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'groupsId':groupsId,'currentPage':currentPagegroups},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg == "success"){
	    	    var html='';
			   html += '<span class="page_nav_area">';
			   html += ' <a style="display: none;" class="btn page_first" href="javascript:void(0);"></a>';     
			   html += '<a class="btn page_prev" href="javascript:page_prevgroups('+groupsId+');" id="page_prevgroups"><i class="arrow"></i></a>';    
			   html += '&nbsp;&nbsp;<span class="page_num"><label id="currentPagegroups">1</label><span class="num_gap">/</span><label id="totalPageNumgroups">'+data.totalPageNum+'</label></span>';     
			   html += '&nbsp;&nbsp;<a class="btn page_next" href="javascript:page_nextgroups('+groupsId+');" id="page_nextgroups"><i class="arrow"></i></a>';
			   html += '<a style="display: none;" class="btn page_last" href="javascript:void(0);"></a>';
			   html += '</span>';
			   html += '<span class="goto_area">';
			   html += '<input type="text" id="goto_areagroups">';
			   html += '<a class="btn page_go" href="javascript:goto_areagroup('+groupsId+');">跳转</a>';
			   html += '</span>';
			   totalPage = data.totalPageNum;
			   $("#wxPagebar_1449020361123").html(html);
	    	      users = data.rows;
					  $.ajax({
								url:'/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error4方法");
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
//全部用户
function allUser(){
      // getJSON();
       location.reload();
}
//新建分组
function savegroup(){
var name = $("#groupname").val();
if(name != '' && name != null){
 $.ajax({   
	       url:'/weixin-studio/follower/savegroupFollower',
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
//点击用户头像发消息
function weixin(name){
userid = name;
$("#dialog").dialog({
modal:true//模式对话框，屏蔽
});
}

function fasong(){
var content = $("#opt").val().trim();
if(content==""){
alert("内容不能为空！");
return;
}
$.ajax({   
	       url:'/weixin-studio/follower/fasongFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'openId':userid,'content':content},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg == "success"){
	    	   alert("发送成功！");
	    	   }
	    	   if(data.msg == "error"){
	    	   alert("发送失败！");
	    	   }
	    	    
	        }   
		});
}
//页面跳转发消息
function open_win(name) 
{
var arrList = [];
var openIdlist='';
var list = $("input[name='subBox']");
for(var i=0;i<list.length;i++){ 
			if(list[i].checked){
			arrList.push(list[i]);
			}
		} 

//alert(list.length);
if(arrList.length == 0){
alert("请选择！");
return;
}
for(var i= 0;i<arrList.length;i++){
    if(i == arrList.length-1){
     openIdlist += arrList[i].value;
    }else{
     openIdlist += arrList[i].value+',';
     }
  }
  //alert(openIdlist);
//window.open("/weixin-studio/interact/tuisongInteract.jsp?"+openIdlist,"_self")
$.ajax({   
	       url:'/weixin-studio/follower/messageFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'openId':openIdlist,'tname':name},
	       dataType: 'json',  
	       success:function(data){ 
	    	   
	    	   window.open(data.msg,"_self");
	    	   
	        }   
		});
}
//全选全不选
$(function() { 
$("#selectAll").click(function() { 
  if ($(this).attr("checked")) { // 全选 

                $("input[name='subBox']").each(function () {
                    $(this).attr("checked", true);
                });
            }
            else { // 取消全选 

                $("input[name='subBox']").each(function () {
                    $(this).attr("checked", false);
                });
            }
});
}); 
//查询
function usearch(){
var uname = $("#uname").val();
 $.ajax({   
	       url:'/weixin-studio/follower/usearchFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'tname':uname},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg == "success"){
	    	      users = data.rows;
					  $.ajax({
								url:'/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error5方法");
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
function mallmessage(name){
///weixin-studio/goods/initOrder
$.ajax({
				url:"/weixin-studio/goods/selectoneOrder?name="+name,
				dataType:"json",
				type:"get",
				async:false,
				success:function(data){
				//debugger;
				if (data.rows.length > 0) {
	            var html='';
	            html += '<div>昵称:<p style="color:#F4A460" id="goodsname">'+data.rows[0].nickName+'</p></div>';
	            html += '<div>性别:<p style="color:#F4A460" id="goodsnum">'+data.rows[0].sex+'</p></div>';
	            html += '<div>生日:<p style="color:#F4A460" id="integral">'+data.rows[0].birthday+'</p></div>';
	            html += '<div>电话:<p style="color:#F4A460" id="address">'+data.rows[0].telephone+'</p></div>';
	            html += '<div>手机:<p style="color:#F4A460" id="goodstime">'+data.rows[0].mobilePhone+'</p></div>';
	            html += '<div>住址:<p style="color:#F4A460" id="address">'+data.rows[0].address+'</p></div>';
	            html += '<div>收货地址:<p id="address">'+data.rows[0].reciveAddress+'</p></div>';
	           $("#exchange").html(html);
	           
				$("#message").dialog({
                modal:true //模式对话框，屏蔽
               
                });
                 return;
				}else{
				$("#exchange").html("该用户未注册！");
				$("#message").dialog({
                modal:true //模式对话框，屏蔽
                });
				}
				//$("#conten").html(html);
					//alert(data.rows[0].questionname);
					
				},
				error:function(data){
					alert("error");
				}
			}); 


}
function page_next(){
           currentPage++;
            var url = '';
			var gstr = '';
			var users='';
		url = '/weixin-studio/follower/netfriendFollower?currentPage='+currentPage;
			
			$.ajax({
				url:url,
				dataType:"json",
				type:"get",
				error:function(){
					console.log("getJSON  error1方法");
				},
				success:function(data){
					if (data.msg == "success")
					{
					  if(currentPage > data.totalPageNum){
					     alert("已经是最后一页了！");
					     currentPage = data.totalPageNum;
					     return;
					  }
					  $("#currentPage").html(currentPage);
					  users = data.rows;
					  $.ajax({
								url:'/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error2方法");
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
function  page_prev(){
            currentPage--;
            var url = '';
			var gstr = '';
			var users='';
			 if( currentPage < 1){
			      alert("已经是第一页了！");
			      currentPage = 1;
					return;
				}
		url = '/weixin-studio/follower/netfriendFollower?currentPage='+currentPage;
			
			$.ajax({
				url:url,
				dataType:"json",
				type:"get",
				error:function(){
					console.log("getJSON  error1方法");
				},
				success:function(data){
					if (data.msg == "success")
					{
					 
					  $("#currentPage").html(currentPage);
					  users = data.rows;
					  
					  $.ajax({
								url:'/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error2方法");
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
function goto_area(){
  currentPage = $("#goto_area").val();
   
  if(currentPage > totalPage || currentPage < 1){
       alert("请输入正确的页码");
       
       return;
  }
        var url = '';
		var gstr = '';
		var users='';
		url = '/weixin-studio/follower/netfriendFollower?currentPage='+currentPage;
			
			$.ajax({
				url:url,
				dataType:"json",
				type:"get",
				error:function(){
					console.log("getJSON  error1方法");
				},
				success:function(data){
					if (data.msg == "success")
					{
					 
					  $("#currentPage").html(currentPage);
					  users = data.rows;
					  
					  $.ajax({
								url:'/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error2方法");
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
function page_nextgroups(groupsId){
    currentPagegroups++;
$.ajax({   
	       url:'/weixin-studio/follower/netInitFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'groupsId':groupsId,'currentPage':currentPagegroups},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg == "success"){
	    	   if(currentPagegroups > data.totalPageNum){
					     alert("已经是最后一页了！");
					     currentPagegroups = data.totalPageNum;
					     return;
					  }
			   $("#currentPagegroups").html(currentPagegroups);
			   $("#totalPageNumgroups").html(data.totalPageNum);
	    	      users = data.rows; 
	    	      
					  $.ajax({
								url:'/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error4方法");
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
function page_prevgroups(groupsId){
    currentPagegroups--;
     if( currentPagegroups < 1){
			      alert("已经是第一页了！");
			      currentPagegroups = 1;
					return;
		}
$.ajax({   
	       url:'/weixin-studio/follower/netInitFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'groupsId':groupsId,'currentPage':currentPagegroups},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg == "success"){
			   $("#currentPagegroups").html(currentPagegroups);
			   $("#totalPageNumgroups").html(data.totalPageNum);
	    	      users = data.rows; 
	    	      
					  $.ajax({
								url:'/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error4方法");
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
function goto_areagroup(groupsId){
 currentPagegroups = $("#goto_areagroups").val();
  if(currentPagegroups > totalPage || currentPagegroups < 1){
       alert("请输入正确的页码");
       
       return;
  }
  
  $.ajax({   
	       url:'/weixin-studio/follower/netInitFollower',
	        type: 'POST',   
	        async: false,
	        data:  {'groupsId':groupsId,'currentPage':currentPagegroups},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg == "success"){
			   $("#currentPagegroups").html(currentPagegroups);
			   $("#totalPageNumgroups").html(data.totalPageNum);
	    	      users = data.rows; 
	    	      
					  $.ajax({
								url:'/weixin-studio/follower/groupsFollower',
								dataType:"json",
								type:"get",
								error:function(){
									console.log("getJSON  error4方法");
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
</script>


    </body>
</html>


