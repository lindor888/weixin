//$(document).ready(function(){
//	$(document).bind('cbox_open', function(){
//		//$(document).unbind("keydown");
//		$(document).bind("keydown","Esc",function(){
//			$.fn.colorbox.close();
//		});
//	});
//});

function checklogin(){
	url = "/CMS/permission/logincheckUserLoginAction.action";
	$.ajax({url:url,async:false,dataType: "json",type:"POST",success:function(data, textStatus){
		if(data.message=='NULL'|| data.message==null){
			//setTimeout("showLogin()",100);
			showLogin();
			return false;
		}else{
		//check after 5 minute 300000
			setTimeout("checklogin()",300000);
			return true;
		}
	}});
}
function showLogin(){
	//$.fn.colorbox({iframe:true,innerWidth:"550", innerHeight:"200", href:"/CMS/login.jsp?flag=1",overlayClose:false,onLoad:function(){$('#cboxClose').remove();}});
	window.parent.location = "/CMS/";
	//window.parent.location = "http://soa.cntv.net/auth/Login";
}

function closeLogin(){
	$.fn.colorbox.close();
	top.location.reload();
	setTimeout("checklogin()",3000);
}

