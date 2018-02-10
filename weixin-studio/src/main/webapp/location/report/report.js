$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'签到信息',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:1250,
			//fitColumns:true,
//			url:'/weixin-studio/account/selectAllAccount',
			url:'/weixin-studio/interact/selectReportInteract',
			sortName: 'reportsTime',
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
	             {field:'nickName',title:'昵称',width:100,align:'center',sortable:true},
	  		   
	                {field:'headImg',title:'头像',width:180,align:'center',sortable:true,
		            	 formatter:function(value,rowData,rowIndex){
		            	    	var html_img = "";
		            	    	if(value!=null){
		            	    		html_img = '<a target="_blank" href="'+value+'"><img src='+value+  ' width="64" height="64"></a>';
		            	    	}
		            	    	
		            	    	return html_img;
			            	}
		            },
		            {field:'city',title:'城市',width:100,align:'center',sortable:true},
		            {field:'reportsTime',title:'签到时间',width:200,align:'center',sortable:true}
	            ]
           ],
			toolbar:[
			],
		
		});
		initGenerglPagination('#dataGrid');
	});

