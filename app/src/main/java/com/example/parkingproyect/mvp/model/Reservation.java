package com.example.parkingproyect.mvp.model;

import java.util.Date;

public class Reservation {

    public int idReservation;
    public ParkingLot parkingLot;
    public Date startDateTime;
    public Date endDateTime;
    public String securityCode;

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getFinishDateTime() {
        return endDateTime;
    }

    public void setFinishDateTime(Date finishDateTime) {
        this.endDateTime = finishDateTime;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
