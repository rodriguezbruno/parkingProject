package com.example.parkingproyect.mvp.model;

import com.example.parkingproyect.entities.Parking;
import com.example.parkingproyect.entities.Reservation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RunWith(RobolectricTestRunner.class)
public class ReservationModelTest {

    private ReservationModel model;

    @Before
    public void setUp() {
        model = new ReservationModel(new Parking(20));
    }

    //region validations getters
    @Test
    public void getParking_success() {
        Assert.assertEquals(20, model.getParking().getParkingSize());
    }

    @Test
    public void getReservationList_fromParking_success() {
        Assert.assertEquals(new ArrayList<>(), model.getParking().getReservationsList());
    }

    @Test
    public void getParkingNumber_convert_parkingNumberInt() {
        Assert.assertEquals(20, model.getParkingNumber("20"));
    }

    @Test
    public void getParkingNumber_convert_notConvert() {
        Assert.assertEquals(0, model.getParkingNumber("XX"));
    }
    //endregion

    //region validations for addReservation
    @Test
    public void addReservation_reservationListEquals0_true() {
        Assert.assertTrue(model.addReservation(new Reservation("A123", new Date().getTime(), new Date().getTime(), 25)));
    }

    @Test
    public void addReservation_sameReservation_false() {
        Assert.assertTrue(model.addReservation(new Reservation("A123", new Date().getTime(), new Date().getTime(), 25)));
        Assert.assertFalse(model.addReservation(new Reservation("A123", new Date().getTime(), new Date().getTime(), 25)));
    }
    //endregion

    //region validations for validateAddReservation
    @Test
    public void validateAddReservation_success() {
        Assert.assertTrue(model.validateAddReservation(new Reservation()));
    }

    @Test
    public void validateAddReservation_differentReservation_true() {
        Assert.assertTrue(model.addReservation(new Reservation("A123", new Date().getTime(), new Date().getTime(), 1)));
        Assert.assertTrue(model.validateAddReservation(new Reservation("A213", new Date().getTime(), new Date().getTime(), 2)));
    }

    //If reservation parking lot equals reservation in list parking lot,
    //start date of reservation must be bigger than end date in reservation list
    @Test
    public void validateAddReservation_sameParkingLotStartDateBiggerThanEndDate_true() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDateReservation = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -2);
        Date endDateReservationInList = calendar.getTime();

        Assert.assertTrue(model.addReservation(new Reservation("A123", new Date().getTime(), endDateReservationInList.getTime(), 1)));
        Assert.assertTrue(model.validateAddReservation(new Reservation("A213", startDateReservation.getTime(), new Date().getTime(), 1)));
    }

    //If reservation parking lot equals reservation in list parking lot,
    //start date of reservation must be bigger than end date in reservation list
    @Test
    public void validateAddReservation_sameParkingLotStartDateBiggerThanEndDate_false() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date startDateReservation = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date endDateReservationInList = calendar.getTime();

        Assert.assertTrue(model.addReservation(new Reservation("A123", new Date().getTime(), endDateReservationInList.getTime(), 1)));
        Assert.assertFalse(model.validateAddReservation(new Reservation("A213", startDateReservation.getTime(), new Date().getTime(), 1)));
    }
    //endregion
}