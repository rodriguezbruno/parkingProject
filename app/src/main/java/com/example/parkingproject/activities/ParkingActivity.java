package com.example.parkingproject.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingproject.databinding.ActivityParkingBinding;
import com.example.parkingproject.mvp.model.ParkingModel;
import com.example.parkingproject.mvp.presenter.ParkingPresenter;
import com.example.parkingproject.mvp.view.ParkingView;

public class ParkingActivity extends AppCompatActivity {

    private ActivityParkingBinding binding;
    private ParkingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParkingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ParkingPresenter(new ParkingModel(), new ParkingView(this, binding));
        setListeners();
    }

    private void setListeners() {
        binding.buttonParkingAddSize.setOnClickListener(view -> {
            presenter.onSizeCreationButtonPressed();
        });
    }
}

