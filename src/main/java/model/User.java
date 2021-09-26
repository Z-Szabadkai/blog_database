package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class User {

    public User() {
    }

    public User(String name, Privilege permission, String e_mail, String password, byte[] avatar, Date registration_date) {}

    @Setter
    @Getter
    public String name;

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
