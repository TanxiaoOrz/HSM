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
                room.setFloor(set.getString("Floor"));
                room.setFeature(set.getString("Feature"));
                room.setOrientation(set.getString("Orientation"));

                Type type = new Type();
                type.setTid(set.getInt("Tid"));
                type.setTypeName(set.getString("TypeName"));
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

}
