$(document).ready(function(){
	var total = 0;
	$("#contentInfo").datagrid({
		title : '挑选角色',
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 15, 20, 40, 60, 80 ],
		width :600,
		singleSelect:false,
		pagination : true,
		loadMsg : '正在加载数据......',
		dataType : 'json',
		type : "post",	
		url : '/weixin-studio/role/querylistRole?random='+Math.round(Math.random()*10000),
		sortName: 'roleId', 
		sortOrder: 'desc',
		idField:'roleId',
		remoteSort:false,//排序关键
		columns : [ [ 
		 {field:'ck',checkbox:true},
		{
			field : 'roleName',
			title : '角色名称',
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
			   
				var url = "/weixin-studio/depart/findByroleRelationAction?record.departId="+$("#departId").val()+"&random="+Math.round(Math.random()*10000);
				$.getJSON(url, function(data){
					
					if(data.message=='OK'){
					
						$.each(data.list,function (i,item){
							$('#contentInfo').datagrid('selectRecord',item.roleId);
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

function quxiao(){
	parent.$.fn.colorbox.close();
}

//提交
function submit(){
	jQuery.ajaxSettings.traditional = true;
	var param={};
	var ids = new Array();

	var rows = $('#contentInfo').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		ids[i] = rows[i].roleId;
	}
	$.extend(param,{"selectedIds":ids});
	$.extend(param,{"departId":$("#departId").val()});
    var url = "/weixin-studio/depart/addrelationRelationAction";
	$.ajax({url:url,async: false,dataType: "json",type:"POST",data:param,
		success:function(data, textStatus){
			if(data.message=='OK'){
				$.messager.alert("提示","操作成功！","warning",function(){parent.$.fn.colorbox.close();});
			}else if(data.message=='ERROR'){
				$.messager.alert("提示","操作失败，请重试！","warning");
			}
		}
	});
}