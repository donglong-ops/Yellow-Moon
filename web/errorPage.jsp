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
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Error page</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:700,900" rel="stylesheet">
        <link href="css/style.css" type="text/css" rel="stylesheet"  />
    </head>

    <body>
        <div id="notfound">
            <div class="notfound">
                <div class="notfound-404">
                    <h1>OOPS</h1></br>
                    <h2>Hmmm...something went wrong</h2>
                </div>
                <a href="HomeController">Home page</a>
            </div>
        </div>
    </body>
</html>
