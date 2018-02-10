


$(document).ready(function(){
	
	var total = 0;
	$("#contentInfo").datagrid({
		title : '挑选微信公共平台',
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 15, 20, 40, 60, 80 ],
		width :600,
		queryParams:{},
		singleSelect:false,
		pagination : true,
		loadMsg : '正在加载数据......',
		dataType : 'json',
		type : "post",	
		url : $("#basePath").val()+'/account/selectAllfebpeitabwaccount',
		sortName: 'waccountId', 
		sortOrder: 'desc',
		idField:'waccountId',
		remoteSort:false,//排序关键
		columns : [ [ 
		 {field:'ck',checkbox:true},
		{
			field : 'name',
			title : '公共平台名称',
			width : 550,
			align : 'center',
			sortable : false
		}
		] ],
		onLoadSuccess:function(data){
			total = data.total;
			$.messager.defaults.ok = "确定";
			if(data.total==0){
				//$.messager.alert("提示","根据您的查询条件，未找到相关内容，请重试！","warning");
			}else{
			
				var url = "/weixin-studio/account/findByaccountRelationAction?record.accountId="+$("#accountId").val()+"&rrrr="+(new Date()).getTime();
				$.getJSON(url, function(data){
					
					if(data.message=='OK'){
					
						$.each(data.list,function (i,item){
							$('#contentInfo').datagrid('selectRecord',item.waccountId);
						});
					}else if(data.message=='ERROR'){
						$.messager.alert("提示","加载失败，请重试！","warning");
					}
				});
			}
		},
		
		
		onSelect:function(rowIndex, rowData){
		
			selected = $('#contentInfo').datagrid('getSelections').length;
			
			if(total==selected)
				$('#contentInfo').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", true); 
		},
		onUnselect:function(rowIndex, rowData){
			selected = $('#contentInfo').datagrid('getSelections').length;
			if(total!=selected)
				$('#contentInfo').parent().find("div .datagrid-header-check").children("input[type='checkbox']").eq(0).attr("checked", false); 
		}
	});
	initGenerglPagination('#contentInfo');
});



function initGenerglPagination(id) {
	var _pag = $(id).datagrid("getPager");
	if (_pag) {
		$(_pag).pagination({
			showPageList : true,
			beforePageText : " 第 ",
			afterPageText : " 页 共{pages}页 ",
			displayMsg : " 第 {from} 至 {to}条  共 {total} 条 "
		});
	}
}



//提交
function submit(){
	jQuery.ajaxSettings.traditional = true;
	var param={};
	var ids = new Array();

	var rows = $('#contentInfo').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		ids[i] = rows[i].waccountId;
	}
	$.extend(param,{"selectedIds":ids});
	$.extend(param,{"accountId":$("#accountId").val()});
    var url = "/weixin-studio/account/addrelationRelationAction";
	$.ajax({url:url,async: false,dataType: "json",type:"POST",data:param,
		success:function(data, textStatus){
			if(data.message=='OK'){
				$.messager.alert("提示","操作成功！","warning",function(){window.parent.closeColorBox(false);});
			}else if(data.message=='ERROR'){
				$.messager.alert("提示","操作失败，请重试！","warning");
			}
		}
	});
}