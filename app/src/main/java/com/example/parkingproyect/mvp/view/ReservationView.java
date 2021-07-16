package com.example.parkingproyect.mvp.view;

import androidx.fragment.app.Fragment;

import com.example.parkingproyect.R;
import com.example.parkingproyect.databinding.FragmentReservationBinding;
import com.example.parkingproyect.utils.Picker;

import java.util.Date;
import java.util.Objects;

public class ReservationView extends FragmentView {

    private final FragmentReservationBinding binding;
    private final Picker startDatePicker;
    private final Picker endDatePicker;

    public ReservationView(Fragment fragmentRef, FragmentReservationBinding binding) {
        super(fragmentRef);
        this.binding = binding;
        startDatePicker = new Picker(binding.startDateTime);
        endDatePicker = new Picker(binding.endDateTime);
    }

    public void showStartDateTimeInput() {
        startDatePicker.show();
    }

    public void showEndDateTimeInput() {
        endDatePicker.show();
    }

    public Date getStartDate() {
        return startDatePicker.getDate();
    }

    public Date getEndDate() {
        return endDatePicker.getDate();
    }

    public String getParkingLotNumber() {
        return binding.inputParkingLotNumber.getText().toString();
    }

    public String getSecurityCode() {
        return binding.inputSecurityCode.getText().toString();
    }

    public void showErrorSecurityCodeNull() {
        showToast(Objects.requireNonNull(getContext()).getString(R.string.toast_reservation_error_null_number_security_code));
    }

    public void showErrorSecurityCodeOutOfNumberRange() {
        showToast(Objects.requireNonNull(getContext()).getString(R.string.toast_reservation_error_range_number_security_code));
    }

    public void showErrorParkingNumberOutOfRange() {
        showToast(Objects.requireNonNull(getContext()).getString(R.string.toast_reservation_error_range_number_parking_lot));
    }

    public void showErrorDatesOutOfRange() {
        showToast(Objects.requireNonNull(getContext()).getString(R.string.toast_reservation_error_range_dates));
    }

    public void showErrorParkingNumberWithSameDates() {
        showToast(Objects.requireNonNull(getContext()).getString(R.string.toast_reservation_added_error_message));
    }

    public void showSuccessMessageReservationAdded() {
        showToast(Objects.requireNonNull(getContext()).getString(R.string.toast_reservation_added_message));
    }

    public void showErrorParkingLotLessOrEqualsTo0() {
        showToast(Objects.requireNonNull(getContext()).getString(R.string.toast_reservation_error_parking_lot_less_or_equals_0));
    }

    public void showErrorReservationDatesEmpty() {
        showToast(Objects.requireNonNull(getContext()).getString(R.string.toast_reservation_error_dates_empty));
    }
}
