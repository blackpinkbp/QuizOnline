/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.controllers;

import hungnp.dao.QuestionDAO;
import hungnp.dao.QuizDAO;
import hungnp.dao.QuizDetailDAO;
import hungnp.dto.QuestionDTO;
import hungnp.dto.QuizDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Win 10
 */
@WebServlet(name = "SearchQuizServlet", urlPatterns = {"/SearchQuizServlet"})
public class SearchQuizServlet extends HttpServlet {
    private static final String QUIZ_HISTORY_DETAIL_PAGE="/WEB-INF/view/quizHistoryDetail.jsp";
    private static final String FILE_NOT_FOUND_PAGE="/WEB-INF/view/fileNotFound.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=FILE_NOT_FOUND_PAGE;
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("noQuiz");
            
            String subjectId = request.getParameter("subjectId");
            if(subjectId==null){
                subjectId = (String) session.getAttribute("subjectId");
            }
//            System.out.println("subj:"+sub);
            QuizDAO quizDAO = new QuizDAO();
            String accountId = (String) session.getAttribute("accountEmail");
            List<String> listQuizId = quizDAO.getAllQuizIdBySubjectId(subjectId, accountId);
            if(listQuizId!=null){
                int numOfPage= listQuizId.size();
                String page = request.getParameter("page");
                int currentPage = 0;
                if (page != null) {
                    if (page.matches("^\\d+$")) {
                        currentPage = Integer.parseInt(page);
                        if(currentPage>0 && currentPage<=numOfPage){
                            url = QUIZ_HISTORY_DETAIL_PAGE;
                        }
                    }
                } else {
                    currentPage = 1;
                    url = QUIZ_HISTORY_DETAIL_PAGE;
                }
                if(currentPage>0){
                    String quizId = listQuizId.get(currentPage-1);
                    QuizDTO quiz = quizDAO.getQuizByID(quizId);
                    
                    QuizDetailDAO quizDetailDAO = new QuizDetailDAO();
                    List<String> listQuestionId = quizDetailDAO.getListQuestionIdInQuiz(quizId);
                    
                    
                    List<String> listSelectedAnswerId = new ArrayList<>();
                    Map<String, String> mapSelectedQuestion = new HashMap<>();
                    
                    if(quizDetailDAO.getAllSelectedAnswerID(quizId)!=null){
                        listSelectedAnswerId = quizDetailDAO.getAllSelectedAnswerID(quizId);
                        QuestionDAO questionDAO = new QuestionDAO();
                        for (String selectedAnswerId : listSelectedAnswerId) {
                            String selectedQuestionId = questionDAO.getQuestionIdBySelectedAnswerId(selectedAnswerId);
                            mapSelectedQuestion.put(selectedQuestionId, selectedAnswerId);
                        }
                    }
                    
                    List<QuestionDTO> listQuestion = new ArrayList<>();
                    QuestionDAO questionDAO = new QuestionDAO();
                    for (String questionId : listQuestionId) {
                        QuestionDTO question = questionDAO.getInfoQuestionFromID(questionId);
                        if(mapSelectedQuestion.containsKey(questionId)){
//                            System.out.println("selec:"+mapSelectedQuestion.get(questionId)+"; "+questionId);
                            question.setSelectedAnswerId(mapSelectedQuestion.get(questionId));
                        }
                        listQuestion.add(question);
                    }
                    quiz.setListQuestion(listQuestion);
                    
                    
                    session.setAttribute("quizDetail", quiz);
                    session.setAttribute("numOfPage", numOfPage);
                    session.setAttribute("currentPage", currentPage);
                    
                }
            }else{
                session.setAttribute("noQuiz", "Don't found any quiz");
                url = QUIZ_HISTORY_DETAIL_PAGE;
            }
            session.setAttribute("subjectId", subjectId);
        } catch (Exception e) {
//            e.printStackTrace();
            log("SearchQuizServlet_processRequest:"+e.getMessage());
        }finally{
            this.getServletContext().getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}















































































