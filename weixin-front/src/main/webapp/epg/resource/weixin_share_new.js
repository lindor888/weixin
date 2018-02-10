function wxfn(option){
	dataForWeixin={appId:option.appId
			,timestamp:option.timestamp
			,nonceStr:option.nonceStr
			,signature:option.signature
			,img:option.img
			,url:option.url
			,title:option.title
			,desc:option.desc
			,fakeid:""}
    ,WeixinApi=(function(){
        
		setTimeout(function(){
			var vs = document.querySelectorAll(".edui-faked-video");
			var fs = window.frames;
			for(var i=0;i<vs.length;i++){
				var fra = window.frames[i];
				var ifs = fra.document.body.clientHeight + 15;
				vs[i].style.height = ifs+"px";
			}
		},1000);

		wx.config({
			debug: false,
			appId: dataForWeixin.appId,
			timestamp: dataForWeixin.timestamp,
			nonceStr: dataForWeixin.nonceStr,
			signature: dataForWeixin.signature,
			jsApiList: [
				'checkJsApi',
				'onMenuShareTimeline',
				'onMenuShareAppMessage',
				'onMenuShareQQ',
				'onMenuShareWeibo',
				'hideMenuItems',
				'showMenuItems',
				'hideAllNonBaseMenuItem',
				'showAllNonBaseMenuItem',
				'previewImage',
				'downloadImage',
				'hideOptionMenu',
				'showOptionMenu',
				'closeWindow',
				'scanQRCode'
			]
		});

		wx.ready(function(){

			wx.checkJsApi({
				jsApiList: [ 'checkJsApi' ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
				success: function(res) {
					// 以键值对的形式返回，可用的api值true，不可用为false
					// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
				}
			});
			wx.onMenuShareTimeline({
				title: dataForWeixin.title,
				link: dataForWeixin.url,
				imgUrl: dataForWeixin.img,
				success: function () {
					//用户确认分享后执行的回调函数
				},
				cancel: function () {
					//用户取消分享后执行的回调函数
				}
			});

			wx.onMenuShareAppMessage({
				title: dataForWeixin.title,
				desc: dataForWeixin.desc,
				link: dataForWeixin.url,
				imgUrl: dataForWeixin.img,
				type: '', // 分享类型,music、video或link，不填默认为link
				dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				success: function () {
					// 用户确认分享后执行的回调函数
				},
				cancel: function () {
					// 用户取消分享后执行的回调函数
				}
			});

			wx.onMenuShareQQ({
				title: dataForWeixin.title,
				desc: dataForWeixin.desc,
				link: dataForWeixin.url,
				imgUrl: dataForWeixin.img,
				success: function () {
					// 用户确认分享后执行的回调函数
				},
				cancel: function () {
					// 用户取消分享后执行的回调函数
				}
			});

			wx.onMenuShareWeibo({
				title: dataForWeixin.title,
				desc: dataForWeixin.desc,
				link: dataForWeixin.url,
				imgUrl: dataForWeixin.img,
				success: function () {
					// 用户确认分享后执行的回调函数
				},
				cancel: function () {
					// 用户取消分享后执行的回调函数
				}
			});
		});
		



    })();
} 