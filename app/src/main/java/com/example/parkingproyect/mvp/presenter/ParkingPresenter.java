package com.example.parkingproyect.mvp.presenter;

import com.example.parkingproyect.mvp.model.ParkingModel;
import com.example.parkingproyect.mvp.view.ParkingView;

public class ParkingPresenter {

    private final ParkingModel parkingModel;
    private final ParkingView parkingView;

    public ParkingPresenter(ParkingModel parkingModel, ParkingView parkingView) {
        this.parkingModel = parkingModel;
        this.parkingView = parkingView;
    }

    public void onSizeCreationButtonPressed() {
        String size = parkingView.getSize();
        try {
            parkingModel.setParkingSize(size);
            int sizeInt = parkingModel.getParkingSize();
            parkingView.navigateToMainMenuActivity(sizeInt);
        } catch (NumberFormatException ex) {
            parkingView.showErrorLargeNumber();
        } catch (IllegalArgumentException ex) {
            parkingView.showErrorLessEqual0();
        }
    }
}
