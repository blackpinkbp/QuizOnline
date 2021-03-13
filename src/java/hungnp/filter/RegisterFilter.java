/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.filter;

import hungnp.dao.AccountDAO;
import hungnp.error.RegisterError;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Win 10
 */
@WebFilter(filterName = "RegisterFilter", urlPatterns = {"/register"})
public class RegisterFilter implements Filter {
    
    private static final boolean debug = true;
    private static final String REGISTER_PAGE="register.jsp";
    private static final String REGISTER_CONTROLLER="/RegisterServlet";
    private static final String FILE_NOT_FOUND_PAGE="fileNotFound.jsp";

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public RegisterFilter() {
    }    

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) request;
        String url = FILE_NOT_FOUND_PAGE;
        try {
            HttpSession session = req.getSession();
            session.removeAttribute("registerError");
            
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            if (email != null && name != null && password != null && confirm != null) {
                RegisterError error = new RegisterError();
                boolean checking=false;
                if (email.trim().isEmpty()) {
                    error.setEmailEmptyError("email is not empty");
                    checking=true;
                } else if (!email.matches("^[a-zA-Z](\\w+)?@[a-zA-Z]+.[a-zA-Z]+(.[a-zA-Z]+)?$")) {
                    error.setEmailInvalidError("email is invalid");
                    checking=true;
                } else if (email.trim().length() > 255) {
                    error.setEmailLengthError("length of email is less than 255 characters");
                    checking=true;
                } else {
                    AccountDAO accountDAO = new AccountDAO();
                    if (accountDAO.checkExistEmail(email)) {
                        error.setEmailExistedError("email is existed");
                        checking=true;
                    }
                }
                if (name.trim().isEmpty()) {
                    error.setNameEmptyError("name is not empty");
                    checking=true;
                }
                if (password.trim().isEmpty()) {
                    error.setPasswordEmptyError("password is not empty");
                    checking=true;
                }
                if (!confirm.equals(password)) {
                    error.setConfirmNotMatchError("confirm is not the same as password");
                    checking=true;
                }
                if(checking){
                    
                    session.setAttribute("registerError", error);
                    session.setAttribute("email", email);
                    session.setAttribute("name", name);
                    url = REGISTER_PAGE;
                }else {
                    url = REGISTER_CONTROLLER;
                }
            } 
//            System.out.println("url:"+url);
            if (url != null) {
                if (url.contains(".jsp")) {
                    url = "/WEB-INF/view/" + url;
                }
                req.getRequestDispatcher(url).forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("RegisterFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("RegisterFilter()");
        }
        StringBuffer sb = new StringBuffer("RegisterFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}













































































































