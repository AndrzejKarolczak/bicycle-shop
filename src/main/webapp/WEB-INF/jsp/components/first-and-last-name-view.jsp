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
</head>
<body>

<c:if test="${param.billing == 'true'}">
    <div class="form-group">
        <input type="text" id="billing-first-name-${param.suffix}" ${param.required} name="billingFirstName"
               class="form-control" placeholder="Imię" value="${param.firstName}"/>
    </div>
    <div class="form-group">
        <input type="text" id="billing-last-name-${param.suffix}" ${param.required} name="billingLastName"
               class="form-control" placeholder="Nazwisko" value="${param.lastName}"/>
    </div>
</c:if>

<c:if test="${param.billing == 'false'}">
    <div class="form-group">
        <input type="text" id="shipping-first-name-${param.suffix}" name="shippingFirstName" class="form-control"
               placeholder="Imię" value="${param.firstName}"/>
    </div>
    <div class="form-group">
        <input type="text" id="shipping-last-name-${param.suffix}" name="shippingLastName" class="form-control"
               placeholder="Nazwisko" value="${param.lastName}"/>
    </div>
</c:if>

</body>
</html>