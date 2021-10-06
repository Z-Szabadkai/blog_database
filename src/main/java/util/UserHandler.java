package util;

import config.Configreader;
import config.*;
import model.*;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserHandler {

    Connection connect = Configreader.connectToDB();
    List<User> users = new ArrayList<>();

    public void addUserToList(User user) {
        if (!users.contains(user)) {
            users.add(user);
        } else {
            System.out.println("This name is already taken! Use a different one or try typing the password again!");
        }
    }

    public boolean addUsersToDB (User user) {
        String query ="INSERT INTO enduser (name, privilege, email, password, avatar, registration_date) VALUES (?, ?, ? ,?, ?, ?);";

        for (User u : users) {
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

    public List<User> getUsers() {
        return users;
    }
}
