var adjust_table;

function renderTable($table) {
    var columns = [
            {checkbox: true},
            {field: 'id', title: 'ID'},
            {field: 'sourceName', title: '源'},
            {field: 'targetName', title: '目标'},
            {field: 'typeName', title: '变更类型'},
            {field: 'description', title: '描述'},
            {field: 'statusName', title: '状态'}
    ];

    adjust_table = $table.bootstrapTable({
        url: '/listAdjust',
        columns: columns,
        detailView: true,
        striped: true,
        height: '200',
        detailFormatter: function(index, row) {
            return detailFormatter(index, row);
        }
    });
}

function refreshTable() {
    $("#adjust_table").bootstrapTable('refresh');
}

// ROW明细格式
function detailFormatter(index, row) {
    var html = [];

    var actions = row.actions;
    for ( var i = 0; i <actions.length; i++) {
        var action = actions[i];
        html.push('<p><b>' + 'Item'+i + ':</b> ' + action.description + '</p>')
    }
    return html.join('')
}

// 发布
function adjust_release() {
    var $table = $("#adjust_table");
    var ids = $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id;
    });

    console.log(ids);

    $.ajax({
        type: "POST",
        url: "/adjust/release",
        data: JSON.stringify(ids),
        contentType: "application/json" ,
        dataType: "json",
        success: function (data) {
            if(data.code == 200) {
                layer.msg(data.msg);
                refreshTable();
                refreshChart();
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            alert("请求发送失败");
        }
    });
}

// 删除
function adjust_delete() {
    var $table = $("#adjust_table");
    var ids = $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id;
    });

    $.ajax({
        type: "POST",
        url: "/adjust/remove",
        data: JSON.stringify(ids),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            if(data.code == 200) {
                layer.msg(data.msg);
                refreshTable();
                refreshChart();
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            alert("请求发送失败");
        }
    });
}


var renderDeptTable = function() {

}