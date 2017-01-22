<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fi">

<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="<c:url value="resources/vendor/jquery/dist/jquery.min.js"/> "></script>
    <%--<script src="<c:url value="resources/vendor/bootstrap/dist/js/bootstrap.js"/> "></script>--%>

    <link rel="icon" href="resources/assets/img/favicon.ico"/>
    <link rel="stylesheet" href="<c:url value="resources/vendor/bootstrap-css-only/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="resources/assets/css/index.css"/>"/>

    <title>Library</title>
</head>

<body ng-app="app">

<lms-navbar></lms-navbar>
<div ui-view></div>

<%--vendor--%>
<script src="resources/vendor/angular/angular.js"></script>
<script src="resources/vendor/angular-ui-router/release/angular-ui-router.js"></script>
<script src="resources/vendor/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<%--<script src="resources/vendor/angular-bootstrap/ui-bootstrap.js"></script>--%>

<%--scripts--%>
<script src="resources/app/js/main.js"></script>

<%--app--%>
<script src="resources/app/app.js"></script>
<script src="resources/app/app.config.js"></script>

<script src="resources/app/navbar/navbar.directive.js"></script>

<script src="resources/app/report/report.directive.js"></script>
<script src="resources/app/report/report.controller.js"></script>
<script src="resources/app/report/report.service.js"></script>

<script src="resources/app/queue/queue.directive.js"></script>

<script src="resources/app/book/book.directive.js"></script>

<script src="resources/app/reader/reader.directive.js"></script>
<script src="resources/app/reader/reader.controller.js"></script>
<script src="resources/app/reader/add-reader.controller.js"></script>

<script src="resources/app/contact/contact.directive.js"></script>

</body>
</html>

