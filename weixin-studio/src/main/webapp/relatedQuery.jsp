<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.springframework.web.context.ContextLoaderListener" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  xml:lang="zh-CN" lang="zh-CN">
<html>
<head>
		<title>素材挑选区</title>
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/css/style.css" />
	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/easyui.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/easyui/icon.css" />
  	<link type="text/css" rel="stylesheet" href="/weixin-studio/style/colorBox/colorbox.css" />
  	<script src="/weixin-studio/style/js/jquery-1.4.2.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/style/js/jquery.easyui.min.js" type="text/javascript"></script>
  	<script src="/weixin-studio/js/tuwenmaterial/tuwenMaterialOperate.js" type="text/javascript"></script>
  	<style>
p{height: 40px;}
.button_1 li:hover {float:left;margin-left:10px;line-height:26px;border:1px solid #800000;color:#ffffff;cursor: pointer}
.button_1 li span:hover {border:1px solid #d40000;float:left;padding:0 20px;font-size:14px;font-weight:bold;background:url(images/button_1.gif);color:#ffffff;cursor: pointer}
i{color: red; }
</style>
</head>
<body>
<script language="javascript">
	/*创建搜索的Dtagrid*/
	//var QueryAction="/weixin-studio/icms/standard/content/article/testjson.json";
	var QueryAction="/weixin-studio/Material/searchTuWenAction";
	var total = 10;
	$(document).ready(function(){
		$('#RelatedSelected').datagrid({
			width:688,
			pageNumber:1,
			pageSize:10,
			pageList:[10,20,30],
			url:"",
			striped: true,
			idField:"id",
			frozenColumns:[[{field:'ck',checkbox:true}]],
			onLoadSuccess:function(data){
				if($('.datagrid-row-selected').length>=20){
					$('#RelatedSelected').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", true);	
				}else{
					$('#RelatedSelected').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", false);	
				}	
			},
			onSelect:function(rowIndex, rowData){
				var selected = $('.datagrid-row-selected').length/2;
				if(total==selected)
					$('#RelatedSelected').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", true); 
			},
			onUnselect:function(rowIndex, rowData){
				var selected = $('.datagrid-row-selected').length/2;
				if(total!=selected)
					$('#RelatedSelected').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", false); 
			},
			columns:[[				
				{field:"title",title:'标题',width:330,
					formatter:function(value,rec){
						if(rec.url!=null&&rec.url.length>0){
							return '<a href='+rec.url+' target=_blank >'+value+'</a>';
						}else{
							return value;
						}
					}
				},
				{field:"type",title:'类型',width:40,
					formatter:function(value,rowData,rowIndex){
						if(value!=null&&value.length>0){
							if (value=='arti') {
								value = "正文";
							} else if (value=='phoa') {
								value = "图集";
							} else if (value=='vide') {
								value = "视频";
							} else if (value=='audi') {
								value = "音频";
							} else if (value=='videalbum') {
								value = "视频集";
							}
							return value;  
						}else{
							return '';
						}
					}				
				},
				{field:"channelName",title:'所属台',width:80},
				{field:"publishdate",title:'发布时间',width:145,formatter:function(value,rowData,rowIndex){
						if(value==null){
							return "";
						}else{		
							var hours=~~value.substr(8,2)+8;//时区问题加8小时							
							if(hours>23){hours=hours-24;}
							if(hours<10){hours="0"+hours;}
							//return value.substr(0,4)+"-"+value.substr(4,2)+"-"+value.substr(6,2)+" "+hours+":"+value.substr(10,2);
							return value.replace("T"," ");
						}
					}
				}
				]],
			rownumbers:true,
			pagination:true,
			remoteSort:false
		});
		initPagination('RelatedSelected');
		//setChannel();
		//combobox($("#queryRelateType").val());
	});
	
	
	function initPagination(Id){
		var _pag = $('#'+Id).datagrid('getPager');
		if (_pag){
			$(_pag).pagination({
				showPageList:false,
				beforePageText:'第',
				afterPageText:'页  共{pages}页',
				displayMsg:'第 {from} 至 {to}条  共 {total} 条'
			});
		}
    }
	/*验证是否选中*/
  	function checkSelect(tname){
  		var row = $(tname).datagrid('getSelections');
  		if (row==''){
  			$.messager.alert('警告','请先选中一行记录！');
  			return false;
  		}else{
  			return row;
  		}
  	}
	
  	/*加入到固化列表*/
	function join(){
		var row = checkSelect("#RelatedSelected");
		if(row){
			var rdlLen = window.parent.getTotal();
			if((row.length + rdlLen) > 10) {
				$.messager.alert('警告','新闻素材总条数已经超过10条！');
			} else {
				$(row).each(function(i){
					//var currentdate=row[i].currentdate.substr(0,4)+"-"+row[i].currentdate.substr(4,2)+"-"+row[i].currentdate.substr(6,2)+" "+row[i].currentdate.substr(8,2)+":"+row[i].currentdate.substr(10,2);
					//parent.window.insertRelatedData(row[i].id,row[i].title,row[i].url,currentdate,row[i].logoPhoto);
					window.parent.insertRelatedData(row[i].id,row[i].title,row[i].url,row[i].publishdate.replace("T"," "),row[i].logoPhoto,row[i].type);
				});
				window.parent.relatedDataToDiv();//刷新显示。
				window.parent.closeColorBox(false);
			}
		}
		
	}
  	function QueryRelatedData(){
  		var keyword=$("#queryRelateWord").val();
  		//var type=$("#queryRelateType").val();
  		//var channelId=$("#channelId").val();
  		var queryType=$("#queryType").val();
  		var param={};
  		var select="";
  		//alert(type);
  		var type=$('#combobox_data').val();
  		//if(channelId.length>0){
  		//	select=" AND (channelId:"+channelId+")";
  		//}
  		
  		if(queryType == "1"){
  			keyword = "(tag:"+keyword+") " + select;
  		}else{
  			keyword = "((title:"+keyword+"^5) OR (brief:"+keyword+"^4) OR (tag:"+keyword+"^3) OR (content:"+keyword+"^2)) "+select;
  		}  		
  		$.extend(param,{"userInput":keyword});
  		$.extend(param,{"type":type});
  		//$.extend(param,{"channelId":"CHAL1320633484098736"});
  		//$.extend(param,{"interfaceUrl":"http://cmsindex.cntv.net/search_main/"});
  		$.extend(param,{"param":"&version=2.2&indent=on&wt=json&cms=true&sort=publishdate+desc,score+desc"});
		jQuery.ajaxSettings.traditional = true;
		$("#RelatedSelected").datagrid({
			url:QueryAction,
			pageNumber:1,
			queryParams:param,
			remoteSort:false
		});
		initPagination('RelatedSelected');
	}
	

	
</script>
<div class="Area" style="width:auto;">
	<div class="content" >
		<div class="cut17">
		<div class="blank10"></div>
		<s:form action="/weixin-studio/icms/standard/content/article/testjson.json" method="post" theme="simple">
		<div class="block10" style="border:0px">
			<center><h1 style="font-size:20px;font-weight: bold;">数据查询</h1></center>
		</div>
		<div class="block10" style="border:0px;width:610px" >
			 <s:select label="数据检索类型：" id="queryType" name="queryType" list="#{'0':'全文检索','1':'关键字检索'}" value="1" />
			<s:textfield name="queryRelateWord" id="queryRelateWord" value='请输入搜索关键字'  onclick="if(value==defaultValue){value='';}" onBlur="if(!value){value=defaultValue;}" size="20" > </s:textfield>
			&nbsp;类型<s:select id="combobox_data" name="combobox_data" list="#{'ARTI':'正文','PHOA':'图集','VIDE':'视频','AUDI':'音频'}"></s:select>	
			<ul class="button_1" style="float: right;padding-bottom: 20px;">
				<li><span onclick="QueryRelatedData();" id="QueryBtn">查询</span></li>
			</ul>
		</div>
		<div class="block2" style="border:0px;">  
		<table id='RelatedSelected'></table>
		</div>
		<div class="blank10"></div>  
		<div style="padding-left: 230px;">
		<ul class="button_1">
				<li><span onclick="join();">确定</span></li>
				<li><span onclick="window.parent.closeColorBox(false);">取消</span></li>
			</ul>
		</div>
		<div class="blank10"></div>
		</s:form>
		</div>
	</div>
</div>
</body>
</html>