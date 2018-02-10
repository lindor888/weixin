var state = "none";	
$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'关注者列表',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:1050,
//			url:'/weixin-studio/account/selectAllAccount',
			url:'/weixin-studio/follower/selectAllFollower',
		
			sortName: 'subscribeTime',
			sortOrder: 'desc',
			idField:'subscribeTime',
			pagination: true,
		    //rownumbers: true,

		    singleSelect: true,
	        columns:[
	            [
		            {field:'headimgurl',title:'',width:70,align:'center',sortable:true,
	            	    formatter:function(value,rowData,rowIndex){
	            	    	var html_img = "";
	            	    	html_img = '<img width="80px" height="80px" src='+value+  '/>';
	            	    	return html_img;
		            	}
		            },
		        {field:'openid',title:'openid',width:240,align:'center',sortable:true},
	            {field:'nickname',title:'昵称',width:150,align:'center',sortable:true },
	            {field:'sex',title:'性别',width:90,align:'center',sortable:true},
	            
	            {field:'city',title:'地区',width:100,align:'center',sortable:true},
	           
                {field:'province',title:'城市',width:100,align:'center',sortable:true},
	           
	            {field:'country',title:'国家',width:100,align:'center',sortable:true },
	            
	            {field:'subscribeTime',title:'关注时间',width:100,sortable:true,align:'center',sortable:true}
	            ]
           ],
			toolbar:[{
			text:'更新关注者',
				iconCls:'icon-add',
				disabled:false,
				handler:function(){
					$.messager.confirm('确认','更新需要等待一段时间，是否更新 ?',function(row){
						if(row){
							
							$.ajax({ 
								url:"/weixin-studio/follower/updateAllFollower", 
								
								success:function(data, textStatus){
									//alert(data);
									if(data.message=='OK'){
										$.messager.alert("提示","更新完成！","warning");
										$('#dataGrid').datagrid("reload");
									}else if(data.message=='ERROR'){
										//alert("adssdfasdas");
										$.messager.alert("提示","更新失败，请重试！","warning");
									}else if(data.message=='FAIL'){
										//alert("122141351231324");
										$.messager.alert("提示","当前公众账号条件不足，请确认后重试！","warning");
									}
								}
							});
							$('#dataGrid').datagrid("reload");
						}
					});
					
					
					
//				$.fn.colorbox({iframe:true,innerWidth:800,innerHeight:300,href:"/weixin-studio/account/findurlAccount?method=add",overlayClose:false,onClosed:function(){
//						$('#dataGrid').datagrid("reload");
//					}});
				}
			}/*,{
				text:'更新关注者',
				iconCls:'icon-add',
				disabled:false,	
			}*/],
			//onLoadSuccess:function(data){
			//	$.messager.defaults.ok = "确定";
			//	if(data.total==0 && state=="none"){$.messager.alert("提示","根据您的查询条件，未找到相关内容，请重试！","warning");}
			//}
		});
		
		initGenerglPagination('#dataGrid');
	});