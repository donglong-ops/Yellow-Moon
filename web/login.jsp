<%-- 
    Document   : login
    Created on : Sep 7, 2021, 14:05:00 PM
    Author     : Dong Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <title>Login Page</title>
    </head>
    <body>
        <div class="container mt-5 border bg-light p-4" style="width: 400px" >
            <h1 class="text-center">Login Page</h1>
            <form action="DispatcherController" method="POST">
                Email: <input class="form-control" type="text" name="txtUsername" value="${param.txtUsername}" /></br>
                Password: <input class="form-control" type="password" name="txtPassword" value="" /></br>
                <div class="form-row" style="margin-left: 30%">
                    <input class="btn btn-success mt-2 px-4"  type="submit" name="btAction" value="Login" />
                    <input class="btn btn-light border mt-2 px-4 ml-2" type="reset" value="Reset" />
                </div>
            </form>
                
            <c:set var="error" value="${requestScope.LOGINFAIL}"/>
            <c:if test="${not empty error}" >
                <div class="container mt-5 border bg-light p-4" style="width: 350px" >
                    <h2 class="text-danger">${error}</h2>
                </div>
            </c:if>
            <a style="margin-left: 50%">Or</a> <br/>
            <a class="btn btn-primary mt-2" href="DispatcherController?btAction=LoginGoogle" style="margin-left: 30%">
                <i class="fa fa-google" aria-hidden="true"></i>
                Login With Google
            </a> <br/>
        </div>
    </body>
</html>
