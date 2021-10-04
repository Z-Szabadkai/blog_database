package util;

import config.Configreader;
import config.GetScanner;
import model.Privilege;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserHandler {

    Connection connect = Configreader.connectToDB();
    List<User> users = new ArrayList<>();

    public void createNewUser() {
        User user = new User();

        System.out.println("What is your username?");
        String userName;
        do {
            System.out.println("That name is already taken, choose another one please!");
            userName = GetScanner.newScanner();
        }
        while (!users.contains(userName));
        user.setUserName(userName);

        user.setPermission(Privilege.USER);

        System.out.println("Please type in your e-mail address!");
        String email = GetScanner.newScanner();
        user.setE_mail(email);

        System.out.println("Please add your password! (Hint: don't let anybody see");
        String pw = GetScanner.newScanner();
        user.setPassword(pw);

        Date date = new Date();
        user.setRegistration_date(date);

        users.add(user);
        System.out.println("Profile successfully created!");
    }

    public boolean addUserToDB (User user) {
        String query ="INSERT INTO enduser (name, privilege, email, password, avatar, registration_date) VALUES (?, ?, ? ,?, ?, ?);";

        try {
            PreparedStatement ps = connect != null ? connect.prepareStatement(query) : null;
            ps.setString(1, user.getUserName());
            ps.setInt(2, user.getPermission().getDBIndex());
            ps.setString(3, user.getE_mail());
            ps.setString(4, user.getPassword());
            ps.setDate(5, (java.sql.Date) user.getRegistration_date());
            return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getUsers() {
        return users;
    }
}
