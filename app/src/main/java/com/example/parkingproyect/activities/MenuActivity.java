package com.example.parkingproyect.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.parkingproyect.R;
import com.example.parkingproyect.databinding.ActivityMenuBinding;
import com.example.parkingproyect.entities.Parking;
import com.example.parkingproyect.fragments.ReservationFragment.ReservationFragmentDelegate;
import com.example.parkingproyect.fragments.ReservationFragment;
import com.example.parkingproyect.mvp.presenter.MenuPresenter;

import static com.example.parkingproyect.fragments.ReservationFragment.PARKING_KEY;

public class MenuActivity extends AppCompatActivity implements ReservationFragmentDelegate {

    private static final String RESERVATION_FRAGMENT_TAG = "RESERVATION_FRAGMENT";
    public static final String PARKING_SIZE_KEY = "SIZE_KEY";

    private ActivityMenuBinding binding;
    private MenuPresenter menuPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int parkingSize = getIntent().getIntExtra(PARKING_SIZE_KEY, 0);
        menuPresenter = new MenuPresenter(parkingSize);
        setListeners();
    }

    private void setListeners() {
        binding.buttonParkingAddReservation.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(PARKING_KEY, menuPresenter.getParking());
            ReservationFragment reservationFragment = new ReservationFragment();
            reservationFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.reservation_fragment, reservationFragment, RESERVATION_FRAGMENT_TAG);
            transaction.commit();
        });
    }

    @Override
    public void onReservationFragmentButtonClicked(Parking parking) {
        menuPresenter.setParking(parking);
    }
}
