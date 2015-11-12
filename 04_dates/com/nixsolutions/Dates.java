/**
 * 
 */
package com.nixsolutions;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Evgeniy Fomin
 *
 */
public class Dates {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dates dates = new Dates();
/*		
		 * int year = dates.getYearFromConsole(); if (year > 0) {
		 * dates.countNumberOfDaysInMonth(year); } else { System.out.println(
		 * "You put incorrect year. Please rerun this program"); }
		 */

		//dates.datesOfMondays(2011, Calendar.NOVEMBER);
		
		//dates.localDates();

	}

	/**
	

	/**
	 *  
	 * 
	 * 
	 * 
	 * */

	
	

	private static void checkDateDifference(Date startDate) {
		Date currentDate = new Date();
		Calendar difference = Calendar.getInstance();
		difference.setTime(new Date(currentDate.getTime() - startDate.getTime()));
		System.out.printf("Period passed: %d year(s), %d month(s), %d day(s)%n", (difference.get(Calendar.YEAR) - 1970),
				difference.get(Calendar.MONTH), difference.get(Calendar.DAY_OF_MONTH));
	}

	private static void localDates() {
		System.out.println("Java 8 Dates:");
		LocalDate newCurrentDate = LocalDate.now();
		DateTimeFormatter newFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		System.out.println("Canada date: " + newCurrentDate.format(newFormat.withLocale(Locale.CANADA)));
		System.out.println("Germany date: " + newCurrentDate.format(newFormat.withLocale(Locale.GERMANY)));
		Locale PAKISTAN = new Locale("ur", "PK");
		System.out.println("Pakistan date: " + newCurrentDate.format(newFormat.withLocale(PAKISTAN)));
		Locale VIETNAM = new Locale("vi", "VN");
		System.out.println("Vietnam date: " + newCurrentDate.format(newFormat.withLocale(VIETNAM)));

		System.out.println("Java 7 Dates:");
		Date currentDate = new Date();
		DateFormat format;
		format = DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA);
		System.out.println("Canada date: " + format.format(currentDate));
		format = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
		System.out.println("Germany date: " + format.format(currentDate));
		format = DateFormat.getDateInstance(DateFormat.FULL, PAKISTAN);
		System.out.println("Pakistan date: " + format.format(currentDate));
		format = DateFormat.getDateInstance(DateFormat.FULL, VIETNAM);
		System.out.println("Vietnam date: " + format.format(currentDate));

	}
}
