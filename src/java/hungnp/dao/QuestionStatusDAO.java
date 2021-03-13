/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.dao;

import hungnp.dto.QuestionStatusDTO;
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
public class QuestionStatusDAO implements Serializable{
    
    public List<QuestionStatusDTO> getAllQuestionStatus() throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<QuestionStatusDTO> listQuestionStatus = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql ="select QuestionStatusID, QuestionStatusName "
                        + "from QuestionStatus ";
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    if(listQuestionStatus == null){
                        listQuestionStatus = new ArrayList<>();
                    }
                    String questionStatusId = resultSet.getString("QuestionStatusID");
                    String questionStatusName = resultSet.getString("QuestionStatusName");
                    listQuestionStatus.add(new QuestionStatusDTO(questionStatusId, questionStatusName));
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return listQuestionStatus;
    }
}









