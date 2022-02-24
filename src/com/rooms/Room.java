package com.rooms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

/**
  * An abstract class that represents the skeleton and general attributes of a room.
  */
public abstract class Room {

    protected Double price; // price of the room
    protected int number; // room number
    protected String roomType; //the type of the room.
    protected HashMap<String, Boolean> availability; //availabilities of the room on a certain date.


    /**
     * Initializes the general attributes of a room.
     * @param num int representing the room number.
     * @param price double representing the price of the room.
     */
    protected void initRoom(int num, Double price){
        this.price = price;
        this.number = num;
        this.availability = new HashMap<>();
    }

    //GETTERS AND SETTERS
    public String getRoomType() { return this.roomType; }
    public Double getPrice() { return this.price; }
    public int getNumber() {return this.number;}
    public void getInfo() {System.out.println("Room number: " + number + "\nRoom type: " +  roomType + "\nPrice: " + price);}
    public void setPrice(Double price) { this.price = price; }

    /**
     * Adds price argument to this classes price attribute.
     * @param price the price to add to the price attribute.
     */
    public void updatePrice(Double price) {this.price += price;}

    /**
     * Decreases the price by percentage amount.
     * @param percentage the percentage to decrease the price attribute by.
     */
    public void applyDiscount(Double percentage) {

        this.price = this.price - (this.price * (percentage * 0.01));
    }

    /**
     * Checks if the room is available on the specified date.
     * @param date an object of LocalDate.
     * @return True if the room is available on the specified date, false otherwise.
     */
    public Boolean isAvailable(LocalDate date){
        String sDate = date.toString();
        if (Objects.isNull(this.availability.get(sDate))){
            return true;
        } else {
            return this.availability.get(sDate);
        }
    }

    /**
     * Checks if a room is available on the specified date and books it.
     * @param date an object of LocalDate used to check availability.
     * @return True if the room has successfully been booked otherwise false.
     */
    public boolean bookRoom(LocalDate date){
        if (isAvailable(date)){
            String sDate = date.toString();
            this.availability.put(sDate, false);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if room is booked on the specified date and cancels its booking.
     * @param date an object of LocalDate used to check availability.
     * @return True if the booking has been successfully canceled otherwise false.
     */
    public boolean cancelBooking(LocalDate date){
        if (!isAvailable(date)){
            String sDate = date.toString();
            this.availability.put(sDate, true);
            return true;
        } else {
            return false;
        }
    }
}
