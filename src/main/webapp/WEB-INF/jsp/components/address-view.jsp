<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/registration.css">
</head>
<body>
<c:if test="${param.billing == 'true'}">
    <div class="form-group">
        <input type="text" name="billingStreet" required class="form-control" placeholder="Ulica"
               value="${param.billingStreet}"/>
    </div>
    <div class="form-group">
        <input type="text" name="billingBuildingNumber" required class="form-control"
               placeholder="Numer budynku" value="${param.billingBuildingNumber}"/>
    </div>
    <div class="form-group">
        <input type="text" name="billingSuiteNumber" class="form-control" placeholder="Numer lokalu"
               value="${param.billingSuiteNumber}"/>
    </div>
    <div class="form-group">
        <input type="text" name="billingCity" required class="form-control" placeholder="Miasto"
               value="${param.billingCity}"/>
    </div>
    <div class="form-group">
        <input type="text" name="billingPostalCode" required class="form-control"
               placeholder="Kod pocztowy: 00-000" value="${param.billingPostalCode}" pattern="[0-9]{2}-[0-9]{3}"/>
    </div>

</c:if>

<c:if test="${param.billing == 'false'}">
    <div class="form-group">
        <input type="text" id="shipping-street-${param.suffix}" name="shippingStreet" class="form-control"
               placeholder="Ulica" value="${param.shippingStreet}"/>
    </div>
    <div class="form-group">
        <input type="text" id="shipping-building-number-${param.suffix}" name="shippingBuildingNumber"
               class="form-control" placeholder="Numer budynku" value="${param.shippingBuildingNumber}"/>
    </div>
    <div class="form-group">
        <input type="text" id="shipping-suite-number-${param.suffix}" name="shippingSuiteNumber" class="form-control"
               placeholder="Numer lokalu" value="${param.shippingSuiteNumber}"/>
    </div>
    <div class="form-group">
        <input type="text" id="shipping-city-${param.suffix}" name="shippingCity" class="form-control"
               placeholder="Miasto" value="${param.shippingCity}"/>
    </div>
    <div class="form-group">
        <input type="text" id="shipping-postal-code-${param.suffix}" name="shippingPostalCode" class="form-control"
               placeholder="Kod pocztowy: 00-000" value="${param.shippingPostalCode}" pattern="[0-9]{2}-[0-9]{3}"/>
    </div>
</c:if>
</body>
</html>