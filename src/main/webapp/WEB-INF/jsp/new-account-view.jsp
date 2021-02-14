<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">

<head>
    <title>Dane do realizacji zamówienia</title>
    <meta charset="UTF-8">
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <script src="js/registration.js"></script>
    <script src="js/basket.js"></script>
    <link rel="stylesheet" type="text/css" href="css/registration.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>

<jsp:include page="components/header-view.jsp"/>

<br>

<div class="container">
    <h3 class="text-center">Podaj dane</h3>
    <hr>

    <div class="register">
        <div class="row">
            <div class="col-sm-12 register-right">
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
                        <%--@elvariable id="customerDetails" type=""--%>
                        <form:form id="individual" modelAttribute="customerDetails" action="payment-details"
                                   method="post"
                                   onsubmit="sendBasketContents()">

                            <input type="hidden" name="sessionId" value="${customerDetails.sessionId}"/>
                            <input type="hidden" name="basketContents"/>

                            <h3 class="register-heading">Osoba prywatna</h3>
                            <div class="row register-form">
                                <div class="col-md-4">
                                    <jsp:include page="components/first-and-last-name-view.jsp">
                                        <jsp:param name="billing" value="true"/>
                                        <jsp:param name="required" value="required"/>
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <jsp:include page="components/email-phone-view.jsp"/>

                                    <jsp:include page="components/passwords-view.jsp">
                                        <jsp:param name="suffix" value="1"/>
                                        <jsp:param name="required" value="required"/>
                                        <jsp:param name="disabled" value=""/>
                                    </jsp:include>
                                </div>

                                <div class="col-md-4">
                                    <jsp:include page="components/shipping-addres-checkbox-view.jsp">
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <div style="margin-top: 23px">
                                        <jsp:include page="components/address-view.jsp">
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
                                    <jsp:include page="components/first-and-last-name-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <jsp:include page="components/company-name-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <jsp:include page="components/address-view.jsp">
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

                            <jsp:include page="components/cancell-and-submit-buttons-view.jsp">
                                <jsp:param name="url" value='${request.getContextPath()}/start' />
                                <jsp:param name="submit" value="Zapisz dane"/>
                                <jsp:param name="onclick" value=""/>
                            </jsp:include>
                        </form:form>
                    </div>

                    <div class="tab-pane fade show" id="firm" role="tabpanel" aria-labelledby="firm-tab">
                        <form:form id="firm" modelAttribute="customerDetails" action="payment-details" method="post"
                                   onsubmit="sendBasketContents()">
                            <input type="hidden" name="sessionId" value="${customerDetails.sessionId}"/>
                            <input type="hidden" name="basketContents"/>

                            <h3 class="register-heading">Firma</h3>
                            <div class="row register-form">
                                <div class="col-md-4">
                                    <jsp:include page="components/company-name-view.jsp">
                                        <jsp:param name="billing" value="true"/>
                                    </jsp:include>
                                    <jsp:include page="components/first-and-last-name-view.jsp">
                                        <jsp:param name="billing" value="true"/>
                                        <jsp:param name="required" value=""/>
                                        <jsp:param name="suffix" value="2"/>
                                    </jsp:include>

                                    <jsp:include page="components/email-phone-view.jsp"/>

                                    <jsp:include page="components/passwords-view.jsp">
                                        <jsp:param name="suffix" value="2"/>
                                        <jsp:param name="required" value="required"/>
                                        <jsp:param name="disabled" value=""/>
                                    </jsp:include>
                                </div>

                                <div class="col-md-4">
                                    <jsp:include page="components/shipping-addres-checkbox-view.jsp">
                                        <jsp:param name="suffix" value="2"/>
                                    </jsp:include>

                                    <div style="margin-top: 23px">
                                        <jsp:include page="components/address-view.jsp">
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
                                    <jsp:include page="components/first-and-last-name-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="2"/>
                                    </jsp:include>
                                    <jsp:include page="components/company-name-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="2"/>
                                    </jsp:include>
                                    <jsp:include page="components/address-view.jsp">
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

                            <jsp:include page="components/cancell-and-submit-buttons-view.jsp">
                                <jsp:param name="url" value='${request.getContextPath()}/start'/>
                                <jsp:param name="submit" value="Zapisz dane"/>
                                <jsp:param name="onclick" value=""/>
                            </jsp:include>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
