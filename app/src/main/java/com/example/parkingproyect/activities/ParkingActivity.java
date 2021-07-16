package com.example.parkingproyect.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.parkingproyect.databinding.ActivityParkingBinding;
import com.example.parkingproyect.mvp.model.ParkingModel;
import com.example.parkingproyect.mvp.presenter.ParkingPresenter;
import com.example.parkingproyect.mvp.view.ParkingView;

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

