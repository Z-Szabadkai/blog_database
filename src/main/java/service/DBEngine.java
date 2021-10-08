package service;

import config.*;
import model.*;
import service.ConnectToOuterDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBEngine {

    Connection connect = ConnectToOuterDB.connectToSQL();

    public boolean registerUser(User user) {
        if ((user.getName() == null) || (user.getEmail() == null) || (user.getPassword() == null)) {
            return false;
        }
        user.setPrivilege(Privilege.USER);
        return true;
    }

    /*
    This method was made to list all the information regarding an user.
     */

    public User getUserData(User user) {
        String query = ConfigReader.selectSQLDB(null, "enduser", "w", "name") + user.getName();
        User result = null;

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
        return result;
    }

    /*
    This method was made to list all the users in the database.
     */

    public List<User> listAllUserData (Privilege privilege) {
        String query = ConfigReader.selectSQLDB(null, "enduser", "w", "privilege") + privilege;
        List<User> userList = new ArrayList<>();

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
        return userList;
    }

    /*
    This method was made to search a blog by name.
     */

    public Blog findBlogByName (String name) {
        String query = ConfigReader.selectSQLDB(null, "blog", "w", "title") + name;
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
        String query = ConfigReader.selectSQLDB(null, "blog", "w", "blog_creator") + userId;

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
        String query = ConfigReader.selectSQLDB(null, "content", "w", "title") + content;
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
        String query = ConfigReader.selectSQLDB(null, "content", "w", "blog_content") + title;
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
        String query = ConfigReader.selectSQLDB(null, "comment", "w", "comment_under") + content.getContentId();
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
        return null;
    }

}
