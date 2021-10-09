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
        return (endUser.getName().equals(name)) && (endUser.getEmail().equals(email)) && (endUser.getPassword().equals(password));
    }

    /*
    This method was made to list all the information regarding an user but only if the profile to be edited is it's own, or the admin wants to modify.
     */

    public User getUserData(User endUser) {
        String query = ConfigReader.selectSQLDB(null, "enduser", "w", "name") + "'" + endUser.getName() + "'";
        User result = null;

        if ((login(endUser)) || (endUser.getPrivilege().equals(Privilege.ADMIN))){
            try {
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String privilegeDB = resultSet.getString("privilege").toUpperCase();
                    Privilege privilege = Privilege.valueOf(privilegeDB);
                    String email = resultSet.getString("email");
                    byte[] avatar = resultSet.getBytes("avatar");
                    LocalDateTime reg_date = resultSet.getTimestamp("registration_date").toLocalDateTime();

                    result = new User(name, privilege, email, avatar, reg_date);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /*
    This method was made to list all the users in the database.
     */

    public List<User> listAllUserData (User endUser, Privilege privilege) {
        String query = ConfigReader.selectSQLDB(null, "enduser", "w", "privilege") + "'" + privilege + "'";
        List<User> userList = new ArrayList<>();

        if ((login(endUser)) && (endUser.getPrivilege().equals(Privilege.ADMIN))) {
            try {
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    long userId = resultSet.getLong("user_id");
                    String name = resultSet.getString("name");
                    String permissionDB = resultSet.getString("privilege").toUpperCase();
                    Privilege permission = Privilege.valueOf(permissionDB);
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    byte[] avatar = resultSet.getBytes("avatar");
                    LocalDateTime reg_date = resultSet.getTimestamp("registration_date").toLocalDateTime();

                    User user = new User(userId, name, permission, email, password, avatar, reg_date);
                    userList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    /*
    This method was made to search a blog by name.
     */

    public Blog findBlogByName (String name) {
        String query = ConfigReader.selectSQLDB(null, "blog", "w", "title") + "'" + name + "'";
        Blog blog = null;

        try {
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String title = resultSet.getString("title");

                blog = new Blog(title);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blog;
    }

    /*
    This method was made to find all the blogs by a given user.
     */

    public List<Blog> findBlogsByUser (long userId) {
        String query = ConfigReader.selectSQLDB(null, "blog", "w", "blog_creator") + "'" +  userId + "'";

        List<Blog> blogList = new ArrayList<>();

        try {
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String blogName = resultSet.getString("title");
                Blog blog = findBlogByName(blogName);
                blogList.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogList;
    }

    /*
    This method was made to find a specific blogpost.
     */

    public Content findContentByName(String content) {
        String query = ConfigReader.selectSQLDB(null, "content", "w", "title") + "'" + content + "'";
        Content result = null;

        try {
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                result = new Content(title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
    This method was made to find all the posts of a given blog.
     */

    public List<Content> readBlog (String title) {
        String query = ConfigReader.selectSQLDB(null, "content", "w", "blog_content") + "'" +  title + "'";
        List<Content> contentList = new ArrayList<>();

        try {
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String blogContent = resultSet.getString("blog_content");
                Content content = findContentByName(blogContent);
                contentList.add(content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentList;
    }

    /*
    This method was made to list all the comments under a specific content.
     */

    public List<Comment> listAllComments (Content content) {
        String query = ConfigReader.selectSQLDB(null, "comment", "w", "comment_under") + "'" +  content.getContentId() + "'";
        List<Comment> commentList = new ArrayList<>();

        try {
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long commentId = resultSet.getLong("comment_id");
                String commentPost = resultSet.getString("comment_post");
                LocalDateTime commentDate = resultSet.getTimestamp("comment_date").toLocalDateTime();

                Comment comment = new Comment(commentId, commentPost, commentDate);
                commentList.add(comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }

    /*
    This method was made to add a new comment to the database, with the required info (as in who write what and when)
     */
    public boolean writeNewComment (Comment comment, User user) {
        String query = ConfigReader.intoSQLDB("comment");

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
