package com.example.hsm.utils;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//使用单例模式完成数据库连接池的创建与链接
public class DbConnection {
    private static DbConnection dbConnection = null;
    private DataSource dataSource;

    //初始化创建方法
    public DbConnection(){
        try{
            Context context =new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/sampleDS");
        }catch (NamingException namingException){
            namingException.printStackTrace();
        }
    }

    //单例获取方法
    public static DbConnection getInstance(){
        if (dbConnection == null){
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    //获取一个连接
    public synchronized final Connection getConnection(){
        try{
            return dataSource.getConnection();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }
}
