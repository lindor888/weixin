$(document).ready(function(){
	    var meetingid = window.location.href.split("type=")[1];
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'互动信息',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:1100,
//			url:'/weixin-studio/account/selectAllAccount',
			url:'/weixin-studio/interact/meetingcontentInteract?type='+meetingid,
			//sortName: 'updateTime',  按照时间排序
			sortName: 'contentserial', 
			sortOrder: 'desc',
			idField:'meetingcontentid',
			pagination: false, //分页组件
		    singleSelect: true,
		    rownumbers:true,
		    queryParams : {'page':1},
		    frozenColumns:[[{field:'ck',checkbox:true}]],
		    singleSelect: true,
		    selectOnCheck: true,
		    checkOnSelect: true,
	        columns:[
	            [
		            {field:'contentserial',title:'序号',width:100,height:30,align:'center',sortable:true },
		            {field:'contenttime',title:'时间',width:150,heigh:30,align:'center',sortable:true},
		            {field:'content',title:'内容',width:200,align:'center',sortable:true},
	                {field:'contentpersion',title:'发言人',width:80,align:'center',sortable:true},
		            {field:'contentgroup',title:'项目组成员',width:200,align:'center',sortable:true },
		           
		            {field:'meetingcontentid',title:'操作',width:160,sortable:true,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		var html = '';
		            		
		            		html += '<a href="javascript:contentupdate(\''+rowData.meetingcontentid+'\')">修改</a>&nbsp;&nbsp;';
		            		
		            		html += '<a href="javascript:contentshanchu(\''+rowData.meetingcontentid+'\')">删除</a>&nbsp;&nbsp;';

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
						window.location="/weixin-studio/interact/jiemudanViewInteract";
					}
			},
			],
			
		});
		initGenerglPagination('#dataGrid');
	});


//通过
function tongguo(id){
	$.ajax({   
       url:'/weixin-studio/interact/tongguojiemuInteract',
        type: 'POST',   
        async: false,
        data:  {'bean.flag':1,'bean.id':id},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $('#dataGrid').datagrid('reload');
    	   }
        }   
	});
}

/**
 * 删除
 * @param id
 */
function contentshanchu(id){

	
	$.messager.defaults.ok = "确定";
	   $.messager.defaults.cancel = "取消";
	   $.messager.confirm('操作提示', '确定要删除吗？', function(r){
			 if (r){
					$.ajax({   
						   url:'/weixin-studio/interact/shanchucontentInteract',
					       type: 'POST',   
					       async: false,
					       data:  {'meetingContent.meetingcontentid':id},
					       dataType: 'json',  
					       success:function(data){ 
					    	   if(data.msg=="success"){
					    		   $.messager.alert("提示","删除成功！","info",function(r){
					    		   parent.$.fn.colorbox.close();
					    		   $('#dataGrid').datagrid('reload');
					    		   });
					    	   }
					    	   if(data.msg=="fail"){
					    		   $.messager.alert("操作提示","删除失败！","error",function(r){
					    		   $('#dataGrid').datagrid('reload');
					    		   });
					    	   }

					        },
					        error: function(){
					        	$.messager.alert("操作提示","系统错误", "error");
					        }
					    });
		 }
	   });
}

//拒绝
function jujue(id){
	$.ajax({   
       url:'/weixin-studio/interact/jiemudanupdateInteract',
        type: 'POST',   
        async: false,
        data:  {'bean.flag':2,'bean.id':id},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $('#dataGrid').datagrid('reload');
    	   }
        }   
	});
}

//修改
function contentupdate(id){
	$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:500,href:"/weixin-studio/interact/contentupdateInteract?ids="+id,overlayClose:false,onClosed:function(){
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
function selectByPagechakan(){
	var flag = $("#flag").val();
	var nickname = $("#nickname").val();
	$("#dataGrid").datagrid({
		url:'/weixin-studio/interact/selectchakanInteract',
		queryParams:{'bean.flag':flag,'bean.nickname':nickname},
		pageNumber:1
	});
	initGenerglPagination("#dataGrid");
	
}

function shuaxin(){
    var programmeid = window.location.href.split("programmeid=")[1];
	$("#dataGrid").datagrid({
		url:'/weixin-studio/interact/chakanInteract?programmeid='+programmeid,
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
			//selectByPage();
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
function update(id){

	$("#dialog").dialog({
	modal:true //模式对话框，屏蔽
	});

	}