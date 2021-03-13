/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.controllers;

import hungnp.dao.QuestionStatusDAO;
import hungnp.dao.SubjectDAO;
import hungnp.dto.QuestionStatusDTO;
import hungnp.dto.SubjectDTO;
import java.io.IOException;
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
@WebServlet(name = "AdminTableServlet", urlPatterns = {"/AdminTableServlet"})
public class AdminTableServlet extends HttpServlet {
    private static final String ADMIN_TABLE_PAGE="/WEB-INF/view/adminTable.jsp";
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
        String url=ADMIN_TABLE_PAGE;
        try {
            HttpSession session = request.getSession();
            session.setAttribute("deactivate", "deactivate");
            
            QuestionStatusDAO questionStatusDAO = new QuestionStatusDAO();
            List<QuestionStatusDTO> listQuestionStatus = questionStatusDAO.getAllQuestionStatus();
            SubjectDAO subjectDAO = new SubjectDAO();
            List<SubjectDTO> listSubject = subjectDAO.getAllSubject();

            session.setAttribute("listQuestionStatus", listQuestionStatus);
            session.setAttribute("listSubject", listSubject);

        } catch (Exception e) {
//            e.printStackTrace();
            log("AdminTableServlet_processRequest:"+e.getMessage());
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
























































































































