package com.example.parkingproyect.mvp.view;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.parkingproyect.databinding.ActivityMainBinding;

public class ParkingView extends ActivityView {

    private final ActivityMainBinding binding;

    public ParkingView(Activity activity, ActivityMainBinding binding){
       super(activity);
       this.binding = binding;
    }

    public String getSize(){
        String size = "";
        size = binding.quantityParkingSlots.getText().toString();
        return size;
    }

    public void showSizeMessage(String size) {
        binding.sizeLabel.setText("Parking lots added: " + size);
    }

    public void showInvalidError() {
        Toast.makeText(getContext(),"Error: " + getSize(),Toast.LENGTH_LONG).show();
    }
}
