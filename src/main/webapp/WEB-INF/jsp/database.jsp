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
    <h1 align="center">DATABASE  INFO</h1>
    <p></p>
    <div class="row">
        <div class="col-md-12">
            <div class="btn-group" role="group" aria-label="...">
                <button type="button" class="btn btn-primary" data-toggle="modal"
                        data-target="#addDatabase">Add
                </button>
                <button type="button" class="btn btn-danger" id="removeButton">Remove</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="database_table"
                   data-height="600"
                   data-click-to-select="true">
            </table>
        </div>
    </div>

</div>
<div class="modal fade" id="addDatabase" tabindex="-1" role="dialog" aria-labelledby="addDatabase">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <form id="databaseAddForm" action="doSaveDatabase" method="post">
                    <div class="form-group">
                        <label for="inputHostName" >Host</label>
                        <input type="text" name="host" id="inputHostName" class="form-control"
                               placeholder="Host">
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
                        <label for="inputService" >Service</label>
                        <input type="text" name="service" id="inputService" class="form-control"
                               placeholder="Service">
                    </div>
                    <div class="form-group">
                        <label for="inputPort" >Port</label>
                        <input type="text" name="port" id="inputPort" class="form-control"
                               placeholder="Port">
                    </div>
                    <div class="form-group">
                        <label for="inputUrl" >Url</label>
                        <input type="text" name="url" id="inputUrl" class="form-control"
                               placeholder="Url">
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-primary" value="Save"/>

                </form>
            </div>

        </div>
    </div>
</div>
<div class="modal fade" id="updateDatabase" tabindex="-1" role="dialog" aria-labelledby="updateDatabase">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form id="databaseUpdateForm" action="doUpdateDatabase" method="post">
                    <input type="hidden" name="id" id="database_id">
                    <div class="form-group">
                        <label for="updateHostName" >Host</label>
                        <input type="text" name="host" id="updateHostName" class="form-control"
                               placeholder="Host">
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
                        <label for="updateService" >Service</label>
                        <input type="text" name="service" id="updateService" class="form-control"
                               placeholder="Service">
                    </div>
                    <div class="form-group">
                        <label for="updatePort" >Path</label>
                        <input type="text" name="port" id="updatePort" class="form-control"
                               placeholder="port">
                    </div>
                    <div class="form-group">
                        <label for="updateUrl" >Script</label>
                        <input type="text" name="url" id="updateUrl" class="form-control"
                               placeholder="Url">
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
        $table = $('#database_table').bootstrapTable({
            method: 'post',
            url: '/environment/getDatabaseList',
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
                    field: 'host',
                    title: 'Host',
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
                    field: 'service',
                    title: 'Service',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'port',
                    title: 'Port',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'url',
                    title: 'Url',
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
            url: "/environment/getDatabaseList",
            dataType: "json",
            success: function (data) {
                console.log(data);
                $table.bootstrapTable({data: data});
            }
        });
        $('#removeButton').click(function () {
            var ids = getIdSelections();
            //console.log([0].id);
            var removeDatabases = $table.bootstrapTable('getSelections');
            var idsArr = '';
            for (var i = 0; i < removeDatabases.length; i++) {
                idsArr += (removeDatabases[i].id + ',');
            }
            console.log(idsArr);
            $table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            $.ajax({
                type: "post",
                url: "/environment/removeDatabases",
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
                url: "/environment/getDatabaseById",
                data: {databaseId: row.id},
                success: function (data) {
                    var jsonObj = $.parseJSON(data);
                    if(jsonObj != null){

                        $('#updateDatabase').modal('show');
                        $('#database_id').val(jsonObj.id);
                        $('#updateHostName').val(jsonObj.host);
                        $('#updateUser').val(jsonObj.user);
                        $('#updatePwd').val(jsonObj.password);
                        $('#updateService').val(jsonObj.service);
                        $('#updatePort').val(jsonObj.port);
                        $('#updateUrl').val(jsonObj.url);
                    }
                }
            });

        }
    };

</script>
</body>
</html>
