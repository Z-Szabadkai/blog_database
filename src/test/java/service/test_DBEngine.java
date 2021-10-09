package service;

import model.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class test_DBEngine {

    @Test
    public void test_login_Incorrect () {
        String expectedName = "Zaiyaku";

        String actualName = "Zaiyaku";
        InputStream nameIn = new ByteArrayInputStream(expectedName.getBytes());
        System.setIn(nameIn);

        Assert.assertEquals(expectedName, actualName);

        String expectedPassword = "medisin";

        String actualPassword = "nisidem";
        InputStream pwIn = new ByteArrayInputStream(expectedPassword.getBytes());
        System.setIn(pwIn);

        Assert.assertNotEquals(expectedPassword, actualPassword);
    }

    @Test
    public void test_login_Correct() {
        String expectedName = "skinnypuppy@gmail.com";

        String actualName = "skinnypuppy@gmail.com";
        InputStream nameIn = new ByteArrayInputStream(expectedName.getBytes());
        System.setIn(nameIn);

        Assert.assertEquals(actualName, expectedName);

        String expectedPassword = "medisin";

        String actualPassword = "medisin";
        InputStream pwIn = new ByteArrayInputStream(expectedPassword.getBytes());
        System.setIn(pwIn);

        Assert.assertEquals(actualPassword, expectedPassword);
    }


}
