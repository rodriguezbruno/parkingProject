package com.example.parkingproyect.mvp.presenter;

import android.util.Log;
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
            parkingView.showErrorLargeNumber();
        } catch (IllegalArgumentException ex) {
            parkingView.showErrorLessEqual0();
        }
    }
}
