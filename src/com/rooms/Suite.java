package com.rooms;

public class Suite extends Room {
    private static final int roomCount = 9;
    public Suite(int num, Double price) {
        this.roomType = "Suite";
        super.initRoom(num, price);
    }

    public static int getRoomCount() {
        return roomCount;
    }
}