<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">

<head>
    <title>System zarządzania księgarnią</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <script src="js/basket.js"></script>
    <link rel="stylesheet" type="text/css" href="css/registration.css">

</head>

<body>

<jsp:include page="header.jsp"/>

<br>

<div class="container">
    <h3 class="text-center">Dane klienta</h3>
    <hr>
<%--    <form method="post" name="products">--%>

    <%--    </form>--%>




        <div class="container register">
            <div class="row">
<%--                register-right --%>
                <div class="col-md-9 ">
                    <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Osoba</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Firma</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <h3 class="register-heading">Osoba prywatna</h3>
                            <div class="row register-form">
                                <div class="col-md-6">

                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="Imię *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="Nazwisko *" value="" />
                                    </div>



                                    <div class="form-group">
                                        <input type="email" class="form-control" placeholder="Your Email *" value="" />
                                    </div>

                                    <div class="form-group">
                                        <input type="password" class="form-control" placeholder="Password *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control"  placeholder="Confirm Password *" value="" />
                                    </div>
<%--                                    <div class="form-group">--%>
<%--                                        <div class="maxl">--%>
<%--                                            <label class="radio inline">--%>
<%--                                                <input type="radio" name="gender" value="male" checked>--%>
<%--                                                <span> Male </span>--%>
<%--                                            </label>--%>
<%--                                            <label class="radio inline">--%>
<%--                                                <input type="radio" name="gender" value="female">--%>
<%--                                                <span>Female </span>--%>
<%--                                            </label>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
                                </div>
                                <div class="col-md-6">

                                    <div class="form-group">
                                        <input type="text" minlength="10" maxlength="10" name="txtEmpPhone" class="form-control" placeholder="Your Phone *" value="" />
                                    </div>
<%--                                    <div class="form-group">--%>
<%--                                        <select class="form-control">--%>
<%--                                            <option class="hidden"  selected disabled>Please select your Sequrity Question</option>--%>
<%--                                            <option>What is your Birthdate?</option>--%>
<%--                                            <option>What is Your old Phone Number</option>--%>
<%--                                            <option>What is your Pet Name?</option>--%>
<%--                                        </select>--%>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
<%--                                        <input type="text" class="form-control" placeholder="Enter Your Answer *" value="" />--%>
<%--                                    </div>--%>
                                    <input type="submit" class="btn btn-primary" value="Zarejestruj"/>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade show" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <h3  class="register-heading">Apply as a Hirer</h3>
                            <div class="row register-form">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="First Name *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="Last Name *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="email" class="form-control" placeholder="Email *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="text" maxlength="10" minlength="10" class="form-control" placeholder="Phone *" value="" />
                                    </div>


                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="password" class="form-control" placeholder="Password *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" placeholder="Confirm Password *" value="" />
                                    </div>

                                    <input type="submit" class="btnRegister"  value="Register"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>



</div>
</body>

</html>
