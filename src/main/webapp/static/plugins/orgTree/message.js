var MessageBoxFactory = {
	boxes : {},
	size : 0,
	maxElementsInMemory : 100,
	memoryStoreEvictionPolicy : "FIFO",
	makeMessageBox : function(id, title, ok) {
		if (this.boxes[id])
			return this.boxes[id];
		else {
			var box = new MessageBox(id, title, ok);
			this.boxes[id] = box;
			this.size++;
			return box;
		}
	},
	getMessageBox : function(id) {
		return this.boxes[id];
	}
};
function MessageBox(id, title, ok) {
	this.id = id;
	this.title = title ? title : "无标题窗口";
	this.ok = ok ? ok : function() {
		return "请定义事件！";
	};
};
MessageBox.prototype.hide = function() {
	this.pop.hide();
};
MessageBox.prototype.show = function() {
	var message = document.getElementById(this.id);
	if (!message) {
		var div = XQuery.makeElement("div", "message", {
			"id" : this.id
		});
		var textarea = XQuery.makeElement("textarea", null, {
			"id" : this.id + "_message"
		});
		div.appendChild(textarea);
		div.style.width = "500px";
		div.style.height = "200px";
		div.style.display = "none";
		document.body.appendChild(div);
	}
	if (!this.pop) {
		this.pop = new Pop(this.id, this.title, {
			"basicOperate" : true,
			"ok" : this.ok
		});
	}
	this.pop.show();
};
MessageBox.prototype.getMessage = function() {
	var message = document.getElementById(this.id + "_message");
	if (message)
		return message.value;
};