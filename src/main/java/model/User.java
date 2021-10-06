package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class User {

    @Setter
    @Getter
    private String userName;

    @Setter
    @Getter
    private Privilege permission;

    @Setter
    @Getter
    private String e_mail;

    private String password;

    public void setPassword(String password) {
        if (password.length() > 6) {
            this.password = password;
        }
        System.out.println("Password is too short!");
    }

    public String getPassword() {
        StringBuilder stringBuilder = null;
        try {
            for (int i = 0; i < password.length(); i++) {
                stringBuilder.append("x");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        return String.valueOf(stringBuilder);
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

    public User(String userName, Privilege permission, String email, String password, byte[] avatar, LocalDateTime registration_date) {
    }

}
