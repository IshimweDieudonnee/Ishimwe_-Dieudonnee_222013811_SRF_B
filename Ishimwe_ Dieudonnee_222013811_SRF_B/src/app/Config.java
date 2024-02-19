package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/eclipse";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

}
