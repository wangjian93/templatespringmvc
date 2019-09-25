var XWindowFactory = {
	xtype : "XWindow",
	cache : {},
	size : 0,
	maxElementsInMemory : 100,
	memoryStoreEvictionPolicy : "FIFO",
	make : function(settings) {
		var id = settings.id || this.xtype + "_" + this.size;;
		if (this.cache[id])
			return this.cache[id];
		else {
			var o = new XWindow(id, settings);
			this.cache[id] = o;
			this.size++;
			return o;
		}
	},
	get : function(id) {
		return this.cache[id];
	}
};
function XWindow(id, settings) {
	this.id = id;
	this.children = [];
	this.title = "无标题窗口";
	this.width = "100%";
	this.height = "100%";
	if (settings) {
		$.extend(this, settings);
	}
}
XWindow.prototype.render = function() {
	var x= this;
	var html = document.getElementById(x.id);
	if(!html) {
		html = x.compile();
		document.body.appendChild(html);
	}
	x.init();
	if (!x.pop) {
		var basicOperate = false;
		var ok = function(){};
		if(x.buttons) {
			basicOperate = true;
			ok = x.buttons.ok;
		}
		x.pop = new Pop(x.id, x.title, {
			"basicOperate" : basicOperate,
			"ok" : ok
		});
	}
	x.pop.show();
};
XWindow.prototype.compile = function() {
	var x = this;
	var w = document.getElementById(x.id);
	if (!w) {
		w = XQuery.makeElement("div", null, {
			"id" : x.id
		});
		w.style.width = x.width;
		w.style.height = x.height;
		if (x.panel) {
			if(x.panel.layout == "column" || x.panel.layout == "fit")
				x.panel.height = w.style.height;
			if(x.panel.layout == "form" || x.panel.layout == "fit")
				x.panel.width = w.style.width;
			var xtype = "XPanel";
			var factory = XQuery.XFactory.get(xtype);
			var child = factory.make(x.panel);
			var html = child.compile();
			w.appendChild(html);
			x.children.push(child);
			this.panel = child;
		}
	}
	return w;
};
XWindow.prototype.init = function() {
	var x= this;
	for(var i in x.children) {
		var child = x.children[i];
		if(typeof(child.init) == "function") {
			child.init();
		}
	}
};
XWindow.prototype.destory = function() {
	var x = this;
	var w = document.getElementById(x.id);
	if (!w) {
		document.body.removeChild(w);		
	}
};