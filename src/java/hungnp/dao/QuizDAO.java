/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.dao;

import hungnp.dto.QuizDTO;
import hungnp.utilities.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Win 10
 */
public class QuizDAO implements Serializable{
    
    public boolean createQuiz(String accountId, String subjectId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "insert into Quiz(QuizID, AccountID, SubjectID, StartTime, EndTime) "
                        + "values(default, ?, ?, default, default) ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, accountId);
                statement.setString(2, subjectId);
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
    
    public int countQuiz() throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select count(QuizID) as amount "
                            + "from Quiz ";
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
    
    public boolean updateTotalScore(String quizId, float totalScore) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "update Quiz "
                        + "set TotalScore = ? "
                        + "where QuizID=? ";
                statement = connection.prepareStatement(sql);
                statement.setFloat(1, totalScore);
                statement.setString(2, quizId);
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
    
    public List<QuizDTO> getAllQuizByAccountId(String accountId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<QuizDTO> listQuiz = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select q.QuizID, s.SubjectName, q.StartTime, q.EndTime, q.TotalScore "
                        + "from Quiz q, Subject s "
                        + "where q.SubjectID=s.SubjectID and q.AccountID=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, accountId);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    if(listQuiz == null){
                        listQuiz = new ArrayList<>();
                    }
                    String quizId = resultSet.getString("QuizID");
                    String subjectName = resultSet.getString("SubjectName");
                    Timestamp startTimeDate = resultSet.getTimestamp("StartTime");
                    Timestamp endTimeDate = resultSet.getTimestamp("EndTime");
                    float totalScore = resultSet.getFloat("TotalScore");
                    QuizDTO quiz = new QuizDTO(quizId, subjectName, startTimeDate, endTimeDate, totalScore);
                    listQuiz.add(quiz);
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return listQuiz;
    }
    
    public Timestamp getEndTime(String quizId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select EndTime "
                        + "from Quiz "
                        + "where QuizID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, quizId);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    Timestamp endTime = resultSet.getTimestamp("EndTime");
                    return endTime;
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return null;
    }
    //not time out
    public boolean updateEndTime(String quizId, Timestamp endTime) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "update Quiz "
                        + "set EndTime = ? "
                        + "where QuizID=? ";
                statement = connection.prepareStatement(sql);
                statement.setTimestamp(1, endTime);
                statement.setString(2, quizId);
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
    
    public List<String> getAllQuizIdBySubjectId(String subjectId, String accountId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<String> listQuiz = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select QuizID "
                        + "from Quiz "
                        + "where SubjectID=? and AccountID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, subjectId);
                statement.setString(2, accountId);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    if(listQuiz == null){
                        listQuiz = new ArrayList<>();
                    }
                    String quizId = resultSet.getString("QuizID");
                    listQuiz.add(quizId);
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return listQuiz;
    }
    
    public QuizDTO getQuizByID(String quizId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select StartTime, EndTime, TotalScore "
                        + "from Quiz "
                        + "where QuizID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, quizId);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    Timestamp startTime = resultSet.getTimestamp("StartTime");
                    Timestamp endTime = resultSet.getTimestamp("EndTime");
                    float totalScore = resultSet.getFloat("TotalScore");
                    QuizDTO quiz = new QuizDTO(quizId, startTime, endTime, totalScore);
                    return quiz;
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

























































































