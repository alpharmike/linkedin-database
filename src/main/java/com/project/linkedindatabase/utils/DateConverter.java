package com.project.linkedindatabase.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateConverter {
    public static String convertDate(Calendar calendar, String format) {
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Calendar parse(String formattedDate, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        Date date = sdf.parse(formattedDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
