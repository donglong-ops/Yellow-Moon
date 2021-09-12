<%-- 
    Document   : historyDetail
    Created on : Sep 15, 2021, 14:05:00 PM
    Author     : Dong Long
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Detail Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container mt-3 border bg-light p-4 mb-2"  >
            <div class="form-row">
                <h2 style="text-align: center">History Detail  </h2>
                <a class="btn btn-success text-center ml-4" href="search.jsp">  Go shopping</a>
            </div>
        </div>
        <c:set var="hisById" value="${requestScope.DETAILBYID}"/> 
        <c:set var="bookingDto" value="${requestScope.DTO}"/> 
        <c:if test="${not empty hisById}">
            <table class="container table table-bordered  p-3">
                <thead>
                    <tr>
                        <th>DateBuy</th>
                        <th>Total</th>
                        <th>Cake Name</th>
                        <th>Image</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td rowspan="10">${bookingDto.importedDate}</td>
                        <td rowspan="10">${bookingDto.total}</td>
                    </tr>
                    <c:forEach var="dto" items="${hisById}" varStatus="counter" >
                        <tr>
                            <td>${dto.cakeName}</td>
                            <td><img class="border rounded" src="${dto.imageLink}" width="150" height="120"/></td>
                            <td>${dto.amount}</td>         
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
