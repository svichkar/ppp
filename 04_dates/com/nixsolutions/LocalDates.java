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
public class LocalDates {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDates dates = new LocalDates();
		dates.localDates();

	}

	private void localDates() {

		Date java7CurrentDate = new Date();
		LocalDate java8CurrentDate = LocalDate.now();
		
		DateTimeFormatter jEightFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		Locale PAKISTAN = new Locale("ur", "PK");
		Locale VIETNAM = new Locale("vi", "VN");

		System.out.println("Canadian date (java 8): " + java8CurrentDate.format(jEightFormat.withLocale(Locale.CANADA)));
		System.out.println("Canadian date (java 7): "+ DateFormat.getDateInstance(DateFormat.FULL, Locale.CANADA).format(java7CurrentDate));
		System.out.println("=======================");

		System.out.println("German date (java 8): " + java8CurrentDate.format(jEightFormat.withLocale(Locale.GERMANY)) );
		System.out.println("German date (java 7): "
				+ DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY).format(java7CurrentDate));
		System.out.println("=======================");

		System.out.println("Pakistan date (java 8): " + java8CurrentDate.format(jEightFormat.withLocale(PAKISTAN)));
		System.out.println("Pakistan date (java 7): "
				+ DateFormat.getDateInstance(DateFormat.FULL, PAKISTAN).format(java7CurrentDate));
		System.out.println("=======================");

		System.out.println("Vietnam date (java 8): " + java8CurrentDate.format(jEightFormat.withLocale(VIETNAM)));
		System.out.println("Vietnam date (java 7): " + DateFormat.getDateInstance(DateFormat.FULL, VIETNAM).format(java7CurrentDate));

	}
}
