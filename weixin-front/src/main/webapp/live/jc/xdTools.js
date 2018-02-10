//创建xmlObj对象-----------------------------------
function xmlObj(){
	try{
		return (new ActiveXObject("Msxml2.XMLHTTP")||new ActiveXObject('Microsoft.XMLHTTP'))
		}catch(e){
		return new XMLHttpRequest()
	}	
}
// setAjax
function setAjax(a,b,c,d,e){
		return new function(){
			var t=this;
			t.method=a||"get";      
			t.action=b;      
			t.async=(c!=undefined?c:true);  
			t.cache=d||0;        
			t.callBack=(typeof(e)=="function"?e:0)         
			t.data="";	
			t.Data="";  
			t.Open=t.Send=t.Test=t.Over=Function;
			t.Err=function(){xmlhttp=null}
			t.xmlObj=null;	
			t.send=function(xmlCache){
			var xmlhttp=t.xmlObj||xmlObj();
			  xmlhttp.open(t.method,t.action,t.async);
			/*
			创建或设置对象 ，尤其注意如果 xmlhttp对象在初始建立，在页面加载同时发送请求，xmlhttp.readyState会返回0，这个问题容易引起错误
			所以初始化些对象时一定是在第一次调用时在同一区域创建			
			*/
			
			  if(t.method.toUpperCase()=="POST"){
				  if(t.cache)xmlhttp.setRequestHeader("Cache-Control","no-cache")
				  xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=UTF-8; text/html");
				}else{
				if(t.cache){
					var c="cache="+Math.random()
						c=(t.action.indexOf("?")!=-1?"&":"?")+c
						t.action+=c
					}
					t.data=null
				}
				//xmlhttp.setRequestHeader("Content-type","charset=UTF-8; text/xml");
				  xmlhttp.onreadystatechange=function(){
					  switch(xmlhttp.readyState){					
						case 2:t.Send();break;						
						case 4:
						  if(xmlhttp.status===200||xmlhttp.status===304){
							  if(t.callBack)t.callBack.call(t,xmlhttp.responseText) ;//responseXML responseText
							  t.xmlObj=(xmlCache?xmlhttp:null);
								xmlhttp=null	 
							 }else{
							  if(typeof(t.Err)=="function")t.Err.call(t)
								}
						break
						default:
							if(xmlhttp.status===404){
								t.error&&t.error();
								xmlhttp.abort()
							}
						break 
						}

					  }
				  xmlhttp.onerror=function(){
					  console.log(arguments)
					  }	  
				  xmlhttp.send(t.data);
				  t.stop=function(){
					xmlhttp.abort()
					}
				};
	
			}	
		}	
		
//function getSearch(name,str){
//	var s=str||location.search
//		,r=new RegExp(name+"=([^#&]+)","i"),result=s.match(r)||[];
//	return result[1]
//	}

function getSearch(str){
	var s=decodeURIComponent(str||location.search.substr(1)),arr=(s!==""&&s.split("&"))||[],arrL=arr.length,i=0,key=null,v=null,a=null,o={};
		if(arrL)for(;i<arrL;i++){
				a=arr[i].split("=")
				o[a[0]]=a[1]	
			}else if(s!==""){
				a=s.split("=")
				o[a[0]]=a[1]
				};
		return o			
	};
	
