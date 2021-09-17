<%-- 
    Document   : confirm
    Created on : Sep 10, 2021, 14:05:00 PM
    Author     : Dong Long
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container mt-3 border bg-light p-4" style="width: 500px" >
            <font color="red">Thank you for Buy In Our Shop! Your confirm has completed</font>
            <a class="btn btn-success" href="HomeController">Go shopping</a>
            <c:set var="cart" value="${requestScope.CART}"/>
            <c:set var="mapCakes" value="${cart.getCake()}"/>
        </div><br>
        <c:if test="${not empty mapCakes}">
            <table class="container table table-bordered">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>CakeName</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Image</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <jsp:useBean id="dao" class="longdh.cake.CakeDAO"/>

                    <c:forEach var="item" items="${cart.items}" varStatus="counter" >
                        <tr>
                            <c:set var="cakeId" value="${item.key}"/>
                            <c:set var="amount" value="${item.value}"></c:set>
                            <c:set var="dto" value="${mapCakes.get(cakeId)}" ></c:set>

                                <td>${counter.count}</td>
                            <td>${dto.cakeName}</td>
                            <td>${cart.getPriceDisplay(cakeId)}</td>
                            <td>${dao.getCategoryName(dto.categoriID)}</td>
                            <td>${dto.description}</td>
                            <td>${amount}</td>
                            <td><img src="${dto.imageLink}" width="150"/></td> 
                            <td>${cart.getPriceOfEachItemDisplay(cakeId)}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <c:set var="guessInfo" value="${requestScope.GUESSINFO}"/>
                        <td colspan="5">
                           
                            <h3 class="text-center" style="color: red">Your Information</h3>
                            <div class="form-row">
                                <div class="col-md-4">
                                    Your Name*: 
                                    <input class="form-control" type="text" disabled value="${guessInfo.name}"/> 
                                </div>
                                <div class="col-md-3">
                                    Phone*: 
                                    <input class="form-control" type="number" disabled value="${guessInfo.phone}"/> 
                                </div>
                                <div class="col-md-5">
                                    Address Ship*: 
                                    <input class="form-control" disabled type="text" value="${guessInfo.address}"/> 
                                </div>
                            </div>
                                
                        </td>
                        <td>Total Price: ${cart.totalPriceDisplay}</td>
                        <td colspan="2"> Payment by: CASH</td>
                    </tr>
                </tbody>
            </table>
        </c:if>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
