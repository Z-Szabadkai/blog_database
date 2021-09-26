package util;

import org.apache.poi.ss.usermodel.Cell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBEngine {

    private Connection connecToDB() {
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

    private Object getCellValue(Cell cell) {
        return cell;
    }
}

