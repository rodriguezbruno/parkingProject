package com.example.parkingproyect.mvp.view;

import android.app.Activity;
import android.util.Log;

import com.example.parkingproyect.databinding.ActivityMainBinding;

public class ParkingView extends ActivityView {

    private final ActivityMainBinding binding;

    public ParkingView(Activity activity, ActivityMainBinding binding){
       super(activity);
       this.binding = binding;
    }

    public int getSize(){
        int size = 0;
        try{
            size = Integer.parseInt(binding.quantityParkingSlots.getText().toString());
        }
        catch(NumberFormatException ex){
            Log.e(ParkingView.class.getSimpleName(), ex.toString());
        }
        return size;
    }

    public void setSize(String size) {
        binding.sizeLabel.setText("Parking lots added: " + size);
    }
}
