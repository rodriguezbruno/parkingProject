package com.example.parkingproyect.mvp.model;

public class Parking {

    private int parkingSize;
    private ArrayList<ParkingLot> parkingLotList;

    public Parking() {
        this.parkingLotList = new ArrayList<>();
    }

    public int getParkingSize() {
        return parkingSize;
    }

    public void setParkingSize(String newParkingSize) throws IllegalArgumentException {
        int parseSize = Integer.parseInt(newParkingSize);
        if (parseSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.parkingSize = parseSize;
    }
}
