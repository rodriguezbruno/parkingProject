package com.example.parkingproyect.mvp.presenter;

import com.example.parkingproyect.mvp.model.Parking;
import com.example.parkingproyect.mvp.view.ParkingView;

public class ParkingPresenter {

    private Parking parking;
    private ParkingView parkingView;

    public ParkingPresenter(Parking parking, ParkingView parkingView) {
        this.parking = parking;
        this.parkingView = parkingView;
    }

    public void onSizeCreationButtonPressed() {
        String size = parkingView.getSize();
        try {
            if (Integer.parseInt(size) <= 0) {
                parkingView.showErrorMessage();
            } else {
                parking.setParkingSize(size);
                parkingView.showSizeMessage(size);
            }
        } catch (NumberFormatException ex) {
            parkingView.showInvalidError();
        }
    }
}
