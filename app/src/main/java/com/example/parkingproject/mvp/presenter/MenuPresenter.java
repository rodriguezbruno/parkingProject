package com.example.parkingproject.mvp.presenter;

import com.example.parkingproject.entities.Parking;

public class MenuPresenter {

    private Parking parking;

    public MenuPresenter(int parkingSize) {
        parking = new Parking(parkingSize);
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
