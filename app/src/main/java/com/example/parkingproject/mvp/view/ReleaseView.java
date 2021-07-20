package com.example.parkingproject.mvp.view;

import androidx.fragment.app.Fragment;

import com.example.parkingproject.R;
import com.example.parkingproject.databinding.FragmentReleaseBinding;

public class ReleaseView extends FragmentView {

    private final FragmentReleaseBinding binding;

    public ReleaseView(Fragment fragmentRef, FragmentReleaseBinding binding) {
        super(fragmentRef);
        this.binding = binding;
    }

    public String getParkingLotNumber() {
        return binding.inputParkingLotNumber.getText().toString();
    }

    public String getSecurityCode() {
        return binding.inputSecurityCode.getText().toString();
    }

    public void showErrorReservationListEmptyException() {
        showToast(R.string.toast_reservation_exception_list_empty);
    }

    public void showErrorReleaseNoMatchException() {
        showToast(R.string.toast_release_exception_no_match);
    }

    public void showErrorReleaseMoreThanOneMatchException() {
        showToast(R.string.toast_release_exception_more_than_one_match);
    }

    public void showSuccessMessageReleaseReservation() {
        showToast(R.string.toast_release_success_message);
    }

    public void showErrorSecurityCodeNull() {
        showToast(R.string.toast_reservation_error_null_number_security_code);
    }

    public void showErrorSecurityCodeOutOfNumberRange() {
        showToast(R.string.toast_reservation_error_range_number_security_code);
    }

    public void showErrorParkingLotLessOrEqualsTo0() {
        showToast(R.string.toast_reservation_error_parking_lot_less_or_equals_0);
    }

    public void showErrorParkingNumberOutOfRange() {
        showToast(R.string.toast_reservation_error_range_number_parking_lot);
    }
}
