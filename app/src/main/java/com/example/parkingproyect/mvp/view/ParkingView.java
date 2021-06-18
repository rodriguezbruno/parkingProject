package com.example.parkingproyect.mvp.view;

import android.app.Activity;
import android.widget.Toast;

import com.example.parkingproyect.R;
import com.example.parkingproyect.databinding.ActivityMainBinding;

public class ParkingView extends ActivityView {

    private final ActivityMainBinding binding;

    public ParkingView(Activity activity, ActivityMainBinding binding){
       super(activity);
       this.binding = binding;
    }

    public String getSize(){
        String size = binding.inputParkingQuantitySlots.getText().toString();
        return size;
    }

    public void showSizeMessage(String size){
        binding.textParkingMessage.setText(getContext().getString(R.string.text_parking_add_size_message, size));
    }

    public void showInvalidError() {
        Toast.makeText(getContext(),"Error: " + getSize(),Toast.LENGTH_LONG).show();
    }
}
