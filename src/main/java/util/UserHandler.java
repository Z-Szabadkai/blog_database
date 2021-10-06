package util;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserHandler {

    public static List<User> users = new ArrayList<>();

    public void addUser (User user) {
        if (!users.contains(user)) {
            users.add(user);
        } else {
            System.out.println("This name is already taken! Use a different one or try typing the password again!");
        }
    }

    public static List<User> getUsers() {
        return users;
    }
}
