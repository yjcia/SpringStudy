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
    <script type="text/javascript" src="bootstrap/js/jquery.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="bootstrap-table/dist/bootstrap-table.js"></script>
    <script type="text/javascript" src="echarts/echarts.common.min.js"></script>
</head>
<body>
<jsp:include page="toolbar.jsp"/>
<div class="container" style="margin-top: 50px">
    <h1 align="center">SERVER  INFO</h1>
    <p></p>
    <div class="row">
        <div class="col-md-12">
            <div id="memoryInfo" style="width: 100%;height:300px;"></div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div id="cpuInfo" style="width: 100%;height:300px;"></div>
        </div>
    </div>
</div>

<script>
    var memWebsocket = null;
    var cpuWebsocket = null;
    $(function () {
        memWebsocket = new WebSocket('ws://10.104.46.238:8081/environment/getServerMemoryInfo');
        memWebsocket.onmessage = function(event) {
            var myChart = echarts.init(document.getElementById("memoryInfo"));
            var option = JSON.parse(event.data);
            myChart.setOption(option);
        };

        cpuWebsocket = new WebSocket('ws://10.104.46.238:8081/environment/getServerCpuInfo');
        cpuWebsocket.onmessage = function(event) {
            var myChart = echarts.init(document.getElementById("cpuInfo"));
            var option = JSON.parse(event.data);
            myChart.setOption(option);
        };
    });



</script>
</body>
</html>
