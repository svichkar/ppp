package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class DateTask1 {
    public static void main(String[] args) {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Please, enter value for year (YYYY):");
	try {
	    printMonthLength(Integer.parseInt(reader.readLine()));
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Prints count of days of each month for specific year
     * 
     * @param specYear
     *            value of specific year
     */
    public static void printMonthLength(int specYear) {
	Calendar c = Calendar.getInstance();
	c.set(Calendar.YEAR, specYear);
	for (int i = 0; i < 12; i++) {
	    c.set(Calendar.MONTH, i);
	    System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
    }
}
