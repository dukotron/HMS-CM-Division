package com.example.homie.views.formatters;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAxisValueFormatter extends ValueFormatter {

    private long referenceTimestamp; // minimum timestamp in your data set
    private DateFormat dataFormat;
    private Date date;

    public DateAxisValueFormatter(long referenceTimestamp) {
        this.referenceTimestamp = referenceTimestamp;
        this.dataFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.date = new Date();
    }
    /**
     * Called when a value from an axis is to be formatted
     * before being drawn. For performance reasons, avoid excessive calculations
     * and memory allocations inside this method.
     *
     * @param value the value to be formatter
     * @return String representation of the value
     */
    @Override
    public String getFormattedValue(float value) {
        // convertedTimestamp = originalTimestamp - referenceTimestamp
        long convertedTimestamp = (long) value;

        // Retrieve original timestamp
        long originalTimestamp = referenceTimestamp + convertedTimestamp;

        // Convert timestamp to y-m-d
        date.setTime(originalTimestamp);

        return dataFormat.format(date);
    }
}
