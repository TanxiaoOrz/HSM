package com.example.hsm.dao;

import com.example.hsm.beans.CheckPeople;
import com.example.hsm.utils.DbConnection;

import java.sql.Types;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckPeopleDao {

    public static ArrayList<CheckPeople> getCheckPeopleByUid(Integer Uid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement;
            if (Uid==0){
                statement = connection.prepareStatement("select * from hsm.check_people where Uid is null");
            }
            else {
                statement = connection.prepareStatement("select * from hsm.check_people where Uid = ?");
                statement.setInt(1, Uid);
            }
            ResultSet set = statement.executeQuery();

            ArrayList<CheckPeople> checkPeopleArrayList = new ArrayList<>();
            while (set.next()) {
                CheckPeople checkPeople = new CheckPeople();
                checkPeople.setCid(set.getInt("Cid"));
                checkPeople.setCheckName(set.getString("CheckName"));
                checkPeople.setCheckPhone(set.getString("CheckPhone"));
                checkPeopleArrayList.add(checkPeople);
            }

            set.close();
            statement.close();
            connection.close();

            return checkPeopleArrayList;

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static ArrayList<CheckPeople> getCheckPeopleByBid(Integer Bid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from hsm.bc_relation_check where Bid = ?");
            statement.setInt(1,Bid);
            ResultSet set = statement.executeQuery();

            ArrayList<CheckPeople> checkPeopleArrayList = new ArrayList<>();
            while (set.next()) {
                CheckPeople checkPeople = new CheckPeople();
                checkPeople.setCid(set.getInt("Cid"));
                checkPeople.setCheckPhone(set.getString("CheckPhone"));
                checkPeople.setCheckName(set.getString("CheckName"));
                checkPeopleArrayList.add(checkPeople);
            }

            set.close();
            statement.close();
            connection.close();

            return checkPeopleArrayList;

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean newCheckPeople(CheckPeople checkPeople){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `hsm`.`check_people` ( `CheckName`, `CheckCode`, `CheckPhone`) VALUES (?,?,?);");
            statement.setString(1, checkPeople.getCheckName());
            statement.setString(2, checkPeople.getCheckCode());
            statement.setString(3, checkPeople.getCheckPhone());

            int result =statement.executeUpdate();
            statement.close();
            connection.close();

            return result==1;
        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean newCheckPeopleWithUid(Integer Uid, CheckPeople checkPeople){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `hsm`.`check_people` (`Uid`, `CheckName`, `CheckCode`, `CheckPhone`) VALUES (?,?,?,?);");
            statement.setInt(1,Uid);
            statement.setString(2, checkPeople.getCheckName());
            statement.setString(3, checkPeople.getCheckCode());
            statement.setString(4, checkPeople.getCheckPhone());

            int result =statement.executeUpdate();
            statement.close();
            connection.close();

            return result==1;
        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean addCheckPeopleToBook(Integer Bid, Integer Cid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `hsm`.`bc_relation` (`Bid`,`Cid`) VALUES (?,?);");
            statement.setInt(1,Bid);
            statement.setInt(2, Cid);

            int result =statement.executeUpdate();
            statement.close();
            connection.close();

            return result==1;
        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return false;
        }
    }
}
