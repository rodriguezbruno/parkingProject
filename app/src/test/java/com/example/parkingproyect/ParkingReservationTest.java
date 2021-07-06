package com.example.parkingproyect;

import com.example.parkingproyect.entities.Parking;
import com.example.parkingproyect.entities.Reservation;
import com.example.parkingproyect.mvp.model.ParkingModel;
import com.example.parkingproyect.mvp.model.ReservationModel;
import com.example.parkingproyect.mvp.presenter.ParkingPresenter;
import com.example.parkingproyect.mvp.presenter.ReservationPresenter;
import com.example.parkingproyect.mvp.view.ParkingView;
import com.example.parkingproyect.mvp.view.ReservationView;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void onSchedule_addReservation_success() {
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

        when(model.getParking()).thenReturn(new Parking(16));

        when(spyPresenter.validateSecurityCode("A123")).thenReturn(true);
        when(spyPresenter.validateParkingLot(15)).thenReturn(true);
        when(spyPresenter.validateReservationDates(startDate.getTime(),endDate.getTime())).thenReturn(true);

        when(model.addReservation(any(Reservation.class))).thenReturn(true);

        spyPresenter.onAddNewReservationPressed();

        verify(view).showSuccessMessageReservationAdded();
    }

   /*
    @Test
    public void onSchedule_addReservation_success() {
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
        when(model.getParking()).thenReturn((new Parking(20)));

        // When
        presenter.onSchedule();
        // Then
        verify(model).addReservation(new Reservation());
        verify(view).showSuccessMessageReservationAdded();
    }
    */




}