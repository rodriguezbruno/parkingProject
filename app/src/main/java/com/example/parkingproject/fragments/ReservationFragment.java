package com.example.parkingproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.parkingproject.databinding.FragmentReservationBinding;
import com.example.parkingproject.entities.Parking;
import com.example.parkingproject.mvp.model.ReservationModel;
import com.example.parkingproject.mvp.presenter.ReservationPresenter;
import com.example.parkingproject.mvp.view.ReservationView;

public class ReservationFragment extends Fragment {

    public interface ReservationFragmentDelegate {
        void onReservationFragmentButtonClicked(Parking parking);
    }

    public static final String PARKING_ARGUMENT = "PARKING";
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
            Parking parking = getArguments().getParcelable(PARKING_ARGUMENT);
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
