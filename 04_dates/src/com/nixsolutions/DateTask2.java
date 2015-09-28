package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Calendar;

public class DateTask2 {
    public static void main(String[] args) {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Please, enter value for month and year (MM-YYYY):");
	String monthYear = "";
	try {
	    monthYear = reader.readLine();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	printMondays(monthYear);
    }

    /**
     * Prints dates of Mondays of specific month and year
     * 
     * @param monYear
     *            is value of month and year
     */
    public static void printMondays(String monYear) {
	Calendar cal = Calendar.getInstance();
	int m = Integer.parseInt(monYear.split("-")[0]); // get month
	int y = Integer.parseInt(monYear.split("-")[1]); // get year
	cal.set(Calendar.MONTH, m); // set month
	cal.set(Calendar.YEAR, y); // set year
	DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
	// find Mondays dates and print them
	for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
	    cal.set(Calendar.DAY_OF_MONTH, i);
	    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
		System.out.println(df.format(cal.getTime()));
	    }
	}
    }
}
