$(function() {
	 $('#chooseText').dialog('close');
	  $('#chooseContent').dialog('close');
		initMenu();
		$("#header_menu").attr("class", "selected");
		//add_menu(1, 0, 0);
	//	$("#bottom_button").css("display", "none");
	
	});
var initMenuJson = "";

function get_token(wx_yx, evt) {
	if (wx_yx == 'weixin') {
		var appid = $("#appid").val();
		var secret = $("#secret").val();
	} else {
		var appid = $("#appid_yixin").val();
		var secret = $("#secret_yixin").val();
	}
	if (appid != '' && secret != '') {
		$.post("/client/app/menu/gettoken", {wx_yx: wx_yx, appid: appid, secret: secret}, function(msg) {
			if (msg == 'success') {
				$("#appid").css("readonly", true);
				$("#secret").css("readonly", true);
				note_info('授权成功!', 'green', evt);
			} else {
				note_info(msg, 'yellow', evt);
				//alert(msg);
			}
		});
	}
}
function add_menu(typeid, parentid, id) {
	
	$("#menu_key_id").val("");
	$("#savebtn").css("visibility", "visible");
	//typdid：1二级，2二级
	$("#fristnote").css("display", "none");
	$("#menuform").css("display", "block");
	//$("#bottom_button").css("display", "block");
	$("#typeid").val(typeid);
	$("#parentid").val(parentid);
	$("#id").val(id);
	if (typeid == '1') {
		$("#menu_name").val("");
		$("#menu_key").val("");
		$("#menu_name").attr("maxlength", '5');
		$("#limitnote").html("一级菜单可输入5个字符");
	} else if (typeid == '2') {
		$("#menu_name").attr("maxlength", '7');
		$("#limitnote").html("二级菜单可输入7个字符");
	}
	if (id!=null && id != '0') {
		$.post($("#basePath").val()+'/account/selectByIdCustomizemenuAction', {id: id}, function(msg) {
		//	var jsoninfo = eval('(' + msg + ')');
			
			$("#menu_name").val(msg.rows.name);
			
			if(msg.rows.menutype!='view')
			$("#menu_key").val(msg.rows.catalogTitle);//内容？
			else
				
				$("#menu_key").val(msg.rows.materialid);//内容？
			
			$("#menu_type").val(msg.rows.menutype);
			
			if(msg.rows.menutype!='view')
			$("#menu_key_id").val(msg.rows.materialid);
			chooseType("false");
		});
	} else {
		$("#menu_name").val("");
		$("#menu_key").val("");
	}
	
}
function initMenu(){
	var url="";
	initMenuJson = "";
	$(".CustomSetup_left").html("");
	var ids=$("#waccountids").val();
	
	if(null!=ids&&ids!="")
		url=$("#basePath").val()+'/account/selectAllCustomizemenuAction?method=zhidingyi&queryData.waccountId='+ids; 

	 else			
			 url=$("#basePath").val()+'/account/selectAllCustomizemenuAction?method=online';
	
		$.post(url,function(strJSON) {
		var num = 0;
		var sub_num = 0;
		var one_flag=false;
		var two_flag=false;
		var two_num=0;
		var menuHtml = "";
		var tj_id_str = "";
		menuHtml+="<dl>"+
					"	<dt>"+
					"		<strong style=\"color:#59a416; font-size:16px;\">"+
					'		菜单列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="pub(event);" value="发布到微信" class="btngreen30" id="pubbtn" />'+
					"		</strong>"+
					"	</dt>"+
					"	<dd id=\"tjyj\">"+
					"		<a title=\"添加一级菜单\" onclick=\"add_menu(1, 0, 0);\" href=\"javascript:void(0);\">"+
					"			<img width=\"20\" height=\"20\" alt=\"添加一级菜单\" src=\""+$('#basePath').val()+"/images/bottom_menu_12.jpg\">"+
					"		</a>"+
					"	</dd>"+
					"</dl>";
	initMenuJson +="{\"button\":[";
		 for (var i = 0; i < strJSON.length; i++) {
		
			num++;
		  var tj_id = "";
			tj_id =  strJSON[i].id+num;
		
			
			menuHtml += "<dl>"+
					"	<dt style=\"color:#59a416;\">"+strJSON[i].name+"</dt>"+
					"	<dd class=\"bm_app_bj\">"+
						"		<a href=\"javascript:void(0);\" onclick=\"add_menu(1, 0, '"+strJSON[i].id+"');\" title=\"编辑\"></a>"+
						"	</dd>"+
					"	<dd class=\"bm_app_sc\">"+
						"		<a href=\"javascript:void(0);\" onclick=\"del('"+strJSON[i].id+"');\" title=\"删除\"></a>"+
						"	</dd>"+
						"	<dd class=\"bm_app_tj\" id=\""+tj_id+"\">"+
						"		<a href=\"javascript:void(0);\" onclick=\"add_menu(2, '"+strJSON[i].id+"', 0);\" title=\"添加二级菜单\"></a>"+
						"	</dd>"+
						"</dl>";
			initMenuJson += "   {"+
			"         \"name\":\""+strJSON[i].name+"\",";
			
			if (strJSON[i].sub_button== undefined || strJSON[i].sub_button.length == 0) {
				
				if(strJSON[i].contentType=="text"||strJSON[i].contentType=="content"){
					
					initMenuJson +="          \"type\":\"click\",";
				}else{
					initMenuJson +="          \"type\":\"view\",";
				}
				
				if(strJSON[i].contentType=="view"){
					initMenuJson +="          \"url\":\""+strJSON[i].MaterialId+"\"";
				}else{
					initMenuJson +=	"          \"key\":\""+strJSON[i].MaterialId+"\"";
				}
				
				initMenuJson += "      },";
			}else if (strJSON[i].sub_button!= undefined && strJSON[i].sub_button.length != 0) {
				
				initMenuJson +="           \"sub_button\":[";
				var json1=strJSON[i].sub_button;
				
				for (var j = 0; j < json1.length; j++) {
				
					menuHtml += "<dl>"+
								"	<dt>╚ "+json1[j].name+"</dt>"+
								"	<dd class=\"bm_app_bj\">"+
								"		<a href=\"javascript:void(0);\" onclick=\"add_menu(2,'"+json1[j].parentid+"', '"+json1[j].id+"');\" title=\"编辑\"></a>"+
								"	</dd>"+
								"	<dd class=\"bm_app_sc\">"+
								"		<a href=\"javascript:void(0);\" onclick=\"del('"+json1[j].id+"');\" title=\"删除\"></a>"+
								"	</dd>"+
								"</dl>";
					initMenuJson += "   {"+
					"         \"name\":\""+json1[j].name+"\",";
					if(json1[j].contentType=="text"||json1[j].contentType=="content"){
						initMenuJson +="          \"type\":\"click\",";
					}else{
						initMenuJson +="          \"type\":\"view\",";
					}
					
					if(json1[j].contentType=="view"){
						initMenuJson +="          \"url\":\""+json1[j].MaterialId+"\"";
					}else{
						initMenuJson +=	"          \"key\":\""+json1[j].MaterialId+"\"";
					}
					if(j==json1.length-1){
						initMenuJson += "      }";
					}else{
						initMenuJson += "      },";
					}
				}
				initMenuJson +="]},";
			
				if(json1.length>=5){
					two_flag=true;
				//	two_num=json1.length;
				//	$("#two_num").val(two_num+"*"+strJSON[i].parentid);
					
					tj_id_str+=tj_id+"#";
				}
				
				
			}
		 }
		 
		 
		 
		initMenuJson=initMenuJson.substring(0,initMenuJson.length-1);
		initMenuJson +="]}";
		if(num>=3){
			one_flag=true;
			$("#one_num").val(num);
			
		}
		$(".CustomSetup_left").html(menuHtml);
		//一级菜单不允许超过3个
		if(one_flag){
			$("#tjyj").html("");
		}
		//每个一级菜单下的二级菜单不允许超过5个
		if(two_flag){
			
			tj_id_str = tj_id_str.substring(0,tj_id_str.length-1);
			
			if(tj_id_str.indexOf("#")>=0){
				var tj_id_arr;   
				tj_id_arr=tj_id_str.split("#");
				for(var i=0;i<tj_id_arr.length;i++){
					$("#"+tj_id_arr[i]).html("");
				}
			}else{
				$("#"+tj_id_str).html("");
			}
			
		
	}
	});
}
function checkUrl(str) {
	var RegUrl = new RegExp();
	RegUrl.compile("^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$");
	if (!RegUrl.test(str)) {
	return false;
	}
	return true;
	} 
