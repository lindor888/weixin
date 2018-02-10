function changenavbg(k)
{if(k==1)
	{getObjecta('li1').className='onsec';}
	else
	{getObjecta('li1').className='unsec1';}
	for(i=2;i<=5;i++)
	{if(i==k)
		{getObjecta('li'+i).className='onsec';}
		else
		{getObjecta('li'+i).className='unsec';}
	}
}
function showSubmenu(n)
{var navID = new Array ("subnav_0","subnav_1")
if (n>-1)
	{for (var i=0;i < navID.length;i++)
		{if (n==i)
			{getObjecta(navID[i]).style.display=""}
			else
			{getObjecta(navID[i]).style.display="none"}
		}
	}
 	else
	{for (var i=0;i < navID.length;i++)
		{getObjecta(navID[i]).style.display="none"}
	}
}


function changenavbga(k)
{if(k==1)
	{getObjecta('ali1').className='onseca';}
	else
	{getObjecta('ali1').className='unseca1';}
	for(i=2;i<=5;i++)
	{if(i==k)
		{getObjecta('ali'+i).className='onseca';}
		else
		{getObjecta('ali'+i).className='unseca';}
	}
}
function showSubmenua(n)
{var navID = new Array ("subnava_0","subnava_1")
if (n>-1)
	{for (var i=0;i < navID.length;i++)
		{if (n==i)
			{getObjecta(navID[i]).style.display=""}
			else
			{getObjecta(navID[i]).style.display="none"}
		}
	}
 	else
	{for (var i=0;i < navID.length;i++)
		{getObjecta(navID[i]).style.display="none"}
	}
}


function changenavbgb(k)
{if(k==1)
	{getObjecta('bli1').className='onsec';}
	else
	{getObjecta('bli1').className='unsec1';}
	for(i=2;i<=5;i++)
	{if(i==k)
		{getObjecta('bli'+i).className='onsec';}
		else
		{getObjecta('bli'+i).className='unsec';}
	}
}
function showSubmenub(n)
{var navID = new Array ("subnavb_0","subnavb_1","subnavb_2","subnavb_3")
if (n>-1)
	{for (var i=0;i < navID.length;i++)
		{if (n==i)
			{getObjecta(navID[i]).style.display=""}
			else
			{getObjecta(navID[i]).style.display="none"}
		}
	}
 	else
	{for (var i=0;i < navID.length;i++)
		{getObjecta(navID[i]).style.display="none"}
	}
}


function changenavbgc(k)
{if(k==1)
	{getObjecta('cli1').className='onsecc';}
	else
	{getObjecta('cli1').className='unsecc1';}
	for(i=2;i<=5;i++)
	{if(i==k)
		{getObjecta('cli'+i).className='onsecc';}
		else
		{getObjecta('cli'+i).className='unsecc';}
	}
}
function showSubmenuc(n)
{var navID = new Array ("subnavc_0","subnavc_1","subnavc_2")
if (n>-1)
	{for (var i=0;i < navID.length;i++)
		{if (n==i)
			{getObjecta(navID[i]).style.display=""}
			else
			{getObjecta(navID[i]).style.display="none"}
		}
	}
 	else
	{for (var i=0;i < navID.length;i++)
		{getObjecta(navID[i]).style.display="none"}
	}
}


function changenavbgd(k)
{if(k==1)
	{getObjecta('dli1').className='onsecc';}
	else
	{getObjecta('dli1').className='unsecc1';}
	for(i=2;i<=5;i++)
	{if(i==k)
		{getObjecta('dli'+i).className='onsecc';}
		else
		{getObjecta('dli'+i).className='unsecc';}
	}
}
function showSubmenud(n)
{var navID = new Array ("subnavd_0","subnavd_1","subnavd_2")
if (n>-1)
	{for (var i=0;i < navID.length;i++)
		{if (n==i)
			{getObjecta(navID[i]).style.display=""}
			else
			{getObjecta(navID[i]).style.display="none"}
		}
	}
 	else
	{for (var i=0;i < navID.length;i++)
		{getObjecta(navID[i]).style.display="none"}
	}
}

function changenavbge(k)
{if(k==1)
	{getObjecta('eli1').className='onsece';}
	else
	{getObjecta('eli1').className='unsece1';}
	for(i=2;i<=5;i++)
	{if(i==k)
		{getObjecta('eli'+i).className='onsece';}
		else
		{getObjecta('eli'+i).className='unsece';}
	}
}
function showSubmenue(n)
{var navID = new Array ("subnave_0","subnave_1")
if (n>-1)
	{for (var i=0;i < navID.length;i++)
		{if (n==i)
			{getObjecta(navID[i]).style.display=""}
			else
			{getObjecta(navID[i]).style.display="none"}
		}
	}
 	else
	{for (var i=0;i < navID.length;i++)
		{getObjecta(navID[i]).style.display="none"}
	}
}

function getObjecta(objectId)
{if(document.getElementById && document.getElementById(objectId))
	{// W3C DOM
	return document.getElementById(objectId);}
	else if (document.all && document.all(objectId))
	{// MSIE 4 DOM
	return document.all(objectId);}
	else if (document.layers && document.layers[objectId])
	{// NN 4 DOM.. note: this won't find nested layers
	return document.layers[objectId];}
	else
	{return false;}
}