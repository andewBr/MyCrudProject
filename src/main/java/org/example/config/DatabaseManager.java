package org.example.config;

import lombok.SneakyThrows;

import java.sql.*;

import static org.example.config.ValueForConfig.*;

public class DatabaseManager {

    private static Connection connection;

    private static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL.getValue(), USERNAME.getValue(), PASSWORD.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @SneakyThrows
    public static PreparedStatement prepareStatement(String sqlRequest) {
        return getConnection().prepareStatement(sqlRequest, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @SneakyThrows
    public static Statement statement() {
         return getConnection().createStatement();

    }
}
