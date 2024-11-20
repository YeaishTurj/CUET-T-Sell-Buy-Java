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
            }else{
                System.out.println("Database connection failed!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return connection;
    }

}


// database name: CUET_T_SELL_DB

// table buyer (name, email(primary_key), password)

// table seller (name, email(primary_key), password, contact, w_app, facebook_link)

// table product (product_id(primary_key, auto increment), seller_email (foreign key), product_title, quantity, price, description)
//      CREATE TABLE product (
//          product_id INT AUTO_INCREMENT PRIMARY KEY,
//          seller_email VARCHAR(255),
//          product_title VARCHAR(255) NOT NULL,
//          quantity INT NOT NULL,
//          price DECIMAL(10, 2) NOT NULL,
//          description TEXT,
//          FOREIGN KEY (seller_email) REFERENCES seller(email) ON DELETE CASCADE
//      );
