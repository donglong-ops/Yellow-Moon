<%-- 
    Document   : createCake
    Created on : Sep 16, 2021, 11:18:00 AM
    Author     : Dong Long
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Cake</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <jsp:include page="navbar.jsp"/>
    <body>
        <div class="container mt-3 border bg-light p-4" style="width: 700px" >
            <h1 class="text-center">Create New Cake</h1>
            <form action="DispatcherController?btAction=Add New Cake" method="POST" enctype="multipart/form-data">
                <c:set var="errors" value="${requestScope.CREATEERROR}"></c:set>
                    <div class="form-row">
                        <div class="col-md-6 mb-2">
                            Cake Name*: <input class="form-control" type="text" name="txtCake" value="${param.txtCake}" />  (2 - 70 characters)</br>
                        <c:if test="${not empty errors.cakenameErr}">
                            <font color="red">${errors.cakenameErr}</font></br>     
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        Description*: <input class="form-control" type="text" name="txtDes" value="${param.txtDes}" /> (2 - 300 characters)</br>
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
                                        <c:if test="${param.txtSearchCategory eq cate.id}">
                                            selected="selected"
                                        </c:if>>Category : ${cate.name}</option>
                            </c:forEach>
                        </select>
                        <c:if test="${not empty errors.categoriIDErr}">
                            <font color="red">${errors.categoriIDErr}</font></br>     
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        Price*: <input class="form-control" type="number" name="txtPrice" value="${param.txtPrice}" />
                        <c:if test="${not empty errors.cakepriceErr}">
                            <font color="red">${errors.cakepriceErr}</font></br>     
                        </c:if>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6 mb-2">
                        Quantity*: <input class="form-control" type="number" name="txtQuantity" value="${param.txtQuantity}" />
                        <c:if test="${not empty errors.quantityErr}">
                            <font color="red">${errors.quantityErr}</font></br>     
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        Expiration Date*: 
                        <input class="form-control" type="date" name="txtExprirationDate" value="${param.txtExprirationDate}" />
                        <c:if test="${not empty errors.expriraDateErr}">
                            <font color="red">${errors.expriraDateErr}</font></br>     
                        </c:if>
                    </div>
                </div>

                Image*: <input type="file" name="fileImage" value="${param.fileImage}" /></br>
                <c:if test="${not empty errors.imageErr}">
                    <font color="red">${errors.imageErr}</font></br>     
                </c:if>
                <div class="text-center"> 
                    <input class="btn btn-success mt-2" type="submit" name="btAction" value="Add New Cake" />
                    <input class="btn-light btn border mt-2" type="reset" value="Reset" />
                </div>
            </form>
        </div>
    </body>
    <jsp:include page="footer.jsp"/>    
</html>
