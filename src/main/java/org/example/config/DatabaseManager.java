package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.config.ValueForConfig.*;

public class DatabaseManager {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL.getValue(), USERNAME.getValue(), PASSWORD.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
