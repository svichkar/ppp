package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTask3 {
    public static void main(String[] args) {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Please, enter value for date (dd.MM.YYYY):");
	String date = "";
	try {
	    date = reader.readLine();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	checkFiday13(date);
    }

    /**
     * Checks if specific date is Friday 13
     * 
     * @param date
     *            string value of date
     */
    public static void checkFiday13(String date) {
	Calendar c = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	try {
	    c.setTime(sdf.parse(date));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	// check if specific date is Friday 13
	if (c.get(Calendar.DAY_OF_MONTH) == 13 && c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
	    System.out.println(sdf.format(c.getTime()) + " is Friday 13");
	} else {
	    System.out.println(sdf.format(c.getTime()) + " is not Friday 13");
	}
    }
}
