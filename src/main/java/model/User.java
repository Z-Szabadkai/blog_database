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
    public String userName;

    @Setter
    @Getter
    public Privilege permission;

    @Setter
    @Getter
    public String e_mail;

    @Setter
    public String password;

    @Setter
    @Getter
    public byte[] avatar;

    @Setter
    @Getter
    public Date registration_date;

}
