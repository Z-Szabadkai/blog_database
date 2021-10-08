package service;

import config.ConfigReader;
import model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
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

    /*
    This method is for the check of an user export.
     */

    public boolean addUsersToDB(User user) {
        Connection connect = connectToSQL();
        String query = ConfigReader.intoSQLDB("user");

        try {
            PreparedStatement ps = connect != null ? connect.prepareStatement(query) : null;
            ps.setString(1, user.getName());
            ps.setInt(2, user.getPrivilege().getDBIndex());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setDate(5, java.sql.Date.valueOf(user.getRegistration_date().toLocalDate()));
            return true;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}

