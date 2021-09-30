package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class User {

    public User() {
    }

    public User(String userName, byte[] avatar) {}

    @Setter
    @Getter
    private String userName;

    @Setter
    @Getter
    private Privilege permission;

    @Setter
    @Getter
    private String e_mail;

    @Setter
    private String password;

    @Setter
    @Getter
    private byte[] avatar;

    @Setter
    @Getter
    private Date registration_date;

}
