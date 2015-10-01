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
public class MonthsLength {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		System.out.println("Enter year:\n");
		Scanner in = new Scanner(System.in);
		int year = in.nextInt();
		in.close();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		System.out.println("Numbers of the days by monthes:\n");
		for (int i = 0; i < 12; i++) {
			calendar.set(Calendar.MONTH, i);
			System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+":");
			System.out.print(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+"\n");
		}
	}

}
