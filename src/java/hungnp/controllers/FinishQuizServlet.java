/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.controllers;

import hungnp.dao.AnswerDAO;
import hungnp.dao.QuizDAO;
import hungnp.dao.QuizDetailDAO;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "FinishQuizServlet", urlPatterns = {"/FinishQuizServlet"})
public class FinishQuizServlet extends HttpServlet {
    private static final String QUIZ_PAGE="/WEB-INF/view/quiz.jsp";
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
        String url = QUIZ_PAGE;
        try {
            HttpSession session = request.getSession();
            
            if(session.getAttribute("quizId")!=null){
                
                String quizId = (String) session.getAttribute("quizId");
                QuizDAO quizDAO  = new QuizDAO();
                if(session.getAttribute("timeout")==null){
                    Date now = new Date();
                    Timestamp endTime = new Timestamp(now.getTime());
                    quizDAO.updateEndTime(quizId, endTime);
                }
                
                QuizDetailDAO quizDetailDAO = new QuizDetailDAO();
                List<String> listSelectedAnswerId = quizDetailDAO.getAllSelectedAnswerID(quizId);
                float correctedAnswer = 0;
                AnswerDAO answerDAO = new AnswerDAO();
                for (String selectedAnswerId : listSelectedAnswerId) {
                    if(answerDAO.checkCorrectAnswerID(selectedAnswerId)){
                        correctedAnswer++;
                    }
                }

                float totalScore = correctedAnswer/50;
                
                quizDAO.updateTotalScore(quizId, totalScore);
                session.setAttribute("totalScore", totalScore);
                session.setAttribute("numCorrectAnswer", (int) correctedAnswer);
                
                session.removeAttribute("quizId");
                session.removeAttribute("listQuestion");
                session.removeAttribute("subjectName");
                session.removeAttribute("questionQuiz");
                session.removeAttribute("questionNum");
                session.removeAttribute("timeout");
                
            }
        } catch (Exception e) {
//            e.printStackTrace();
            log("FinishQuizServlet_processRequest:"+e.getMessage());
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










































