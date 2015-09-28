/**
 * 
 */
package com.nixsolutions;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author mixeyes
 *
 */
public class Friday13 {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		System.out.println("Please enter some date in format:dd.MM.yyyy \n");
		Scanner in = new Scanner(System.in);
		String date = in.nextLine();
		in.close();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

		Date dd = df.parse(date);
		calendar.setTime(dd);
		System.out.println(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())+":");
		System.out.println(calendar.getDisplayName(Calendar.DAY_OF_MONTH, Calendar.LONG, Locale.getDefault())+":");
		System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+":");
		if (calendar.get(Calendar.DAY_OF_MONTH) == 13) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				System.out.println("today is Friday 13 \n");
			} else {
				System.out.println("today is not Friday\n");
			}
		} else {
			System.out.println("today is not 13 \n");
		}
	}

}
