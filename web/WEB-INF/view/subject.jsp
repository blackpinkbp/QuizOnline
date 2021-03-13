<%-- 
    Document   : subject
    Created on : Jan 27, 2021, 8:57:37 AM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Quiz Online - Subject</title>
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
        <!-- Third party plugin CSS-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <style>
            .quiz{
                width: 100%;
                text-align: center;
            }
            #footer {
                clear: both;
                top:100%;
                position: relative;
            }
            @media (min-width: 992px){
                #mainNav .navbar-brand {
                    color: rgba(0, 0, 4, 1);
                }
                #mainNav .navbar-nav .nav-item .nav-link {
                    color: rgba(0, 0, 4, 1);
                    padding: 0 1rem;
                }
                #mainNav .navbar-brand {
                    color: rgba(0, 0, 4, 1);
                }
            }
        </style>
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav" style="background-color: #1adeed;">
            <div class="container">
                <p class="navbar-brand js-scroll-trigger">Quiz Online</p>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" 
                        data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" 
                        aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto my-2 my-lg-0" style="margin-right: 5px;">
                        <li class="nav-item"><p class="nav-link js-scroll-trigger">${sessionScope.accountName}</p></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="quizHistoryPage">Quiz History</a></li>
                    </ul>
                    <form action="logout" method="POST">
                        <input type="submit" value="Logout" />
                    </form>
                </div>
            </div>
        </nav><br/><br/><br/><br/>
        <div class="quiz">
            <h2>Subject:</h2>
            <c:forEach var="subject" items="${sessionScope.listSubject}">
                <form action="takeQuiz" method="POST">
                    <h3 style="color: gold;">${subject.subjectName}</h3>
                    <input type="submit" value="Take Quiz" />
                    <input type="hidden" name="subjectId" value="${subject.subjectId}" />
                    <input type="hidden" name="subjectName" value="${subject.subjectName}" />
                </form><br/>
            </c:forEach>
            <c:if test="${not empty sessionScope.notEnough}">
                <p>${sessionScope.notEnough}</p>
            </c:if>
        </div>
        <!-- Footer-->
        <footer class="bg-light py-5" id="footer">
            <div class="container"><div class="small text-center text-muted">Copyright © 2020 - Quiz Online</div></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
