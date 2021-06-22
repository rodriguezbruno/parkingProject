package com.example.parkingproyect.mvp.model;

import android.util.Log;

import com.example.parkingproyect.entities.Parking;
import com.example.parkingproyect.entities.Reservation;

import java.util.ArrayList;

public class ReservationModel {

    private final Parking parking;

    public ReservationModel(Parking parking) {
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public boolean addReservation(Reservation reservation) {
        if (validateAddReservation(reservation)) {
            parking.getReservationsList().add(reservation);
            return true;
        } else {
            return false;
        }
    }

    public boolean validateAddReservation(Reservation reservation) {
        ArrayList<Reservation> reservations = parking.getReservationsList();
        if (reservations.size() == 0) {
            return true;
        }
        for (Reservation reservationInList : reservations) {
            if (reservation.getParkingLot() == reservationInList.getParkingLot() &&
                    reservation.getStartDateTime() <= reservationInList.getEndDateTime()) {
                return false;
            }
        }
        return true;
    }

    public int getParkingNumber(String parkingNumberString) {
        int parkingNumber = 0;
        try {
            parkingNumber = Integer.parseInt(parkingNumberString);
        } catch (NumberFormatException ex) {
            Log.e(ReservationModel.class.getSimpleName(), ex.getLocalizedMessage());
        }
        return parkingNumber;
    }
}
