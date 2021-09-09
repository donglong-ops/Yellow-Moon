<%-- 
    Document   : view
    Created on : Sep 12, 2021, 14:05:00 PM
    Author     : Dong Long
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <c:set var="cart" value="${sessionScope.CART}"></c:set>

            <c:if test="${not empty cart}">
                <h1>Your Cart Includes</h1>
            </c:if>

            <div class="form-row mb-2 mt-3">
                <div class="col-md-3">
                    <a href="search.jsp" class="btn btn-primary">Add More Items To Cart</a>
                </div>
            </div>


            <c:set var="confirmError" value="${requestScope.CONFIRM_ERROR}"/>
            <c:if test="${not empty confirmError}">
                <p class="alert alert-danger">
                    <c:forEach var="item" items="${confirmError}">
                        <font color="red">${item}<br/></font>
                    </c:forEach>
                </p>
            </c:if>

            <c:if test="${not empty cart}">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Description</th>
                            <th>Image</th>
                            <th>Amount</th>
                            <th>Total</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="mapCakes" value="${cart.getCake()}"/>
                        <jsp:useBean id="dao" class="longdh.cake.CakeDAO"/>
                        <c:forEach var="item" items="${cart.items}" varStatus="counter" >
                        <form action="DispatcherController">
                            <tr>
                                <c:set var="cakeId" value="${item.key}"/>
                                <c:set var="amount" value="${item.value}"/>
                                <c:set var="dto" value="${mapCakes.get(cakeId)}"/>

                                <td>${counter.count}</td>
                                <td>${dto.cakeName}</td>
                                <td>${cart.getPriceDisplay(cakeId)}</td>
                                <td>${dao.getCategoryName(dto.categoriID)}</td>
                                <td>${dto.description}</td>
                                <td><img src="${dto.imageLink}" width="160"/></td> 
                                <td><input class="form-control" type="text" name="txtAmount" value="${amount}" /></td>
                                <td>${cart.getPriceOfEachItemDisplay(cakeId)}</td>

                                <td>
                                    <input type="hidden" name="txtCakeId" value="${cakeId}" />
                                    <input class="btn btn-info" type="submit" name="btAction" value="Update Item" />
                                </td>
                                <td>
                                    <c:url var="deleteUrl" value="DispatcherController?btAction=DeleteItem">
                                        <c:param name="txtCakeId" value="${cakeId}"> </c:param>
                                    </c:url>
                                    <a class="btn btn-danger" href="${deleteUrl}" onclick="return confirm('Are you sure to remove item from cart?');">Delete</a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    <tr>
                        <td colspan="5"></td>
                        <td>Total Price: ${cart.totalPriceDisplay}</td>
                        <td colspan="4">                    
                            <form action="DispatcherController" method="POST">
                                <div class="form-row">
                                    <div class="col-md-6 mb-2">
                                        Payment: 
                                        <select class="form-control form-control-line" name="txtCategory">
                                            <option value="1">Cash</option>
                                            <option value="2">Momo</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6 mt-4">
                                        <input class="btn btn-success ml-5" type="submit" name="btAction" value="Confirm Booking" onclick="return confirm('Are you sure to Confirm?');"/>       
                                    </div>
                            </form> 
                        </td>
                    </tr>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty cart}">
                <img class="container border rounded mt-3 mr-5 mb-4" src="img/empty-cart2.png" width="350" height="400" />
            </c:if>
        </div>
    </body>
    <c:if test="${not empty cart}">
        <jsp:include page="footer.jsp"/>
    </c:if>

</html>
