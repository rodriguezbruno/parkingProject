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
          //Declare the string variable to make it more clear
          String size = parkingView.getSize();
          try {
              parking.setParkingSize(size);
              parkingView.showSizeMessage(String.valueOf(size));
          }
          catch(NumberFormatException ex){
              parkingView.showInvalidError();
          }

      }

}
