$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'写字活动信息',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:900,
//			url:'${web.context.path}/account/selectAllAccount',
			url:'${web.context.path}/writewords/selectWriteWordsActivity',
			sortName: 'updateTime',
			sortOrder: 'desc',
			idField:'activityId',
			pagination: true,
		    singleSelect: true,
		    rownumbers:true,
		    queryParams : {'example.page':1},
	        columns:[
	            [
		            {field:'activityTitle',title:'名称',width:100,align:'center',sortable:true },
		            {field:'beginTime',title:'开始时间',width:180,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		return value.replace('T',' ');
		            	}
		            },
		            {field:'lastTime',title:'持续时间',width:80,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		return value+"秒";
		            	}
		            },
		            {field:'flag',title:'状态',width:100,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		switch(value){
		            			case 0: return "未发布"; break;
		            			case 1: return "<a style='color:red'>已发布</a>"; break;
		            		}
		            	}
		            },
		            {field:'updateTime',title:'数据更新时间',width:180,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		return value.replace('T',' ');
		            	}
		            },
		            {field:'id',title:'操作',width:150,sortable:true,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		var html = '';
		            		html += '<a href="javascript:update(\''+rowData.activityId+'\')">修改</a>&nbsp;&nbsp;';
		            		if(rowData.flag==0){
		            			html += '<a href="javascript:fabu(\''+rowData.activityId+'\')">发布</a>&nbsp;&nbsp;';
		            		}else{
		            			html += '<a href="javascript:huishou(\''+rowData.activityId+'\')">回收</a>&nbsp;&nbsp;';
		            		}
		            		return html;
		            	}
		            }
	            ]
           ],
			toolbar:[{
			text:'添加',
				iconCls:'icon-add',
				disabled:false,
				handler:function(){
					$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:500,href:"${web.context.path}/writewords/activity/addActivity.jsp",overlayClose:false,onClosed:function(){
							$('#dataGrid').datagrid("reload");
						}});
					}
			}
			],
			//onLoadSuccess:function(data){
			//	$.messager.defaults.ok = "确定";
			//	if(data.total==0 && state=="none"){$.messager.alert("提示","根据您的查询条件，未找到相关内容，请重试！","warning");}
			//}
		});
		initGenerglPagination('#dataGrid');
	});


//发布
function fabu(id){
	$.ajax({   
       url:'${web.context.path}/writewords/fabuWriteWordsActivity',
        type: 'POST',   
        async: false,
        data:  {'bean.flag':1,'bean.activityId':id},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $.messager.alert("提示","操作成功！","info",function(r){
    			   $('#dataGrid').datagrid('reload');
    		   });
    	   }
        }   
	});
}

//回收
function huishou(id){
	$.ajax({   
       url:'${web.context.path}/writewords/huishouWriteWordsActivity',
        type: 'POST',   
        async: false,
        data:  {'bean.flag':0,'bean.activityId':id},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $.messager.alert("提示","操作成功！","info",function(r){
    			   $('#dataGrid').datagrid('reload');
    		   });
    	   }
        }   
	});
}

//修改
function update(id){
	$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:500,href:"${web.context.path}/writewords/activity/updateActivity.jsp?id="+id,overlayClose:false,onClosed:function(){
		$('#dataGrid').datagrid("reload");
	}});
}


//查询
function selectByPage(){
	var flag = $("#flag").val();
	var activityTitle = $("#activityTitle").val();
	$("#dataGrid").datagrid({
		url:'${web.context.path}/writewords/selectWriteWordsActivity',
		queryParams:{'example.flag':flag,'example.activityTitle':activityTitle},
		pageNumber:1
	});
	initGenerglPagination("#dataGrid");
	
}
