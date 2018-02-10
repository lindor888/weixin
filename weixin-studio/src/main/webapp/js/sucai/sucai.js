$(function() {
	$.messager.defaults.ok = "确定";
	$.messager.defaults.cancel = "取消";
	var material=$('#material').val();
	var title ="文本素材";
	var urls="";
	if(material=='0'){
		title="文本素材";
		urls="/weixin-studio/Material/queryWenBenAction?random="+Math.round(Math.random()*10000);
	}
	if(material=='1'){
		title="图文素材";
		urls="/weixin-studio/Material/queryTuWenAction?random="+Math.round(Math.random()*10000);
	}
		$('#sucai').datagrid({
			title:title,
			loadMsg:'正在加载数据，请等待..............',
			width:400,
			url:urls,
			//pageList:[10,20,40],
			dataType:'json', 
			type:"post",				
			sortName: 'modifydate',
			sortOrder: 'desc',
			idField:'id',
			remoteSort:false,
		    columns:[
		        [	
		        	{field:'catalogTitle',title:'默认词名称',width:340,align:'center',sortable:true}
		        ]
		    ],
		    onSelect:function(rowIndex, rowData){
					
									
			},
		    rownumbers:true,
			singleSelect:true,
			pagination:true
		});
	initGenerglPagination('#sucai');
	urls="";
});
function initGenerglPagination(id){
	  var _pag = $(id).datagrid("getPager");
	  if (_pag){
	  $(_pag).pagination({
	  showPageList:false,
	  beforePageText:" 第 ",
	  afterPageText:" 页 共{pages}页 ",
	  displayMsg:" 第 {from} 至 {to}条  共 {total} 条 "
	  });
	  }
	}
function query(){
	var sucaiType=$('#material').val();
	var sucaiTitle=$('#sucaiTitle').val();
	var param={};
	var urlType="";
	if(sucaiType=='0'){
		urlType="/weixin-studio/Material/queryWenBenAction?random="+Math.round(Math.random()*10000);
		param={"textMaterial.catalogTitle":sucaiTitle};
	}
	if(sucaiType=='1'){
		urlType="/weixin-studio/Material/queryTuWenAction?random="+Math.round(Math.random()*10000);
		param={"graphicMaterial.catalogTitle":sucaiTitle};
	}
	$('#sucai').datagrid({
		queryParams:param,
		pageNumber:1,
		type:"post",				
		url:urlType
	});
	initGenerglPagination('#sucai');
}


