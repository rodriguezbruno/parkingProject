package com.example.parkingproyect.mvp.model;

public class Parking {

    private int parkingSize;

    public int getParkingSize() {
            return parkingSize;
    }

    public void setParkingSize(String newParkingSize) throws NumberFormatException{
            this.parkingSize = Integer.parseInt(newParkingSize);
    }
}
