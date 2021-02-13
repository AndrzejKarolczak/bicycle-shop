<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">

<head>
    <title>Dane do realizacji zamówienia</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <script src="js/registration.js"></script>
    <script src="js/basket.js"></script>
    <link rel="stylesheet" type="text/css" href="css/registration.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp"/>

<br>

<div class="container">
    <h3 class="text-center">Podaj dane</h3>
    <hr>

    <div class="container register">
        <div class="row">
            <div class="col-12 register-right">
                <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="individual-tab" data-toggle="tab" href="#individual" role="tab"
                           aria-controls="home" aria-selected="true">Osoba</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="firm-tab" data-toggle="tab" href="#firm" role="tab"
                           aria-controls="profile" aria-selected="false">Firma</a>
                    </li>
                </ul>

                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="individual" role="tabpanel"
                         aria-labelledby="individual-tab">
                        <form:form modelAttribute="customerDetails" action="customer-details" method="post"
                                   onsubmit="sendBasketContents()">

                            <input type="hidden" name="sessionId" value="${session}"/>
                            <input type="hidden" name="basketContents"/>

                            <h3 class="register-heading">Osoba prywatna</h3>
                            <div class="row register-form">
                                <div class="col-md-4">
                                    <jsp:include page="first-and-last-name-view.jsp">
                                        <jsp:param name="billing" value="true"/>
                                        <jsp:param name="required" value="required"/>
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <jsp:include page="email-phone-view.jsp"/>

                                    <div class="form-group form-check" style="margin-top: 30px">
                                        <input type="checkbox" class="form-check-input" id="register-me-1"
                                               name="register-me"
                                               onclick="enablePasswords(1)">
                                        <label class="form-check-label" for="register-me-1">Chcę założyć konto</label>
                                    </div>

                                    <jsp:include page="passwords-view.jsp">
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <input type="submit" class="btn btn-primary" value="Dalej"/>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group form-check" style="margin-top: 7px">
                                        <input type="checkbox" class="form-check-input" id="include-shipping-1"
                                               name="include-shipping-1" onclick="showShippingAddress(1)">
                                        <label class="form-check-label" for="include-shipping-1">
                                            Inny adres do wysyłki</label>
                                    </div>
                                    <div style="margin-top: 23px">
                                        <jsp:include page="address-view.jsp">
                                            <jsp:param name="billing" value="true"/>
                                        </jsp:include>
                                    </div>
                                    <div class="form-group">
                                        <select name="billingCountry" class="form-control" required
                                                id="billing-country-1">
                                            <c:forEach var="country" items="${countries}">
                                                <option value="${country.id}"
                                                        <c:if test="${country.name == 'Polska'}">selected</c:if>>
                                                        ${country.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-4" id="shipping-address-group-1" style="display: none">
                                    <jsp:include page="first-and-last-name-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <jsp:include page="company-name-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <jsp:include page="address-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <div class="form-group">
                                        <select class="form-control" id="shipping-country-1" name="shippingCountry">
                                            <c:forEach var="country" items="${countries}">
                                                <option value="${country.id}"
                                                        <c:if test="${country.name == 'Polska'}">selected</c:if>>${country.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </div>

                    <div class="tab-pane fade show" id="firm" role="tabpanel" aria-labelledby="firm-tab">
                        <form:form modelAttribute="customerDetails" action="customer-details" method="post"
                                   onsubmit="sendBasketContents()">
                            <input type="hidden" name="sessionId" value="${session}"/>
                            <input type="hidden" name="basketContents"/>

                            <h3 class="register-heading">Firma</h3>
                            <div class="row register-form">
                                <div class="col-md-4">
                                    <jsp:include page="company-name-view.jsp">
                                        <jsp:param name="billing" value="true"/>
                                    </jsp:include>
                                    <jsp:include page="first-and-last-name-view.jsp">
                                        <jsp:param name="billing" value="true"/>
                                        <jsp:param name="required" value=""/>
                                        <jsp:param name="suffix" value="2"/>
                                    </jsp:include>

                                    <jsp:include page="email-phone-view.jsp"/>

                                    <div class="form-group form-check" style="margin-top: 30px">
                                        <input type="checkbox" class="form-check-input" id="register-me-2"
                                               name="register-me"
                                               onclick="enablePasswords(2)">
                                        <label class="form-check-label" for="register-me-2">Chcę założyć konto</label>
                                    </div>

                                    <jsp:include page="passwords-view.jsp">
                                        <jsp:param name="suffix" value="2"/>
                                    </jsp:include>

                                    <input type="submit" class="btn btn-primary" value="Dalej"/>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group form-check" style="margin-top: 7px">
                                        <input type="checkbox" class="form-check-input" id="include-shipping-2"
                                               name="include-shipping" onclick="showShippingAddress(2)">
                                        <label class="form-check-label" for="include-shipping-2">
                                            Inny adres do wysyłki</label>
                                    </div>
                                    <div style="margin-top: 23px">
                                        <jsp:include page="address-view.jsp">
                                            <jsp:param name="billing" value="true"/>
                                        </jsp:include>
                                    </div>
                                    <div class="form-group">
                                        <select name="billingCountry" class="form-control" required
                                                id="billing-country-2">
                                            <c:forEach var="country" items="${countries}">
                                                <option value="${country.id}"
                                                        <c:if test="${country.name == 'Polska'}">selected</c:if>>${country.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-4" id="shipping-address-group-2" style="display: none">
                                    <jsp:include page="first-and-last-name-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="2"/>
                                    </jsp:include>
                                    <jsp:include page="company-name-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="2"/>
                                    </jsp:include>
                                    <jsp:include page="address-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="2"/>
                                    </jsp:include>

                                    <div class="form-group">
                                        <select name="shippingCountry" class="form-control" id="shipping-country-2"
                                                name="shipping-country">
                                            <c:forEach var="country" items="${countries}">
                                                <option value="${country.id}"
                                                        <c:if test="${country.name == 'Polska'}">selected</c:if>>
                                                        ${country.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
</body>

</html>
