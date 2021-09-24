<%-- 
    Document   : receipt
    Created on : Sep 19, 2021, 14:05:00 PM
    Author     : Dong Long
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Payment Receipt</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <style type="text/css">
            table { border: 0; }
            table td { padding: 5px; }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container mt-3 border bg-light p-4" style="width: 600px" >
            <div align="center">
                <h1 style="color: red">Payment Done. Thank you for purchasing our products</h1>
                <h2>Receipt Details:</h2>
                <table>
                    <tr>
                        <td><b>Merchant:</b></td>
                        <td>Booking Cake</td>
                    </tr>
                    <tr>
                        <td><b>Payer:</b></td>
                        <td>${payer.firstName} ${payer.lastName}</td>      
                    </tr>
                    <tr>
                        <td><b>Description:</b></td>
                        <td>${transaction.description}</td>
                    </tr>
                    <tr>
                        <td><b>Total:</b></td>
                        <td>${transaction.amount.total} USD</td>
                    </tr>                    
                </table>
                <a class="btn btn-success" href="HomeController">Go shopping</a>
            </div>

        </div>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
