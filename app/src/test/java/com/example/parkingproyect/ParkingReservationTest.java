package com.example.parkingproyect;

import com.example.parkingproyect.entities.Parking;
import com.example.parkingproyect.entities.Reservation;
import com.example.parkingproyect.mvp.model.ReservationModel;
import com.example.parkingproyect.mvp.presenter.ReservationPresenter;
import com.example.parkingproyect.mvp.view.ReservationView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)

public class ParkingReservationTest {
    private ReservationPresenter presenter;
    private ReservationModel model;
    private ReservationView view;

    @Before
    public void setup() {
        model = mock(ReservationModel.class);
        view = mock(ReservationView.class);
        presenter = new ReservationPresenter(model, view);
    }

    @Test
    public void validate_show_start_date_time() {
        presenter.onStartDateTimePressed();
        verify(view).showStartDateTimeInput();
    }

    @Test
    public void validate_show_end_date_time() {
        presenter.onEndDateTimePressed();
        verify(view).showEndDateTimeInput();
    }

    //region addReservation validators test
    @Test
    public void validate_addReservation_success() {
        ReservationPresenter spyPresenter = spy(presenter);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endDate = calendar.getTime();

        when(view.getStartDate()).thenReturn(startDate);
        when(view.getEndDate()).thenReturn(endDate);
        when(view.getParkingLotNumber()).thenReturn("15");
        when(view.getSecurityCode()).thenReturn("A123");
        when(model.getParkingNumber("15")).thenReturn(15);

        when(model.getParking()).thenReturn(new Parking(16));

        doReturn(true).when(spyPresenter).validateSecurityCode("A123");
        doReturn(true).when(spyPresenter).validateParkingLot(15);
        doReturn(true).when(spyPresenter).validateReservationDates(startDate.getTime(), endDate.getTime());

        when(model.addReservation(any(Reservation.class))).thenReturn(true);

        spyPresenter.onAddNewReservationPressed();

        verify(view).showSuccessMessageReservationAdded();
    }

    @Test
    public void validate_addReservation_fail() {
        ReservationPresenter spyPresenter = spy(presenter);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endDate = calendar.getTime();

        // Given
        when(view.getStartDate()).thenReturn(startDate);
        when(view.getEndDate()).thenReturn(endDate);
        when(view.getParkingLotNumber()).thenReturn("15");
        when(view.getSecurityCode()).thenReturn("A123");
        when(model.getParkingNumber("15")).thenReturn(15);

        when(model.getParking()).thenReturn(new Parking(16));

        doReturn(true).when(spyPresenter).validateSecurityCode("A123");
        doReturn(true).when(spyPresenter).validateParkingLot(15);
        doReturn(true).when(spyPresenter).validateReservationDates(startDate.getTime(), endDate.getTime());

        when(model.addReservation(any(Reservation.class))).thenReturn(false);

        spyPresenter.onAddNewReservationPressed();

        verify(view).showErrorParkingNumberWithSameDates();
    }
    //endregion

    //region securityCode validators test
    @Test
    public void validate_securityCode_success() {
        Assert.assertTrue(presenter.validateSecurityCode("A123"));
    }

    @Test
    public void validate_securityCode_null() {
        Assert.assertFalse(presenter.validateSecurityCode(null));
        verify(view).showErrorSecurityCodeNull();
    }

    @Test
    public void validate_securityCode_outOfRange_toLong() {
        Assert.assertFalse(presenter.validateSecurityCode("A123456"));
        verify(view).showErrorSecurityCodeOutOfNumberRange();
    }

    @Test
    public void validate_securityCode_outOfRange_toShort() {
        Assert.assertFalse(presenter.validateSecurityCode("A1"));
        verify(view).showErrorSecurityCodeOutOfNumberRange();
    }
    //endregion

    //region parkingNumber validators test
    @Test
    public void validate_parkingNumber_success() {
        when(model.getParking()).thenReturn(new Parking(20));
        Assert.assertTrue(presenter.validateParkingLot(15));
    }

    @Test
    public void validate_parkingNumber_lessOrEquals0() {
        Assert.assertFalse(presenter.validateParkingLot(-1));
        verify(view).showErrorParkingLotLessOrEqualsTo0();
    }

    @Test
    public void validate_parkingNumber_less_parkingSize() {
        when(model.getParking()).thenReturn(new Parking(14));
        Assert.assertFalse(presenter.validateParkingLot(15));
        verify(view).showErrorParkingNumberOutOfRange();
    }
    //endregion

    //region reservationDates validators test
    @Test
    public void validate_parkingDates_success() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endDate = calendar.getTime();

        Assert.assertTrue(presenter.validateReservationDates(startDate.getTime(), endDate.getTime()));
    }

    @Test
    public void validate_parkingDates_startDate_and_endDate_empty() {
        Assert.assertFalse(presenter.validateReservationDates(0, 0));
        verify(view).showErrorReservationDatesEmpty();
    }

    @Test
    public void validate_parkingDates_startDate_empty() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endDate = calendar.getTime();

        Assert.assertFalse(presenter.validateReservationDates(0, endDate.getTime()));
        verify(view).showErrorReservationDatesEmpty();
    }


    @Test
    public void validate_parkingDates_endDate_empty() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();

        Assert.assertFalse(presenter.validateReservationDates(startDate.getTime(), 0));
        verify(view).showErrorReservationDatesEmpty();
    }


    @Test
    public void validate_parkingDates_startDate_less_today() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -2);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 5);
        Date endDate = calendar.getTime();

        Assert.assertFalse(presenter.validateReservationDates(startDate.getTime(), endDate.getTime()));
        verify(view).showErrorDatesOutOfRange();
    }

    @Test
    public void validate_parkingDates_endDate_less_startDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -2);
        Date endDate = calendar.getTime();

        Assert.assertFalse(presenter.validateReservationDates(startDate.getTime(), endDate.getTime()));
        verify(view).showErrorDatesOutOfRange();
    }
    //endregion

    @Test
    public void validate_get_parking_with_list_of_reservations() {
        when(model.getParking()).thenReturn(new Parking(20));
        Assert.assertEquals(new Parking(20).getParkingSize(), presenter.getParkingWithReservations().getParkingSize());
    }
}