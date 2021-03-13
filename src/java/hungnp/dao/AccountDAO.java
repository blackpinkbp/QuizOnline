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
public class AccountDAO implements Serializable{
    
    public boolean checkLogin(String email, String password) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select Email "
                        + "from Account "
                        + "where Email=? and Password=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, email);
                statement.setString(2, password);
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
    
    public String getStatusFromEmail(String email) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select s.AccountStatusName "
                            + "from Account a, AccountStatus s "
                            + "where a.AccountStatusID=s.AccountStatusID and a.Email=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, email);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    return resultSet.getString("AccountStatusName");
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return null;
    }
    
    public String getRoleFromEmail(String email) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select r.AccountRoleName "
                            + "from Account a, AccountRole r "
                            + "where a.AccountRoleID=r.AccountRoleID and a.Email=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, email);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    return resultSet.getString("AccountRoleName");
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return null;
    }
    
    public String getAccountNameFromEmail(String email) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select AccountName "
                            + "from Account "
                            + "where Email=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, email);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    return resultSet.getNString("AccountName");
                }
            }
        }finally{
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return null;
    }
    
    public boolean checkExistEmail(String email) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "select Email "
                            + "from Account "
                            + "where Email=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, email);
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
    
    public boolean createAccount(String email, String name, String password) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = MyConnection.makeConnection();
            if(connection!=null){
                String sql = "insert into Account(Email, AccountName, Password, AccountRoleID, AccountStatusID) "
                        + "values(?, ?, ?, 'AR1', 'AS0')";
                statement = connection.prepareStatement(sql);
                statement.setString(1, email);
                statement.setNString(2, name);
                statement.setString(3, password);
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
    
}



































































































