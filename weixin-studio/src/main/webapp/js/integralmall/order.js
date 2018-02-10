/**
 * 商品管理
 */
$(document).ready(function(){
		initGenerglDataGrid('#orderList');
		$('#orderList').datagrid({
			title:'商品管理',
			//pageNumber : 1,
			pageSize : 10,
			loadMsg:'正在加载数据，请等待..............',
			pageList:[10,20,40],
//			url:'/weixin-studio/epg/getListEpg',
			url:'/weixin-studio/goods/selectallOrder',
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
	             	{field:'nickName',title:'用户名',width:120,align:'center',sortable:false},
	            	{field:'headUrl',title:'头像',width:50,align:'center',
		           		formatter:function(value, rec){//使用formatter格式化刷子
		           			var str = "";
		           	    	if(value!="" || value!=null){
		           	    		str = "<img style=\"height: 30px;width: 30px;\" src=\""+value+"\"/>";
		           	            return str;
		           	    	}
		           		}
		           	},
		           	{field:'goodsName',title:'商品名称',width:180,align:'center',sortable:false},
		           	{field:'count',title:'兑换数量',width:50,align:'center',sortable:false},
		            {field:'point',title:'消耗积分',width:80,align:'center',sortable:false},
		           	{field:'reciveInfo',title:'收货信息',width:180,align:'center',sortable:false,
		            	formatter:function(value,rowData,rowIndex){
		            		var array = value.split("_");
		            		var str = "";
		            		if(array[0]!=""){
		            			str+="收货地址:"+array[0];
		            		}
		            		if((array[1]!="")&&(array[1]!=";")){
		            			str+="联系方式："+array[1];
		            		}
		            		return str;
		            	}
		           	},
		        	{field:'createTime',title:'兑换时间',width:160,align:'center',sortable:false,
		            	formatter:function(value,rowData,rowIndex){
		            		return value.replace("T"," ");
		            		}
		        	},
		        	{field:'exchangeRecordID',title:'操作',width:195,align:'center',sortable:false,
		        		formatter:function(value,rowDate,rowIndex) {
		        			
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
		initGenerglPagination2('#orderList');
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

