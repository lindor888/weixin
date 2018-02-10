$(document).ready(function(){
	    var programmeid = window.location.href.split("programmeid=")[1];
		initGenerglDataGrid('#dataGrid');
		$('#dataGrid').datagrid({
			title:'互动信息',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 15, 20, 40, 60, 80 ],
			width:1100,
//			url:'/weixin-studio/account/selectAllAccount',
			url:'/weixin-studio/interact/chankanListInteract?programmeId='+programmeid,
			//sortName: 'updateTime',  按照时间排序
			sortName: 'orderId', 
			sortOrder: 'desc',
			idField:'id',
			pagination: false, //分页组件
		    singleSelect: true,
		    rownumbers:true,
		    queryParams : {'example.page':1},
		    frozenColumns:[[{field:'ck',checkbox:true}]],
		    singleSelect: false,
		    selectOnCheck: true,
		    checkOnSelect: true,
	        columns:[
	            [
		            {field:'headimgurl',title:'头像',width:70,align:'center',sortable:true,
	            	    formatter:function(value,rowData,rowIndex){
	            	    	var html_img = "";
	            	    	if(value!=null){
	            	    		html_img = '<a target="_blank" href="'+value+'"><img src='+value+  ' width="64" height="64"></a>';
	            	    	}
	            	    	
	            	    	return html_img;
		            	}
		            },
		            {field:'nickname',title:'昵称',width:80,height:30,align:'center',sortable:true },
		            {field:'sex',title:'性别',width:50,heigh:30,align:'center',sortable:true},
		            {field:'city',title:'地区',width:50,align:'center',sortable:true},
	                {field:'province',title:'城市',width:50,align:'center',sortable:true},
		            {field:'country',title:'国家',width:50,align:'center',sortable:true },
		            {field:'content',title:'内容',width:230,sortable:true,align:'center',sortable:true,formatter:function(value,rowData,rowIndex){
		            	var html = '';
		            	if(rowData.type==0){
		            		html += '<a title="'+ rowData.content +'">' + rowData.content + '</a>';
		            	}else if(rowData.type==1){
		            		html += '<a target="_blank" href="'+rowData.image+'"><img width="64" height="64" src='+rowData.image+'?r='+Math.random()+' ></a>';
		            	}else if(rowData.type==2){
		            		html += '<audio src="'+rowData.audio+'" controls="controls"></audio>';
		            	}else if(rowData.type==3){
		            		html += '<a target="_blank" href="'+rowData.video+'"><img src="/weixin-studio/style/images/play.png" /></a>';
		            	}
		            	
		            	return html;
		            }},
		            {field:'updateTime',title:'时间',width:150,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		if(value!=null){
		            			value = value.replace('T',' ');
		            		}
		            		return value;
		            	}
		            },
		            {field:'flag',title:'状态',width:80,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		console.info(value);
		            		switch(value){
		            			case 0: return "未处理"; break;
		            			case 1: return "<a style='color:red'>审核通过</a>"; break;
		            			case 2: return "审核未通过"; break;
		            		}
		            	}
		            },
		            {field:'id',title:'操作',width:160,sortable:true,align:'center',sortable:true,
		            	formatter:function(value,rowData,rowIndex){
		            		var html = '';
		            		if(rowData.type==3){
		            			var xuanzhuan = rowData.xuanzhuan;
		            			//html += '<a href="javascript:void(0)" title="向右旋转90度" onclick="xuanzhuanVideo(\''+rowData.id+'\')"><img src="/weixin-studio/style/images/rotate.png" /></a>';
		            			html += '<select onchange="xuanzhuanShipin(this.value,\''+rowData.id+'\')">' +
		            			'<option value="0" '+(xuanzhuan==0?"selected":"")+'>不旋转</option>'+
		            			'<option value="1" '+(xuanzhuan==1?"selected":"")+'>90度</option>'+
		            			'<option value="2" '+(xuanzhuan==2?"selected":"")+'>180度</option>'+
		            			'<option value="3" '+(xuanzhuan==3?"selected":"")+'>270度</option>'+
		            			'</select>';
		            		}
		            		if(rowData.type==0||rowData.type==1){
		            			html += '<a href="javascript:update(\''+rowData.pid+'\',\''+rowData.programme_save_id+'\')">修改</a>&nbsp;&nbsp;';
		            		}
		            		html += '<a href="javascript:tongguo(\''+rowData.pid+'\')">通过</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:jujue(\''+rowData.pid+'\')">拒绝</a>&nbsp;&nbsp;';
		            		html += '<a href="javascript:shanchu(\''+rowData.pid+'\')">删除</a>&nbsp;&nbsp;';
		            		
		            		var openId = rowData.openId;
		            		var huifu = rowData.huifu;
		            		if(openId.indexOf('Foll')!=0){
		            			if(huifu==null||huifu==''){
		            				//超过48小时的信息不能回复
		            				var insertTime = rowData.insertTime.replace('T','');
		            				var now = new Date(new Date().getTime() - 1000 * 60 * 60 *24);
		            				var nowStr = now.Format("yyyy-MM-dd HH:mm:ss");  
		            				if(insertTime > nowStr) {
		            					html += '<a href="javascript:huifu(\''+rowData.orderId+'\',\'0\')">回复消息</a>';
		            				}
		            				
		            			}else{
		            				html += '<a href="javascript:huifu(\''+rowData.orderId+'\',\'1\')">查看回复</a>';
		            			}
		            		}
		            		return html;
		            	}
		            }
	            ]
           ],
			toolbar:[
			{
				text:'批量通过',
					iconCls:'icon-ok',
					disabled:false,
					handler:function(){
						
						var rows = $("#dataGrid").datagrid('getSelections');
					
						
						if(rows.length==0){
							$.messager.alert('警告','请先选中一行记录！');
							return;
						}
						var rowIds = "";
						for(var i=0;i<rows.length;i++){
							
							rowIds += rows[i].orderId + ",";
						}
						rowIds = rowIds.substring(0,rowIds.length-1);
						$('#dataGrid').datagrid('reload');
						$('#dataGrid').datagrid('clearSelections');
						$("input[type='checkbox']").eq(0).attr("checked", false); 
						
						$.ajax({
							url : "/weixin-studio/interact/batchTongguoInteract",
							type : 'POST',
							async : false,
							data : {"rowIds":rowIds},
							success:function(data){ 
					    	   if(data.msg=="success"){
					    		   $.messager.alert('提示','批量通过成功！');
					    		   $('#dataGrid').datagrid('reload');
					    	   }
						    } 
						});
					}
			},
			{
				text:'返回',
					iconCls:'icon-ok',
					disabled:false,
					handler:function(){
						window.location="/weixin-studio/interact/jiemudanViewInteract";
					}
			},
			],
			
			//onLoadSuccess:function(data){
			//	$.messager.defaults.ok = "确定";
			//	if(data.total==0 && state=="none"){$.messager.alert("提示","根据您的查询条件，未找到相关内容，请重试！","warning");}
			//}
		});
		initGenerglPagination('#dataGrid');
	});


