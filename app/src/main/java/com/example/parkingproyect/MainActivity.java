package com.example.parkingproyect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.parkingproyect.databinding.ActivityMainBinding;
import com.example.parkingproyect.mvp.model.Parking;
import com.example.parkingproyect.mvp.presenter.ParkingPresenter;
import com.example.parkingproyect.mvp.view.ParkingView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ParkingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ParkingPresenter(new Parking(), new ParkingView(this, binding));

        setListeners();
    }

    private void setListeners(){
        binding.addsizeButton.setOnClickListener(view -> {
            presenter.onSizeCreationButtonPressed();
        });
    }
}

