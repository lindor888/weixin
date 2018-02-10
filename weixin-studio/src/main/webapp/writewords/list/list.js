$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'写字活动信息',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:900,
//			url:'${web.context.path}/account/selectAllAccount',
			url:'${web.context.path}/writewords/selectWriteWordsList',
			sortName: 'insertTime',
			sortOrder: 'desc',
			idField:'id',
			pagination: true,
		    singleSelect: true,
		    rownumbers:true,
		    queryParams : {'example.page':1},
	        columns:[
	            [
		            {field:'activityTitle',title:'活动名称',width:200,align:'center',sortable:true },
		            {field:'headimgurl',title:'头像',width:100,align:'center',sortable:true,
	            	    formatter:function(value,rowData,rowIndex){
	            	    	var html_img = "";
	            	    	if(value!=null){
	            	    		html_img = '<a target="_blank" href="'+value+'"><img src='+value+  ' width="64" height="64"></a>';
	            	    	}
	            	    	
	            	    	return html_img;
		            	}
		            },
		            {field:'nickname',title:'昵称',width:100,align:'center',sortable:true },
		            {field:'sex',title:'性别',width:50,align:'center',sortable:true},
		            {field:'image',title:'字',width:100,align:'center',sortable:true,
	            	    formatter:function(value,rowData,rowIndex){
	            	    	var html_img = "";
	            	    	if(value!=null){
	            	    		html_img = '<a target="_blank" href="'+value+'"><img src='+value+  ' width="64" height="64"></a>';
	            	    	}
	            	    	return html_img;
		            	}
		            },
		            {field:'insertTime',title:'提交时间',width:180,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		return value.replace('T',' ');
		            	}
		            },
	            ]
           ],
			/*toolbar:[{
			text:'添加',
				iconCls:'icon-add',
				disabled:false,
				handler:function(){
					$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:500,href:"${web.context.path}/writewords/activity/addActivity.jsp",overlayClose:false,onClosed:function(){
							$('#dataGrid').datagrid("reload");
						}});
					}
			}
			],*/
			//onLoadSuccess:function(data){
			//	$.messager.defaults.ok = "确定";
			//	if(data.total==0 && state=="none"){$.messager.alert("提示","根据您的查询条件，未找到相关内容，请重试！","warning");}
			//}
		});
		initGenerglPagination('#dataGrid');
	});


//发布
/*function fabu(id){
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
}*/


//查询
function selectByPage(){
	var beginTimeStr = $("#beginTimeStr").val();
	var activityTitle = $("#activityTitle").val();
	$("#dataGrid").datagrid({
		url:'${web.context.path}/writewords/selectWriteWordsList',
		queryParams:{'example.beginTimeStr':beginTimeStr,'example.activityTitle':activityTitle},
		pageNumber:1
	});
	initGenerglPagination("#dataGrid");
	
}
