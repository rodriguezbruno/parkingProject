package com.example.parkingproject.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.parkingproject.R;
import com.example.parkingproject.activities.MenuActivity;
import com.example.parkingproject.databinding.ActivityParkingBinding;

import static com.example.parkingproject.activities.MenuActivity.PARKING_SIZE_EXTRA;

public class ParkingView extends ActivityView {

    private final ActivityParkingBinding binding;

    public ParkingView(Activity activity, ActivityParkingBinding binding) {
        super(activity);
        this.binding = binding;
    }

    public String getSize() {
        return binding.inputParkingQuantitySlots.getText().toString();
    }

    public void navigateToMainMenuActivity(int parkingSize) {
        if (getContext() != null) {
            Intent intent = new Intent(getContext(), MenuActivity.class);
            intent.putExtra(PARKING_SIZE_EXTRA, parkingSize);
            getContext().startActivity(intent);
        }
    }

    public void showErrorLargeNumber() {
        if (getContext() != null) {
            Toast.makeText(getContext(), R.string.toast_parking_error_large_number + getSize(), Toast.LENGTH_LONG).show();
        }
    }

    public void showErrorLessEqual0() {
        if (getContext() != null) {
            Toast.makeText(getContext(), R.string.toast_parking_error_less_equal_0, Toast.LENGTH_LONG).show();
        }
    }
}
