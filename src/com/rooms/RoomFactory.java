package com.rooms;

import java.util.stream.IntStream;

public class RoomFactory {
    private static final double StandardPrice = 99.99;
    private static final double DeluxePrice = 199.99;
    private static final double SuitePrice = 349.99;

    public static Room makeRoom(int number){
        if (number <= Standard.getRoomCount()) {
            return new Standard(number, StandardPrice);
        } else if (number <= Deluxe.getRoomCount()){
            return new Deluxe(number, DeluxePrice);
        } else if (number <= Suite.getRoomCount()){
            return new Suite(number, SuitePrice);
        }
        return null;
    }

    public static IntStream getRoomRange(String roomType){
        if (roomType.equalsIgnoreCase("Standard")) {
            return IntStream.rangeClosed(0, Standard.getRoomCount());
        } else if (roomType.equalsIgnoreCase("Deluxe")){
            return IntStream.rangeClosed(Standard.getRoomCount(), Deluxe.getRoomCount());
        } else if (roomType.equalsIgnoreCase("Suite")){
            return IntStream.rangeClosed(Deluxe.getRoomCount(), Suite.getRoomCount());
        }
        return null;
    }
}
