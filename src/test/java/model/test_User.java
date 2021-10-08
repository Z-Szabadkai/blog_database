package model;

import org.junit.Assert;
import org.junit.Test;

public class test_User {

    @Test
    public void test_getPassword_null() {
        User user = new User();
        user.setPassword(null);

        Assert.assertEquals("There has been a mistake, you need to give a password!", user.getPassword());
    }

    @Test
    public void test_getPassword_Short() {
        User user = new User();
        user.setPassword("abc");

        Assert.assertEquals("Password is too short, try again please!", user.getPassword());
    }

    @Test
    public void test_getPassword_Actual() {
        User user = new User();
        user.setPassword("abcdef");

        Assert.assertEquals("******", user.getPassword());
    }

}
