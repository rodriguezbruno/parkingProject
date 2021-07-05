package com.example.parkingproyect.utils;

import java.util.Date;

public class DateTimeUtils {
    public static boolean isDateInTheFuture(Date date) {
        return date.after(new Date());
    }
}
