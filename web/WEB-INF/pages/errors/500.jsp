<%--
  Created by IntelliJ IDEA.
  User: ksenia
  Date: 10.08.17
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" isErrorPage="true" %>
<html>
<link rel="stylesheet"
      href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<head>
    <title>Server error</title>
</head>
<body>
<div class="container">
<div class="center-block text-center" style="min-width: 100%; min-height: 100%;">
        <div class="row">
            <h3 style="font-size: 200%" align="center">There is something strange inside me!</h3>
            <div class="col-md-4 col-md-offset-4">
                <img class="img-responsive" src="/resources/hm500.jpg" />
            </div>
        </div>
        <div class="row">
            <h1 style="font-size: 400%" align="center"><small>A monster known as</small> 500 <small> is inside me!</small></h1>
            <h3 style="font-size: 400%" align="center"><small>You can see what he wants below &#10549;</small></h3>
        </div>


</div>
    <h3>Stack trace:</h3>
    <div>
        <jsp:scriptlet>
                            exception.printStackTrace(new java.io.PrintWriter(out));
            </jsp:scriptlet>
    </div>
</div>
</body>
</html>
