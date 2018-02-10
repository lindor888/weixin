/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	config.language = 'zh-cn'; //设置中文环境  
   /*
	config.font_names = '宋体;楷体_GB2312;新宋体;黑体;隶书;幼圆;微软雅黑;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana'; //编辑字体设置  
    config.toolbar =  
    [  
        ['Source', '-', 'Preview'], ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord'],  
        ['Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll', 'RemoveFormat'],  
        ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript'],  
        ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'], ['Link', 'Unlink', 'Anchor'],  
        ['Image', 'Flash', 'Table', 'HorizontalRule', 'SpecialChar', 'Styles',  
        'Format', 'Font', 'FontSize'], ['TextColor', 'BGColor'], ['Maximize', '-', 'About']  
    ]; 
    */ 
  
     //设置引用路径 
	 
    config.filebrowserBrowseUrl = 'js/ckfinder/ckfinder.html';  
    config.filebrowserImageBrowseUrl = 'js/ckfinder/ckfinder.html?Type=Images';  
    config.filebrowserFlashBrowseUrl = 'js/ckfinder/ckfinder.html?Type=Flash';  
    config.filebrowserUploadUrl = 'js/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files'; //上传一般文档
    config.filebrowserImageUploadUrl = 'js/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images';//上传图片
    config.filebrowserFlashUploadUrl = 'js/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Flash';//上传flash
	
	
	/*
	config.filebrowserImageUploadUrl = '/ckeditor_upload.php?type=img';
    config.filebrowserFlashUploadUrl = '/ckeditor_upload.php?type=flash';
	*/
    
};
