<%-- 
    Document   : history
    Created on : Sep 12, 2021, 14:05:00 PM
    Author     : Dong Long
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container mt-3 border bg-light p-4 mb-5"  >
            <Form action="DispatcherController">
                <div class="form-row" >          
                    <div class="col-md-3" >
                        <h1>Your History</h1>
                    </div>
                    <div class="col-md-3 mt-3">
                        <input placeholder="From Date" type="date" class="form-control" name="txtFromDate" value="${param.txtFromDate}" />     
                    </div> 
                    <div class="col-md-3 mt-3">
                        <input placeholder="To Date" type="date" class="form-control" name="txtToDate" value="${param.txtToDate}" />        
                    </div> 
                    <div class="col-md-2 mt-3">
                        <input class="col-md- btn btn-primary px-5" type="submit" name="btAction" value="Search History" />
                    </div> 
                    <c:if test="${not empty requestScope.DATEERR}">
                        <input class="col-md-3" style="color: red; margin-left: 35%" value="${requestScope.DATEERR}">
                    </c:if>
                </div>
            </Form>
            <c:set var="historyList" value="${requestScope.ALLHISTORY}"/>
        </div>
        <c:if test="${not empty historyList}">
            <table class="container table table-bordered  p-4">
                <thead>
                    <tr>
                        <th>Booking Code</th>
                        <th>DateBuy</th>
                        <th>Total</th>
                        <th>Payment</th>
                        <th><a class="btn btn-success text-center" href="search.jsp">Let's Go shopping</a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${historyList}" varStatus="counter" >
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.importedDate}</td>
                            <td>${item.total}</td>
                            <td>${item.payment}</td> 
                            <td>
                                <c:url var="viewMore" value="DispatcherController?btAction=ViewDetail">
                                    <c:param name="ID" value="${item.id}"> </c:param>
                                </c:url>
                                <a class="btn btn-success" href="${viewMore}">View Detail</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty historyList}">
            <h5 class="container alert alert-danger text-center">Not Have History!!!!!</h5>
        </c:if>
    </body>
    <c:if test="${not empty historyList}">
        <jsp:include page="footer.jsp"/>
    </c:if>
</html>
