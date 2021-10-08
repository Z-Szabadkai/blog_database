package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class User {

    @Setter
    @Getter
    private long userId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Privilege privilege = Privilege.LURKER;

    @Setter
    @Getter
    private String email;

    @Setter
    private String password;

    /*
    This method takes the given password (which must be at least 6 characters long) as an input, and hides it.
    */

    public String getPassword() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (password == null) {
                return "There has been a mistake, you need to give a password!";
            } else if (password.length() < 6) {
                return "Password is too short, try again please!";
            } else {
                for (int i = 0; i < password.length(); i++) {
                    stringBuilder.append("*");
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        return stringBuilder.toString();
    }

    @Setter
    @Getter
    private byte[] avatar;

    @Setter
    @Getter
    private LocalDateTime registration_date;

    @Getter
    @Setter
    private List<Blog> blogList;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, Privilege privilege, String email, byte[] avatar, LocalDateTime registration_date) {
        this.name = name;
        this.privilege = privilege;
        this.email = email;
        this.avatar = avatar;
        this.registration_date = registration_date;
    }

    public User(Long userId, String name, Privilege privilege, String email, String password, byte[] avatar, LocalDateTime registration_date) {
        this.name = name;
        this.privilege = privilege;
        this.email = email;
        this.password = password;
        this.registration_date = registration_date;
    }

}
