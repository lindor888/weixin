$(document).ready(function(){
		initGenerglDataGrid('#epgList');
		$('#epgList').datagrid({
			title:'EPG节目单管理',
			//pageNumber : 1,
			pageSize : 10,
			loadMsg:'正在加载数据，请等待..............',
			pageList:[10,20,40],
//			url:'/weixin-studio/account/selectAllAccount',
			url:'/weixin-studio/epg/getListEpg',
			//sortName: 'updateTime',  按照时间排序
			sortName: 'start_time', 
			sortOrder: 'desc',
			idField:'reservation_id',
			queryParams : {'page':1,'rows':10},
			pagination: true, //分页组件
		    rownumbers:true,
		    remoteSort:false,
			nowrap:false,
			width:1200,
		   // queryParams : {'example.page':1},
		    frozenColumns:[[{field:'ck',checkbox:true}]],
		    singleSelect: false,
		    selectOnCheck: true,
		    checkOnSelect: true,
	        columns:[
	            [
		           	{field:'program_name',title:'节目名称',width:180,align:'center',sortable:false},
		            {field:'start_time',title:'开始时间',width:160,align:'center',sortable:false,
	            	formatter:function(value,rowData,rowIndex){
	            		return value.replace("T"," ");
	            		}
		            },
		        	{field:'end_time',title:'结束时间',width:160,align:'center',sortable:false,
		            	formatter:function(value,rowData,rowIndex){
		            		return value.replace("T"," ");
		            		}
		        	},
		        	{field:'live_url',title:'播放地址',width:320,align:'center',sortable:false},
		        	{field:'reservation_id',title:'操作',width:195,align:'center',sortable:false,
		        		formatter:function(value,rowDate,rowIndex) {
		        			var updatehtm = '<a class="easyui-linkbutton l-btn l-btn-plain" onclick="modify(\''+value+'\')"><span class="l-btn-left"><span class="l-btn-text icon-edit" style="padding-left: 20px;">修改</span></span></a>';
		        			var delhtm = '<a class="easyui-linkbutton l-btn l-btn-plain" onclick="deleteText(\''+value+'\')"><span class="l-btn-left"><span class="l-btn-text icon-remove" style="padding-left: 20px;">删除</span></span></a>';
		        			return updatehtm + '&nbsp;&nbsp;&nbsp;' + '&nbsp;&nbsp;&nbsp;'+ delhtm;
		        		}
		        	}
	            ]
           ],
           toolbar:[{
				text:'添加节目',
				iconCls:'icon-add',
				handler:function(){
					add();
			    }
			}
			]
		});
		initGenerglPagination2('#epgList');
});

//修改节目
function modify(id){
	var _href = '/weixin-studio/epg/toUpdateEpg';
	if(id != null) {
		_href += '?record.reservation_id=' + id;
	}
	$.fn.colorbox({iframe:true,innerWidth:736, innerHeight:400, href:_href,overlayClose:false,onClosed:function(){$('#epgList').datagrid('reload');}});
}
function add(){
	$.fn.colorbox({iframe:true,innerWidth:736,innerHeight:400,href:"/weixin-studio/epg/editepg.jsp",overlayClose:false,onClosed:function(){
		$('#epgList').datagrid("reload");
	}});
}

function deleteText(id){
	$.messager.defaults.ok = "确定";
   $.messager.defaults.cancel = "取消";
   $.messager.confirm('操作提示', '确定要删除吗？', function(r){
		 if (r){
				$.ajax({   
				       url:'/weixin-studio/epg/deleteOneEpg',
				        type: 'POST',   
				        async: false,
				        data:  {"record.reservation_id":id},
				       dataType: 'json',  
				       success:function(data){ 
				    	   if(data.msg=="success"){
				    		   $.messager.alert("提示","删除成功！","info",function(r){
				    		   parent.$.fn.colorbox.close();
				    		   $('#epgList').datagrid('reload');
				    		   });
				    	   }
				    	   if(data.msg=="fail"){
				    		   $.messager.alert("操作提示","删除失败！","error",function(r){
				    		   $('#epgList').datagrid('reload');
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


function initGenerglPagination2(id){
	  var _pag = $(id).datagrid("getPager");
	  if (_pag){
	  $(_pag).pagination({
	  showPageList:true,
	  beforePageText:" 第 ",
	  afterPageText:" 页 共{pages}页 ",
	  displayMsg:" 第 {from} 至 {to}条  共 {total} 条 "
	  });
	  }
	}

function query(){
 		var start_time=$('#start_time').val();
 		var program_name=$('#program_name').val();
 		if(start_time !=null || program_name != null){
 		//查询
 			$("#epgList").datagrid({
 				url:'/weixin-studio/epg/queryEpg',
 				queryParams:{'record.program_name':program_name,'record.start_time':start_time},
 				pageNumber:1
 			});
 			initGenerglPagination2("#epgList");
 		}
}
