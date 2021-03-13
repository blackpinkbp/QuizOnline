/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.filter;

import hungnp.dto.AnswerDTO;
import hungnp.dto.QuestionDTO;
import hungnp.error.QuestionError;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
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
@WebFilter(filterName = "AdminUpdateQuestionFilter", urlPatterns = {"/updateQuestion"})
public class AdminUpdateQuestionFilter implements Filter {
    
    private static final boolean debug = true;
    private static final String FILE_NOT_FOUND_PAGE="/WEB-INF/view/fileNotFound.jsp";
    private static final String UPDATE_QUESTION_PAGE="/WEB-INF/view/updateQuestion.jsp";
    private static final String ADMIN_UPDATE_QUESTION_CONTROLLER="/AdminUpdateQuestionServlet";

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AdminUpdateQuestionFilter() {
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
        HttpServletRequest req = (HttpServletRequest) request;
        String url = FILE_NOT_FOUND_PAGE;
        try{
            HttpSession session = req.getSession();
            session.removeAttribute("updateQuestionError");
            
            QuestionDTO tempQuestion = (QuestionDTO) session.getAttribute("questionUpdate");
            String questionContent = request.getParameter("questionContent");
            String[] answerIds = request.getParameterValues("answerDTO");
            String answerId1 = answerIds[0];
            String answerId2 = answerIds[1];
            String answerId3 = answerIds[2];
            String answerId4 = answerIds[3];
            String answerContent1 = request.getParameter(answerId1);
            String answerContent2 = request.getParameter(answerId2);
            String answerContent3 = request.getParameter(answerId3);
            String answerContent4 = request.getParameter(answerId4);
            String[][] arrayAnswer = {{answerId1, answerContent1},
                                      {answerId2, answerContent2},
                                      {answerId3, answerContent3},
                                      {answerId4, answerContent4}};
            
            
            tempQuestion.setQuestionContent(questionContent);
            List<AnswerDTO> tempListAnswer = tempQuestion.getListAnswer();
            String answerIdCorrect = request.getParameter("correcting");
            for (int i = 0; i < tempListAnswer.size(); i++) {
                AnswerDTO answer= tempListAnswer.get(i);
                if(answerIdCorrect.equals(answer.getAnswerId())){
                    tempListAnswer.set(i, new AnswerDTO(arrayAnswer[i][0], arrayAnswer[i][1], true));
                }else{
                    tempListAnswer.set(i, new AnswerDTO(arrayAnswer[i][0], arrayAnswer[i][1], false));
                }
            }
            tempQuestion.setListAnswer(tempListAnswer);
            
            if(questionContent!=null  && answerContent1!=null && answerContent2 !=null && answerContent3 !=null && answerContent4!=null){
                QuestionError error = new QuestionError();
                boolean checking = false;
                if(questionContent.trim().isEmpty()){
                    error.setQuestionContentEmptyError("question content is not empty");
                    checking = true;
                }
                if(answerContent1.trim().isEmpty()){
                    error.setAnswerFirstContentEmptyError("answer is not empty");
                    checking = true;
                }
                if(answerContent2.trim().isEmpty()){
                    error.setAnswerSecondContentEmptyError("answer is not empty");
                    checking = true;
                }else{
                    if (answerContent2.trim().equals(answerContent1)) {
                        error.setAnswerSecondDuplicationError("this answer is duplicated");
                        checking = true;
                    }
                }
                if(answerContent3.trim().isEmpty()){
                    error.setAnswerThirdContentEmptyError("answer is not empty");
                    checking = true;
                }else{
                    if (answerContent3.trim().equals(answerContent1) || answerContent3.trim().equals(answerContent2)) {
                        error.setAnswerThirdContentEmptyError("this answer is duplicated");
                        checking = true;
                    }
                }
                if(answerContent4.trim().isEmpty()){
                    error.setAnswerFourthContentEmptyError("answer is not empty");
                    checking = true;
                }else{
                    if (answerContent4.trim().equals(answerContent1) || answerContent4.trim().equals(answerContent2) || 
                            answerContent4.trim().equals(answerContent3)) {
                        error.setAnswerFourthDuplicationError("this answer is duplicated");
                        checking = true;
                    }
                }
                if(checking){
                    session.setAttribute("updateQuestionError", error);
                    session.setAttribute("questionUpdate", tempQuestion);
                    url = UPDATE_QUESTION_PAGE;
                }else{
                    url = ADMIN_UPDATE_QUESTION_CONTROLLER;
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
                log("UpdateQuestionFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("UpdateQuestionFilter()");
        }
        StringBuffer sb = new StringBuffer("UpdateQuestionFilter(");
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





























































































