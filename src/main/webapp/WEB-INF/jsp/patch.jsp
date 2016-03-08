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
    <h1 align="center">PATCH  INFO</h1>
    <p></p>
    <div class="row">
        <div class="col-md-12">
            <div class="btn-group" role="group" aria-label="...">
                <button type="button" class="btn btn-primary" data-toggle="modal"
                        data-target="#addPatch">Add
                </button>
                <button type="button" class="btn btn-danger" id="removeButton">Remove</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="patch_table"
                   data-height="600"
                   data-click-to-select="true">
            </table>
        </div>
    </div>

</div>
<div class="modal fade" id="addPatch" tabindex="-1" role="dialog" aria-labelledby="addPatch">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <form id="patchAddForm" action="doSavePatch" method="post">
                    <div class="form-group">
                        <label for="inputPatchName" >Patch Name</label>
                        <input type="text" name="patchname" id="inputPatchName" class="form-control"
                               placeholder="Patch Name">
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
                        <label for="inputScript" >Script</label>
                        <input type="text" name="script" id="inputScript" class="form-control"
                               placeholder="Script">
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-primary" value="Save"/>

                </form>
            </div>

        </div>
    </div>
</div>
<div class="modal fade" id="updatePatch" tabindex="-1" role="dialog" aria-labelledby="updatePatch">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form id="patchUpdateForm" action="doUpdatePatch" method="post">
                    <input type="hidden" name="id" id="patch_id">
                    <div class="form-group">
                        <label for="updatePatchName" >Patch Name</label>
                        <input type="text" name="patchname" id="updatePatchName" class="form-control"
                               placeholder="Patch Name">
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
                        <label for="updateScript" >Script</label>
                        <input type="text" name="script" id="updateScript" class="form-control"
                               placeholder="Script">
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
        $table = $('#patch_table').bootstrapTable({
            method: 'post',
            url: '/environment/getPatchList',
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
                    field: 'patch_name',
                    title: 'Patch Name',
                    align: 'center',
                    valign: 'middle',
                    sortable: true
                }, {
                    field: 'user',
                    title: 'user',
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
                    field: 'script',
                    title: 'Script',
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
            url: "/environment/getPatchList",
            dataType: "json",
            success: function (data) {
                console.log(data);
                $table.bootstrapTable({data: data});
            }
        });
        $('#removeButton').click(function () {
            var ids = getIdSelections();
            //console.log([0].id);
            var removePatchs = $table.bootstrapTable('getSelections');
            var idsArr = '';
            for (var i = 0; i < removePatchs.length; i++) {
                idsArr += (removePatchs[i].id + ',');
            }
            console.log(idsArr);
            $table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            $.ajax({
                type: "post",
                url: "/environment/removePatchs",
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
            '<button class="update" type="button" class="btn btn-info">Update</button>'
        ].join('');
    }
    window.operateEvents = {
        'click .update': function (e, value, row, index) {
            $.ajax({
                type: "post",
                url: "/environment/getPatchById",
                data: {patchId: row.id},
                success: function (data) {
                    var jsonObj = $.parseJSON(data);
                    if(jsonObj != null){

                        $('#updatePatch').modal('show');
                        $('#patch_id').val(jsonObj.id);
                        $('#updatePatchName').val(jsonObj.patch_name);
                        $('#updateUser').val(jsonObj.user);
                        $('#updatePwd').val(jsonObj.password);
                        $('#updatePath').val(jsonObj.path);
                        $('#updateScript').val(jsonObj.script);
                        $('#updateHost').val(jsonObj.host);
                    }
                }
            });

        }
    };

</script>
</body>
</html>
