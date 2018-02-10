//列表
$(function(){
		$('#keyword').datagrid({
			title:'关键字管理',
			loadMsg:'正在加载数据，请等待..............',
			width:1140,
			pageList:[10,20,40],
			url:'/weixin-studio/keyword/queryKeywordAction?random='+Math.round(Math.random()*10000),
			dataType:'json', 
			type:"post",				
			//sortName: 'modifydate', 
			//sortOrder: 'desc',
			idField:'id',
			remoteSort:false,
			nowrap:false,
	        columns:[
	            [	{field:'keywordName',title:'关键字',width:150,align:'center',sortable:true},
		            {field:'createdate',title:'创建时间',width:140,align:'center',sortable:false,
	            		formatter:function(value,rowData,rowIndex){
	            		return value.replace("T"," ");
	            		}
		            },
		        	{field:'modifyuser',title:'最后修改人',width:100,align:'center',sortable:false},
		        	{field:'modifydate',title:'修改时间',width:140,align:'center',sortable:true,
		        		formatter:function(value,rowData,rowIndex){
		            		return value.replace("T"," ");
		            		}
		        	},
		        	{field:'keywordWeight',title:'权重',width:46,align:'center',sortable:false},
		        	{field:'keywordRule',title:'匹配规则',width:60,align:'center',sortable:false,
		        		formatter:function(value,rowData,rowIndex){
		        			if(value==0){
		        				return "模糊";
		        			}
		        			if(value==1){
		        				return "精确";
		        			}
		        		}
		        	},
		        	{field:'type',title:'匹配类型',width:60,align:'center',sortable:false,
						formatter:function(value,rowData,rowIndex){
		        			if(value==0){
		        				return "文本";
		        			}
		        			if(value==1){
		        				return "图文";
		        			}
						}
		        	},
		        	{field:'name',title:'所属公共号',width:125,align:'center',sortable:false},
		        	{field:'status',title:'状态',width:46,align:'center',sortable:false,
		        		formatter:function(value,rowData,rowIndex){
		        			if(value==0){
		        				return "<span style='color:red;'>停用</span>";
		        			}
		        			if(value==1){
		        				return "启用";
		        			}
		        		}
		        	},
		        	{field:'operate',title:'操作',width:150,align:'center',sortable:false}
                ]
            ],
            rownumbers:true,
			singleSelect:true,
			pagination:true,
			toolbar:[{
				text:'添加关键字',
				iconCls:'icon-add',
				handler:function(){
					$.fn.colorbox({iframe:true,innerWidth:600, innerHeight:500, href:'/weixin-studio/keyword/tiaozhuanKeywordAction',overlayClose:false,onClosed:function(){$('#keyword').datagrid('reload');}});
			    }
			}
			]
		});
		initGenerglPagination('#keyword');
});

/*function query(){
	var param={};
	var tcptType=$('#type').val();
	var sourceName=$('#sourceName').val();
	param={"photoClipping.tcptType":tcptType,"photoClipping.sourceName":sourceName};
	$('#photoDataGrid').datagrid({
		queryParams:param,
		type:"post",				
		url:'/weixin-studio/content/queryPhotoCut?random='+Math.round(Math.random()*10000)
	});
	initGenerglPagination('#photoDataGrid');
}*/

function modify(id){
	$.fn.colorbox({iframe:true,innerWidth:600, innerHeight:500, href:'/weixin-studio/keyword/tiaozhuanKeywordAction?keyword.id='+id,overlayClose:false,onClosed:function(){$('#keyword').datagrid('reload');}});
}
function deleteKeyWord(id){
	$.messager.defaults.ok = "确定";
    $.messager.defaults.cancel = "取消";
    $.messager.confirm('操作提示', '确定要删除吗？', function(r){
		 if (r){
		var param={"eidtkeyword.id":id};
				$.ajax({   
				       url:'/weixin-studio/keyword/deleteKeywordAction?random='+Math.round(Math.random()*10000),
				        type: 'POST',   
				        async: false,
				        data:  param,
				       dataType: 'json',  
				       success:function(data){ 
				    	   if(data.mg=="success"){
				    		   $.messager.alert("提示","删除成功！","info",function(r){
				    		   $('#keyword').datagrid('reload');
				    		   });
				    	   }
				    	   if(data.mg=="fail"){
				    		   $.messager.alert("操作提示","删除失败！","error",function(r){
				    		   $('#keyword').datagrid('reload');
				    		   });
				    	   }

				        }   
				    });
	 }
    });
}

function kwdQuery(){
	var param={};
	var kweName=$.trim($('#kweName').val());
	var zhuangtai=$('#zhuangtai').val();
	var rules=$('#rules').val();
	var type=$('#type').val();
	param={"keyword.keywordName":kweName,"keyword.status":zhuangtai,"keyword.keywordRule":rules,"keyword.type":type};
	$('#keyword').datagrid({
		queryParams:param,
		pageNumber:1,
		type:"post",				
		url:'/weixin-studio/keyword/queryKeywordAction?random='+Math.round(Math.random()*10000)
	});
	initGenerglPagination('#keyword');
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
