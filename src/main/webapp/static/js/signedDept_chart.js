var oc;

var getOption = function () {
    var deptId = $("input[name]").val();
    var ajaxURLs = {
        'children': '/orgchart/children/',
        'parent': '/orgchart/parent/',
        'siblings': function(nodeData) {
            return '/orgchart/siblings/' + nodeData.id;
        },
        'families': function(nodeData) {
            return '/orgchart/families/' + nodeData.id;
        }
    };

    var option = {
        'data': '/listPathTree?deptId='+deptId,
        'ajaxURL': ajaxURLs,
        'nodeTitle': 'deptName',
        'nodeContent': 'deptHeader',
        'direction': 't2b',
        'toggleSiblingsResp': true,
        'createNode': function($node, data) {
            // selectNodeColor($node, data);

            if(data.id == deptId) {
                $node.css("background-color","yellow");
            }

            var secondMenuIcon = $('<i>', {
                'class': 'glyphicon glyphicon-edit',
                click: function() {
                    var source = $("input[name='source']").val();
                    var sourceName = $("input[name='sourceName']").val();
                    var target = data.id;
                    var  targetName = data.deptName;
                    adjust_open(source, source, target, targetName);
                }
            });

            if(data.parentId && data.adjustAble) {
                $node.prepend(secondMenuIcon);
            }
            $node.children(".topEdge").remove();
        }
    };
    return option;
};

function renderChart($chart, deptId) {
    oc = $chart.orgchart(getOption(deptId));
}

var refreshChart = function () {
    var deptId = $("input[name]").val();
    oc.init(getOption(deptId));
};


// 选择节点的颜色
var selectNodeColor = function ($node, data) {
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

    var deptType = data.type;
    switch (deptType) {
        case 0 : $node.css('border-color', 'grey'); break;
        default : break;
    }
};