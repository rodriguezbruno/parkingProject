package com.example.parkingproyect.mvp.presenter;

import com.example.parkingproyect.mvp.model.Parking;
import com.example.parkingproyect.mvp.view.ParkingView;

public class ParkingPresenter {

      private Parking parking;
      private ParkingView parkingView;

      public ParkingPresenter(Parking parking, ParkingView parkingView){
             this.parking = parking;
             this.parkingView = parkingView;
      }

      public void onSizeCreationButtonPressed(){
          //Declaro la variable int para dejarlo mas claro
          int size = parkingView.getSize();
          parking.setParkingSize(size);
          parkingView.setSize(String.valueOf(parking.getParkingSize()));
      }

}
