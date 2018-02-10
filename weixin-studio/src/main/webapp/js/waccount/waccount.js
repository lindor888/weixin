var state = "none";	
$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'微信公共平台账号列表',
			width:1140,
			pageList: [10, 20, 40, 60, 80],
			url:'/weixin-studio/account/selectAlltabwaccount',
			loadMsg:"正在加载数据，请等待!",
			sortName: 'waccountId',
			sortOrder: 'desc',
			idField:'waccountId',
			pagination: true,
		    rownumbers: true,
		    nowrap:false,
	        columns:[
	            [
	            {field:'name',title:'账号名称',width:160,align:'center',sortable:true},
	            {field:'createtimestring',title:'创建时间',width:140,align:'center',sortable:true,
	            	formatter:function(value,rowData,rowIndex){
	            		var edit_html = "";
	            			return edit_html +='<span style="cursor:pointer;" title="'+value+'">'+value.substr(0,19)+'</span>&nbsp;';
	            	}
	             },
	              {field:'operatetimestring',title:'最后修改时间',width:140,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		var edit_html = "";
		            			return edit_html +='<span style="cursor:pointer;" title="'+value+'">'+value.substr(0,19)+'</span>&nbsp;';
		            	}
		             },
	            {field:'operatname',title:'最后操作人',width:100,align:'center',sortable:true},
	            {field:'urgentState',title:'应急状态',width:70,align:'center',sortable:true,
	            	formatter:function(value,rowData,rowIndex){
	            		switch(value){
	            			case 0: return "正常"; break;
	            			case 1: return "<i style='color: red;'>应急</i>"; break;
	            		}
	            	}
	            
	            },
	            
	            {field:'state',title:'账号状态',width:70,align:'center',sortable:true,
	            	formatter:function(value,rowData,rowIndex){
	            		switch(value){
	            			case 0: return "正常"; break;
	            			case 1: return "<i style='color: red;'>停用</i>"; break;
	            		}
	            	}
	            
	            },
	            
                {field:'birthDate',title:'操作',width:308,align:'center',sortable:true,
	            	formatter:function(value,rowData,rowIndex){
	            	var edit_html = "";
	            	
	            	
	            	if(rowData.state==0){
	            	
	            	edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="editwaccount(\''+rowData.waccountId+'\');"><span class="l-btn-text icon-edit" style="padding-left: 20px;">修改</span></span></a>&nbsp;';	           
	            	edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="deleteUser(\''+rowData.waccountId+'\',1)"><span class="l-btn-text icon-remove" style="padding-left: 20px;">停用</span></span></a>&nbsp;';
	            	if(rowData.urgentState==0){
	            	edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="UrgentState(\''+rowData.waccountId+'\')"><span class="l-btn-text icon-cancel" style="padding-left: 20px;">启动应急</span></span></a>&nbsp;';
	            	}else{
	            		edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="removeUrgentState(\''+rowData.waccountId+'\')"><span class="l-btn-text icon-cancel" style="padding-left: 20px;">取消应急</span></span></a>&nbsp;';
	            	}
	            	//edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="reSysmenu(\''+rowData.waccountId+'\')"><span class="l-btn-text icon-topic" style="padding-left: 20px;">自定义菜单</span></span></a>&nbsp;';
		        	
	            	}else{
	            		edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="deleteUser(\''+rowData.waccountId+'\',0)"><span class="l-btn-text icon-remove" style="padding-left: 20px;">启用</span></span></a>&nbsp;';

	            		
	            	}
	            	
	            	return edit_html;
	        	}}
	        	]
           ],
			toolbar:[{
				text:'添加公共账号',
				iconCls:'icon-add',
				disabled:false,
				handler:function(){
				$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:450,href:"/weixin-studio/account/insertfindurltabwaccount",overlayClose:false,onClosed:function(){
						$('#dataGrid').datagrid("reload");
					}});
				}
			}],
			onLoadSuccess:function(data){
				$.messager.defaults.ok = "确定";
				if(data.total==0 && state=="none"){$.messager.alert("提示","根据您的查询条件，未找到相关内容，请重试！","warning");}
			}
		});
		initGenerglPagination('#dataGrid');
	});

	//用户删除
	function deleteUser(rowId,status){
		$.messager.defaults.ok = "确定";
	    $.messager.defaults.cancel = "取消";
	    var msg = (status == 0) ? "确认启用此公共账号吗？" : "确认停用此公共账号吗？";
	    $.messager.confirm('提示', msg, function(r){
	    		 if (r){
				    var url = "/weixin-studio/account/deletewaccounttabwaccount?waccount.state="+status;
					$.ajax({url:url,async: false,dataType: "json",type:"POST",data: "waccount.waccountId="+rowId,
						success:function(data, textStatus){
						
							if(data.message=='true'){
								deleteRowById(rowId);
								$.messager.alert("提示","操作成功！","warning",function(r){
									this.parent.right.location = "/weixin-studio/account/selectAlltabwaccount";
									//window.right.location = "/weixin-studio/permission/manageuserUserAction";
								});
							}else if(data.message=='ERROR'){
								$.messager.alert("提示","操作失败，请重试！","warning");
							}
						}});
	    		 }
	     });
	}
	
	//启动应急模式
	function UrgentState(rowId){
		$.messager.defaults.ok = "确定";
	    $.messager.defaults.cancel = "取消";
	    $.messager.confirm('提示', '确认启动应急模式吗？', function(r){
	    		 if (r){
				    var url = "/weixin-studio/account/UrgentStatewaccounttabwaccount?urgentstate=1";
					$.ajax({url:url,async: false,dataType: "json",type:"POST",data: "waccount.waccountId="+rowId,
						success:function(data, textStatus){
						
							if(data.message=='true'){
								
								$.messager.alert("提示","操作成功！","warning",function(r){
									$('#dataGrid').datagrid("reload");
								});
							}else if(data.message=='ERROR'){
								$.messager.alert("提示","操作失败，请重试！","warning");
							}
						}});
	    		 }
	     });
	}
	
	
	//取消应急模式
	function removeUrgentState(rowId){
		$.messager.defaults.ok = "确定";
	    $.messager.defaults.cancel = "取消";
	    $.messager.confirm('提示', '确认取消应急模式吗？', function(r){
	    		 if (r){
				    var url = "/weixin-studio/account/UrgentStatewaccounttabwaccount?urgentstate=0";
					$.ajax({url:url,async: false,dataType: "json",type:"POST",data: "waccount.waccountId="+rowId,
						success:function(data, textStatus){
							if(data.message=='true'){
								
								$.messager.alert("提示","操作成功！","warning",function(r){
									$('#dataGrid').datagrid("reload");
								});
							}else if(data.message=='ERROR'){
								$.messager.alert("提示","操作失败，请重试！","warning");
							}
							
						}});
	    		 }
	     });
	}
	
	

	
	
	function deleteRowById(rowId){
		state = "delete";
		var index = $('#dataGrid').datagrid('getRowIndex', rowId);
		$('#dataGrid').datagrid('deleteRow', index);
	    state = "none";
		
	}
	
	
	
	function reSysmenu(id){
			window.location.href ="/weixin-studio/account/selectBymenuCustomizemenuAction?queryData.waccountId="+id;
		
		 

	}
	
	function editwaccount(id){
		$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:505,href:"/weixin-studio/account/findurltabwaccount?wid="+id,overlayClose:false,onClosed:function(){
			$('#dataGrid').datagrid("reload");
		}});
		
	}
	

	//页面的查询按钮 
	function selectUserByPage(){
		
	searchForm('userInfFrom','dataGrid','/weixin-studio/account/selectAllBysearchtabwaccount');
	}