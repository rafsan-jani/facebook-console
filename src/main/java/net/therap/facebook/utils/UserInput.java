package main.java.net.therap.facebook.utils;


import main.java.net.therap.facebook.entities.UserInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class UserInput {

    private static final UserInput userInput = new UserInput();
    private Scanner scanner;

    private UserInput() {
        scanner = new Scanner(System.in);
    }

    public static UserInput getInstance() {
        return userInput;
    }

    public String inputString() {
        String string;
        string = scanner.nextLine();
        scanner.nextLine();
        return string;
    }

    public int inputInteger() {
        int integer;
        integer = scanner.nextInt();
        scanner.nextLine();
        return integer;
    }

    public String getEmail() {
        System.out.print("Enter email: ");
        return inputString();
    }

    public String getPassword(int attempt) {
        if (attempt == 0) System.out.print("Enter password: ");
        else if (attempt == 1) System.out.print("Confirm password: ");
        return inputString();
    }

    private String inputFirstName() {
        System.out.print("First Name: ");
        return inputString();
    }

    private String inputLastName() {
        System.out.print("Last Name: ");
        return inputString();
    }

    private String inputSex() {
        System.out.print("Sex: ");
        return inputString();
    }

    public Date inputDate() {
        int day;
        int month;
        int year;

        System.out.println("Date of Birth(dd\\mm\\year)");
        System.out.print("Day(1-31): ");
        day = inputInteger();

        System.out.print("Month(1-12): ");
        month = inputInteger();

        System.out.print("Year: ");
        year = inputInteger();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        return calendar.getTime();
    }

    public UserInfo getUserInfo(int userId) {
        String firstName = inputFirstName();
        String lastName = inputLastName();
        String sex = inputSex();
        Date dateOfBirth = inputDate();

        return new UserInfo(userId, firstName, lastName, sex, dateOfBirth);
    }

    public String getStatus() {
        System.out.println("What's on your mind:");
        return inputString();
    }

    public int getId() {
        System.out.print("Select Id: ");
        return inputInteger();
    }

    public String getString(Clob clob) {
        if (clob == null) return "";

        StringBuffer str = new StringBuffer();
        String strng;


        BufferedReader bufferRead = null;
        try {
            bufferRead = new BufferedReader(clob.getCharacterStream());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while ((strng = bufferRead.readLine()) != null)
                str.append(strng);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str.toString();
    }

    public String getMessage() {
        System.out.println("Message content: ");
        return inputString();
    }

    public String getComment() {
        System.out.println("Comment content: ");
        return inputString();
    }
}
