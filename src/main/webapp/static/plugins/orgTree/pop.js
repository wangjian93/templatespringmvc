function Pop(id, title, settings) {
	this.id = id;
	this.title = title ? title : "无标题窗口";
	if (settings) {
		this.fixed = settings.fixed;
		this.basicOperate = settings.basicOperate;
		if (this.basicOperate) {
			this.ok = settings.ok ? settings.ok : function() {
				return "请定义事件！";
			};
		}
	} else {
		this.fixed = false;
		this.basicOperate = false;
	}
}
Pop.prototype.hide = function() {
	var outer = document.getElementById(this.id + "_pop");
	if (outer)
		outer.style.display = "none";
	var mask = document.getElementById(this.id + "_mask");
	if (mask)
		mask.style.display = "none";
};
Pop.prototype.show = function() {
	var pop = document.getElementById(this.id);
	if (pop) {
		var outer = document.getElementById(this.id + "_pop");
		var xOuter;
		if (outer) {
			xOuter = $("#" + this.id + "_pop");
		} else {
			var xc = this;
			outer = XQuery.makeElement("div", "pop", {
				"id" : this.id + "_pop"
			});
			outer.style.display = "none";
			document.body.appendChild(outer);
			xOuter = $("#" + this.id + "_pop");
			xOuter.width($("#" + this.id).width());
			var head = XQuery.makeElement("div", "pop_title", {
				"innerHTML" : this.title,
				"onselectstart" : "return false;"
			});
			var close = XQuery.makeElement("a", "pop_title_close", {
				"href" : "javascript:void(0);",
				"title" : "关闭",
				"hideFocus" : "true",
				"onclick" : function() {
					xc.hide();
				}
			});
			head.appendChild(close);
			outer.appendChild(head);
			if (!this.fixed) {
				head.onmousedown = function(event) {
					var x, y, left, top;
					if (window.event != null) {
						event = window.event;
					}
					if (event.button == 0 || event.button == 1) {
						outer.style.opacity = 0.6;
						outer.style.filter = "Alpha(opacity=60)";
						x = event.screenX;
						y = event.screenY;
						left = parseInt(outer.style.left);
						top = parseInt(outer.style.top);
						if (outer.setCapture) {
							outer.setCapture();
						} else if (window.addEventListener) {
							window.addEventListener("mousemove",
									document.onmousemove, false);
							window.addEventListener("mouseup",
									document.onmouseup, false);
						}
						document.onmousemove = function(evt) {
							if (window.event != null) {
								evt = window.event;
							}
							if (evt.button == 0 || evt.button == 1) {
								var currentLeft = left + evt.screenX - x;
								if (currentLeft <= 0) {
									outer.style.left = 0;
								} else {
									if (currentLeft >= (document.documentElement.clientWidth - xOuter
											.width())) {
										outer.style.left = (document.documentElement.clientWidth - xOuter
												.width())
												+ "px";
									} else {
										outer.style.left = currentLeft + "px";
									}
								}
								var currentTop = top + evt.screenY - y;
								if (currentTop <= 0) {
									outer.style.top = 0;
								} else {
									if (currentTop >= (document.documentElement.clientHeight - xOuter
											.height())) {
										outer.style.top = (document.documentElement.clientHeight - xOuter
												.height())
												+ "px";
									} else {
										outer.style.top = currentTop + "px";
									}
								}
							}
						};
						document.onmouseup = function() {
							if (outer.releaseCapture) {
								outer.releaseCapture(true);
							} else if (window.removeEventListener) {
								window.removeEventListener("mousemove",
										document.onmousemove);
								window.removeEventListener("mousemove",
										document.onmouseup);
							}
							outer.style.opacity = 1;
							outer.style.filter = "Alpha(opacity=100)";
							document.onmousemove = null;
							document.onmouseup = null;
						};
					}
				};
			}
			var main = XQuery.makeElement("div", null);
			main.appendChild(pop);
			outer.appendChild(main);
			if (this.basicOperate) {
				var operate = XQuery.makeElement("div", "pop_operate");
				var ok = XQuery.makeElement("a", null, {
					"href" : "javascript:void(0);",
					"innerHTML" : "确定",
					"title" : "确定",
					"hideFocus" : "true",
					"onclick" : function() {
						if (xc.ok) {
							var x = xc.ok();
							if (x) {
								alert(x);
							} else {
								xc.hide();
							}
						} else
							xc.hide();
					}
				});
				var cancle = XQuery.makeElement("a", null, {
					"href" : "javascript:void(0);",
					"title" : "取消",
					"innerHTML" : "取消",
					"hideFocus" : "true",
					"onclick" : function() {
						xc.hide();
					}
				});
				var operatePanel = XQuery.makeElement("div",
						"pop_operatePanel", {
							"hidefocus" : "true"
						});
				operatePanel.appendChild(ok);
				operatePanel.appendChild(cancle);
				operate.appendChild(operatePanel);
				outer.appendChild(operate);
			}
			if (pop.style.display = "none")
				pop.style.display = "";
		}
		var mask = document.getElementById(this.id + "_mask");
		if (!mask) {
			mask = XQuery.makeElement("div", "mask", {
				"id" : this.id + "_mask"
			});
			mask.style.display = "none";
			document.body.appendChild(mask);
		}
		var width = Math.max(document.documentElement.scrollWidth,
				document.documentElement.clientWidth);
		var height = Math.max(document.documentElement.scrollHeight,
				document.documentElement.clientHeight);
		mask.style.width = width + "px";
		mask.style.height = height + "px";
		mask.style.display = "";
		outer.style.top = (document.documentElement.clientHeight - xOuter
				.height())
				/ 2 + "px";
		outer.style.left = (document.documentElement.clientWidth - xOuter
				.width())
				/ 2 + "px";
		outer.style.display = "";
	} else
		alert("No element!");
};
