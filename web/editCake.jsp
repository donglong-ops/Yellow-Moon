<%-- 
    Document   : editCake
    Created on : Sep 16, 2021, 10:47:00 AM
    Author     : Dong Long
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Cake</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <jsp:include page="navbar.jsp"/>
    <body>
        <div class="container mt-3 border bg-light p-4 mb-3" style="width: 700px" >
            <h2 class="text-center">Update Cake</h2>
            <form action="DispatcherController?btAction=Update" method="POST" enctype="multipart/form-data">
                <c:set var="dto" value="${requestScope.CAKEDTO}"></c:set>
                <c:set var="errors" value="${requestScope.CREATEERROR}"></c:set>
                <input type="hidden" name="txtCakeId" value="${dto.cakeId}" />
                <div class="form-row">
                    <div class="col-md-6 mb-2">
                        Cake Name*: <input class="form-control" type="text" name="txtCake" value="${dto.cakeName}" />
                        <c:if test="${not empty errors.cakenameErr}">
                            <font color="red">${errors.cakenameErr}</font></br>     
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        Description*: <input class="form-control" type="text" name="txtDes" value="${dto.description}" />
                        <c:if test="${not empty errors.descriptionErr}">
                            <font color="red">${errors.descriptionErr}</font></br>   
                        </c:if>
                    </div>
                </div>  
                <div class="form-row">
                    <div class="col-md-6 mb-2">
                        Category*: 
                        <select class="form-control form-control-line" name="txtCategory">
                            <c:set var="listCate" value="${sessionScope.LISTCATE}"/>
                            <c:forEach var="cate" items="${listCate}">
                                <option value="${cate.id}"
                                        <c:if test="${dto.categoriID eq cate.id}">
                                            selected="selected"
                                        </c:if>>Category : ${cate.name}</option>
                            </c:forEach>
                        </select>
                        <c:if test="${not empty errors.categoriIDErr}">
                            <font color="red">${errors.categoriIDErr}</font></br>     
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        Price*: <input class="form-control" type="number" name="txtPrice" value="${dto.cakePrice}" />
                        <c:if test="${not empty errors.cakepriceErr}">
                            <font color="red">${errors.cakepriceErr}</font></br>     
                        </c:if>
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-6 mb-2">
                        Quantity*: <input class="form-control" type="number" name="txtQuantity" value="${dto.quantity}" />
                        <c:if test="${not empty errors.quantityErr}">
                            <font color="red">${errors.quantityErr}</font></br>     
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        Expiration Date*: 
                        <input class="form-control" type="date" name="txtExprirationDate" value="${dto.expirationDate}" />
                        <c:if test="${not empty errors.expriraDateErr}">
                            <font color="red">${errors.expriraDateErr}</font></br>     
                        </c:if>
                    </div>
                </div>
                <div class="form-row">
                    <input type="hidden" name="txtImage" value="${dto.imageLink}" />
                    <div class="col-md-6 mb-2">
                        Image: <img class="border rounded" src="${dto.imageLink}" width="300" height="220"/> </br>
                    </div>
                    <div class="col-md-6">
                        <jsp:useBean id="regisDao" class="longdh.registration.RegistrationDAO"/>
                        Create Date: <input class="form-control" disabled value="${dto.createDate}" />
                        Last Update: <input class="form-control" disabled value="${dto.updateDate}" />
                        Update User: <input class="form-control" disabled value="${regisDao.getUserNameByID(dto.userId)}" />
                        Change Image: <input class="mt-2" type="file" name="fileImage" value="${param.fileImage}" /></br>
                        <c:if test="${not empty errors.imageErr}">
                            <font color="red">${errors.imageErr}</font></br>     
                        </c:if>
                    </div>
                </div>
                <div class="text-center"> 
                    <a class="btn btn-info mt-2" href="search.jsp">Search Page</a>
                    <input class="btn btn-success mt-2 " type="submit" name="btAction" value="Update Cake" />
                </div>
            </form>
        </div>
    </body> 
</html>
