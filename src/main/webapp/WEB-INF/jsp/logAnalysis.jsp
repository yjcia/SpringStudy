<%--
  Created by IntelliJ IDEA.
  User: YanJun
  Date: 2016/2/25
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap-table/dist/bootstrap-table.css" rel="stylesheet">
    <link href="bootstrapValidator/dist/css/bootstrapValidator.css" rel="stylesheet">
    <link href="highlight/styles/agate.css" rel="stylesheet" >
    <script type="text/javascript" src="bootstrap/js/jquery.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="bootstrap-table/dist/bootstrap-table.js"></script>

    <script type="text/javascript" src="highlight/highlight.pack.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<jsp:include page="toolbar.jsp"/>
<div class="container" style="margin-top: 50px">
    <h1 align="center">LOG ANALYSIS INFO</h1>
    <p></p>
    <div class="row">
        <div class="col-md-12">
            <form class="form-inline">
                <div class="form-group">
                    <label for="logPath">Path</label>
                    <input type="text" class="form-control" name="logPath" id="logPath" placeholder="Log Path">
                </div>
                <button type="button" class="btn btn-default" onclick="startAnalysisSql()">Start</button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="log_table"
                   data-height="600"
                   data-click-to-select="true">
            </table>
        </div>
    </div>
</div>

<script>
    function startAnalysisSql(){
        $.ajax({
            type: "post",
            url: "/environment/getLogSqlExecTimeList",
            dataType: "json",
            data:{
                path:$('#logPath').val()
            },
            success: function (result) {
                console.log(result);
                $('#log_table').bootstrapTable({
                    //method: 'post',
                    //url: '/environment/getLogSqlExecTimeList',
                    cache: false,
                    striped: true,
                    search: true,
                    showRefresh: true,
                    showToggle: true,
                    pagination:true,
                    data:result,
                    columns: [
                        {
                            field: 'execSelectSql',
                            title: 'Exec Sql',
                            formatter:execSqlFormatter
                        },
                        {
                            field: 'execTime',
                            title: 'Exec Time (MS)',
                            formatter:execTimeFormatter,
                            sortable:true
                        }
                    ]
                });

            }
        });
    }
    function execTimeFormatter(value,row,index){
        return [
            '<div class="progress">' +
            '<div class="progress-bar progress-bar-striped active progress-bar-info " role="progressbar" ' +
            'aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:'+ row.execTime/10 +';">' +
            row.execTime+'MS</div></div>'
        ].join('');
    }

    function execSqlFormatter(value,row,index){

        return [
            '<pre><code class="sql">'+ row.execSelectSql +'</code></pre>'
        ].join('');
    }
</script>
</body>
</html>
