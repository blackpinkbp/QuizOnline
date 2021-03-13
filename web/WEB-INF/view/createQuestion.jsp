<%-- 
    Document   : createQuestion
    Created on : Feb 1, 2021, 10:27:49 AM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Admin - Create Question</title>
        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
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
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
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
                        <h1 class="h3 mb-2 text-gray-800">Create Questions</h1>
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <form action="createQuestion" method="POST">
                                    <c:set var="error" value="${sessionScope.createQuestionError}"/>
                                    Content: <input type="text" name="questionContent" value="${sessionScope.questionContent}" style="width: 100%;"/>
                                    <p style="color: red">${error.questionContentEmptyError}</p>
                                    <br/><br/>
                                    Status: <select name="questionStatus">
                                        <c:forEach var="questionStatus" items="${sessionScope.listQuestionStatus}">
                                            <option value="${questionStatus.questionStatusId}">${questionStatus.questionStatusName}</option>
                                        </c:forEach>
                                    </select>
                                    <br/><br/>
                                    Subject: <select name="subject">Subject:
                                        <c:forEach var="subject" items="${sessionScope.listSubject}">
                                            <option value="${subject.subjectId}">${subject.subjectName}</option>
                                        </c:forEach>
                                    </select>
                                    <br/><br/>
                                    <div class="table-responsive">
                                        <table class="table table-bordered" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Answer Content</th>
                                                    <th>Correcting</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><input type="text" name="answer1" value="${sessionScope.answer1}" style="width: 100%;"/>
                                                    <p style="color: red">${error.answerFirstContentEmptyError}</p>
                                                    </td>
                                                    
                                                    <td> <input type="radio" name="correcting" value="answer1" checked="checked"/> </td>
                                                </tr>
                                                <tr>
                                                    <td><input type="text" name="answer2" value="${sessionScope.answer2}" style="width: 100%;"/>
                                                        <p style="color: red">${error.answerSecondContentEmptyError}</p>
                                                        <p style="color: red">${error.answerSecondDuplicationError}</p>
                                                    </td>
                                                    <td> <input type="radio" name="correcting" value="answer2" /> </td>
                                                </tr>
                                                <tr>
                                                    <td><input type="text" name="answer3" value="${sessionScope.answer3}" style="width: 100%;"/>
                                                        <p style="color: red">${error.answerThirdContentEmptyError}</p>
                                                        <p style="color: red">${error.answerThirdDuplicationError}</p>
                                                    </td>
                                                    <td> <input type="radio" name="correcting" value="answer3" /> </td>
                                                </tr>
                                                <tr>
                                                    <td><input type="text" name="answer4" value="${sessionScope.answer4}" style="width: 100%;"/>
                                                        <p style="color: red">${error.answerFourthContentEmptyError}</p>
                                                        <p style="color: red">${error.answerFourthDuplicationError}</p>
                                                    </td>
                                                    <td> <input type="radio" name="correcting" value="answer4" /> </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div style="text-align: center">
                                        <input type="submit" value="Create" />
                                    </div>
                                </form>
                            </div>
                        </div>
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
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>
    </body>
</html>
