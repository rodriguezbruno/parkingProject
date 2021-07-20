package com.example.parkingproject.exceptions;

public class ReleaseMoreThanOneMatchException extends Exception {

    public ReleaseMoreThanOneMatchException() {
        super("More than one match");
    }
}
