package service;

import config.*;
import model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBEngine {

    Connection connect = ConnectToOuterDB.connectToSQL();

    /*
    This method was made for logging in with the help of a scanner. It won't let the user call other methods without the exact credentials.
     */

    public boolean login(User endUser) {
        String name = getScanner();
        String email = getScanner();
        String password = getScanner();
        return ((endUser.getName().equals(name)) || (endUser.getEmail().equals(email))) && (endUser.getPassword().equals(password));
    }

    /*
    This method is for the check of an user export.
     */

    public boolean addUsersToDB(User user) {
        String query = ConfigReader.intoSQLDB("user");

        if ((login(user) && user.getPrivilege().equals(Privilege.ADMIN))) {
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
            }
        }
        return false;
    }

    public boolean authorizeContent(User user, Content content) {
        if ((user.getPrivilege().equals(Privilege.ADMIN)) && (content.getStatus() == Content.postStatus.PENDING)) {
            content.setStatus(Content.postStatus.PUBLISHED);
            return true;
        }
        return false;
    }

    public void addContentToDB (User user, User endUser, Content content) {
        String query = ConfigReader.intoSQLDB("blog");

        if (authorizeContent(user, content)) {

            try {
                PreparedStatement ps = connect != null ? connect.prepareStatement(query) : null;
                ps.setString(1, endUser.getName());
                ps.setString(2, content.getTitle());
                ps.setString(3, content.getPost());
                for (String str : content.getTags()) {
                    ps.setString(4, str);
                }
                ps.setString(5, content.getStatus().toString());
                ps.setTimestamp(6, Timestamp.valueOf(content.getPublished_date()));

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean writeNewComment (Comment comment, Content content, User user) {
        String query = ConfigReader.intoSQLDB("comment");

        if ((!user.getPrivilege().equals(Privilege.LURKER)) && (content.isCan_comment_under()))
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, comment.getComment_post());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(comment.getComment_date()));

            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getScanner() {
        return new Scanner(System.in).next();
    }
}
