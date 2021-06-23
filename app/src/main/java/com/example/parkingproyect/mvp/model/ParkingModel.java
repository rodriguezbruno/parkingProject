package com.example.parkingproyect.mvp.model;

public class ParkingModel {

    private int parkingSize;

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

    public boolean addNewReservation(Reservation reservation) {
        if (reservation == null) {
            return false;
        }
        if (reservation.getParkingLot() <= getParkingSize()) {
            this.reservationsList.add(reservation);
        }
        return true;
    }

}
