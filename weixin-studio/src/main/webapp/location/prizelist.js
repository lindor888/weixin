$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'奖品管理',
			//pageNumber : 1,
			pageSize : 10,
			loadMsg:'正在加载数据，请等待..............',
			pageList:[10,20,40],
//			url:'/weixin-studio/account/selectAllAccount',
			url:'/weixin-studio/interact/prizelistInteract',
			//sortName: 'updateTime',  按照时间排序
			sortName: 'createtime', 
			sortOrder: 'desc',
			idField:'prizetitleId',
			queryParams : {'page':1,'rows':10},
			pagination: true, //分页组件
		    rownumbers:true,
		    remoteSort:false,
			nowrap:false,
			width:1200,
		   // queryParams : {'example.page':1},
		    frozenColumns:[[{field:'ck',checkbox:true}]],
		    singleSelect: true,
		    selectOnCheck: true,
		    checkOnSelect: true,
	        columns:[
	            [
		           	{field:'prizetitleName',title:'活动名称',width:160,align:'center',sortable:false},
		            {field:'starTime',title:'开始时间',width:160,align:'center',sortable:false},
		            {field:'endTime',title:'结束时间',width:160,align:'center',sortable:false},
		        	{field:'flag',title:'发布状态',width:80,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		if(value=="0"){
		            			return "禁用";
		            		}
		            		if(value=="1"){
		            			return "<a style='color:red'>已启用</a>";
		            		}		
		            	
		            	}
		            },
		        	   {field:'meetingid',title:'操作',width:260,sortable:true,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		var html = '';
		            	
		            		html += '<a href="javascript:qiyong(\''+rowData.prizetitleId+'\')">启用</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:jinyong(\''+rowData.prizetitleId+'\')">禁用</a>&nbsp;&nbsp;';   	
		            		html += '<a href="javascript:update(\''+rowData.prizetitleId+'\')">修改</a>&nbsp;&nbsp;';     	
		            		html += '<a href="javascript:chakan(\''+rowData.prizetitleId+'\')">查看奖品</a>&nbsp;&nbsp;';
		            		return html;
		            	}
		            }
	            ]
           ],
           toolbar:[{
				text:'添加奖品',
				iconCls:'icon-add',
				handler:function(){
					add();
			    }
			}
			]
		});
		initGenerglPagination2('#dataGrid');
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
	$.fn.colorbox({iframe:true,innerWidth:736,innerHeight:400,href:"/weixin-studio/location/prizeadd.jsp",overlayClose:false,onClosed:function(){
		$('#dataGrid').datagrid("reload");
	}});
}

function deleteText(meetingid){
	$.messager.defaults.ok = "确定";
   $.messager.defaults.cancel = "取消";
   $.messager.confirm('操作提示', '确定要删除吗？', function(r){
		 if (r){
				$.ajax({   
					url:'/weixin-studio/interact/meetingstateInteract',
			        type: 'POST',   
			        async: false,
			        data:  {'meetingAgenda.meetingid':meetingid,'meetingAgenda.status':'3'},
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

/**
 * 禁用
 * @param id
 */
function jinyong(prizetitleId){
	$.ajax({   
	       url:'/weixin-studio/interact/prizetitleFlagInteract',
	        type: 'POST',   
	        async: false,
	        data:  {'prizeTitle.prizetitleId':prizetitleId,'prizeTitle.flag':0},
	       dataType: 'json',  
	       success:function(data){ 
	    	   if(data.msg=="success"){
	    		   $('#dataGrid').datagrid('reload');
	    	   }
	        }   
		});
}

//启用
function qiyong(prizetitleId){
	$.ajax({   
       url:'/weixin-studio/interact/prizetitleFlagInteract',
        type: 'POST',   
        async: false,
        data:  {'prizeTitle.prizetitleId':prizetitleId,'prizeTitle.flag':1},
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
function chakan(prizetitleId){
	
	window.location = "/weixin-studio/location/update.jsp?prizetitleId="+prizetitleId;
}
//修改
function update(id){
	$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:500,href:"/weixin-studio/interact/updateprizeInteract?type="+id,overlayClose:false,onClosed:function(){
		$('#dataGrid').datagrid("reload");
	}});
}