//通过
function tongguo(id){
	$.ajax({   
       url:'/weixin-studio/interact/tongguojiemuInteract',
        type: 'POST',   
        async: false,
        data:  {'bean.flag':1,'bean.id':id},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $('#dataGrid').datagrid('reload');
    	   }
        }   
	});
}

/**
 * 选择删除互动节目
 * @param id
 */
function shanchu(id){

	$.ajax({   
       url:'/weixin-studio/interact/shanchujiemuInteract',
       type: 'POST',   
       async: false,
       data:  {'bean.flag':1,'bean.id':id},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   //$.messager.alert('提示','选择成功！');
    		   $('#dataGrid').datagrid('reload');
    	   }else{
    		   
    		   $.messager.alert('提示','删除失败！');
    	   }
        }   
	});
}

//拒绝
function jujue(id){
	
	$.ajax({   
       url:'/weixin-studio/interact/jiemudanupdateInteract',
        type: 'POST',   
        async: false,
        data:  {'bean.flag':2,'bean.id':id},
       dataType: 'json',  
       success:function(data){ 
    	   if(data.msg=="success"){
    		   $('#dataGrid').datagrid('reload');
    	   }
        }   
	});
}

//修改
function update(id,programme_save_id){
	$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:500,href:"/weixin-studio/interact/updatejiemudanInteract.jsp?id="+id+"&programme_save_id="+programme_save_id,overlayClose:false,onClosed:function(){
		$('#dataGrid').datagrid("reload");
	}});
}

//回复
function huifu(id,type){
	$.fn.colorbox({iframe:true,innerWidth:700,innerHeight:500,href:"/weixin-studio/interact/huifuInteract.jsp?id="+id+"&type="+type,overlayClose:false,onClosed:function(){
		$('#dataGrid').datagrid("reload");
	}});
}

//查询
function selectByPagechakan(){
	var flag = $("#flag").val();
	var nickname = $("#nickname").val();
	$("#dataGrid").datagrid({
		url:'/weixin-studio/interact/selectchakanInteract',
		queryParams:{'bean.flag':flag,'bean.nickname':nickname},
		pageNumber:1
	});
	initGenerglPagination("#dataGrid");
	
}

function shuaxin(){
    var programmeid = window.location.href.split("programmeid=")[1];
	$("#dataGrid").datagrid({
		url:'/weixin-studio/interact/chakanInteract?programmeid='+programmeid,
		queryParams:{'example.flag':'','example.nickname':''},
		pageNumber:1
	});
	$("#flag").val('');
	$("#nickname").val('');
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

$(function(){
	window.setInterval(function(){
		var curPage = $("#dataGrid").datagrid('getPager').data("pagination").options.pageNumber;
		if(curPage==1){
			//selectByPage();
		}
	},30000);
});

function xKeyEvent(e){
	var e = e || window.event;
	switch(e.keyCode | e.which | e.charCode) {//按键 ASCII 码值 
		case 112:{
			shuaxin();
		}
	}  
}

//旋转视频
function xuanzhuanShipin(xuanzhuan,id){
	$.ajax({   
	       url:'/weixin-studio/interact/updateInteract',
	        type: 'POST',   
	        async: false,
	        data:  {'bean.xuanzhuan':xuanzhuan,'bean.id':id},
	       dataType: 'json',  
	       success:function(data){ 
	    	   
	        }   
		});
}

//真实旋转视频
function xuanzhuanVideo(id){
	$.ajax({   
	       url:'/weixin-studio/interact/xuanzhuanVideoInteract',
	        type: 'POST',   
	        async: false,
	        data:  {'bean.id':id},
	       dataType: 'json',  
	       success:function(data){ 
	    	   $('#dataGrid').datagrid("reload");
	        }   
		});
}
