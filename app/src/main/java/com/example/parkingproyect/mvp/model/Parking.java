package com.example.parkingproyect.mvp.model;

import java.util.ArrayList;

public class Parking {

    private int parkingSize;
    public ArrayList<ParkingLot> parkingLotList;

    public Parking(){
        this.parkingLotList = new ArrayList<>();
    }

    public int getParkingSize() {
        return parkingSize;
    }

    public void setParkingSize(int newParkingSize){
        this.parkingSize = newParkingSize;
    }
}
