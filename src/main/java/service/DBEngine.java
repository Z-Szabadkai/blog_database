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

    public List<Content> readBlog(User user) {
        List<Blog> userBlog = user.getBlogList();
        List<Content> userContent = new ArrayList<>();

        if (userBlog.size() == 1) {
            userContent = userBlog.get(0).getPostList();
        } else {

        }
        return userContent;
    }


    public User findUser (String name) {
        String query = ConfigReader.selectSQLDB(null, "enduser", "w") + "name = ?";
        User user = null;

        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("user_id");
                String userName = resultSet.getString("name");
                String privilegeDB = resultSet.getString("privilege").toUpperCase();
                Privilege privilege = Privilege.valueOf(privilegeDB);
                String email = resultSet.getString("email");
                LocalDateTime date = resultSet.getTimestamp("registration_date").toLocalDateTime();

                user = new User(userName, privilege, email, date);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /*
    This method is made to give back all the users with their own blogs.
     */

    public List<User> listAllUsers (User endUser) throws SQLException {
        String query = ConfigReader.selectSQLDB(null, "enduser", null);
        List<User> userList = new ArrayList<>();
        PreparedStatement statement = connect.prepareStatement(query);

        if (!endUser.getPrivilege().equals(Privilege.LURKER)) {

            try {
                statement.setLong(1, Long.parseLong("userId"));
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    long userId = resultSet.getLong("user_id");
                    String name = resultSet.getString("name");
                    String privilegeDB = resultSet.getString("privilege").toUpperCase();
                    Privilege privilege = Privilege.valueOf(privilegeDB);
                    String email = resultSet.getString("email");
                    LocalDateTime date = resultSet.getTimestamp("registration_date").toLocalDateTime();

                    User user = new User(name, privilege, email, date);
                    user.setBlogList(findBlogsByUser(userId));
                    userList.add(user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    /*
    This method is for searching a blog by name.
     */

    public Blog findBlogByName (String name){
        String query = ConfigReader.selectSQLDB(null, "blog", "w") + "title = ?";
        Blog blog = null;

        try {
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

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
    This method is for finding the blogs by a given user.
     */

    public List<Blog> findBlogsByUser (long userId) {
        String query = ConfigReader.selectSQLDB(null, "blog", "w") + "blog_creator = ?";

        List<Blog> blogs = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String blogName = resultSet.getString("title");
                Blog blog = findBlogByName(blogName);
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
}
