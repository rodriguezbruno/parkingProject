package com.example.parkingproyect.mvp.model;

import java.util.ArrayList;

public class Parking {

    private int parkingSize;
    private ArrayList<ParkingLot> parkingLotList;

    public Parking(){
        this.parkingLotList = new ArrayList<>();
    }

    public int getParkingSize() {
            return parkingSize;
    }

    public void setParkingSize(String newParkingSize) throws NumberFormatException{
            this.parkingSize = Integer.parseInt(newParkingSize);
    }

}
