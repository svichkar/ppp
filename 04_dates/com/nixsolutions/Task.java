package com.nixsolutions;

import java.util.Scanner;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Task {

	public static void main(String[] args) {
		System.out.println("===================\nPlease enter a year");
		Scanner inYear = new Scanner(System.in);
		String userYear = inYear.next();
		monthLength(Integer.valueOf(userYear));
		System.out.println("===================\nPlease enter a year");
		inYear = new Scanner(System.in);
		userYear = inYear.next();
		System.out.println("Please enter a number of month");
		Scanner inMonth = new Scanner(System.in);
		String userMonth = inMonth.next();
		datesMonday(Integer.valueOf(userYear), Integer.valueOf(userMonth) - 1);
		System.out.println("===================\nPlease enter a date in DD-MM-YYYY format");
		Scanner inDate = new Scanner(System.in);
		String userDate = inDate.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate locDate = LocalDate.parse(userDate, formatter);
		Instant instant = locDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		checkFridayThirteen(date);
		System.out.println("===================\nPlease enter the previous date in DD-MM-YYYY format");
		inDate = new Scanner(System.in);
		userDate = inDate.next();
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		locDate = LocalDate.parse(userDate, formatter);
		instant = locDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		date = Date.from(instant);
		checkDateDifference(date);
		System.out.println("===================");
		localDates();
		inYear.close();
		inMonth.close();
		inDate.close();

	}

	private static void monthLength(int year) {
		Calendar myCalendar = Calendar.getInstance();
		for (int i = 0; i < 12; i++) {
			myCalendar.set(year, i, 1);
			System.out.println(myCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " "
					+ myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		}
	}

	private static void datesMonday(int year, int month) {
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.set(year, month, 1);
		for (int i = 1; i < myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++) {
			myCalendar.set(Calendar.DAY_OF_MONTH, i);
			if (myCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
				System.out.println(myCalendar.get(Calendar.DAY_OF_MONTH));

		}
	}

	private static void checkFridayThirteen(Date date) {
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.setTime(date);
		if (myCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && myCalendar.get(Calendar.DAY_OF_MONTH) == 13)
			System.out.println(date + " This is Fri, 13th");
		else
			System.out.println(date + " This is not Fri, 13th");
	}

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
