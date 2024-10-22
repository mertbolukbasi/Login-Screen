package com.example.timeistime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {


    public static final String DATABASE_NAME = "sys";
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PASSWORD = "zeref";
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
    private Connection connection;

    public boolean openDataBase () {

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("BAGLANDIK LAAAN");
            return true;
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e.getMessage());
            return false;
        }
    }

    public void closeDataBase () {

        if(connection != null) {
            try {
                connection.close();
            }

            catch (SQLException e) {
                System.out.println("Connection Error: " + e.getMessage());
            }
        }
    }


}
