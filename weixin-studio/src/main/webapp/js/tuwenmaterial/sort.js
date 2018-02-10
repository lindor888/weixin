/**
 * 排序
 */
function orderNews() {
	var orderArr = new Array();
	for (var i = 1; i < 11; i++) {
		var order = $.trim($('#order' + i).val());
		if (isNaN(order)) {
			$.messager.alert("操作提示", "第" + i + "条新闻的排序号必须为数字！", "error");
			return;
		}
		
		if ($("#tiao" + i).css("display") == "none") {
			break;
		}

		var title = $('#title' + i).val();
		var desc = $('#desc' + i).val();
		desc = (desc == null) ? '' : desc;
		var imageUrl = $('#imageUrl' + i).val();
		var url = $('#url' + i).val();
		var pic = $('#pic' + i).attr("src");
		
		var iptObj = new inputObjec(title, desc, imageUrl, url, order, pic);
		orderArr.push(iptObj);
	}

	orderArr.sort(function(object1, object2) {
		var value1 = object1.order*1;
		var value2 = object2.order*1;
		return value1 < value2 ? -1 : 1;
	});

	initData(orderArr);

}

/**
 * news对象
 * @param _title
 * @param _describes
 * @param _imageUrl
 * @param _url
 * @param _order
 */
function inputObjec(_title, _desc, _imageUrl, _url, _order, _pic) {
	this.title = _title;
	this.desc = _desc;
	this.imageUrl = _imageUrl;
	this.url = _url;
	this.order = _order;
	this.pic = _pic;
}

/**
 * 排序后的展示
 * @param data
 */
function initData(data) {
	var size = data.length;
	for (var i = 0; i < size; i++) {
		$('#title' + (1+i)).val(data[i].title);
		$('#desc' + (1+i)).val(data[i].desc);
		$('#imageUrl' + (1+i)).val(data[i].imageUrl);
		$('#url' + (1+i)).val(data[i].url);
		$('#order' + (1+i)).val((i+1));
		$('#pic' + (1+i)).attr("src",data[i].pic);
	}
}

function checkOrder(that) {
	var idx = that.id.substring(5);
	if (isNaN(that.value)) {
		$.messager.alert("操作提示", "第"+idx+"条新闻的排序号必须为数字！", "error");
	} else if(idx != that.value) {
		orderNews();
	}
}