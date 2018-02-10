
function pushgraphic(){
	var param={};	
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
	
	
	
		param={
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
				url:'/weixin-studio/graphicPush/graphicpushAction?random='+Math.round(Math.random()*10000),
		        type: 'POST',   
		        async: false,
		        data:  param,
		       dataType: 'json',  
		       success:function(data){ 
		    	   if(data == null){
		               $.messager.alert("提示","发送失败！");
		           }else if(data.message=="success"){		    		   
		    		   $.messager.alert("提示","发送成功！");	    		  
		    	   }else{
		    	       $.messager.alert("提示","发送失败！");
		    	   }
		       }
		    });
			
		
	
	
}
