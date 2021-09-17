<%-- 
    Document   : createAccount
    Created on : Sep 16, 2021, 11:16:57 PM
    Author     : Dong Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <title>Create Account Page</title>
    </head>
    <body>
        <div class="container mt-5 border bg-light p-4" style="width: 500px" >
            <h1 class="text-center">Create Page</h1>
            <form action="DispatcherController" method="POST">
                <c:set var="error" value="${requestScope.CREATEERROR}" />
                <div class="form-row">
                    <div class="col-md-6 mb-2">
                        Email*: <input class="form-control" type="email" required name="txtEmail" value="${param.txtEmail}" />  (2 - 20 characters)</br>
                        <c:if test="${not empty error.emailLengthError}">
                            <font color="red">
                            ${error.emailLengthError}
                            </font> <br/>
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        Fullname*: <input class="form-control" type="text" required name="txtFullname" value="${param.txtFullname}" /> (2 - 50 characters)</br>
                        <c:if test="${not empty error.fullnameLengthError}">
                            <font color="red">
                            ${error.fullnameLengthError}
                            </font> <br/>
                        </c:if>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6 mb-2">
                        Password*: <input class="form-control" type="password" required name="txtPassword" value="" />  (2 - 10 characters)</br>
                        <c:if test="${not empty error.passwordLengthError}">
                            <font color="red">
                            ${error.passwordLengthError}
                            </font> <br/>
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        Re-password*: <input class="form-control" type="password" required name="txtRepassword" value="" /> (2 - 10 characters)</br>
                        <c:if test="${not empty error.confirmNotMatched}">
                            <font color="red">
                            ${error.confirmNotMatched}
                            </font> <br/>
                        </c:if>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6 mb-2">
                        Address*: <input class="form-control" type="text" required name="txtAddress" value="${param.txtAddress}" />  (2 - 300 characters)</br>
                        <c:if test="${not empty error.addressLengthError}">
                            <font color="red">
                            ${error.addressLengthError}
                            </font> <br/>
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        Phone*: <input class="form-control" type="number" required name="txtPhone" value="${param.txtPhone}" /> (2 - 10 characters)</br>
                        <c:if test="${not empty error.phoneLengthError}">
                            <font color="red">
                            ${error.phoneLengthError}
                            </font> <br/>
                        </c:if>
                    </div>
                </div>
                <div class="form-row" style="margin-left: 20%">
                    <input class="btn btn-light border mt-2 px-4 ml-2" type="reset" value="Reset" />
                    <input class="btn btn-success mt-2 px-4 ml-2"  type="submit" name="btAction" value="Create Account" />
                </div>
            </form>
        </div>
    </body>
</html>
