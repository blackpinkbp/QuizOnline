/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.dao;

import hungnp.dto.AnswerDTO;
import hungnp.dto.QuestionDTO;
import hungnp.utilities.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author Win 10
 */
public class QuestionDAO implements Serializable{
    
    public QuestionDTO getQuestionFromID(String questionId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        QuestionDTO question = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select q.QuestionContent, qs.QuestionStatusName, q.CreatedDate, a.AnswerContent, a.CorrectAnswer "
                        + "from Question q, QuestionStatus qs, Answer a "
                        + "where q.QuestionStatusID=qs.QuestionStatusID and q.QuestionID=a.QuestionID and q.QuestionID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, questionId);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    String answerContent = resultSet.getNString("AnswerContent");
                    boolean correcting = resultSet.getBoolean("CorrectAnswer");
                    AnswerDTO answer = new AnswerDTO(answerContent, correcting);
                    if(question ==null){
                        String questionContent = resultSet.getString("QuestionContent");
                        String questionStatus = resultSet.getString("QuestionStatusName");
                        Date createdDate = resultSet.getTimestamp("CreatedDate");
                        List<AnswerDTO> listAnswer = new ArrayList<>();
                        listAnswer.add(answer);
                        question = new QuestionDTO(questionId, questionContent, questionStatus, createdDate, listAnswer);
                    }else{
                        List<AnswerDTO> listAnwser = question.getListAnswer();
                        listAnwser.add(answer);
                        question.setListAnswer(listAnwser);
                    }
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return question;
    }
    //have AnswerID
    public QuestionDTO getFullQuestionFromID(String questionId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        QuestionDTO question = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select q.QuestionContent, qs.QuestionStatusName, q.CreatedDate, a.AnswerID, a.AnswerContent, a.CorrectAnswer "
                        + "from Question q, QuestionStatus qs, Answer a "
                        + "where q.QuestionStatusID=qs.QuestionStatusID and q.QuestionID=a.QuestionID and q.QuestionID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, questionId);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    String answerId= resultSet.getString("AnswerID");
                    String answerContent = resultSet.getNString("AnswerContent");
                    boolean correcting = resultSet.getBoolean("CorrectAnswer");
                    AnswerDTO answer = new AnswerDTO(answerId, answerContent, correcting);
                    if(question ==null){
                        String questionContent = resultSet.getString("QuestionContent");
                        String questionStatus = resultSet.getString("QuestionStatusName");
                        Date createdDate = resultSet.getTimestamp("CreatedDate");
                        List<AnswerDTO> listAnswer = new ArrayList<>();
                        listAnswer.add(answer);
                        question = new QuestionDTO(questionId, questionContent, questionStatus, createdDate, listAnswer);
                    }else{
                        List<AnswerDTO> listAnwser = question.getListAnswer();
                        listAnwser.add(answer);
                        question.setListAnswer(listAnwser);
                    }
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return question;
    }
    // ko co createdDate and status
    public QuestionDTO getInfoQuestionFromID(String questionId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        QuestionDTO question = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select q.QuestionContent, a.AnswerID, a.AnswerContent, a.CorrectAnswer "
                        + "from Question q, Answer a "
                        + "where q.QuestionID=a.QuestionID and q.QuestionID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, questionId);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    String answerId = resultSet.getString("AnswerID");
                    String answerContent = resultSet.getNString("AnswerContent");
                    boolean correcting = resultSet.getBoolean("CorrectAnswer");
                    AnswerDTO answer = new AnswerDTO(answerId, answerContent, correcting);
                    if(question ==null){
                        String questionContent = resultSet.getString("QuestionContent");
                        List<AnswerDTO> listAnswer = new ArrayList<>();
                        listAnswer.add(answer);
                        question = new QuestionDTO(questionContent, listAnswer);
                    }else{
                        List<AnswerDTO> listAnwser = question.getListAnswer();
                        listAnwser.add(answer);
                        question.setListAnswer(listAnwser);
                    }
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return question;
    }
    
    
    //key subjectName; value list questionId
    public Map<String, List<String>> searchMapQuestionIdByContent(String questionContent) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        Map<String, List<String>> mapQuestionId = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select s.SubjectName, q.QuestionID "
                        + "from Question q, Subject s "
                        + "where q.SubjectID = s.SubjectID and q.QuestionContent like ? "
                        + "order by q.QuestionContent asc ";
                statement = connection.prepareStatement(sql);
                statement.setNString(1, "%"+questionContent+"%");
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    if(mapQuestionId ==null){
                        mapQuestionId = new HashMap<>();
                    }
                    String subjectName = resultSet.getString("SubjectName");
                    String questionId = resultSet.getString("QuestionID");
                    if(mapQuestionId.containsKey(subjectName)){
                        List<String> listQuestionId = mapQuestionId.get(subjectName);
                        listQuestionId.add(questionId);
                    }else{
                        List<String> listQuestionId = new ArrayList<>();
                        listQuestionId.add(questionId);
                        mapQuestionId.put(subjectName, listQuestionId);
                    }
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return mapQuestionId;
    }
    
    public Map<String, List<String>> searchMapQuestionIdByStatus(String questionStatusId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        Map<String, List<String>> mapQuestionId = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select s.SubjectName, q.QuestionID "
                        + "from Question q, Subject s, QuestionStatus qs "
                        + "where q.SubjectID = s.SubjectID and q.QuestionStatusID=qs.QuestionStatusID and qs.questionStatusId=? "
                        + "order by q.QuestionContent asc ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, questionStatusId);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    if(mapQuestionId ==null){
                        mapQuestionId = new HashMap<>();
                    }
                    String subjectName = resultSet.getString("SubjectName");
                    String questionId = resultSet.getString("QuestionID");
                    if(mapQuestionId.containsKey(subjectName)){
                        List<String> listQuestionId = mapQuestionId.get(subjectName);
                        listQuestionId.add(questionId);
                    }else{
                        List<String> listQuestionId = new ArrayList<>();
                        listQuestionId.add(questionId);
                        mapQuestionId.put(subjectName, listQuestionId);
                    }
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return mapQuestionId;
    }
    
    public int countQuestionIdBySubject(String subjectId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select count(q.QuestionID) as amount "
                        + "from Subject s, Question q "
                        + "where s.SubjectID = q.SubjectID and s.SubjectID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, subjectId);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    return resultSet.getInt("amount");
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return -1;
    }
    
    public List<String> searchQuestionIDbySubject(String subjectId, int skippedQuestion, int nextQuestion) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<String> listQuestionId = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select q.QuestionID "
                        + "from Subject s, Question q "
                        + "where s.SubjectID = q.SubjectID and s.SubjectID=? "
                        + "order by q.QuestionContent asc "
                        + "offset ? rows "
                        + "fetch first ? rows only ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, subjectId);
                statement.setInt(2, skippedQuestion);
                statement.setInt(3, nextQuestion);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    if(listQuestionId ==null){
                        listQuestionId = new ArrayList<>();
                    }
                    String questionId = resultSet.getString("QuestionID");
                    listQuestionId.add(questionId);
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return listQuestionId;
    }
    
    //---------------------------------------------------------------------------------------------------
    public boolean createQuestion(String questionContent, String questionStatusId, String subjectId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "insert into Question(QuestionID, QuestionContent, QuestionStatusID, SubjectID, CreatedDate) "
                            + "values(default, ?, ?, ?, default) ";
                statement = connection.prepareStatement(sql);
                statement.setNString(1, questionContent);
                statement.setString(2, questionStatusId);
                statement.setString(3, subjectId);
                int row = statement.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return false;
    }
    
    public int countQuestion() throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select count(QuestionID) as amount "
                            + "from Question ";
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    return resultSet.getInt("amount");
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return -1;
    }
    
    //----------------------------------------------------------------------------------------------------
    public boolean checkQuestionIdExist(String questionId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select QuestionID "
                        + "from Question "
                        + "where QuestionID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, questionId);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    return true;
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return false;
    }
    //key: subjectId, value: QuestionDTO
    public Map<String, QuestionDTO> getMapQuestionForUpdatePage(String questionId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        Map<String, QuestionDTO> mapQuestion = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select q.QuestionContent, qs.QuestionStatusName, q.SubjectID, a.AnswerID, a.AnswerContent, a.CorrectAnswer "
                        + "from Question q, QuestionStatus qs, Answer a "
                        + "where q.QuestionID = a.QuestionID and q.QuestionStatusID = qs.QuestionStatusID and q.QuestionID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, questionId);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    if(mapQuestion == null){
                        mapQuestion  = new HashMap<>();
                    }
                    String subjectId = resultSet.getString("SubjectID");
                    if(mapQuestion.containsKey(subjectId)){
                        String answerId = resultSet.getString("AnswerID");
                        String answerContent = resultSet.getString("AnswerContent");
                        boolean correcting = resultSet.getBoolean("CorrectAnswer");
                        AnswerDTO answer = new AnswerDTO(answerId, answerContent, correcting);
                        
                        QuestionDTO question = mapQuestion.get(subjectId);
                        List<AnswerDTO> listAnswer = question.getListAnswer();
                        listAnswer.add(answer);
                        question.setListAnswer(listAnswer);
                    }else{
                        String answerId = resultSet.getString("AnswerID");
                        String answerContent = resultSet.getString("AnswerContent");
                        boolean correcting = resultSet.getBoolean("CorrectAnswer");
                        AnswerDTO answer = new AnswerDTO(answerId, answerContent, correcting);
                        List<AnswerDTO> listAnswer = new ArrayList<>();
                        listAnswer.add(answer);
                        
                        String questionContent = resultSet.getString("QuestionContent");
                        String questionStatus = resultSet.getString("QuestionStatusName");
                        QuestionDTO question = new QuestionDTO(questionId, questionContent, questionStatus, listAnswer);
                        mapQuestion.put(subjectId, question);
                    }
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return mapQuestion;
    }
    
    public boolean updateQuestion(String questionId, String questionContent, String questionStatusId, String subjectId)
            throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "update Question "
                        + "set QuestionContent=?, QuestionStatusID=?, SubjectID=? "
                        + "where QuestionID=? ";
                statement = connection.prepareStatement(sql);
                statement.setNString(1, questionContent);
                statement.setString(2, questionStatusId);
                statement.setString(3, subjectId);
                statement.setString(4, questionId);
                int row = statement.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return false;
    }
    
    public boolean deleteQuestion(String questionId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "update Question "
                        + "set QuestionStatusID='QS1' "
                        + "where QuestionID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, questionId);
                int row = statement.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return false;
    }
    
    //-------------------------------------------------------------------------------------
    public List<String> getRandomQuestionIdForQuiz(String subjectId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<String> listRandomQuestionId = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select top 50 q.QuestionID "
                        + "from Question q, Subject s "
                        + "where q.SubjectID= s.SubjectID and q.QuestionStatusID='QS0' and s.SubjectID=? "
                        + "order by newid() ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, subjectId);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    if(listRandomQuestionId == null){
                        listRandomQuestionId  = new ArrayList<>();
                    }
                    String questionId = resultSet.getString("QuestionID");
                    listRandomQuestionId.add(questionId);
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return listRandomQuestionId;
    }
    
    //------------------------------------------------------------------------------
    public String getQuestionIdBySelectedAnswerId(String selectedAnswerId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select q.QuestionID "
                        + "from Question q, QuizDetail qd "
                        + "where q.QuestionID=qd.QuestionID and qd.SelectedAnswerID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, selectedAnswerId);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String selectedQuestionId = resultSet.getString("QuestionID");
                    return selectedQuestionId;
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return null;
    }
}








































































































































































































































































































































































































