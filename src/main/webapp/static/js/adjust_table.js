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
        url: 'listAdjust',
        columns: columns,
        detailView: true,
        striped: true,
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
        url: "adjust/release",
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
        url: "adjust/remove",
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

var dept_table;
var renderDeptTable = function() {
    if(dept_table) {
        $("#dept_table").bootstrapTable('refresh');
        return;
    }
    var $table = $('#dept_table');
    dept_table = $("#dept_table").bootstrapTable({
        url: 'listSignedDept',
        striped: true,
        // sidePagination: 'server',
        idField: 'deptId',
        columns: [
            {
                field: 'deptId',
                title: '部门编号'
            },
            {
                field: 'deptName',
                title: '部门名称'
            },
            {
                field: 'deptNameS',
                title: '部门简称'
            },
            {
                field: 'deptLevel',
                title: '级别'
            },
            {
                field: 'deptHeader',
                title: '主管'
            },{
                field: 'type',
                title: '类型'
            },{
                field: 'parentId',
                title: '上级部门'
            }
        ],
        treeShowField: 'deptId',
        parentIdField: 'parentId',
        onLoadSuccess: function(data) {
            $("#dept_table").treegrid({
                treeColumn: 1,
                onChange: function() {
                    $("#dept_table").bootstrapTable('resetWidth')
                }
            })
        }

        // onResetView: function(data) {
        //     //console.log('load');
        //     $table.treegrid({
        //         initialState: 'collapsed',// 所有节点都折叠
        //         // initialState: 'expanded',// 所有节点都展开，默认展开
        //         treeColumn: 1,
        //         // expanderExpandedClass: 'glyphicon glyphicon-minus',  //图标样式
        //         // expanderCollapsedClass: 'glyphicon glyphicon-plus',
        //         onChange: function() {
        //             $table.bootstrapTable('resetWidth');
        //         }
        //     });
        //
        //     //只展开树形的第一级节点
        //     $table.treegrid('getRootNodes').treegrid('expand');
        //
        // }
        // ,
        // onCheck:function(row){
        //     var datas = $table.bootstrapTable('getData');
        //     // 勾选子类
        //     selectChilds(datas,row,"id","pid",true);
        //
        //     // 勾选父类
        //     selectParentChecked(datas,row,"id","pid")
        //
        //     // 刷新数据
        //     $table.bootstrapTable('load', datas);
        // },
        //
        // onUncheck:function(row){
        //     var datas = $table.bootstrapTable('getData');
        //     selectChilds(datas,row,"id","pid",false);
        //     $table.bootstrapTable('load', datas);
        // }
    });
};

var refreshDeptTable = function() {
    $("#dept_table").bootstrapTable('refresh');
};