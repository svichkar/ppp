package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


public class DateCalculater {

	public static void getNumberOfDaysInEachMonth(String year) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		int[] daysInMonth = new int[12];
		for (int i = 0; i < 12; i++) {
			calendar.set(Calendar.MONTH, i);
			calendar.set(Calendar.DATE, 1);
			daysInMonth[i] = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("Month: " + (i + 1) + ", count of days is " + daysInMonth[i]);
		}
	}

	public static void getMondaysDate(String year, String month) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		List<Integer> datesMonday = new ArrayList<Integer>();
		for (int i = 1; i <= daysInMonth; i++) {
			calendar.set(Calendar.DATE, i);
			if (calendar.get(Calendar.DAY_OF_WEEK) == 2)
				datesMonday.add(i);
		}
		for (int i = 0; i < datesMonday.size(); i++) {
			System.out.println("Monday is " + datesMonday.get(i));
		}
	}

	public static void checkIfFridayThirteen(LocalDate date) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, date.getYear());
		calendar.set(Calendar.MONTH, date.getMonthValue() - 1);
		calendar.set(Calendar.DATE, date.getDayOfMonth());
		if (date.getDayOfMonth() == 13) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
				System.out.println("Date is Friday, 13");
		} else
			System.out.println("Date is not Friday, 13");
	}

	public static void getDaysPassed(LocalDate dateFrom) {

		Date currDate = new Date();
		Date fromDate = Date.from(dateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());

		Calendar diff = new GregorianCalendar();
		diff.setTime(new Date(currDate.getTime() - fromDate.getTime()));

		System.out.println("Days passed: " + (diff.get(Calendar.YEAR) - 1970) + " years, " + diff.get(Calendar.MONTH)
				+ " monthes, " + diff.get(Calendar.DAY_OF_MONTH) + " days");
	}

	public static void printDateInSpecificLocale() {

		Date currDate = new Date();
		
		System.out.println("Current date in different locales");
				
		ZonedDateTime zoneDateTime = ZonedDateTime.now();
		DateTimeFormatter canadaFrm = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy", Locale.CANADA);
		System.out.println("Canada: " + zoneDateTime.format(canadaFrm));

		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
		System.out.println("Germany: " + df.format(currDate));

		Locale pakistanLocale = new Locale("ur", "PK");
		df = DateFormat.getDateInstance(DateFormat.FULL, pakistanLocale);
		System.out.println("Pakistan: " + df.format(currDate));

		Locale vietnamLocale = new Locale("vi", "VE");
		df = DateFormat.getDateInstance(DateFormat.FULL, vietnamLocale);
		System.out.println("Vietnam: " + df.format(currDate));

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String valueYear = "";
		String valueMonth = "";
		String fullDate = "";
		
		System.out.print("Please enter a year: ");
		valueYear = br.readLine();
		getNumberOfDaysInEachMonth(valueYear);
		System.out.println();

		System.out.print("Please enter a month (number): ");
		valueMonth = br.readLine();
		getMondaysDate(valueYear, valueMonth);
		System.out.println();

		System.out.print("Please enter a full date in format 'MMM d yyy': ");
		fullDate = br.readLine();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
			LocalDate date = LocalDate.parse(fullDate, formatter);
			
			checkIfFridayThirteen(date);
			System.out.println();
			
			getDaysPassed(date);
			System.out.println();

		} catch (DateTimeException ex) {
			System.out.println("Date is not parsable");
		}

		printDateInSpecificLocale();

	}
}
