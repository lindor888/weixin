$(function() {
	$.get('/index.php?action=blackwords-AjaxGetList', function(data) {
		results = eval('(' + data + ')');
		$("span[name='black']").each(function(){
			for (var key in results){
				keyword = results[key].words;
				var text = $(this).html();
				reg=eval("/"+keyword+"/g");
				var rst=text.replace(reg,'<font color="#FF0000">'+keyword+'</font>');
				$(this).html(rst);
			}
		});
	});
});