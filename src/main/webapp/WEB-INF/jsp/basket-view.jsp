<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="pl">
<head>
    <title>Koszyk</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="js/basket.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body onload="showAll()">

<jsp:include page="header.jsp"/>

<br>

<form method="post" action="<%=request.getContextPath()%>/submit-basket" name="ShoppingList">
    <div id="main" class="container">
        <table class="table table-bordered table-striped table-sm">
            <thead class="thead-dark">
            <tr>
                <th>Numer produktu</th>
                <th>Nazwa produktu</th>
                <th>Liczba sztuk</th>
                <th>Cena</th>
                <th>Dodaj</th>
                <th>Odejmij</th>
                <th>Usuń</th>
            </tr>
            </thead>
            <tbody id="list">

            </tbody>
        </table>
        <br>

        <div style="float: left">
            <label class="label-bold" for="basket-value">Wartość koszyka</label>
            <input class="basket-value-input" disabled type="text" name="basket-value" id="basket-value"
                   value="0.00 PLN"/>
        </div>


        <div style="float: right">
            <input class="btn btn-danger" type="button" value="Wyczyść koszyk" onclick="clearAll()">
            <a class="btn btn-primary" href="<%=request.getContextPath()%>/">Wróć do zakupów</a>
            <input class="btn btn-success" type="submit" value="Realizuj zamówienie">
        </div>

    </div>
</form>

</body>
</html>
