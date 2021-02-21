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

<body > <%--onload="disableSubmitButtonsOnEmptyBasket()"--%>

<jsp:include page="components/header-view.jsp"/>

<br>

<div class="container">
    <h3 class="text-center">Podaj dane potrzebne do realizacji zamówienia</h3>
    <hr>

    <div class="register">
        <div class="row">
            <div class="col-12 register-right">
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="individual" role="tabpanel"
                         aria-labelledby="individual-tab">
                        <%--@elvariable id="customerDetails" type=""--%>
                        <form:form id="individual" modelAttribute="customerDetails" action="payment-details"
                                   method="post"
                                   onsubmit="saveBasketContents()">

                            <input type="hidden" name="sessionId" value="${customerDetails.sessionId}"/>
                            <input type="hidden" name="basketContents"/>
                            <input type="hidden" name="isIndividual" value="true"/>

                            <h3 class="register-heading">Osoba prywatna</h3>
                            <div class="row register-form">
                                <div class="col-md-4">
                                    <jsp:include page="components/first-and-last-name-view.jsp">
                                        <jsp:param name="billing" value="true"/>
                                        <jsp:param name="required" value="required"/>
                                        <jsp:param name="suffix" value="1"/>
                                        <jsp:param name="firstName" value="${customerDetails.billingFirstName}"/>
                                        <jsp:param name="lastName" value="${customerDetails.billingLastName}"/>
                                    </jsp:include>

                                    <jsp:include page="components/email-phone-view.jsp">
                                        <jsp:param name="email" value="${customerDetails.email}"/>
                                        <jsp:param name="phone" value="${customerDetails.phone}"/>
                                    </jsp:include>

                                    <jsp:include page="components/passwords-checkbox-view.jsp">
                                        <jsp:param name="message" value="Chcę zmienić hasło"/>
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <jsp:include page="components/passwords-view.jsp">
                                        <jsp:param name="suffix" value="1"/>
                                        <jsp:param name="required" value=""/>
                                        <jsp:param name="disabled" value="disabled"/>
                                    </jsp:include>
                                </div>

                                <div class="col-md-4">
                                    <jsp:include page="components/shipping-addres-checkbox-view.jsp">
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <div style="margin-top: 23px">
                                        <jsp:include page="components/address-view.jsp">
                                            <jsp:param name="billing" value="true"/>
                                            <jsp:param name="billingStreet" value="${customerDetails.billingStreet}"/>
                                            <jsp:param name="billingBuildingNumber" value="${customerDetails.billingBuildingNumber}"/>
                                            <jsp:param name="billingSuiteNumber" value="${customerDetails.billingSuiteNumber}"/>
                                            <jsp:param name="billingCity" value="${customerDetails.billingCity}"/>
                                            <jsp:param name="billingPostalCode" value="${customerDetails.billingPostalCode}"/>
                                        </jsp:include>
                                    </div>

                                    <jsp:include page="components/countries-view.jsp">
                                        <jsp:param name="suffix" value="1"/>
                                        <jsp:param name="options" value='${countries}'/>
                                        <jsp:param name="billing" value="true"/>
                                    </jsp:include>
                                </div>

                                <div class="col-md-4" id="shipping-address-group-1" style="display: none">
                                    <jsp:include page="components/first-and-last-name-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="1"/>
                                        <jsp:param name="firstName" value="${customerDetails.shippingFirstName}"/>
                                        <jsp:param name="lastName" value="${customerDetails.shippingLastName}"/>
                                    </jsp:include>

                                    <jsp:include page="components/company-name-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="1"/>
                                    </jsp:include>

                                    <jsp:include page="components/address-view.jsp">
                                        <jsp:param name="billing" value="false"/>
                                        <jsp:param name="suffix" value="1"/>
                                        <jsp:param name="shippingStreet" value="${customerDetails.shippingStreet}"/>
                                        <jsp:param name="shippingBuildingNumber" value="${customerDetails.shippingBuildingNumber}"/>
                                        <jsp:param name="shippingSuiteNumber" value="${customerDetails.shippingSuiteNumber}"/>
                                        <jsp:param name="shippingCity" value="${customerDetails.shippingCity}"/>
                                        <jsp:param name="shippingPostalCode" value="${customerDetails.shippingPostalCode}"/>
                                    </jsp:include>

                                    <jsp:include page="components/countries-view.jsp">
                                        <jsp:param name="suffix" value="1"/>
                                        <jsp:param name="options" value='${countries}'/>
                                        <jsp:param name="billing" value="false"/>
                                    </jsp:include>
                                </div>
                            </div>

                            <jsp:include page="components/cancell-and-submit-buttons-view.jsp">
                                <jsp:param name="url"
                                           value='${request.getContextPath()}/order-cancelled?session=${customerDetails.sessionId}'/>
                                <jsp:param name="submit" value='Wybierz rodzaj płatności'/>
                                <jsp:param name="onclick" value="onclick='clearBasketContents()'"/>
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
