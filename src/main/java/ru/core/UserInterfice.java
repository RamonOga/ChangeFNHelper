package ru.core;

import java.util.Scanner;

public class UserInterfice {

    public String scanStringData() {

        Scanner scanner = new Scanner(System.in,"cp866");
        /*String output = new String("utf-8");*/
        String output = scanner.nextLine();
        return output;
    }

    public int scanIntData() {
        Scanner scanner = new Scanner(System.in);
        int put = scanner.nextInt();
        return put;
    }
}
