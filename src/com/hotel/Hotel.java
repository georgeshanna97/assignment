package com.hotel;

import com.rooms.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.stream.IntStream;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.Math.*;

/**
 * @author Georges Hanna
 */
public class Hotel {

    private ArrayList<Room> rooms = null; //Array containing the rooms of this hotel
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d,M,yyyy"); //date format parser

    /**
     *
     * @param roomCount: Number of rooms this hotel has.
     */
    public Hotel(int roomCount) {
        rooms = new ArrayList<Room>();
        for (int i = 0; i < roomCount; i++) {
            Room newRoom = RoomFactory.makeRoom(i);
            rooms.add(newRoom);
        }
    }

    /**
     * Prints out the information of a room at index <num>.
     * @param num index of a room in the rooms array.
     */
    public void getRoomInfo(int num){
        rooms.get(num).getInfo();
    }

    /**
     * Checks if a certain room type is available on a specific date and prints out according information.
     * @param date A string representing a date in the format of the formatter.
     * @param roomType A string representing the room type.
     */
    public void isAvailable(String date, String roomType) {
        LocalDate localDate = LocalDate.parse(date, formatter);
        for (Room r : this.rooms) {
            if (roomType.equalsIgnoreCase(r.getRoomType())) {
                if (r.isAvailable(localDate)) {
                    System.out.println("A " + roomType + " room is available on " + date);
                    return;
                }
            }
        }
        System.out.println("There is no available " + roomType + " rooms on " + date);
    }

    /**
     * Modifies the price of all rooms that match the roomType to the price specified in newPrice arguments.
     * @param roomType A string representing the room type.
     * @param newPrice A non-0 number that will represent the new price of the specified room types.
     */
    public void changePrice(String roomType, double newPrice) {
        for (Room r : rooms) {
            if (roomType.equalsIgnoreCase(r.getRoomType())) {
                r.setPrice(newPrice);
            }
        }
        System.out.println("The prices of " + roomType + " rooms have successfully changed to " + newPrice);
    }

    /**
     * Updates the prices to either increase or decrease by cost amount for
     * a range of rooms specified in the ranges ArrayList.
     * @param ranges An Arraylist of strings in the format of "1-5,10-15,Standard" representing different ranges
     *               of rooms to apply the change of cost.
     * @param cost A number representing how much should the price be updated by.
     *             Positive for increase and negative for decrease.
     */
    public void updatePrice(ArrayList<String> ranges, double cost) {
        for (String s: ranges){
            IntStream range = RoomFactory.getRoomRange(s.toLowerCase());
            if (range == null){
                String[] split = s.split("-");
                int first = Integer.parseInt(split[0]);
                int second = Integer.parseInt(split[1]);
                range = IntStream.rangeClosed(min(first, second) , max(first, second));
            }
            range.forEach(i -> {
                double price = rooms.get(i).getPrice();
                System.out.println("Price of room " + i + " updated from " + price + " to " + (price + cost));
                rooms.get(i).updatePrice(cost);
            });
            if (s.contains("-")){
                System.out.println("Price of rooms in the range " + s + " updated.");
            }else{
                System.out.println("Price of rooms of type " + s + " updated.");
            }
        }
    }

    /**
     * Applies a discount of percent amount to a range of rooms specified in the ranges ArrayList.
     * @param ranges An array list of strings in the format of "1-5,10-15,Standard" representing different ranges
     *               of rooms to apply the change of cost.
     * @param percent A number representing a percentage to be used as a discount.
     */
    public void applyDiscount(ArrayList<String> ranges, double percent) {
        for (String s: ranges){
            IntStream range = RoomFactory.getRoomRange(s.toLowerCase());
            if (range == null){
                String[] split = s.split("-");
                int first = Integer.parseInt(split[0]);
                int second = Integer.parseInt(split[1]);
                range = IntStream.rangeClosed(min(first, second) , max(first, second));
            }
            range.forEach(i -> {
                double price = rooms.get(i).getPrice();
                System.out.println("Price of room " + i + " discounted by  " + (price * (0.01* percent)));
                rooms.get(i).applyDiscount(percent);
            });
            if (s.contains("-")){
                System.out.println("Price of rooms in the range " + s + " updated.");
            }else{
                System.out.println("Price of rooms of type " + s + " updated.");
            }
        }
    }

    /**
     * If available Books a room of specified roomType on the specific Date.
     * @param date A string representing a date in the format of the formatter.
     * @param roomType A string representing the room type.
     */
    public void bookRoom(String date, String roomType) {
        LocalDate localDate = LocalDate.parse(date, formatter);
        for (Room r : rooms) {
            if (roomType.equalsIgnoreCase(r.getRoomType())) {
                if (r.bookRoom(localDate)) {
                    System.out.println(
                            roomType + " room # " + r.getNumber() + " has been successfully booked on " + date + ".");
                    return;
                }
            }
        }
        System.out.println("There are no " + roomType + " rooms available to book on " + date);
    }

    /**
     * If booked, cancels the booking for the room with the specified room number.
     * @param date A string representing a date in the format of the formatter.
     * @param roomNumber A number representing a room number.
     */
    public void cancelBooking(String date, int roomNumber) {
        LocalDate localDate = LocalDate.parse(date, formatter);
        Room r = rooms.get(roomNumber);
        if (r.cancelBooking(localDate)) {
            System.out.println("room " + roomNumber + " booking has been successfully cancelled for " + date);
        } else {
            System.out.println("room " + roomNumber + " is not booked on " + date);
        }
    }
}
