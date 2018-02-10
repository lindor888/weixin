$(document).ready(function(){
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'互动信息',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:1250,
			//fitColumns:true,
//			url:'/weixin-studio/account/selectAllAccount',
			url:'/weixin-studio/question/selectQuestion',
			sortName: 'createtime',
			sortOrder: 'desc',
			idField:'headtitleid',
			pagination: true,
			singleSelect:true,
		    rownumbers:true,
		    queryParams : {'page':1},
		   // frozenColumns:[[{field:'ck',checkbox:true}]],
		  
		    //selectOnCheck: true,
		    //checkOnSelect: true,
	        columns:[
	            [
		            {field:'headtitlename',title:'问卷名称',width:100,align:'center',sortable:true },
		            {field:'createtime',title:'创建时间',width:150,align:'center',sortable:true},
		            {field:'headtitletype',title:'问卷类型',width:100,align:'center',sortable:true},
		            {field:'status',title:'处理状态',width:100,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		if(value=="0"){
		            			return "禁用";
		            		}
		            		if(value=="1"){
		            			return "<a style='color:red'>启用</a>";
		            		}		
		            	
		            	}
		            },
		            {field:'id',title:'操作',width:260,sortable:true,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		var html = '';
		            		
		            		html += '<a href="javascript:qiyong(\''+rowData.headtitleid+'\')">启用</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:jinyong(\''+rowData.headtitleid+'\')">禁用</a>&nbsp;&nbsp;';
		            		console.log("================"+rowData.flag);
		            	
		            		html += '<a href="javascript:chakan(\''+rowData.headtitleid+'\')">查看</a>&nbsp;&nbsp;';
		            	
		            		return html;
		            	}
		            }
	            ]
           ],
			
			
			//onLoadSuccess:function(data){
			//	$.messager.defaults.ok = "确定";
			//	if(data.total==0 && state=="none"){$.messager.alert("提示","根据您的查询条件，未找到相关内容，请重试！","warning");}
			//}
		});
		initGenerglPagination('#dataGrid');
	});


//通过
function chakan(headtitleid){
	window.location="/weixin-studio/question/questionlist.jsp?id="+headtitleid;
}

/**
 * 禁用
 * @param id
 */
function jinyong(headtitleid){
	$.ajax({   
       url:'/weixin-studio/question/questionstateQuestion',
        type: 'POST',   
        async: false,
        data:  {'headtitleBean.headtitleid':headtitleid,'headtitleBean.status':'0'},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $('#dataGrid').datagrid('reload');
    	   }
        }   
	});
}

//启用
function qiyong(headtitleid){
	$.ajax({   
       url:'/weixin-studio/question/questionstateQuestion',
        type: 'POST',   
        async: false,
        data:  {'headtitleBean.headtitleid':headtitleid,'headtitleBean.status':'1'},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $('#dataGrid').datagrid('reload');
    	   }
        }   
	});
}

//修改
	
function update(id){
		
	$('#myform').get(0).reset();
	//$('#myform').form('clear');
	$('#mydialog').dialog('open'); //调用load方法把所选中的数据load到表单中,非常方便
		
	}  	

//回复
function huifu(id,type){
	$('#mydialog').dialog('open');
	$('#table').datagrid('load');
}

//查询
function selectByPage(){
	var flag = $("#flag").val();
	var nickname = $("#nickname").val();
	$("#dataGrid").datagrid({
		url:'/weixin-studio/interact/selectInteract',
		queryParams:{'example.flag':flag,'example.nickname':nickname},
		pageNumber:1
	});
	initGenerglPagination("#dataGrid");
	
}



Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}







