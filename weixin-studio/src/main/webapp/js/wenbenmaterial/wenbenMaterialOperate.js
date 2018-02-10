//列表
$.messager.defaults.ok = "确定";
$.messager.defaults.cancel = "取消";
function addtxt(){
	var param={};
	var catalogTitle=$.trim($('#catalogTitle').val());
	var status=$('input[name="status"]:checked').val();
	var wenbenxml=$.trim($('#wenbenxml').val());
	var keyword0 =$.trim($('#keyword0').val());
	var keyword1 =$.trim($('#keyword1').val());
	
	
	if(keyword0 != null && keyword0 != '') {
		var kwds0 = keyword0.split(",");
		for(var i = 0; i < kwds0.length; i++) {
			for(var j = 0; j < kwds0.length && j != i; j++) {
				if($.trim(kwds0[i]) == $.trim(kwds0[j])) {
					$.messager.alert("提示","模糊关键词内不能有重复！","error");
					return;
				}
			}
		}
	}
	if(keyword1 != null && keyword1 != '') {
		var kwds1 = keyword1.split(",");
		for(var i = 0; i < kwds1.length; i++) {
			for(var j = 0; j < kwds1.length && j != i; j++) {
				if($.trim(kwds1[i]) == $.trim(kwds1[j])) {
					$.messager.alert("提示","精确关键词内不能有重复！","error");
					return;
				}
			}
		}
	}
	if(keyword0 != null && keyword0 != '' && keyword1 != null && keyword1 != '') {
		var kwds0 = keyword0.split(",");
		var kwds1 = keyword1.split(",");
		for(var i = 0; i < kwds0.length; i++) {
			for(var j = 0; j < kwds1.length; j++) {
				if($.trim(kwds0[i]) == $.trim(kwds1[j])) {
					$.messager.alert("提示","两种关键词类型内不能有重复！","error");
					return;
				}
			}
		}
	}
	if(wenbenxml.length>500){
		$.messager.alert("提示","内容不超过500字符或者汉字！","warning");
		return;
	}
	
	if(catalogTitle!=null&&catalogTitle!=""&&wenbenxml!=null&&wenbenxml!=""&&status!=null&status!=""){
		param={"text.catalogTitle":catalogTitle,"text.keyword0":keyword0,"text.keyword1":keyword1,"text.status":status,"text.wenbenxml":wenbenxml};
		$.ajax({   
				url:'/weixin-studio/Material/addWenBenAction?random='+Math.round(Math.random()*10000),
		        type: 'POST',   
		        async: false,
		        data:  param,
		       dataType: 'json',  
		       success:function(data){ 
		    	   if(data.mg=="success"){
		    		   $.messager.alert("提示","添加成功！","info",function(r){
		    			   parent.$.fn.colorbox.close();
						});
		    		  
		    	   } else if(data.mg=="fail"){
		    		   $.messager.alert("操作提示","添加失败！","error");
		    	   } else if(data.mg=="exist"){
		    		   $.messager.alert("操作提示","文本素材已存在！","error");
		    	   } else if(data.mg == 'jqexist') {
		    		   $.messager.alert("操作提示","精确关键词已存在！","error");
			       } else if(data.mg == 'mhexist') {
			    	   $.messager.alert("操作提示","模糊关键词已存在！","error");
			       }
		        }   
		    });
			
		}else{
			 $.messager.alert("提示","有必选项没有写！","warning");
		}
	
	
}
function updatetxt(){
	var param={};
	var catalogTitle=$.trim($('#catalogTitle').val());
	var status=$('input[name="status"]:checked').val();
	var wenbenxml=$.trim($('#wenbenxml').val());
	var keyword0 =$.trim($('#keyword0').val());
	var keyword1 =$.trim($('#keyword1').val());
	

	if(keyword0 != null && keyword0 != '') {
		var kwds0 = keyword0.split(",");
		for(var i = 0; i < kwds0.length; i++) {
			for(var j = 0; j < kwds0.length && j != i; j++) {
				if($.trim(kwds0[i]) == $.trim(kwds0[j])) {
					$.messager.alert("提示","模糊关键词内不能有重复！","error");
					return;
				}
			}
		}
	}
	if(keyword1 != null && keyword1 != '') {
		var kwds1 = keyword1.split(",");
		for(var i = 0; i < kwds1.length; i++) {
			for(var j = 0; j < kwds1.length && j != i; j++) {
				if($.trim(kwds1[i]) == $.trim(kwds1[j])) {
					$.messager.alert("提示","精确关键词内不能有重复！","error");
					return;
				}
			}
		}
	}
	if(keyword0 != null && keyword0 != '' && keyword1 != null && keyword1 != '') {
		var kwds0 = keyword0.split(",");
		var kwds1 = keyword1.split(",");
		for(var i = 0; i < kwds0.length; i++) {
			for(var j = 0; j < kwds1.length; j++) {
				if($.trim(kwds0[i]) == $.trim(kwds1[j])) {
					$.messager.alert("提示","两种关键词类型内不能有重复！","error");
					return;
				}
			}
		}
	}
	if(wenbenxml.length>500){
		$.messager.alert("提示","内容不超过500字符或者汉字！","warning");
		return;
	}
	
	var textId=$('#textId').val();
	var waccount_id=$('#waccount_id').val();
		if(catalogTitle!=null&&catalogTitle!=""&&wenbenxml!=null&&wenbenxml!=""&&status!=null&status!=""){
			param={"text.id":textId,"text.waccount_id":waccount_id,"text.keyword1":keyword1,"text.keyword0":keyword0,"text.catalogTitle":catalogTitle,"text.status":status,"text.wenbenxml":wenbenxml};
			$.ajax({   
				url:'/weixin-studio/Material/updateWenBenAction?random='+Math.round(Math.random()*10000),
		        type: 'POST',   
		        async: false,
		        data:  param,
		       dataType: 'json',  
		       success:function(data){ 
		    	   if(data.mg=="success"){
		    		   $.messager.alert("提示","修改成功！","info",function(r){
		    			   parent.$.fn.colorbox.close();
						});
		    	   } else if(data.mg=="fail"){
		    		   $.messager.alert("操作提示","添加失败！","error");
		    	   } else if(data.mg=="exist"){
		    		   $.messager.alert("操作提示","文本素材标题已存在！","error");
		    	   } else if(data.mg=="noexist"){
		    		   $.messager.alert("操作提示","文本素材不存在！","error");
		    	   } else if(data.mg == 'jqexist') {
		    		   $.messager.alert("操作提示","精确关键词已存在！","error");
			       } else if(data.mg == 'mhexist') {
			    	   $.messager.alert("操作提示","模糊关键词已存在！","error");
			       }
		        }   
		    });
			
		}else{
			 $.messager.alert("提示","有必选项没有写！","warning");
		}
	
}

function quxiao(){
	parent.$.fn.colorbox.close();
}
