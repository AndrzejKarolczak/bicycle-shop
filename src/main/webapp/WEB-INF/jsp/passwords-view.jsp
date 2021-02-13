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
    <script src="js/registration.js"></script>
    <link rel="stylesheet" type="text/css" href="css/registration.css">
</head>
<body>
<div class="form-group">
    <input type="password" id="password-first-${param.suffix}" name="passwordFirst" ${param.disabled} ${param.required}
           class="form-control" placeholder="Podaj hasło" value=""/>
</div>
<div class="form-group">
    <input type="password" id="password-second-${param.suffix}" name="passwordSecond" ${param.disabled}
    ${param.required} class="form-control" placeholder="Potwierdź hasło" value=""/>
</div>
</body>
</html>