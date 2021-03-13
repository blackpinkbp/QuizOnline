/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
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
@WebFilter(filterName = "DispatcherFilter", urlPatterns = {"/*"})
public class DispatcherFilter implements Filter {
    
    private static final boolean debug = true;
    private static final String HOME_PAGE="home.jsp";
    private static final String LOGIN_PAGE="login.jsp";
    private static final String REGISTER_PAGE="register.jsp";
    private static final String FILE_NOT_FOUND_PAGE="fileNotFound.jsp";
    
    private static final String LOGIN_CONTROLLER="/LoginServlet";
    private static final String LOGOUT_CONTROLLER="/LogoutServlet";
    private static final String SUBJECT_PAGE_CONTROLLER="/SubjectPageServlet";
    private static final String TAKE_QUIZ_CONTROLLER="/TakeQuizServlet";
    private static final String SAVE_ANSWER_CONTROLLER="/SaveAnswerServlet";
    private static final String UPLOAD_QUESTION_QUIZ_CONTROLLER="/UploadQuestionQuizServlet";
    private static final String FINISH_QUIZ_CONTROLLER="/FinishQuizServlet";
    private static final String QUIZ_HISTORY_PAGE_CONTROLLER="/QuizHistoryPageServlet";
    private static final String SEARCH_QUIZ_CONTROLLER="/SearchQuizServlet";
    
    private static final String ADMIN_TABLE_PAGE_CONTROLLER="/AdminTableServlet";
    private static final String ADMIN_SEARCH_CONTROLLER="/AdminSearchServlet";
    private static final String ADMIN_CREATE_QUESTION_PAGE_CONTROLLER="/AdminCreateQuestionPageServlet";
    private static final String ADMIN_CREATE_QUESTION_CONTROLLER = "/AdminCreateQuestionServlet";
    private static final String ADMIN_DELETE_QUESTION_CONTROLLER="/AdminDeleteQuestionServlet";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public DispatcherFilter() {
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
        
        ServletContext context = request.getServletContext();
        HttpServletRequest req = (HttpServletRequest) request;
        //uri:  /Assignment2_NguyenPhiHung/
        //url:  http://localhost:8084/Assignment2_NguyenPhiHung/
        String uri = req.getRequestURI();
//        System.out.println("uri:"+uri);
        String url= FILE_NOT_FOUND_PAGE;
        try {
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex+1);//homePage
//            System.out.println("resource:"+resource);
            HttpSession session = req.getSession();
            String role = (String) session.getAttribute("role");
//            System.out.println("role:"+role);
//            System.out.println(resource);
            if(role==null){
                if(resource.equals("homePage") || resource.trim().isEmpty()){
                    url=HOME_PAGE;
                }else if (resource.equals("loginPage")) {
                    url = LOGIN_PAGE;
                } else if (resource.equals("login")) {
                    url = LOGIN_CONTROLLER;
                } else if (resource.equals("registerPage")) {
                    url = REGISTER_PAGE;
                } else if (resource.equals("register")) {
                    chain.doFilter(request, response);
                }
            }else if(role.equals("admin")){
                if (resource.equals("homePage") || resource.trim().isEmpty()) {
                    url = ADMIN_TABLE_PAGE_CONTROLLER;
                }else if (resource.equals("logout")) {
                    url = LOGOUT_CONTROLLER;
                } else if (resource.equals("adminTable")) {
                    url = ADMIN_TABLE_PAGE_CONTROLLER;
                } else if (resource.equals("search")){
                    chain.doFilter(request, response);
                } else if (resource.equals("createQuestionPage")){
                    url = ADMIN_CREATE_QUESTION_PAGE_CONTROLLER;
                } else if(resource.equals("createQuestion")){
                    chain.doFilter(request, response);
                } else if (resource.equals("updateQuestionPage")){
                    chain.doFilter(request, response);
                } else if (resource.equals("updateQuestion")){
                    chain.doFilter(request, response);
                } else if (resource.equals("delete")){
                    url = ADMIN_DELETE_QUESTION_CONTROLLER;
                }
            }else if(role.equals("student")){
                if (resource.equals("homePage") || resource.trim().isEmpty()) {
                    url = SUBJECT_PAGE_CONTROLLER;
                } else if (resource.equals("logout")) {
                    url = LOGOUT_CONTROLLER;
                } else if (resource.equals("takeQuiz")) {
                    url = TAKE_QUIZ_CONTROLLER;
                }else if (resource.equals("saveAnswer")){
                    url = SAVE_ANSWER_CONTROLLER;
                } else if (resource.equals("getQuestionQuiz")){
                    url = UPLOAD_QUESTION_QUIZ_CONTROLLER;
                }else if (resource.equals("finishedQuiz")){
                    url = FINISH_QUIZ_CONTROLLER;
                }else if (resource.equals("quizHistoryPage")){
                    url = QUIZ_HISTORY_PAGE_CONTROLLER;
                }else if(resource.equals("searchQuiz")){
                    url = SEARCH_QUIZ_CONTROLLER;
                }
            }
            
            if (uri.indexOf("/css/") > 0) {
                url = "/css/" + resource;
            } else if (uri.indexOf("/js/") > 0) {
                url = "/js/" + resource;
            } else if (uri.indexOf("/img/") > 0) {
                url = "/img/" + resource;
            } else if (uri.indexOf("/fonts/") > 0) {
                url = "/fonts/" + resource;
            }
//            System.out.println("url:"+url);
            if(url!=null){
                if (url.contains(".jsp")) {
                    url = "/WEB-INF/view/" + url;
                }
                if (!resource.equals("register") && !resource.equals("search") && !resource.equals("createQuestion")
                    && !resource.equals("updateQuestionPage") && !resource.equals("updateQuestion") ) {
                    req.getServletContext().getRequestDispatcher(url).forward(request, response);
                }
            }else{
                if (!resource.equals("register") && !resource.equals("search") && !resource.equals("createQuestion")
                    && !resource.equals("updateQuestionPage") && !resource.equals("updateQuestion") ) {
                    chain.doFilter(request, response);
                }
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
                log("DispatcherFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("DispatcherFilter()");
        }
        StringBuffer sb = new StringBuffer("DispatcherFilter(");
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























































































































































































































































































































