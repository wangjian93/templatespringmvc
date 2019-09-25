var $table = $('#table'),
    $removeBtn = $('#removeBtn'),
    $releaseBtn = $('#releaseBtn');

var columns = [{
    checkbox: true
}, {
    title: 'id',
    align: 'center',
    field: 'id'
}, {
    title: '部门',
    align: 'center',
    field: 'sourceName'
}, {
    title: '操作类型',
    align: 'center',
    field: 'typeName',
},{
    title: '描述',
    align: 'left',
    field: 'description',
},{
    title: '状态',
    align: 'center',
    field: 'statusName',
}];

// 渲染表格
var renderTable = function () {
    $('#table').bootstrapTable({
        toolbar: '#toolbar',
        url: 'listAdjust',
        striped: true,
        columns: columns
    });

    $removeBtn.click(function () {
        var ids = $.map($table.bootstrapTable('getSelections'), function (row) {
            console.log(row);
            return row.id;
        });
        postRemove(ids);
    });

    $releaseBtn.click(function () {
        var ids = $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id;
        });
        postRelease(ids);
    });
};

// 刷新表格
var refreshTable = function() {
    $('table').bootstrapTable('refresh');
};

// 发送移除请求
var postRemove = function(ids) {
    $.ajax({
        type: 'POST',
        url: 'manage/remove',
        data: JSON.stringify(ids),
        contentType: "application/json" ,
        dataType: "json",
        success: function (data) {
            refreshTable();
            refreshChart($("#source").val());
        },
        error: function () {
            alert("请求发送异常");
            refreshTable();
            refreshChart($("#source").val());
        }
    });
};

// 发送发布请求
var postRelease = function(ids) {
    $.ajax({
        type: 'POST',
        url: 'manage/release',
        data: JSON.stringify(ids),
        contentType: "application/json" ,
        dataType: "json",
        success: function (data) {
            refreshTable();
            refreshChart($("#source").val());
        },
        error: function () {
            alert("请求发送异常");
            refreshTable();
            refreshChart($("#source").val());
        }
    });
};

// 格式化状态
function statusFormatter(value, row, index) {
    return "未发布";
}

function typeFormatter(value, row, index) {
    if (value === 1) {
        return '<span class="label label-success">移动</span>';
    } else {
        return '<span class="label label-primary">添加</span>';
    }
}



