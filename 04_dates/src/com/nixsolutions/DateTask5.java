package com.nixsolutions;

import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTask5 {
    public static void main(String[] args) {
	// Java 7
	Date currentDate = new Date();
	DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA);
	System.out.println(df.format(currentDate));
	df = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
	System.out.println(df.format(currentDate));
	Locale locale = new Locale("en", "PK");
	df = DateFormat.getDateInstance(DateFormat.FULL, locale);
	System.out.println(df.format(currentDate));
	locale = new Locale("vi", "VN");
	df = DateFormat.getDateInstance(DateFormat.FULL, locale);
	System.out.println(df.format(currentDate));
	// Java 8
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
	LocalDateTime curDate = LocalDateTime.now();
	dtf = dtf.withLocale(Locale.CANADA);
	System.out.println(dtf.format(curDate));
	dtf = dtf.withLocale(Locale.GERMANY);
	System.out.println(dtf.format(curDate));
	locale = new Locale("en", "PK");
	dtf = dtf.withLocale(locale);
	System.out.println(dtf.format(curDate));
	locale = new Locale("vi", "VN");
	dtf = dtf.withLocale(locale);
	System.out.println(dtf.format(curDate));
    }
}
