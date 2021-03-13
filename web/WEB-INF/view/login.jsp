<%-- 
    Document   : login
    Created on : Jan 8, 2021, 4:37:08 PM
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
                    <form class="login" action="login" method="POST">
                        <span class="login100-form-logo">
                            <i class="zmdi zmdi-landscape"></i>
                        </span>
                        <span class="login100-form-title p-b-34 p-t-27">
                            Log in
                        </span>					
                        <div class="wrap-input100 validate-input" data-validate = "Enter email">
                            <input class="input100" type="text" name="email" placeholder="Email" value="${sessionScope.email}">
                            <span class="focus-input100" data-placeholder="&#xf207;"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Enter password">
                            <input class="input100" type="password" name="password" placeholder="Password">
                            <span class="focus-input100" data-placeholder="&#xf191;"></span>
                        </div>
                        <c:if test="${not empty sessionScope.accountNotFound}">
                            <p style="color: #1adeed; font-size: 15px; margin-top: -5px;">${sessionScope.accountNotFound}</p>
                        </c:if>
                        <c:if test="${not empty sessionScope.deactivate}">
                            <p style="color: #1adeed; font-size: 15px; margin-top: -5px;">${sessionScope.deactivate}</p>
                        </c:if>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn">
                                Login
                            </button>
                        </div>
                        <div class="text-center p-t-90">
                            <a class="txt1" href="registerPage" style="font-size: 16px;">
                                Not account!!! Register
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
