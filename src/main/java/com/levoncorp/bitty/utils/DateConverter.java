package com.levoncorp.bitty.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateConverter {
    public static String timeInMillisToIso(long timeInMillis) {
        return dateToIso(new Date(timeInMillis));
    }

    public static String dateToIso(Date time) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        df.setTimeZone(tz);
        String iso = df.format(time);
        return iso;
    }
}
