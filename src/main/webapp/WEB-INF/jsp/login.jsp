<%--
  Created by IntelliJ IDEA.
  User: YanJun
  Date: 2016/2/1
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/signin.css" rel="stylesheet">
    <link href="bootstrapValidator/dist/css/bootstrapValidator.css" rel="stylesheet">
    <script type="text/javascript" src="bootstrap/js/jquery.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="bootstrapValidator/dist/js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="bootstrap/js/loginValidator.js"></script>
</head>
<body>
    <div class="container">

        <form class="form-signin" action="doLogin" method="post" id="userLoginForm">
            <div class="form-group">
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="email" name="email" id="inputEmail" class="form-control"
                       placeholder="Email address" autofocus>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="password" id="inputPassword" class="form-control"
                       placeholder="Password">
            </div>
            <div class="form-group">
                <div class="btn-group btn-group-justified" role="group">
                    <div class="btn-group" role="group">
                        <button class="btn btn-primary" type="submit" name="submit">Sign in</button>
                    </div>
                    <div class="btn-group" role="group">
                        <button class="btn btn-info" type="button">Register</button>
                    </div>
                </div>
            </div>
        </form>

    </div>
</body>
</html>
