<%-- 
    Document   : singleSubject
    Created on : Jan 28, 2021, 9:54:57 AM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Quiz Online - Quiz</title>
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
        <!-- Third party plugin CSS-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css" rel="stylesheet" />
        
        <link href="css/styles.css" rel="stylesheet" />
        <style>
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
                <p class="navbar-brand js-scroll-trigger" >Quiz Online</p>
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
        <div style="margin-left: 20px;">
            <h2>Subject: ${sessionScope.subjectName}</h2> 
            <h3>Time: 60'</h3>
            <c:if test="${not empty sessionScope.totalScore}">
                <p style="color: red; font-size: 30px">Correct: ${sessionScope.numCorrectAnswer}/50</p>
                <p style="color: red; font-size: 30px">Score: ${sessionScope.totalScore}</p>
                <div style="width: 80%; text-align: center; margin: 20px">
                    <form action="homePage" method="POST">
                        <input type="submit" value="Return home" />
                    </form>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.totalScore}">
            <div style="width: 90%; margin: 50px;">
                <form action="saveAnswer" method="POST">
                    <c:set var="question" value="${sessionScope.questionQuiz}"/>
                    <h5>Question ${sessionScope.questionNum + 1}: ${question.questionContent}</h5>

                    <c:forEach var="answer" items="${question.listAnswer}" varStatus="counter">
                        <c:if test="${not empty question.selectedAnswerId}">
                            <c:if test="${question.selectedAnswerId eq answer.answerId}">
                                <input type="radio" id="${counter.count}" name="answerId" value="${answer.answerId}" checked=""/>
                                <label for="${counter.count}">${answer.answerContent}</label>
                            </c:if>
                            <c:if test="${question.selectedAnswerId ne answer.answerId}">
                                <input type="radio" id="${counter.count}" name="answerId" value="${answer.answerId}"/>
                                <label for="${counter.count}">${answer.answerContent}</label>
                            </c:if>
                        </c:if>
                        <c:if test="${empty question.selectedAnswerId}">
                            <input type="radio" id="${counter.count}" name="answerId" value="${answer.answerId}"/>
                            <label for="${counter.count}">${answer.answerContent}</label>
                        </c:if>
                        <br/>
                    </c:forEach>
                    <input type="submit" value="Enter" />
                    <input type="hidden" name="questionId" value="${question.questionId}" />
                </form>
                    <br/>
                <c:forEach begin="1" end="50" varStatus="counter">
                    <form action="getQuestionQuiz" method="POST" style="float:left;">
                        <input type="submit" value="${counter.count}"/>
                        <input type="hidden" name="questionNum" value="${counter.count}" />
                    </form>
                </c:forEach>
            </div>
                <div style="width: 80%; text-align: center; margin: 20px">
                    <form action="finishedQuiz" method="POST">
                        <input type="submit" value="Finish" />
                    </form>
                </div>
            </c:if>
                    
        </div>
        <!-- Footer-->
        <footer class="bg-light py-5" id="footer">
            <div class="container"><div class="small text-center text-muted">Copyright © 2020 - Quiz Online</div></div>
        </footer>
        <!-- Bootstrap core JS-->
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        
    </body>
</html>
