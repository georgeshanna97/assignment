package com.application;

import com.hotel.*;
import java.util.*;
import com.parser.Parser;

public class Main {
    private static final int roomCount = 10; //Single location to update the room count of our hotel
    private static final HashMap<String, Runnable> commands = new HashMap<>(); //A hashmap storing a mapping of string to function to reduce usage of if statements.

    public static void main(String[] args) {
        Hotel myHotel = new Hotel(roomCount);
        commands.put("help", Main::helpMessage);
        commands.put("availability", () -> isAvailable(myHotel));
        commands.put("book", () -> bookRoom(myHotel));
        commands.put("cancel", () -> cancelBooking(myHotel));
        commands.put("change", () -> changePrice(myHotel));
        commands.put("update", () -> updatePrice(myHotel));
        commands.put("discount", () -> applyDiscount(myHotel));
        //TODO get room info by number
        commands.put("room info", () -> getRoomInfo(myHotel));
        System.out.print("Enter a command (help for more information): ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("EXIT")) {
            if (Objects.isNull(commands.get(input.toLowerCase()))) {
                System.out.println("Command not found.");
            } else {
                commands.get(input.toLowerCase()).run();
            }
            System.out.print("Enter a command (help for more information, Exit to end the program): ");
            input = sc.nextLine();
        }
        sc.close();
        System.out.println("Program has successfully terminated.");
    }

    /**
     * Parses input accordingly to get the information of a certain room number.
     * @param hotel An instance of a Hotel
     */
    public static void getRoomInfo(Hotel hotel){
        int num = Parser.roomNumberParser(roomCount);
        if (num == -1){
            return;
        }
        hotel.getRoomInfo(num);
    }

    /**
     * Parses input accordingly to apply a percentage discount to a range of rooms.
     * @param hotel An instance of a Hotel object.
     */
    private static void applyDiscount(Hotel hotel) {
        double percent = Parser.numberParser();
        ArrayList<String> roomTypes = Parser.rangeParser();
        if (roomTypes.isEmpty() || percent == -1) {
            return;
        } else if (percent > 100){
            percent = Parser.numberParser();
        }
        hotel.applyDiscount(roomTypes, percent);
    }

    /**
     * Parses input accordingly to either increase or decrease the price of a range of rooms by the specified amount.
     * @param hotel An instance of a Hotel object.
     */
    private static void updatePrice(Hotel hotel) {
        double updateValue = Parser.numberParser();
        ArrayList<String> roomTypes = Parser.rangeParser();
        if (roomTypes.isEmpty() || updateValue == -1) {
            return;
        }
        hotel.updatePrice(roomTypes, updateValue);
    }

    /**
     * Parses input accordingly to modify the price of all rooms of a certain room type to the specified new cost.
     * @param hotel An instance of a Hotel object.
     */
    private static void changePrice(Hotel hotel) {
        double newPrice = Parser.numberParser();
        String roomType = Parser.roomParser();
        if (roomType == null || newPrice == -1) {
            return;
        }
        hotel.changePrice(roomType, newPrice);
    }

    /**
     * Parses input accordingly to cancel a booking of a room number on the specified date.
     * @param hotel An instance of a Hotel object.
     */
    private static void cancelBooking(Hotel hotel) {
        int roomNumberParser = Parser.roomNumberParser(roomCount);
        String date = Parser.dateParser();
        if (roomNumberParser == -1 || date == null) {
            return;
        }
        hotel.cancelBooking(date, roomNumberParser);
    }

    /**
     * Parses input accordingly to book a certain room type on the specified date.
     * @param hotel An instance of a Hotel object.
     */
    private static void bookRoom(Hotel hotel) {
        String roomType = Parser.roomParser();
        String date = Parser.dateParser();
        if (roomType == null || date == null) {
            return;
        }
        hotel.bookRoom(date, roomType);
    }

    /**
     * Parses input accordingly to check if a certain room type is available on the specified date.
     * @param hotel An instance of a Hotel object.
     */
    private static void isAvailable(Hotel hotel) {
        String roomType = Parser.roomParser();
        String date = Parser.dateParser();
        if (roomType == null || date == null) {
            return;
        }
        hotel.isAvailable(date, roomType);
    }

    /**
     * Prints a help message specifying how to use this program.
     */
    private static void helpMessage() {
        System.out.println(
                "This program has 8 commands:\n" +
                "- \"availability\" to check if a room is available on a certain date after or for today.\n" +
                "- \"book\" to book a room on a certain date.\n" +
                "- \"cancel\" to cancel the booking for a certain room on a certain date after or for.\n"+
                "- \"change\" to modify the price of a range of rooms to a new price.\n"+
                "- \"update\" to update the price of a range of rooms by a certain amount.\n" +
                "- \"discount\" to apply a discount of a certain percentage to a set of room types.\n"+
                "- \"room info\" to get information of room type and price by number.\n"+
                "- \"help\" to display the commands.\n");
    }
}
