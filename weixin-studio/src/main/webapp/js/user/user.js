var state = "none";	
$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'用户列表',
			width:1160,
			url:'/weixin-studio/account/selectAllAccount',
			sortName: 'accountId',
			sortOrder: 'desc',
			idField:'accountId',
			pagination: true,
		    rownumbers: true,
		    singleSelect: true,
	        columns:[
	            [
	            {field:'login',title:'用户名',width:160,align:'center',sortable:true},
	            {field:'createtimestring',title:'创建时间',width:100,align:'center',sortable:true,
	            	//时间转换
	            	formatter:function(value,rowData,rowIndex){
	            		var edit_html = "";
	            		if(value.length>=8){
	            			return edit_html +='<span style="cursor:pointer;" title="'+value+'">'+value.substr(0,19)+'</span>&nbsp;';
	            		}else{
	            			return value;
	            		}
            		}
	             },
	            {field:'username',title:'真实姓名',width:100,align:'center',sortable:true},
	            
	            {field:'departName',title:'部门',width:130,align:'center',sortable:true},
	           
                {field:'isinside',title:'部门所属',width:50,align:'center',sortable:true},
	           
	            {field:'roleName',title:'角色',width:100,align:'center',sortable:true,
                	formatter:function(value,rowData,rowIndex){
	            		
	            		if(value==''||value==null){
	            			
	            			value="无";
	            			return value;
	            			}else{
	            				return value;
	            			}
	            		
            		}
	            },
		            
	            {field:'state',title:'状态',width:50,align:'center',sortable:true,
	            	formatter:function(value,rowData,rowIndex){
	            		switch(value){
	            			case 0: return "正常"; break;
	            			case 1: return "<i style='color: red;'>停用</i>"; break;
	            		}
	            	}
	            },
	            {field:'birthDate',title:'操作',width:360,sortable:true,align:'center',
	            	formatter:function(value,rowData,rowIndex){
		            	var edit_html = "";
		            	if(rowData.state=='0'){
		            		edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="updateaccount(\''+rowData.accountId+'\');"><span class="l-btn-left"><span class="l-btn-text icon-edit" style="padding-left: 20px;">修改</span></span></a>&nbsp;';
			            	edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="deleteUser(\''+rowData.accountId+'\',1)"><span class="l-btn-left"><span class="l-btn-text icon-remove" style="padding-left: 20px;">停用</span></span></a>&nbsp;';
			            	
			            	edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="reSysPassWord(\''+rowData.accountId+'\',\''+rowData.isinside+'\')"><span class="l-btn-left"><span class="l-btn-text icon-page" style="padding-left: 20px;">重置密码</span></span></a>&nbsp;';
			            	edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="allotWaccount(\''+rowData.accountId+'\')"><span class="l-btn-left"><span class="l-btn-text icon-channel" style="padding-left: 20px;">分配公共账号</span></span></a>&nbsp;';
		            	}else{
		            		edit_html +='<a class="easyui-linkbutton l-btn l-btn-plain" onclick="deleteUser(\''+rowData.accountId+'\',0)"><span class="l-btn-left"><span class="l-btn-text icon-remove" style="padding-left: 20px;">启用</span></span></a>&nbsp;';
		            	}
		            	
		            	return edit_html;
		        	}
	            }
	            ]
           ],
			toolbar:[{
				text:'添加账号',
				iconCls:'icon-add',
				disabled:false,
				handler:function(){
				$.fn.colorbox({iframe:true,innerWidth:800,innerHeight:300,href:"/weixin-studio/account/findurlAccount?method=add",overlayClose:false,onClosed:function(){
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
	function deleteUser(rowId,state){
		
		
		$.messager.defaults.ok = "确定";
	    $.messager.defaults.cancel = "取消";
	    var msg="";
	    if(state=="1"){
	    	msg="确定要停用此条用户吗？";
	    }
	    if(state=="0"){
	    	msg="确定要启用此条用户吗？";
	    }
	    $.messager.confirm('提示', msg, function(r){
	    		 if (r){
				    var url = "/weixin-studio/account/inputAccount?method=delete&account.state="+state;
					$.ajax({url:url,async: false,dataType: "json",type:"POST",data: "account.accountId="+rowId,
						success:function(data, textStatus){
							if(data.message=='true'){
								deleteRowById(rowId);
								$.messager.alert("提示","操作成功！","warning",function(r){
									//window.right.location = "/weixin-studio/permission/manageuserUserAction";
									//this.parent.right.location = "/weixin-studio/account/selectAllAccount";
									$('#dataGrid').datagrid("reload");
								});
							}else if(data.message=='ERROR'){
								$.messager.alert("提示","操作失败，请重试！","warning");
							}
						}});
	    		 }
	     });
	}
	

	//重置系统默认密码
	function reSysPassWord(userId,isinside){
		 $.messager.defaults.ok = "确定";
		  $.messager.defaults.cancel = "取消";
		/*if(isinside=='内部'){
			 $.messager.confirm('提示', '该用户为公司内部用户,请去CMS更改密码！');
			 return ;
			
		}*/
	  $.messager.confirm('提示', '您确认为该用户重置密码吗？该用户密码将更改为系统默认密码。', function(r){
	  	if (r){
			var url = "/weixin-studio/account/rePasswordByIdAccount";
			$.ajax({url:url,async: false,dataType: "json",type:"POST",data: "account.accountId="+userId+"&newPassword=111111",
				success:function(data, textStatus){
					if(data.message=='OK'){
						$.messager.alert("提示","操作成功！","warning");
					}else if(data.message=='ERROR'){
						$.messager.alert("提示","操作失败，请重试！","warning");
					}
				}
			});
	  	}
	  });
	}
	
	function updateaccount(id){
		$.fn.colorbox({iframe:true,innerWidth:500,innerHeight:380,href:"/weixin-studio/account/findurlAccount?method=update&account.accountId="+id,overlayClose:false,onClosed:function(){
			$('#dataGrid').datagrid("reload");
		}});
	}
	
	//页面的查询按钮 
	function selectUserByPage(){
	searchForm('userInfFrom','dataGrid','/weixin-studio/account/selectAllBystateAccount');
	}
	
	//
	function deleteRowById(rowId){
		state = "delete";
		var index = $('#dataGrid').datagrid('getRowIndex', rowId);
		$('#dataGrid').datagrid('deleteRow', index);
		state = "none";
	}
//分配公共账号	
function allotWaccount(id){
	//校验权限？           
	
	$.fn.colorbox({iframe:true,innerWidth:600,innerHeight:430,href:"/weixin-studio/account/findurlAccount?method=allotrelation&account.accountId="+id,overlayClose:false,onClosed:function(){
	//	$('#dataGrid').datagrid("reload");
	}});
}
	
