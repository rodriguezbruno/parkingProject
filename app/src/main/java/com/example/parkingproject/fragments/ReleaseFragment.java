package com.example.parkingproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.parkingproject.databinding.FragmentReleaseBinding;
import com.example.parkingproject.entities.Parking;
import com.example.parkingproject.mvp.model.ReleaseModel;
import com.example.parkingproject.mvp.presenter.ReleasePresenter;
import com.example.parkingproject.mvp.view.ReleaseView;

public class ReleaseFragment extends Fragment {

    public interface ReleaseFragmentDelegate {
        void onReleaseFragmentButtonClicked(Parking parking);
    }

    public static final String PARKING_ARGUMENT = "PARKING";
    private ReleasePresenter presenter;
    private FragmentReleaseBinding binding;
    private ReleaseFragmentDelegate delegate;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ReleaseFragmentDelegate) {
            delegate = (ReleaseFragmentDelegate) context;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReleaseBinding.inflate(inflater, container, false);
        if (getArguments() != null) {
            Parking parking = getArguments().getParcelable(PARKING_ARGUMENT);
            presenter = new ReleasePresenter(new ReleaseModel(parking), new ReleaseView(this, binding));
            setListeners();
        }
        return binding.getRoot();
    }

    private void setListeners() {
        binding.buttonParkingRelease.setOnClickListener(view ->
        {
            if (presenter.onReleaseParkingPressed() && delegate != null) {
                delegate.onReleaseFragmentButtonClicked(presenter.getParkingWithReservations());
            }
        });
    }
}
