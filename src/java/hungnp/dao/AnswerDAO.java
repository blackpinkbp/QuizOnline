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
import javax.naming.NamingException;

/**
 *
 * @author Win 10
 */
public class AnswerDAO implements Serializable{
    
    public boolean createAnswer(String answerContent, String questionId, boolean correcting) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "insert into Answer(AnswerID, AnswerContent, QuestionID, CorrectAnswer) "
                            + "values(default, ?, ?, ?) ";
                statement = connection.prepareStatement(sql);
                statement.setNString(1, answerContent);
                statement.setString(2, questionId);
                statement.setBoolean(3, correcting);
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
    
    public boolean updateAnswer(String answerId, String answerContent, boolean correcting) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "update Answer "
                        + "set AnswerContent=?, CorrectAnswer=? "
                        + "where AnswerID=? ";
                statement = connection.prepareStatement(sql);
                statement.setNString(1, answerContent);
                statement.setBoolean(2, correcting);
                statement.setString(3, answerId);
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
    
    public boolean checkCorrectAnswerID(String selectedAnswerId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        boolean correcting = false;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select CorrectAnswer "
                        + "from Answer "
                        + "where AnswerID = ? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, selectedAnswerId);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    correcting = resultSet.getBoolean("CorrectAnswer");
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return correcting;
    }
    
}





























