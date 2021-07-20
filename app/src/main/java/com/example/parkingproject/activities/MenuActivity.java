package com.example.parkingproject.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.parkingproject.R;
import com.example.parkingproject.databinding.ActivityMenuBinding;
import com.example.parkingproject.entities.Parking;
import com.example.parkingproject.fragments.ReleaseFragment;
import com.example.parkingproject.fragments.ReservationFragment;
import com.example.parkingproject.fragments.ReservationFragment.ReservationFragmentDelegate;
import com.example.parkingproject.mvp.presenter.MenuPresenter;

import static com.example.parkingproject.fragments.ReservationFragment.PARKING_ARGUMENT;

public class MenuActivity extends AppCompatActivity implements ReservationFragmentDelegate, ReleaseFragment.ReleaseFragmentDelegate {

    private static final String RESERVATION_FRAGMENT_TAG = "RESERVATION_FRAGMENT";
    private static final String RELEASE_FRAGMENT_TAG = "RELEASE_FRAGMENT";
    public static final String PARKING_SIZE_EXTRA = "SIZE_KEY";

    private ActivityMenuBinding binding;
    private MenuPresenter menuPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int parkingSize = getIntent().getIntExtra(PARKING_SIZE_EXTRA, 0);
        if (parkingSize == 0) {
            finish();
        }
        menuPresenter = new MenuPresenter(parkingSize);
        setListeners();
    }

    private void setListeners() {
        binding.buttonParkingAddReservation.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(PARKING_ARGUMENT, menuPresenter.getParking());
            ReservationFragment reservationFragment = new ReservationFragment();
            reservationFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.menu_fragment_container, reservationFragment, RESERVATION_FRAGMENT_TAG);
            transaction.commit();
        });

        binding.buttonParkingRelease.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(PARKING_ARGUMENT, menuPresenter.getParking());
            ReleaseFragment releaseFragment = new ReleaseFragment();
            releaseFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.menu_fragment_container, releaseFragment, RELEASE_FRAGMENT_TAG);
            transaction.commit();
        });
    }

    @Override
    public void onReservationFragmentButtonClicked(Parking parking) {
        menuPresenter.setParking(parking);
    }

    @Override
    public void onReleaseFragmentButtonClicked(Parking parking) {
        menuPresenter.setParking(parking);
    }
}
