	function upload(element,url,index){
		var sessionIda = getCookie("JSESSIONID");
		$(element).uploadify({
	    	'uploader'       : '/weixin-studio/style/js/uploadify.swf',  
			'script'         : url+';jsessionid='+sessionIda,
			'cancelImg'      : '/weixin-studio/style/images/cancel.png', 
			'buttonImg' 	 : '/weixin-studio/style/images/upload.jpg', 
	        'fileDataName'   : 'fileInput',  
	        // 'queueID': '', 
	        'method'         : 'POST',
	        'sizeLimit'      : 13073741824,
			'width': 50,
			'height': 30,  
			'fileDesc' : 'jpg、gif、jpeg、png类型文件',
			'fileExt' : '*.jpg;*.gif;*.png;*.jpeg',
	        'auto': false,
	        'multi': false,
	        'simUploadLimit' : 1,  
	        //'buttonText': "上传",  
	        //'displayData': 'percentage',  
	        'onSelectOnce' :function(event,data){
	        	        	       	        	       		
			}, 
			'onSelect':function(event,queueID,fileObject){
				
			},
			'onError':function(event,queueId,fileObj,errorObj){
				
			},  
	        'onComplete': function (event, queueID, fileObj, response, data){
	        	uploadComplete(response, index);
	        },
			'onAllComplete'  : function(event,data){
				
			} 
		}); 
	}
	function getCookie(name){
		var strcookie=document.cookie;
		var arrcookie=strcookie.split("; ");
		for(var i=0;i<arrcookie.length;i++){
			var arr=arrcookie[i].split("=");
			if(arr[0]==name)
			return unescape(arr[1]);
		}
		return "";
	} 
