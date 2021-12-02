package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String URL = "jdbc:mysql://localhost/gorshkov_db";
    private static String USERNAME = "root";
    private static String PASSWORD = "toortoor";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          //  System.out.println("connection ok");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
          //  System.out.println("connection error");
        }
        return connection;
    }
}
