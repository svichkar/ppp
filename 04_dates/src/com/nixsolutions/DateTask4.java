package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTask4 {

    public static void main(String[] args) {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Please, enter value for date (dd.MM.YYYY):");
	String date = "";
	try {
	    date = reader.readLine();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	countPassedTime(date);
    }

    /**
     * Calculates difference between dates
     * 
     * @param date
     *            value for specific date
     * 
     */
    public static void countPassedTime(String date) {
	Date currentDate = new Date();
	Date specificDate = new Date();
	Calendar diff = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	try {
	    specificDate = sdf.parse(date); // set specific date
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	// check if date from past
	if (specificDate.before(currentDate)) {
	    // calculate difference between dates 
	    diff.setTime(new Date(currentDate.getTime() - specificDate.getTime()));
	    System.out.println((diff.get(Calendar.YEAR) - 1970) + " years, " + diff.get(Calendar.MONTH) + " monthes, "
		    + diff.get(Calendar.DAY_OF_MONTH) + " days");
	} else {
	    System.out.println("Specified date is from future!!!");
	}
    }

}
