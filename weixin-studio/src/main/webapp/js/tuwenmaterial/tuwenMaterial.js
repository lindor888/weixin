//列表
$(function(){
		$('#tuwenMaterial').datagrid({
			title:'图文素材管理',
			loadMsg:'正在加载数据，请等待..............',
			pageList:[10,20,40],
			url:'/weixin-studio/Material/querylistTuWenAction',
			dataType:'json', 
			type:"post",				
			sortName: 'modifydate', 
			sortOrder: 'desc',
			idField:'id',
			remoteSort:false,
			nowrap:false,
			width:1170,
	        columns:[
	            [	{field:'catalogTitle',title:'图文素材名称',width:100,align:'center',sortable:false},
		            {field:'createdate',title:'创建时间',width:140,align:'center',sortable:false,
	            		formatter:function(value,rowData,rowIndex){
	            		return value.replace("T"," ");
	            		}
		            },
		        	{field:'modifyuser',title:'修改人',width:100,align:'center',sortable:false},
		        	{field:'modifydate',title:'修改时间',width:140,align:'center',sortable:false,
		        		formatter:function(value,rowData,rowIndex){
		            		return value.replace("T"," ");
		            		}
		        	},
		        	{field:'keyword1',title:'精确关键字',width:170,align:'center',sortable:false},
		        	{field:'keyword0',title:'模糊关键字',width:170,align:'center',sortable:false},
		        	{field:'status',title:'状态',width:50,align:'center',sortable:false,
		        		formatter:function(value,rowData,rowIndex){
		        			if(value==0){
		        				return "<span style='color:red'>停用</span>";
		        			}
		        			if(value==1){
		        				return "启用";
		        			}
		        		}
		        	},
		        	{field:'id',title:'操作',width:195,align:'center',sortable:false,
		        		formatter:function(value,rowDate,rowIndex) {
		        			var updatehtm = '<a class="easyui-linkbutton l-btn l-btn-plain" onclick="modify(\''+value+'\')"><span class="l-btn-left"><span class="l-btn-text icon-edit" style="padding-left: 20px;">修改</span></span></a>';
		        			if(rowDate.status==1){
			        			var Switchinghtm = '<a class="easyui-linkbutton l-btn l-btn-plain" onclick="switching(\''+value+'\',0)"><span class="l-btn-left"><span class="l-btn-text icon-edit" style="padding-left: 20px;">停用</span></span></a>';
		        			}else{
			        			Switchinghtm = '<a class="easyui-linkbutton l-btn l-btn-plain" onclick="switching(\''+value+'\',1)"><span class="l-btn-left"><span class="l-btn-text icon-edit" style="padding-left: 20px;">启用</span></span></a>';
		        			}
		        			
		        			var delhtm = '<a class="easyui-linkbutton l-btn l-btn-plain" onclick="deleteText(\''+value+'\')"><span class="l-btn-left"><span class="l-btn-text icon-remove" style="padding-left: 20px;">删除</span></span></a>';
		        			return updatehtm + '&nbsp;&nbsp;&nbsp;'+Switchinghtm + '&nbsp;&nbsp;&nbsp;'+ delhtm;
		        		}
		        	}
                ]
            ],
            rownumbers:true,
			singleSelect:true,
			pagination:true,
			toolbar:[{
				text:'添加图文素材',
				iconCls:'icon-add',
				handler:function(){
					modify();
			    }
			}
			]
		});
		initGenerglPagination('#tuwenMaterial');
});

/*
 * function query(){ var param={}; var tcptType=$('#type').val(); var
 * sourceName=$('#sourceName').val();
 * param={"photoClipping.tcptType":tcptType,"photoClipping.sourceName":sourceName};
 * $('#photoDataGrid').datagrid({ queryParams:param, type:"post",
 * url:'/weixin-studio/content/queryPhotoCut?random='+Math.round(Math.random()*10000)
 * }); initGenerglPagination('#photoDataGrid'); }
 */

function modify(id){
	var _href = '/weixin-studio/Material/modifyTuWenAction';
	if(id != null) {
		_href += '?g.id=' + id;
	}
	$.fn.colorbox({iframe:true,innerWidth:736, innerHeight:520, href:_href,overlayClose:false,onClosed:function(){$('#tuwenMaterial').datagrid('reload');}});
}

