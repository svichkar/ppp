package com.nixsolutions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateProcessing {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Task #1.");
			System.out.println("Please enter a year.");
			printMonthLengthByYear(Integer.parseInt(br.readLine()));
		} catch (Exception ex) {
			System.out.println("Invalid year. Please start again.");
		}
		try {
			System.out.println("Task #2.");
			System.out.println("Please enter a year.");
			int year = Integer.parseInt(br.readLine());
			System.out.println("Please enter a month.");
			int month = Integer.parseInt(br.readLine());
			printMondays(year, month - 1);
		} catch (Exception ex) {
			System.out.println("Invalid value encountered. Please start again.");
		}
		try {
			System.out.println("Task #3.");
			System.out.println("Please enter a date in dd/MM/yyyy format.");
			System.out.println(
					isFridayThirteenth(new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine())));
		} catch (Exception ex) {
			System.out.println("Invalid date. Please start again.");
		}
		try {
			System.out.println("Task #4.");
			System.out.println("Please enter a date in dd/MM/yyyy format.");
			System.out.println(
					getCurrentDifference(new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine())));
		} catch (Exception ex) {
			System.out.println("Invalid date. Please start again.");
		}
		System.out.println("Task #5.");
		printDateInLocales();
	}

	private static void printMonthLengthByYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		System.out.printf("Lengths of months for selected year. Year: %s.%n", year);
		for (int i = 0; i < 12; i++) {
			cal.set(Calendar.MONTH, i);
			System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
	}
	
	private static void printMondays(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		for (int i = 1; i < cal.getActualMaximum(Calendar.WEEK_OF_MONTH) + 1; i++) {
			cal.set(Calendar.WEEK_OF_MONTH, i);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			if (cal.get(Calendar.MONTH) != month) {
				cal.set(Calendar.MONTH, month);
			} else {
				System.out.println(cal.get(Calendar.DAY_OF_MONTH));
			}
		}
	}
	
	private static Boolean isFridayThirteenth(Date input) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(input);		
		return cal.get(Calendar.DAY_OF_MONTH) == 13 &&
				cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}
	
	private static String getCurrentDifference(Date input) {
		Date currentDate = new Date();
		String resStr = "";
		if (input.before(currentDate)) {
			long difference = currentDate.getTime() - input.getTime();
			long days = TimeUnit.MILLISECONDS.toDays(difference);
			long months = TimeUnit.MILLISECONDS.toDays(difference) * 12 / 365 % 12;
			long years = TimeUnit.MILLISECONDS.toDays(difference) / 365;
			resStr = String.format("Elapsed - days: %1$s, months: %2$s, years: %3$s.", 
					days, months, years); 
		} else {
			System.out.
			println("Invalid input - date is bigger than current. Returning empty string.");
		}
		return resStr;
	}
	
	private static void printDateInLocales() {
		System.out.printf("Java 7 dates:%n");
		Date curDate = new Date();
		DateFormat df;
		df = DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA);
		System.out.printf("Canada locale: %1$s%n", df.format(curDate));
		df = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
		System.out.printf("Germany locale: %1$s%n", df.format(curDate));
		df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("en", "PK"));
		System.out.printf("Please note that english is a co-official language in Pakistan.%n");
		System.out.printf("Pakistan locale: %1$s%n", df.format(curDate));
		df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("vi", "VN"));
		System.out.printf("Vietnam locale: %1$s%n", df.format(curDate));
		System.out.printf("Java 8 dates:%n");
		LocalDate locCurDate = LocalDate.now();
		DateTimeFormatter dtf =  DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		System.out.printf("Canada locale: %1$s%n",
				locCurDate.format(dtf.withLocale(Locale.CANADA)));
		System.out.printf("Germany locale: %1$s%n",
				locCurDate.format(dtf.withLocale(Locale.GERMANY)));
		System.out.printf("Please note that english is a co-official language in Pakistan.%n");
		System.out.printf("Pakistan locale: %1$s%n",
				locCurDate.format(dtf.withLocale(new Locale("en", "PK"))));
		System.out.printf("Vietnam locale: %1$s%n",
				locCurDate.format(dtf.withLocale(new Locale("vi", "VN"))));
	}
}
