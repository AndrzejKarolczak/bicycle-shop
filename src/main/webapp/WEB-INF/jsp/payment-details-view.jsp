<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">

<head>
    <title>Sposoby płatności</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <script src="js/basket.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body onload="disableSubmitButtonsOnEmptyBasket()">

<jsp:include page="components/header-view.jsp"/>

<br>
<div class="container">
    <h3 class="text-center">Metody płatności</h3>
    <hr>
    <div class="checkout__step checkout__step--three" data-order="3" data-step="three">
        <div class="payment-methods-wrapper">
            <div class="payment-methods">
                <div class="radio-wrapper payment-methods__item" style="display: block;">
                    <input class="radio-wrapper__input" type="radio" name="payment[method]" id="p_method_payu_account"
                           title="Szybki przelew" value="payu_account" data-testid="checkout-payment-payu_account">
                    <label class="radio-wrapper__label payment-methods__label payment-methods__label--payu_account"
                           for="p_method_payu_account">
                        <span class="radio-wrapper__icon"></span>
                        <span class="payment-methods__title">
Szybki przelew </span>
                    </label>
                    <div class="payment-methods__desc payment-method-desc-for-payu_account" style="display: block;">
                        <%--                        Aby dokończyć proces płatności, po kliknięciu "Zamawiam i płacę", nastąpi przekierowanie do--%>
                        <%--                        naszego dostawcy usług płatniczych PayU. Pamiętaj, że może być konieczne potwierdzenie--%>
                        <%--                        transakcji poprzez 3D-Secure, zgodnie z procedurą Twojego banku.--%>
                    </div>
                </div>
                <div class="radio-wrapper payment-methods__item" style="display: block;">
                    <input class="radio-wrapper__input" type="radio" name="payment[method]" id="p_method_payu_blik"
                           title="Blik" value="payu_blik" checked="checked" data-testid="checkout-payment-payu_blik">
                    <label class="radio-wrapper__label payment-methods__label payment-methods__label--payu_blik"
                           for="p_method_payu_blik">
                        <span class="radio-wrapper__icon"></span>
                        <span class="payment-methods__title">
Blik </span>
                    </label>
                    <div class="payment-methods__desc payment-method-desc-for-payu_blik" style="display: none;">
                        Kod BLIK wygenerujesz w swojej aplikacji bankowej.
                    </div>
                </div>
                <div class="radio-wrapper payment-methods__item" style="display: block;">
                    <input class="radio-wrapper__input" type="radio" name="payment[method]" id="p_method_payu_card"
                           title="Płatność kartą" value="payu_card" data-testid="checkout-payment-payu_card">
                    <label class="radio-wrapper__label payment-methods__label payment-methods__label--payu_card"
                           for="p_method_payu_card">
                        <span class="radio-wrapper__icon"></span>
                        <span class="payment-methods__title">
Płatność kartą </span>
                    </label>
                    <div class="payment-methods__desc payment-method-desc-for-payu_card" style="display: none;">
                        Proces płatności nastąpi po kliknięciu "Zamawiam i płacę". Zostaniesz przekierowany do naszego
                        dostawcy płatności PayU. Wybierając kartę konieczne będzie uwierzytelnienie transakcji poprzez
                        3D-Secure, zgodnie z procedurą Twojego banku.
                    </div>
                </div>
                <div class="radio-wrapper payment-methods__item" style="display: block;">
                    <input class="radio-wrapper__input" type="radio" name="payment[method]" id="p_method_cashondelivery"
                           title="Płatne przy odbiorze" value="cashondelivery"
                           data-testid="checkout-payment-cashondelivery">
                    <label class="radio-wrapper__label payment-methods__label payment-methods__label--cashondelivery"
                           for="p_method_cashondelivery">
                        <span class="radio-wrapper__icon"></span>
                        <span class="payment-methods__title">
Płatne przy odbiorze </span>
                    </label>
                    <div class="payment-methods__desc payment-method-desc-for-cashondelivery" style="display: none;">
                        Unikaj kontaktów z kurierami w trakcie epidemii. Skorzystaj z innych form płatności lub zapłać
                        kurierowi kartą
                    </div>
                </div>
                <div class="radio-wrapper payment-methods__item" style="display: block;">
                    <input class="radio-wrapper__input" type="radio" name="payment[method]"
                           id="p_method_paypal_standard" title="PayPal" value="paypal_standard"
                           data-testid="checkout-payment-paypal_standard">
                    <label class="radio-wrapper__label payment-methods__label payment-methods__label--paypal_standard"
                           for="p_method_paypal_standard">
                        <span class="radio-wrapper__icon"></span>
                        <span class="payment-methods__title">
PayPal </span>
                    </label>
                </div>
                <div class="radio-wrapper payment-methods__item" style="display: block;">
                    <input class="radio-wrapper__input" type="radio" name="payment[method]"
                           id="p_method_snowmasscollect" title="Przelew tradycyjny" value="snowmasscollect"
                           data-testid="checkout-payment-snowmasscollect">
                    <label class="radio-wrapper__label payment-methods__label payment-methods__label--snowmasscollect"
                           for="p_method_snowmasscollect">
                        <span class="radio-wrapper__icon"></span>
                        <span class="payment-methods__title">
Przelew tradycyjny </span>
                    </label>
                    <div class="payment-methods__desc payment-method-desc-for-snowmasscollect" style="display: none;">
                        Prosimy o wykonanie przelewu na konto, którego dane otrzymają Państwo na stronie z podsumowaniem
                        zamówienia oraz w mailu potwierdzającym jego złożenie. W tytule płatności prosimy podać numer
                        zamówienia oraz opcjonalnie login, co ułatwi realizację zamówienia.
                    </div>
                </div>
                <div class="radio-wrapper payment-methods__item" style="display: block;">
                    <input class="radio-wrapper__input" type="radio" name="payment[method]" id="p_method_snowpaypo"
                           title="PayPo - kup teraz, zapłać za 30 dni" value="snowpaypo"
                           data-testid="checkout-payment-snowpaypo">
                    <label class="radio-wrapper__label payment-methods__label payment-methods__label--snowpaypo"
                           for="p_method_snowpaypo">
                        <span class="radio-wrapper__icon"></span>
                        <span class="payment-methods__title">
PayPo - kup teraz, zapłać za 30 dni </span>
                    </label>
                    <div class="payment-methods__desc payment-method-desc-for-snowpaypo" style="display: none;">
                        Po złożeniu zamówienia nastąpi przekierowanie do PayPo. Kup teraz, zapłać później z PayPo. Masz
                        30 dni na realizację płatności. <a href="https://www.eobuwie.com.pl/metody-platnosci"
                                                           target="_blank">Poznaj szczegóły.</a></div>
                </div>
            </div>
        </div>
    </div>
    <div>
        <label class="label-bold" for="order-value">Wartość zamówienia</label>
        <input class="basket-value-input" id="order-value" type="text" readonly value="${order.calculateOrderValue()}">
    </div>
    <div>
        <form:form method="post" id="proceed-payment" cssStyle="float: right"
                   action="payment-successful?orderId=${order.getOrderId()}"
                   modelAttribute="paymentDetails" onsubmit="clearBasketContents()">

        </form:form>
        <form:form method="post" id="cancel-order" action="order-cancelled?orderId=${order.getOrderId()}"
                   onsubmit="clearBasketContents()">
        </form:form>
    </div>
    <div>
        <input type="submit" form="cancel-order" class="btn btn-danger" value="Anuluj"/>
        <input type="submit" name="submit" form="proceed-payment" class="btn btn-success" value="Zapłać"/>
    </div>

</div>
</body>

</html>

