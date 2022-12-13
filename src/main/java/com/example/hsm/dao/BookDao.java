package com.example.hsm.dao;

import com.example.hsm.beans.Book;
import com.example.hsm.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class BookDao {

    public static ArrayList<Book> getBookByRid(Integer Rid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hsm.book where Rid = ? order by StartTime");
            statement.setInt(1,Rid);

            return getBooks(connection, statement);

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean newBook(Book book){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("Select Bid from hsm.book where ? > StartTime and Rid = ?;");
            statement.setDate(1,book.getEndTime());
            statement.setInt(2,book.getRid());
            ResultSet set = statement.executeQuery();
            if (set.next()){
                return false;
            }
            statement.close();
            set.close();

            statement = connection.prepareStatement("SELECT * from hsm.book where ? < book.EndTime and Rid = ?");
            statement.setDate(1,book.getEndTime());
            statement.setInt(2,book.getRid());
            set = statement.executeQuery();
            if (set.next()){
                return false;
            }
            statement.close();
            set.close();

            statement = connection.prepareStatement("INSERT INTO hsm.book (`Rid`, `UserCode`, `StartTime`, `EndTime`) VALUES (?, ?, ?, ?);");
            statement.setInt(1,book.getRid());
            statement.setString(2, book.getUserCode());
            statement.setDate(3,book.getStartTime());
            statement.setDate(4,book.getEndTime());
            //System.out.println(statement);

            statement.execute();
            statement.close();
            connection.close();

            return true;
        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Book> getBookByUserCode(String UserCode){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hsm.book where UserCode = ? order by StartTime desc ");
            statement.setString(1,UserCode);

            return getBooks(connection, statement);

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }

    private static ArrayList<Book> getBooks(Connection connection, PreparedStatement statement) throws SQLException {
        ResultSet set = statement.executeQuery();
        ArrayList<Book> books =new  ArrayList<>();

        while (set.next()){
            Book book = new Book();
            book.setBid(set.getInt("Bid"));
            book.setRid(set.getInt("Rid"));
            book.setUserCode(set.getString("UserCode"));
            book.setStartTime(set.getDate("StartTime"));
            book.setEndTime(set.getDate("EndTime"));
            book.setPrice(set.getFloat("Price"));
            book.setChecked(set.getString("Checked"));
            book.setPaid(set.getString("Paid"));

            books.add(book);
        }

        set.close();
        statement.close();
        connection.close();

        return books;
    }

    public static boolean checkBook(Integer Bid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE hsm.book set Checked = 'yes' where Bid = ?");
            statement.setInt(1,Bid);
            System.out.println(statement);

            return true;

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean delayBook(Integer Bid, Date EndTime){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE hsm.book set EndTime = ? where Bid = ?");
            statement.setDate(1,EndTime);
            statement.setInt(2,Bid);
            statement.execute();
            return true;

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean payBook(Integer Bid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE hsm.book set Paid = 'yes' where Bid = ?");
            statement.setInt(1,Bid);
            statement.execute();
            return true;

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return false;
        }
    }


}