function save(evt) {
	$.messager.defaults.ok = "确定";
	var waccountids= $("#waccountids").val();
	var parentid = $("#parentid").val();
	var   filter=/^[^\u4e00-\u9fa5]{0,}$/;
	var menu_type = $("#menu_type").val();
	var menu_name = $.trim($("#menu_name").val());
	
	var menu_key = $.trim($("#menu_key").val());
	
	if(menu_name==''){
		$.messager.alert("提示","请输入菜单名称！","warning");
		return ;
		
	}
	
	if(menu_key==''){
		$.messager.alert("提示","请填入链接或文本或图文新闻","warning");
		return ;
	}
	
	if(menu_type=='view'){
		
	if(!filter.test(menu_key)){
		$("#chooseButton").text("不允许有汉字");
		return;
	}
	/* url 验证
	if(!checkUrl(menu_key)){
		$("#chooseButton").text("输入正确的链接地址");
		return;
	}
	*/
	}
	var menu_key_id = $("#menu_key_id").val();
	
	var myparentId=$("#myparentId").val();
	
	
	var typeid = $("#typeid").val();
	var id = $("#id").val();
	$("#savebtn").css("visibility", "hidden");
	//$("#savebtn").css("disabled", true);
	//$("#pubbtn").css("disabled", true);
	/*if (typeid == '1' && menu_key == '') {
		menu_key = '无';
	}*/
	if (menu_name != '' && menu_key != '') {
		//$.post("/client/app/menu/checkmenu", {menu_key: menu_key}, function(msg) {
			/*if (menu_key == '无' || menu_type == 'view') {
				msg = 'can_use';
			}*/
			//if (msg == 'can_use') {
				
	$.post($("#basePath").val()+'/account/addmenuCustomizemenuAction?method=add', {menu_type: menu_type, menu_name: menu_name, menu_key: menu_key, typeid: typeid, parentid: parentid, id: id,menu_key_id:menu_key_id,waccountids:waccountids}, function(msg) {
		if(msg.message=="ok"){
			$.messager.alert("提示","操作成功！","warning");	
		    initMenu();
		   $("#menu_name").val("");
		   $("#menu_key").val("");
		   $("#id").val("0");
		   $("#savebtn").css("visibility", "hidden");
		    
		}else {
			
			$.messager.alert("提示","操作失败,"+msg.message,"warning");

		}
				});
			/*} else {
				note_info('此规则不存在或已关闭，请先在其它版块中添加此规则。', 'yellow', evt);
			}*/
		//});
	} else {
		note_info('请输入菜单名称和绑定的规则！', 'yellow', evt);
		//$("#savebtn").css("disabled", false);
		//$("#pubbtn").css("disabled", false);
	}
	
}
function del(id) {
	$.messager.defaults.ok = "确定";
	if (confirm("确认删除此菜单项？")) {
		$("#two_num").val("");
		$("#one_num").val("");
		$.post($("#basePath").val()+"/account/delmenuCustomizemenuAction", {id: id}, function(msg) {
			
			if(msg.message=="ok"){
				$.messager.alert("提示","操作成功！","warning");	
				 $("#menu_name").val("");
				 $("#menu_key").val("");
				 $("#savebtn").css("visibility", "hidden");
				 initMenu();
				 
			}else if(msg.message=="error"){
				
				$.messager.alert("提示","操作失败，请重试！","warning");

			}
		});
	}
}
function pub(evt) {
	$.messager.defaults.ok = "确定";
	if (confirm("确认发布？")) {
		//alert(initMenuJson);
		$.post($("#basePath").val()+"/account/pubweixinCustomizemenuAction", {initMenuJson:initMenuJson}, function(msg) {
			if(msg.message=="ok"){
				$.messager.alert("提示","操作成功！","warning");	
			initMenu();
			// $("#bottom_button").css("display", "none");
			}else if(msg.message=="error"){
				
				$.messager.alert("提示","操作失败，请重试！","warning");

			}
		});
	}
}

