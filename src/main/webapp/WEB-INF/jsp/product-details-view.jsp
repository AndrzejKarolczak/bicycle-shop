<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pl">

<head>
    <title>Karta produktu</title>
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
    <h3 class="text-center">${product.getName()}</h3>
    <hr>

    <table class="table table-bordered table-striped table-sm">
        <thead class="thead-dark">
        <tr>
            <th>&nbsp</th>
            <th>Producent</th>
            <th>Kod produktu</th>
            <th>Cena</th>
            <th>&nbsp</th>
        </tr>
        </thead>
        <tbody>

            <tr>
                <td class="pictureTd">
                    <img src="${product.linkToPicture}" height="70" width="70" alt="Obraz produktu"/>
                </td>
                <td>
                    <c:out value="${product.getManufacturer().getName()}"/>
                </td>
                <td>
                    <c:out value="${product.getCode()}"/>
                </td>
                <td class="rightAlignedColumn">
                        <fmt:formatNumber type="number" pattern="###,###.##" value="${product.getPrice()}"/>
                <td class="buttonColumn">
                    <input class="btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#exampleModal"
                           value="Dodaj do koszyka"
                           onclick="saveItem(<c:out value="${product.productId}"/>,
                                   '<c:out value="${product.name}"/>', 1, <c:out value="${product.price}"/>)" />

                </td>
            </tr>

        </tbody>
    </table>

    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Produkt dodano do koszyka</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Kontynuuj zakupy</button>
                    <a href="<%=request.getContextPath()%>/basket" class="btn btn-primary">Przejdź do koszyka</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
