package com.nixsolutions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class DatesStudy {

	public static void main(String[] args) throws ParseException {

		System.out.println("Please enter a date in the next format: dd MM yyyy");
		Scanner scan = new Scanner(System.in);
		Calendar cal = Calendar.getInstance();
		Locale def = Locale.getDefault();


		DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
		cal.setLenient(false);
		dateFormat.setCalendar(cal);
		Date df = dateFormat.parse(scan.nextLine());

		System.out.println("Here are lenght of months in the given year");
		lengthOfMonths(cal, def, df);
		
		System.out.println("\nHere are all the mondays of the month in the given year");
		getMondays(cal, def, df);
		
		System.out.println("\nIs the date Friday 13?");
		fridayThirteen(cal, def, df);
		
		System.out.println("\nTime passed since the date:");
		howLong(cal, def, df);
		
		System.out.println("\nLocales:");
		checkLocales();

	}

	public static void lengthOfMonths(Calendar cal, Locale def, Date df) {

		cal.setTime(df);
		cal.set(Calendar.MONTH, 0);
		for (int i = 0; i < 11; i++) {

			System.out.println("days in " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG, def) + " "
					+ cal.get(Calendar.YEAR) + ": " + cal.getActualMaximum(Calendar.DAY_OF_MONTH));

			cal.roll(Calendar.MONTH, 1);
		}
	}

	public static void getMondays(Calendar cal, Locale def, Date df) {

		cal.setTime(df);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		for (int i = 1; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {

			if (cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, def).equals("Monday")) {

				System.out.println(DateFormat.getDateInstance(DateFormat.FULL, def).format(cal.getTime()));

			}

			cal.roll(Calendar.DAY_OF_MONTH, 1);
		}

	}

	public static void fridayThirteen(Calendar cal, Locale def, Date df) {

		cal.setTime(df);

		if (cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, def).equals("Friday")
				&& cal.get(Calendar.DAY_OF_MONTH) == 13) {
			System.out.println("this is the day!!!");
		} else {
			System.out.println("this day is not Fri 13!!!");
		}

	}

	public static void howLong(Calendar cal, Locale def, Date df) {

		Calendar old = Calendar.getInstance();
		Calendar curr = Calendar.getInstance();

		old.setTime(df);
		curr.setTime(new Date());

		old.set(Calendar.HOUR_OF_DAY, 1);
		old.set(Calendar.MINUTE, 1);

		curr.set(Calendar.HOUR_OF_DAY, 1);
		curr.set(Calendar.MINUTE, 1);

		long timeDiff = curr.getTimeInMillis() - old.getTimeInMillis();
		// year ms 31536000000
		// month ms 2592000000
		// day ms 86400000
		long years = timeDiff / 31535999000L;
		long months = (timeDiff - (years * 31536000000L)) / 2591999000L;
		long days = (timeDiff - ((years * 31536000000L) + (months * 2592000000L))) / 86399000L;

		System.out.println(years + " years " + months + " months " + days + " days");

	}

	public static void checkLocales() {

		String dateOut;

		// Canada locale using DateFormat
		Locale canadaLoc = Locale.CANADA;
		dateOut = DateFormat.getDateInstance(DateFormat.FULL, canadaLoc).format(new Date());
		System.out.println(dateOut + " " + canadaLoc.toString());

		// Germany locale using DateFormat
		Locale germanyLoc = Locale.GERMANY;
		dateOut = DateFormat.getDateInstance(DateFormat.FULL, germanyLoc).format(new Date());
		System.out.println(dateOut + " " + germanyLoc.toString());

		// Pakistan locale using DateFormat
		Locale pakistanLoc = new Locale("en", "PK");
		dateOut = DateFormat.getDateInstance(DateFormat.FULL, pakistanLoc).format(new Date());
		System.out.println(dateOut + " " + pakistanLoc.toString());

		// Vietnam locale using DateFormat
		Locale vietnamLoc = new Locale("vi", "VN");
		dateOut = DateFormat.getDateInstance(DateFormat.FULL, vietnamLoc).format(new Date());
		System.out.println(dateOut + " " + vietnamLoc.toString());

	}

}
