/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	config.language = 'zh-cn'; //�������Ļ���  
   /*
	config.font_names = '����;����_GB2312;������;����;����;��Բ;΢���ź�;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana'; //�༭��������  
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
  
     //��������·�� 
	 
    config.filebrowserBrowseUrl = 'js/ckfinder/ckfinder.html';  
    config.filebrowserImageBrowseUrl = 'js/ckfinder/ckfinder.html?Type=Images';  
    config.filebrowserFlashBrowseUrl = 'js/ckfinder/ckfinder.html?Type=Flash';  
    config.filebrowserUploadUrl = 'js/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files'; //�ϴ�һ���ĵ�
    config.filebrowserImageUploadUrl = 'js/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images';//�ϴ�ͼƬ
    config.filebrowserFlashUploadUrl = 'js/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Flash';//�ϴ�flash
	
	
	/*
	config.filebrowserImageUploadUrl = '/ckeditor_upload.php?type=img';
    config.filebrowserFlashUploadUrl = '/ckeditor_upload.php?type=flash';
	*/
    
};
