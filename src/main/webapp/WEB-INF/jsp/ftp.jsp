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
    <h1 align="center">FTP INFO</h1>
    <p></p>
    <div class="row">
        <div class="col-md-12">
            <div class="btn-group" role="group" aria-label="...">
                <button type="button" class="btn btn-primary" data-toggle="modal"
                        data-target="#addFtp">Add
                </button>
                <button type="button" class="btn btn-danger" id="removeButton">Remove</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="ftp_table"
                   data-height="500"
                   data-click-to-select="true">
            </table>
        </div>
    </div>

</div>
<div class="modal fade" id="addFtp" tabindex="-1" role="dialog" aria-labelledby="addFtp">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <form id="ftpAddForm" action="doSaveFtp" method="post">
                    <div class="form-group">
                        <label for="inputHostName" >Host Name</label>
                        <input type="text" name="hostname" id="inputHostName" class="form-control"
                               placeholder="Host Name">
                    </div>
                    <div class="form-group">
                        <label for="inputUser" >User</label>
                        <input type="text" name="user" id="inputUser" class="form-control"
                               placeholder="Login User Name">
                    </div>
                    <div class="form-group">
                        <label for="inputPwd" >Password</label>
                        <input type="text" name="password" id="inputPwd" class="form-control"
                               placeholder="Login Password">
                    </div>
                    <div class="form-group">
                        <label for="inputPort" >Port</label>
                        <input type="text" name="port" id="inputPort" class="form-control"
                               placeholder="Port">
                    </div>
                    <div class="form-group">
                        <label for="inputPath" >Path</label>
                        <input type="text" name="path" id="inputPath" class="form-control"
                               placeholder="Path">
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
<div class="modal fade" id="updateFtp" tabindex="-1" role="dialog" aria-labelledby="updateFtp">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form id="ftpUpdateForm" action="doUpdateFtp" method="post">
                    <input type="hidden" name="id" id="ftp_id">
                    <div class="form-group">
                        <label for="updateHostName" >Host Name</label>
                        <input type="text" name="hostname" id="updateHostName" class="form-control"
                               placeholder="Host Name">
                    </div>
                    <div class="form-group">
                        <label for="updateUser" >User</label>
                        <input type="text" name="user" id="updateUser" class="form-control"
                               placeholder="Login User Name">
                    </div>
                    <div class="form-group">
                        <label for="updatePwd" >Password</label>
                        <input type="text" name="password" id="updatePwd" class="form-control"
                               placeholder="Login Password">
                    </div>
                    <div class="form-group">
                        <label for="updatePort" >Port</label>
                        <input type="text" name="port" id="updatePort" class="form-control"
                               placeholder="Port">
                    </div>
                    <div class="form-group">
                        <label for="updatePath" >Port</label>
                        <input type="text" name="path" id="updatePath" class="form-control"
                               placeholder="Path">
                    </div>
                    <div class="form-group">
                        <label for="updateRemark" >Remark</label>
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
        $table = $('#ftp_table').bootstrapTable({
            method: 'post',
            url: '/environment/getFtpList',
            cache: false,
            striped: true,
            search: true,
            showRefresh: true,
            showToggle: true,
            clickToSelect: true,
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
                    field: 'hostName',
                    title: 'Host Name',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    field: 'user',
                    title: 'User',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    field: 'password',
                    title: 'Password',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    field: 'port',
                    title: 'Port',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'path',
                    title: 'Path',
                    align: 'center',
                    valign: 'middle'
                }, {
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
            url: "/environment/getFtpList",
            dataType: "json",
            success: function (data) {
                console.log(data);
                $table.bootstrapTable({data: data});
            }
        });
        $('#removeButton').click(function () {
            var ids = getIdSelections();
            //console.log([0].id);
            var removeFtps = $table.bootstrapTable('getSelections');
            var idsArr = '';
            for (var i = 0; i < removeFtps.length; i++) {
                idsArr += (removeFtps[i].id + ',');
            }
            console.log(idsArr);
            $table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            $.ajax({
                type: "post",
                url: "/environment/removeFtps",
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
            '<span id="update" class="glyphicon glyphicon-wrench" aria-hidden="true" style="cursor: hand"></span>'
        ].join('');
    }
    window.operateEvents = {
        'click #update': function (e, value, row, index) {
            $.ajax({
                type: "post",
                url: "/environment/getFtpById",
                data: {ftpId: row.id},
                success: function (data) {
                    var jsonObj = $.parseJSON(data);
                    if(jsonObj != null){

                        $('#updateFtp').modal('show');
                        $('#ftp_id').val(jsonObj.id);
                        $('#updateHostName').val(jsonObj.hostName);
                        $('#updateUser').val(jsonObj.user);
                        $('#updatePwd').val(jsonObj.password);
                        $('#updatePort').val(jsonObj.port);
                        $('#updatePath').val(jsonObj.path);
                        $('#updateRemark').val(jsonObj.remark);
                    }
                }
            });

        }
    };

</script>
</body>
</html>
