<%--
  Created by IntelliJ IDEA.
  User: wangjian
  Date: 2018/11/23
  Time: 8:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="static.jsp" %>
    <style>
        #chart-container {
            height: 100%;
            width: calc(100% - 24px);
            top: 10px;
            bottom: 10px;
            left: 10px;
            right: 10px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">组织签核图</a>
        </div>
    </div>
</nav>

<div id="chart-container"></div>

<script>

    var chartOptins = {
        'data': 'listSignedDeptTree',
        'nodeTitle': 'deptName',
        'nodeContent': 'deptHeader',
        'direction': 't2b',
        'createNode': function($node, data) {
            renderNodeColor($node, data);
        },
        'verticalLevel': 7,
        'exportButton': true,
        'exportFilename': 'Orgchart',
        'exportFileextension': 'png',
        'pan': true,
        'zoom': true,
        'zoomoutLimit': 0.1
    };

    // 渲染节点颜色
    var renderNodeColor = function ($node, data) {
        // var level = data.level;
        // switch (level) {
        //     case 1 : $node.addClass('goldenrod'); break;
        //     case 2 : $node.addClass('lemonchiffon'); break;
        //     case 3 : $node.addClass('rd-dept'); break;
        //     case 4 : $node.addClass('pipeline1'); break;
        //     case 5 : $node.addClass('middle-level'); break;
        //     case 6 : $node.addClass('product-dept'); break;
        //     case 7 : $node.addClass('frontend1'); break;
        //     case 8 : $node.addClass('lemonchiffon'); break;
        //     case 9 : $node.addClass('frontend1'); break;
        //     default : break;
        // }
        var deptType = data.deptType;
        switch (deptType) {
            case 2 : $node.css('border-color', 'grey'); break;
            default : break;
        }
    };

    $(function() {
        var oc = $('#chart-container').orgchart(chartOptins);
    });
</script>

</body>
</html>
