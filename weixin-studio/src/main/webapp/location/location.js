var zhutiid = window.location.href.split("zhutiid=")[1];
$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'位置信息',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:1250,
			//fitColumns:true,
//			url:'/weixin-studio/account/selectAllAccount',
			url:'/weixin-studio/interact/selectLocationInteract?zhutiid='+zhutiid,
			sortName: 'updateTime',
			sortOrder: 'desc',
			idField:'interactid',
			pagination: true,
		    singleSelect: true,
		    rownumbers:true,
		    queryParams : {'example.page':1},
		    frozenColumns:[[{field:'ck',checkbox:true}]],
		    singleSelect: true,
		    selectOnCheck: true,
		    checkOnSelect: true,
	        columns:[
	            [
		            
		            {field:'nickname',title:'昵称',width:80,align:'center',
		            	 formatter:function(value,rowData,rowIndex){
		            	    	var html_img = "";
		            	    	if(value!=null){
		            	    		html_img = '<a style="cursor:pointer" target="_blank" name = '+rowData.openid+' onclick="weixin(name)">'+value+'</a>';
		            	    	}
		            	    	
		            	    	return html_img;
			            	}
		            
		            },
		            {field:'title',title:'主题',width:100,align:'center',sortable:true},
		            {field:'content',title:'内容',width:150,align:'center',sortable:true},
	                {field:'send_time',title:'时间',width:150,align:'center',sortable:true},
	                {field:'latitude',title:'纬度',width:100,align:'center',sortable:true,hidden:true},
	                {field:'longitude',title:'经度',width:100,align:'center',sortable:true},
	                {field:'zhutiid',title:'地图',width:170,align:'center',sortable:true,
	            	    formatter:function(value,rowData,rowIndex){
	            	    	var position = rowData.longitude +','+rowData.latitude;
	            	   
	            	    	var url = 'http://api.map.baidu.com/staticimage?center='+position+'&width=300&height=200&zoom=16&markers='+position;
	            	    	var html_img = "";
	            	    	if(value!=null){
	            	    		html_img = '<img src='+url+' width="140" height="100">';
	            	    	}
	            	    	
	            	    	return html_img;
		            	}
		            },
		            {field:'flag',title:'处理状态',width:80,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		switch(value){
		            			case 0: return "未处理"; break;
		            			case 1: return "<a style='color:red'>审核通过</a>"; break;
		            			case 2: return "审核未通过"; break;
		            		}
		            	
		            	}
		            },
		            {field:'openid',title:'操作',width:160,sortable:true,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		var html = '';
		            		var position = rowData.longitude +','+rowData.latitude;
		            		var url = 'map.jsp?position='+position;
		            		
		            		html += '<a href="javascript:tongguo(\''+rowData.interactid+'\')">通过</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:jujue(\''+rowData.interactid+'\')">拒绝</a>&nbsp;&nbsp;';
		            		if(value!=null){
	            				html += '<a href='+url+'>查看详情</a>';
	            			
	            		    }
		            		return html;
		            	}
		            }
	            ]
           ],
			
		});
		initGenerglPagination('#dataGrid');
	});

function jujue(interactid){
	$.ajax({   
       url:'/weixin-studio/interact/locationFlagInteract',
        type: 'POST',   
        async: false,
        data:  {'interactLocation.interactid':interactid,'interactLocation.flag':2},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $('#dataGrid').datagrid('reload');
    	   }
        }   
	});
}
function tongguo(interactid){
	$.ajax({   
       url:'/weixin-studio/interact/locationFlagInteract',
        type: 'POST',   
        async: false,
        data:  {'interactLocation.interactid':interactid,'interactLocation.flag':1},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $('#dataGrid').datagrid('reload');
    	   }
        }   
	});
}
