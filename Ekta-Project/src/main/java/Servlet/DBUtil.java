package Servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
//    private static final String URL = "jdbc:mysql://localhost:3306/register";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "uday12ka4";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( "jdbc:mysql://localhost:3306/register",  "root", "uday12ka4");
    }
}