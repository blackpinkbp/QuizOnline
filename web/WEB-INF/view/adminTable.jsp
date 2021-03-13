<%-- 
    Document   : adminTable
    Created on : Jan 28, 2021, 9:54:16 PM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Admin - Tables</title>
        <!-- Custom fonts for this template -->
        <link href="css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="css/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>

    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">
            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
                <!-- Sidebar - Brand -->
                <div class="sidebar-brand d-flex align-items-center justify-content-center">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Admin</div>
                </div>
                <!-- Divider -->
                <hr class="sidebar-divider my-0">
                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="createQuestionPage">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Create Question</span></a>
                </li>
                <!-- Nav Item - Tables -->
                <li class="nav-item active">
                    <a class="nav-link" href="adminTable">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Tables</span></a>
                </li>
                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">
                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>
            </ul>
            <!-- End of Sidebar -->
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">
                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">
                            <div class="topbar-divider d-none d-sm-block"></div>
                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <div class="nav-link dropdown-toggle" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                    <img class="img-profile rounded-circle"
                                         src="img/undraw_profile.svg">
                                </div>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="logout">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </nav>
                    <!-- End of Topbar -->
                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Tables</h1>
                        <form action="search" method="POST">
                            <h5>Please select 1 option to search:</h5>
                            <c:if test="${sessionScope.optionSearch eq sessionScope.questionName}">
                                <input type="radio" id="name" name="optionSearch" value="questionName" checked=""/>
                            </c:if>
                            <c:if test="${sessionScope.optionSearch ne sessionScope.questionName}">
                                <input type="radio" id="name" name="optionSearch" value="questionName"/>
                            </c:if>
                                <label for="name">Question Name: </label>
                                <input type="text" name="questionName" value="${sessionScope.searchQuestionName}" />
                            <br/>
                            
                            <c:if test="${sessionScope.optionSearch eq sessionScope.questionStatus}">
                                <input type="radio" id="status" name="optionSearch" value="questionStatus" checked=""/>
                            </c:if>
                            <c:if test="${sessionScope.optionSearch ne sessionScope.questionStatus}">
                                <input type="radio" id="status" name="optionSearch" value="questionStatus"/>
                            </c:if>
                                <label for="status">Question Status: </label>
                                <select name="questionStatusId">
                                    <c:forEach var="questionStatus" items="${sessionScope.listQuestionStatus}">
                                        <c:if test="${sessionScope.searchQuestionStatusId eq questionStatus.questionStatusId}">
                                            <option value="${questionStatus.questionStatusId}" selected="selected">
                                                ${questionStatus.questionStatusName}
                                            </option>
                                        </c:if>
                                        <c:if test="${sessionScope.searchQuestionStatusId ne questionStatus.questionStatusId}">
                                            <option value="${questionStatus.questionStatusId}">
                                                ${questionStatus.questionStatusName}
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            <br/>
                            
                            <c:if test="${sessionScope.optionSearch eq sessionScope.subject}">
                                <input type="radio" id="subject" name="optionSearch" value="subject" checked="checked"/>
                            </c:if>
                            <c:if test="${sessionScope.optionSearch ne sessionScope.subject}">
                                <input type="radio" id="subject" name="optionSearch" value="subject"/>
                            </c:if>
                                <label for="subject">Subject Name: </label>
                                <select name="subjectId">
                                    <c:forEach var="subject" items="${sessionScope.listSubject}">
                                        <c:if test="${sessionScope.searchSubjectId eq subject.subjectId}">
                                            <option value="${subject.subjectId}" selected="selected">${subject.subjectName}</option>
                                        </c:if>
                                        <c:if test="${sessionScope.searchSubjectId ne subject.subjectId}">
                                            <option value="${subject.subjectId}">${subject.subjectName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            <br/>
                            <input type="submit" value="Search" />
                        </form><br/>
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">${sessionScope.subjectName}</h6>
                            </div>
                            <div class="card-body">
                                <c:if test="${empty sessionScope.noQuestion}">
                                    <c:forEach var="question" items="${sessionScope.listQuestion}" varStatus="counter"><!--L<Q>-->
                                        <form action="delete" method="POST">
                                            <div>
                                                <p style="font-weight: bold;">
                                                    ${counter.count}: ${question.questionContent}
                                                </p>
                                                <span style="margin-right: 50px">Status: ${question.questionStatus}</span>
                                                <span>Created Date: ${question.createdDate}</span>
                                            </div>
                                            <div class="table-responsive">
                                                <table class="table table-bordered" width="100%" cellspacing="0">
                                                    <thead>
                                                        <tr>
                                                            <th>Answer Content</th>
                                                            <th>Correcting</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="answer" items="${question.listAnswer}"><!--L<A>-->
                                                            <tr>
                                                                <td>${answer.answerContent}</td>
                                                                <td>${answer.correcting}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                <a href="updateQuestionPage?questionId=${question.questionId}" 
                                                   style="text-decoration: none; margin-right: 20px;"> 
                                                    Update
                                                </a>
                                                <c:if test="${sessionScope.deactivate ne question.questionStatus}">
                                                    <input type="submit" value="Delete"/>
                                                </c:if>
                                                <input type="hidden" name="questionId" value="${question.questionId}" />
                                            </div>
                                        </form>
                                        <hr><br/>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${not empty sessionScope.noQuestion}">
                                    <h5>${sessionScope.noQuestion}</h5>
                                </c:if>
                            </div>
                        </div>
                        <nav>
                            <ul class="pagination justify-content-center">
                                <c:forEach begin="1" end="${sessionScope.numOfPage}" step="1" varStatus="counter">
                                    <c:if test="${counter.count eq sessionScope.currentPage}">
                                        <li class="page-item active">
                                            <a class="page-link" href="search?page=${counter.count}">
                                                ${counter.count}
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${counter.count ne sessionScope.currentPage}">
                                        <li class="page-item">
                                            <a class="page-link" href="search?page=${counter.count}">
                                                ${counter.count}
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                    <!-- /.container-fluid -->
                </div>
                <!-- End of Main Content -->
                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2020</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->
        </div>
        <!-- End of Page Wrapper -->
        <!-- Bootstrap core JavaScript-->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>
    </body>
</html>
