package com.example.baitapsession15servlet.mvc_model.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    private static final String DRIVER  = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/libarymanagerment?createDatabaseIfNotExist=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection connection (){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);

        }catch (ClassNotFoundException | java.sql.SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection conn){
        try{
            if(!conn.isClosed()){
                conn.close();
            }
        }catch (java.sql.SQLException e){
            throw new RuntimeException(e);
        }
    }
}
