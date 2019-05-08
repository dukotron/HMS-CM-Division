package com.example.homie.network.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatConverter {

    public static String convertDateToDotNetFormat(Date date) {
        String pattern = "yyyy-MM-dd'T'HH:MM:ss.SSSSSSS";

        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
