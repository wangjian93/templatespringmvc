var XGridFactory = {
	xtype : "XGrid",
	cache : {},
	size : 0,
	maxElementsInMemory : 100,
	memoryStoreEvictionPolicy : "FIFO",
	make : function(settings) {
		var id = settings.id || this.xtype + "_" + this.size;;
		if (this.cache[id])
			return this.cache[id];
		else {
			var grid = new XGrid(id, settings);
			this.cache[id] = grid;
			this.size++;
			return grid;
		}
	},
	get : function(id) {
		return this.cache[id];
	}
};
function XGrid(id, settings) {
	this.id = id;
	this.width = "100%";
	this.height = "100%";
	this.columns = "[]";
	this.reader = {
		totalProperty : "total",
		root : "items"
	};
	if (settings) {
		$.extend(this, settings);
	}
}
XGrid.prototype.render = function() {
	var x= this;
	var html = document.getElementById(x.id);
	if(!html) {
		html = x.compile();
		document.body.appendChild(html);
	}
	x.init();
};
XGrid.prototype.compile = function() {
	var x = this;
	var grid = document.getElementById(x.id);
	if (!grid) {
		grid = XQuery.makeElement("div", "grid_panel", {
			"id" : x.id
		});
		grid.style.width = x.width;
		grid.style.height = x.height;
		
		var toolbar = XQuery.makeElement("div", "toolbar");
		if(typeof(x.toolbar) != "undefined") {
			toolbar.style.height = "23px";
			for(var i in x.toolbar) {
				var tool = x.toolbar[i];
				var button = XQuery.makeElement("a", "button", {
					"href" : "javascript:void(0);",
					"innerHTML" : tool.text,
					"hideFocus" : "true",
					"onclick" : tool.handler
				});
				toolbar.appendChild(button);
			}
		}
		grid.appendChild(toolbar);
		
		var thead = XQuery.makeElement("div", "head", {
			"id" : x.id + "_head"
		});
		thead.style.height = "25px";
		var ol = XQuery.makeElement("ol");
		ol.style.listStyleType = "none";
		
		for ( var i in x.columns) {
			var column = x.columns[i];
			var li = XQuery.makeElement("li", "column");
			var width = x.columnWidth(column);
			li.style.width = width;
			var text = XQuery.makeElement("span", "column_text", {
				"innerHTML" : column.text
			});
			li.appendChild(text);
			ol.appendChild(li);
		}
		thead.appendChild(ol);
		
		var tbody = XQuery.makeElement("div", "body", {
			"id" : x.id + "_body"
		});
		var tfoot = XQuery.makeElement("div", "foot", {
			"id" : x.id + "_foot"
		});

		$(tbody).height($(grid).height() - $(thead).height() - $(tfoot).height() - $(toolbar).height());
		tbody.style.overflow = "auto";
		
		grid.appendChild(thead);
		grid.appendChild(tbody);
		grid.appendChild(tfoot);
	}
	return grid;
};
XGrid.prototype.init = function() {
	var x= this;
	x.reload();
};
XGrid.prototype.destory = function() {
	var x = this;
	var grid = document.getElementById(x.id);
	if (!grid) {
		document.body.removeChild(grid);		
	}
};
XGrid.prototype.findCMByKey = function(key) {
	var x = this;
	for ( var i in x.columns) {
		if (this.columns[i].dataIndex == key)
			return this.columns[i];
	}
};
XGrid.prototype.createCell = function(rowTemplate, rowNumber, column, record) {
	var value = "";
	var dataIndex = column.dataIndex;
	if (dataIndex != "rowId")
		value = record[dataIndex];
	var dispalyValue = value;
	if (column.renderer) {
		dispalyValue = column.renderer(rowNumber, value, record);
	}
	var pattern = '@' + dataIndex + '@';
	return rowTemplate.replace(pattern, dispalyValue);
};
XGrid.prototype.reload = function() {
	var x = this;
	if(x.url)
		$.post(x.url, function(data) {
			if (data.error) {
			} else {
				x.load(data);
			}
		}, "json");
};
XGrid.prototype.load = function(data, add) {
	var x = this;
	var tbody = document.getElementById(x.id + "_body");
	if (tbody) {
		if (typeof (data) == "undefined") {
			x.reload();
		} else {
			var body = x.bodyCompile(data);
			if(add)
				tbody.innerHTML += body;
			else
				tbody.innerHTML = body;
		}
	}
};
XGrid.prototype.bodyCompile = function(data) {
	var x = this;
	var body = "";
	if (typeof (data) != "undefiend") {
		var rowTemplate = x.rowTemplate();
		var records = data[x.reader.root];
		for ( var i in records) {
			var record = records[i];
			var row = rowTemplate;
			row = x.createCell(row, i, "rowId", record);
			for ( var column in x.columns) {
				row = x.createCell(row, i, x.columns[column], record);
			}
			body += row;
		}
	}
	return body;
};
XGrid.prototype.rowTemplate = function() {
	var x = this;
	var rowTemplate = "";
	if (typeof (x.columns) != "undefiend") {
		for ( var i in x.columns) {
			var column = x.columns[i];
			var width = x.columnWidth(column);
			var cell = "<li class=\"cell\" style=\"width:" + width + "\">" + '@' + column.dataIndex
					+ '@' + "</li>";
			rowTemplate += cell;
		}
		rowTemplate = "<ol class=\"row\" style=\"list-style-type:none;\">" + rowTemplate + "</ol>";
	}
	return rowTemplate;
};
XGrid.prototype.columnWidth = function(column) {
	var x = this;
	var width = 0;
	if (typeof (column) != "undefiend") {
		width = column.width;
	}
	if(typeof(width) == "undefined") {
		if(x.columns != "undefiend")
			width = (1 / x.columns.length) * 100 + "%";
		else
			width = 0;
	}
	return width;
};
XGrid.prototype.clearData = function() {
	var x= this;
	var tbody = document.getElementById(x.id + "_body");
	if(tbody) {
		tbody.innerHTML = "";
	}
};