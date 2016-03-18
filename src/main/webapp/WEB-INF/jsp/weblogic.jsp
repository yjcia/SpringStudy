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
    <h1 align="center">WEBLOGIC  INFO</h1>
    <p></p>
    <div class="row">
        <div class="col-md-12">
            <div class="btn-group" role="group" aria-label="...">
                <button type="button" class="btn btn-primary" data-toggle="modal"
                        data-target="#addWeblogic">Add
                </button>
                <button type="button" class="btn btn-danger" id="removeButton">Remove</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="weblogic_table"
                   data-height="600"
                   data-click-to-select="true">
            </table>
        </div>
    </div>

</div>
<div class="modal fade" id="addWeblogic" tabindex="-1" role="dialog" aria-labelledby="addWeblogic">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <form id="weblogicAddForm" action="doSaveWeblogic" method="post">
                    <div class="form-group">
                        <label for="inputWeblogicName" >weblogic Name</label>
                        <input type="text" name="name" id="inputWeblogicName" class="form-control"
                               placeholder="Weblogic Name">
                    </div>
                    <div class="form-group">
                        <label for="inputHost" >Host</label>
                        <input type="text" name="host" id="inputHost" class="form-control"
                               placeholder="Host">
                    </div>
                    <div class="form-group">
                        <label for="inputPath" >Path</label>
                        <input type="text" name="path" id="inputPath" class="form-control"
                               placeholder="Path">
                    </div>
                    <div class="form-group">
                        <label for="inputRsPath" >RS Path</label>
                        <input type="text" name="rspath" id="inputRsPath" class="form-control"
                               placeholder="RS Path">
                    </div>
                    <div class="form-group">
                        <label for="inputRemark" >Remark</label>
                        <input type="text" name="remark" id="inputRemark" class="form-control"
                               placeholder="Remark">
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-primary" value="Save"/>

                </form>
            </div>

        </div>
    </div>
</div>
<div class="modal fade" id="updateWeblogic" tabindex="-1" role="dialog" aria-labelledby="updateWeblogic">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form id="weblogicUpdateForm" action="doUpdateWeblogic" method="post">
                    <input type="hidden" name="id" id="weblogic_id">
                    <div class="form-group">
                        <label for="updateWeblogicName" >weblogic Name</label>
                        <input type="text" name="name" id="updateWeblogicName" class="form-control"
                               placeholder="Weblogic Name">
                    </div>
                    <div class="form-group">
                        <label for="updateHost" >Host</label>
                        <input type="text" name="host" id="updateHost" class="form-control"
                               placeholder="Host">
                    </div>
                    <div class="form-group">
                        <label for="updatePath" >Path</label>
                        <input type="text" name="path" id="updatePath" class="form-control"
                               placeholder="Path">
                    </div>
                    <div class="form-group">
                        <label for="updateRsPath" >Path</label>
                        <input type="text" name="rspath" id="updateRsPath" class="form-control"
                               placeholder="RS Path">
                    </div>
                    <div class="form-group">
                        <label for="updateRemark" >Script</label>
                        <input type="text" name="remark" id="updateRemark" class="form-control"
                               placeholder="Remark">
                    </div>

                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-primary" value="Update"/>

                </form>
            </div>

        </div>
    </div>
</div>
<script>

    var ftpData = null;
    var $table = null;
    $(function () {
        $table = $('#weblogic_table').bootstrapTable({
            method: 'post',
            url: '/environment/getWeblogicList',
            cache: false,
            striped: true,
            search: true,
            showRefresh: true,
            showToggle: true,
            clickToSelect: true,
            pagination:true,

            columns: [
                {
                    field: 'state',
                    checkbox: true,
                    align: 'center',
                    valign: 'middle',
                    visible: true
                }, {
                    field: 'id',
                    title: 'id',
                    align: 'center',
                    valign: 'middle',
                    visible: true
                }, {
                    field: 'name',
                    title: 'weblogic Name',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    field: 'host',
                    title: 'Host',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'path',
                    title: 'Path',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'rspath',
                    title: 'RS Path',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'remark',
                    title: 'Remark',
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
            url: "/environment/getWeblogicList",
            dataType: "json",
            success: function (data) {
                console.log(data);
                $table.bootstrapTable({data: data});
            }
        });
        $('#removeButton').click(function () {
            var ids = getIdSelections();
            //console.log([0].id);
            var removeWeblogics = $table.bootstrapTable('getSelections');
            var idsArr = '';
            for (var i = 0; i < removeWeblogics.length; i++) {
                idsArr += (removeWeblogics[i].id + ',');
            }
            console.log(idsArr);
            $table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            $.ajax({
                type: "post",
                url: "/environment/removeWeblogics",
                data: {deleteIds: idsArr},
                success: function (data) {

                }
            });
            //$('#removeButton').prop('disabled', true);
        });

    });

    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }

    function operateFormatter(value, row, index) {
        return [
            '<span id="update" class="glyphicon glyphicon-wrench" aria-hidden="true" style="cursor: hand"></span>&nbsp&nbsp',
            '<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>'
        ].join('');
    }
    window.operateEvents = {
        'click #update': function (e, value, row, index) {
            $.ajax({
                type: "post",
                url: "/environment/getWeblogicById",
                data: {weblogicId: row.id},
                success: function (data) {
                    var jsonObj = $.parseJSON(data);
                    if(jsonObj != null){

                        $('#updateWeblogic').modal('show');
                        $('#weblogic_id').val(jsonObj.id);
                        $('#updateWeblogicName').val(jsonObj.name);
                        $('#updatePath').val(jsonObj.path);
                        $('#updateRsPath').val(jsonObj.rspath);
                        $('#updateRemark').val(jsonObj.remark);
                        $('#updateHost').val(jsonObj.host);
                    }
                }
            });

        }
    };

</script>
</body>
</html>
