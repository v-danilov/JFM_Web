<%--
  Created by IntelliJ IDEA.
  User: Bounc
  Date: 03.08.2017
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
<link rel="stylesheet" href="dist/themes/default/style.min.css"/>
<link rel="stylesheet"
      href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
<link rel="stylesheet"
      href="/resources/css/mainpage.css"/>
<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

<!-- JS Tree scripts-->
<script src="dist/jstree.min.js"></script>


<!-- Main page scripts-->
<script src="resources/js/mainpage.js"></script>

<head>
    <title>Tree browser</title>
</head>
<body>
<div class="container">
    <div class="row top-buffer">
        <div id="jstree">
            <!-- Tree -->
            <ul>
                <li id="1">Root node 1</li>
            </ul>
        </div>
    </div>
    <div class="row top-buffer">
        <button type="button" onclick="updateTree()" class="btn btn-primary"> Sync &#8635;</button>
    </div>
    <!--div class="row top-buffer">
        <a href="https://www.jstree.com/docs/json/">jsTree docs</a>
    </div-->
</div>
</body>
</html>
