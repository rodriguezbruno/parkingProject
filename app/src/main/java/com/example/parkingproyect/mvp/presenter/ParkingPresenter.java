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
            parking.setParkingSize(size);
            parkingView.showSizeMessage(size);
        } catch (NumberFormatException ex) {
            parkingView.showInvalidError();
        } catch (IllegalArgumentException ex) {
            parkingView.showErrorMessage();
        }
    }
}