function viewProfile() {
typeof WeixinJSBridge != "undefined" && WeixinJSBridge.invoke && WeixinJSBridge.invoke("profile",{username:'心动',scene:"57"});
}	
// 绑定监听
if(document.addEventListener){
	addEvent=function(_,Eve,Fun,b){_.addEventListener(Eve,Fun,b||false)};
	delEvent=function(_,Eve,Fun,b){_.removeEventListener(Eve,Fun||null,b||false)};
}else{
	addEvent=function(_,Eve,Fun){_.attachEvent("on"+Eve,Fun)};
	delEvent=function(_,Eve,Fun){_.detachEvent('on'+Eve,Fun||null)};	
}	
function noPop(e){
	e.preventDefault();
	e.stopPropagation();
	e.returnValue=false;
	e.cancelBubble=true
}
// 微信相关方法
function wxfn(option){
	 var dataForWeixin={appId:option.appId
			,img:option.img
			,url:option.url
			,title:option.title
			,desc:option.desc
			,fakeid:""}
	,WeixinApi=(function(){
		"use strict";
		function weixinShareTimeline(data, callbacks) {
			callbacks = callbacks || {};
			var shareTimeline = function(theData) {
				WeixinJSBridge.invoke('shareTimeline', {
							"appid": theData.appId ? theData.appId: '',
							"img_url": theData.img,
							"link": theData.url,
							"desc": theData.title,
							"title": theData.desc,
							"img_width": "120",
							"img_height": "120"
						},
						function(resp) {
							switch (resp.err_msg) {
								case 'share_timeline:cancel':
									callbacks.cancel && callbacks.cancel(resp);
									break;
								case 'share_timeline:fail':
									callbacks.fail && callbacks.fail(resp);
									break;
								case 'share_timeline:confirm':
								case 'share_timeline:ok':
									callbacks.confirm && callbacks.confirm(resp);
									break
							}
							callbacks.all && callbacks.all(resp)
						})
			};
			WeixinJSBridge.on('menu:share:timeline',
					function(argv) {
						if (callbacks.async && callbacks.ready) {
							window["_wx_loadedCb_"] = callbacks.dataLoaded || new Function();
							if (window["_wx_loadedCb_"].toString().indexOf("_wx_loadedCb_") > 0) {
								window["_wx_loadedCb_"] = new Function()
							}
							callbacks.dataLoaded = function(newData) {
								window["_wx_loadedCb_"](newData);
								shareTimeline(newData)
							};
							callbacks.ready && callbacks.ready(argv)
						} else {
							callbacks.ready && callbacks.ready(argv);
							shareTimeline(data)
						}
					})
		}
		function weixinSendAppMessage(data, callbacks) {
			callbacks = callbacks || {};
			var sendAppMessage = function(theData) {
				WeixinJSBridge.invoke('sendAppMessage', {
							"appid": theData.appId ? theData.appId: '',
							"img_url": theData.img,
							"link": theData.url,
							"desc": theData.desc,
							"title": theData.title,
							"img_width": "120",
							"img_height": "120"
						},
						function(resp) {
							switch (resp.err_msg) {
								case 'send_app_msg:cancel':
									callbacks.cancel && callbacks.cancel(resp);
									break;
								case 'send_app_msg:fail':
									callbacks.fail && callbacks.fail(resp);
									break;
								case 'send_app_msg:confirm':
								case 'send_app_msg:ok':
									callbacks.confirm && callbacks.confirm(resp);
									break
							}
							callbacks.all && callbacks.all(resp)
						})
			};
			WeixinJSBridge.on('menu:share:appmessage',
					function(argv) {
						if (callbacks.async && callbacks.ready) {
							window["_wx_loadedCb_"] = callbacks.dataLoaded || new Function();
							if (window["_wx_loadedCb_"].toString().indexOf("_wx_loadedCb_") > 0) {
								window["_wx_loadedCb_"] = new Function()
							}
							callbacks.dataLoaded = function(newData) {
								window["_wx_loadedCb_"](newData);
								sendAppMessage(newData)
							};

							callbacks.ready && callbacks.ready(argv)
						} else {
							callbacks.ready && callbacks.ready(argv);
							sendAppMessage(data)
						}
					})
		}
		function weixinShareWeibo(data, callbacks) {
			callbacks = callbacks || {};
			var shareWeibo = function(theData) {
				WeixinJSBridge.invoke('shareWeibo', {
							"content": theData.desc,
							"link": theData.url,
							"img_url": theData.img,
							"title": theData.title,
							"img_width": "120",
							"img_height": "120"
						},
						function(resp) {
							switch (resp.err_msg) {
								case 'share_weibo:cancel':
									callbacks.cancel && callbacks.cancel(resp);
									break;
								case 'share_weibo:fail':
									callbacks.fail && callbacks.fail(resp);
									break;
								case 'share_weibo:confirm':
								case 'share_weibo:ok':
									callbacks.confirm && callbacks.confirm(resp);
									break
							}
							callbacks.all && callbacks.all(resp)
						})
			};
			WeixinJSBridge.on('menu:share:weibo',
					function(argv) {
						if (callbacks.async && callbacks.ready) {
							window["_wx_loadedCb_"] = callbacks.dataLoaded || new Function();
							if (window["_wx_loadedCb_"].toString().indexOf("_wx_loadedCb_") > 0) {
								window["_wx_loadedCb_"] = new Function()
							}
							callbacks.dataLoaded = function(newData) {
								window["_wx_loadedCb_"](newData);
								shareWeibo(newData)
							};
							callbacks.ready && callbacks.ready(argv)
						} else {
							callbacks.ready && callbacks.ready(argv);
							shareWeibo(data)
						}
					})
		}
		function imagePreview(curSrc, srcList) {
			if (!curSrc || !srcList || srcList.length == 0) {
				return
			}
			WeixinJSBridge.invoke('imagePreview', {
				'current': curSrc,
				'urls': srcList
			})
		}
		function showOptionMenu() {
			WeixinJSBridge.call('showOptionMenu')
		}
		function hideOptionMenu() {
			WeixinJSBridge.call('hideOptionMenu')
		}
		function showToolbar() {
			WeixinJSBridge.call('showToolbar')
		}
		function hideToolbar() {
			WeixinJSBridge.call('hideToolbar')
		}
		function getNetworkType(callback) {
			if (callback && typeof callback == 'function') {
				WeixinJSBridge.invoke('getNetworkType', {},
						function(e) {
							callback(e.err_msg)
						})
			}
		}
		function closeWindow() {
			WeixinJSBridge.call("closeWindow")
		}
		function wxJsBridgeReady(readyCallback) {
			if (readyCallback && typeof readyCallback == 'function') {
				var Api = this;
				var wxReadyFunc = function() {
					readyCallback(Api)
				};
				if (typeof window.WeixinJSBridge == "undefined") {
					if (document.addEventListener) {
						document.addEventListener('WeixinJSBridgeReady', wxReadyFunc, false)
					} else if (document.attachEvent) {
						document.attachEvent('WeixinJSBridgeReady', wxReadyFunc);
						document.attachEvent('onWeixinJSBridgeReady', wxReadyFunc)
					}
				} else {
					wxReadyFunc()
				}
			}
		}
		return {
			version: "1.6",
			ready: wxJsBridgeReady,
			shareToTimeline: weixinShareTimeline,
			shareToWeibo: weixinShareWeibo,
			shareToFriend: weixinSendAppMessage,
			showOptionMenu: showOptionMenu,
			hideOptionMenu: hideOptionMenu,
			showToolbar: showToolbar,
			hideToolbar: hideToolbar,
			getNetworkType: getNetworkType,
			imagePreview: imagePreview,
			closeWindow: closeWindow
		}
	})();
	WeixinApi.ready(function(Api) {
		var wxCallbacks = {
			ready: function() {
				//alert("="+dataForWeixin.url)
			},
			cancel: function(resp) {
				//alert("分享被取消")
			},
			fail: function(resp) {
				//alert("分享失败")
			},
			confirm: function(resp) {
				//alert("分享成功")
			},
			all: function(resp) {
				//alert("分享结束")
			}
		};
		Api.shareToFriend(dataForWeixin, wxCallbacks);
		Api.shareToTimeline(dataForWeixin, wxCallbacks);
		Api.shareToWeibo(dataForWeixin, wxCallbacks)
	})
} 
window.requestAnimFrame=(function(){
return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function( callback ){window.setTimeout( callback, 1000 / 60 )}})();
