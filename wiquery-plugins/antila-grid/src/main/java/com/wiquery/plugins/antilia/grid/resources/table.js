Antilia.isIE8 = function() {
	var index = navigator.userAgent.indexOf("MSIE");
	var version = parseFloat(navigator.userAgent.substring(index + 5));
	return Wicket.Browser.isIE() && version >= 8;
}

function Table(id, url, rows, ncols, rendringCount, ie6, dragColumns, loadingMsg) {
	this.id = id;
	// rows is an array of Rows	
	this.rows = rows;
	this.url = url;
	this.ncols = ncols;	
	this.rendringCount = rendringCount;
	this.ie6 = ie6;
	this.loadingMsg = loadingMsg;
	this.dragColumns = dragColumns;
	var tId = "#" + id + "> div";
	var tBody = "#" + id + "_tBody";
	var body = $(tBody);
	var table = $(tId);
	this.resId = id + "_res";
	this.loadingId = id + "_loading";
	var tB = document.getElementById(id+'_tTB');	
	//var div = $(tBody+" > div ");
    var height = body.height() - 2;
    
    //var table = $(tBody+" > div.dTHeader > table");
    
    if(Wicket.Browser.isIE7()) {    
    	if(!Antilia.isIE8()) {
    		height = body.height()+ 1;
    	}
    } 
          
    /*
    if(Wicket.Browser.isIE7()) {    
    	if(Antilia.isIE8())
    		table.css("width", "100%");
    	else {
    		table.css("width", "auto");
    		body.css("overflow","visible"); 
    		body.css("overflow-x","auto"); 
    		body.css("overflow-y","hidden");
    	}
    } 
    */  
    if(tB) {
	    if(Wicket.Browser.isIE7()) {    
	    	if(!Antilia.isIE8()) {    
	    		tB.firstChild.style.height = (height - 21) + "px";
	    	}
	    } else {
	    	tB.style.height = (height - 17) + "px";
	    }
    }
    var resizer = "<div id='"+this.resId+"' class='collResizer ui-widget ui-widget-content' style='position: absolute; width: 0px; height:"+ height + "px; top: 0px; left: 0px; display: none;'></div>";    
    body.append(resizer);
    var tHei = table.height();
    var tWid = table.width();
    var lTop = (table.height()/2)-20;
    var lLeft = (body.width()/2)-60;
    var loading = "<div id='"+this.loadingId +"' class='ui-widget ui-corner-all' "
    +"style='position: absolute; display: none; top: 0px; left: 0px; height: "
    +tHei+"px; width: " +tWid+"px;'>" 
    +"<div style='top: "
    +lTop+"px; left: " +lLeft+"px;' class='loadingTable ui-widget ui-widget-content ui-state-default ui-corner-all'>"
    +this.loadingMsg+"</div></div>";
    table.append(loading);
    
	this.columns = new Array();    
    for(var i = 0; i < this.ncols; i++) {               
    	if(i == 0)
    		this.addColumn(i, this.url);
    	else 
    		this.addColumn(i, this.url);
    }       
}

Table.prototype.addColumn = function(col, url) {
	var column = new Column(this.id, col, url);	
	this.columns[this.columns.length]= column;
}

Table.prototype.toggleSelected = function(row) {	
	var rowObj = this.rows[row];
	if(rowObj)
		rowObj.toggleSelection();
}

Table.prototype.highlight = function(row) {	
	var rowObj = this.rows[row];
	if(rowObj)
		rowObj.highlight();
}

Table.prototype.unhighlight = function(row) {	
	var rowObj = this.rows[row];
	if(rowObj)
		rowObj.unhighlight();
}

function Column(tableId, number, url) {
	this.tableId = tableId;
	this.url = url;
	this.number = number;
	this.resizeHandleId = this.tableId + '_c_' + this.number;
	this.resizeHandle = document.getElementById(this.resizeHandleId);	
	if(this.resizeHandle) {
		 var resizer = tableId + "_res";
		 this.resizeHandle.resizer = document.getElementById(resizer);
		 this.resizeHandle.tableId = tableId;
		 this.resizeHandle.url = url;
	     this.resizeHandle.number = number;
		Antilia.Drag.init(this.resizeHandle, function(obj) {
			var td = obj.parentNode.parentNode.parentNode.parentNode;
			var resdiv = td.parentNode.parentNode.parentNode.parentNode;
			obj.resizer.style.left = parseInt(td.offsetLeft) + parseInt(resdiv.style.left) + parseInt(td.offsetWidth) + "px";
			obj.resizer.style.display = "block";
		} , this.onEndDrag, this.onResize);
	}		
}


Column.prototype.onResize = function (obj, deltaX, deltaY) {
	var td = obj.parentNode.parentNode.parentNode.parentNode;	
	var resdiv = td.parentNode.parentNode.parentNode.parentNode;
	var dleft = parseInt(resdiv.style.left);
	if(dleft < 0)
		dleft = -dleft;
	if ((parseInt(obj.resizer.style.left)+dleft-parseInt(td.offsetLeft)+deltaX)>40) {
    	obj.resizer.style.left = parseInt(obj.resizer.style.left) + deltaX+"px";       	
		if(dleft > 0)
			td.getElementsByTagName("input")[0].value= parseInt(obj.resizer.style.left)+dleft-parseInt(td.offsetLeft)+deltaX;
		else
			td.getElementsByTagName("input")[0].value= parseInt(obj.resizer.style.left)-parseInt(td.offsetLeft)+deltaX;
    }
    else { 
		td.getElementsByTagName("input")[0].value="40";
    }
  	var res = [0, 0];
	return res;
}	
 
Column.prototype.onEndDrag = function (obj) {
    var td = obj.parentNode.parentNode.parentNode.parentNode;  
    var id = td.id.replace("tdh", "tdb");
    var btd = document.getElementById(id);
    var value = parseInt(td.getElementsByTagName("input")[0].value);
    td.style.width =  value+"px";
    if(btd != null)
    	btd.style.width = value + "px";
    var  tH = document.getElementById(obj.tableId+'_tTH');
    var  tB = document.getElementById(obj.tableId+'_tTB');
    tH.style.left=(-tB.scrollLeft)+'px';
    var url = this.url+ '&sourceId=' + parseInt(td.style.width) + '&targetId=resize' + '&number=' + this.number
    obj.resizer.style.display = "none";
    wicketAjaxGet(url);      
}
 
function Row(tableId, number, selected) {
	this.tableId = tableId;
	this.number = number;
	this.selected = selected;
	this.rowId = this.tableId + '_r_' + this.number;
	this.row = document.getElementById(this.rowId);		
}

Row.prototype.toggleSelection = function () {
	if(this.selected==false) {
		this.selected = true;	
	} else {
		this.selected = false;
	}		
}

Row.prototype.highlight = function () {	
	if(this.row) {
		this.row.className = 'highlightedRow';
	}	
}

Row.prototype.unhighlight = function () {
	var styleClass = 'tbodyrow' + (this.number%2);
	if(this.row) {
		this.row.className = styleClass;
	}	
}