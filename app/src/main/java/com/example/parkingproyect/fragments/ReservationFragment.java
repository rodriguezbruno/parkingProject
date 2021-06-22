package com.example.parkingproyect.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.parkingproyect.databinding.FragmentReservationBinding;
import com.example.parkingproyect.entities.Parking;
import com.example.parkingproyect.mvp.model.ReservationModel;
import com.example.parkingproyect.mvp.presenter.ReservationPresenter;
import com.example.parkingproyect.mvp.view.ReservationView;

public class ReservationFragment extends Fragment {

    public interface ReservationFragmentDelegate {
        void onReservationFragmentButtonClicked(Parking parking);
    }

    public static final String PARKING_KEY = "PARKING";
    private ReservationPresenter presenter;
    private FragmentReservationBinding binding;
    private ReservationFragmentDelegate delegate;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ReservationFragmentDelegate) {
            delegate = (ReservationFragmentDelegate) context;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReservationBinding.inflate(inflater, container, false);
        binding.startDateTime.setInputType(InputType.TYPE_NULL);
        binding.endDateTime.setInputType(InputType.TYPE_NULL);
        if (getArguments() != null) {
            Parking parking = getArguments().getParcelable(PARKING_KEY);
            presenter = new ReservationPresenter(new ReservationModel(parking), new ReservationView(this, binding));
        }
        setListeners();
        return binding.getRoot();
    }

    private void setListeners() {
        binding.startDateTime.setOnClickListener(view -> presenter.onStartDateTimePressed());
        binding.endDateTime.setOnClickListener(view -> presenter.onEndDateTimePressed());
        binding.buttonParkingAddNewReservation.setOnClickListener(view ->
        {
            if (presenter.onAddNewReservationPressed() && delegate != null) {
                delegate.onReservationFragmentButtonClicked(presenter.getParkingWithReservations());
            }
        });
    }
}
