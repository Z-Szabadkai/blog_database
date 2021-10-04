package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class User {

    public User() {
    }

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
    private Date registration_date;

}
