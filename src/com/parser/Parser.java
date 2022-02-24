package com.parser;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A class used to parse for specific input.
 */
public class Parser {
    private static final String roomString = "Standard|Deluxe|Suite";
    private static final String rangeString = "[0-9]|10";

    /**
     * Parses the input to match the range format.
     * @return Arraylist of the accepted input, an empty ArrayList if the user exits.
     */
    public static ArrayList<String> rangeParser() {
        System.out.print("Enter a range or room types separated by commas (eg: 1-5,205-210,suite,standard): ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replaceAll("\\s", "");
        String str_pat = "^((" + roomString + ")?,?)*((" + rangeString + ")-(" + rangeString + "),?)*" +
                "((" + roomString + ")?,?)*$";
        Pattern pattern = Pattern.compile(str_pat, Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(input);
        while (!match.matches()) {
            System.out.print("Invalid input\n" +
                    "Enter a range or room types separated by commas (eg: 1-5,205-210, Suite, Standard): ");
            input = sc.nextLine();
            match = pattern.matcher(input);
            if (input.equalsIgnoreCase("EXIT")) {
                return new ArrayList<String>();
            }
        }
        String[] values = input.split(",");
        List<String> valuesToList = Arrays.asList(values);
        valuesToList = valuesToList.stream().distinct().collect(Collectors.toList());

        return new ArrayList<String>(valuesToList);
    }

    /**
     * Parses input for a number of type double.
     * @return Double representing the input number.
     */
    public static double numberParser() {
        try {
            System.out.print("Enter a number: ");
            Scanner sc = new Scanner(System.in);
            return  sc.nextDouble();
        } catch (InputMismatchException e){
            System.out.println("Input Mismatch! Please enter numbers: ");
            return numberParser();
        }
    }

    /**
     * Parses input for a non-negative integer.
     * @param max represents the largest accepted integer.
     * @return Int representing the accepted input, -1 if the user exits.
     */
    public static int roomNumberParser(int max) {
        try {
            System.out.print("Enter a room number between 0 and " + max + ": ");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            while (input < 0 || input > max) {
                System.out.print("Invalid room number\nEnter a room number between 0 and " + max + ": (-1 to exit): ");
                input = sc.nextInt();
                if (input == -1) {
                    return -1;
                }
            }
            return input;
        } catch (InputMismatchException e){
            System.out.println("Input Mismatch! Please enter numbers: ");
            return roomNumberParser(max);
        }
    }

    /**
     * Parses input for a string representing the room types.
     * @return String if the input is valid, null if the user exits.
     */
    public static String roomParser() {
        System.out.print("Enter a room type (" + roomString + "): ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String str_pat = "^STANDARD$|^DELUXE$|^SUITE$";
        Pattern pattern = Pattern.compile(str_pat, Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(input);
        while (!match.matches()) {
            System.out.print("Invalid input\nplease enter one of the following rooms (" + roomString + "): ");
            input = sc.nextLine();
            pattern = Pattern.compile(str_pat, Pattern.CASE_INSENSITIVE);
            match = pattern.matcher(input);
            if (input.equalsIgnoreCase("EXIT")) {
                return null;
            }
        }
        return input;
    }

    /**
     * Parses input for a string of format dd,mm,yyyy.
     * @return a String if the input is valid, null if the user exits.
     */
    public static String dateParser() {
        System.out.print("Enter a date (dd,mm,yyyy): ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!isValid(input)) {
            System.out.print("Invalid date input (dd,mm,yyyy): ");
            input = sc.nextLine();
            if (input.equalsIgnoreCase("EXIT")) {

                return null;
            }
        }

        return input;
    }

    /**
     * Parses the text argument to check if it matches date formatting (dd,mm,yyyy).
     * @param text A string text to be validated as correct format.
     * @return true if the format is valid, false if the format is invalid.
     */
    private static boolean isValid(String text) {
        String pattern = "^[0-3]?\\d,[01]?\\d,\\d{4}$";
        if (text == null || !text.matches(pattern))
            return false;
        SimpleDateFormat df = new SimpleDateFormat("dd,MM,yyyy");
        df.setLenient(false);
        try {
            Date enteredDate = df.parse(text);
            Date currentDate = new Date();
            return enteredDate.compareTo(currentDate) >= 0;
        } catch (Exception ex) {
            return false;
        }
    }
}
