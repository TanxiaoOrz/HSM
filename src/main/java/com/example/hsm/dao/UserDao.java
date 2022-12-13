package com.example.hsm.dao;

import com.example.hsm.beans.User;
import com.example.hsm.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static User select(String UserCode,String UserPass,String UserType){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * from hsm.user where UserCode = ? and UserPass = ? and UserType= ?");
            statement.setString(1, UserCode);
            statement.setString(2, UserPass);
            statement.setString(3, UserType);
            //System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            User user = new User();
            user.setUid(resultSet.getInt("Uid"));
            user.setUserCode(resultSet.getString("UserCode"));
            user.setUserPass(resultSet.getString("UserPass"));
            user.setUserType(resultSet.getString("UserType"));

            resultSet.close();
            statement.close();
            connection.close();

            return user;

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static User register(String UserCode,String UserPass,String UserType){
        Connection connection = DbConnection.getInstance().getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("insert into hsm.user ( UserPass, UserType, UserCode) values (?,?,?)");

            statement.setString(3, UserCode);
            statement.setString(1, UserPass);
            statement.setString(2, UserType);

            statement.execute();

            statement.close();
            connection.close();

            return select(UserCode,UserPass,UserType);

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean delete(String UserCode,String UserType){
        Connection connection = DbConnection.getInstance().getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("delete from hsm.user where UserCode = ? and UserType = ?");

            statement.setString(1, UserCode);
            statement.setString(2, UserType);

            statement.execute();

            statement.close();
            connection.close();

            return true;

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return false;
        }
    }


}
