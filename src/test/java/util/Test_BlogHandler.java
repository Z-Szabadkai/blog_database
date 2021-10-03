package util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Test_BlogHandler {

    @Test
    void test_createNewUser () {
        String data = "Zoli" +  "zoltan@sgmail.com" + "pw";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
    }
}
