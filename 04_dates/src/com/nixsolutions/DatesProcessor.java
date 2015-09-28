package com.nixsolutions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class DatesProcessor {

    public int[] getLenghtOfEachMonthForParticularYear() {
	System.out.println("-----------------------------------");
	int year;
	final int YEAR_LENGTH = 12;
	int[] output = new int[YEAR_LENGTH];
	try {
	    System.out.println("Please enter YEAR in YYYY format.");
	    year = System.in.read();
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.YEAR, year);
	    for (int i = 0; i < YEAR_LENGTH; i++) {
		calendar.set(Calendar.MONTH, i);

		int numDays = calendar.getActualMaximum(Calendar.DATE);
		output[i] = numDays;
		System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
			+ "'s length is " + numDays);
		System.out.println("-----------------------------------");

	    }
	} catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    return null;
	}
	return output;

    }

    public int[] mondaysDetector() {
	return null;

    }

    public boolean isDateFirdayThirteenth() {
	System.out.println("-----------------------------------");
	Scanner in = new Scanner(System.in);
	String date = null;
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date d;
	try {
	    System.out.println("To define is  date is Friday Thirteenth, please enter date in next format dd/MM/yyyy");
	    date = in.nextLine();
	    d = (Date) formatter.parse(date);

	    String[] parts = d.toString().split(" ");
	    if (parts[0].equals("Fri") && parts[2].equals("13")) {

		System.out.println("This date is Friday Thirteenth");
	    } else {
		System.out.println("This date is NOT Friday Thirteenth");
	    }

	    System.out.println("-----------------------------------");
	} catch (ParseException e) {

	    e.printStackTrace();
	}

	return false;

    }

    public String dateCalc() {
	System.out.println("-----------------------------------");
	Scanner in = new Scanner(System.in);
	String date = null;
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date dateOne;
	Date currentDate;
	Calendar cal = Calendar.getInstance();

	try {
	    System.out.println("Please enter date in next format dd/MM/yyyy");
	    date = in.nextLine();
	    dateOne = (Date) formatter.parse(date);
	    currentDate = cal.getTime();
	    cal.setTime(new Date(currentDate.getTime() - dateOne.getTime()));
	    if (cal.get(Calendar.YEAR) - 1970 >= 0) {
		System.out.println((cal.get(Calendar.YEAR) - 1970) + " years, " + cal.get(Calendar.MONTH) + " monthes, "
			+ cal.get(Calendar.DAY_OF_MONTH) + " days");

	    } else {

		System.out.println("Please enter date from past. ");
		return null;

	    }

	} catch (ParseException e) {

	    e.printStackTrace();
	}
	return ((cal.get(Calendar.YEAR) - 1970) + " years, " + cal.get(Calendar.MONTH) + " monthes, "
		+ cal.get(Calendar.DAY_OF_MONTH) + " days");
    }

}
