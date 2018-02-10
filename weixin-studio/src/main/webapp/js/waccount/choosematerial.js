
$(function() {
	
	  $('#choosewaccountText').dialog('close');
	  $('#choosewaccountContent').dialog('close');
	//	initMenu();
	//	$("#header_menu").attr("class", "selected");
		//add_menu(1, 0, 0);
	//	$("#bottom_button").css("display", "none");
	
	});
function choosematerial(){
	
	var type=$("#welcomeContented").val();

	
	if(type=='1'){
		$("#textbacktype").val("1");
		chooseText();
  }else if(type=='2'){
	  $("#conterbacktype").val("1");
	chooseContent();
	}
}


function nochoosematerial(){
	var noreplyed=$("#noreplyed").val();
	
	if(noreplyed=='1'){
		$("#textbacktype").val("2");
		chooseText();
  }else if(noreplyed=='2'){
	  $("#conterbacktype").val("2");
	chooseContent();
	}
}


function chooseContent(){
	var waccountid=$("#waccountId").val();
	$("#catalogTitlequeryContent").val("");
	$('#choosewaccountContent').dialog('open').dialog({
		title: '挑选图文新闻',
		//bgiframe: true,
	    resizable: true,
	    height:480,
	    modal: true,
	    top :20,
	    width: 500
	});
	$("#contentInfo").datagrid({
		title : '挑选图文新闻',
		pageNumber : 1,
		pageSize : 10,
		width :430,
		height:350,
		nowrap : true,
		striped : true,
		queryParams:{},
		remoteSort : true,
		loadMsg : '正在加载数据......',
		dataType : 'json',
		type : "post",	
		url : $("#basePath").val()+'/Material/querylistedTuWenAction?graphicMaterial.waccount_id='+waccountid,
		sortName: 'id', 
		sortOrder: 'desc',
		idField:'id',
		remoteSort:false,//排序关键
		columns : [ [ 
		
		{
			field : 'catalogTitle',
			title : '默认词名称',
			width : 290,
			align : 'center',
			sortable : false
		},
		{field:'birthDate',title:'操作',width:70,align:'center',sortable:true,
        	formatter:function(value,rowData,rowIndex){
        
            	var edit_html = "";
            	edit_html +='<input type="checkbox" name="ck" onclick="chooseOne(this);"/>&nbsp;';
            	       		return edit_html;
        	}}
		] ],
		rownumbers : true,
		singleSelect : true,
		pagination : true,
       	onLoadSuccess:function(data){				
		}
	});
	initGenerglPagination('#contentInfo');
}

function chooseText(){
	var waccountid=$("#waccountId").val();
	$('#choosewaccountText').dialog('open').dialog({
		title: '挑选文本',
		bgiframe: true,
	    resizable: true,
	    height:480,
	    modal: true,
	    top :20,
	    width: 500
	});
	$("#catalogTitlequeryText").val("");
	$("#textInfo").datagrid({
		title : '挑选文本',
		pageNumber : 1,
		pageSize : 10,
		width :430,
		height:350,
		//remoteSort : true,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		queryParams:{},
		loadMsg : '正在加载数据......',
		dataType : 'json',
		type : "post",
		url : $("#basePath").val()+'/Material/queryBytitleedWenBenAction?textMaterial.waccount_id='+waccountid,
		sortName: 'id', 
		sortOrder: 'desc',
		idField:'id',
		//remoteSort:false,//排序关键
		columns : [ [ 
		{
			field : 'catalogTitle',
			title : '素材名称',
			width : 270,
			align : 'center',
			sortable : true
		
			
		},
		{field:'birthDate',title:'操作',width:70,align:'center',sortable:true,
        	formatter:function(value,rowData,rowIndex){
        
            	var edit_html = "";
            	edit_html +='<input name="ck" type="checkbox" onclick="chooseOne(this);" />&nbsp;';
            	       		return edit_html;
        	}}
		] ],
		
		
       	onLoadSuccess:function(data){				
			JT_init();
		}
	});
	initGenerglPagination('#textInfo');
	
}

function initGenerglPagination(id) {
	var _pag = $(id).datagrid("getPager");
	if (_pag) {
		$(_pag).pagination({
			showPageList : false,
			beforePageText : " 第 ",
			afterPageText : " 页 共{pages}页 ",
			displayMsg : " 第 {from} 至 {to}条  共 {total} 条 "
		});
	}
}
function queryContent(){
	var waccountid=$("#waccountId").val();
	var catalogTitlequeryContent=$.trim($("#catalogTitlequeryContent").val());
	var param={"graphicMaterial.catalogTitle":catalogTitlequeryContent,"graphicMaterial.waccount_id":waccountid};
	var query_url=$("#basePath").val()+'/Material/querylistedTuWenAction';
	query_url=encodeURI(query_url);
	$('#contentInfo').datagrid({
		queryParams:param,
		type:"post",
		pageNumber:1,
		url:query_url
	});
	initGenerglPagination('#contentInfo');
}
function queryText(){
	var waccountid=$("#waccountId").val();
	var catalogTitlequeryText=$.trim($("#catalogTitlequeryText").val());
	var param={"textMaterial.catalogTitle":catalogTitlequeryText,"textMaterial.waccount_id":waccountid};
	var query_url=$("#basePath").val()+'/Material/queryBytitleedWenBenAction';
	query_url=encodeURI(query_url);
	$('#textInfo').datagrid({
		queryParams:param,
		type:"post",	
		pageNumber:1,
		url:query_url
	
	});
	initGenerglPagination('#textInfo');
}
function sureContent(){
	var row = $('#contentInfo').datagrid('getSelected');
	var s=$('#conterbacktype').val();
	if (row) {
		if(s=='1'){
		$("#comtitle").val(row.catalogTitle);
		$("#welcomeContent").val(row.id);
		}else if(s=='2'){
			$("#notitle").val(row.catalogTitle);
			$("#noReplyContent").val(row.id);
		}
		$('#choosewaccountContent').dialog('close');
	}else{
		alert("请选择默认词");
	}
}

function sureText(){
	
	var row = $('#textInfo').datagrid('getSelected');

	var s=$('#textbacktype').val();

	if (row) {
		if(s=='1'){
			
		$("#comtitle").val(row.catalogTitle);
		$("#welcomeContent").val(row.id);
		}else if(s=='2'){
			$("#notitle").val(row.catalogTitle);
			$("#noReplyContent").val(row.id);
		}
		$('#choosewaccountText').dialog('close');
	}else{
		alert("请选择默认词");
	}
}



function createurl(){
	
	var waccountId=$("#waccountId").val();
		var url = "/weixin-studio/account/createurltabwaccount";
		$.ajax({url:url,async: false,dataType: "json",type:"POST",data: "wid="+waccountId,
			success:function(data, textStatus){
				if(data.message=='OK'){
					$("#url").val(data.url);
				}else if(data.message=='ERROR'){
					$.messager.alert("提示","操作失败，请重试！","warning");
				}
			}
		});
 
}

function qiehuan(name){
	if(name=='notitle'){
		$('#notitle').val('');
	}
	if(name=='comtitle'){
		$('#comtitle').val('');
	}
	
	
}




