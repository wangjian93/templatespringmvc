// zTree设置
var treeOptions = {
    check: {
        enable: false
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: null
        },
        key: {
            name: "deptName"
        },
    },
    callback: {
        onClick: zTreeOnClick
    }
};

// 节点单击事件
function zTreeOnClick(event, treeId, treeNode) {
    var deptId = treeNode.deptId;
    refreshChart(deptId);
}

// 渲染树
function renderTree() {
    $.ajax({
        type: "POST",
        url: "listDept",
        data: {},
        dataType: "json",
        success: function (data) {
            var zNodes = data.data;
            $.fn.zTree.init($("#treeDemo"), treeOptions, zNodes);
        },
        error: function (message) {
            alert("数据加载失败");
        }
    });
}