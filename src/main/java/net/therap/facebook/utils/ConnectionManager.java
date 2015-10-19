package main.java.net.therap.facebook.utils;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class ConnectionManager {
    /*
    * This method should be replaced by connection pool
    * as creation of new connection has overhead.
    *
    * */
    public static Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("db.properties");
            properties.load(fileInputStream);

            String driverClass = properties.getProperty("DB_DRIVER_CLASS");
            String dbUrl = properties.getProperty("DB_URL");
            String dbUser = properties.getProperty("DB_USERNAME");
            String dbPass = properties.getProperty("DB_PASSWORD");

            Class.forName(driverClass);
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement prearedStatement) {
        if (prearedStatement != null) {
            try {
                prearedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}