$(document).ready(function(){
		initGenerglDataGrid('#commentList');
		$('#commentList').datagrid({
			title:'视频评论管理',
			//pageNumber : 1,
			pageSize : 10,
			loadMsg:'正在加载数据，请等待..............',
			pageList:[10,20,40],
			url:'/weixin-studio/epgcomment/getListComment',
			sortName: 'comment_time', 
			sortOrder: 'desc',
			idField:'comment_id',
			queryParams : {'page':1,'rows':10},
			pagination: true, //分页组件
		    rownumbers:true,
		    remoteSort:false,
			nowrap:false,
			width:1200,
		   // queryParams : {'example.page':1},
		    frozenColumns:[[{field:'ck',checkbox:true}]],
		    singleSelect: false,
//		    selectOnCheck: true,
//		    checkOnSelect: true,
	        columns:[
	            [
		           	{field:'openid',title:'用户的标识',width:200,align:'center',sortable:false},
		            {field:'userhead',title:'头像',width:70,align:'center',sortable:false,
	            	    formatter:function(value,rowData,rowIndex){
	            	    	var html_img = "";
	            	    	if(value!=null){
	            	    		html_img = '<a target="_blank" href="'+value+'"><img src='+value+  ' width="64" height="64"></a>';
	            	    	}
	            	    	return html_img;
		            	}
		            },
		            //{field:'userhead',title:'用户头像',width:160,align:'center',sortable:false},
		        	{field:'nickname',title:'用户昵称',width:160,align:'center',sortable:false},
		        	{field:'content',title:'评论内容',width:195,align:'center',sortable:false},
		        	{field:'comment_time',title:'时间',width:160,align:'center',sortable:false},
		        	{field:'reservation_id',title:'视频标识',width:160,align:'center',sortable:false}
	            ]
           ],
           toolbar:[
			
			]
		});
		initGenerglPagination2('#commentList');
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

function query1(){
 		var comment_time=$('#comment_time').val();
 		if(comment_time !=null){
 		//	查询
 			$("#commentList").datagrid({
 				url:'/weixin-studio/epgcomment/queryComment',
 				queryParams:{'record.comment_time':comment_time},
 				pageNumber:1
 			});
 			initGenerglPagination2("#commentList");
 		}
}