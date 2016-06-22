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
                        <label>Host Name</label>
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
                    <input id="ftpSave" type="submit" class="btn btn-primary" value="Save"/>

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
                    <input type="submit" class="btn btn-primary" value="Update" id="updateFtpBtn"/>

                </form>
            </div>

        </div>
    </div>
</div>
<div class="modal fade" id="uploadFtp" tabindex="-1" role="dialog" aria-labelledby="uploadFtp">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form id="ftpUploadForm" action="doUploadFtp" enctype="multipart/form-data"
                      method="post" target="uploadf">
                    <input type="hidden" name="id" id="upload_ftp_id">
                    <input type="hidden" name="hostname" id="upload_ftp_hostname"/>
                    <input type="hidden" name="user" id="upload_ftp_user"/>
                    <input type="hidden" name="password" id="upload_ftp_password"/>
                    <input type="hidden" name="port" id="upload_ftp_port"/>
                    <input type="hidden" name="path" id="upload_ftp_path"/>
                    <input type="file" name="uploadFile" id="uploadFile"><br/>
                    <div class="form-group">
                        <div class="progress progress-success progress-striped">
                            <div id='proBar'
                                 class="progress-bar progress-bar-striped active progress-bar-info"
                                 role="progressbar" style="width:0%;">

                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-default" onclick="closeUpload()" data-dismiss="modal">Close</button>
                    <input type="button" class="btn btn-primary" value="Upload" id="uploadFtpBtn"/>

                </form>
                <iframe name="uploadf" style="display:none"></iframe>
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
        });
        $('#uploadFtpBtn').click(
                function(){
                    $('#ftpUploadForm').submit();
                    var eventFun = function(){
                        $.ajax({
                            type: 'get',
                            url: '/environment/getProgress',
                            data: {},
                            dataType: 'json',
                            success : function(data){
                                $('#proBar').css('width',data.rate+''+'%');
                                $('#proBar').empty();
                                $('#proBar').append(data.show);
                                if(data.rate == 100){
                                    window.clearInterval(intId);
                                }
                            }});};
                    var intId = window.setInterval(eventFun,500);
                });

    });

    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }

    function operateFormatter(value, row, index) {
        return [
            '<span id="update" class="glyphicon glyphicon-wrench" aria-hidden="true" style="cursor: hand"></span>' +
            '&nbsp;&nbsp;',
            '<span id="upload" class="glyphicon glyphicon-upload" aria-hidden="true" style="cursor: hand"></span>'
        ].join('');
    }
    function closeUpload(){
        $('#uploadFtp').modal('hide');
        $('#upload_ftp_id').val('');
        $('#uploadFile').val('');
        $('#proBar').attr("style","width:0%;");

    }
    window.operateEvents = {
        'click #update': function (e, value, row, index) {
        $('#updateFtp').modal('show');
        $('#ftp_id').val(row.id);
        $('#updateHostName').val(row.hostName);
        $('#updateUser').val(row.user);
        $('#updatePwd').val(row.password);
        $('#updatePort').val(row.port);
        $('#updatePath').val(row.path);
        $('#updateRemark').val(row.remark);

        },'click #upload': function (e, value, row, index) {
            $('#uploadFtp').modal('show');
            $('#upload_ftp_id').val(row.id);
            $('#upload_ftp_hostname').val(row.hostName);
            $('#upload_ftp_user').val(row.user);
            $('#upload_ftp_password').val(row.password);
            $('#upload_ftp_port').val(row.port);
            $('#upload_ftp_path').val(row.path);
        }
    };

</script>
</body>
</html>
