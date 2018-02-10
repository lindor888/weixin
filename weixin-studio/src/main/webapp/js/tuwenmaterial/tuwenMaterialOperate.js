//列表
$.messager.defaults.ok = "确定";
$.messager.defaults.cancel = "取消";


var relatedDataList = new Array();
function relatedData(Id,relatedObjectsId,relatedTitle,relatedUrl,relatedDate,relatedLogo,relatedType,relatedOrder){
		this.Id=Id== null ? "": Id;
		this.relatedObjectsId=relatedObjectsId== null ? "": relatedObjectsId;
		this.relatedTitle=relatedTitle== null ? "":relatedTitle ;
		this.relatedUrl=relatedUrl== null ? "": relatedUrl;
		this.relatedDate=relatedDate== null ? "": relatedDate;
		this.relatedLogo=relatedLogo == null ? "": relatedLogo;
		this.relatedOrder=relatedOrder == null ? "": relatedOrder;
		this.relatedType=relatedType == null ? "": relatedType;
}

function insertRelatedData(relatedObjectsId,relatedTitle,relatedUrl,relatedDate,relatedLogo,relatedType){
	var listnum=relatedDataList.length;
	for(var k=0;k<relatedDataList.length;k++){
		if((relatedDataList[k].relatedObjectsId==relatedObjectsId)&&(relatedDataList[k].relatedTitle==relatedTitle)){
			return false;//有重复，退出，不添加。
		}
	}
		relatedDataList[listnum]=new relatedData(listnum,relatedObjectsId,relatedTitle,relatedUrl,relatedDate,relatedLogo,relatedType,listnum);
}

function getTotal(){
	var j=0;
	for(;j<10;j++){
		var value=$('#title'+(j+1)).val();
		if(value==null||value==""){
			break;
		}
	}
	return j;
}

function relatedDataToDiv(){//相关插入到指定id,Div
	var j = getTotal();

	if(j==0){
		for(var k=0;k<relatedDataList.length;k++){
			$('#title'+(k+1)).val(relatedDataList[k].relatedTitle);
			$('#url'+(k+1)).val(relatedDataList[k].relatedUrl);
			$('#imageUrl'+(k+1)).val(relatedDataList[k].relatedLogo);
			document.getElementById("pic"+(k+1)).src= relatedDataList[k].relatedLogo;
			document.getElementById("tiao"+(k+1)).style.display='block'; 
			document.getElementById("pic"+(k+1)).style.display='block';
		}
	}else{
		var rdlLen = relatedDataList.length;
		for(var i=j;i<rdlLen;i++){
			$('#title'+(i+1)).val(relatedDataList[i].relatedTitle);
			$('#url'+(i+1)).val(relatedDataList[i].relatedUrl);
			$('#imageUrl'+(i+1)).val(relatedDataList[i].relatedLogo);
			document.getElementById("pic"+(i+1)).src= relatedDataList[i].relatedLogo;
			document.getElementById("tiao"+(i+1)).style.display='block'; 
			document.getElementById("pic"+(i+1)).style.display='block';
		}
	}
		return;
}

