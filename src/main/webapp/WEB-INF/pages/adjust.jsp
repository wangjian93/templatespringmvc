<%--
  Created by IntelliJ IDEA.
  User: wangjian
  Date: 2018/12/21
  Time: 8:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="description" content="">
    <meta name="author" content="wangjian">
    <title>加签处理</title>
    <%@ include file="static.jsp" %>
    <title>组织加签管理</title>

    <style>
        .fixed-table-body {
            height: auto;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand">组织加签管理</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="print" target="_blank">图示</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container-fluid" style="padding-left: 40px; padding-right: 40px;">
        <div class="row">
            <h4 class="sub-header">加签部门</h4>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <label>部门</label>
                            <input type="text" class="form-control input-sm" name="sourceName" onclick="deptTree()">
                        </div>

                        <div hidden>
                            <input type="text" class="form-control input-sm" name="source" value="10000000">
                        </div>

                        <button type="button" class="btn btn-sm" onclick="search_click()">查询</button>

                    </form>

                    <div id="signedDept_chart"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <h4 class="sub-header">加签内容</h4>
            <button class="btn btn-sm" onclick="adjust_release()">发布</button>
            <button class="btn btn-sm" onclick="adjust_delete()">删除</button>
            <table id="adjust_table"></table>
        </div>

        <br>

        <div class="row">
            <h4 class="sub-header">部门列表</h4>
            <button type="button" class="btn btn-default btn-sm" onclick="renderDeptTable()">列表</button>
            <a href="print" target="_blank" class="btn btn-primary btn-sm">图示</a>
            <table id="dept_table"></table>
        </div>
    </div>

    <br>
    <br>
    <br>

    <div id="adjust_html" hidden>
        <form id="adjust_form" class="form-horizontal">
            <div hidden>
                <input class="form-control input-sm" name="adjust_source">
                <input class="form-control input-sm" name="adjust_target">
                <input class="form-control input-sm" name="adjust_adjustParent">
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">部门</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_sourceName" readonly>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">范围</label>
                <div class="radio col-sm-6">
                    <label>
                        <input type="radio" name="scope" checked="chekced" readonly/> 所有签核单
                    </label>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">层级签核至</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_targetName" readonly>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">变更类型</label>
                <div class="radio col-sm-6">
                    <label>
                        <input type="radio" name="adjust_type"  value="1" checked="chekced" /> 加签虚拟部门
                    </label>
                </div>
            </div>

            <div class="form-group" hidden>
                <label class="col-sm-4 control-label">上级部门</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_adjustParentName">
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-4 control-label">加签部门名称</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_deptName">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">加签部门名英</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_deptNameEN">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">加签部门简称</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_deptNameS">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">加签部门级别</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_deptLevel">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">加签部门负责人</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_deptHeaderName" onclick="empTree()" />
                </div>
                <div hidden>
                    <input class="form-control input-sm" name="adjust_deptHeader" hidden/>
                </div>
            </div>
        </form>
    </div>

    <div id="empTree" hidden>
        <div>
            <!--人员组织树-->
            <div id="memo_emp"></div>
            <div id="treeArea_emp"></div>
        </div>
    </div>

    <div id="deptTree" hidden>
        <div>
            <!--人员组织树-->
            <div id="memo_dept"></div>
            <div id="treeArea_dept"></div>
        </div>
    </div>

    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/adjust.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/adjust_table.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/signedDept_chart.js"></script>

    <script>
        /**人员组织树**/
        function empTree() {
            XQuery.XFactory.init();
            var tree = XQuery.make({
                width : "250px",
                height : "400px",
                xtype : "XTree",
                root : "10000000",
                url : "http://myivo.ivo.com.cn/org/" + "/org/emp",
                crossdomain : true,
                autoParam : [ "id" ],
                nodeClick : function(tree, node) {
                    if($("input[name='adjust_deptHeaderName']")) {
                        $("input[name='adjust_deptHeaderName']").val(node.id + " " + node.name);
                    }
                    if($("input[name='adjust_deptHeader']")) {
                        $("input[name='adjust_deptHeader']").val(node.id);
                    }
                    layer.close(empTree_open);
                }
            });
            var html = tree.compile();
            $("#memo_emp").html("");
            $("#treeArea_emp").html(html);
            tree.init();

            var empTree_open = layer.open({
                id: 'empTree_open',
                type: 1,
                title: '人员选择',
                area: ['250px', '400px'],
                offset: ['200px', '100px'],
                shadeClose: true, //点击遮罩关闭
                content: $('#empTree'),
                cancel: function(){}
            });
        }


        /** 部门组织树 */
        function deptTree() {
            XQuery.XFactory.init();
            var tree = XQuery.make({
                width : "250px",
                height : "400px",
                xtype : "XTree",
                root : "10000000",
                url : "http://myivo.ivo.com.cn/org/" + "/org/dept",
                crossdomain : true,
                autoParam : [ "id" ],
                nodeClick : function(tree, node) {
                    console.log(node);
                    if($("input[name='sourceName']")) {
                        $("input[name='sourceName']").val("[" + node.id + "] " + node.name);
                    }
                    if($("input[name='source']")) {
                        $("input[name='source']").val(node.id);
                    }
                    layer.close(deptTree_open);
                    search_click();

                }
            });
            var html = tree.compile();
            $("#memo_dept").html("");
            $("#treeArea_dept").html(html);
            tree.init();

            var deptTree_open = layer.open({
                id: 'deptTree_open',
                type: 1,
                title: '部门选择',
                area: ['250px', '400px'],
                offset: ['200px', '100px'],
                shadeClose: true, //点击遮罩关闭
                content: $('#deptTree'),
                cancel: function(){}
            });
        }
    </script>
</body>
</html>
