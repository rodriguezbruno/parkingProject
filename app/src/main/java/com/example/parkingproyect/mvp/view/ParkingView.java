package com.example.parkingproyect.mvp.view;

import android.app.Activity;
import android.widget.Toast;

import com.example.parkingproyect.R;
import com.example.parkingproyect.databinding.ActivityMainBinding;

public class ParkingView extends ActivityView {

    private final ActivityMainBinding binding;

    public ParkingView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    public String getSize() {
        return binding.inputParkingQuantitySlots.getText().toString();
    }

    public void showSizeMessage(String size) {
        if (getContext() != null)
            binding.textParkingMessage.setText(getContext().getString(R.string.text_parking_add_size_message, size));
    }

    public void showInvalidError() {
        if (getContext() != null)
            Toast.makeText(getContext(), "Error: " + getSize(), Toast.LENGTH_LONG).show();
    }
}
