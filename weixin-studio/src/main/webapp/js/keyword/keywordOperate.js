//列表
var title="";
$(function() {
	title=$('#keywordName').val();
});
$.messager.defaults.ok = "确定";
$.messager.defaults.cancel = "取消";
function sucaiEvent(){
	var rowData = $('#sucai').datagrid('getSelected');
	if(rowData==null){
		$.messager.alert("提示","请选择素材！","info");
	}else{
		window.parent.insertSuCai(rowData);
		parent.$.fn.colorbox.close();
	}
	
}
function insertSuCai(rowData){
	$('#sucaiTitle').val(rowData.catalogTitle);
	$('#id').val(rowData.id);
}
function addKeyWord(){
	var param={};
	var keywordName=$.trim($('#keywordName').val());
	var keywordRule=$('input[name="keywordRule"]:checked').val();
	var status=$('input[name="status"]:checked').val();
	var keywordWeight=$.trim($('#keywordWeight').val());
	var type=$('#type').val();
	var sucaiTitle=$.trim($('#sucaiTitle').val());
	var id=$('#id').val();
	if(!isNaN(keywordWeight)){
		if(keywordName!=null&&keywordName!=""&&keywordRule!=null&&keywordRule!=""&&keywordWeight!=null&keywordWeight!=""&&type!=null&&type!=""&sucaiTitle!=null&&sucaiTitle!=""){
			param={"eidtkeyword.keywordName":keywordName,"eidtkeyword.status":status,"eidtkeyword.keywordRule":keywordRule,"eidtkeyword.keywordWeight":keywordWeight,"eidtkeyword.type":type,"eidtkeyword.catalogTitle":sucaiTitle,"eidtkeyword.materialId":id};
			$.ajax({   
				url:'/weixin-studio/keyword/addKeywordAction?random='+Math.round(Math.random()*10000),
		        type: 'POST',   
		        async: false,
		        data:  param,
		       dataType: 'json',  
		       success:function(data){ 
		    	   if(data.mg=="success"){
		    		   $.messager.alert("提示","添加成功！","info",function(r){
		    			   parent.$.fn.colorbox.close();
		    			   $('#keyword').datagrid('reload');
						});
		    		  
		    	   }
		    	   if(data.mg=="fail"){
		    		   $.messager.alert("操作提示","添加失败！","error",function(r){
		    			   $('#keyword').datagrid('reload');
						});
		    	   }
		    	   if(data.mg=="exist"){
		    		   $.messager.alert("操作提示","匹配词已存在！");
		    	   }
		        }   
		    });
			
		}else{
			 $.messager.alert("提示","有必选项没有写！","warning");
		}
	}else{
		$.messager.alert("提示","权重必须为数字！","warning");
	}
	
}
function updateKwd(){
	var param={};
	var keywordName=$.trim($('#keywordName').val());
	var keywordRule=$('input[name="keywordRule"]:checked').val();
	var status=$('input[name="status"]:checked').val();
	var keywordWeight=$.trim($('#keywordWeight').val());
	var type=$('#type').val();
	var sucaiTitle=$.trim($('#sucaiTitle').val());
	var id=$('#kwdMaterialId').val();
	var eidtkeywordId=$('#eidtkeywordId').val();
	if(!isNaN(keywordWeight)){
		if(keywordName!=null&&keywordName!=""&&keywordRule!=null&&keywordRule!=""&&keywordWeight!=null&keywordWeight!=""&&type!=null&&type!=""&sucaiTitle!=null&&sucaiTitle!=""){
			param={"eidtkeyword.id":eidtkeywordId,"eidtkeyword.keywordName":keywordName,"eidtkeyword.status":status,"eidtkeyword.keywordRule":keywordRule,"eidtkeyword.keywordWeight":keywordWeight,"eidtkeyword.type":type,"eidtkeyword.catalogTitle":sucaiTitle,"eidtkeyword.materialId":id,"eidtkeyword.cacheTitle":title};
			$.ajax({   
				url:'/weixin-studio/keyword/updateKeywordAction?random='+Math.round(Math.random()*10000),
		        type: 'POST',   
		        async: false,
		        data:  param,
		       dataType: 'json',  
		       success:function(data){ 
		    	   if(data.mg=="success"){
		    		   $.messager.alert("提示","修改成功！","info",function(r){
		    			   parent.$.fn.colorbox.close();
						});
		    		  
		    	   }
		    	   if(data.mg=="fail"){
		    		   $.messager.alert("操作提示","修改失败！","error",function(r){
		    			   $('#keyword').datagrid('reload');
						});
		    	   }
		    	   if(data.mg=="exist"){
		    		   $.messager.alert("操作提示","匹配词已存在！");
		    	   }
		        }   
		    });
			
		}else{
			 $.messager.alert("提示","有必选项没有写！","warning");
		}
	}else{
		$.messager.alert("提示","权重必须为数字！","warning");
	}
	
}
function qingkong(){
	$('#sucaiTitle').val('');
}
function quxiao(){
	parent.$.fn.colorbox.close();
}
function rule(id){
	if(id==0){
		$('#keywordWeight').removeAttr("disabled"); 
	}
	if(id==1){
		$('#keywordWeight').val('1');
		$('#keywordWeight').attr("disabled","true");
	}
}
