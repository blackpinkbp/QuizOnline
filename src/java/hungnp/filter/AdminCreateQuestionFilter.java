/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.filter;

import hungnp.error.QuestionError;
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
@WebFilter(filterName = "AdminCreateQuestionFilter", urlPatterns = {"/createQuestion"})
public class AdminCreateQuestionFilter implements Filter {
    
    private static final boolean debug = true;
    private static final String CREATE_QUESTION_PAGE="/WEB-INF/view/createQuestion.jsp";
    private static final String ADMIN_CREATE_QUESTION_CONTROLLER= "/AdminCreateQuestionServlet";
    private static final String FILE_NOT_FOUND_PAGE="/WEB-INF/view/fileNotFound.jsp";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AdminCreateQuestionFilter() {
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
        HttpServletRequest req= (HttpServletRequest) request;
        String url = FILE_NOT_FOUND_PAGE;
        try {
            HttpSession session = req.getSession();
            session.removeAttribute("createQuestionError");
                    
            String questionContent = request.getParameter("questionContent");
            String answer1 = request.getParameter("answer1");
            String answer2 = request.getParameter("answer2");
            String answer3 = request.getParameter("answer3");
            String answer4 = request.getParameter("answer4");
            
            if(questionContent!=null  && answer1!=null && answer2 !=null && answer3 !=null && answer4!=null){
                QuestionError error = new QuestionError();
                boolean checking = false;
                if(questionContent.trim().isEmpty()){
                    error.setQuestionContentEmptyError("question content is not empty");
                    checking = true;
                }
                if(answer1.trim().isEmpty()){
                    error.setAnswerFirstContentEmptyError("answer is not empty");
                    checking = true;
                }
                if(answer2.trim().isEmpty()){
                    error.setAnswerSecondContentEmptyError("answer is not empty");
                    checking = true;
                }else{
                    if (answer2.trim().equals(answer1)) {
                        error.setAnswerSecondDuplicationError("this answer is duplicated");
                        checking = true;
                    }
                }
                if(answer3.trim().isEmpty()){
                    error.setAnswerThirdContentEmptyError("answer is not empty");
                    checking = true;
                }else{
                    if (answer3.trim().equals(answer1) || answer3.trim().equals(answer2)) {
                        error.setAnswerThirdContentEmptyError("this answer is duplicated");
                        checking = true;
                    }
                }
                if(answer4.trim().isEmpty()){
                    error.setAnswerFourthContentEmptyError("answer is not empty");
                    checking = true;
                }else{
                    if (answer4.trim().equals(answer1) || answer4.trim().equals(answer2) || answer4.trim().equals(answer3)) {
                        error.setAnswerFourthDuplicationError("this answer is duplicated");
                        checking = true;
                    }
                }
                if(checking){
                    
                    session.setAttribute("createQuestionError", error);
                    session.setAttribute("questionContent", questionContent);
                    session.setAttribute("answer1", answer1);
                    session.setAttribute("answer2", answer2);
                    session.setAttribute("answer3", answer3);
                    session.setAttribute("answer4", answer4);
                    url = CREATE_QUESTION_PAGE;
                }else{
                    url = ADMIN_CREATE_QUESTION_CONTROLLER;
                }
            }
            
            if (url != null) {
                request.getServletContext().getRequestDispatcher(url).forward(request, response);
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
                log("CreateQuestionFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("CreateQuestionFilter()");
        }
        StringBuffer sb = new StringBuffer("CreateQuestionFilter(");
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











































