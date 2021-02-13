<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="pl">

<head>

    <title>Strona logowania</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
<jsp:include page="header.jsp"/>

<br>
<%--<div>
    <div id="loginbox" style="margin-top: 50px;" class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign In</div>
            </div>

            <div style="padding-top: 30px" class="panel-body">

                <form action="${pageContext.request.contextPath}/user-authentication"
                      method="POST" class="form-horizontal">
                    <div class="form-group col-xs-15">
                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                Invalid username and password.
                            </div>
                        </c:if>

                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                You have been logged out.
                            </div>
                        </c:if>
                    </div>


                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" name="username" placeholder="username" class="form-control">
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input type="password" name="password" placeholder="password" class="form-control">
                    </div>


                    <div style="margin-top: 10px" class="form-group">
                        <div class="col-sm-6 controls">
                            <button type="submit" class="btn btn-success">Login</button>
                        </div>
                    </div>

                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>--%>


<main class="login-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Logowanie</div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/user-authentication" method="post">
<%--                            <div class="form-group col-xs-15">--%>
<%--                                <c:if test="${param.error != null}">--%>
<%--                                    <div class="alert alert-danger col-xs-offset-1 col-xs-10">--%>
<%--                                        Zły login lub hasło--%>
<%--                                    </div>--%>
<%--                                </c:if>--%>

<%--                                <c:if test="${param.logout != null}">--%>
<%--                                    <div class="alert alert-success col-xs-offset-1 col-xs-10">--%>
<%--                                        Wylogowano--%>
<%--                                    </div>--%>
<%--                                </c:if>--%>
<%--                            </div>--%>

                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Login</label>
                                <div class="col-md-6">
                                    <input type="text" id="email_address" class="form-control" name="email-address"
                                           required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Hasło</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" required>
                                </div>
                            </div>

                            <%--                            <div class="form-group row">--%>
                            <%--                                <div class="col-md-6 offset-md-4">--%>
                            <%--                                    <div class="checkbox">--%>
                            <%--                                        <label>--%>
                            <%--                                            <input type="checkbox" name="remember"> Remember Me--%>
                            <%--                                        </label>--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>

                            <div class="col-md-6 offset-md-4">
                                <input type="submit" class="btn btn-primary" value="Zaloguj">
                                <a href="#" class="btn btn-link" style="float: right">
                                    Zapomniałeś hasła?
                                </a>
                            </div>

<%--                            <input type="hidden"--%>
<%--                                   name="${_csrf.parameterName}"--%>
<%--                                   value="${_csrf.token}"/>--%>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>