package com.vehicleInterface;

public interface Repairable {

    public static final int CAR = 500;
    public static final int MOTO = 300;
    public static final int TRUCK = 400;
    public static final int BIKE = 50;

    public boolean repair();
}

