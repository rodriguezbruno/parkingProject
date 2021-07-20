package com.example.parkingproject.mvp.presenter;

import com.example.parkingproject.entities.Parking;
import com.example.parkingproject.entities.Reservation;
import com.example.parkingproject.mvp.model.ReservationModel;
import com.example.parkingproject.mvp.view.ReservationView;

import java.util.Date;

public class ReservationPresenter {

    private final ReservationModel reservationModel;
    private final ReservationView reservationView;

    public ReservationPresenter(ReservationModel reservationModel, ReservationView reservationView) {
        this.reservationModel = reservationModel;
        this.reservationView = reservationView;
    }

    public void onStartDateTimePressed() {
        reservationView.showStartDateTimeInput();
    }

    public void onEndDateTimePressed() {
        reservationView.showEndDateTimeInput();
    }

    public boolean onAddNewReservationPressed() {
        String securityCode = reservationView.getSecurityCode();
        int parkingNumber = reservationModel.getParkingNumber(reservationView.getParkingLotNumber());
        long startDate = reservationView.getStartDate().getTime();
        long endDate = reservationView.getEndDate().getTime();

        if (validateSecurityCode(securityCode) && validateParkingLot(parkingNumber) && validateReservationDates(startDate, endDate)) {
            Reservation reservation = new Reservation(securityCode, startDate, endDate, parkingNumber);
            if (reservationModel.addReservation(reservation)) {
                reservationView.showSuccessMessageReservationAdded();
                return true;
            } else {
                reservationView.showErrorParkingNumberWithSameDates();
                return false;
            }
        }
        return false;
    }

    public boolean validateSecurityCode(String securityCode) {
        if (securityCode.isEmpty()) {
            reservationView.showErrorSecurityCodeNull();
            return false;
        }
        if (!(securityCode.length() >= 3 && securityCode.length() <= 5)) {
            reservationView.showErrorSecurityCodeOutOfNumberRange();
            return false;
        }
        return true;
    }

    public boolean validateParkingLot(int parkingNumber) {
        if (parkingNumber <= 0) {
            reservationView.showErrorParkingLotLessOrEqualsTo0();
            return false;
        }
        if (!(parkingNumber <= reservationModel.getParking().getParkingSize())) {
            reservationView.showErrorParkingNumberOutOfRange();
            return false;
        }
        return true;
    }

    public boolean validateReservationDates(long startDate, long endDate) {
        if (startDate <= 0 || endDate <= 0) {
            reservationView.showErrorReservationDatesEmpty();
            return false;
        }
        if (!(startDate < endDate && startDate >= new Date().getTime())) {
            reservationView.showErrorDatesOutOfRange();
            return false;
        }
        return true;
    }

    public Parking getParkingWithReservations() {
        return reservationModel.getParking();
    }
}
