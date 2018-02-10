var relatedDataList = new Array();
function relatedData(Id,relatedObjectsId,relatedTitle,relatedUrl,relatedDate,relatedLogo,relatedType,relatedOrder){
		this.Id=Id== null ? "": Id;
		this.relatedObjectsId=relatedObjectsId== null ? "": relatedObjectsId;
		this.relatedTitle=relatedTitle== null ? "":relatedTitle ;
		this.relatedUrl=relatedUrl== null ? "": relatedUrl;
		this.relatedDate=relatedDate== null ? "": relatedDate;
		this.relatedLogo=relatedLogo == null ? "": relatedLogo;
		this.relatedOrder=relatedOrder == null ? "": relatedOrder;
		this.relatedType=relatedType == null ? "": relatedType;
}

function insertRelatedData(relatedObjectsId,relatedTitle,relatedUrl,relatedDate,relatedLogo,relatedType){
	var listnum=relatedDataList.length;
	//var limitNum=10;
/*	if(RelatedDataType!='tabArticleRelatedList'){
		limitNum=6;
	}*/
	for(var k=0;k<relatedDataList.length;k++){
		if((relatedDataList[k].relatedObjectsId==relatedObjectsId)&&(relatedDataList[k].relatedTitle==relatedTitle)){
			return false;//有重复，退出，不添加。
		}
	}
	//if(listnum<limitNum){
		relatedDataList[listnum]=new relatedData(listnum,relatedObjectsId,relatedTitle,relatedUrl,relatedDate,relatedLogo,relatedType,listnum);
	//}
}

function relatedDataToDiv(){//相关插入到指定id,Div
	var display="";
	var htmldata="";
	var RelatedQueryWord=$('#RelatedQueryWord').val();
		htmldata=htmldata+"<p class='mt5'>";
		//htmldata=htmldata+"<input type='button' name='button8'  value='添加' onclick='addRelated()' />	";
		htmldata=htmldata+"</p>";
		for(var j=0;j<relatedDataList.length;j++){
			htmldata=htmldata+"<p class='mt5'>";
			htmldata=htmldata+"标题&nbsp;:<input type='text'  name='" + "ARTI" + "["+j+"].relatedTitle'  value='"+relatedDataList[j].relatedTitle+"' size='"+20+"' onchange='relatedTitleChange("+j+",this)' />&nbsp;";
			htmldata=htmldata+"描述&nbsp;:<input type='text'  name='" + "ARTI" + "  size='"+20+"' onchange='relatedUrlChange("+j+",this)' />";
			htmldata=htmldata+"图片地址&nbsp;:<input type='text'  name='" + "ARTI" + "["+j+"].relatedLogo' value='"+relatedDataList[j].relatedLogo+"' size='"+20+"' onchange='relatedTitleChange("+j+",this)' />&nbsp;";
			htmldata=htmldata+"URL地址&nbsp;:<input type='text'  name='" + "ARTI" + "["+j+"].relatedUrl'  value='"+relatedDataList[j].relatedUrl+"' size='"+20+"' onchange='relatedTitleChange("+j+",this)' />&nbsp;";
			htmldata=htmldata+"排序&nbsp;:<input type='text'  name='" + "ARTI" + "["+j+"].relatedDate' "+" value='"+relatedDataList[j].relatedOrder+"' size='"+20+"' onchange='relatedTitleChange("+j+",this)' />&nbsp;";
			htmldata=htmldata+"<input type='hidden'  name='" + "ARTI" + "["+j+"].relatedDate'  value='"+relatedDataList[j].relatedDate+"' />";
			htmldata=htmldata+"<input type='hidden'  name='" + "ARTI" + "["+j+"].relatedLogo'  value='"+relatedDataList[j].relatedLogo+"' />";
			htmldata=htmldata+"<input type='hidden'  name='" + "ARTI" + "["+j+"].relatedOrder'  value='"+relatedDataList[j].relatedOrder+"' />";
			htmldata=htmldata+"<input type='hidden'  name='" + "ARTI" + "["+j+"].relatedType'  value='"+relatedDataList[j].relatedType+"' />";
			htmldata=htmldata+"	<a class='blue' onclick=\"upDownRelatedDate(-1,'"+j+"')\">上移</a> ";
			htmldata=htmldata+"<a class='blue' onclick=\"upDownRelatedDate(1,'"+j+"')\">下移</a> ";
			htmldata=htmldata+"<a class='blue' onclick=\"deleteRelatedData('"+j+"')\" >删除</a>";
			htmldata=htmldata+"</p>";
		}
	$("#tiaoxuan").html(htmldata);
}
function deleteRelatedData(Id){//删除相关数组元素
	var arrayTemp=new Array();
	var j=0;
	var s=0;
	for(var i=0;i<relatedDataList.length;i++){
		if(relatedDataList[i].Id!=Id){
			arrayTemp[j]=new relatedData(j,relatedDataList[i].relatedObjectsId,relatedDataList[i].relatedTitle,relatedDataList[i].relatedUrl,relatedDataList[i].relatedDate,relatedDataList[i].relatedLogo,relatedDataList[i].relatedType,s++);
			j++;
		}
	}
	relatedDataList=arrayTemp;
	relatedDataToDiv();
}
function upDownRelatedDate(updown,Id){
	var index=new Number(updown)+new Number(Id);// alert(flg);
	if(index<0||index>=relatedDataList.length){
		return false;
	}else{
		var flg=new Number(Id); updown=new Number(updown);
		var arrayTemp=new Array();
		var rdf=relatedDataList[flg];
		var rdfupd=relatedDataList[flg+updown];
		for(var i=0;i<relatedDataList.length;i++){
			if(i==flg){
				arrayTemp[i]=new relatedData(i,rdfupd.relatedObjectsId,rdfupd.relatedTitle,rdfupd.relatedUrl,rdfupd.relatedDate,rdfupd.relatedLogo,rdfupd.relatedType,i);
			}else if(i==(flg+updown)){
				arrayTemp[i]=new relatedData(i,rdf.relatedObjectsId,rdf.relatedTitle,rdf.relatedUrl,rdf.relatedDate,rdf.relatedLogo,rdfupd.relatedType,i);
			}else{
				arrayTemp[i]=new relatedData(i,relatedDataList[i].relatedObjectsId,relatedDataList[i].relatedTitle,relatedDataList[i].relatedUrl,relatedDataList[i].relatedDate,relatedDataList[i].relatedLogo,rdfupd.relatedType,i);
			}
		}
		relatedDataList=arrayTemp;	
	}
	relatedDataToDiv();
}