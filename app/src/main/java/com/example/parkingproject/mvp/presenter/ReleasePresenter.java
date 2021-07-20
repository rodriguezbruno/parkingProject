package com.example.parkingproject.mvp.presenter;

import com.example.parkingproject.entities.Parking;
import com.example.parkingproject.entities.Reservation;
import com.example.parkingproject.exceptions.ReleaseMoreThanOneMatchException;
import com.example.parkingproject.exceptions.ReleaseNoMatchException;
import com.example.parkingproject.exceptions.ReservationListEmptyException;
import com.example.parkingproject.mvp.model.ReleaseModel;
import com.example.parkingproject.mvp.view.ReleaseView;

public class ReleasePresenter {

    private final ReleaseModel releaseModel;
    private final ReleaseView releaseView;

    public ReleasePresenter(ReleaseModel releaseModel, ReleaseView releaseView) {
        this.releaseModel = releaseModel;
        this.releaseView = releaseView;
    }

    public boolean onReleaseParkingPressed() {
        Reservation reservation = new Reservation();
        int parkingNumber = releaseModel.getParkingNumber(releaseView.getParkingLotNumber());
        String securityCode = releaseView.getSecurityCode();

        if (validateSecurityCode(securityCode) && validateParkingLot(parkingNumber)) {
            reservation.setSecurityCode(securityCode);
            reservation.setParkingLot(parkingNumber);

            try {
                releaseModel.parkingRelease(reservation);
                releaseView.showSuccessMessageReleaseReservation();
                return true;
            } catch (ReservationListEmptyException ex) {
                releaseView.showErrorReservationListEmptyException();
            } catch (ReleaseMoreThanOneMatchException ex) {
                releaseView.showErrorReleaseMoreThanOneMatchException();
            } catch (ReleaseNoMatchException ex) {
                releaseView.showErrorReleaseNoMatchException();
            }
        }
        return false;
    }

    public boolean validateSecurityCode(String securityCode) {
        if (securityCode.isEmpty()) {
            releaseView.showErrorSecurityCodeNull();
            return false;
        }
        if (!(securityCode.length() >= 3 && securityCode.length() <= 5)) {
            releaseView.showErrorSecurityCodeOutOfNumberRange();
            return false;
        }
        return true;
    }

    public boolean validateParkingLot(int parkingNumber) {
        if (parkingNumber <= 0) {
            releaseView.showErrorParkingLotLessOrEqualsTo0();
            return false;
        }
        if (!(parkingNumber <= releaseModel.getParking().getParkingSize())) {
            releaseView.showErrorParkingNumberOutOfRange();
            return false;
        }
        return true;
    }

    public Parking getParkingWithReservations() {
        return releaseModel.getParking();
    }
}
