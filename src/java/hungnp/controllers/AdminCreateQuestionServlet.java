/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.controllers;

import hungnp.dao.AnswerDAO;
import hungnp.dao.QuestionDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Win 10
 */
@WebServlet(name = "AdminCreateQuestionServlet", urlPatterns = {"/AdminCreateQuestionServlet"})
public class AdminCreateQuestionServlet extends HttpServlet {
    private static final String ADMIN_CREATE_QUESTION_PAGE_CONTROLLER= "/AdminCreateQuestionPageServlet";
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
    private String getQuestionIdFromAmount(int amountQuestion){
        if(amountQuestion >= 0 && amountQuestion <= 9)
            return "Q000000"+ amountQuestion;
        else if(amountQuestion >= 10 && amountQuestion <= 99)
            return "Q00000"+ amountQuestion;
        else if(amountQuestion >= 100 && amountQuestion <= 999)
            return "Q0000"+ amountQuestion;
        else if(amountQuestion >= 1000 && amountQuestion <= 9999)
            return "Q000"+ amountQuestion;
        else if(amountQuestion >= 10000 && amountQuestion <= 99999)
            return "Q00"+ amountQuestion;
        else if(amountQuestion >= 100000 && amountQuestion <= 999999)
            return "Q0"+ amountQuestion;
        return "Q"+ amountQuestion;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FILE_NOT_FOUND_PAGE;
        try {
            String questionContent = request.getParameter("questionContent");
            String questionStatusId = request.getParameter("questionStatus");
            String subjectId = request.getParameter("subject");
            String answer1 = request.getParameter("answer1");
            String answer2 = request.getParameter("answer2");
            String answer3 = request.getParameter("answer3");
            String answer4 = request.getParameter("answer4");
            String answerCorrect = request.getParameter("correcting");
            
            QuestionDAO questionDAO = new QuestionDAO();
            boolean checkingQuestion = questionDAO.createQuestion(questionContent, questionStatusId, subjectId);
            if(checkingQuestion){
                int amountQuestion = questionDAO.countQuestion();
                if (amountQuestion > -1) {
                    String questionId = getQuestionIdFromAmount(amountQuestion);
                    AnswerDAO answerDAO = new AnswerDAO();
                    boolean correcting1 = false;
                    boolean correcting2 = false;
                    boolean correcting3 = false;
                    boolean correcting4 = false;
                    if(answerCorrect.equals("answer1")){
                        correcting1=true;
                    }else if(answerCorrect.equals("answer2")){
                        correcting2=true;
                    }else if(answerCorrect.equals("answer3")){
                        correcting3=true;
                    }else if(answerCorrect.equals("answer4")){
                        correcting4=true;
                    }
                    boolean checkingAnswer1 = answerDAO.createAnswer(answer1, questionId, correcting1);
                    boolean checkingAnswer2 = answerDAO.createAnswer(answer2, questionId, correcting2);
                    boolean checkingAnswer3 = answerDAO.createAnswer(answer3, questionId, correcting3);
                    boolean checkingAnswer4 = answerDAO.createAnswer(answer4, questionId, correcting4);
                    if (checkingAnswer1 && checkingAnswer2 && checkingAnswer3 && checkingAnswer4){
                        url = ADMIN_CREATE_QUESTION_PAGE_CONTROLLER;
                    }
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            log("AdminCreateQuestionServlet_processRequest:"+e.getMessage());
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























































