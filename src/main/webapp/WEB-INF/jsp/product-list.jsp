<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">

<head>
    <title>System zarządzania księgarnią</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"--%>
<%--            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"--%>
<%--            crossorigin="anonymous"></script>--%>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"--%>
<%--            integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"--%>
<%--            crossorigin="anonymous"></script>--%>

    <script src="js/basket.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>

<jsp:include page="header.jsp"/>

<br>

<div class="container">
    <h3 class="text-center">Lista produktów</h3>
    <hr>
    <form method="post" name="products">
        <table class="table table-bordered table-striped table-sm">
            <thead class="thead-dark">
            <tr>
                <th hidden>Product Id</th>
                <th>&nbsp</th>
                <th>Nazwa produktu</th>
                <th>Producent</th>
                <th>Kod produktu</th>
                <th>Cena</th>
                <th>&nbsp</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${items}">

                <tr>
                    <td hidden>
                        <c:out value="${item.productId}"/>
                    </td>
                    <td class="pictureTd">
                        <img src="${item.linkToPicture}" height="70" width="70" alt="Obraz produktu"/>
                    </td>
                    <td>
                        <c:out value="${item.name}"/>
                    </td>
                    <td>
                        <c:out value="${item.manufacturer.getName()}"/>
                    </td>
                    <td>
                        <c:out value="${item.code}"/>
                    </td>
                    <td class="numberColumn">
                            <c:out value="${item.price}"/>
                    <td class="buttonColumn">
                        <input class="btn btn-primary btn-sm" type="button"
                               value="Dodaj do koszyka"
                               onclick="saveItem(<c:out value="${item.productId}"/>,
                                       '<c:out value="${item.name}"/>', 1, <c:out value="${item.price}"/>)"/>

                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </form>
</div>
</body>

</html>
