<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #4747d4; margin-bottom: 10px;">
        <ul class="navbar-nav">
            <li class="nav-item active"><a href="<%=request.getContextPath()%>/start" class="nav-link">Strona główna</a>
            </li>
            <li class="nav-item"><a href="<%=request.getContextPath()%>/products?productType=BICYCLE" class="nav-link">Rowery</a>
            </li>
            <li class="nav-item"><a href="<%=request.getContextPath()%>/products?productType=PART" class="nav-link">Części
                rowerowe</a></li>
            <%--            <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                Produkty
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="<%=request.getContextPath()%>/products?productType=BICYCLE">Rowery</a>
                                <a class="dropdown-item" href="<%=request.getContextPath()%>/products?productType=PART">Części do rowerów</a>
                            </div>
                        </li>--%>
        </ul>

        <ul class="navbar-nav ml-auto">
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item" style="margin-right: 20px">
                    <span class="navbar-text">
                        Zalogowany użytkownik: <sec:authentication property="principal.username"/> -
                    </span>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Konto
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/saved-customer-details">Dane</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/orders-list">Zamówienia</a>
                    </div>
                </li>

                <li class="nav-item">
                    <form id="logout" method="post" action="<%=request.getContextPath()%>/logout">
                        <a href="javascript:{}" onclick="document.getElementById('logout').submit(); return false;"
                           class="nav-link">Wyloguj</a>
                    </form>
                </li>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
            <li class="nav-item ">
                <a href="<%=request.getContextPath()%>/new-account" class="nav-link">Załóż konto</a>
            </li>
            <li class="nav-item ">
                <form id="login" method="post" action="<%=request.getContextPath()%>/login">
                    <a href="javascript:{}" onclick="document.getElementById('login').submit(); return false;"
                       class="nav-link">Zaloguj</a>
                </form>
            </li>
            </sec:authorize>
            <li class="nav-item ">
                <a href="<%=request.getContextPath()%>/basket" class="nav-link">Koszyk</a>
            </li>
        </ul>
    </nav>
</header>
</body>
</html>