package com.example.parkingproject.mvp.model;

import android.util.Log;

import com.example.parkingproject.entities.Parking;
import com.example.parkingproject.entities.Reservation;
import com.example.parkingproject.exceptions.ReleaseMoreThanOneMatchException;
import com.example.parkingproject.exceptions.ReleaseNoMatchException;
import com.example.parkingproject.exceptions.ReservationListEmptyException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class ReleaseModelTest {

    private ReleaseModel model;
    private MockedStatic<Log> logMockedStatic;

    @Before
    public void setUp() {
        model = new ReleaseModel(new Parking(20));
        logMockedStatic = mockStatic(Log.class);
    }

    //region getters tests
    @Test
    public void getParking_success() {
        assertEquals(20, model.getParking().getParkingSize());
    }

    @Test
    public void getParkingNumber_parkingNumberToInt_success() {
        Assert.assertEquals(20, model.getParkingNumber("20"));
    }

    @Test
    public void getParkingNumber_exception_not_converted() {
        assertEquals(0, model.getParkingNumber("XX"));
    }

    //endregion
    //region parkingRelease tests
    @Test
    public void parkingRelease_success() throws Exception{
        Reservation reservation = new Reservation("A123", new Date().getTime(), new Date().getTime(), 1);
        model.getParking().getReservationsList().add(reservation);
        model.parkingRelease(reservation);
    }

    @Test
    public void parkingRelease_throw_reservationListEmptyException() {
        assertThrows(ReservationListEmptyException.class, () -> model.parkingRelease(new Reservation()));
    }

    @Test
    public void parkingRelease_throw_releaseMoreThanOneMatchException() {
        Reservation reservation = new Reservation("A123", new Date().getTime(), new Date().getTime(), 1);
        model.getParking().getReservationsList().add(reservation);
        model.getParking().getReservationsList().add(reservation);
        assertThrows(ReleaseMoreThanOneMatchException.class, () -> model.parkingRelease(reservation));
    }

    @Test
    public void parkingRelease_throw_releaseNoMatchException() {
        model.getParking().getReservationsList().add(new Reservation("A123", new Date().getTime(), new Date().getTime(), 1));
        assertThrows(ReleaseNoMatchException.class, () -> model.parkingRelease(new Reservation("B123", new Date().getTime(), new Date().getTime(), 1)));
    }

    //endregion
    //region countSameReservationInList tests
    @Test
    public void parkingRelease_countSameReservationInList_equals_0() {
        model.getParking().getReservationsList().add(new Reservation("A123", new Date().getTime(), new Date().getTime(), 1));
        assertEquals(0, model.countSameReservationInList(new Reservation("B123", new Date().getTime(), new Date().getTime(), 1)));
    }

    @Test
    public void parkingRelease_countSameReservationInList_oneOrMoreMatch() {
        model.getParking().getReservationsList().add(new Reservation("B123", new Date().getTime(), new Date().getTime(), 1));
        model.getParking().getReservationsList().add(new Reservation("B123", new Date().getTime(), new Date().getTime(), 1));
        assertEquals(2, model.countSameReservationInList(new Reservation("B123", new Date().getTime(), new Date().getTime(), 1)));
    }

    //endregion
    @After
    public void destroy() {
        logMockedStatic.close();
    }
}