function teQuery(){
	var param={};
	var Name=$.trim($('#imageMaterialName').val());
	var zhuangtai=$('#zhuangtai').val();
	//var waccount_id=$('#waccount_id').val();
	var kwordname=$('#kwordname').val();
	//param={"graphicMaterial.catalogTitle":Name,"graphicMaterial.status":zhuangtai,"graphicMaterial.waccount_id":waccount_id,"graphicMaterial.keyword":kwordname};
	param={"graphicMaterial.catalogTitle":Name,"graphicMaterial.status":zhuangtai,"graphicMaterial.keyword0":kwordname};
	$('#tuwenMaterial').datagrid({
		queryParams:param,
		pageNumber:1,
		type:"post",				
		url:'/weixin-studio/Material/querylistTuWenAction'
	});
	initGenerglPagination('#tuwenMaterial');
}
function deleteText(id){
	$.messager.defaults.ok = "确定";
    $.messager.defaults.cancel = "取消";
    $.messager.confirm('操作提示', '确定要删除吗？', function(r){
		 if (r){
		var param={"gm.id":id};
				$.ajax({   
				       url:'/weixin-studio/Material/deleteTuWenAction?random='+Math.round(Math.random()*10000),
				        type: 'POST',   
				        async: false,
				        data:  param,
				       dataType: 'json',  
				       success:function(data){ 
				    	   if(data.mg=="success"){
				    		   $.messager.alert("提示","删除成功！","info",function(r){
				    		   parent.$.fn.colorbox.close();
				    		   $('#tuwenMaterial').datagrid('reload');
				    		   });
				    	   }
				    	   if(data.mg=="fail"){
				    		   $.messager.alert("操作提示","删除失败！","error",function(r){
				    		   $('#tuwenMaterial').datagrid('reload');
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


function switching(id,status){
	$.messager.defaults.ok = "确定";
    $.messager.defaults.cancel = "取消";
    if(status==0){
    $.messager.confirm('操作提示', '确定要停用吗？', function(r){
		 if (r){
		var param={"gm.id":id,"gm.status":status};
				$.ajax({   
				       url:'/weixin-studio/Material/switchingTuWenAction?random='+Math.round(Math.random()*10000),
				        type: 'POST',   
				        async: false,
				        data:  param,
				       dataType: 'json',  
				       success:function(data){ 
				    	   if(data.mg=="success"){
				    		   $.messager.alert("提示","操作成功！","info",function(r){
				    		   parent.$.fn.colorbox.close();
				    		   $('#tuwenMaterial').datagrid('reload');
				    		   });
				    	   }
				    	   if(data.mg=="fail"){
				    		   $.messager.alert("操作提示","操作失败！","error",function(r){
				    		   $('#tuwenMaterial').datagrid('reload');
				    		   });
				    	   }

				        },
				        error: function(){
				        	$.messager.alert("操作提示","系统错误", "error");
				        }
				    });
	 }
    });
    }else{$.messager.confirm('操作提示', '确定要启用吗？', function(r){
    	 if (r){
    			var param={"gm.id":id,"gm.status":status};
    					$.ajax({   
    					       url:'/weixin-studio/Material/switchingTuWenAction?random='+Math.round(Math.random()*10000),
    					        type: 'POST',   
    					        async: false,
    					        data:  param,
    					       dataType: 'json',  
    					       success:function(data){ 
    					    	   if(data.mg=="success"){
    					    		   $.messager.alert("提示","操作成功！","info",function(r){
    					    		   parent.$.fn.colorbox.close();
    					    		   $('#tuwenMaterial').datagrid('reload');
    					    		   });
    					    	   }
    					    	   if(data.mg=="fail"){
    					    		   $.messager.alert("操作提示","操作失败！","error",function(r){
    					    		   $('#tuwenMaterial').datagrid('reload');
    					    		   });
    					    	   }

    					        },
    					        error: function(){
    					        	$.messager.alert("操作提示","系统错误", "error");
    					        }
						    });
			 }
		    });}
}

function initGenerglPagination(id){
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
