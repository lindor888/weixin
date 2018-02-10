$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'节目单列表',
			pageNumber : 1,
			pageSize : 5,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:1100,
//			url:'/weixin-studio/account/selectAllAccount',
			url:'/weixin-studio/interact/viewInteract',
			//sortName: 'updateTime',  按照时间排序
			sortName: 'id',
			sortOrder: 'desc',
			idField:'id',
			pagination: true, //分页组件
		    singleSelect: true,
		    rownumbers:true,
		    frozenColumns:[[{field:'ck',checkbox:true}]],
		    singleSelect: false,
		    selectOnCheck: true,
		    checkOnSelect: true,
		    
		   // fitColumns: true,
	        columns:[
	            [
		            
		            {field:'programme_name',title:'节目单名称',width:200,align:'center',sortable:true ,},
		            {field:'programme_account_name',title:'创建者',width:200,align:'center',sortable:true},
		            {field:'programme_time',title:'时间',width:200,align:'center',sortable:true,fitColumns: true},
		            {field:'flag',title:'处理状态',width:100,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		switch(value){
		            			case 0: return "未处理"; break;
		            			case 1: return "<a style='color:red'>审核通过</a>"; break;
		            			case 2: return "审核未通过"; break;
		            		}
		            	
		            	}
		            },

		            {field:'id',title:'操作',width:260,sortable:true,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		var html = '';
		            		
		            	
		            		html += '<a href="javascript:update(\''+rowData.programme_id+'\')">修改</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:programmetongguo(\''+rowData.programme_id+'\')">通过</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:chakan(\''+rowData.programme_id+'\')">查看</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:daochu(\''+rowData.programme_id+'\')">导出</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:shanchu(\''+rowData.programme_id+'\')">删除</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:tianjia(\''+rowData.programme_id+'\')">添加</a>&nbsp;&nbsp;';
		            		var openId = rowData.openId;
		            		var huifu = rowData.huifu;
		            	
		            		return html;
		            	}
		            }
	            ]
           ],
			toolbar:[
			{
				text:'返回',
					iconCls:'icon-ok',
					disabled:false,
					handler:function(){
						history.go(-1);
					//window.location="/weixin-studio/interact/bianjiInteract";
					}
			},
			],
			
			//onLoadSuccess:function(data){
			//	$.messager.defaults.ok = "确定";
			//	if(data.total==0 && state=="none"){$.messager.alert("提示","根据您的查询条件，未找到相关内容，请重试！","warning");}
			//}
		});
		initGenerglPagination('#dataGrid');
	});


//通过
function programmetongguo(programme_id){
	$.ajax({   
        url:'/weixin-studio/interact/programmeTongguoInteract',
        type: 'POST',   
        async: false,
        data:  {'programmeId':programme_id},
        dataType: 'json',  
        success:function(data){ 
    	   if(data.msg=="success"){
    		   $('#dataGrid').datagrid('reload');
    	   }
        }   
	});
}
/**
 * 查看
 * @param id
 */
function chakan(id){
	
	window.location = "/weixin-studio/interact/chakanInteract?programmeid="+id;
}


/**
 * 导出
 * @param id
 */
function daochu(programme_id){

	if(programme_id){
		
     window.location='/weixin-studio/download.do?programme_id='+programme_id;
     //window.location='/weixin-studio/interact/viewInteract';
	}
}
/**
 * 选择互动节目
 * @param id
 */
function shanchu(programme_id){

	$.ajax({   
       url:'/weixin-studio/interact/shanchujiemudanInteract',
       type: 'POST',   
       async: false,
       data:  {'programmename.programme_id':programme_id},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   //$.messager.alert('提示','选择成功！');
    		   $('#dataGrid').datagrid('reload');
    	   }else{
    		   
    		   $.messager.alert('提示','删除失败！');
    	   }
        }   
	});
}

//拒绝
function jujue(id){
	$.ajax({   
       url:'/weixin-studio/interact/updateInteract',
        type: 'POST',   
        async: false,
        data:  {'programmename.programme_id':programme_id},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $('#dataGrid').datagrid('reload');
    	   }
        }   
	});
}
function tianjia(programme_id){
	
	if(programme_id !="" && programme_id != null){
		
	     window.location='/weixin-studio/interact/tianjiaInteract?programmeId='+programme_id;
	    
		}
}
//修改
function update(programme_id){
	$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:500,href:"/weixin-studio/interact/xiugaiInteract.jsp?programme_id="+programme_id,overlayClose:false,onClosed:function(){
		$('#dataGrid').datagrid("reload");
	}});
}

//回复
function huifu(id,type){
	$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:500,href:"/weixin-studio/interact/huifuInteract.jsp?id="+id+"&type="+type,overlayClose:false,onClosed:function(){
		$('#dataGrid').datagrid("reload");
	}});
}

//查询
function selectByPagebianji(){
	var flag = $("#flag").val();
	var programme_name = $("#nickname").val();
	$("#dataGrid").datagrid({
		url:'/weixin-studio/interact/selectbianjiInteract',
		queryParams:{'programmename.flag':flag,'programmename.programme_name':programme_name},
		pageNumber:1
	});
	initGenerglPagination("#dataGrid");
	
}

function shuaxin(){
	$("#dataGrid").datagrid({
		url:'/weixin-studio/interact/viewInteract',
		queryParams:{'example.flag':'','example.nickname':''},
		pageNumber:1
	});
	$("#flag").val('');
	$("#nickname").val('');
	initGenerglPagination("#dataGrid");
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

$(function(){
	window.setInterval(function(){
		var curPage = $("#dataGrid").datagrid('getPager').data("pagination").options.pageNumber;
		if(curPage==1){
			selectByPage();
		}
	},30000);
});

function xKeyEvent(e){
	var e = e || window.event;
	switch(e.keyCode | e.which | e.charCode) {//按键 ASCII 码值 
		case 112:{
			shuaxin();
		}
	}  
}

//旋转视频
function xuanzhuanShipin(xuanzhuan,id){
	$.ajax({   
	       url:'/weixin-studio/interact/updateInteract',
	        type: 'POST',   
	        async: false,
	        data:  {'bean.xuanzhuan':xuanzhuan,'bean.id':id},
	       dataType: 'json',  
	       success:function(data){ 
	    	   
	        }   
		});
}

//真实旋转视频
function xuanzhuanVideo(id){
	$.ajax({   
	       url:'/weixin-studio/interact/xuanzhuanVideoInteract',
	        type: 'POST',   
	        async: false,
	        data:  {'bean.id':id},
	       dataType: 'json',  
	       success:function(data){ 
	    	   $('#dataGrid').datagrid("reload");
	        }   
		});
}
