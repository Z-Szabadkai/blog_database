package config;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Configreader {

    String xlsPath = "src/main/resources/UserDatabase.xls";
    String userPath = "user, u, enduser";
    String blogPath = "blog";

    private Connection connectToDB() {
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

    private void exportToXLS () throws IOException {
        Connection connect = connectToDB();
        Workbook workbook = WorkbookFactory.create(new FileInputStream(xlsPath));
        Workbook writeWorkbook = new HSSFWorkbook();
        Sheet sheet = writeWorkbook.createSheet("Blog User");

        try{
            String query = "SELECT * FROM blog";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            Row row = sheet.createRow(0);

            for(int col=0 ;col < columnsNumber;col++) {
                Cell newpath = row.createCell(col);
                newpath.setCellValue(rsmd.getColumnLabel(col+1));
            }
            while(resultSet.next()) {
                Row desRow = sheet.createRow(resultSet.getRow());
                for(int col=0 ;col < columnsNumber;col++) {
                    Cell newpath = desRow.createCell(col);
                    newpath.setCellValue(resultSet.getString(col+1));
                }
                FileOutputStream fileOut = new FileOutputStream("C:\\Users\\CEPL\\Desktop\\test.xls");
                writeWorkbook.write(fileOut);
                fileOut.close();
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to get data from database");
        }
    }
}

