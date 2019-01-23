<%--
  Created by IntelliJ IDEA.
  User: wangjian
  Date: 2018/11/15
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="description" content="">
    <meta name="author" content="">
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
            </div><!--/.nav-collapse -->
        </div>
    </nav>

    <div class="container-fluid">
        <div class="col-md-3 sidebar">
            <div class="row placeholders">
                <ul id="treeDemo" class="ztree">
                </ul>
            </div>
        </div>

        <div class="col-md-9 col-md-offset-3 main">
            <h4 class="page-header">部门签核流程</h4>
            <div class="row placeholders">
                <div class="col-md-3">
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon">当前选择部门</span>
                        <input type="text" class="form-control btn-xs" id="selected-node" aria-describedby="basic-addon" readonly>
                        <input type="text" id="source" name="source" hidden>
                    </div>
                </div>

                <div class="col-md-4">
                    <button class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#AddParent" aria-expanded="false" aria-controls="collapseExample">
                        加签
                    </button>
                    <%--<button class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#Add" aria-expanded="false" aria-controls="collapseExample">--%>
                        <%--增加子部门--%>
                    <%--</button>--%>
                    <button class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#Mobile" aria-expanded="false" aria-controls="collapseExample">
                        变动上级部门
                    </button>
                    <button class="btn btn-primary btn-sm" type="button">
                        Remove
                    </button>
                </div>

                <div class="col-md-5">
                    <div class="well collapse" id="AddParent">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="addParent_selected" class="col-sm-3 control-label">选择部门</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm" id="addParent_selected" name="addParent_selected" placeholder="部门名称">
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<label for="addParent_parentName" class="col-sm-3 control-label">上级部门</label>--%>
                                <%--<div class="col-sm-9">--%>
                                    <%--<input type="text" class="form-control input-sm" id="addParent_parentName" name="addParent_parentName" placeholder="部门名称">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label for="addParent_deptName" class="col-sm-3 control-label">部门名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm" id="addParent_deptName" name="addParent_deptName" placeholder="部门名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="addParent_deptNameS" class="col-sm-3 control-label">部门简称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm" id="addParent_deptNameS" name="addParent_deptNameS" placeholder="部门简称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="addParent_deptHeader" class="col-sm-3 control-label">部门主管</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm" id="addParent_deptHeader" name="addParent_deptHeader" placeholder="部门主管">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="addParent_deptLevel" class="col-sm-3 control-label">部门等级</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm" id="addParent_deptLevel" name="addParent_deptLevel" placeholder="部门等级">
                                </div>
                            </div>
                            <button id="btn-addParent-nodes" type="button" class="btn btn-primary btn-xs">添加</button>
                        </form>
                    </div>

                    <div class="well collapse" id="Add">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="add_parentName" class="col-sm-3 control-label">上级部门</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm" id="add_parentName" name="add_parentName" placeholder="部门名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add_deptName" class="col-sm-3 control-label">部门名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm" id="add_deptName" name="add_deptName" placeholder="部门名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add_deptNameS" class="col-sm-3 control-label">部门简称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm" id="add_deptNameS" name="add_deptNameS" placeholder="部门简称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add_deptHeader" class="col-sm-3 control-label">部门主管</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm" id="add_deptHeader" name="add_deptHeader" placeholder="部门主管">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add_deptLevel" class="col-sm-3 control-label">部门等级</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm" id="add_deptLevel" name="add_deptLevel" placeholder="部门等级">
                                </div>
                            </div>
                            <button id="btn-add-nodes" type="button" class="btn btn-primary btn-xs">添加</button>
                        </form>
                    </div>

                    <div class="well collapse" id="Mobile">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="mobile_deptName" class="col-sm-3 control-label">当前部门</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control input-sm" id="mobile_deptName" name="mobile_deptName" placeholder="部门名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="mobile_parentId" class="col-sm-3 control-label">上级部门</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control input-sm" id="mobile_parentId" name="mobile_parentId" placeholder="部门名称">
                                </div>
                            </div>
                            <button id="btn-mobile-nodes" type="button" class="btn btn-primary btn-xs">确定</button>
                        </form>
                    </div>
                </div>

                <div id="chart-container"></div>
            </div>

            <h4 class="sub-header">变更记录</h4>
            <div class="table-responsive">
                <div id="toolbar">
                    <button id="removeBtn" class="btn btn-default">移除</button>
                    <button id="releaseBtn" class="btn btn-default">发布</button>
                </div>
                <table id="table"></table>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/home.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/chart.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/tree.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/table.js"></script>
</body>
</html>
