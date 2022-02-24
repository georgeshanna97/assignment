package com.rooms;

public class Standard extends Room{
    private static final int roomCount = 5;
    public Standard(Integer num, Double price) {
        super.initRoom(num, price);
        this.roomType = "Standard";
    }

    public static int getRoomCount() {
        return roomCount;
    }
}
