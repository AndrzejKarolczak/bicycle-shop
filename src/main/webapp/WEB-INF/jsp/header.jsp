<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <li class="nav-item active"><a href="<%=request.getContextPath()%>/" class="nav-link">Strona główna</a></li>
            <li class="nav-item"><a href="<%=request.getContextPath()%>/products" class="nav-link">Produkty</a></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Produkty
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Rowery</a>
                    <a class="dropdown-item" href="#">Części do rowerów</a>
                </div>
            </li>
        </ul>

        <ul class="navbar-nav ml-auto">
            <li class="nav-item "><a href="<%=request.getContextPath()%>/new-account" class="nav-link">Załóż konto</a></li>
            <li class="nav-item "><a href="<%=request.getContextPath()%>/login" class="nav-link">Zaloguj</a></li>
            <li class="nav-item "><a href="<%=request.getContextPath()%>/basket" class="nav-link">Koszyk</a></li>
        </ul>
    </nav>
</header>
</body>
</html>