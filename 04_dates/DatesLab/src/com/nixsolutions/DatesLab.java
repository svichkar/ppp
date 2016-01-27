package com.nixsolutions;

import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

public class DatesLab {

	public static void getMonthsLength(int year) {
		System.out.println("Number of days in month for " + year + ":");
		for (int month = 1; month <= 12; month++) {
			YearMonth yearMonthObj = YearMonth.of(year, month);
			System.out.println(yearMonthObj.getMonth() + " - " + yearMonthObj.lengthOfMonth());
		}
	}

	public static void getMondays(String yearStr, String monthStr) {
		try {
			Month month = Month.valueOf(monthStr.toUpperCase());
			LocalDate date = Year.parse(yearStr).atMonth(month).atDay(1)
					.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
			System.out.println("All Mondays of " + monthStr + ", " + yearStr + ":");
			while (date.getMonth() == month) {
				System.out.println(date);
				date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			}
		} catch (IllegalArgumentException | DateTimeParseException ex) {
			System.out.println("Input is in incorrect format. Please, check year and month values.");
		}
	}

	public static boolean isFridayTheThirteenth(String dateStr) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.parse(dateStr, formatter);
			if (date.getDayOfMonth() == 13 && date.getDayOfWeek() == DayOfWeek.FRIDAY) {
				return true;
			}
		} catch (DateTimeParseException exc) {
			System.out.printf("Input value \'%s\' is in incorrect format. Use dd-MM-yyyy format only.%n", dateStr);
		}
		return false;
	}

	public static String getPassedTime(String dateStr) {
		String result = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate dateFrom = LocalDate.parse(dateStr, formatter);
			LocalDate dateTo = LocalDate.now();
			LocalDate dateTemp = LocalDate.from(dateFrom);
			if (dateTo.isAfter(dateFrom)) {
				long years = dateTemp.until(dateTo, ChronoUnit.YEARS);
				dateTemp = dateTemp.plusYears(years);
				long months = dateTemp.until(dateTo, ChronoUnit.MONTHS);
				dateTemp = dateTemp.plusMonths(months);
				long days = dateTemp.until(dateTo, ChronoUnit.DAYS);
				dateTemp = dateTemp.plusDays(days);
				result = years + " years " + months + " months " + days + " days";
			} else {
				result = "date is greater than current date";
			}
		} catch (DateTimeParseException exc) {
			System.out.printf("Input value \'%s\' is in incorrect format. Use dd-MM-yyyy format only.%n", dateStr);
		}
		return result;
	}

	public static void showDateUseLocal(Locale locale) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
		Date currentDate = new Date();
		System.out.printf("Current date for %s - %s%n", locale.getDisplayCountry(), df.format(currentDate));
	}

	public static void main(String[] args) {
		System.out.println("Task 1:");
		getMonthsLength(2007);
		System.out.println("Task 2:");
		getMondays("2016", "October");
		System.out.println("Task 3:");
		String date1 = "13-05-2016";
		System.out.printf("Is \'%s\' Friday the 13th? - %b%n", date1, isFridayTheThirteenth(date1));
		System.out.println("Task 4:");
		String date2 = "26-01-2014";
		System.out.printf("From \'%s\' have passed: %s.%n", date2, getPassedTime(date2));
		System.out.println("Task 5:");
		showDateUseLocal(new Locale("en", "CA"));
		showDateUseLocal(new Locale("de", "DE"));
		showDateUseLocal(new Locale("vi", "VN"));
		showDateUseLocal(new Locale("en", "PK"));
	}
}
