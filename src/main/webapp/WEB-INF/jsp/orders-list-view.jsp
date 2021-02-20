<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="pl">

<head>
    <title>Lista zamówień</title>
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
    <h3 class="text-center">Lista zamówień</h3>
    <hr>

    <table class="table table-bordered table-striped table-sm">
        <thead class="thead-dark">
        <tr>
            <th>Numer zamówienia</th>
            <th>Data złożenia</th>
            <th>Wartość</th>
            <th>Status</th>
            <th>Skład</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${orders}">
            <tr>
                <td class="rightAlignedColumn" style="width: 15%">
                    <c:out value="${item.getOrderId()}"/>
                </td>
                <td class="rightAlignedColumn">
                    <c:out value="${item.getOrderTimestampAsFormattedString()}"/>
                </td>
                <td class="rightAlignedColumn">
                    <fmt:formatNumber type="number" pattern="###,###.##" value="${item.calculateOrderValue()}" />
                </td>
                <td>
                    <c:out value="${item.getOrderStatus().toString()}"/>
                </td>
                <td class="buttonColumn">
                    <a href="<%=request.getContextPath()%>/order-details?id=${item.getOrderId()}" class="btn btn-info btn-sm">Zobacz</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>

</html>
