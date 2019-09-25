<%--
  Created by IntelliJ IDEA.
  User: wangjian
  Date: 2018/11/12
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>

<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Jquery -->
<script src="<%=request.getContextPath()%>/static/plugins/jquery/jquery.min.js"></script>

<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/static/plugins/bootstrap/js/bootstrap.min.js"></script>


<!-- bootstrap-table -->
<link href="<%=request.getContextPath()%>/static/plugins/bootstrapTable/bootstrap-table.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/static/plugins/bootstrapTable/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath()%>/static/plugins/bootstrapTable/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=request.getContextPath()%>/static/plugins/bootstrapTable/extensions/treegrid/bootstrap-table-treegrid.min.js"></script>


<!-- jquery-treegrid -->
<link href="<%=request.getContextPath()%>/static/plugins/jquery/treegrid/jquery.treegrid.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/static/plugins/jquery/treegrid/jquery.treegrid.min.js"></script>


<!-- orgChart -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/plugins/orgChart/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/plugins/orgChart/css/jquery.orgchart.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/plugins/orgChart/css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgChart/js/html2canvas.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgChart/js/jspdf.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgChart/js/jquery.orgchart.js"></script>



<!-- zTree CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/plugins/zTree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/zTree/js/jquery.ztree.exedit.js"></script>




<!-- loading -->
<link href="<%=request.getContextPath()%>/static/plugins/loading/load.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/static/plugins/loading/load.js"></script>



<!-- layer -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/layer/layer.js"></script>



<!-- orgTree -->
<%--<link href="<%=request.getContextPath()%>/static/plugins/orgTree/css/clear.css" rel="stylesheet">--%>
<%--<link href="<%=request.getContextPath()%>/static/plugins/orgTree/css/message.css" rel="stylesheet">--%>
<%--<link href="<%=request.getContextPath()%>/static/plugins/orgTree/css/pop.css" rel="stylesheet">--%>
<%--<link href="<%=request.getContextPath()%>/static/plugins/orgTree/css/xgrid.css" rel="stylesheet">--%>
<%--<link href="<%=request.getContextPath()%>/static/plugins/orgTree/zTreeStyle.css" rel="stylesheet">--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgTree/xquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgTree/xgrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgTree/xpanel.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgTree/xwindow.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgTree/ajaxform.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgTree/pop.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgTree/message.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgTree/xtree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgTree/jquery.json-2.4.min.js"></script>


<style>
    .dashed {
        border-style: dashed;
        border-width: 1px;
    }
</style>


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="<%=request.getContextPath()%>/static/plugins/bootstrap/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/static/css/dashboard.css" rel="stylesheet">
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="<%=request.getContextPath()%>/static/plugins/bootstrap/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="<%=request.getContextPath()%>/static/plugins/bootstrap/assets/js/ie-emulation-modes-warning.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<!--<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>-->
<!--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>-->
<![endif]-->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script>
    window.jQuery || document.write('<script src="<%=request.getContextPath()%>/static/plugins/bootstrap/assets/js/vendor/jquery.min.js"><\/script>')
</script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="<%=request.getContextPath()%>/static/plugins/bootstrap/assets/js/vendor/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<%=request.getContextPath()%>/static/plugins/bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>














