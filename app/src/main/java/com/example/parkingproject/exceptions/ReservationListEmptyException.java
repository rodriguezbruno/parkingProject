package com.example.parkingproject.exceptions;

public class ReservationListEmptyException extends Exception {
    public ReservationListEmptyException() {
        super("Reservation list is empty");
    }
}
