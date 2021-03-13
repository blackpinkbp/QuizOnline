<%-- 
    Document   : quizHistoryDetail
    Created on : Feb 6, 2021, 10:14:03 PM
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
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/dataTables.bootstrap4.min.css" rel="stylesheet">
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
                <form action="searchQuiz" method="POST">
                    Search quiz by subject: 
                    <select name="subjectId">
                        <c:forEach var="subject" items="${sessionScope.listSubject}">
                            <c:if test="${sessionScope.subjectId eq subject.subjectId}">
                                <option value="${subject.subjectId}" selected="">${subject.subjectName}</option>
                            </c:if>
                            <c:if test="${sessionScope.subjectId ne subject.subjectId}">
                                <option value="${subject.subjectId}">${subject.subjectName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Search" />
                </form>
                <br/>
                <c:if test="${empty sessionScope.noQuiz}">
                <div class="container-fluid" style="text-align: left;">
                    <div class="card shadow mb-4">
                        <c:set var="quiz" value="${sessionScope.quizDetail}"/>
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Quiz: ${quiz.quizId}</h6>
                            <h6 class="m-0 font-weight-bold text-primary">Start Time: ${quiz.startTime}</h6>
                            <h6 class="m-0 font-weight-bold text-primary">End Time: ${quiz.endTime}</h6>
                            <h6 class="m-0 font-weight-bold text-primary">Total Score: ${quiz.totalScore}</h6>
                        </div>
                        <div class="card-body">
                            <c:forEach var="question" items="${quiz.listQuestion}" varStatus="counter"><!--L<Q>-->
                                <div>
                                    <p style="font-weight: bold;">
                                        ${counter.count}: ${question.questionContent}
                                    </p>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-bordered" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Answer Content</th>
                                                <th>Correcting</th>
                                                <th>Selected Answer</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="answer" items="${question.listAnswer}"><!--L<A>-->
                                                <tr>
                                                    <td>${answer.answerContent}</td>
                                                    <td>${answer.correcting}</td>
                                                    <c:if test="${question.selectedAnswerId eq answer.answerId}">
                                                        <td>selected</td>
                                                    </c:if>
                                                    <c:if test="${question.selectedAnswerId ne answer.answerId}">
                                                        <td>Not Selected</td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <hr><br/>
                            </c:forEach>
                        </div>
                    </div>
                    <nav>
                        <ul class="pagination justify-content-center">
                            <c:forEach begin="1" end="${sessionScope.numOfPage}" step="1" varStatus="counter">
                                <c:if test="${counter.count eq sessionScope.currentPage}">
                                    <li class="page-item active">
                                        <a class="page-link" href="searchQuiz?page=${counter.count}">
                                            ${counter.count}
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${counter.count ne sessionScope.currentPage}">
                                    <li class="page-item">
                                        <a class="page-link" href="searchQuiz?page=${counter.count}">
                                            ${counter.count}
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>
                </c:if>
                <c:if test="${not empty sessionScope.noQuiz}">
                    <p>${sessionScope.noQuiz}</p>
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
