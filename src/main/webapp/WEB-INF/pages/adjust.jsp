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
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand">组织加签管理</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="print">Print</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div style="padding-left: 40px; padding-right: 40px;">
        <div class="row">
            <h4 class="sub-header">部门签核层级</h4>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <label>部门</label>
                            <input type="text" class="form-control input-sm" name="source" value="10000000">
                        </div>

                        <div hidden>
                            <input type="text" class="form-control input-sm" name="sourceName" hidden>
                        </div>

                        <button type="button" class="btn btn-sm" onclick="search_click()">查询</button>

                    </form>

                    <div id="signedDept_chart"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <h4 class="sub-header">调整内容</h4>
            <button class="btn btn-sm" onclick="adjust_release()">发布</button>
            <button class="btn btn-sm" onclick="adjust_delete()">删除</button>
            <table id="adjust_table"></table>
        </div>

        <br>

        <div class="row">
            <h4 class="sub-header">部门列表</h4>
            <table id="dept_table"></table>
        </div>
    </div>

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
                <label class="col-sm-4 control-label">签核至</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_targetName" readonly>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">变更类型</label>
                <div class="radio col-sm-6">
                    <label>
                        <input type="radio" name="adjust_type"  value="1" checked> 加签虚拟部门
                    </label>
                    <%--<label>--%>
                    <%--<input type="radio" name="adjust_type"  value="2" > 调整部门上级--%>
                    <%--</label>--%>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">上级部门</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_adjustParentName">
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-4 control-label">部门名称</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_deptName">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">部门名英</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_deptNameEN">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">部门简称</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_deptNameS">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">部门级别</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_deptLevel">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">部门负责人</label>
                <div class="col-sm-6">
                    <input class="form-control input-sm" name="adjust_deptHeader">
                </div>
            </div>
        </form>
    </div>




    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/adjust.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/adjust_table.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/signedDept_chart.js"></script>
</body>
</html>
