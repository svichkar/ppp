package com.nixsolutions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

public class DatesProcessor {

    final static String months[] = { null, "January", "February", "March", "April", "May", "June", "July", "August",
	    "September", "October", "November", "December" };

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
			+ "'s length is " + numDays + " day(s).");
		System.out.println("-----------------------------------");

	    }
	} catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    return null;
	}
	return output;

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

    public void mondayFinder() {
	System.out.println("-----------------------------------");
	Scanner in = new Scanner(System.in);
	String date = null;
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date dateOne;
	System.out.println("-----------------------------------");
	try {

	    System.out.println("Please enter date in next format dd/MM/yyyy");
	    date = in.nextLine();
	    date = "01/" + date;
	    dateOne = (Date) formatter.parse(date);

	    Month month = Month.valueOf(months[dateOne.getMonth() + 1].toUpperCase());
	    LocalDate dateOf = Year.now().atMonth(month).atDay(1)
		    .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
	    Month mi = dateOf.getMonth();
	    System.out.println("Here is the list of MONDAYS in " + months[dateOne.getMonth() + 1]);
	    while (mi == month) {
		System.out.printf("%s%n", dateOf);
		dateOf = dateOf.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		mi = dateOf.getMonth();
	    }

	} catch (Exception e) {
	}
	System.out.println("-----------------------------------");

    }

    public void showDateInDifferenLocales() {
	int style = DateFormat.FULL;

	Date date = new Date();
	DateFormat df;
	df = DateFormat.getDateInstance(style, Locale.CANADA);
	System.out.println("Canada : " + df.format(date));

	df = DateFormat.getDateInstance(style, Locale.GERMANY);
	System.out.println("Germany : " + df.format(date));

	df = DateFormat.getDateInstance(style, Locale.PRC);
	System.out.println("PRC : " + df.format(date));

	df = DateFormat.getDateInstance(style, new Locale("vi", "VN"));
	System.out.println("Vietnam : " + df.format(date));
	System.out.println("-----------------------------------");

	ZonedDateTime timeCanada = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Canada/Central"));
	System.out.println("Canada : " + timeCanada);

	ZonedDateTime timeGermany = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/Berlin"));
	System.out.println("Germany : " + timeGermany);

	ZonedDateTime timePakistan = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Karachi"));
	System.out.println("Pakistan : " + timePakistan);

	ZonedDateTime timeVietnam = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Ho_Chi_Minh"));
	System.out.println("Pakistan : " + timeVietnam);
	System.out.println("-----------------------------------");

    }

}
