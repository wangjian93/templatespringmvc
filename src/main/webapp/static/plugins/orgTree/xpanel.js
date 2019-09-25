var XPanelFactory = {
	xtype : "XPanel",
	cache : {},
	size : 0,
	maxElementsInMemory : 100,
	memoryStoreEvictionPolicy : "FIFO",
	make : function(settings) {
		var id = settings.id || this.xtype + "_" + this.size;;
		if (this.cache[id])
			return this.cache[id];
		else {
			var panel = new XPanel(id, settings);
			this.cache[id] = panel;
			this.size++;
			return panel;
		}
	},
	get : function(id) {
		return this.cache[id];
	}
};
function XPanel(id, settings) {
	this.children = [];
	this.id = id;
	this.layout = "fit";
	this.width = "100%";
	this.height = "100%";
	this.items = [];
	if (settings) {
		$.extend(this, settings);
	}
}
XPanel.prototype.render = function() {
	var x= this;
	var html = document.getElementById(x.id);
	if(!html) {
		html = x.compile();
		document.body.appendChild(html);
	}
	x.init();
};
XPanel.prototype.compile = function() {
	var x = this;
	var panel = document.getElementById(x.id);
	if (!panel) {
		panel = XQuery.makeElement("div", null, {
			"id" : x.id
		});
		panel.style.width = x.width;
		panel.style.height = x.height;
		if(typeof(x.el) != "undefined") {
			var html = document.getElementById(x.el);
			if(html) {
				html.style.width = panel.style.width;
				html.style.height = panel.style.height;
				panel.appendChild(html);
			} else {
				console.warn("Element id =  " + x.el + " is not exsit");
				return;
			}
		}
		if(typeof(x.html) != "undefined") {
			panel.innerHTML = x.html;
		} else {
			var float = "none";
			if(x.layout == "column") {
				float = "left";
			} else if(x.layout == "form") {
			}
			for ( var i in x.items) {
				var item = x.items[i];
				if(x.layout == "column" || x.layout == "fit")
					item.height = panel.style.height;
				if(x.layout == "form" || x.layout == "fit")
					item.width = panel.style.width;
				var xtype = item.xtype;
				if(typeof(xtype) == "undefined")
					xtype = "XPanel";
				var factory = XQuery.XFactory.get(xtype);
				var child = factory.make(item);
				var html = child.compile();
				if(typeof(html.style.styleFloat) != "undefined")
					html.style.styleFloat = float;
				else if(typeof(html.style.cssFloat) != "undefined")
					html.style.cssFloat = float;
				panel.appendChild(html);
				x.children.push(child);
			}
		}
	}
	return panel;
};
XPanel.prototype.init = function() {
	var x= this;
	for(var i in x.children) {
		var child = x.children[i];
		if(typeof(child.init) == "function") {
			child.init();
		}
	}
};
XPanel.prototype.destory = function() {
	var x = this;
	var panel = document.getElementById(x.id);
	if (!panel) {
		document.body.removeChild(panel);		
	}
};
XPanel.prototype.getChildren = function(xtype, settings) {
	var x = this;
	if(typeof(xtype) == "undefined")
		return x.children;
	var result = [];
	for(var i in x.children) {
		var child = x.children[i];
		if(child.xtype == xtype) {
			var same = true;
			if(typeof(settings) != "undefined") {
				for(var i in settings) {
					if(child[i] != settings[i]) {
						same = false;
						break;
					}
				}
			}
			if(same)
				result.push(child);
		}
	}
	return result;
};