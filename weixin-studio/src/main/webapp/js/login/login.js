function login(){
	var username=$.trim($('#userName').val());
	var password=$.trim($('#passWord').val());
	if(username!=null&&username!=""&&password!=null&&password!=""){
		$('#msgs').html("");
		$('#loginForm').submit();
	}else{
		$('#msgs').html("用户名和密码不能为空");
	}
}

document.onkeydown=function(event){
		e=event?event:(window.event?window.event:null);
		if(e.keyCode==13){
			$("#login").click();
		}
	}
