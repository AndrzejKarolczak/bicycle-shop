<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pl">

<head>
    <title>Skład zamówienia</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <script src="js/basket.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>

<jsp:include page="components/header-view.jsp"/>

<br>
<div class="container">
    <h3 class="text-center">Szczegóły zamówienia nr ${orderDetails.getOrderId()}</h3>
    <hr>

    <table class="table table-bordered table-striped table-sm">
        <thead class="thead-dark">
        <tr>
            <th>Nazwa produktu</th>
            <th>Liczba</th>
            <th>Cena</th>
            <th>Szczegóły</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${orderDetails.getProductsInOrder()}">
            <tr>
                <td>
                    <c:out value="${item.getProduct().getName()}"/>
                </td>
                <td class="rightAlignedColumn">
                    <c:out value="${item.getQuantity()}"/>
                </td>
                <td class="rightAlignedColumn">
                    <fmt:formatNumber type="number" pattern="###,###.##" value="${item.getPrice()}" />
                </td>
                <td class="buttonColumn">
                    <a href="<%=request.getContextPath()%>/product-details?id=${item.getProduct().getProductId()}"
                       class="btn btn-info btn-sm">Zobacz</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div>

    </div>
</div>
</body>

</html>