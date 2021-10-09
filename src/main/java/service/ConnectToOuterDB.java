package service;

import config.ConfigReader;
import model.*;

import java.sql.*;
import java.util.Properties;

public class ConnectToOuterDB {

    /* This method is for the general connection of the sql database.
     */

    public static Connection connectToSQL() {
        String url = "jdbc:mysql://localhost:3306/blogDB" +
                "?useUnicode=yes&characterEncoding=UTF-8";

        Properties properties = new Properties();
        properties.put("user", System.getenv("DB_USER"));
        properties.put("password", System.getenv("DB_PASSWORD"));

        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

