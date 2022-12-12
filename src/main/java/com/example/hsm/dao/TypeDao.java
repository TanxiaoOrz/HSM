package com.example.hsm.dao;

import com.example.hsm.beans.Type;
import com.example.hsm.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeDao {

    public static ArrayList<Type> getType(){
        Connection connection = DbConnection.getInstance().getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hsm.type_count;");

            ResultSet set = statement.executeQuery();

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

}
