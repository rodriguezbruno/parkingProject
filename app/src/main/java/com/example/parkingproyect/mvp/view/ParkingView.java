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

    public void showErrorLargeNumber() {
        if (context != null) {
            Toast.makeText(context, R.string.toast_parking_error_large_number + getSize(), Toast.LENGTH_LONG).show();
        }
    }

    public void showErrorLessEqual0() {
        if (context != null) {
            Toast.makeText(context, R.string.toast_parking_error_less_equal_0, Toast.LENGTH_LONG).show();
        }
    }
}
