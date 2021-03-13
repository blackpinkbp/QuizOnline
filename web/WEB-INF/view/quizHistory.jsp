<%-- 
    Document   : quizHistory
    Created on : Feb 6, 2021, 2:02:53 PM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Quiz Online - Quiz History</title>
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
        <!-- Third party plugin CSS-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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
                    </ul>
                    <form action="logout" method="POST">
                        <input type="submit" value="Logout" />
                    </form>
                </div>
            </div>
        </nav><br/><br/><br/><br/>
        <div class="quiz">
            <h2>Quiz History</h2>
            <div>
                <c:if test="${not empty noQuiz}">
                    <p>${sessionScope.noQuiz}</p>
                </c:if>
                <c:if test="${empty noQuiz}">
                    <form action="searchQuiz" method="POST">
                        Search quiz by subject: 
                        <select name="subjectId">
                            <c:forEach var="subject" items="${sessionScope.listSubject}">
                                <option value="${subject.subjectId}">${subject.subjectName}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Search" />
                    </form>
                    <br/>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">QuizId</th>
                                <th scope="col">Subject Name</th>
                                <th scope="col">Start Time</th>
                                <th scope="col">End Time</th>
                                <th scope="col">Total Score</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="quiz" items="${sessionScope.listQuiz}">
                                <tr>
                                    <th scope="row">${quiz.quizId}</th>
                                    <td>${quiz.subjectName}</td>
                                    <td>${quiz.startTime}</td>
                                    <td>${quiz.endTime}</td>
                                    <td>${quiz.totalScore}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <a href="homePage">Return Home</a>
            </div>
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
