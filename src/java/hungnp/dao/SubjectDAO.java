/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.dao;

import hungnp.dto.SubjectDTO;
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
public class SubjectDAO implements Serializable{
    
    public List<SubjectDTO> getAllSubject() throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SubjectDTO> listSubject = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql ="select SubjectID, SubjectName "
                        + "from Subject ";
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    if(listSubject == null){
                        listSubject = new ArrayList<>();
                    }
                    String subjectId = resultSet.getString("SubjectID");
                    String subjectName = resultSet.getString("SubjectName");
                    listSubject.add(new SubjectDTO(subjectId, subjectName));
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return listSubject;
    }
    
//    public String getSubjectNameFromID(String subjectId) throws SQLException, NamingException{
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try{
//            connection = MyConnection.makeConnection();
//            if(connection!=null){
//                String sql ="select SubjectName "
//                        + "from Subject "
//                        + "where SubjectID=? ";
//                statement = connection.prepareStatement(sql);
//                statement.setString(1, subjectId);
//                resultSet = statement.executeQuery();
//                if(resultSet.next()){
//                    return resultSet.getString("SubjectName");
//                }
//            }
//        }finally{
//            if(resultSet!=null) resultSet.close();
//            if(statement!=null) statement.close();
//            if(connection!=null) connection.close();
//        }
//        return null;
//    }
    
}

























































