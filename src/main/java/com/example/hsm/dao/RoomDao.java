package com.example.hsm.dao;

import com.example.hsm.beans.Room;
import com.example.hsm.beans.RoomStatus;
import com.example.hsm.beans.Type;
import com.example.hsm.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;

public class RoomDao {

    public static RoomStatus getRoomStatus(){
        Connection connection = DbConnection.getInstance().getConnection();
        try {

            Statement statement = connection.createStatement();

            ResultSet set = statement.executeQuery("SELECT count(*) as EmptyCount  FROM hsm.room_empty");
            set.next();
            RoomStatus roomStatus = new RoomStatus();
            int Empty=set.getInt("EmptyCount");
            roomStatus.setEmptyCount(Empty);
            set.close();

            set = statement.executeQuery("SELECT count(*) as Count  FROM hsm.room");
            set.next();
            roomStatus.setOrderedCount(set.getInt("Count")-Empty);
            set.close();

            statement.close();
            connection.close();

            return roomStatus;


        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Room> getRoomAll(){
        Connection connection = DbConnection.getInstance().getConnection();
        try {

            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM hsm.room_type");

            ArrayList<Room> rooms = new ArrayList<>();
            while (set.next()){

                Room room =new Room();
                room.setRid(set.getInt("Rid"));
                room.setFeature(set.getString("Feature"));
                room.setFloor(set.getString("Floor"));
                room.setOrientation(set.getString("Orientation"));

                Type type = new Type();
                type.setTid(set.getInt("Tid"));
                type.setTypePrice(set.getString("TypePrice"));
                type.setTypeName(set.getString("TypeName"));

                room.setType(type);

                rooms.add(room);
            }

            return rooms;


        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Room> getRoomByTid(Integer Tid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hsm.room_type where Tid = ?");
            statement.setInt(1,Tid);

            ResultSet set = statement.executeQuery();

            ArrayList<Room> rooms = new ArrayList<>();
            while (set.next()){

                Room room =new Room();
                room.setFloor(set.getString("Floor"));
                room.setRid(set.getInt("Rid"));
                room.setFeature(set.getString("Feature"));
                room.setOrientation(set.getString("Orientation"));

                Type type = new Type();
                type.setTypeName(set.getString("TypeName"));
                type.setTid(set.getInt("Tid"));
                type.setTypePrice(set.getString("TypePrice"));

                room.setType(type);

                rooms.add(room);
            }

            return rooms;


        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Room> getRoomEmptyByTid(Integer Tid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hsm.room_empty where Tid = ?");
            statement.setInt(1,Tid);

            ResultSet set = statement.executeQuery();

            ArrayList<Room> rooms = new ArrayList<>();
            while (set.next()){

                Room room =new Room();
                room.setOrientation(set.getString("Orientation"));
                room.setFloor(set.getString("Floor"));
                room.setRid(set.getInt("Rid"));
                room.setFeature(set.getString("Feature"));

                Type type = new Type();
                type.setTypeName(set.getString("TypeName"));
                type.setTid(set.getInt("Tid"));
                type.setTypePrice(set.getString("TypePrice"));

                room.setType(type);

                rooms.add(room);
            }

            return rooms;


        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Room> getRoomEmpty(){
        Connection connection = DbConnection.getInstance().getConnection();
        try {

            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM hsm.room_empty");

            ArrayList<Room> rooms = new ArrayList<>();
            while (set.next()){

                Room room =new Room();
                room.setFeature(set.getString("Feature"));
                room.setRid(set.getInt("Rid"));
                room.setFloor(set.getString("Floor"));
                room.setOrientation(set.getString("Orientation"));

                Type type = new Type();
                type.setTypePrice(set.getString("TypePrice"));
                type.setTid(set.getInt("Tid"));
                type.setTypeName(set.getString("TypeName"));

                room.setType(type);

                rooms.add(room);
            }

            return rooms;


        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Room> getRoomEmptyByDate(Date date){
        Connection connection = DbConnection.getInstance().getConnection();
        try {

            PreparedStatement statement = connection.prepareStatement("Select * from hsm.`room_type` WHERE `room_type`.`Rid` IN (SELECT  `book`.`Rid` FROM hsm.`book`  WHERE ( ? BETWEEN `book`.`StartTime` AND `book`.`EndTime`)) IS FALSE");
            statement.setDate(1,date);
            ResultSet set = statement.executeQuery();

            ArrayList<Room> rooms = new ArrayList<>();
            while (set.next()){

                Room room =new Room();
                room.setOrientation(set.getString("Orientation"));
                room.setFeature(set.getString("Feature"));
                room.setRid(set.getInt("Rid"));
                room.setFloor(set.getString("Floor"));

                Type type = new Type();
                type.setTypePrice(set.getString("TypePrice"));
                type.setTid(set.getInt("Tid"));
                type.setTypeName(set.getString("TypeName"));

                room.setType(type);

                rooms.add(room);
            }

            return rooms;


        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean modifyRoom(Room room){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `hsm`.`room` SET `Floor` = ?, `Feature` = ?, `Orientation` = ?, `Tid` = ? WHERE (`Rid` = ?);");
            statement.setString(1, room.getFloor());
            statement.setString(2, room.getFeature());
            statement.setString(3,room.getOrientation());
            statement.setInt(4,room.getType().getTid());
            statement.setInt(5,room.getRid());
            //System.out.println(statement);
            int result = statement.executeUpdate();

            statement.close();
            connection.close();

            return result==1;
        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public static Room getRoom(Integer Rid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hsm.room_type where Rid = ?");
            statement.setInt(1,Rid);
            ResultSet set = statement.executeQuery();

            set.next();

            Room room =new Room();
            room.setRid(set.getInt("Rid"));
            room.setFloor(set.getString("Floor"));
            room.setFeature(set.getString("Feature"));
            room.setOrientation(set.getString("Orientation"));

            Type type = new Type();
            type.setTid(set.getInt("Tid"));
            type.setTypeName(set.getString("TypeName"));
            type.setTypePrice(set.getString("TypePrice"));

            room.setType(type);


            set.close();
            statement.close();
            connection.close();

            room.setBooks(BookDao.getBookByRid(Rid));

            return room;
        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

}
