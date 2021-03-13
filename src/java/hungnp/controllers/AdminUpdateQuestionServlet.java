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
@WebServlet(name = "AdminUpdateQuestionServlet", urlPatterns = {"/AdminUpdateQuestionServlet"})
public class AdminUpdateQuestionServlet extends HttpServlet {
    private static final String ADMIN_SEARCH_CONTROLLER="/AdminSearchServlet";
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
        String url = ADMIN_SEARCH_CONTROLLER;
        try {
            String questionId = request.getParameter("questionId");
            String questionContent = request.getParameter("questionContent");
            String questionStatusId = request.getParameter("questionStatus");
            String subjectId = request.getParameter("subject");
            
            String[] answerIds = request.getParameterValues("answerDTO");
            String answerIdCorrect = request.getParameter("correcting");
            
            QuestionDAO questionDAO = new QuestionDAO();
            questionDAO.updateQuestion(questionId, questionContent, questionStatusId, subjectId);
            
            AnswerDAO answerDAO = new AnswerDAO();
            for (String answerId : answerIds) {
                if(answerId.equals(answerIdCorrect)){
                    answerDAO.updateAnswer(answerId, request.getParameter(answerId), true);
                }else{
                    answerDAO.updateAnswer(answerId, request.getParameter(answerId), false);
                }
            }
            
        } catch (Exception e) {
//            e.printStackTrace();
            log("AdminUpdateQuestionServlet_processRequest:"+e.getMessage());
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





















