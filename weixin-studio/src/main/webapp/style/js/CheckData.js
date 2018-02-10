function checkIsNull(name,viewError){
	     strValue=document.all(name).value;
	     if(strValue==null||strValue==""||strValue.length==0){
	     		//��ʾ���������ǿ��ַ�
	     		alert(viewError); 
	     		document.all(name).focus();
	     		return true;
	     	}else{
	     		return false;
	     		}
	}

//�ж��ǲ����ʼ��ĸ�ʽ
function isEmail (theStr) {
	var atIndex = theStr.indexOf('@');
	var dotIndex = theStr.indexOf('.', atIndex);
	var flag = true;
	theSub = theStr.substring(0, dotIndex+1)

	if ((atIndex < 1)||(atIndex != theStr.lastIndexOf('@'))||(dotIndex < atIndex + 2)||(theStr.length <= theSub.length)) { 
		return false; 
	}else { 
		return true; 
	}
}	
function validate(discreteness,msg){
	var temp=document.all(discreteness).value;
	if(temp==null || temp.replace(/\s/g,"")=="" || temp.length==0){
		alert(msg);
		document.all(discreteness).focus();
		return true;
	}
		return false;
}

function validateInt(discreteness,msg){
   var checkOk="0123456789";
   var CLEN=checkOk.length;
   var checkStr=document.all(discreteness).value;
    for(i=0;i<checkStr.length;i++) {
         for(j=0;j<CLEN;j++) {               
         	if(checkStr.charAt(i)==checkOk.charAt(j)){
                break;
            }    
         }
         if(j==CLEN) {
           alert(msg);
           document.all(discreteness).focus();
           return true;
         }
   }
   return false;   
}
function debarChinese(discreteness,msg){
   var checkOk="0123456789qwertyuiopasdfghjklzxcvbnm";
   var CLEN=checkOk.length;
   var checkStr=document.all(discreteness).value;
    for(i=0;i<checkStr.length;i++) {
         for(j=0;j<CLEN;j++) {               
         	if(checkStr.charAt(i)==checkOk.charAt(j)){
                break;
            }    
         }
         if(j==CLEN) {
           alert(msg);
           document.all(discreteness).focus();
           return true;
         }
   }
   return false;   
}

function validateStr(discreteness,msg){
  
   var checkOk="0123456789#@.$";
   var CLEN=checkOk.length;
   var checkStr=document.all(discreteness).value;
    for(i=0;i<checkStr.length;i++) {
         for(j=0;j<CLEN;j++) {               
           if(checkStr.charAt(i)==checkOk.charAt(j)) {        	
              alert(msg);
              document.all(discreteness).focus(); 
              return true;
           }             	        
         }               
    }
    
    return false;
}    
function validateLength(discreteness,msg){
   	var length=document.all(discreteness).value.length;
	if(length<2){
          	alert(msg);
          	document.all(discreteness).focus();
            return true;		
	}
	return false; 
}
