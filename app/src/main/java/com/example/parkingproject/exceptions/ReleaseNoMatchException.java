package com.example.parkingproject.exceptions;

public class ReleaseNoMatchException extends Exception {
    public ReleaseNoMatchException() {
        super("No matches for this reservation");
    }
}
