package module;

import config.SQLConnection;
import model.User;
import util.UserHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBEngine {

    Connection connect = SQLConnection.connectToDB();

    public boolean addUsersToDB (User user) {

        String query ="INSERT INTO enduser (name, privilege, email, password, avatar, registration_date) VALUES (?, ?, ? ,?, ?, ?);";

        for (User u : UserHandler.getUsers()) {
            try {
                PreparedStatement ps = connect != null ? connect.prepareStatement(query) : null;
                ps.setString(1, user.getUserName());
                ps.setInt(2, user.getPermission().getDBIndex());
                ps.setString(3, user.getE_mail());
                ps.setString(4, user.getPassword());
                ps.setDate(5, java.sql.Date.valueOf(user.getRegistration_date().toLocalDate()));
                return true;
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
