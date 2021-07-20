package com.example.parkingproject.mvp.model;

import android.util.Log;

import com.example.parkingproject.entities.Parking;
import com.example.parkingproject.entities.Reservation;
import com.example.parkingproject.exceptions.ReleaseMoreThanOneMatchException;
import com.example.parkingproject.exceptions.ReleaseNoMatchException;
import com.example.parkingproject.exceptions.ReservationListEmptyException;

public class ReleaseModel {

    private final Parking parking;

    public ReleaseModel(Parking parking) {
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public void parkingRelease(Reservation reservation) throws ReservationListEmptyException,
            ReleaseMoreThanOneMatchException, ReleaseNoMatchException {
        if (getParking().getReservationsList().size() <= 0) {
            throw new ReservationListEmptyException();
        }
        int matches = countSameReservationInList(reservation);
        if (matches <= 0) {
            throw new ReleaseNoMatchException();
        }
        if (matches > 1) {
            throw new ReleaseMoreThanOneMatchException();
        }
        getParking().getReservationsList().remove(reservation);
    }

    int countSameReservationInList(Reservation reservation) {
        int countSameReservation = 0;
        for (Reservation reservationInList : getParking().getReservationsList()) {
            if (reservation.equals(reservationInList)) {
                countSameReservation++;
            }
        }
        return countSameReservation;
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