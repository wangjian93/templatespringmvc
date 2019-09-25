$(function() {
    var $table = $('#adjust_table');
    renderTable($table);

    var $chart = $('#signedDept_chart');
    renderChart($chart);

    // renderDeptTable();
});

var search_click = function() {
    refreshChart();
};

// 打开弹框
function adjust_open(source, sourceName, target, targetName) {
    adjust_init(source, sourceName, target, targetName);
    layer.open({
        id: 'adjust_open',
        type: 1,
        title: '签核调整',
        area: ['500px', '500px'],
        offset: ['100px', '50px'],
        shadeClose: true, //点击遮罩关闭
        content: $('#adjust_html'),
        btn: ['提交'],
        yes: function(){
            adjust_submit();
        },
        cancel: function(){
            adjust_clear();
        }
    });
}

// 打开弹框初始赋值
function adjust_init(source, sourceName, target, targetName) {
    adjust_clear();
    $("input[name='adjust_source']").val(source);
    $("input[name='adjust_sourceName']").val(sourceName);

    $("input[name='adjust_target']").val(target);
    $("input[name='adjust_targetName']").val(targetName);
}

// 清空弹框的赋值
function adjust_clear() {
    $("input[name='adjust_source']").val("");
    $("input[name='adjust_target']").val("");
    $("input[name='adjust_adjustParent']").val("");
    $("input[name='adjust_sourceName']").val("");
    $("input[name='adjust_targetName']").val("");
    $("input:radio[name='adjust_type']").attr('checked',false);
    $("input[name='adjust_adjustParentName']").val("");
    $("input[name='adjust_deptName']").val("");
    $("input[name='adjust_deptNameEN']").val("");
    $("input[name='adjust_deptNameS']").val("");
    $("input[name='adjust_deptLevel']").val("");
    $("input[name='adjust_deptHeader']").val("");
}

// 提交
function adjust_submit() {
    var source = $("input[name='adjust_sourceName']").val();
    if(source == "") {

    }
    var target = $("input[name='adjust_target']").val();
    var type = $("input:radio[name='adjust_type']").val();
    var adjustParent = $("input[name='adjust_adjustParentName']").val();
    var deptName = $("input[name='adjust_deptName']").val();
    var deptNameEN = $("input[name='adjust_deptNameEN']").val();
    var deptNameS = $("input[name='adjust_deptNameS']").val();
    var deptLevel = $("input[name='adjust_deptLevel']").val();
    var deptHeader = $("input[name='adjust_deptHeader']").val();

    $.ajax({
        type: "POST",
        url: "adjust",
        data: {
            "source" : source,
            "target": target,
            "type": type,
            "adjustDept": target,
            "adjustParent": adjustParent,
            "deptName": deptName,
            "deptNameEN": deptNameEN,
            "deptNameS": deptNameS,
            "deptLevel": deptLevel,
            "deptHeader": deptHeader
        },
        dataType: "json",
        async : false,
        success: function (data) {
            if(data.code == 200) {
                layer.msg(data.msg);
                layer.closeAll();
                adjust_clear();
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