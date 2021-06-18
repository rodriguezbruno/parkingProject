package com.example.parkingproyect.mvp.model;

import android.util.Log;

import com.example.parkingproyect.mvp.view.ParkingView;

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

    public void setParkingSize(String newParkingSize) throws NumberFormatException{
            this.parkingSize = Integer.parseInt(newParkingSize);
    }

}
