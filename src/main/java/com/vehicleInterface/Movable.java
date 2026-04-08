package com.vehicleInterface;

public interface Movable {

    public static final int CAR = 10;
    public static final int MOTO = 8;
    public static final int TRUCK = 7;
    public static final int BIKE = 3;
    public static final int CAR_CONSUM = 10;
    public static final int MOTO_CONSUM = 4;
    public static final int TRUCK_CONSUM = 6;

    public boolean move(char pos);

    public boolean collision();
}
