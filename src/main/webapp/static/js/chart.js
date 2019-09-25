var oc;
var chartData;
var chartOptins = {
    'nodeTitle': 'deptName',
    'nodeContent': 'deptHeader',
    'direction': 't2b',
    'createNode': function($node, data) {
        renderNodeColor($node, data);
        $node.on('click', node_click);
    },
    'initCompleted': function($chart) {
        // $chart.find('.node:first').css('border-color', 'blue');
    }
};

// 渲染节点颜色
var renderNodeColor = function ($node, data) {
    var level = data.level;
    switch (level) {
        case 1 : $node.addClass('goldenrod'); break;
        case 2 : $node.addClass('lemonchiffon'); break;
        case 3 : $node.addClass('rd-dept'); break;
        case 4 : $node.addClass('pipeline1'); break;
        case 5 : $node.addClass('middle-level'); break;
        case 6 : $node.addClass('product-dept'); break;
        case 7 : $node.addClass('frontend1'); break;
        case 8 : $node.addClass('lemonchiffon'); break;
        case 9 : $node.addClass('frontend1'); break;
        default : break;
    }

    var deptType = data.deptType;
    switch (deptType) {
        case 0 : $node.css('border-color', 'grey'); break;
        default : break;
    }
};

// 节点单击事件执行方法
var node_click = function() {
    var deptID = $(this).attr('id');
    var deptName = $(this).children('.title').text();
    $('#selected-node').val(deptName);
    $('#source').val(deptID);
    $('#add_parentName').val(deptName);
    $('#mobile_deptName').val(deptName);
    $('#addParent_selected').val(deptName);
};

// 根据选中的节点进行收缩
var collapseBySelected = function() {
    var $selected = $('#chart-container').find('.node.focused');
    // oc.hideSiblings($selected, 'right');
    // oc.hideSiblings($selected, 'left');
    oc.hideChildren($selected);
    if ($selected.length) {
        $selected.parents('.nodes').children(':has(.focused)').find('.node:first').each(function(index, superior) {
            if (!$(superior).find('.horizontalEdge:first').closest('table').parent().siblings().is('.hidden')) {
                $(superior).find('.horizontalEdge:first').trigger('click');
            }
        });
    } else {
        alert('please select the node firstly');
    }
};

// 收缩全部
var collapse = function() {
    oc.hideChildren(oc.$chart.find('.node:first'));
};

// 展开全部
var expand = function() {
    var $temp = oc.$chart.find('.hidden').removeClass('hidden');
    $temp[0].offsetWidth;
    $temp.find('.slide-up').removeClass('slide-up');
};

// 根据部门id手动触发节点的单击事件
var selectNode = function(deptId) {
    console.log(deptId);
    $('#chart-container')
        .find(".node[id="+deptId+"]")
        .click();
};

// chart图渲染
var renderChart = function() {
    chartData = getChartData('10000000');
    chartOptins.data = chartData;
    //加载层
    $.mask_element('#chart-container', 1000);
    oc = $('#chart-container').orgchart(chartOptins);
    selectNode('10000000');
    collapseBySelected();
    return oc;
};

// chart图刷新
var refreshChart = function(deptId) {
    chartData = getChartData(deptId);
    chartOptins.data = chartData;
    //加载层
    $.mask_element('#chart-container', 1000);
    oc.init(chartOptins);
    selectNode(deptId);
    setTimeout(function () {
        collapseBySelected();
    }, 1);
};


$(function() {
    renderTree();
    renderTable();
    oc = renderChart();
    $('#btn-add-nodes').on('click', addNode);
    $('#btn-mobile-nodes').on('click', mobileNode);
    $('#btn-addParent-nodes').on('click', addParentNode);
});

// 添加节点
var addNode = function() {
    var $selected = $('#chart-container').find('.node.focused');
    var $node = $selected;
    var source = $("input[name='source']").val();
    var parentId = source;
    var deptName = $("input[name='add_deptName']").val();
    var deptNameEN = "";
    var deptNameS = $("input[name='add_deptNameS']").val();
    var deptLevel = $("input[name='add_deptLevel']").val();
    var deptHeader = $("input[name='add_deptHeader']").val();

    addPost(source, parentId, deptName, deptNameEN, deptNameS, deptLevel, deptHeader);

    refreshChart(source);

    $('.collapse').collapse('hide');
};

// 移动节点
var mobileNode = function() {
    var source = $("input[name='source']").val();
    var deptId = $("input[name='source']").val();
    var parentId = $("input[name='mobile_parentId']").val();
    mobilePost(source, deptId, parentId);
    refreshChart(source);
    $('.collapse').collapse('hide');
};

// 发送请求获取数据
var getChartData = function(deptId) {
    var r;
    $.ajax({
        type: "POST",
        url: "listSignedDeptTree",
        data: {
            "deptId" : deptId
        },
        dataType: "json",
        async : false,
        success: function (data) {
            r = data.data;
        },
        error: function (message) {
            alert("请求发送失败");
        }
    });
    return r;
};

// 发送add请求
function addPost(source, parentId, deptName, deptNameEN, deptNameS, deptLevel, deptHeader) {
    $.ajax({
        type: "POST",
        url: "adjust",
        data: {
            "source" : source,
            "target": source,
            "type": 1,
            "adjustDept": '',
            "adjustParent": '',
            "deptName": deptName,
            "deptNameEN": deptNameEN,
            "deptNameS": deptNameS,
            "deptLevel": deptLevel,
            "deptHeader": deptHeader
        },
        dataType: "json",
        async : false,
        success: function (data) {
            refreshTable();
        },
        error: function () {
            alert("请求发送失败");
        }
    });
}

// 发送mobile请求
function mobilePost(source, deptId, parentId) {
    var flag = false;
    $.ajax({
        type: "POST",
        url: "manage/mobile",
        data: {
            "source" : source,
            "deptId": deptId,
            "parentId" : parentId
        },
        dataType: "json",
        async : false,
        success: function (data) {
            flag = true;
            refreshTable();
        },
        error: function () {
            alert("请求发送失败");
        }
    });
    return flag;
}



var addParentNode = function() {
    var $selected = $('#chart-container').find('.node.focused');
    var $node = $selected;
    var source = $("input[name='source']").val();


    var parentId = $node.attr('data-parent');

    var deptName = $("input[name='addParent_deptName']").val();
    var deptNameEN = "";
    var deptNameS = $("input[name='addParent_deptNameS']").val();
    var deptLevel = $("input[name='addParent_deptLevel']").val();
    var deptHeader = $("input[name='addParent_deptHeader']").val();

    addParentPost(source, parentId, deptName, deptNameEN, deptNameS, deptLevel, deptHeader);

    refreshChart(source);
    $('.collapse').collapse('hide');
};


function addParentPost(source, parentId, deptName, deptNameEN, deptNameS, deptLevel, deptHeader) {
    $.ajax({
        type: "POST",
        url: "adjust",
        data: {
            "source" : source,
            "target": source,
            "type": 1,
            "adjustDept": '',
            "adjustParent": '',
            "deptName": deptName,
            "deptNameEN": deptNameEN,
            "deptNameS": deptNameS,
            "deptLevel": deptLevel,
            "deptHeader": deptHeader
        },
        dataType: "json",
        async : false,
        success: function (data) {
            refreshTable();
        },
        error: function () {
            alert("请求发送失败");
        }
    });
}

