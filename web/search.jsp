<%-- 
    Document   : search
    Created on : Sep 15, 2021, 14:05:00 PM
    Author     : Dong Long
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <head>
        <meta charset="UTF-8" http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container form-row">
            <div class="container border mt-3 bg-light p-3" style="max-width: 500px; margin-left: 10%"> 
                <form action="DispatcherController" method="POST">
                    <h3 class="text-center mb-4">Search form</h3>
                    <div class="form-row">
                        <div class="col-md-6 mb-2">
                            <input placeholder="Cake Name" type="text" class="form-control" name="txtSearchName" value="${param.txtSearchName}" />
                        </div>
                        <div class="col-md-6">
                            <select class="form-control form-control-line" name="txtSearchCategory">
                                <c:set var="listCate" value="${sessionScope.LISTCATE}"/>
                                <c:forEach var="cate" items="${listCate}">
                                    <option value="${cate.id}"
                                            <c:if test="${param.txtSearchCategory eq cate.id}">
                                                selected="selected"
                                            </c:if>>Category : ${cate.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 mb-2">
                            <input placeholder="From Price" type="text" class="form-control" name="txtFromPrice" value="${param.txtFromPrice}" />
                        </div>
                        <div class="col-md-6">
                            <input placeholder="To Price" type="text" class="form-control" name="txtToPrice" value="${param.txtToPrice}" />
                        </div>
                    </div> 
                    <div class="form-row">
                        <input class="col-md-12 mt-2 ml-1 btn btn-primary px-5" type="submit" name="btAction" value="Search" />
                    </div>
                </form>
                <c:set var="search" value="${requestScope.SEARCH_RESULT}"/>
                <c:if test="${not empty search}">
                    <div class="form-row mt-3">
                        <div class="form-row">
                            <c:if test="${not empty param.pageNum}">
                                <p>  Current Page  [ ${param.pageNum} ]: </p>
                            </c:if>
                            <c:if test="${empty param.pageNum}">
                                <p>  Current Page  [ 1 ]: </p>
                            </c:if>
                            <c:forEach begin="1" end="${requestScope.PAGENUMBER}" varStatus="counter" step="1">
                                <form action="SearchController" method="POST">
                                    <input type="hidden" name="txtSearchName" value="${param.txtSearchName}" />
                                    <input type="hidden" name="txtSearchCategory" value="${param.txtSearchCategory}" />
                                    <input type="hidden" name="txtFromPrice" value="${param.txtFromPrice}" />
                                    <input type="hidden" name="txtToPrice" value="${param.txtToPrice}" />
                                    <input type="hidden" name="pageNum" value="${counter.count}" />
                                    <input id="page" class="btn btn-primary ml-2" type="submit" value="${counter.count}"/>
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </div>
            <img class="border rounded mt-3" src="img/yellow2.png" style="max-width: 500px; width: 470px; height: 320px;  margin-left: 2%"/>
        </div>
        <div class="container mt-5" style="width: 1150px">
            <c:set var="cart" value="${sessionScope.CART}"/>

            <c:if test="${not empty search}">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Cake Name</th>
                            <th>Image</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quanti</th>
                            <th>CreateDate</th>
                            <th>ExpirationDate</th>
                            <th>Category</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <jsp:useBean id="dao" class="longdh.cake.CakeDAO"/>
                        <c:forEach var="dto" items="${search}" varStatus="counter">
                            <tr>
                                <td>${counter.count} </td>
                                <td>${dto.cakeName}</td>
                                <td>
                                    <img class="border rounded" src="${dto.imageLink}" width="170" height="150"/>
                                </td>
                                <td>${dto.description}</td> 
                                <td>${dto.cakePrice}</td>
                                <td>${dto.quantity}</td>
                                <td>${dto.createDate}</td>
                                <td>${dto.expirationDate}</td>
                                <td>${dao.getCategoryName(dto.categoriID)}</td>
                                <td>
                                    <c:if test="${empty sessionScope.USER || sessionScope.USER.role.name == 'User'}">
                                        <form action="DispatcherController" method="POST">
                                            <input type="hidden" name="cakeId" value="${dto.cakeId}" />
                                            <input type="hidden" name="txtSearchName" value="${param.txtSearchName}" />
                                            <input type="hidden" name="txtSearchCategory" value="${param.txtSearchCategory}" />
                                            <input type="hidden" name="txtFromPrice" value="${param.txtFromPrice}" />
                                            <input type="hidden" name="txtToPrice" value="${param.txtToPrice}" />
                                            <input type="hidden" name="pageNum" value="${param.pageNum}" />
                                            <input class="btn btn-success" type="submit" name="btAction" value="Add Cart"/>
                                        </form>
                                    </c:if>
                                    <c:if test="${sessionScope.USER.role.name == 'Admin'}">
                                        <form action="DispatcherController" method="POST">
                                            <c:url var="editUrl" value="DispatcherController?btAction=Edit">
                                                <c:param name="ID" value="${dto.cakeId}"> </c:param>
                                                <c:param name="pageNum" value="${param.pageNum}"> </c:param>
                                            </c:url>
                                            <a class="btn btn-success" href="${editUrl}">Edit</a>
                                            <c:url var="deleteUrl" value="DispatcherController?btAction=Delete">
                                                <c:param name="ID" value="${dto.cakeId}"> </c:param>
                                                <c:param name="pageNum" value="${param.pageNum}"> </c:param>
                                            </c:url>
                                            <a class="btn btn-danger" href="${deleteUrl}" onclick="return confirm('Are you sure to Delete Cake?');">Delete</a>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${not empty search}">
                    <div class="form-row" style="float: right">
                        <c:if test="${not empty param.pageNum}">
                            <p>  Current Page  [ ${param.pageNum} ]: </p>
                        </c:if>
                        <c:if test="${empty param.pageNum}">
                            <p>  Current Page  [ 1 ]: </p>
                        </c:if>
                        <c:forEach begin="1" end="${requestScope.PAGENUMBER}" varStatus="counter" step="1">
                            <form action="SearchController" method="POST">
                                <input type="hidden" name="txtSearchName" value="${param.txtSearchName}" />
                                <input type="hidden" name="txtSearchCategory" value="${param.txtSearchCategory}" />
                                <input type="hidden" name="txtFromPrice" value="${param.txtFromPrice}" />
                                <input type="hidden" name="txtToPrice" value="${param.txtToPrice}" />
                                <input type="hidden" name="pageNum" value="${counter.count}" />
                                <input id="page" class="btn btn-primary ml-2" type="submit" value="${counter.count}"/>
                            </form>
                        </c:forEach>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${not empty param.txtSearchCategory && empty search}">
                <h5 class="alert alert-danger text-center">Not Found!!!!!</h5>
            </c:if>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
