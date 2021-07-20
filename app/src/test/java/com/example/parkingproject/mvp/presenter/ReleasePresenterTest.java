package com.example.parkingproject.mvp.presenter;

import com.example.parkingproject.entities.Parking;
import com.example.parkingproject.entities.Reservation;
import com.example.parkingproject.exceptions.ReleaseMoreThanOneMatchException;
import com.example.parkingproject.exceptions.ReleaseNoMatchException;
import com.example.parkingproject.exceptions.ReservationListEmptyException;
import com.example.parkingproject.mvp.model.ReleaseModel;
import com.example.parkingproject.mvp.view.ReleaseView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReleasePresenterTest {
    private ReleasePresenter presenter;
    private ReleaseModel model;
    private ReleaseView view;

    @Before
    public void setup() {
        model = mock(ReleaseModel.class);
        view = mock(ReleaseView.class);
        presenter = new ReleasePresenter(model, view);
    }

    //region onReleaseParkingPressed tests
    @Test
    public void onReleaseParkingPressed_success() throws Exception {
        ReleasePresenter spyPresenter = spy(presenter);

        when(view.getParkingLotNumber()).thenReturn("15");
        when(view.getSecurityCode()).thenReturn("A123");
        when(model.getParkingNumber("15")).thenReturn(15);

        Parking parking = new Parking(16);
        Reservation reservation = new Reservation("A123", new Date().getTime(), new Date().getTime(), 15);
        parking.getReservationsList().add(reservation);

        when(model.getParking()).thenReturn(parking);

        doReturn(true).when(spyPresenter).validateSecurityCode("A123");
        doReturn(true).when(spyPresenter).validateParkingLot(15);

        spyPresenter.onReleaseParkingPressed();

        verify(model).parkingRelease(reservation);
        verify(view).showSuccessMessageReleaseReservation();
    }

    @Test
    public void onReleaseParkingPressed_reservationListEmptyException_showError() throws Exception {
        ReleasePresenter spyPresenter = spy(presenter);

        when(view.getParkingLotNumber()).thenReturn("15");
        when(view.getSecurityCode()).thenReturn("A123");
        when(model.getParkingNumber("15")).thenReturn(15);

        Parking parking = new Parking(16);
        Reservation reservation = new Reservation("A123", new Date().getTime(), new Date().getTime(), 15);

        when(model.getParking()).thenReturn(parking);

        doThrow(new ReservationListEmptyException()).when(model).parkingRelease(reservation);

        spyPresenter.onReleaseParkingPressed();

        verify(model).parkingRelease(reservation);
        verify(view).showErrorReservationListEmptyException();
    }

    @Test
    public void onReleaseParkingPressed_releaseMoreThanOneMatchException_showError() throws Exception {
        ReleasePresenter spyPresenter = spy(presenter);

        when(view.getParkingLotNumber()).thenReturn("15");
        when(view.getSecurityCode()).thenReturn("A123");
        when(model.getParkingNumber("15")).thenReturn(15);

        Parking parking = new Parking(16);
        Reservation reservation = new Reservation("A123", new Date().getTime(), new Date().getTime(), 15);
        parking.getReservationsList().add(reservation);
        parking.getReservationsList().add(reservation);

        when(model.getParking()).thenReturn(parking);

        doThrow(new ReleaseMoreThanOneMatchException()).when(model).parkingRelease(reservation);

        spyPresenter.onReleaseParkingPressed();

        verify(model).parkingRelease(reservation);
        verify(view).showErrorReleaseMoreThanOneMatchException();
    }

    @Test
    public void onReleaseParkingPressed_releaseNoMatchException_showError() throws Exception {
        ReleasePresenter spyPresenter = spy(presenter);

        when(view.getParkingLotNumber()).thenReturn("15");
        when(view.getSecurityCode()).thenReturn("A123");
        when(model.getParkingNumber("15")).thenReturn(15);

        Parking parking = new Parking(16);
        Reservation reservationInList = new Reservation("B123", new Date().getTime(), new Date().getTime(), 14);
        parking.getReservationsList().add(reservationInList);

        when(model.getParking()).thenReturn(parking);

        Reservation reservationToRelease = new Reservation("A123", new Date().getTime(), new Date().getTime(), 15);
        doThrow(new ReleaseNoMatchException()).when(model).parkingRelease(reservationToRelease);

        spyPresenter.onReleaseParkingPressed();

        verify(model).parkingRelease(reservationToRelease);
        verify(view).showErrorReleaseNoMatchException();
    }

    //endregion
    //region securityCode tests
    @Test
    public void validateSecurityCode_success() {
        Assert.assertTrue(presenter.validateSecurityCode("A123"));
        verify(view, never()).showErrorSecurityCodeNull();
        verify(view, never()).showErrorSecurityCodeOutOfNumberRange();
    }

    @Test
    public void validateSecurityCode_isNull_showError() {
        Assert.assertFalse(presenter.validateSecurityCode(""));
        verify(view).showErrorSecurityCodeNull();
    }

    @Test
    public void validateSecurityCode_toLong_showError() {
        Assert.assertFalse(presenter.validateSecurityCode("A123456"));
        verify(view).showErrorSecurityCodeOutOfNumberRange();
    }

    @Test
    public void validateSecurityCode_tooShort_showError() {
        Assert.assertFalse(presenter.validateSecurityCode("A1"));
        verify(view).showErrorSecurityCodeOutOfNumberRange();
    }

    //endregion
    //region parkingNumber test
    @Test
    public void validateParkingLot_success() {
        when(model.getParking()).thenReturn(new Parking(20));
        Assert.assertTrue(presenter.validateParkingLot(15));
        verify(view, never()).showErrorParkingLotLessOrEqualsTo0();
        verify(view, never()).showErrorParkingNumberOutOfRange();
    }

    @Test
    public void validateParkingLot_lessOrEquals0_showError() {
        Assert.assertFalse(presenter.validateParkingLot(-1));
        verify(view).showErrorParkingLotLessOrEqualsTo0();
    }

    @Test
    public void validateParkingLot_lessParkingSize_showError() {
        when(model.getParking()).thenReturn(new Parking(14));
        Assert.assertFalse(presenter.validateParkingLot(15));
        verify(view).showErrorParkingNumberOutOfRange();
    }

    //endregion
    @Test
    public void validate_get_parking_with_list_of_reservations() {
        int parkingSize = 20;
        when(model.getParking()).thenReturn(new Parking(20));
        Assert.assertEquals(parkingSize, presenter.getParkingWithReservations().getParkingSize());
    }
}
