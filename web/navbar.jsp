<%-- 
    Document   : navbar
    Created on : Sep 6, 2021, 10:18:00 AM
    Author     : Dong Long
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <nav class="container navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Yellow Moon Shop</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse d-flex justify-content-between" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link btn btn-primary text-white" href="search.jsp">Search <span class="sr-only">(current)</span></a>
                    </li>
                </ul>
                <div>
                    <c:if test="${not empty sessionScope.USER}">
                        <font color="red">Welcome, ${sessionScope.USER.fullname}</font>
                        <a class="btn border btn-light" href="DispatcherController?btAction=LogOut">LogOut</a>
                    </c:if>
                    <c:if test="${sessionScope.USER.role.name == 'User'}">
                        <a class="btn btn-info" href="DispatcherController?btAction=ViewCart">View Cart</a>
                    </c:if>
                    <c:if test="${sessionScope.USER.role.name == 'User'}">
                        <a class="btn btn-info" href="DispatcherController?btAction=History">History</a>
                    </c:if>
                    <c:if test="${sessionScope.USER.role.name == 'Admin'}">
                        <a class="btn-primary btn" href="DispatcherController?btAction=Manager">Manager Cakes</a>        
                    </c:if>
                    <c:if test="${sessionScope.USER.role.name == 'Admin'}">
                        <a class="btn-primary btn" href="createCake.html">Create New Cake</a>        
                    </c:if>
                    <c:if test="${empty sessionScope.USER}">
                        <a class="btn btn-success" href="login.jsp">Login here!!!</a>
                    </c:if>

                </div>
            </div>
        </nav>
    </body>
</html>
