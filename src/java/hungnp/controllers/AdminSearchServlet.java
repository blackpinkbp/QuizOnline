/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.controllers;

import hungnp.dao.QuestionDAO;
import hungnp.dto.QuestionDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@WebServlet(name = "AdminSearchServlet", urlPatterns = {"/AdminSearchServlet"})
public class AdminSearchServlet extends HttpServlet {

    private static final String FILE_NOT_FOUND_PAGE = "/WEB-INF/view/fileNotFound.jsp";
    private static final String ADMIN_TABLE_PAGE = "/WEB-INF/view/adminTable.jsp";

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
        String url = ADMIN_TABLE_PAGE;
        try {
            HttpSession session = request.getSession();
            session.setAttribute("questionName", "questionName");
            session.setAttribute("questionStatus", "questionStatus");
            session.setAttribute("subject", "subject");

            session.removeAttribute("noQuestion");
            session.removeAttribute("currentPage");
            session.removeAttribute("numOfPage");
            session.removeAttribute("listQuestion");
            session.removeAttribute("subjectName");

            String optionSearch = request.getParameter("optionSearch");
            if(optionSearch==null){
                optionSearch = (String) session.getAttribute("optionSearch");
            }
            if (optionSearch.equals("questionName")) {
                String searchQuestionName = request.getParameter("questionName");
                if(searchQuestionName==null){
                    searchQuestionName = (String) session.getAttribute("searchQuestionName");
                }
                session.setAttribute("optionSearch", optionSearch);
                session.setAttribute("searchQuestionName", searchQuestionName);
                
//                System.out.println("op:"+optionSearch);
//                System.out.println("searchValue:"+searchQuestionName);
                
                QuestionDAO questionDAO = new QuestionDAO();
                Map<String, List<String>> mapQuestionId = questionDAO.searchMapQuestionIdByContent(searchQuestionName);
                if(mapQuestionId != null){
                    int numOfPage = mapQuestionId.size();
                    String page = request.getParameter("page");
                    int currentPage = 0;
                    if(page!=null){
                        if(page.matches("^[1-"+numOfPage+"]$")){
                            currentPage = Integer.parseInt(page);
                        }else{
                            url = FILE_NOT_FOUND_PAGE;
                        }
                    }else{
                        currentPage = 1;
                    }
                    if(currentPage > 0){
                        Set setSubjectName = mapQuestionId.keySet();
                        String[] arraySubjectName = new String[setSubjectName.size()];
                        setSubjectName.toArray(arraySubjectName);
                        String subjectName = arraySubjectName[currentPage-1];

                        List<String> listQuestionId = mapQuestionId.get(subjectName);
                        List<QuestionDTO> listQuestion = new ArrayList<>();
                        for (String questionId : listQuestionId) {
                            QuestionDTO question = questionDAO.getQuestionFromID(questionId);
                            listQuestion.add(question);
                        }
                        
                        session.setAttribute("subjectName", subjectName);
                        session.setAttribute("listQuestion", listQuestion);
                        session.setAttribute("numOfPage", numOfPage);
                        session.setAttribute("currentPage", currentPage);
                        
                        
                    }
                }else{
                    session.setAttribute("noQuestion", "Don't have any question is matched");
                }
                
                
            } else if (optionSearch.equals("questionStatus")) {
                String searchQuestionStatusId = request.getParameter("questionStatusId");
                if(searchQuestionStatusId == null){
                    searchQuestionStatusId = (String) session.getAttribute("searchQuestionStatusId");
                }
                session.setAttribute("optionSearch", optionSearch);
                session.setAttribute("searchQuestionStatusId", searchQuestionStatusId);
                
                QuestionDAO questionDAO = new QuestionDAO();
                Map<String, List<String>> mapQuestionId = questionDAO.searchMapQuestionIdByStatus(searchQuestionStatusId);
                if(mapQuestionId!=null){
                    int numOfPage = mapQuestionId.size();
                    int currentPage = 0;
                    String page = request.getParameter("page");
                    if(page!=null){
                        if(page.matches("^[1-"+numOfPage+"]$")){
                            currentPage = Integer.parseInt(page);
                        }else{
                            url = FILE_NOT_FOUND_PAGE;
                        }
                    }else{
                        currentPage = 1;
                    }
                    
                    if(currentPage > 0){
                        Set setSubjectName = mapQuestionId.keySet();
                        String[] arraySubjectName = new String[setSubjectName.size()];
                        setSubjectName.toArray(arraySubjectName);
                        String subjectName = arraySubjectName[currentPage-1];
                        
                        List<QuestionDTO> listQuestion = new ArrayList<>();
                        List<String> listQuestionId = mapQuestionId.get(subjectName);
                        for (String questionId : listQuestionId) {
                            QuestionDTO question = questionDAO.getQuestionFromID(questionId);
                            listQuestion.add(question);
                        }
                        
                        session.setAttribute("listQuestion", listQuestion);
                        session.setAttribute("subjectName", subjectName);
                        session.setAttribute("currentPage", currentPage);
                        session.setAttribute("numOfPage", numOfPage);
                    }
                    
                }else{
                    session.setAttribute("noQuestion", "Don't have any question is matched");
                }
                
                
            } else if (optionSearch.equals("subject")) {
                String searchSubjectId = request.getParameter("subjectId");
                if(searchSubjectId == null){
                    searchSubjectId = (String) session.getAttribute("searchSubjectId");
                }
                session.setAttribute("optionSearch", optionSearch);
                session.setAttribute("searchSubjectId", searchSubjectId);
                
                QuestionDAO questionDAO = new QuestionDAO();
                double totalQuestion = questionDAO.countQuestionIdBySubject(searchSubjectId);
                double amountQuestionPerPage = 20;
                if(totalQuestion > 0){
                    int numOfPage = (int) Math.ceil(totalQuestion / amountQuestionPerPage);
                    int skippedQuestion = 0;
                    int nextQuestion = 0;
                    int currentPage = 0;
                    String page = request.getParameter("page");
                    if (page != null) {
                        if (page.matches("^[1-"+numOfPage+"]$")) {
                            currentPage = Integer.parseInt(page);
                            if(currentPage == numOfPage){
                                if(totalQuestion % amountQuestionPerPage == 0){
                                    nextQuestion = 20;
                                }else{
                                    nextQuestion = (int) (totalQuestion % amountQuestionPerPage);
                                }
                            }else{
                                nextQuestion = 20;
                            }
                            skippedQuestion = (int) (currentPage * amountQuestionPerPage - 20);
                        }else{
                            url = FILE_NOT_FOUND_PAGE;
                        }
                    } else {
                        currentPage = 1;
                        nextQuestion = 20;
                    }
                    
                    if( currentPage > 0){
                        List<String> listQuestionId = questionDAO.searchQuestionIDbySubject(searchSubjectId, skippedQuestion, nextQuestion);
                        if(listQuestionId!=null){
                            List<QuestionDTO> listQuestion = new ArrayList<>();
                            for (String questionId : listQuestionId) {
                                QuestionDTO question = questionDAO.getQuestionFromID(questionId);
                                listQuestion.add(question);
                            }
                            
                            session.setAttribute("numOfPage", numOfPage);
                            session.setAttribute("currentPage", currentPage);
                            session.setAttribute("listQuestion", listQuestion);
                        }
                    }
                    
                }else{
                    session.setAttribute("noQuestion", "Don't have any question is matched");
                }
            }

        } catch (Exception e) {
//            e.printStackTrace();
            log("AdminSearchServlet_processRequest:" + e.getMessage());
        } finally {
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























































































































































