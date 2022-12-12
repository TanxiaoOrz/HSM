package com.example.hsm.dao;

import com.example.hsm.beans.Book;
import com.example.hsm.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDao {

    public static ArrayList<Book> getBookByRid(Integer Rid){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM hsm.book where Rid = ? order by StartTime");
            statement.setInt(1,Rid);

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

        }catch (SQLException | NullPointerException exception){
            exception.printStackTrace();
            return null;
        }
    }
}
