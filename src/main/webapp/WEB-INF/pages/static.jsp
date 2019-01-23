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

<!-- Bootstrap core CSS -->
<link href="<%=request.getContextPath()%>/static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- bootstrap-table CSS -->
<link href="<%=request.getContextPath()%>/static/plugins/bootstrapTable/bootstrap-table.min.css" rel="stylesheet">

<!-- orgChart CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/plugins/orgChart/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/plugins/orgChart/css/jquery.orgchart.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/plugins/orgChart/css/style.css">

<!-- zTree CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/plugins/zTree/css/metroStyle/metroStyle.css" type="text/css">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="<%=request.getContextPath()%>/static/plugins/bootstrap/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/static/css/dashboard.css" rel="stylesheet">
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="<%=request.getContextPath()%>/static/plugins/bootstrap/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="<%=request.getContextPath()%>/static/plugins/bootstrap/assets/js/ie-emulation-modes-warning.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<link href="<%=request.getContextPath()%>/static/plugins/loading/load.css" rel="stylesheet">


<script src="<%=request.getContextPath()%>/static/plugins/jquery/jquery.min.js"></script>

<!--bootStrap JS -->
<script src="<%=request.getContextPath()%>/static/plugins/bootstrap/js/bootstrap.min.js"></script>

<!-- bootstrap-table JS -->
<script src="<%=request.getContextPath()%>/static/plugins/bootstrapTable/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath()%>/static/plugins/bootstrapTable/locale/bootstrap-table-zh-CN.min.js"></script>

<!-- orgchart JS -->
<!-- the following reference is specific for IE -->
<script src="https://cdn.bootcss.com/es6-promise/4.1.1/es6-promise.auto.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgChart/js/html2canvas.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgChart/js/jspdf.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/orgChart/js/jquery.orgchart.js"></script>


<!-- zTree JS -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/zTree/js/jquery.ztree.exedit.js"></script>


<!-- layer -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/plugins/layer/layer.js"></script>


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

<script src="<%=request.getContextPath()%>/static/plugins/loading/load.js"></script>

<style>
    .dashed {
        border-style: dashed;
        border-width: 1px;
    }
</style>