package com.nixsolutions;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class DateFive {

	public static void main(String[] args) {
		//Java 7
		System.out.printf("Java 7 dates:%n");
		Date curDate = new Date();
		DateFormat df;
		df = DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA);
		System.out.printf("Canada locale: %1$s%n", df.format(curDate));
		df = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
		System.out.printf("Germany locale: %1$s%n", df.format(curDate));
		//Need to do
		Locale pakiLoc = new Locale.Builder().setLanguage("ur").setRegion("PK").setScript("Deva").build();
		//pakiLoc. m49 code - 586, Asia/Karachi
		df = DateFormat.getDateInstance(DateFormat.FULL, pakiLoc);
		//("pa", "PK")
		System.out.printf("Pakistan locale: %1$s%n", df.format(curDate));
		df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("vi", "VN"));
		System.out.printf("Vietnam locale: %1$s%n", df.format(curDate));
		//Java 8
		System.out.printf("Java 8 dates:%n");
		LocalDate locCurDate = LocalDate.now();
		DateTimeFormatter dtf =  DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		System.out.printf("Canada locale: %1$s%n", locCurDate.format(dtf.withLocale(Locale.CANADA)));
		System.out.printf("Germany locale: %1$s%n", locCurDate.format(dtf.withLocale(Locale.GERMANY)));
		//Same with Pakistan locale
		System.out.printf("Pakistan locale: %1$s%n", locCurDate.format(dtf.withLocale(pakiLoc)));
		System.out.printf("Vietnam locale: %1$s%n", locCurDate.format(dtf.withLocale(new Locale("vi", "VN"))));
	}

}
