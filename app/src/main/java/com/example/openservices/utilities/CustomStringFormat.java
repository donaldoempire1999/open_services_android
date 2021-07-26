package com.example.openservices.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class CustomStringFormat {

    public static String getDateISO8601(String year, String month, String day) {
        String dateISO8601 = null;
        dateISO8601 = year;
        dateISO8601 += "-" + month;
        dateISO8601 += "-" + day;
        dateISO8601 += "'T'00:00:00:000'Z'";
        return dateISO8601;
    }

    public static Date getFormattedDateISO8601(String isoDate) {
        Date date = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            TemporalAccessor ta = null;
            ta = DateTimeFormatter.ISO_INSTANT.parse(isoDate);
            Instant i = Instant.from(ta);
            date = Date.from(i);
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'", Locale.getDefault());
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            try {
                date = format.parse(isoDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date == null) {
                try {
                    date = format2.parse(isoDate);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        }
        return date;
    }
}
