package com.apple.phoenix.MyCode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {
    public static void main(String[] args) {
        // create a SimpleDateFormat object with the input format
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        // parse the input string to a Date object
        Date date = null;
        try {
            date = inputFormat.parse("2023-04-17 15:30:45.123");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // create a Calendar object and set its time to the input date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // add 4 years and 6 months to the date
        calendar.add(Calendar.YEAR, 4);
        calendar.add(Calendar.MONTH, 6);
        // create a SimpleDateFormat object with the output format
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        // format the result as a string
        String result = outputFormat.format(calendar.getTime());
        // print the result
        System.out.println("Result: " + result);
    }
}

