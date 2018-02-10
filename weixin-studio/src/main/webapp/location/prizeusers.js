$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'中奖信息',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:1250,
			//fitColumns:true,
//			url:'/weixin-studio/account/selectAllAccount',
			url:'/weixin-studio/interact/prizeusersInteract',
			sortName: 'updateTime',
			sortOrder: 'desc',
			idField:'id',
			pagination: true,
		    singleSelect: true,
		    rownumbers:true,
		    queryParams : {'example.page':1},
		    frozenColumns:[[{field:'ck',checkbox:true}]],
		    singleSelect: false,
		    selectOnCheck: true,
		    checkOnSelect: true,
	        columns:[
	            [
	             {field:'headimgurl',title:'头像',width:100,align:'center',sortable:true,
	            	    formatter:function(value,rowData,rowIndex){
	            	    	var html_img = "";
	            	    	if(value!=null){
	            	    		html_img = '<a target="_blank" ><img name = '+rowData.openid+' onclick="weixin(name)" src='+value+  ' width="64" height="64" style="cursor:pointer"></a>';
	            	    	}
	            	    	
	            	    	return html_img;
		            	}
		            },
		            {field:'nickname',title:'昵称',width:100,align:'center',sortable:true },
		            {field:'sex',title:'性别',width:100,align:'center',sortable:true},
		            {field:'city',title:'地区',width:100,align:'center',sortable:true},
	                {field:'province',title:'城市',width:100,align:'center',sortable:true},
	                {field:'country',title:'国家',width:100,align:'center',sortable:true },
	                {field:'prizeName',title:'奖品',width:150,align:'center',sortable:true},
	                {field:'prizeTime',title:'获奖时间',width:200,align:'center',sortable:true},
	            ]
           ],
//			toolbar:[{
//			text:'添加',
//				iconCls:'icon-add',
//				disabled:false,
//				handler:function(){
//					$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:500,href:"/weixin-studio/interact/addInteract.jsp",overlayClose:false,onClosed:function(){
//							$('#dataGrid').datagrid("reload");
//						}});
//					}
//			},
//	
//			
//		
//			
//			],
//			
			//onLoadSuccess:function(data){
			//	$.messager.defaults.ok = "确定";
			//	if(data.total==0 && state=="none"){$.messager.alert("提示","根据您的查询条件，未找到相关内容，请重试！","warning");}
			//}
		});
		initGenerglPagination('#dataGrid');
	});


