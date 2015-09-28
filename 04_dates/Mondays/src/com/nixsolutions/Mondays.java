/**
 * 
 */
package com.nixsolutions;

import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Михаил
 *
 */
public class Mondays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int year;
		int month;
		Calendar calendar = Calendar.getInstance();
		System.out.println("Enter year:\n");
		Scanner in = new Scanner(System.in);
		year = in.nextInt();
		System.out.println("Enter number of the month:\n");
		month = in.nextInt();
		in.close();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);

		System.out.println("Dates of the all Mondays:\n");
		for (int i = 1; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			calendar.set(Calendar.DAY_OF_MONTH, i);
			if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
				System.out.println(
						calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + ":");
				System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + ":");
				System.out.print(calendar.get(Calendar.DAY_OF_MONTH) + "\n");
			}
		}
	}

}
