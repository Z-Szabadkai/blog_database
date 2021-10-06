package util;

import model.Privilege;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Test_UserHandler {

    @Test
    public void test_createNewUser () {
        String data = "Zoli" + Privilege.USER + "zoltan@sgmail.com" + "fwocwcw" + LocalDateTime.now();
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
    }
}
