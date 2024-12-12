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
//          mainImg LONGBLOB NOT NULL,
//          addImg1 LONGBLOB,
//          addImg2 LONGBLOB,
//          addImg3 LONGBLOB,
//          addImg4 LONGBLOB,
//          FOREIGN KEY (seller_email) REFERENCES seller(email) ON DELETE CASCADE
//      );

//If you are using XAMPP, you need to increase the `max_allowed_packet` value in the MySQL configuration file bundled with XAMPP. Here are the steps:
//
//        ### **1. Locate the MySQL Configuration File**
//The MySQL configuration file is typically named `my.ini` or `my.cnf`, and it is located in the XAMPP installation directory:
//        - **Linux/Mac**: Look under the XAMPP directory, e.g., `/opt/lampp/etc/my.cnf`.
//
//        ### **2. Edit the `my.ini` or `my.cnf` File**
//        1. Open the file in a text editor.
//        2. Locate the `[mysqld]` section (if it doesn't exist, add it).
//        3. Add or update the `max_allowed_packet` directive. For example:

//        [mysqld]
//        max_allowed_packet = 16M
//
//        ### **3. Restart the MySQL Server**
//        1. Open the XAMPP Control Panel.
//        2. Stop the MySQL service by clicking **Stop**.
//        3. Start the MySQL service again by clicking **Start**.
//
//        ### **4. Verify the Change**
//        1. Open the MySQL command-line tool or any database management tool (e.g., phpMyAdmin).
//        2. Run the following SQL command:
//              SHOW VARIABLES LIKE 'max_allowed_packet';
//        3. Ensure the value has been updated (e.g., `16777216` for 16 MB).
