package pracaDomowaZapisDoBazyDanych.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/samochody?useSSL=false&serverTimezone=UTC",
                "root",
                "root");
    }
}
