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
    <script type="text/javascript" src="bootstrap/js/jquery.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="bootstrap-table/dist/bootstrap-table.js"></script>
</head>
<body>
<jsp:include page="toolbar.jsp"/>
<div class="container" style="margin-top: 50px">
    <h1 align="center">STORAGE INFO</h1>
    <p></p>
    <div class="row">

    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="storage_table"
                   data-height="600"
                   data-click-to-select="true">
            </table>
        </div>
    </div>
</div>

<script>

    var ftpData = null;
    var $table = null;
    $(function () {
        $table = $('#storage_table').bootstrapTable({
            method: 'post',
            url: '/environment/getStorageList',
            cache: false,
            striped: true,
            search: true,
            showRefresh: true,
            showToggle: true,
            clickToSelect: true,
            columns: [
                 {
                    field: 'hostName',
                    title: 'Host Name',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    field: 'fileSystem',
                    title: 'FileSyatem',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    field: 'size',
                    title: 'Size',
                    align: 'center',
                    valign: 'middle'

                }, {
                    field: 'used',
                    title: 'Used',
                    align: 'center',
                    valign: 'middle'

                }, {
                    field: 'avail',
                    title: 'Avail',
                    align: 'center',
                    valign: 'middle'

                }, {
                    field: 'use',
                    title: 'Use (%)',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    formatter:usedFormatter
                },
                {
                    field: 'mounted',
                    title: 'Mounted',
                    align: 'center',
                    valign: 'middle'

                }, {
                    field: 'operate',
                    title: 'Operate',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                }
            ]
        });
        $.ajax({
            type: "post",
            url: "/environment/getStorageList",
            dataType: "json",
            success: function (data) {
                console.log(data);
                $table.bootstrapTable({data: data});
            }
        });
    });

    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }

    function operateFormatter(value, row, index) {
        return [
            '<span id="update" class="glyphicon glyphicon-wrench" aria-hidden="true" style="cursor: hand"></span>'
        ].join('');
    }
    function usedFormatter(value,row,index){
        var barStyleClass = "progress-bar-success";
        if(row.use>=0 && row.use<=50){
            barStyleClass = "progress-bar-info";
        }else if(row.use>50 && row.use<=80){
            barStyleClass = "progress-bar-warning";
        }else if(row.use>80 && row.use<=100){
            barStyleClass = "progress-bar-danger";
        }
        return [
            '<div class="progress">' +
            '<div class="progress-bar progress-bar-striped active '+ barStyleClass + '" role="progressbar" ' +
            'aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:'+row.use+';">' +
             row.use+'%</div></div>'
        ].join('');
    }
    window.operateEvents = {
        'click #update': function (e, value, row, index) {
        }
    };

</script>
</body>
</html>
