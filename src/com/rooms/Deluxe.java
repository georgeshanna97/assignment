package com.rooms;

public class Deluxe extends Room {
    private static final int roomCount = 8;
    public Deluxe(int num, Double price) {
        super.initRoom(num, price);
        this.roomType = "Deluxe";
    }
    public static int getRoomCount() {
        return roomCount;
    }
}