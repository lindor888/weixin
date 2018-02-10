$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'敏感词',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:800,
			url:'${web.context.path}/words/selectWords',
			sortName: 'id',
			sortOrder: 'desc',
			idField:'id',
			pagination: true,
		    singleSelect: true,
		    rownumbers:true,
		    queryParams : {'example.page':1},
	        columns:[
	            [
		            {field:'content',title:'敏感词',width:550,align:'center',sortable:true },
		            {field:'id',title:'操作',width:200,sortable:true,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		var html = '';
		            		html += '<a href="javascript:update(\''+rowData.id+'\')">修改</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:del(\''+rowData.id+'\')">删除</a>';
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
					$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:450,href:"${web.context.path}/words/toAddWords",overlayClose:false,onClosed:function(){
							$('#dataGrid').datagrid("reload");
						}});
				}
			}
			],
		});
		initGenerglPagination('#dataGrid');
	});



function update(id){
	$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:450,href:"${web.context.path}/words/toUpdateWords?bean.id="+id,overlayClose:false,onClosed:function(){
		$('#dataGrid').datagrid("reload");
	}});
}

function del(id){
	$.ajax({   
       url:'${web.context.path}/words/delWords',
        type: 'POST',   
        async: false,
        data:  {'bean.id':id},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $.messager.alert("提示","删除成功！","info",function(r){
    			   $('#dataGrid').datagrid('reload');
    		   });
    	   }
        }   
	});
}