function fill_rule(menu_key) {
	$("#menu_key").val(menu_key);
}

function chooseContent(){
	$('#chooseContent').dialog('open').dialog({
		title:"挑选图文新闻",
		top:200
	});
	var waccount=$("#waccountids").val();
	$("#catalogTitlequeryContent").val("");
	$("#contentInfo").datagrid({
		title : '挑选图文新闻',
		pageNumber : 1,
		pageSize : 10,
		queryParams:{},
		width :490,
		nowrap : true,
		striped : true,
		remoteSort : true,
		loadMsg : '正在加载数据......',
		dataType : 'json',
		type : "post",		url : $("#basePath").val()+'/Material/queryTuWenAction?graphicMaterial.waccount_id='+waccount,
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
		{field:'birthDate',title:'操作',width:120,align:'center',sortable:true,
        	formatter:function(value,rowData,rowIndex){
        
            	var edit_html = "";
            	edit_html +='<input type="checkbox" name="ck" onclick="chooseOne(this);"/>&nbsp;';
            	       		return edit_html;
        	}}
		] ],
		rownumbers : true,
		singleSelect : true,
		pagination : true
       
	});
	initGenerglPagination('#contentInfo');
}

function chooseText(){
	
	
	$('#chooseText').dialog('open').dialog({
		title:"挑选文本",
		top:200
	});
	var waccountid=$("#waccountids").val();
	
	
	$("#catalogTitlequeryText").val("");
	$("#textInfo").datagrid({
		title : '挑选文本',
		pageNumber : 1,
		pageSize : 10,
		width :490,
		queryParams:{},
		//remoteSort : true,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		loadMsg : '正在加载数据......',
		dataType : 'json',
		type : "post",
		url : $("#basePath").val()+'/Material/queryWenBenAction?textMaterial.waccount_id='+waccountid,
		sortName: 'id', 
		sortOrder: 'desc',
		idField:'id',
		//remoteSort:false,//排序关键//$("#basePath").val()+'/Material/queryWenBenAction?textMaterial.waccount_id='+$("#waccountids").val(),
		columns : [ [ 
		{
			field : 'catalogTitle',
			title : '素材名称',
			width : 290,
			align : 'center',
			sortable : true
		
			
		},
		{field:'birthDate',title:'操作',width:130,align:'center',sortable:true,
        	formatter:function(value,rowData,rowIndex){
        
            	var edit_html = "";
            	edit_html +='<input name="ck" type="checkbox" onclick="chooseOne(this);" />&nbsp;';
            	       		return edit_html;
        	}}
		] ]
		
		
       	
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
	var catalogTitlequeryContent=$("#catalogTitlequeryContent").val();
	var param={"graphicMaterial.catalogTitle":catalogTitlequeryContent};
	var query_url=$("#basePath").val()+'/Material/queryTuWenAction';
//	query_url=encodeURI(query_url);
	$('#contentInfo').datagrid({
		queryParams:param,
		type:"post",
		pageNumber:1,
		url:query_url
	});
	initGenerglPagination('#contentInfo');
}
function queryText(){
	var param={"textMaterial.catalogTitle":$("#catalogTitlequeryText").val()};
	var query_url=$("#basePath").val()+'/Material/queryBytitleWenBenAction';
//	query_url=encodeURI(query_url);
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
	
	if (row) {
		$("#menu_key").val(row.catalogTitle);
		$("#menu_key_id").val(row.id);
		$('#chooseContent').dialog('close');
	}else{
		alert("请选择默认词");
	}
	$("#catalogTitlequeryContent").val("");
	
}
function sureText(){
	var row = $('#textInfo').datagrid('getSelected');
	
	if (row) {
		$("#menu_key").val(row.catalogTitle);
		$("#menu_key_id").val(row.id);
		$('#chooseText').dialog('close');
	}else{
		alert("请选择默认词");
	}
	$("#catalogTitlequeryText").val("");
}
function chooseType(flag){
	$("#catalogTitlequeryContent").val("");
	$("#catalogTitlequeryText").val("");
	
		if(flag=="true"){
			$("#menu_key").val("");
			$("#menu_key_id").val("");
		}
	 var type_str=document.getElementById("menu_type").value;
	 if(type_str=="view"){
		 $("#menu_key").removeAttr("readonly");
		 $("#chooseButton").html("链接限制最长256字符，不允许汉字");
	 }else if(type_str=="text"){
		 $("#menu_key").attr("readonly", "readonly");
		 $("#chooseButton").html("<a href=\"javascript:void(0);\" onclick=\"chooseText();\" style=\"color:#59a416;\" >挑选</a>");
	 }else if(type_str=="content"){
		 $("#menu_key").attr("readonly", "readonly");
		 $("#chooseButton").html("<a href=\"javascript:void(0);\" onclick=\"chooseContent();\" style=\"color:#59a416;\" >挑选</a>");
	 }
}