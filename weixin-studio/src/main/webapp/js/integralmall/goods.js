/**
 * 商品管理
 */
$(document).ready(function(){
		initGenerglDataGrid('#goodsList');
		$('#goodsList').datagrid({
			title:'商品管理',
			//pageNumber : 1,
			pageSize : 10,
			loadMsg:'正在加载数据，请等待..............',
			pageList:[10,20,40],
//			url:'/weixin-studio/epg/getListEpg',
			url:'/weixin-studio/goods/selectallGoods',
			//sortName: 'updateTime',  按照时间排序
//			sortName: 'start_time', 
//			sortOrder: 'asc',
//			idField:'reservation_id',
			queryParams : {'page':1,'rows':10},
			pagination: true, //分页组件
		    rownumbers:true,
		    remoteSort:false,
			nowrap:false,
			width:1200,
		   /* frozenColumns:[[{field:'ck',checkbox:true}]],*/
		    singleSelect: false,
		    selectOnCheck: true,
		    checkOnSelect: true,
	        columns:[
	            [
		           	{field:'goodsName',title:'商品名称',width:180,align:'center',sortable:false},
		           	{field:'goodsTypeName',title:'商品类型',width:120,align:'center',sortable:false},
		           	{field:'goodsDescript',title:'商品描述',width:180,align:'center',sortable:false},
		            {field:'goodsUnitPoint',title:'商品单价',width:100,align:'center',sortable:false,
		            	formatter:function(value,rowData,rowIndex){
		            		return value+"积分";
		            	}
		            },
		           	{field:'inventory',title:'库存',width:100,align:'center',sortable:false},
		           	{field:'goodsImageName',title:'商品图片',width:150,align:'center',
		           		formatter:function(value, rec){//使用formatter格式化刷子
		           			var str = "";
		           	    	if(value!="" || value!=null){
		           	    		str = "<img style=\"height: 30px;width: 30px;\" src=\""+value+"\"/>";
		           	            return str;
		           	    	}
		           		}
		           	},
//		        	{field:'end_time',title:'结束时间',width:160,align:'center',sortable:false,
//		            	formatter:function(value,rowData,rowIndex){
//		            		return value.replace("T"," ");
//		            		}
//		        	},
		        	{field:'goodsID',title:'操作',width:195,align:'center',sortable:false,
		        		formatter:function(value,rowDate,rowIndex) {
		        			var updatehtm = '<a class="easyui-linkbutton l-btn l-btn-plain" onclick="modify(\''+value+'\')"><span class="l-btn-left"><span class="l-btn-text icon-edit" style="padding-left: 20px;">修改</span></span></a>';
		        			var delhtm = '<a class="easyui-linkbutton l-btn l-btn-plain" onclick="deleteText(\''+value+'\')"><span class="l-btn-left"><span class="l-btn-text icon-remove" style="padding-left: 20px;">删除</span></span></a>';
		        			return updatehtm + '&nbsp;&nbsp;&nbsp;' + '&nbsp;&nbsp;&nbsp;'+ delhtm;
		        		}
		        	}
	            ]
           ],
           toolbar:[{
				text:'添加商品',
				iconCls:'icon-add',
				handler:function(){
					add();
			    }
			}]
		});
		initGenerglPagination2('#goodsList');
});

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


//修改节目
function modify(id){
	var _href = '/weixin-studio/goods/toUpdateGoods';
	if(id != null) {
		_href += '?record.goodsID=' + id;
	}
	$.fn.colorbox({iframe:true,innerWidth:736, innerHeight:480, href:_href,overlayClose:false,onClosed:function(){$('#goodsList').datagrid('reload');}});
}
function add(){
	var _href = '/weixin-studio/goods/toAddGoods';
	$.fn.colorbox({iframe:true,innerWidth:736, innerHeight:480, href:_href,overlayClose:false,onClosed:function(){$('#goodsList').datagrid('reload');}});
}

function deleteText(id){
	$.messager.defaults.ok = "确定";
	   $.messager.defaults.cancel = "取消";
	   $.messager.confirm('操作提示', '确定要删除吗？', function(r){
			 if (r){
					$.ajax({   
					       url:'/weixin-studio/goods/deleteOneGoods',
					        type: 'POST',   
					        async: false,
					        data:  {"record.goodsID":id},
					       dataType: 'json',  
					       success:function(data){ 
					    	   if(data.msg=="success"){
					    		   $.messager.alert("提示","删除成功！","info",function(r){
					    		   parent.$.fn.colorbox.close();
					    		   $('#goodsList').datagrid('reload');
					    		   });
					    	   }
					    	   if(data.msg=="fail"){
					    		   $.messager.alert("操作提示","删除失败！","error",function(r){
					    		   $('#goodsList').datagrid('reload');
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
