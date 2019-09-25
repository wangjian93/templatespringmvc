var XTreeFactory = {
	xtype : "XTree",
	cache : {},
	size : 0,
	maxElementsInMemory : 100,
	memoryStoreEvictionPolicy : "FIFO",
	make : function(settings) {
		var id = settings.id || this.xtype + "_" + this.size;;
		if (this.cache[id])
			return this.cache[id];
		else {
			var tree = new XTree(id, settings);
			this.cache[id] = tree;
			this.size++;
			return tree;
		}
	},
	get : function(id) {
		return this.cache[id];
	}
};
function XTree(id, settings) {
	this.id = id;
	this.nodes = [];
	this.autoParam = [ "id" ];
	this.multi = false;
	this.parentSelect = false;
	this.displayItems = [ "id", "name" ];
	this.width = "100%";
	this.height = "100%";
	this.clickMap = {};
	if (settings) {
		$.extend(this, settings);
	}
}
XTree.prototype.compile = function() {
	var x = this;
	var panel = document.getElementById(x.id);
	if (!panel) {
		panel = XQuery.makeElement("div", null, {
			"id" : x.id
		});
		panel.style.width = x.width;
		panel.style.height = x.height;
		
		var tree_panel = XQuery.makeElement("div", "tree_panel");
		tree_panel.style.width = panel.style.width;
		tree_panel.style.height = panel.style.height;
		var search = XQuery.makeElement("div", "search");
		search.style.width = tree_panel.style.width;
		search.style.height = "40px";
		var search_panel = XQuery.makeElement("div", "search_panel");
		var searchContent = XQuery.makeElement("input", null, {
			"type" : "text",
			"id" : x.id + "_search",
			"autocomplete" : "off",
			"onkeydown" : function(event) {
				if (window.event != null) {
					event = window.event;
				}
				if (event.keyCode == 13) {
					this.parentNode.childNodes[1].click();
					return;
				}
			},
			"onkeyup" : function(event) {
				if (window.event != null) {
					event = window.event;
				}
				if (event.keyCode == 8) {
					if (this.value == "") {
						this.parentNode.childNodes[1].click();
						return;
					}
				}
			}
		});
		var searchButton = XQuery
				.makeElement(
						"a",
						null,
						{
							"href" : "javascript:void(0);",
							"title" : "查询",
							"hideFocus" : "true",
							"onclick" : function() {
								var query = document.getElementById(x.id
										+ "_search").value;
								if (query && query != "") {
									document.getElementById(x.id + "_tree").style.display = "none";
									var queryTree = document
											.getElementById(x.id + "_query");
									queryTree.innerHTML = "查询中。。。";
									queryTree.style.display = "";
									var dataType = "json";
									if(x.crossdomain)
										dataType = "jsonp";
									$.ajax({
										type : "POST",
										url : x.url + "?m=query",
										data : {root : x.root, queryString : query},
										dataType : dataType,
										success : function(data) {
											if (data == "")
												queryTree.innerHTML = "查找到：<br>没有結果。";
											else {
												queryTree.innerHTML = "";
												$.fn.zTree.init($("#" + x.id + "_query"), {
													data : {
														key : {
															name : 'queryName'
														}
													},
													view : {
														selectedMulti : false,
														autoCancelSelected : false,
														showTitle : false,
														dblClickExpand : false,
														showLine : false
													},
													callback : {
														onClick : x.selectNode
													}
												}, data);
											}
										}
									});
								} else {
									document.getElementById(x.id + "_tree").style.display = "";
									document.getElementById(x.id + "_query").style.display = "none";
								}
							}
						});
		$(searchContent).width($(search).width() - 40);
		search_panel.appendChild(searchContent);
		search_panel.appendChild(searchButton);
		search.appendChild(search_panel);
		tree_panel.appendChild(search);
		var ztree = XQuery.makeElement("div", "ztree_panel");
		$(ztree).height($(tree_panel).height() - $(search).height());
		var ul = XQuery.makeElement("ul", "ztree", {
			"id" : x.id + "_tree"
		});
		var ulquery = XQuery.makeElement("ul", "ztree", {
			"id" : x.id + "_query"
		});
		ulquery.style.display = "none";
		ztree.appendChild(ul);
		ztree.appendChild(ulquery);
		tree_panel.appendChild(ztree);
		panel.appendChild(tree_panel);
	}
	return panel;
};
XTree.prototype.init = function() {
	var x = this;
	var dataType = "json";
	if(x.crossdomain)
		dataType = "jsonp";
	x.treeObj = $.fn.zTree.init($("#" + x.id + "_tree"), {
		view : {
			selectedMulti : false,
			autoCancelSelected : false,
			showTitle : false,
			dblClickExpand : false
		},
		async : {
			autoParam : x.autoParam,
			enable : true,
			url : x.url + "?m=load",
			dataType : dataType,
			otherParam : [ "root", x.root ]
		},
		callback : {
			onClick : x.selectNode,
			onDblClick : x.dblClick
		}
	});
	x.initSelectNodes(x.nodes);
};
XTree.prototype.selectNode = function(event, zTreeId, treeNode) {
	var token = zTreeId.indexOf("_query") > -1 ? "_query" : "_tree";
	var treeId = zTreeId.replace(token, "");
	var x = XQuery.getCmp(treeId, "XTree");
	if (treeNode.isParent) {
		if (!x.parentSelect)
			return;
	}
	if (!x.multi) {
		x.nodes = [];
		x.nodes.push(treeNode);
	} else {
		if (treeNode.isParent) {
			if(x.cascade) {
				var type = x.clickMap["node_" + treeNode.id];
				if(typeof(type) == "undefined")
					type = 0;
				var children = x.treeObj.getNodesByParam("isParent", false, treeNode);
				for(var n in children) {
					if(type == 0) {
						var add = true;
						for (var i = 0; i < x.nodes.length; i++) {
							if (x.nodes[i].id == children[n].id) {
								add = false;
								break;
							}
						}
						if (add)
							x.nodes.push(children[n]);
						x.clickMap["node_" + treeNode.id] = 1;
						
					} else {
						for (var i = 0; i < x.nodes.length; i++) {
							if (x.nodes[i].id == children[n].id) {
								x.nodes.splice(i, 1);
								break;
							}
						}
						x.clickMap["node_" + treeNode.id] = 0;
					}
				}
			} else {
				var add = true;
				for (var i = 0; i < x.nodes.length; i++) {
					if (x.nodes[i].id == treeNode.id) {
						x.nodes.splice(i, 1);
						add = false;
						break;
					}
				}
				if (add)
					x.nodes.push(treeNode);
			}
		} else {
			var add = true;
			for (var i = 0; i < x.nodes.length; i++) {
				if (x.nodes[i].id == treeNode.id) {
					x.nodes.splice(i, 1);
					add = false;
					break;
				}
			}
			if (add)
				x.nodes.push(treeNode);
		}
	}
	if(typeof(x.nodeClick) == "function")
		x.nodeClick(x, treeNode);
};
XTree.prototype.dblClick = function(event, zTreeId, treeNode) {
	var token = zTreeId.indexOf("_query") > -1 ? "_query" : "_tree";
	var treeId = zTreeId.replace(token, "");
	var x = XQuery.getCmp(treeId, "XTree");
	if (treeNode.isParent) {
		x.treeObj.expandNode(treeNode, !treeNode.open);
	}
	if(typeof(x.nodeDblClick) == "function")
		x.nodeDblClick(x, treeNode);
};
XTree.prototype.initSelectNodes = function(nodes) {
	if(typeof(nodes) == "undefined")
		nodes = [];
	var x = this;
//	x.nodes = nodes;
	for(var i in nodes) {
		if(typeof(x.nodeClick) == "function")
			x.nodeClick(x, nodes[i]);
	}
	
};
XTree.prototype.getSelectedNodes = function() {
	return this.nodes.slice(0);
};