function addtxt(){
	var param={};
	var catalogTitle=$.trim($('#catalogTitle').val());
	var status=$('input[name="status"]:checked').val();
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

	var title1=$.trim($('#title1').val());
	var url1=$.trim($('#url1').val());
	var desc1=$.trim($('#desc1').val());
	var imageUrl1=$.trim($('#imageUrl1').val());
	var order1=$.trim($('#order1').val());
	
	var title2=$.trim($('#title2').val());
	var url2=$.trim($('#url2').val());
	var imageUrl2=$.trim($('#imageUrl2').val());
	var order2=$.trim($('#order2').val());
	
	var title3=$.trim($('#title3').val());
	var url3=$.trim($('#url3').val());
	var imageUrl3=$.trim($('#imageUrl3').val());
	var order3=$.trim($('#order3').val());
	
	var title4=$.trim($('#title4').val());
	var url4=$.trim($('#url4').val());
	var imageUrl4=$.trim($('#imageUrl4').val());
	var order4=$.trim($('#order4').val());

	var title5=$.trim($('#title5').val());
	var url5=$.trim($('#url5').val());
	var imageUrl5=$.trim($('#imageUrl5').val());
	var order5=$.trim($('#order5').val());
	
	var title6=$.trim($('#title6').val());
	var url6=$.trim($('#url6').val());
	var imageUrl6=$.trim($('#imageUrl6').val());
	var order6=$.trim($('#order6').val());
	
	var title7=$.trim($('#title7').val());
	var url7=$.trim($('#url7').val());
	var imageUrl7=$.trim($('#imageUrl7').val());
	var order7=$.trim($('#order7').val());
	
	
	var title8=$.trim($('#title8').val());
	var url8=$.trim($('#url8').val());
	var imageUrl8=$.trim($('#imageUrl8').val());
	var order8=$.trim($('#order8').val());
	
	var title9=$.trim($('#title9').val());
	var url9=$.trim($('#url9').val());
	var imageUrl9=$.trim($('#imageUrl9').val());
	var order9=$.trim($('#order9').val());
	
	var title10=$.trim($('#title10').val());
	var url10=$.trim($('#url10').val());
	var imageUrl10=$.trim($('#imageUrl10').val());
	var order10=$.trim($('#order10').val());
	
	
	if(catalogTitle!=null&&catalogTitle!=""&&status!=null&&status!=""
		&&title1!=null&&title1!=""&&desc1!=null
		&&desc1!=""&&imageUrl1!=null&&imageUrl1!="") {
		param={"gm.catalogTitle":catalogTitle,"gm.keyword0":keyword0,"gm.keyword1":keyword1,"gm.status":status,
				"image.title1":title1,"image.url1":url1,"image.desc1":desc1,"image.imageUrl1":imageUrl1,"image.order1":order1,
				"image.title2":title2,"image.url2":url2,"image.imageUrl2":imageUrl2,"image.order2":order2,
				"image.title3":title3,"image.url3":url3,"image.imageUrl3":imageUrl3,"image.order3":order3,
				"image.title4":title4,"image.url4":url4,"image.imageUrl4":imageUrl4,"image.order4":order4,
				"image.title5":title5,"image.url5":url5,"image.imageUrl5":imageUrl5,"image.order5":order5,
				"image.title6":title6,"image.url6":url6,"image.imageUrl6":imageUrl6,"image.order6":order6,
				"image.title7":title7,"image.url7":url7,"image.imageUrl7":imageUrl7,"image.order7":order7,
				"image.title8":title8,"image.url8":url8,"image.imageUrl8":imageUrl8,"image.order8":order8,
				"image.title9":title9,"image.url9":url9,"image.imageUrl9":imageUrl9,"image.order9":order9,
				"image.title10":title10,"image.url10":url10,"image.imageUrl10":imageUrl10,"image.order10":order10
				
				};
		$.ajax({   
				url:'/weixin-studio/Material/addTuWenAction?random='+Math.round(Math.random()*10000),
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
		    		   $.messager.alert("操作提示","图文素材已存在！","error");
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
function addmore(){
	for(var i=1;i<11;i++){
		var value=$('#title'+i).val();
		if(value==null||value==""){
			var tiao="tiao"+i;
			break;
		}
	}
 	
 	var stiao=document.getElementById(tiao);
 	if(stiao != null) {
 		stiao.style.display='block';
 	}
	if(i > 10) {
		$.messager.alert("提示","最多只能添加10条！","warning");
	}
}

function updatetxt(){
	var param={};
	var catalogTitle=$.trim($('#catalogTitle').val());
	var status=$('input[name="status"]:checked').val();
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
	
	
	var textId=$('#textId').val();
	var waccount_id=$('#waccount_id').val();
	
	var title1=$.trim($('#title1').val());
	var url1=$.trim($('#url1').val());
	var desc1=$.trim($('#desc1').val());
	var imageUrl1=$.trim($('#imageUrl1').val());
	var order1=$.trim($('#order1').val());
	
	var title2=$.trim($('#title2').val());
	var url2=$.trim($('#url2').val());
	var imageUrl2=$.trim($('#imageUrl2').val());
	var order2=$.trim($('#order2').val());
	
	var title3=$.trim($('#title3').val());
	var url3=$.trim($('#url3').val());
	var imageUrl3=$.trim($('#imageUrl3').val());
	var order3=$.trim($('#order3').val());
	
	var title4=$.trim($('#title4').val());
	var url4=$.trim($('#url4').val());
	var imageUrl4=$.trim($('#imageUrl4').val());
	var order4=$.trim($('#order4').val());
	

	var title5=$.trim($('#title5').val());
	var url5=$.trim($('#url5').val());
	var imageUrl5=$.trim($('#imageUrl5').val());
	var order5=$.trim($('#order5').val());
	
	var title6=$.trim($('#title6').val());
	var url6=$.trim($('#url6').val());
	var imageUrl6=$.trim($('#imageUrl6').val());
	var order6=$.trim($('#order6').val());
	
	var title7=$.trim($('#title7').val());
	var url7=$.trim($('#url7').val());
	var imageUrl7=$.trim($('#imageUrl7').val());
	var order7=$.trim($('#order7').val());
	
	
	var title8=$.trim($('#title8').val());
	var url8=$.trim($('#url8').val());
	var imageUrl8=$.trim($('#imageUrl8').val());
	var order8=$.trim($('#order8').val());
	
	var title9=$.trim($('#title9').val());
	var url9=$.trim($('#url9').val());
	var imageUrl9=$.trim($('#imageUrl9').val());
	var order9=$.trim($('#order9').val());
	
	var title10=$.trim($('#title10').val());
	var url10=$.trim($('#url10').val());
	var imageUrl10=$.trim($('#imageUrl10').val());
	var order10=$.trim($('#order10').val());
	 
	
		if(catalogTitle!=null&&catalogTitle!=""&&status!=null&&status!=""
			&&title1!=null&&title1!=""&&desc1!=null&&desc1!=""&&imageUrl1!=null&&imageUrl1!=""

		){
			param={"gm.id":textId,"gm.waccount_id":waccount_id,"gm.keyword0":keyword0,"gm.keyword1":keyword1,
					"gm.catalogTitle":catalogTitle,"gm.status":status,"image.title1":title1,
					"image.url1":url1,"image.desc1":desc1,"image.imageUrl1":imageUrl1,"image.order1":order1,
					"image.title2":title2,"image.url2":url2,"image.imageUrl2":imageUrl2,"image.order2":order2,
					"image.title3":title3,"image.url3":url3,"image.imageUrl3":imageUrl3,"image.order3":order3,
					"image.title4":title4,"image.url4":url4,"image.imageUrl4":imageUrl4,"image.order4":order4,
					"image.title5":title5,"image.url5":url5,"image.imageUrl5":imageUrl5,"image.order5":order5,
					"image.title6":title6,"image.url6":url6,"image.imageUrl6":imageUrl6,"image.order6":order6,
					"image.title7":title7,"image.url7":url7,"image.imageUrl7":imageUrl7,"image.order7":order7,
					"image.title8":title8,"image.url8":url8,"image.imageUrl8":imageUrl8,"image.order8":order8,
					"image.title9":title9,"image.url9":url9,"image.imageUrl9":imageUrl9,"image.order9":order9,
					"image.title10":title10,"image.url10":url10,"image.imageUrl10":imageUrl10,"image.order10":order10
				 };
			$.ajax({   
				url:'/weixin-studio/Material/updateTuWenAction?random='+Math.round(Math.random()*10000),
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
		    		   $.messager.alert("操作提示","图文素材已存在！","error");
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

function uploadComplete(response, index){
	var img = eval("("+response+")");
	$("#pic"+index).attr("src", img.image);
	$("#pic"+index).show();
	$("#imageUrl"+index).val(img.image);
}

function showImg(idx) {
	var src = $("#pic"+idx).attr("src");
	$.fn.colorbox({
		photo : true,
		innerWidth : 690,
		innerHeight : 440,
		href : src,
		overlayClose : false
	});
}