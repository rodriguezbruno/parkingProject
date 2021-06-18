package com.example.parkingproyect.mvp.view;

import android.app.Activity;
import android.widget.Toast;

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
        //Sorry for the big comment :(
        //I dont now why but R.string.text_parking_add_size_message throw me a number
        //string.xml have this <string name="text_parking_add_size_message">Parking lots added: </string>
        //String text = R.string.text_parking_add_size_message + " " + size;
        //binding.textParkingMessage.setText(text);
        binding.textParkingMessage.setText("Parking lots added: " + size);
    }

    public void showInvalidError() {
        Toast.makeText(getContext(),"Error: " + getSize(),Toast.LENGTH_LONG).show();
    }
}
