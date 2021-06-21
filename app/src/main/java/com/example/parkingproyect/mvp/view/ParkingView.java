package com.example.parkingproyect.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.parkingproyect.R;
import com.example.parkingproyect.databinding.ActivityMainBinding;

public class ParkingView extends ActivityView {

    private final ActivityMainBinding binding;
    private Context context;

    public ParkingView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
        this.context = getContext();
    }

    public String getSize() {
        String size = binding.inputParkingQuantitySlots.getText().toString();
        return size;
    }


    public void showSizeMessage(String size) {
        if (context != null) {
            binding.textParkingMessage.setText(context.getString(R.string.text_parking_add_size_message, size));
        }
    }

    public void showInvalidError() {
        if (context != null) {
            Toast.makeText(context, R.string.toast_parking_error_message1 + getSize(), Toast.LENGTH_LONG).show();
        }
    }

    public void showErrorMessage() {
        if (context != null) {
            Toast.makeText(context, R.string.toast_parking_error_message2, Toast.LENGTH_LONG).show();
        }
    }
}
