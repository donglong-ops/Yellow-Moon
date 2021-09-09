<%-- 
    Document   : errorPage
    Created on : Sep 7, 2021, 22:45:00 PM
    Author     : Dong Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <h1 class="container btn btn-danger mt-5 ml-5"> Error Page!!</h1>
        <h1 class="container btn btn-success mt-5 ml-5"> <a href="DispatcherController?btAction=null"> Search Cake </a></h1>

    </body>
</html>
