/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.controllers;

import hungnp.dao.AccountDAO;
import java.io.IOException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PAGE="login.jsp";
    private static final String SUBJECT_PAGE_CONTROLLER="/SubjectPageServlet";
    private static final String ADMIN_TABLE_PAGE_CONTROLLER="/AdminTableServlet";
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
        String url="";
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("deactivate");
            session.removeAttribute("accountNotFound");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String encryptedPassword=org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
            AccountDAO accountDAO = new AccountDAO();
            boolean checking = accountDAO.checkLogin(email, encryptedPassword);
            if(checking){
                String accountStatus = accountDAO.getStatusFromEmail(email);
                if(accountStatus.equals("deactivate")){
                    session.setAttribute("deactivate", "The account has deactived!!!");
                    session.setAttribute("email", email);
                    url = LOGIN_PAGE;
                }else{
                    String accountRole = accountDAO.getRoleFromEmail(email);
                    session.setAttribute("accountName", accountDAO.getAccountNameFromEmail(email));
                    session.setAttribute("accountEmail", email);
                    if(accountRole.equals("student")){
                        url= SUBJECT_PAGE_CONTROLLER;
                    }else{
                        url= ADMIN_TABLE_PAGE_CONTROLLER;
                    }
                    session.setAttribute("role", accountRole);
                }
            }else{
                session.setAttribute("accountNotFound", "Email or password is incorrect!!!");
                session.setAttribute("email", email);
                url = LOGIN_PAGE;
            }
            if (url.contains(".jsp")) {
                url = "/WEB-INF/view/" + url;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            log("LoginServlet_processRequest:"+e.getMessage());
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

































































