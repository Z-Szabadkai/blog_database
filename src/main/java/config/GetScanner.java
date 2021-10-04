package config;

import java.util.Scanner;

public class GetScanner {

    public static String newScanner() {
        Scanner sc =  new Scanner(System.in);
        return sc.next();
    }
}
