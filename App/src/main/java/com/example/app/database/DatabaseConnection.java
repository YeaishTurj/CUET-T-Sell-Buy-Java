package com.example.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() {
        String url = "jdbc:mysql://localhost:3306/CUET_T_SELL_DB";
        String user = "root";
        String password = "";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return connection;
    }

}
