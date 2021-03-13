<%-- 
    Document   : register
    Created on : Jan 27, 2021, 9:38:03 AM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/material-design-iconic-font.min.css">
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <title>Quiz Online - Login</title>
    </head>
    <body>
        <div class="limiter">
            <div class="container-login100" style="background-image: url('img/bg-01.jpg');">
                <div class="wrap-login100">
                    <form class="login" action="register" method="POST">
                        <span class="login100-form-logo">
                            <i class="zmdi zmdi-landscape"></i>
                        </span>
                        <span class="login100-form-title p-b-34 p-t-27">
                            Register
                        </span>					
                        <c:set var="registerError" value="${sessionScope.registerError}"/>
                        <div class="wrap-input100 validate-input" data-validate = "Enter email">
                            <input class="input100" type="text" name="email" placeholder="Email" value="${sessionScope.email}">
                            <span class="focus-input100" data-placeholder="&#xf207;"></span>
                        </div>
                        <c:if test="${not empty registerError.emailEmptyError}">
                            <p style="color: tomato">${registerError.emailEmptyError}</p>
                        </c:if>
                        <c:if test="${not empty registerError.emailInvalidError}">
                            <p style="color: tomato">${registerError.emailInvalidError}</p>
                        </c:if>
                        <c:if test="${not empty registerError.emailLengthError}">
                            <p style="color: tomato">${registerError.emailLengthError}</p>
                        </c:if>
                        <c:if test="${not empty registerError.emailExistedError}">
                            <p style="color: tomato">${registerError.emailExistedError}</p>
                        </c:if>
                        <div class="wrap-input100 validate-input" data-validate="Enter name">
                            <input class="input100" type="text" name="name" placeholder="Name" value="${sessionScope.name}">
                            <span class="focus-input100" data-placeholder="&#xf207;"></span>
                        </div>
                        <c:if test="${not empty registerError.nameEmptyError}">
                            <p style="color: tomato">${registerError.nameEmptyError}</p>
                        </c:if>
                        <div class="wrap-input100 validate-input" data-validate="Enter password">
                            <input class="input100" type="password" name="password" placeholder="Password">
                            <span class="focus-input100" data-placeholder="&#xf191;"></span>
                        </div>
                        <c:if test="${not empty registerError.passwordEmptyError}">
                            <p style="color: tomato">${registerError.passwordEmptyError}</p>
                        </c:if> 
                        <div class="wrap-input100 validate-input" data-validate="Enter password">
                            <input class="input100" type="password" name="confirm" placeholder="Confirm">
                            <span class="focus-input100" data-placeholder="&#xf191;"></span>
                        </div>
                        <c:if test="${not empty registerError.confirmNotMatchError}">
                            <p style="color: tomato">${registerError.confirmNotMatchError}</p>
                        </c:if>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn">
                                Register
                            </button>
                        </div>
                        <div class="text-center p-t-90" style="margin-top: -40px;">
                            <a class="txt1" href="loginPage" style="font-size: 16px;">
                                Already have an account!!! Login
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
