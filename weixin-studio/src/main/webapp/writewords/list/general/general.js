	var c_width = 700;
	var c_height = 400;
	
	//打开弹出层
	function colorBox(url){
		$.fn.colorbox({iframe:true,innerWidth:c_width, innerHeight:c_height, href:url,overlayClose:false,onClosed:function(){  }});
	}
	//关闭弹出层
	function closeColorBox(isReload){
		$.fn.colorbox.close();
		//if(isReload)
		if(isReload) {
			if (window.frames['frame2'] == null) window.frames[2].document.location.href = window.frames[2].document.location.href;
			else window.frames['frame2'].document.location.href = window.frames['frame2'].document.location.href;
		}
	}
	
   //UI数据表格
   function initGenerglDataGrid(id){
		$(id).datagrid({
			pageSize:10,
			pageList:[10,20,40,60,80],
			width:720,
			nowrap: false,
			//striped: true,
			loadMsg:"正在加载数据，请等待!",
			pagination:true,
			//rownumbers:true,
			singleSelect:true,
			remoteSort:false
		});
   }
   
   //UI分页
   function initGenerglPagination(id){
	
	   var _pag = $(id).datagrid("getPager");
	  
	   if (_pag){
	   $(_pag).pagination({
	   showPageList:false,
	   beforePageText:" 第 ",
	   afterPageText:" 页 共{pages}页 ",
	   displayMsg:" 第 {from} 至 {to}条  共 {total} 条 "
	   });
	   }
	}
   
   //返回表单中参数列表
   function getParam(formId){
		var param = {};
		var param_array = $("#"+formId).serializeArray();
		$.each(param_array,function(i,n){
			switch(n["name"]){
				case "queryData.login":
					$.extend(param,{"queryData.login":n["value"]});
					break;
				case "queryData.username":
					$.extend(param,{"queryData.username":n["value"]});
					break;
				case "queryData.department":
					$.extend(param,{"queryData.department":n["value"]});
					break;
				case "queryData.title":
					$.extend(param,{"queryData.title":n["value"]});
					break;
			   case "queryData.type":
					$.extend(param,{"queryData.type":n["value"]});
					break;
			   case "queryData.roleId":
					$.extend(param,{"queryData.roleId":n["value"]});
					break;
			   case "queryData.state":
					$.extend(param,{"queryData.state":n["value"]});
					break;
			   case "queryData.nickname":
					$.extend(param,{"queryData.nickname":n["value"]});
					//alert("asdfasdf");
					//$.messager.alert('提示','sdsdfsdaasdfafdzcsadsvxzcvsaf');
					break;
			   case "queryData.province":
					$.extend(param,{"queryData.province":n["value"]});
					//alert("asdfasdf");
					
					break;
			   case "queryData.sex":
					$.extend(param,{"queryData.sex":n["value"]});
					//alert("asdfasdf");
					break;
			}
		});
		return param;
	}
   
   //表单搜索
   function searchForm(formId,dataGridId,url){
     
       if(formId=='operatelogForm'){     
	       var s1 = document.getElementById("sdate1").value;
	       var e1 = document.getElementById("edate1").value;                        
	   if(s1.length>0 && e1.length>0){
 
        var s1_ = s1.split(" ");
        var e1_ = e1.split(" ");        

          
        var ymd1 = s1_[0].split("-");
        var ymd2 = e1_[0].split("-");
        var time_forie1 = new Date(ymd1[0],ymd1[1],ymd1[2]);
        var time_forie2 = new Date(ymd2[0],ymd2[1],ymd2[2]);
        var time_ie1 = (time_forie2.getTime()-time_forie1.getTime())/3600/1000 ;    
    

        if(time_ie1 > 744)
        {
           $.messager.alert('提示','时间间隔不能大于一个月（月/31天）','warning');
           return false;
        }
        
        //mdy hgh,将日期字符串转成时间long进行比较
        var s1_long = Date.parse(s1.replace(/-/g,"/"));
        var e1_long = Date.parse(e1.replace(/-/g,"/"));
        if(s1_long > e1_long){
			$.messager.alert('提示','开始时间不能大于结束时间','warning');
			 return false;
        }
       }else if((s1.length>0 && e1.length<=0)||(s1.length<=0 && e1.length>0)){
      	   $.messager.alert('提示','请将时间填写完整','warning');
			 return false;
       }
      }else if(formId=='loginlogForm'){
       var s2 = document.getElementById("sdate2").value;
       var e2 = document.getElementById("edate2").value;
       if(s2.length>0 && e2.length>0){
       
        var s2_ = s2.split(" ");
        var e2_ = e2.split(" ");
        var s2Date = new Date(s2_[0]);
        var e2Date = new Date(e2_[0]); 
        var time2 = (e2Date.getTime()-s2Date.getTime())/3600/1000 ;        
       
       
        var ymd3 = s2_[0].split("-");
        var ymd4 = e2_[0].split("-");
        var time_forie3 = new Date(ymd3[0],ymd3[1],ymd3[2]);
        var time_forie4 = new Date(ymd4[0],ymd4[1],ymd4[2]);
        var time_ie2 = (time_forie4.getTime()-time_forie3.getTime())/3600/1000 ;   
       
       
        if(time_ie2 > 2232)
        {
           $.messager.alert('提示','时间间隔不能大于三个月（月/31天）','warning');
            return false;
        }
        //mdy hgh,将日期字符串转成时间long进行比较
        var s2_long = Date.parse(s2.replace(/-/g,"/"));
        var e2_long = Date.parse(e2.replace(/-/g,"/"));
        if(s2_long > e2_long){
			$.messager.alert('提示','开始时间不能大于结束时间','warning');
			 return false;
        }
         }else if((s2.length>0 && e2.length<=0)||(s2.length<=0 && e2.length>0)){
      	   $.messager.alert('提示','请将时间填写完整','warning');
			 return false;
         }
         }
		var param = getParam(formId);
		$("#"+dataGridId).datagrid({
			url:url,
			queryParams:param,
			pageNumber:1
		});
		initGenerglPagination("#"+dataGridId);
	}
