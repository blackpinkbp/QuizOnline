/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.dao;

import hungnp.utilities.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Win 10
 */
public class QuizDetailDAO implements Serializable{
    
    public boolean createQuizDetail(String quizId, String questionId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "insert into QuizDetail(QuizID, QuestionID) "
                        + "values(?,?) ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, quizId);
                statement.setString(2, questionId);
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
    
    public boolean updateSelectedAnswer(String questionId, String selectedAnswerId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "update QuizDetail "
                        + "set SelectedAnswerID=? "
                        + "where QuestionID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, selectedAnswerId);
                statement.setString(2, questionId);
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
    
    public List<String> getListQuestionIdInQuiz(String quizId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<String> listQuestionId= null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select QuestionID "
                        + "from QuizDetail "
                        + "where QuizID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, quizId);
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
    
    public List<String> getAllSelectedAnswerID(String quizId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<String> listSelectedAnswerId= null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select SelectedAnswerID "
                        + "from QuizDetail "
                        + "where QuizID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, quizId);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    if(listSelectedAnswerId ==null){
                        listSelectedAnswerId = new ArrayList<>();
                    }
                    String selectedAnswerId = resultSet.getString("SelectedAnswerID");
                    if(selectedAnswerId!=null){
                        listSelectedAnswerId.add(selectedAnswerId);
                    }
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return listSelectedAnswerId;
    }
}






























