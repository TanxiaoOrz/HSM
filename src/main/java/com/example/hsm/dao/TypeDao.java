package com.example.hsm.dao;

import com.example.hsm.beans.Type;
import com.example.hsm.utils.DbConnection;

import java.sql.*;

import java.util.ArrayList;

public class TypeDao {

    public static ArrayList<Type> getType(){
        Connection connection = DbConnection.getInstance().getConnection();
        try{
            Statement statement = connection.createStatement();

            ResultSet set = statement.executeQuery("SELECT * FROM hsm.type_count;");

            ArrayList<Type> types = new ArrayList<>();

            while (set.next()){
                Type type = new Type();
                type.setTid(set.getInt("Tid"));
                type.setTypeName(set.getString("TypeName"));
                type.setTypePrice(set.getString("TypePrice"));
                type.setEmptyRoom(set.getInt("EmptyCount"));
                types.add(type);
            }

            statement.close();
            connection.close();

            return types;

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static Type getTypeByTid(Integer Tid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hsm.type_count where Tid = ?;");
            statement.setInt(1,Tid);

            ResultSet set = statement.executeQuery();
            set.next();
            Type type = new Type();
            type.setTid(set.getInt("Tid"));
            type.setTypeName(set.getString("TypeName"));
            type.setTypePrice(set.getString("TypePrice"));
            type.setEmptyRoom(set.getInt("EmptyCount"));

            set.close();
            statement.close();
            connection.close();

            return type;

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean modifyType(Type type){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `hsm`.`type` SET `TypeName` = ?, `TypePrice` = ? WHERE (`Tid` = ?);");
            statement.setString(1, type.getTypeName());
            statement.setString(2,type.getTypePrice());
            statement.setInt(3,type.getTid());

            int result = statement.executeUpdate();

            statement.close();
            connection.close();

            return result==1;
        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return false;
        }
    }

}
